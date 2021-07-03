/**
 * Tape (Band) implementation of Turing Machine, with Read-Write head
 * (Lese-Schreibe Kopf). Directions of the Tape are called from Enum class Direction.
 * 
 * @author Semanur Cerkez (cerkesem), Aleksandra Timofeeva (timofale) & Gabriele
 *         Pace (pacegab1)
 */

public class Tape {
	private String tapeContent;
	private int rwPosition;
	private int amountOfSymbolsLeftAndRight = 15;

	/**
	 * Constructor, with specified Tape content initialization
	 * 
	 * @param tapeContent
	 */
	public Tape(String tapeContent) {
		this.tapeContent = tapeContent;
		this.rwPosition = 0;

		fillWithSpaces();
	}

	/**
	 * Fills the rest of the Tape with empty spaces
	 */
	public void fillWithSpaces() {
		tapeContent = fillWithSpaces(tapeContent, 20);
	}

	/**
	 * Prints out the Tape on screen
	 */
	public void printTape() {

		String outputRW = " ";
		for (int i = 0; i < amountOfSymbolsLeftAndRight; i++) {
			outputRW += "  ";
		}
		outputRW += "V"; // Read-Write head (Tape). Lese-Schreibe Kopf (auf dem Band).
		for (int i = 0; i < amountOfSymbolsLeftAndRight; i++) {
			outputRW += "  ";
		}

		String partOfTapeContent = tapeContent.substring(rwPosition - amountOfSymbolsLeftAndRight - 1,
				rwPosition + amountOfSymbolsLeftAndRight);
		String outputTape = "|";

		for (char c : partOfTapeContent.toCharArray()) {
			outputTape += c + "|";
		}

		System.out.println(outputRW);
		System.out.println(outputTape);
	}

	/**
	 * Fills out the Tape, with specified content and spaces amount
	 * 
	 * @param content
	 * @param amountOfSpaces
	 * 
	 * @return content
	 */
	private String fillWithSpaces(String content, int amountOfSpaces) {

		// Special case: the tape is empty
		if (content.trim().length() == 0) {
			content = getSpaces(amountOfSpaces * 2 + 1);
			rwPosition = amountOfSpaces;
			return content;
		}

		int existingSpaceCounterLeft = 0;

		// Count all spaces from left side
		while (existingSpaceCounterLeft < content.length() && content.charAt(existingSpaceCounterLeft) == ' ') {
			existingSpaceCounterLeft++;
		}

		rwPosition = rwPosition + (amountOfSpaces - existingSpaceCounterLeft);
		String additionalSpacesLeft = getSpaces(amountOfSpaces - existingSpaceCounterLeft);

		int existingSpaceCounterRight = 0;
		int contentIndex = content.length() - 1;

		while (existingSpaceCounterRight < content.length() && content.charAt(contentIndex) == ' ') {
			contentIndex--;
			existingSpaceCounterRight++;
		}

		String additionalSpacesRight = getSpaces(amountOfSpaces - existingSpaceCounterRight);
		content = additionalSpacesLeft + content + additionalSpacesRight;

		return content;

	}

	/**
	 * Get the spaces of the Tape, with specified spaces amount
	 * 
	 * @param amountOfSpaces
	 * 
	 * @return spaces
	 */
	private String getSpaces(int amountOfSpaces) {
		String spaces = "";
		for (int i = 0; i < amountOfSpaces; i++) {
			spaces += " ";
		}
		return spaces;
	}

	/**
	 * Get the Tape content
	 * 
	 * @return tapeContent
	 */
	public String getTapeContent() {
		return tapeContent;
	}

	/**
	 * Set the Tape content
	 * 
	 * @param tapeContent
	 */
	public void setTapeContent(String tapeContent) {
		this.tapeContent = tapeContent;
	}

	/**
	 * Get Read-Write head position on the Tape
	 * 
	 * @return rwPosition
	 */
	public int getRwPosition() {
		return rwPosition;
	}

	/**
	 * Get Character of Read-Write head position
	 * 
	 * @return tapeContent
	 */
	public char getCharAtRWPosition() {
		return tapeContent.charAt(rwPosition);
	}

	/**
	 * Set Character of Read-Write head position
	 * 
	 * @param charToWrite
	 */
	private void setCharAtRWPosition(char charToWrite) {
		char[] tapContentCharArray = tapeContent.toCharArray();
		tapContentCharArray[rwPosition] = charToWrite;
		tapeContent = String.valueOf(tapContentCharArray);
	}

	/**
	 * Verify if the written Character (in <setCharAtRWPosition>) equals the
	 * Character to read
	 * 
	 * @param charToRead
	 * 
	 * @return readChar
	 */
	public boolean canReadCharAtPosition(char charToRead) {
		char readChar = getCharAtRWPosition();
		return readChar == charToRead;
	}

	/**
	 * Read-Write head movement on the Tape, by giving a Character to read, a
	 * Character to write and the Direction
	 * 
	 * @param read
	 * @param write
	 * @param direction
	 */
	public void readWriteMove(char read, char write, Direction direction) {
		char readChar = getCharAtRWPosition();
		if (readChar == read) {
			setCharAtRWPosition(write);

			if (direction == Direction.Left) {
				rwPosition = rwPosition - 1;
			} else if (direction == Direction.Right) {
				rwPosition = rwPosition + 1;
			}

			fillWithSpaces();
		}
	}
}
