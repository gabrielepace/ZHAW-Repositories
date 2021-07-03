package ch.zhaw.ads;

public class InsertionSort {
	public static long sort(int[] array) {
		long startTime = System.nanoTime();
		if(array != null) {
			return InsertionSort.sort(array, 0, array.length-1);
		}
		return System.nanoTime() - startTime;
	}
	
	public static long sort(int[] array, int floor, int ceiling) {
		long startTime = System.nanoTime();
		if (array != null) {
			for (int i = floor + 1; i <= ceiling; i++) {
				int element = array[i];
				int j = i;

				while (j > floor && array[j - 1] > element) {
					array[j] = array[j - 1];
					j--;
				}
				array[j] = element;
			}
		}
		return System.nanoTime() - startTime;
	}
}