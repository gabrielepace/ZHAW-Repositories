/**
* Selbststudium 1 - Kontrollstrukturen
* Aufgabe 2: Zählen von Zeichen und Wörtern
*
* @author Gabriele Pace (pacegab1)
*/

#include <stdio.h>
#include <stdlib.h>
#include <ctype.h>

#define NEWLINE '\n'
#define BLANK ' '
#define TAB '\t'

typedef enum {
	false,
	true
} boolean;

/**
 * Reads the user input from terminal and prints out the number of characters and words
 */
static void processTextInput() {
	boolean wasWord = false;
    int charCounter = 0;
    int wordCounter = 0;
    
	char inputChar = getchar();

    while(inputChar != EOF && inputChar != NEWLINE) {
		charCounter++;
		if(inputChar == TAB || inputChar == BLANK) {
			if(wasWord) {
				wordCounter++;
				wasWord = false;
			}
		}
		else {
				wasWord = true;		
			}
		inputChar = getchar();
	}
	// Decide if last word has to be added
	if (wasWord && (inputChar == EOF || inputChar == NEWLINE)) {
		wordCounter++;
	}
	// Prints the number of characters and words	
    printf("Number of characters: %d\n", charCounter);
	printf("Number of words: %d\n", wordCounter);

}

/**
 * Asks for user input from terminal and processes input
 *
 * @returns EXIT_SUCCESS
 */
int main(void){
	printf("Enter any word or character:\n");    
	processTextInput();
	return EXIT_SUCCESS;
}
