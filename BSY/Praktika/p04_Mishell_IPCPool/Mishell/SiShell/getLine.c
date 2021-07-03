/******************************************************************************
// File:    getLine.c
// Fach:    BSy
// Autor:   M. Thaler
// Version: v.fs20
******************************************************************************/

#include <stdio.h>
#include <stdlib.h>
#include "getLine.h"

/*****************************************************************************/
/* getLine() reads a line or at most count-1 characters from stdin into the  */
/* buffer buf, returns the number of characters (! must be <= count-1        */

int getLine(const char *prompt, char *buf, int count) {
    printf("%s", prompt);
    int i;
    int cha = getchar();
    *buf = cha;
    
    for(i = 0; i < count && cha != EOF && cha != '\n' && cha != '\0'; cha = getchar()){
        buf[i] = cha;
        i++;
    }
    buf[i] = '\0'; // Sentinel am Ende der Kommandozeile
    
    return i;
}

/*****************************************************************************/
