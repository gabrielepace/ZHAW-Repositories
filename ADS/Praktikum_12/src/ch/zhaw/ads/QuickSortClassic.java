package ch.zhaw.ads;

public class QuickSortClassic {
	public static long sort(int[] array) {
		long startTime = System.nanoTime();
		if (array != null) {
			quickSortIteration(array, 0, array.length - 1);
		}
		return System.nanoTime() - startTime;
	}

	private static void quickSortIteration(int[] array, int floor, int ceiling) {
		int pivotIndex = (floor + ceiling) / 2;
		if (ceiling > floor) {
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

	private static void swap(int[] array, int e1, int e2) {
		int temp = array[e1];
		array[e1] = array[e2];
		array[e2] = temp;
	}
}
