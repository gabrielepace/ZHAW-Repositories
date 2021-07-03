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
#include <math.h>
#include "CUnit/Basic.h"
#include "test_utils.h"
#include <assert.h>

#ifndef TARGET // must be given by the make file --> see test target
#error missing TARGET define
#endif

/// UUT - Unit-Under-Test
double calculate(char text[]);

/// Epsilo for double comparisons.
#define EPSILON 0.001

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
static void test_number(void)
{
	// arrange
	// act
	double d = calculate("1");
	// assert
	CU_ASSERT_DOUBLE_EQUAL(d, 1.0, EPSILON);
}
static void test_term(void)
{
	// arrange
	// act
	double d = calculate("1+2");
	// assert
	CU_ASSERT_DOUBLE_EQUAL(d, 3.0, EPSILON);
}
static void test_factor(void)
{
	// arrange
	// act
	double d = calculate("2*3");
	// assert
	CU_ASSERT_DOUBLE_EQUAL(d, 6.0, EPSILON);
}
static void test_unary(void)
{
	// arrange
	// act
	double d = calculate("-2*3");
	// assert
	CU_ASSERT_DOUBLE_EQUAL(d, -6.0, EPSILON);
}
static void test_nested(void)
{
	// arrange
	// act
	double d = calculate("-(2+3)*-4");
	// assert
	CU_ASSERT_DOUBLE_EQUAL(d, 20.0, EPSILON);
}
static void test_special_values(void)
{
	// arrange
	// act
	double d1 = calculate("1/0");
	double d2 = calculate("-1/0");
	double d3 = calculate("0/0");
	// assert
	CU_ASSERT_EQUAL(isinf(d1), 1);
	CU_ASSERT_EQUAL(isinf(d2), -1);
	CU_ASSERT_TRUE(isnan(d3));
}

/**
 * @brief Registers and runs the tests.
 */
int main(void)
{
	// setup, run, teardown
	TestMainBasic("Hello World", setup, teardown
				  , test_number
				  , test_term
				  , test_factor
				  , test_unary
				  , test_nested
				  , test_special_values
				  );
}
