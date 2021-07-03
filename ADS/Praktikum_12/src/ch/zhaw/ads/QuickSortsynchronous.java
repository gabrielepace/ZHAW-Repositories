package ch.zhaw.ads;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class QuickSortsynchronous implements Runnable {
    public static final int MAX_THREADS = Runtime.getRuntime().availableProcessors();
    static final ExecutorService executor = Executors.newFixedThreadPool(MAX_THREADS);

    final int[] numbers;
    final int floor, ceiling;

    private final int minPartionSize;

    public QuickSortsynchronous(int minPartionSize, int[] array, int floor, int ceiling) {
        this.minPartionSize = minPartionSize;
        this.numbers = array;
        this.floor = floor;
        this.ceiling = ceiling;
    }

    public void run() {
        quicksortIteration(numbers, floor, ceiling);
    }

    public void quicksortIteration(int[] array, int floor, int ceiling) {
        int length = ceiling - floor + 1;
        if (length <= 1)
            return;

        int pivotIndex = (floor + ceiling) / 2;
		int pivot = array[pivotIndex];
		int partition = floor;
		
		swap(array, pivotIndex, ceiling);
		for (int currentElement = floor; currentElement < ceiling; currentElement++) {
			if (array[currentElement] <= pivot) {
				swap(array, partition++, currentElement);
			}
		}
		swap(array, ceiling, partition);

        if (length > minPartionSize) {
            QuickSortsynchronous quickSortsynchronous = new QuickSortsynchronous(minPartionSize, array, floor, partition - 1);
            executor.submit(quickSortsynchronous);
            quicksortIteration(array, partition + 1, ceiling);
        } else {
            quicksortIteration(array, floor, partition - 1);
            quicksortIteration(array, partition + 1, ceiling);
        }
    }    
    
    private void swap(int[] array, int e1, int e2) {
		int temp = array[e1];
		array[e1] = array[e2];
		array[e2] = temp;
	}
}