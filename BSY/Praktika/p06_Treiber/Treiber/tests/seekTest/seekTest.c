/*************************************************************
* Purpose   Driver Testprogram
* Author:   M. Thaler
* File:     seekTest.c
* Revision: 11/2001    M. Thaler
* Version:  v.fs20
**************************************************************/

#include <unistd.h>
#include <stdlib.h>
#include <stdio.h>
#include <errno.h>
#include <string.h>
#include <fcntl.h>

#define STRELN 50

int main(int argc, char* argv[]) {
    int MyDev, pos;
    int res = 0;

    const char *DefDev = "/dev/MemDev0";
    const char *DefStr = "0123456789ABCDEFGHIKabcdefghikLMNOPQRSTU";
    
    char str[STRELN];
    char rstr[STRELN];
    char dev[STRELN];
    
    char errStr[STRELN];

    strcpy(dev, DefDev);
    strcpy(str, DefStr);

    printf("\n--> device: %s",   dev);    
    printf("\n--> string: %s\n", str);    

    MyDev = open(dev, O_RDWR);
    if (MyDev < 1) {
        sprintf(errStr, "\n** Cannot open device %s", dev);
        perror(errStr);
        exit(-1);
    }
    else
        printf("\n-> writing to %s(%d): %s(%d)\n",dev,MyDev,str,strlen(str));
    res = write(MyDev, str, strlen(str));

    printf("\n");

    pos = lseek(MyDev, 0, SEEK_END);
    printf("-> seeting offset to end of file -> %d\n", pos);

    pos = lseek(MyDev, -10, SEEK_END);
    res = read(MyDev, rstr, 10);
    rstr[10] = '\0';
    printf("-> reading from position -> %2d %s\n", pos, rstr);

    pos = lseek(MyDev, -20, SEEK_END);
    res = read(MyDev, rstr, 10);
    rstr[10] = '\0';
    printf("-> reading from position -> %2d %s\n", pos, rstr);

    pos = lseek(MyDev, -30, SEEK_END);
    res = read(MyDev, rstr, 10);
    rstr[10] = '\0';
    printf("-> reading from position -> %2d %s\n", pos, rstr);

    pos = lseek(MyDev, -40, SEEK_END);
    res = read(MyDev, rstr, 10);
    rstr[10] = '\0';
    printf("-> reading from position -> %2d %s\n", pos, rstr);

    pos = lseek(MyDev, 25, SEEK_SET);
    res = read(MyDev, rstr, 10);
    rstr[10] = '\0';
    printf("-> reading from position -> %2d %s\n", pos, rstr);

    pos = lseek(MyDev, 25, SEEK_SET);
    pos = lseek(MyDev, 5, SEEK_CUR);
    res = read(MyDev, rstr, 10);
    rstr[10] = '\0';
    printf("-> reading from position -> %2d %s\n", pos, rstr);

    res = close(MyDev);
}
