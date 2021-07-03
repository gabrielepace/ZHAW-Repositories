/**
* Selbststudium 4 - Pointer-Arrays-Strings
* Aufgabe 1: Woerter sortieren
*
* @author Gruppe 2: Gabriele Pace (pacegab1), Omar Shakir (shakioma), Vincent Schmid (schmivin)
*
* @file wordlist.c
*/

#include <stdio.h>
#include <stdlib.h>
#include <string.h>
#include "wordlist.h"

/**
 * @brief               Saves a word at the end of the wordlist.
 * @param[in] wordlist  Pointer to the wordlist in which the word will be inserted.
 * @param[in] entry     The word that will be safed in the wordlist.
 * @param[in] position  Position in which the word will be safed in the wordlist.
 */
void saveWord(char *wordlist[], char entry[], int position) {
    wordlist[position] = entry;
}

/**
 * @brief               Sorts the wordlist
 * @param[in] wordlist  Pointer to the wordlist which will be sorted.
 * @param[in] size      Size of the wordlist
 */
void sort_wordlist(char *wordlist[], int size) {
    char *tmp_word;

    for (size_t i = 0; i < size; i++) {
        for (size_t j = i + 1; j < size; j++) {
            // if following word is alphabetically bigger than the first, swap them
            if (strcmp(wordlist[i], wordlist[j]) > 0) {
                tmp_word = wordlist[j];
                wordlist[j] = wordlist[i];
                wordlist[i] = tmp_word;
            }
        }
    }
}

/**
 * @brief               Prints the wordlist
 * @param[in] wordlist  Pointer to the wordlist which will be printed.
 * @param[in] size      Size of the wordlist
 */
void print_wordlist(char *wordlist[], int size) {
    (void)printf("\n\nWordlist:\n");
    (void)printf("---------\n");

    for (size_t i = 0; i < size; i++) {
        (void)printf("%s\n", wordlist[i]);
        free(wordlist[i]);
    }
}