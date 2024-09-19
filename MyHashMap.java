public class MyHashMap {

    public static void main(String[] args) {
        MyHashMap map = new MyHashMap();

        map.put("matt", "dragon fruit");
        map.put("matt", "raspberry");
        map.put("brian", "kiwi");
        map.put("mike", "mango");

        System.out.println(map.get("matt"));
        System.out.println(map.get("brian"));
        System.out.println(map.get("mike"));
        System.out.println(map.get("not in the map"));
    }

    private static final double LOADFACTOR = 0.7;

    private int maxSize;

    private int size = 0;

    private MyLinkedList<Pair<String, String>>[] values;

    public MyHashMap() {
        this(1);
    }

    public MyHashMap(int maxSize) {
        this.maxSize = maxSize;
        this.values = new MyLinkedList[maxSize];
    }

    public void put(String key, String value) {
        int hash = hash(key);
        MyLinkedList valuesList = values[hash];
        if (valuesList == null) {
            valuesList = new MyLinkedList<Pair<String, String>>();
            values[hash] = valuesList;
        }


        Pair<String, String> keyValuePair = getPair(key, hash);
        if (keyValuePair == null) {
            valuesList.add(new Pair<>(key, value));
            size++;
        } else {
            keyValuePair.value = value;
        }


        if (size / (double)maxSize > LOADFACTOR) {
            MyHashMap newMap = new MyHashMap(2 * maxSize);
            for (int i = 0; i < maxSize; ++i) {
                MyLinkedList currentValuesList = values[i];
                if (currentValuesList != null) {
                    for (Object valueObj : currentValuesList) {
                        keyValuePair = (Pair<String, String>) valueObj;
                        newMap.put(keyValuePair.key, keyValuePair.value);
                    }
                }
            }
            this.maxSize = newMap.maxSize;
            this.values = newMap.values;
        }
    }

    public String get(String key) {
        return get(key, hash(key));
    }

    private String get(String key, int hash) {
        Pair<String, String> keyValuePair = getPair(key, hash);
        if (keyValuePair == null) {
            return null;
        }
        return keyValuePair.value;
    }

    private Pair<String, String> getPair(String key, int hash) {
        MyLinkedList valuesList = values[hash];
        if (valuesList == null) {
            return null;
        }
        for (Object valueObj : valuesList) {
            Pair<String, String> keyValuePair = (Pair<String, String>) valueObj;
            if (keyValuePair.key.equals(key)) {
                return keyValuePair;
            }
        }
        return null;
    }

    private int hash(String key) {
        return key.hashCode() % maxSize;
    }

    private class Pair<K, V> {
        public K key;
        public V value;

        Pair(K key, V value) {
            this.key = key;
            this.value = value;
        }

        @Override
        public String toString() {
            return "[ " + key + " : " + value + " ]";
        }
    }
}
