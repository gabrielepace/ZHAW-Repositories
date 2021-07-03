
/**
 * Main class of the Turing Machine App
 * 
 * @author Semanur Cerkez (cerkesem), Aleksandra Timofeeva (timofale) & Gabriele Pace (pacegab1)
 */
import java.util.Scanner;

public class TuringMachineApp {
	public static void main(String[] args) {

		// Input routine
		String input = "";
		boolean enableStepMode = false;

		System.out.println("Welcome to the Turing Machine App");
		
		
		while (!input.equals("quit")) {
			System.out.println(
					"\nIf you write \"switch mode\", you will change in Step-Mode (for each step clic Enter on the Keyboard)");
			System.out.println("Or \"quit\" to stop the Turing Machine App");
			System.out.println("If you want to do a multiplication, write number*number (for example: 2*4, 13*17, 1*27 or 23*0).");
			System.out.print("\nEnter command or calculation: ");
			@SuppressWarnings("resource")
			Scanner scan = new Scanner(System.in); // Scanner for console input is never closed.
			input = scan.nextLine(); // To close it, should be written the "quit" command.

			String[] numbers = input.split("\\*");
			if (numbers.length == 2 && canParseInt(numbers[0]) && canParseInt(numbers[1])) {
				int number1 = Integer.parseInt(numbers[0]);
				int number2 = Integer.parseInt(numbers[1]);

				ControlUnit controlUnit = new ControlUnit(number1, number2);
				controlUnit.setStepMode(enableStepMode);
				controlUnit.startState0();
			} else if (input.equals("switch mode")) {
				enableStepMode = !enableStepMode;
			} else if (!input.equals("quit") && !input.equals("")) {
				System.out.println("Invalid input.");
			}
		}
	}

	/**
	 * Given a number in String, parses to an integer. If the String is not a
	 * number, NumberFormatException will be executed
	 * 
	 * @param value
	 * @return true
	 * @return false
	 */
	static boolean canParseInt(String value) {
		try {
			Integer.parseInt(value);
			return true;
		} catch (NumberFormatException e) {
			return false;
		}
	}

}
