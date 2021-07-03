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
 * @brief Test suite for the given package.
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

// setup & cleanup
static int setup(void)
{
	remove_file_if_exists(OUTFILE);
	return 0; // success
}

static int teardown(void)
{
	// Do nothing.
	// Especially: do not remove result files - they are removed in int setup(void) *before* running a test.
	return 0; // success
}


// tests
static void test_p1p2(void)
{
	// arrange
	const char *out_txt[] = {
		"line -1/1-2/5\n"
	};
	// act
	int exit_code = system(XSTR(TARGET) " -1 1 2 5 1>" OUTFILE);
	// assert
	CU_ASSERT_EQUAL(exit_code, 0);
	assert_lines(OUTFILE, out_txt, sizeof(out_txt)/sizeof(*out_txt));
}

static void test_errors(void)
{
	// arrange
	// act
	int exit_code = system(XSTR(TARGET)); // no input
	// assert
	CU_ASSERT_NOT_EQUAL(exit_code, 0);

	// act
	exit_code = system(XSTR(TARGET) " 1 2 3"); // too little input
	// assert
	CU_ASSERT_NOT_EQUAL(exit_code, 0);

	// act
	exit_code = system(XSTR(TARGET) " 1 2 3 4 5"); // too many input
	// assert
	CU_ASSERT_NOT_EQUAL(exit_code, 0);

	// act
	exit_code = system(XSTR(TARGET) " 1 2 3 a"); // non numeric input
	// assert
	CU_ASSERT_NOT_EQUAL(exit_code, 0);
}


/**
 * @brief Registers and runs the tests.
 */
int main(void)
{
	// setup, run, teardown
	TestMainBasic("Structs", setup, teardown
				  , test_p1p2
				  , test_errors
				  );
}
