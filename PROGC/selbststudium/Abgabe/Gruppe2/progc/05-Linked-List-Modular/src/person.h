/**
* Selbststudium 5 - Linked-List-Modular
* Aufgabe 1: Personenverwaltung
*
* @author Gruppe 2: Gabriele Pace (pacegab1), Omar Shakir (shakioma), Vincent Schmid (schmivin)
*
* @file person.h
*/

#ifndef PERSON_H
#define PERSON_H

#include "utils.h"

/// @brief Definition of a person
typedef struct {
    char name[MAX_NAME_LENGTH + 1]; /**< Name of the person. */
    char firstname[MAX_NAME_LENGTH + 1]; /**< Firstname of the person. */
    unsigned age; /**< Age of the person. */
} Person;

Person createPerson(void);
int comparePersons(Person p1, Person p2);
#endif