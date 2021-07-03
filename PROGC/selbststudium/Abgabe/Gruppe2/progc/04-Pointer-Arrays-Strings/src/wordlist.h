/**
* Selbststudium 4 - Pointer-Arrays-Strings
* Aufgabe 1: Woerter sortieren
*
* @author Gruppe 2: Gabriele Pace (pacegab1), Omar Shakir (shakioma), Vincent Schmid (schmivin)
*
* @file wordlist.h
*/

#ifndef WORDLIST_H
#define WORDLIST_H
void saveWord(char *wordlist[], char entry[], int position);
void sort_wordlist(char *wordlist[], int size);
void print_wordlist(char *wordlist[], int size);
/// @brief Maximum word length
#define MAX_WORD_LENGTH 20
/// @brief Maximum wordlist length
#define WORDLIST_LENGTH 100
/// @brief Abort String which will terminate the input of words
#define ABORT_STRING "ZZZ"
#endif