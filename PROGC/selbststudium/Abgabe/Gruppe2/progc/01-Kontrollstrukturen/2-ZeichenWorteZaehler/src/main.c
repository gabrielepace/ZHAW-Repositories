/**
* Selbststudium 1 - Kontrollstrukturen
* Aufgabe 2: Zaehlen von Zeichen und Woertern
*
* @author Gruppe 2: Gabriele Pace (pacegab1), Omar Shakir (shakioma), Vincent Schmid (schmivin)
*
* @file main.c
*/

#include <stdio.h>
#include <stdlib.h>

/**
 * @brief Main entry point.
 * @returns Returns EXIT_SUCCESS (=0) on success,
 *                  EXIT_FAILURE (=1) if more than one argument is given.
 */
int main(void)
{	
	(void)printf("Please enter some stuff:\n");
	char character = (char)getchar(); //get next character of stdin

	int counter_chars = 0;
	int counter_words = 0;
	int char_before_is_space = 1; 

	while(character != '\n'){
		if(character == ' ' || character == '\t'){
		    // what happens if actual char is a space or tab:
			if(!char_before_is_space){
			    // to be sure the counter doesn't count spaces
				counter_words++;
			}
			// if multiple spaces were typed
			char_before_is_space = 1;
		} else {
		    // we didn't read a space
			char_before_is_space = 0;
		}

		counter_chars++;
		character = (char)getchar(); // get next char from stdin
		if(character == '\n' && !char_before_is_space){
		    // case for the last char, if the last one was a word and not a space
			counter_words++;
		}
	}

	//print out the counters
	(void)printf("Chars: %d\n", counter_chars);
	(void)printf("Words: %d\n", counter_words);
	
	return EXIT_SUCCESS;
}