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
#include <stdarg.h>
#include "CUnit/Basic.h"
#include "test_utils.h"

/// from main: Unit-Under-Test
void swap(void *a, void *b, size_t bytes);

#ifndef TARGET // must be given by the make file --> see test target
#error missing TARGET define
#endif

/// convenience macro to assert array values
#define ASSERT_VALUES(V, ...) do { assert_values(#V, V, sizeof(V)/sizeof(*V), __VA_ARGS__); } while(0)


// setup & cleanup
static int setup(void)
{
	// Do nothing.
	return 0; // success
}

static int teardown(void)
{
	// Do nothing.
	// Especially: do not remove result files - they are removed in int setup(void) *before* running a test.
	return 0; // success
}

static void assert_values(const char name[], int array[], size_t n, ...) {
	va_list ap;
	va_start(ap, n);
	for(size_t i = 0; i < n; i++) {
		int value = va_arg(ap, int);
		(void)printf("%s[%zi] = %d =?= %d\n", name, i, array[i], value);
		CU_ASSERT_EQUAL(array[i], value);
	}
	va_end(ap);
}

// tests
static void test_swap_empty(void)
{
	// arrange
	int values[0] = { };
	int others[0] = { };

	// act
	swap(values, others, sizeof(values));
	// assert
	ASSERT_VALUES(values, 0);
	ASSERT_VALUES(others, 0);
}

static void test_swap_one(void)
{
	// arrange
	int values[1] = { 1 };
	int others[1] = { 0 };

	// act
	swap(values, others, sizeof(values));
	// assert
	ASSERT_VALUES(values, 0);
	ASSERT_VALUES(others, 1);
}

static void test_swap_two(void)
{
	// arrange
	int values[2] = { 1, 2 };
	int others[2] = { 0 };

	// act
	swap(values, others, sizeof(values));
	// assert
	ASSERT_VALUES(values, 0, 0);
	ASSERT_VALUES(others, 1, 2);
}

/**
 * @brief Registers and runs the tests.
 */
int main(void)
{
	// setup, run, teardown
	TestMainBasic("Generic Swap", setup, teardown
				  , test_swap_empty
				  , test_swap_one
				  , test_swap_two
				  );
}
