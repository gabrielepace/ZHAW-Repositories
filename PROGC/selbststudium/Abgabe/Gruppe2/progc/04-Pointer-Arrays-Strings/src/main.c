/**
* Selbststudium 4 - Pointer-Arrays-Strings
* Aufgabe 1: Woerter sortieren
*
* @author Gruppe 2: Gabriele Pace (pacegab1), Omar Shakir (shakioma), Vincent Schmid (schmivin)
*
* @file main.c
*/

#include <stdio.h>
#include <stdlib.h>
#include <string.h>
#include "wordlist.h"

/**
 * @brief Main entry point.
 * @returns Returns EXIT_SUCCESS on success
 * 					EXIT_FAILURE if:
 * 					- Memory could not be allocated
 */
int main(void) {
    const int INPUT_SIZE = MAX_WORD_LENGTH + 2; // +1 -> '\0' end of string & +1 -> '\n' fgets() reads
	char word[INPUT_SIZE];
    char *wordlist[WORDLIST_LENGTH];
    const char ABORT_CONDITION[] = ABORT_STRING;
    int counter = 0;

    (void)printf("Please enter any words (one by one / max. %d chars per word / max. %d words). Enter %s to stop.\n\n", MAX_WORD_LENGTH, WORDLIST_LENGTH, ABORT_CONDITION);
    
    // Save input words in wordlist, as long as input != "ZZZ"
    do {
        (void)printf("\nWord Nr.%d:\n", counter + 1);
        
        fgets(word, INPUT_SIZE, stdin);

        char *end_of_word = strchr(word, '\n');
        
        // Check if input was too long
        // (fgets reads '\n' as last char -> if not found => input more than 20 chars)
        if (end_of_word == NULL) {
            (void)printf("Invalid input [LENGTH] --> max. %d chars\n", MAX_WORD_LENGTH);
            // clear remaining input
            char c;
            do {
                c = getchar();
            } while (c != '\n' && c != EOF);
            continue;
        // Check if input was empty
        } else if (end_of_word == &word[0]) {
            (void)printf("Invalid input [EMPTY] --> Please enter at least 1 character\n");
            continue;
        // Check for whitespace in input
        } else if (strchr(word, ' ') != NULL || strchr(word, '\t') != NULL) {
            (void)printf("Invalid input [WHITESPACE] --> no whitespace allowed\n");
            continue;
        // fgets reads '\n' as the last char, replace it with '\0'
        } else {
            *end_of_word = '\0';
        }

        if (strcmp(word, ABORT_CONDITION)) {
            size_t n = strlen(word);
            // Dynamically create an array with size of input
            char *entry = malloc(n+1); // +1 for end of String \0

            if (entry == NULL) {
                (void)printf("Could not allocate %zu bytes of memory for new word", n+1);
                return EXIT_FAILURE;
            }
            
            strcpy(entry, word); // Copy content of word to entry
            saveWord(wordlist, entry, counter); // Save word in wordlist
            counter++;

            if (counter == 100) {
                (void)printf("\nYou entered %d words, limit reached\n", WORDLIST_LENGTH);
            }
            
        }
    } while (strcmp(word, ABORT_CONDITION) && counter < 100);

    sort_wordlist(wordlist, counter);
    print_wordlist(wordlist, counter);

    return EXIT_SUCCESS;
}