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
/// @brief The name of the STDERR text file.
#define ERRFILE "stderr.txt"

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
static void test_main_with_no_arg(void)
{
	// arrange

	// act
	int exit_code = system(XSTR(TARGET) " 1>" OUTFILE " 2>" ERRFILE);
	// assert
	CU_ASSERT_EQUAL(exit_code, 255 << 8);
}
static void test_main_with_negative_arg(void)
{
	// arrange

	// act
	int exit_code = system(XSTR(TARGET) " -895 1>" OUTFILE " 2>" ERRFILE);
	// assert
	CU_ASSERT_EQUAL(exit_code, 255 << 8);
}
static void test_main_with_non_numeric_arg(void)
{
	// arrange

	// act
	int exit_code = system(XSTR(TARGET) " garbage 1>" OUTFILE " 2>" ERRFILE);
	// assert
	CU_ASSERT_EQUAL(exit_code, 255 << 8);
}

static void test_main_with_895_0_rest(void)
{
	// arrange
	const char *out_txt[] =
		{ "CHF 8.95:\n"
		, "- 1 x 5.00\n"
		, "- 1 x 2.00\n"
		, "- 1 x 1.00\n"
		, "- 1 x 0.50\n"
		, "- 2 x 0.20\n"
		, "- 0 x 0.10\n"
		, "- 1 x 0.05\n"
		, "Kein Rest\n"
		};
	const char *err_txt[] = {};
	// act
	int exit_code = system(XSTR(TARGET) " 895 1>" OUTFILE " 2>" ERRFILE);
	// assert
	CU_ASSERT_EQUAL(exit_code, 0 << 8);
	assert_lines(OUTFILE, out_txt, sizeof(out_txt)/sizeof(*out_txt));
	assert_lines(ERRFILE, err_txt, sizeof(err_txt)/sizeof(*err_txt));
}
static void test_main_with_897_2_rest(void)
{
	// arrange
	const char *out_txt[] =
		{ "CHF 8.97:\n"
		, "- 1 x 5.00\n"
		, "- 1 x 2.00\n"
		, "- 1 x 1.00\n"
		, "- 1 x 0.50\n"
		, "- 2 x 0.20\n"
		, "- 0 x 0.10\n"
		, "- 1 x 0.05\n"
		, "Rest = 0.02\n"
		};
	const char *err_txt[] = {};
	// act
	int exit_code = system(XSTR(TARGET) " 897 1>" OUTFILE " 2>" ERRFILE);
	// assert
	CU_ASSERT_EQUAL(exit_code, 2 << 8);
	assert_lines(OUTFILE, out_txt, sizeof(out_txt)/sizeof(*out_txt));
	assert_lines(ERRFILE, err_txt, sizeof(err_txt)/sizeof(*err_txt));
}

/**
 * @brief Registers and runs the tests.
 */
int main(void)
{
	// setup, run, teardown
	TestMainBasic("Integer Arithmetic", setup, teardown
				  , test_main_with_no_arg
				  , test_main_with_negative_arg
				  , test_main_with_non_numeric_arg
				  , test_main_with_895_0_rest
				  , test_main_with_897_2_rest
				  );
}
