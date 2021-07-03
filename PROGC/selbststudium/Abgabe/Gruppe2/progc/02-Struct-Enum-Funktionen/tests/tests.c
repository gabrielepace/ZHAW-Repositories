/**
* Selbststudium 2 - Struct-Enum-Funktionen
* Aufgabe 1: Berechnung des Folgetages
*
* @author Gruppe 2: Gabriele Pace (pacegab1), Omar Shakir (shakioma), Vincent Schmid (schmivin)
*
* @brief Test suite for the given package.
* @file test.c
*/

#include <stdio.h>
#include <stdlib.h>
#include "CUnit/Basic.h"
#include "test_utils.h"

#ifndef TARGET // must be given by the make file --> see test target
#error missing TARGET define
#endif

/// @brief The name of the STDOUT text file.
#define OUTFILE "stdout.txt"
/// @brief The name of the STDERR text file.
#define ERRFILE "stderr.txt"
/// @brief The stimulus for valid dates
#define INFILE_VALID "valid.input"
/// @brief The stimulus for invalid dates
#define INFILE_INVALID "invalid.input"


// setup & cleanup
static int setup(void)
{
	remove_file_if_exists(OUTFILE);
	remove_file_if_exists(ERRFILE);
	return 0; // success
}

static int teardown(void)
{
	// Do nothing.
	// Especially: do not remove result files - they are removed in int setup(void) *before* running a test.
	return 0; // success
}


// tests
static void test_valid_dates(void)
{
	// arrange
	const char *out_txt[] = {
		"Please enter a valid date (dd MM yyyy):\n",
		"Next day: 1.1.2000\n"		
	};
	// act
	int exit_code = system(XSTR(TARGET) " 1>" OUTFILE " 2>" ERRFILE " <" INFILE_VALID);
	// assert
	CU_ASSERT_EQUAL(exit_code, 0);
	assert_lines(OUTFILE, out_txt, sizeof(out_txt)/sizeof(*out_txt));
}

static void test_invalid_dates(void)
{
	// arrange
	const char *out_txt[] = {
		"Please enter a valid date (dd MM yyyy):\n",
		"Invalid date!\n",
		"Please enter a valid date (dd MM yyyy):\n",
		"Invalid date!\n",
		"Please enter a valid date (dd MM yyyy):\n",
		"Invalid date!\n",
		"Please enter a valid date (dd MM yyyy):\n",
		"Invalid date!\n",
		"Please enter a valid date (dd MM yyyy):\n",
		"Invalid date!\n",
		"Please enter a valid date (dd MM yyyy):\n",
		"Next day: 1.3.2016\n"
	};
	// act
	int exit_code = system(XSTR(TARGET) " 1>" OUTFILE " 2>" ERRFILE " <" INFILE_INVALID);
	// assert
	CU_ASSERT_EQUAL(exit_code, 0);
	assert_lines(OUTFILE, out_txt, sizeof(out_txt)/sizeof(*out_txt));
}


/**
 * @brief Registers and runs the tests.
 */
int main(void)
{
	// setup, run, teardown
	TestMainBasic("Selbststudium 2 - Struct-Enum-Funktionen", setup, teardown
				  , test_valid_dates
				  , test_invalid_dates
				  );
}