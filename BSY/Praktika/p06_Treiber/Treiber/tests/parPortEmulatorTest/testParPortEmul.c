/**************************************************************************
* Test for parallel port emulator
* Author:  M. Thaler
* File:    ParPortEmul.c
* Version: 5/2018
***************************************************************************/

#include <stdio.h>
#include <unistd.h>
#include <stdlib.h>
#include <fcntl.h>
#include <sys/types.h>
#include <sys/ioctl.h>

int main(void) {
    int  i;
    char wbuf[1], rbuf[2];
    int  fd[3];
    int  errcount = 0;

    fd[0] = open("/dev/ParPortEmul_lp0", O_RDWR);
    fd[1] = open("/dev/ParPortEmul_lp1", O_RDWR);
    fd[2] = open("/dev/ParPortEmul_lp2", O_RDWR);

    if ((fd[0] < 0) || (fd[1] < 0) || (fd[2] < 0)) {
        printf("\n*** cannot open parallel port emulation ***\n");
        printf("\n***    make sure the modul is loaded    ***\n\n");
        return 0;
    }

    for (i = 0; i < 3; i++) {
        rbuf[0] = rbuf[1] = -1;
        wbuf[0] = '@';                      // write '@'
        write(fd[i], wbuf, 1);
        ioctl(fd[i], 10);                   // copy: written -> read
        read(fd[i], rbuf, 2);               // read data -> should be '@'
        if (rbuf[0] != '@')
            errcount++;
    }

    for (i = 0; i < 3; i++) {
        rbuf[0] = rbuf[1] = -1;
        ioctl(fd[i], 11);                   // should read data value 2
        read(fd[i], rbuf, 1);               // read only data port
        if (rbuf[0] != 11)
            errcount++;
    }

    for (i = 0; i < 3; i++) {
        rbuf[0] = rbuf[1] = -1;
        ioctl(fd[i], 12);                   // should read data value 2
        read(fd[i], rbuf, 2);               // read data and cntl port
        if ((rbuf[0] != 12) || (rbuf[1] != 12))
            errcount++;
    }


    if (errcount == 0) {
        printf("\n*** paralel port emulation works ***\n\n");
    } else {
        printf("*** paralel port emulation failed ***\n\n");
    }

    for (i = 0; i < 3; i++) {
        close(fd[i]);
    }

}
