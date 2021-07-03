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
// begin students to add code for task 4.2
typedef struct {
	double x;
	double y;
} point;
// end students to add code

/**
 * line structure of two points
 */
// begin students to add code for task 4.2
typedef struct {
	point pa;
	point pb;
} line;
// end students to add code

/**
 * @brief Main entry point.
 * @param[in] argc  The size of the argv array.
 * @param[in] argv  The command line arguments,
 *                  with argv[0] being the command call,
 *                  argv[1] the 1st argument,
 *                  argv[argc-1] the last argument.
 * @returns Returns EXIT_SUCCESS (=0) on success and
 *                  EXIT_FAILURE (=1) on error.
 */
int main(int argc, char* argv[])
{
	// begin students to add code for task 4.2
	if(argc == 1){
		return EXIT_FAILURE;
	}

	point pa = {0.0, 0.0};
	point pb = {0.0, 0.0};

	// parse arguments with error handling
	if(argc != 5){
		return EXIT_FAILURE;
	}

	int argument = sscanf(argv[1], "%lf", &pa.x);
	if(argument != 1){
		return EXIT_FAILURE;
	}

	argument += sscanf(argv[2], "%lf", &pa.y);
	if(argument != 2){
		return EXIT_FAILURE;
	}

	argument += sscanf(argv[3], "%lf", &pb.x);
	if(argument != 3){
		return EXIT_FAILURE;
	}

	argument += sscanf(argv[4], "%lf", &pb.y);
	if(argument != 4){
		return EXIT_FAILURE;
	}

	// make one line variable an initialize from the parsed arguments
	line lineInit = {
		{pa.x, pa.y},
		{pb.x, pb.y}
	};
	// print the line variable in the following format: 
	//	(void)printf("line %g/%g-%g/%g\n", ...);
	(void) printf("line %g/%g-%g/%g\n", lineInit.pa.x, lineInit.pa.y, lineInit.pb.x, lineInit.pb.y);

	// end students to add code
	
	return EXIT_SUCCESS;
}