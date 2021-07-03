package ch.zhaw.ads;

import java.util.*;

public class SortServer implements CommandExecutor {
	private Map<Character, Character> parenthesis;

	@Override
	public String execute(String command) throws Exception {
		int size = Integer.parseInt(command.split(" ")[1]);
		int[] numbers = new int[size];
		for (int i = 0; i < size; i++) {
			numbers[i] = new Random().nextInt(size);
		}
		long startTime = System.nanoTime();
		switch (command.split(" ")[0]) {
		case "BUBBLE":
			bubbleSort(numbers);
			break;
		case "INSERTION":
			insertionSort(numbers);
			break;
		case "SELECTION":
			selectionSort(numbers);
			break;
		default:
			return "Wrong input";
		}

		long estimatedTime = (System.nanoTime() - startTime) / 1000000;
		return checkSorted(numbers) ? "finshed sorting estimated time is: " + estimatedTime + "ms\n" : "didnt sorted";
	}

	private void bubbleSort(int[] numbers) {
		for (int i = numbers.length; i > 0; i--) {
			boolean swap = false;
			for (int j = 0; j < numbers.length - 1; j++) {
				if (numbers[j] > numbers[j + 1]) {
					int temp = numbers[j];
					numbers[j] = numbers[j + 1];
					numbers[j + 1] = temp;
					swap = true;
				}
			}
			if (!swap)
				break;
		}
	}

	private void selectionSort(int numbers[]) {
		for (int i = 0; i < numbers.length; i++) {
			int min = i;
			for (int j = i + 1; j < numbers.length; j++) {
				if (numbers[j] < numbers[min]) {
					min = j;
				}
			}
			if (min != i) {
				int temp = numbers[i];
				numbers[i] = numbers[min];
				numbers[min] = temp;
			}
		}
	}

	private void insertionSort(int numbers[]) {
		for (int i = 1; i < numbers.length; i++) {
			if (numbers[i] < numbers[i - 1]) {
				for (int j = i; j >= 1 && (numbers[j] < numbers[j - 1]); j--) {
					int temp = numbers[j];
					numbers[j] = numbers[j - 1];
					numbers[j - 1] = temp;
				}
			}
		}
	}

	private boolean checkSorted(int[] numbers) {
		for (int i = 0; i < numbers.length - 1; i++) {
			if (numbers[i] > numbers[i + 1])
				return false;
		}
		return true;
	}

}