import java.util.Arrays;
import java.util.Random;

public class AlgorithmComplexityTest {

    // Test sizes required for the activity
    private static final int[] TEST_SIZES = {
        1000, 5000, 10000, 50000, 100000
    };

    private static final Random random = new Random();

    public static void main(String[] args) {

        System.out.println("==============================================================");
        System.out.println("              ALGORITHM COMPLEXITY TEST");
        System.out.println("==============================================================");

        warmUp();

        for (int size : TEST_SIZES) {

            System.out.println();
            System.out.println("Input Size: " + size);
            System.out.println("--------------------------------------------------------------");
            System.out.printf("%-20s %15s%n", "Algorithm", "Time (ms)");
            System.out.println("--------------------------------------------------------------");

            int[] original = generateRandomArray(size);

            int key = original[random.nextInt(original.length)];

            long start = System.nanoTime();
            linearSearch(original, key);
            long end = System.nanoTime();

            System.out.printf("%-20s %15.4f%n",
                    "Linear Search", toMilliseconds(end - start));

            // Binary Search requires a sorted array.
            int[] binaryArray = Arrays.copyOf(original, original.length);
            Arrays.sort(binaryArray);

            start = System.nanoTime();
            binarySearch(binaryArray, key);
            end = System.nanoTime();

            System.out.printf("%-20s %15.4f%n",
                    "Binary Search", toMilliseconds(end - start));

            // Quick Sort
            int[] quickArray = Arrays.copyOf(original, original.length);
            start = System.nanoTime();
            quickSort(quickArray, 0, quickArray.length - 1);
            end = System.nanoTime();

            System.out.printf("%-20s %15.4f%n",
                    "Quick Sort", toMilliseconds(end - start));

            // Merge Sort
            int[] mergeArray = Arrays.copyOf(original, original.length);
            start = System.nanoTime();
            mergeSort(mergeArray, 0, mergeArray.length - 1);
            end = System.nanoTime();

            System.out.printf("%-20s %15.4f%n",
                    "Merge Sort", toMilliseconds(end - start));

            // Heap Sort
            int[] heapArray = Arrays.copyOf(original, original.length);
            start = System.nanoTime();
            heapSort(heapArray);
            end = System.nanoTime();

            System.out.printf("%-20s %15.4f%n",
                    "Heap Sort", toMilliseconds(end - start));

            // Bubble Sort
            int[] bubbleArray = Arrays.copyOf(original, original.length);
            start = System.nanoTime();
            bubbleSort(bubbleArray);
            end = System.nanoTime();

            System.out.printf("%-20s %15.4f%n",
                    "Bubble Sort", toMilliseconds(end - start));

            // Insertion Sort
            int[] insertionArray = Arrays.copyOf(original, original.length);
            start = System.nanoTime();
            insertionSort(insertionArray);
            end = System.nanoTime();

            System.out.printf("%-20s %15.4f%n",
                    "Insertion Sort", toMilliseconds(end - start));

            // Selection Sort
            int[] selectionArray = Arrays.copyOf(original, original.length);
            start = System.nanoTime();
            selectionSort(selectionArray);
            end = System.nanoTime();

            System.out.printf("%-20s %15.4f%n",
                    "Selection Sort", toMilliseconds(end - start));
        }

        System.out.println();
        System.out.println("==============================================================");
        System.out.println("Benchmark complete.");
        System.out.println("==============================================================");
    }

    private static int[] generateRandomArray(int size) {
        int[] array = new int[size];

        for (int i = 0; i < size; i++) {
            array[i] = random.nextInt(1_000_000);
        }

        return array;
    }

    public static int linearSearch(int[] array, int key) {

        for (int i = 0; i < array.length; i++) {
            if (array[i] == key) {
                return i;
            }
        }

        return -1;
    }

    public static int binarySearch(int[] array, int key) {

        int left = 0;
        int right = array.length - 1;

        while (left <= right) {

            int middle = left + (right - left) / 2;

            if (array[middle] == key) {
                return middle;
            }

            if (array[middle] < key) {
                left = middle + 1;
            } else {
                right = middle - 1;
            }
        }

        return -1;
    }

    public static void quickSort(int[] array, int low, int high) {

        if (low < high) {

            int pivotIndex = partition(array, low, high);

            quickSort(array, low, pivotIndex - 1);
            quickSort(array, pivotIndex + 1, high);
        }
    }

    private static int partition(int[] array, int low, int high) {

        int pivot = array[high];
        int i = low - 1;

        for (int j = low; j < high; j++) {

            if (array[j] <= pivot) {

                i++;

                int temp = array[i];
                array[i] = array[j];
                array[j] = temp;
            }
        }

        int temp = array[i + 1];
        array[i + 1] = array[high];
        array[high] = temp;

        return i + 1;
    }

