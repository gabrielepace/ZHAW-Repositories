/**
* Selbststudium 5 - Linked-List-Modular
* Aufgabe 1: Personenverwaltung
*
* @author Gruppe 2: Gabriele Pace (pacegab1), Omar Shakir (shakioma), Vincent Schmid (schmivin)
*
* @file main.c
*/

#include <stdio.h>
#include <stdlib.h>
#include <string.h>
#include "list.h"
#include "person.h"
#include "utils.h"

/**
 * @brief   Main entry point of the Person Management System
 * @returns Returns EXIT_SUCCESS (=0) on success.
 */
int main(void) {
    int is_terminated = 0;
    char choice;
    ListElement *sentinel = init_list();

    print_welcome_message();

    do {
        print_instructions();

        choice = get_char_input();
        switch(choice) {
            case 'I':
            case 'i':
                insertPerson(sentinel);
                break;
            case 'R':
            case 'r':
                removePerson(sentinel);
                break;
            case 'S':
            case 's':
                showList(sentinel);
                break;
            case 'C':
            case 'c':
                clearList(sentinel);
                (void)printf("\nList successfully cleared.\n");
                break;
            case 'E':
            case 'e':
                (void)printf("\nClosing application...\n");
                clearList(sentinel);
                free(sentinel);
                is_terminated = 1;
                break;
            default:
                (void)printf("\nInvalid input, please choose one of the given options.");
                break;
        }

    } while (!is_terminated);
    return EXIT_SUCCESS;
}