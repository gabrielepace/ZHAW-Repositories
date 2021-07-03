package ch.zhaw.ads;

import java.util.Arrays;

public class Sorter {
	public static long sort(int[] numbers) {
		long startTime = System.nanoTime();
		if (numbers != null) {
			Arrays.sort(numbers);
		}
		return System.nanoTime() - startTime;
	}
}