    public static void mergeSort(int[] array, int left, int right) {

        if (left < right) {

            int middle = left + (right - left) / 2;

            mergeSort(array, left, middle);
            mergeSort(array, middle + 1, right);

            merge(array, left, middle, right);
        }
    }

    private static void merge(
            int[] array, int left, int middle, int right) {

        int leftSize = middle - left + 1;
        int rightSize = right - middle;

        int[] leftArray = new int[leftSize];
        int[] rightArray = new int[rightSize];

        for (int i = 0; i < leftSize; i++) {
            leftArray[i] = array[left + i];
        }

        for (int j = 0; j < rightSize; j++) {
            rightArray[j] = array[middle + 1 + j];
        }

        int i = 0;
        int j = 0;
        int k = left;

        while (i < leftSize && j < rightSize) {

            if (leftArray[i] <= rightArray[j]) {
                array[k] = leftArray[i];
                i++;
            } else {
                array[k] = rightArray[j];
                j++;
            }

            k++;
        }

        while (i < leftSize) {
            array[k] = leftArray[i];
            i++;
            k++;
        }

        while (j < rightSize) {
            array[k] = rightArray[j];
            j++;
            k++;
        }
    }

    public static void heapSort(int[] array) {

        int n = array.length;

        for (int i = n / 2 - 1; i >= 0; i--) {
            heapify(array, n, i);
        }
        for (int i = n - 1; i > 0; i--) {

            int temp = array[0];
            array[0] = array[i];
            array[i] = temp;

            heapify(array, i, 0);
        }
    }

    private static void heapify(int[] array, int n, int root) {

        int largest = root;
        int left = 2 * root + 1;
        int right = 2 * root + 2;

        if (left < n && array[left] > array[largest]) {
            largest = left;
        }

        if (right < n && array[right] > array[largest]) {
            largest = right;
        }

        if (largest != root) {

            int temp = array[root];
            array[root] = array[largest];
            array[largest] = temp;

            heapify(array, n, largest);
        }
    }

    public static void bubbleSort(int[] array) {

        for (int i = 0; i < array.length - 1; i++) {

            boolean swapped = false;

            for (int j = 0; j < array.length - i - 1; j++) {

                if (array[j] > array[j + 1]) {

                    int temp = array[j];
                    array[j] = array[j + 1];
                    array[j + 1] = temp;

                    swapped = true;
                }
            }

            // Stop early if the array is already sorted.
            if (!swapped) {
                break;
            }
        }
    }

    public static void insertionSort(int[] array) {

        for (int i = 1; i < array.length; i++) {

            int key = array[i];
            int j = i - 1;

            while (j >= 0 && array[j] > key) {

                array[j + 1] = array[j];
                j--;
            }

            array[j + 1] = key;
        }
    }

    public static void selectionSort(int[] array) {

        for (int i = 0; i < array.length - 1; i++) {

            int minimumIndex = i;

            for (int j = i + 1; j < array.length; j++) {

                if (array[j] < array[minimumIndex]) {
                    minimumIndex = j;
                }
            }

            int temp = array[i];
            array[i] = array[minimumIndex];
            array[minimumIndex] = temp;
        }
    }

    private static void warmUp() {

        int[] warmUpArray = generateRandomArray(1000);

        linearSearch(warmUpArray, warmUpArray[500]);

        int[] binaryArray = Arrays.copyOf(warmUpArray, warmUpArray.length);
        Arrays.sort(binaryArray);
        binarySearch(binaryArray, binaryArray[500]);

        int[] quickArray = Arrays.copyOf(warmUpArray, warmUpArray.length);
        quickSort(quickArray, 0, quickArray.length - 1);

        int[] mergeArray = Arrays.copyOf(warmUpArray, warmUpArray.length);
        mergeSort(mergeArray, 0, mergeArray.length - 1);

        int[] heapArray = Arrays.copyOf(warmUpArray, warmUpArray.length);
        heapSort(heapArray);

        int[] bubbleArray = Arrays.copyOf(warmUpArray, warmUpArray.length);
        bubbleSort(bubbleArray);

        int[] insertionArray = Arrays.copyOf(warmUpArray, warmUpArray.length);
        insertionSort(insertionArray);

        int[] selectionArray = Arrays.copyOf(warmUpArray, warmUpArray.length);
        selectionSort(selectionArray);
    }

    private static double toMilliseconds(long nanoseconds) {
        return nanoseconds / 1_000_000.0;
    }
}
