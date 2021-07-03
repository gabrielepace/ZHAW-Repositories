#include <stdio.h>
#include <stdlib.h>
#include <string.h>
#include <ctype.h>
#include "person.h"
#include "list.h"

/*
* Compares two person and returns a value <0 || 0 || >0
* @param Person1
* @param Person2
* @return result as an Integer
*/
int comparePersons(Person *person1, Person *person2){

    int result;
    
    result = strcmp(person1->name, person2->name);
    if(result != 0) {
        return result;
    }
    
    result = strcmp(person1->firstname, person2->firstname);
    if(result != 0){
        return result;
    }
    
    result = person1->age - person2->age;
    return result;
}

Person *readPerson(void) {
    
    Person *person = malloc(sizeof(Person));
    
    printf("Please enter the name of the new person - max Size: 19\n");
    while(scanf("%19s", person->name) != 1) {
		printf("The Input can't be empty\n");
    }    
    // Clears the input Buffer
    while((getchar()) != '\n');
    
    printf("Please enter the firstname of the new person - max Size: 19\n");
    while(scanf("%19s", person->firstname) != 1) {
        printf("The Input can't be empty\n");
    }
    // Clears the input Buffer
    while((getchar()) != '\n');
    
    printf("Please enter the age of the new person\n");
    while(scanf("%u", &(person->age)) != 1) {
        printf("The Input can't be empty\n"); 
    }
    // Clears the input Buffer
    while((getchar()) != '\n');
    
    return person;
}