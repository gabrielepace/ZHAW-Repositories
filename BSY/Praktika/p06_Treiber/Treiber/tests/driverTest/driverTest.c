/*************************************************************
* Purpose:  Driver Testprogram
* Author:	M. Thaler
* File:		DriverTest.cc
* Version:	11/2001	M. Thaler 
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
	int MyDev, j;
	int res = 0;

	const char *DefDev = "/dev/MemDev0";
	const char *DefStr = "I am going to the data buffer, follow me !\n";
	
	char str[STRELN];
	char dev[STRELN];
	
	char errStr[STRELN];

	switch (argc) {
		case 2:
			if (argv[1][0] == '/') {
				strcpy(dev, argv[1]);
				strcpy(str, DefStr);
			}
			else {
				strcpy(str, argv[1]);
				strcpy(dev, DefDev);
			}
			break;
		case 3:
			if (argv[1][0] == '/') {
				strcpy(dev, argv[1]);
				strcpy(str, argv[2]);
			}
			else {
				strcpy(dev, argv[2]);
				strcpy(str, argv[1]);
			}
			break;
		default:
			strcpy(str, DefStr);
			strcpy(dev, DefDev);
			printf("\n--> using default settings\n");
			break;
	}

	printf("\n--> device: %s\n", dev);	
	printf("--> string: %s\n", str);	

	MyDev = open(dev, O_RDWR);
	if (MyDev < 1) {
		sprintf(errStr, "\n** Cannot open device %s", dev);
		perror(errStr);
		exit(-1);
	}
	else
		printf("\n--> writing to %s(%d): %s\n", dev, MyDev, str);
	res = write(MyDev, str, strlen(str));

	strcpy(str, "------------------------------------------"); 

	printf("\n--> filling local string with \"%s\"\n", str);

	int num_chars = 50;
	printf("\n--> sleeping for 3 seconds\n");
	sleep(3);

	printf("\n--> reading from %s", dev);
	for (j = 0; j < 3; j++) {
		printf(" .");
		fflush(stdout);
		sleep(1);
	}
	printf("\n");
	res = read(MyDev, str, num_chars);
	str[res] = '\0';
	if (res > 0)
		printf("--> just read %d characters: %s\n", res, str);
	else {
		perror("something's wrong");
		printf("--> got nothing:   num %d\n", res);
	}
	printf("\n");
	res = close(MyDev);
}
