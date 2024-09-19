import java.util.Iterator;

public class MyLinkedList<T> implements Iterable<T> {

    public static void main(String[] args) {
        MyLinkedList<Integer> ints = new MyLinkedList<>();

        ints.add(1);
        ints.add(2);
        ints.add(3);

        System.out.println(ints.get(0));
        System.out.println(ints.get(2));

        for (Integer i : ints) {
            System.out.println(i);
        }
    }

    private Node<T> head = new Node<>(null);

    private Node<T> last;

    public void add(T thing) {
        Node<T> newNode = new Node<>(thing);

        if (last == null) {
            head.next = last = newNode;
        } else {
            last.next = newNode;
            last = newNode;
        }
    }

    public T get(int index) {
        Node<T> current = head.next;
        for (int i = 0; i < index; ++i) {
            current = current.next;
        }
        return current.value;
    }

    public T getLast() {
        return last.value;
    }

    @Override
    public Iterator<T> iterator() {
        return new MyLinkedListIterator<>(head);
    }

    private class MyLinkedListIterator<T> implements Iterator<T> {

        private Node<T> current;

        public MyLinkedListIterator(Node<T> current) {
            this.current = current;
        }

        @Override
        public boolean hasNext() {
            return current.next != null;
        }

        @Override
        public T next() {
            current = current.next;
            return current.value;
        }
    }

    private class Node<T> {
        public T value;

        public Node<T> next;

        public Node(T value) {
            this.value = value;
        }
    }
}
