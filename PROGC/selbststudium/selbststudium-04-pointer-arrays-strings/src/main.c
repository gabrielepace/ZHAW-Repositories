#include <stdlib.h>
#include <stdio.h>
#include <string.h>
#include <ctype.h>
#define ENDOFINPUT "ZZZ"
#define MAXWORDSINWORDLIST 100

char *wordlist[MAXWORDSINWORDLIST];
char word[20] = {0};
int wordArrayindex = 0;
int wordlistArrayIndex = 0;


/**
* Adds a word to the wordlist
* @param [char]word[]
* @param [char]*wordlist[]
* @param [int]wordlistSize
* @param [int]wordlistArrayIndex
*/
void addWordToWordlist(char word[], char* wordlist[], int wordlistSize, int wordlistArrayindex){
    // size_t can hold any array index
    size_t wordSize = strlen(word) + 1;

    // Allocate a size of bytes for the new array
    void *arrayPointer = (char *) malloc(wordSize * sizeof(char));

    // Check if there is free space
    if((arrayPointer && wordlistSize) > wordlistArrayIndex){
        wordlist[wordlistArrayIndex] = arrayPointer;
        // Copy the word to the new ArrayPointer and save the address in the wordlist
        strcpy(wordlist[wordlistArrayIndex], word);

        printf("[SUCCESS]...Your word %s has been successfully added\n", word);
    } else {
        printf("[FAILED]...Your word %s hat NOT been added\n", word);
        }
}

/**
* Sorts the given wordlist in a alphabetical order
* @param [char]*wordlist[]
* @param [int]wordlistSize
*/
void sortWordlist (char* wordlist[], int wordlistSize){
    char *tmp;

    for(int i=0; i<wordlistSize-1; i++){
        for(int j=0; j<wordlistSize-1; j++){
            if(strcmp(wordlist[j], wordlist[j + 1]) > 0){
                tmp = wordlist[j];
                wordlist[j] = wordlist[j + 1];
                wordlist[j + 1] = tmp;
            }
        }
    }
}

/**
* Prints the whole wordlist
* @param [char]*wordlist[]
* @param [int]wordlistArraySize
*/
void printWordlist(char *wordlist[], int wordlistArraySize){
    for(int i=0; i<wordlistArraySize; i++){
        printf("%s \n",wordlist[i]);
    }
}

/**
* Checks if the word is already in the wordlist
* @param [char]word[]
* @param [char]*wordlist[]
* @param [int]currentSize
* @return 1 if the word is already in the wordlist
* @return 0 if the word is not in the wordlist
*/
int isWordInWordlist(char word[], char *wordlist[], int currentSize){
    for(int i=0; i<currentSize; i++){
        if(strcmp(word, wordlist[i]) == 0){
            return 1;
        }
    }
    return 0;
}

/**
* Main-method were the program starts, it allows to input some words until the word ZZZ is found
* @return EXIT_SUCCESS
*/
int main(void){
    while(strcmp(word, ENDOFINPUT) != 0){
        printf ("Enter your word: with 'ZZZ' you can finish your input\n");
        // Check if the input is not Null
        if(scanf("%s", word) > 0){
            // Check if the input word is not equal to the ENDOFINPUT
            if(strcmp(word, ENDOFINPUT) != 0){
                // Convert every letter to lowercase
                for(int i=0; word[i]; i++){
                    word[i] = tolower(word[i]);
                }
                // Check if the input word already exists in the wordlist
                if(isWordInWordlist(word, wordlist, wordlistArrayIndex) == 0){
                    addWordToWordlist(word, wordlist, MAXWORDSINWORDLIST, wordlistArrayIndex);
                    wordlistArrayIndex++;
                } else {
                    printf("[FAILED]...The word %s is already in the wordlist!\n", word);
                }
            }
        }
        // Error message if the input is invalid
        else {
            printf("[FAILED]...This is not a valid word\n");
            return EXIT_FAILURE;
        }
    }

    printf("[BEFORE]...This is your unsorted Wordlist: \n");
    printWordlist(wordlist, wordArrayindex);

    sortWordlist(wordlist, wordArrayindex);

    printf("[AFTER]...This is your sorted Wordlist!\n");
    printWordlist(wordlist, wordArrayindex);

    return EXIT_SUCCESS;
}
