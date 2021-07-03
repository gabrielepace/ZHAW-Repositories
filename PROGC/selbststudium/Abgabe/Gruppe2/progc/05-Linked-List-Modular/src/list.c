/**
* Selbststudium 5 - Linked-List-Modular
* Aufgabe 1: Personenverwaltung
*
* @author Gruppe 2: Gabriele Pace (pacegab1), Omar Shakir (shakioma), Vincent Schmid (schmivin)
*
* @file list.c
*/

#include <stdio.h>
#include <stdlib.h>
#include <string.h>
#include "list.h"

/**
 * @brief   Create a list element
 * @returns Returns a pointer to the created list element
 */
ListElement* create_list_element(void) {
    size_t le_size = sizeof(ListElement);
    ListElement *list_element = malloc(le_size);

    if (list_element == NULL) {
        (void)printf("ERROR: Could not allocate %zu Bytes of memory", le_size);
        exit(EXIT_FAILURE);
    }

    return list_element;
}

/**
 * @brief   Initialize a list
 * @returns Returns a pointer to the sentinel element of a list
 */
ListElement* init_list(void) {
    ListElement *sentinel = create_list_element();

    // Person is not initialized => {NULL, NULL, 0}
    sentinel->next = sentinel;

    return sentinel;
}

/**
 * @brief  Inserts a person into the list
 * @param  [in]sentinel  Pointer to sentinel of List in which to insert
 */
void insertPerson(ListElement *sentinel) {
    ListElement *new_entry = create_list_element();
    new_entry->content = createPerson();
    
    ListElement *current = sentinel;

    while (current->next != sentinel && comparePersons(current->next->content, new_entry->content) < 0) {
        current = current->next;
    }

    new_entry->next = current->next;
    current->next = new_entry;
}

/**
 * @brief  Removes a person from a list
 * @param  [in]sentinel  Pointer to sentinel of list from which to remove
 */
void removePerson(ListElement *sentinel) {
    Person remove_person = createPerson();

    ListElement *current = sentinel;

    while (current->next != sentinel && comparePersons(current->next->content, remove_person) != 0) {
        current = current->next;
    }

    if (current->next == sentinel) {
        (void)printf("\nNo matching person found\n");
    } else {
        ListElement *remove_element = current->next;
        current->next = remove_element->next;
        free(remove_element);
        (void)printf("\nPerson successfully removed\n");
    }
}

/**
 * @brief  Prints out the content of the list
 * @param  [in]sentinel  Pointer to sentinel of list
 */
void showList(ListElement *sentinel) {
    ListElement *current = sentinel;

    (void)printf("-------------------------\n");
    (void)printf("\tPerson List:\n");
    (void)printf("-------------------------\n");

    while (current->next != sentinel) {
        (void)printf("%s %s, %u\n", current->next->content.name, current->next->content.firstname, current->next->content.age);
        current = current->next;
    }
}

/**
 * @brief  Clears list, removes all elements (except sentinel)
 * @param  [in]sentinel  Pointer to sentinel of list
 */
void clearList(ListElement *sentinel) {
    while (sentinel->next != sentinel) {
        ListElement *remove_element = sentinel->next;
        sentinel->next = remove_element->next;
        free(remove_element);
    }
}