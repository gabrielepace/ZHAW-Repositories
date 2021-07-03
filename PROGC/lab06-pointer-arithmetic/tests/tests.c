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

/**
 * @brief   swaps two characters at the given addresses.
 * @param[in] a  the location of 1st character
 * @param[in] b  the location of 2nd character
 */
void swap_char(char *a, char *b);
/**
 * @brief              Reverses buffer content inplace.
 * @param[in] buffer   The buffer to process.
 * @return             The passed buffer.
 */
char *reverse(char buffer[]);

#ifndef TARGET // must be given by the make file --> see test target
#error missing TARGET define
#endif

// setup & cleanup
static int setup(void)
{
	// Do nothing.
	return 0; // success
}

static int teardown(void)
{
	// Do nothing.
	return 0; // success
}


// tests
static void test_swap(void)
{
	// arrange
	char x = 'x';
	char y = 'y';
	// act
	swap_char(&x, &y);
	// assert
	CU_ASSERT_EQUAL(x, 'y');
	CU_ASSERT_EQUAL(y, 'x');
}

static void test_empty(void)
{
	// arrange
	char buffer[] = "";
	// act
	int n = printf("\n%s\n", reverse(buffer));
	// assert
	CU_ASSERT_EQUAL(n, 2);
}

static void test_odd(void)
{
	// arrange
	char buffer[] = "abc";
	// act
	int n = printf("\n%s\n", reverse(buffer));
	// assert
	CU_ASSERT_EQUAL(n, 5);
	CU_ASSERT_STRING_EQUAL(buffer, "cba");
}

static void test_even(void)
{
	// arrange
	char buffer[] = "abcd";
	// act
	int n = printf("\n%s\n", reverse(buffer));
	// assert
	CU_ASSERT_EQUAL(n, 6);
	CU_ASSERT_STRING_EQUAL(buffer, "dcba");
}

/**
 * @brief Registers and runs the tests.
 */
int main(void)
{
	// setup, run, teardown
	TestMainBasic("Hello World", setup, teardown
				  , test_swap
				  , test_empty
				  , test_odd
				  , test_even
				  );
}
