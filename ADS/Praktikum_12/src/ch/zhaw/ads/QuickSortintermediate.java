package ch.zhaw.ads;

public class QuickSortintermediate {
	public static long sort(int[] array) {
		long startTime = System.nanoTime();
		if (array != null) {
			quickSortIteration(array, 0, array.length - 1);
		}
		return System.nanoTime() - startTime;
	}

	private static void quickSortIteration(int[] array, int floor, int ceiling) {
		if (ceiling > floor) {
			int pivotIndex = getMedianValue(array, floor, ceiling);
			int partition = floor;
			int pivot = array[pivotIndex];

			swap(array, pivotIndex, ceiling);
			for (int currentElement = floor; currentElement < ceiling; currentElement++) {
				if (array[currentElement] <= pivot) {
					swap(array, partition++, currentElement);
				}
			}
			swap(array, ceiling, partition);

			quickSortIteration(array, floor, partition - 1);
			quickSortIteration(array, partition + 1, ceiling);
		}
	}

	private static int getMedianValue(int[] array, int floor, int ceiling) {
		int middle = (floor + ceiling) / 2;
		
		if(array[floor] < array[middle] && array[floor] > array[ceiling]) {
			return floor;
		} else if(array[floor] > array[middle] && array[floor] < array[ceiling]) {
			return ceiling;
		} else {
			return middle;
		}
	}

	private static void swap(int[] array, int e1, int e2) {
		int temp = array[e1];
		array[e1] = array[e2];
		array[e2] = temp;
	}
}