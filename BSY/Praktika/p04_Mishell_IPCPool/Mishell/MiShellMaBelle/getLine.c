/******************************************************************************
// File:    getLine.c
// Fach:    BSy
// Autor:   M. Thaler
// Version: v.fs20
******************************************************************************/

#include <stdio.h>
#include <stdlib.h>
#include "getLine.h"
#include <string.h>
#include <readline/readline.h>
#include <readline/history.h>

/*****************************************************************************/
/* getLine() reads a line or at most count-1 characters from stdin into the  */
/* buffer buf, returns the number of characters (! must be <= count-1        */

int getLine(const char *prompt, char *buf, int count) {
  if(!history_is_stifled()){
    stifle_history(100);
  }
  // komplette Kommandozeile auf einmal nach buf kopieren
  strncpy(buf, readline(prompt), count-1);
  add_history(buf);
  return strlen(buf);
}

/*****************************************************************************/
