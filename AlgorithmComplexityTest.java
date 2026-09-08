import java.util.Arrays;
import java.util.Random;

public class AlgorithmComplexityTest {

    private static final int[] TEST_SIZES = {
        1000, 5000, 10000, 50000, 100000
    };

    private static final Random random = new Random();

    public static void main(String[] args) {

        warmUp();

        System.out.println("==============================================================");
        System.out.println("              HEAP SORT vs BUBBLE SORT");
        System.out.println("==============================================================");
        System.out.printf("%-15s %-20s %-20s%n",
                "Input Size", "Heap Sort (ms)", "Bubble Sort (ms)");
        System.out.println("--------------------------------------------------------------");

        for (int size : TEST_SIZES) {

            int[] originalArray = generateRandomArray(size);

            int[] heapArray = Arrays.copyOf(
                    originalArray, originalArray.length);

            int[] bubbleArray = Arrays.copyOf(
                    originalArray, originalArray.length);


            long startTime = System.nanoTime();

            heapSort(heapArray);

            long endTime = System.nanoTime();

            double heapTime = toMilliseconds(
                    endTime - startTime);

            startTime = System.nanoTime();

            bubbleSort(bubbleArray);

            endTime = System.nanoTime();

            double bubbleTime = toMilliseconds(
                    endTime - startTime);

            System.out.printf(
                    "%-15d %-20.4f %-20.4f%n",
                    size,
                    heapTime,
                    bubbleTime
            );
        }

        System.out.println("==============================================================");
    }

    private static int[] generateRandomArray(int size) {

        int[] array = new int[size];

        for (int i = 0; i < size; i++) {
            array[i] = random.nextInt(1_000_000);
        }

        return array;
    }

    // ==================== HEAP SORT ====================

    private static void heapSort(int[] array) {

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

    private static void heapify(
            int[] array,
            int n,
            int root) {

        int largest = root;

        int left = 2 * root + 1;
        int right = 2 * root + 2;

        if (left < n &&
                array[left] > array[largest]) {

            largest = left;
        }

        if (right < n &&
                array[right] > array[largest]) {

            largest = right;
        }

        if (largest != root) {

            int temp = array[root];
            array[root] = array[largest];
            array[largest] = temp;

            heapify(array, n, largest);
        }
    }

    private static void bubbleSort(int[] array) {

        int n = array.length;

        for (int i = 0; i < n - 1; i++) {

            boolean swapped = false;

            for (int j = 0; j < n - i - 1; j++) {

                if (array[j] > array[j + 1]) {

                    int temp = array[j];

                    array[j] = array[j + 1];

                    array[j + 1] = temp;

                    swapped = true;
                }
            }

            if (!swapped) {
                break;
            }
        }
    }
    private static void warmUp() {

        int[] testArray = generateRandomArray(1000);

        int[] heapArray = Arrays.copyOf(
                testArray, testArray.length);

        int[] bubbleArray = Arrays.copyOf(
                testArray, testArray.length);

        heapSort(heapArray);

        bubbleSort(bubbleArray);
    }

    private static double toMilliseconds(
            long nanoseconds) {

        return nanoseconds / 1_000_000.0;
    }
}