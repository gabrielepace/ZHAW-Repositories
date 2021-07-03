//*****************************************************************************
// Purpose:	simulation of traffic lights
//          simple pseudo graphics implementation that does not require java
// Author:	M. Thaler
// Version:	5/2019
//*****************************************************************************

#include <stdio.h>
#include <stdlib.h>
#include <unistd.h>
#include <fcntl.h>

//*****************************************************************************

#define PAR_PORT "/dev/ParPortEmul_lp1"         // define port name

#define RED   "\033[41m      \033[0m"           // traffic lights
#define GREEN "\033[42m      \033[0m"
#define BROWN "\033[43m      \033[0m"
#define BLANK "      "

//*****************************************************************************
// top and bottom line of
 
void topBottom(void) {
    printf("    --------    "); 
    printf("\n");
}

//*****************************************************************************
// simulate button -> activated by "enter"

void button(int fd) {
    char c;
    printf("\n       ");
    fflush(stdout);
    c = 0;
    if (read(0, &c, 1) > 0) {
        //printf("key");
        fflush(stdout);
        c = 0xff;
        write(fd, &c, 1);
    } else write(fd, &c, 1);
    usleep(100*1000);
    printf("\n");
}

//*****************************************************************************
// set color of light

void setColor(char *c, int n) {
    int i;
    for (i = 0; i < n; i++) {
        printf("   | %s |   ", c); 
        printf("\n");
    }
}

//*****************************************************************************
// set ampel: blank red, yellow (brown), green

void setAmpel(int c) {
    int nl = 3;

    c = c & 0x7;

    if (c == 0) {
        topBottom();
        setColor(BLANK, 1);
        setColor(BLANK, nl);
        setColor(BLANK, 1);  
        setColor(BLANK, nl);
        setColor(BLANK, 1); 
        setColor(BLANK, nl);
        setColor(BLANK, 1);
        topBottom(); 
    }
    else if (c == 1) {
        topBottom();
        setColor(BLANK, 1);
        setColor(RED,   nl);
        setColor(BLANK, 1);  
        setColor(BLANK, nl);
        setColor(BLANK, 1); 
        setColor(BLANK, nl);
        setColor(BLANK, 1);
        topBottom(); 
    }
    else if (c == 2) {
        topBottom();
        setColor(BLANK, 1);
        setColor(BLANK, nl);
        setColor(BLANK, 1);  
        setColor(BROWN, nl);
        setColor(BLANK, 1); 
        setColor(BLANK, nl);
        setColor(BLANK, 1);
        topBottom(); 
    }
    else if (c == 4) {
        topBottom();
        setColor(BLANK, 1);
        setColor(BLANK, nl);
        setColor(BLANK, 1);  
        setColor(BLANK, nl);
        setColor(BLANK, 1); 
        setColor(GREEN, nl);
        setColor(BLANK, 1);
        topBottom(); 
    }
}

//*****************************************************************************

int main(void) {
    int  fd, er;
    char newin = 0, in = 1;

    // ... manual
    printf("\nTo activate the button -> press \"ENTER\"\n");
    printf("\n\tto continue -> press \"ENTER\": ");
    getchar();

    // open port
    fd = open(PAR_PORT, O_RDWR);

    // set port nonblocking
    if (fd > 0) {
        if (fcntl(fd, F_SETFL, fcntl(fd, F_GETFL) | O_NONBLOCK) < 0) {
            perror("*** file error 1 ***\n"); //exit(1);
        }
    } else {
        perror("*** file error 2 ***\n"); //exit(1);
    }

    // set stdin nonblocking (button)
    if (fcntl(0, F_SETFL, fcntl(0, F_GETFL) | O_NONBLOCK) < 0) {
        printf("*** file error 3 ***\n"); //exit(1);
    }

    while (1) {
        printf("\033[2J\033[1;H");  // clear screen
        er = read(fd, &newin, 1);   // read data from port -> color
        if (er > 0)                 //     if data available -> new color
            in = newin;
        setAmpel(in);               // set ampel
        button(fd);                 // check if key pressed and write to port   
    }

}

//*****************************************************************************
