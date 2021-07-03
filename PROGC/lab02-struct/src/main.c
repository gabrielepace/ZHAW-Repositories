/* ----------------------------------------------------------------------------
 * --  _____       ______  _____                                              -
 * -- |_   _|     |  ____|/ ____|                                             -
 * --   | |  _ __ | |__  | (___    Institute of Embedded Systems              -
 * --   | | | '_ \|  __|  \___ \   Zuercher Hochschule Winterthur             -
 * --  _| |_| | | | |____ ____) |  (University of Applied Sciences)           -
 * -- |_____|_| |_|______|_____/   8401 Winterthur, Switzerland               -
 * ----------------------------------------------------------------------------
 */
/**
 * @file
 * @brief Lab implementation
 */
#include <stdio.h>
#include <stdlib.h>
#include <math.h>

/**
 * @brief  point structure of double coordinates
 */
// begin students to add code for task 4.1
	struct point {
	    double x;
	    double y;
	};
// end students to add code


/**
 * @brief Main entry point.
 * @param[in] argc  The size of the argv array.
 * @param[in] argv  The command line arguments,
 *                  with argv[0] being the command call,
 *                  argv[1] the 1st argument,
 *                  argv[argc-1] the last argument.
 * @returns Returns EXIT_SUCCESS (=0) on success.
 */
int main(int argc, char* argv[])
{
	double distance = 0.0;

	// begin students to add code for task 4.1
	struct point a = { 0.0, 0.0 };
	struct point b = { 0.0, 0.0 };
	// parse arguments with error handling
	if (argc != 5){
	   printf("ERROR: Wrong number of arguments\n");
	   return EXIT_FAILURE;
	}
	
	// make two point variable p1 and p2 from the parsed arguments
	int argument = sscanf(argv[1], "%lf", &a.x);
	if(argument != 1){
		printf("ERROR: Failed to parse argument\n");
		return EXIT_FAILURE;
	}

	argument += sscanf(argv[2], "%lf", &a.y);
	if(argument != 2){
		printf("ERROR: Failed to parse argument\n");
		return EXIT_FAILURE;
	}

	argument += sscanf(argv[3], "%lf", &b.x);
	if(argument != 3){
		printf("ERROR: Failed to parse argument\n");
		return EXIT_FAILURE;
	}

	argument += sscanf(argv[4], "%lf", &b.y);
	if(argument != 4){
		printf("ERROR: Failed to parse argument\n");
		return EXIT_FAILURE;
	}

	// calcuate the dx and dy values
	double deltaX = a.x-b.x;
	double deltaY = a.y-b.y;

	// calculate distance form dx and dy values
	distance = sqrt((deltaX*deltaX) + (deltaY*deltaY));

	// end students to add code

	(void)printf("distance = %g\n", distance);
	
	return EXIT_SUCCESS;
}