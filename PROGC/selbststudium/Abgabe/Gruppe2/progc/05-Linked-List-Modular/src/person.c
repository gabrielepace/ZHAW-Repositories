/**
* Selbststudium 5 - Linked-List-Modular
* Aufgabe 1: Personenverwaltung
*
* @author Gruppe 2: Gabriele Pace (pacegab1), Omar Shakir (shakioma), Vincent Schmid (schmivin)
*
* @file person.c
*/

#include <stdio.h>
#include <stdlib.h>
#include <string.h>
#include "person.h"

/**
 * @brief       Creates a new person
 * @returns     Created person
 */
Person createPerson(void) {
    char *name;
    char *firstname;
    int age;

    (void)printf("----------------\n");
    (void)printf("\nPerson Data\n");
    (void)printf("----------------\n");
    (void)printf("\nName: ");
    name = get_string_input();
    (void)printf("\nFirstname: ");
    firstname = get_string_input();
    (void)printf("\nAge: ");
    age = get_unsigned_int_input();

    Person new_person;
    strcpy(new_person.name, name);
    strcpy(new_person.firstname, firstname);
    new_person.age = age;

    free(name);
    free(firstname);

    return new_person;
}

/**
 * @brief         Compares two persons alphabetically
 * @param [in]p1  First person to compare
 * @param [in p2  Second person to compare
 * @returns       -1 if p1 < p2 || 0 if p1 == p2 || 1 if p1 > p2
 */
int comparePersons(Person p1, Person p2) {
    int result = strcmp(p1.name, p2.name);
    if (result == 0) {
        result = strcmp(p1.firstname, p2.firstname);
    } else if (result == 0) {
        if (p1.age > p2.age) {
            result = 1;
        } else if (p1.age < p2.age) {
            result = -1;
        } else if (p1.age == p2.age) {
            result = 0;
        }
    }
    return result;
}