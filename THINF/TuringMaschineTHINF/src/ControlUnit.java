
/**
 * Turing Machine Control Unit (Interpreter) class,
 * where calculation is made. Calculations are Unary coded (Unär kodiert) --> for example: 2*5 (in decimal) is 00 for 2, 1 for *, 00000 for 5
 * This Turing Machine uses 3 Tapes.
 * 
 * @author Semanur Cerkez (cerkesem), Aleksandra Timofeeva (timofale) & Gabriele Pace (pacegab1)
 */

import java.util.Scanner;

public class ControlUnit {

	private Tape tape1;
	private Tape tape2;
	private Tape tape3;

	private int stepCounter = 0;
	private boolean stepThrough = false;

	/**
	 * Constructor, given two numbers (from Main Class), initializes the Tapes
	 * 
	 * @param number1
	 * @param number2
	 */
	public ControlUnit(int number1, int number2) {
		tape1 = new Tape(getZeros(number1) + "1" + getZeros(number2));
		tape2 = new Tape(" ");
		tape3 = new Tape(" ");
	}

	/**
	 * Starts the calculation from State (Zustand) 0 and finally prints out the
	 * result
	 */
	public void startState0() {
		state0();
		System.out.println("Result: " + tape3.getTapeContent().trim().length());
		System.out.println("=========================");
		System.out.println("Calculation finished. " + stepCounter + " steps needed.\n\n\n");
	}

	/**
	 * Spaces in the Tape are filled of 0, from given amount (anzahl) of zero
	 * 
	 * @param amountOfZeroes
	 * 
	 * @return spaces
	 */
	private String getZeros(int amountOfZeroes) {
		String spaces = "";
		for (int i = 0; i < amountOfZeroes; i++) {
			spaces += "0";
		}
		return spaces;
	}

	/**
	 * State (Zustand) 0, with different Read-Write (and finally move) conditions
	 */
	private void state0() {
		printState("STATE 0");
		if (canReadOnTape("0  ")) { // Unary coded (Unär kodiert)
			readWriteMove("0  ", "0  ", Direction.Right, Direction.Neutral, Direction.Neutral);
			state0();
		} else if (canReadOnTape("1  ")) {
			readWriteMove("1  ", "1  ", Direction.Right, Direction.Neutral, Direction.Neutral);
			state0();
		} else if (canReadOnTape("   ")) {
			readWriteMove("   ", "   ", Direction.Left, Direction.Neutral, Direction.Neutral);
			state1();
		}
	}

	/**
	 * State (Zustand) 1, with different Read-Write (and finally move) conditions
	 */
	private void state1() {
		printState("STATE 1");
		if (canReadOnTape("0  ")) {
			readWriteMove("0  ", " 0 ", Direction.Left, Direction.Right, Direction.Neutral);
			state1();
		} else if (canReadOnTape("1  ")) {
			readWriteMove("1  ", "   ", Direction.Left, Direction.Left, Direction.Neutral);
			state2();
		}
	}

	/**
	 * State (Zustand) 2, with different Read-Write (and finally move) conditions
	 */
	private void state2() {
		printState("STATE 2");
		if (canReadOnTape("00 ")) {
			readWriteMove("00 ", "000", Direction.Neutral, Direction.Left, Direction.Right);
			state2();
		} else if (canReadOnTape("0  ")) {
			readWriteMove("0  ", "   ", Direction.Left, Direction.Right, Direction.Neutral);
			state3();
		} else if (canReadOnTape(" 0 ")) {
			readWriteMove(" 0 ", "   ", Direction.Neutral, Direction.Left, Direction.Neutral);
			state2();
		} else if (canReadOnTape("   ")) {
			readWriteMove("   ", "   ", Direction.Neutral, Direction.Neutral, Direction.Neutral);
			state3();
		}
	}

	/**
	 * State (Zustand) 3, with different Read-Write (and finally move) conditions.
	 * This State is also the end State (Endzustand) --> If calculation is done
	 * here, will be also accepted
	 */
	private void state3() {
		printState("STATE 3");
		if (canReadOnTape(" 0 ")) {
			readWriteMove(" 0 ", "   ", Direction.Neutral, Direction.Right, Direction.Neutral);
			state3();
		} else if (canReadOnTape("00 ")) {
			readWriteMove("00 ", "000", Direction.Neutral, Direction.Right, Direction.Right);
			state3();
		} else if (canReadOnTape("0  ")) {
			readWriteMove("0  ", "   ", Direction.Left, Direction.Left, Direction.Neutral);
			state2();
		} else {
			System.out.println("Accepted");
		}
	}

	/**
	 * Given a String (a number), checks if to read on the 3 Tapes. This String will
	 * be also casted to a Character Array.
	 * 
	 * @param canRead
	 * 
	 * @return tape1, tape2, tape3
	 */
	public boolean canReadOnTape(String canRead) {
		char[] canReadChars = canRead.toCharArray();
		return tape1.canReadCharAtPosition(canReadChars[0]) && tape2.canReadCharAtPosition(canReadChars[1])
				&& tape3.canReadCharAtPosition(canReadChars[2]);
	}

	/**
	 * Prints out the States (Zustände)
	 * 
	 * @param state
	 */
	private void printState(String state) {
		System.out.println("---------------------------------------------------");
		System.out.println(state);
		System.out.println("---------------------------------------------------");
	}

	/**
	 * Given a boolean expression, if true Step Mode will be enabled, else not
	 * 
	 * @param enableStepMode
	 */
	public void setStepMode(boolean enableStepMode) {
		stepThrough = enableStepMode;
	}

	/**
	 * Main Runtime work of the Turing Machine. Given different parameters (calling
	 * from Tape), here are executed the calculations of the States (Zustände). At
	 * every State (for instance Read-Write and then Move), calculations step will
	 * be counted.
	 * 
	 * @param read
	 * @param write
	 * @param dirTape1
	 * @param dirTape2
	 * @param dirTape3
	 */
	public void readWriteMove(String read, String write, Direction dirTape1, Direction dirTape2, Direction dirTape3) {

		stepCounter++;
		System.out.println("Read '" + read + "' | Write '" + write + "' | Move '" + dirTape1 + "," + dirTape2 + ","
				+ dirTape3 + "' | Step No. " + stepCounter);

		char[] readChars = read.toCharArray();
		char[] writeChars = write.toCharArray();

		tape1.readWriteMove(readChars[0], writeChars[0], dirTape1);
		tape2.readWriteMove(readChars[1], writeChars[1], dirTape2);
		tape3.readWriteMove(readChars[2], writeChars[2], dirTape3);

		tape1.printTape();
		tape2.printTape();
		tape3.printTape();

		try {
			Thread.sleep(250); // Time in millisecond to execute the calculation task on screen (better known
								// as Thread)
		} catch (InterruptedException e) {
			e.printStackTrace();
		}

		if (stepThrough) {
			@SuppressWarnings("resource")
			Scanner scan = new Scanner(System.in); // Scanner for console input is never closed.
			scan.nextLine(); // To close it, should be written the "quit" command.
		}
	}
}
