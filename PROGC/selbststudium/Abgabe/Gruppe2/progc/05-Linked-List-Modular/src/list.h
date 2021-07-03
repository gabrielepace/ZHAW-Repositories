/**
* Selbststudium 5 - Linked-List-Modular
* Aufgabe 1: Personenverwaltung
*
* @author Gruppe 2: Gabriele Pace (pacegab1), Omar Shakir (shakioma), Vincent Schmid (schmivin)
*
* @file list.h
*/

#ifndef LIST_H
#define LIST_H
#include "person.h"
#include "utils.h"

/// @brief Definition of a list-element
typedef struct LE ListElement;
/// @brief Definition of a List Element structure
struct LE {
    Person content; /**< Person saved in list element. */
    ListElement *next; /**< Pointer to next list element in linked list. */
};

ListElement* create_list_element(void);
ListElement* init_list(void);
void insertPerson(ListElement *sentinel);
void removePerson(ListElement *sentinel);
void showList(ListElement *sentinel);
void clearList(ListElement *sentinel);
#endif