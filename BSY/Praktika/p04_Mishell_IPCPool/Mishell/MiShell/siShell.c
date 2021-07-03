/******************************************************************************
// Aufgabe:    Simple Shell
// File:       sishell.c
// Fach:       Betriebssysteme
// Autor:      M. Thaler
// Version:    v.fs20
******************************************************************************/

#include <stdio.h>
#include <string.h>
#include <unistd.h>
#include <stdlib.h>
#include <sys/types.h>
#include <fcntl.h>
#include <sys/wait.h>

#include "getLine.h"

//*****************************************************************************

#define MAX_ARGV  16                    // max number of command line arguments
#define STR_LEN  255                    // max length of command line arguments

//*****************************************************************************
// split command line into tokens and store tokens -> argv[]

void correctArgv(char *argv[], int length, int pos);


void tokenizeCommand(char *cmdLine, char *argv[], char *redir[]) {
    const char *errMsg = "\n*** too many arguments ***\n";
    char* token;
    int idx = 0;
    redir[0] = NULL;
    redir[1] = NULL;

    token = strtok(cmdLine," \t\n");
    argv[idx++] = token;  // get first word
    while((token = strtok(NULL, " \t\n"))) { // erstes Argument NULL, weil strtok bei cmdLine weitermachen soll
        argv[idx] = token;
        idx++;
    }
    for (int i = 0 ; i < idx-1; i++){
        if (strcmp(argv[i], ">") == 0) {
            redir[1] = malloc(strlen(argv[i+1])+1);
            strcpy(redir[1], argv[i+1]);
            argv[i+1] = NULL;
            argv[i] = NULL;
            correctArgv(argv, idx, i);
            idx -= 2;
            i -= 1;
        }
        if (strcmp(argv[i], "<") == 0) {
            redir[0] = malloc(strlen(argv[i+1])+1);
            strcpy(redir[0], argv[i+1]);
            argv[i+1] = NULL;
            argv[i] = NULL;
            correctArgv(argv, idx, i);
            idx -= 2;
            i -= 1;
        }
      }
    argv[idx] = NULL;
    if (idx < MAX_ARGV) {
        argv[idx] = NULL;  // terminate argument list by NULL
    } else {
        printf("%s", errMsg);
        argv[0] = NULL;
    }
}

// "<" und ">" dürfen nicht als eigenständige Argumente behandelt werden
void correctArgv(char *argv[], int length, int pos){
    if (length < pos + 2){ // 2; < oder > plus Leerschlag
        return;
    }
    int i = pos;
    while(i+2 <= length){
        argv[i] = argv[i+2];
        argv[i+2] = NULL;
        i++;
    }
}

//-----------------------------------------------------------------------------
// execute an external command, exit on failure of fork

void externalCommand(char *argv[]) {
    pid_t PID;                          // process identifier
    if ((PID = fork()) == 0) {          // fork child process
        execvp(argv[0],  &argv[0]);     // execute command
        printf("!!! panic !!!\n");      // should not come here
            exit(-1);                   // if we came here ... we have to exit
    }
    else if (PID < 0) {
        printf("fork failed\n");        // fork didn't succeed
        exit(-1);                       // terminate sishell
    }
    else  {                             // here we are parents
        wait(0);                        // wait for child process to terminate
    }
}

int internalCommand(char *argv[]) {
    if (strcmp(argv[0], "logout") == 0 || strcmp(argv[0], "exit") == 0) {
        exit(0);
    }
    else if (strcmp(argv[0], "cd")== 0) {
        if (argv[1] == NULL){
            printf("path is needed!\n");
        }else {
            chdir(argv[1]);

        }
        return 1;
    }
    return 0;
}

//-----------------------------------------------------------------------------
// execute command if not NULL pointer (invalid or "empty" command)

void executeCommand(char *argv[], char *redir[]) {
  int stdin_copy = dup(0);
  int stdout_copy = dup(1);

  int infile;
  int outfile;

  // Auf Inputfile prüfen
  if (redir[0] != NULL) {
      infile = open(redir[0], O_RDONLY);
      dup2(infile, 0);
  }
  //auf Outputfile prüfen
  if (redir[1] != NULL) {
      outfile = open(redir[1], O_CREAT | O_TRUNC | O_WRONLY, 0644);
      dup2(outfile, 1);
  }
  if (argv[0] != NULL) {
      if (internalCommand(argv) == 0){
          externalCommand(argv);
      }
  }
  if (redir[0] != NULL) {
      dup2(stdin_copy, 0);
      redir[0] = NULL;
  }
  if (redir[1] != NULL) {
      dup2(stdout_copy, 1);
      redir[1] = NULL;
  }
}

//-----------------------------------------------------------------------------
// main program for shell

int main(void) {
    char  *argv[MAX_ARGV];               // pointer to command line arguments
    char  buf[STR_LEN];
    char  *redir[2];                  // buffer for command line and command

    while (1) {
        getLine("si ? ", buf, STR_LEN); // read one line from stdin
        tokenizeCommand(buf, argv, redir);      // split command line into tokens
        executeCommand(argv, redir);            // execute command
    }
    exit(0);
}

//*****************************************************************************
