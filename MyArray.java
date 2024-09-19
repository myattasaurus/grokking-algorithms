import java.util.Random;

public class MyArray {

    public static void main(String[] args) {

        System.out.println("Binary search (chapter 1):");
        int [] nums = new int[100];
        for (int i = 0; i < 100; ++i) {
            nums[i] = 2*i;
        }
        System.out.println(new MyArray(nums).binarySearch(24));
        System.out.println();

        System.out.println("Recursive binary search (chapter 4):");
        System.out.println(new MyArray(nums).recursiveBinarySearch(24));
        System.out.println();

        System.out.println("Bubble sort (chapter 2):");
        new MyArray(8, 4, 4, 3, 9, 7, 1, 2, 0, 6)
                .bubbleSort()
                .print();

        System.out.println("Recursive sum (chapter 4):");
        System.out.println(new MyArray(1, 2, 3, 4).recursiveSum());
        System.out.println();

        System.out.println("Recursive count (chapter 4):");
        System.out.println(new MyArray(1, 2, 3, 4).recursiveCount());
        System.out.println();

        System.out.println("Recursive max (chapter 4):");
        System.out.println(new MyArray(8, 4, 4, 3, 9, 7, 1, 2, 0, 6).recursiveMax());
        System.out.println();

        System.out.println("Quicksort (chapter 4):");
        new MyArray(8, 4, 4, 3, 9, 7, 1, 2, 0, 6)
                .quickSort()
                .print();
    }
    
    private static Random random = new Random();

    public int[] nums;

    public MyArray(int... nums) {
        this.nums = nums;
    }

    public int binarySearch(int target) {
        int lowIndex = 0;
        int highIndex = nums.length - 1;
        int currentIndex;

        while (highIndex >= lowIndex) {
            currentIndex = (lowIndex + highIndex) / 2;
            if (nums[currentIndex] == target) {
                return currentIndex;
            } else if (nums[currentIndex] > target) {
                highIndex = currentIndex - 1;
            } else {
                lowIndex = currentIndex + 1;
            }
        }
        return -1;
    }

    public int recursiveBinarySearch(int target) {
        return recursiveBinarySearch(target, 0, nums.length - 1);
    }

    private int recursiveBinarySearch(int target, int low, int high) {
        if (nums[low] == target) {
            return low;
        } else if (nums[high] == target) {
            return high;
        } else if (low >= high) {
            return -1;
        }
        int middle = (low + high) / 2;
        if (nums[middle] <= target) {
            return recursiveBinarySearch(target, middle, high);
        } else {
            return recursiveBinarySearch(target, low, middle - 1);
        }
    }

    public int recursiveMax() {
        return recursiveMax(0);
    }

    public int recursiveMax(int cursor) {
        if (cursor == nums.length - 1) {
            return nums[cursor];
        }
        int current = nums[cursor];
        int max = recursiveMax(cursor + 1);
        return current > max ? current : max;
    }

    public int recursiveCount() {
        return recursiveCount(0);
    }

    public int recursiveCount(int cursor) {
        try {
            int i = nums[cursor];
            return 1 + recursiveCount(cursor + 1);
        } catch (Exception e) {
            return 0;
        }
    }

    public int recursiveSum() {
        return recursiveSum(0);
    }

    private int recursiveSum(int cursor) {
        if (nums.length - cursor <= 1) {
            return nums[cursor];
        }
        return nums[cursor] + recursiveSum(cursor + 1);
    }

    public MyArray bubbleSort() {
        for (int lowIndex = 0; lowIndex < nums.length - 1; ++lowIndex) {
            int smallestIndex = lowIndex;
            for (int i = lowIndex + 1; i < nums.length; ++i) {
                if (nums[smallestIndex] > nums[i]) {
                    smallestIndex = i;
                }
            }
            swap(smallestIndex, lowIndex);
        }
        return this;
    }

    public MyArray quickSort() {
        quickSort(0, nums.length - 1);
        return this;
    }

    private void quickSort(int lowIndex, int highIndex) {
        if (highIndex - lowIndex < 2) {
            return;
        }

        int pivotIndex = random.nextInt(highIndex - lowIndex) + lowIndex;
        int pivot = nums[pivotIndex];
        int lowSwapIndex = lowIndex - 1;
        int highSwapIndex = highIndex + 1;

        for (int i = lowIndex; i < highSwapIndex; ++i) {
            if (nums[i] < pivot) {
                swap(i, ++lowSwapIndex);
            } else if (nums[i] > pivot) {
                swap(i, --highSwapIndex);
                --i;
            }
        }
        quickSort(lowIndex, lowSwapIndex);
        quickSort(highSwapIndex, highIndex);
    }

    private void swap(int index1, int index2) {
        if (index1 == index2) {
            return;
        }
        int temp = nums[index1];
        nums[index1] = nums[index2];
        nums[index2] = temp;
    }

    public void print() {
        char print = '[';
        for (int num : nums) {
            System.out.print(print);
            System.out.print(num);
            print = ',';
        }
        System.out.println(']');
        System.out.println();
    }
}
