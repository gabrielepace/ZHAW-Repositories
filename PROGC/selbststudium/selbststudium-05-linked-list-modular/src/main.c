#include <stdio.h>
#include <stdlib.h>
#include <string.h>
#include <ctype.h>
#include <stdarg.h>
#include "list.h"
#include "person.h"

#define INSERT 'I'
#define REMOVE 'R'
#define SHOW 'S'
#define CLEAR 'C'
#define END 'E'

/*
* Main-method where the program starts
*/
int main(void) {
    char command;
    // Create the startpoint (Anchor)
    createHeadElement();
    

    // Commands I(nsert), R(emove), S(how), C(lear), E(nd)  
    while(1){
        printf("What would you like to do? Enter your command:\n");
        printf("I = Insert, R = Remove, S = Show, C = Clear, E = End\n");

        scanf("%c", &command);
        command = toupper(command);
        switch(command) {
            case INSERT:
                // Insert-Command
                printf("Execute Insert-Command...\n");
                insert(readPerson());
                printf("\n");
                break;
            case REMOVE:
                // Remove-Command
                printf("Execute Remove-Command...\n");
                removePerson(readPerson());
                printf("\n");
                break;
            case SHOW:
                // Show-Command
                printf("Execute Show-Command...\n");
                printOutList();
                printf("\n");
                break;
            case CLEAR:
                // Clear-Command
                printf("Execute Clear-Command...\n");
                clearList();
                printf("\n");
                break;
            case END:
                printf("The program terminate...\n");
                return EXIT_SUCCESS;
                break;
            default:
                printf("[FAILED]...This is not an valid command...\n");
                printf("\n");
        }
        printf("To enter the next command press [ENTER]");
        while((getchar())!= '\n');
        
    }         
    
    return EXIT_SUCCESS;         
}