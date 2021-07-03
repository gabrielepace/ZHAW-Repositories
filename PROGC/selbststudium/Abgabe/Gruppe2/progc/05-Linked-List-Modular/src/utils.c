/**
* Selbststudium 5 - Linked-List-Modular
* Aufgabe 1: Personenverwaltung
*
* @author Gruppe 2: Gabriele Pace (pacegab1), Omar Shakir (shakioma), Vincent Schmid (schmivin)
*
* @file utils.c
*/

#include <stdio.h>
#include <stdlib.h>
#include <string.h>
#include "utils.h"

/**
 * @brief  Clears the input buffer
 */
void clear_input_buffer(void) {
    char c;
    do {
        c = getchar();
    } while (c != '\n' && c != EOF);
}

/**
 * @brief       Reads a char from user input
 * @returns     input char
 */
char get_char_input(void) {
    char buffer[BUFFER_SIZE];
    char result;
    int validation = 0;

    while (!validation) {
        fgets(buffer, BUFFER_SIZE, stdin);
        validation = sscanf(buffer, "%c", &result);
        if (!validation) {
            (void)printf("Something went wrong, please try again:\n");
        }
    }

    return result;
}

/**
 * @brief       Reads a string from user input
 * @returns     input string
 */
char* get_string_input(void) {
    char buffer[BUFFER_SIZE];
    char *result = malloc(MAX_NAME_LENGTH);
    int validation = 0;

    while (!validation) {
        fgets(buffer, BUFFER_SIZE, stdin);
        if (buffer[0] == '\n') {
            (void)printf("No input, please try again:\n");
        } else {
            validation = sscanf(buffer, "%20s", result); // TODO: Maybe make this dynamic
            if (!validation) {
                (void)printf("Something went wrong, please try again:\n");
            }
        }
    }

    return result;
}

/**
 * @brief       Reads a unsigned int from user input
 * @returns     input unsigned int
 */
int get_unsigned_int_input(void) {
    char buffer[BUFFER_SIZE];
    unsigned result = 0;
    int is_valid = 0;
    
    while (!is_valid) {
        fgets(buffer, BUFFER_SIZE, stdin);

        if (strchr(buffer, '-') != NULL || buffer[0] == '0' || buffer[0] == '\n') {
            (void)printf("Must be a positive number. Please try again:\n");
        } else {
            is_valid = sscanf(buffer, "%u", &result);

            if (!is_valid) {
                (void)printf("Something went wrong, please try again:\n");
            }
        }
    }

    return result;
}

/**
 * @brief  Prints the instructions
 */
void print_instructions(void) {
    (void)printf("\n\nWhat do you want to do?:\n\n");
    (void)printf("(I) -> Insert person in list\n");
    (void)printf("(R) -> Remove person from list\n");
    (void)printf("(S) -> Show entire list of persons\n");
    (void)printf("(C) -> Clear person list\n");
    (void)printf("(E) -> End the programm\n\n");
}

/**
 * @brief  Prints the welcome message
 */
void print_welcome_message(void) {
    (void)printf("\n//////////////////////////////////////////\n");
    (void)printf("//\t\t\t\t\t//\n");
    (void)printf("//\tPeople Management System\t//\n");
    (void)printf("//\t\t\t\t\t//\n");
    (void)printf("//////////////////////////////////////////\n");
}
