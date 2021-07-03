/******************************************************************************
* Ampel Controller
* Author:	M. Thaler
* File:		ampelController.c
* Version:	5/2016	M. Thaler
******************************************************************************/

#include <unistd.h>
#include <stdlib.h>
#include <stdio.h>
#include <errno.h>
#include <string.h>
#include <fcntl.h>

#define OFF    0x80
#define RED    0x81
#define YELLOW 0x82
#define GREEN  0x84
#define STRELN 50

int main(int argc, char* argv[]) {
	int res = 0;
	const char *parDev = "/dev/ParDev";
    char lights[1];                         // byte to   ampel (lights)
    char button[1];                         // byte from ampel (button)

    // open device and initialize ampel
    // your code
    char errStr[STRELN];
    int MyDev, i;

    MyDev = open(parDev, O_RDWR);
    if (MyDev < 1) {
	    sprintf(errStr, "\n** Cannot open device %s", *parDev);
	    perror(errStr);
	    exit(-1);
    }

    lights[0] = RED;

    // ampel loop
    
	while (1) {
        res = write(MyDev, lights, 1);
        res = read(MyDev,button,1);

        if(button[0] & 0x40){
            lights[0] = GREEN;
            res = write(MyDev, lights, 1);
            usleep(3000000);

            lights[0] = YELLOW;
            res = write(MyDev, lights, 1);
            usleep(3000000);

            for(i = 0; i < 3; i++){
                lights[0] = OFF;
                res = write(MyDev, lights, 1);
                usleep(500000);
                
                lights[0] = YELLOW;
                res = write(MyDev, lights, 1);
                usleep(500000);
            }

            lights[0] = RED;
        }
    }
}
