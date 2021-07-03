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
#include <assert.h>
#include <stdio.h>
#include "evaluate.h"

// begin students to add code for task 4.1
static double nop(double v){
	return v;
}

static double neg(double v){
	return -v;
}

static double add(double a, double b){
	return a + b;
}

static double sub(double a, double b){
	return a - b;
}

static double mul(double a, double b){
	return a * b;
}

static double div(double a, double b){
	return a / b;
}
// end students to add code


/// map for unary functions
struct unaryLookup {
	char op; ///< operator as key
	// begin students to add code for task 4.1
	double (*unary_function) (double a)//< function pointer to operator function
	// end students to add code
};

/// map for binary functions
struct binaryLookup {
	char op; ///< operator as key
	// begin students to add code for task 4.1
	double (*binary_function) (double a, double b)//< function pointer to operator function
	// end students to add code
};

/// lookup table for unary operations
static struct unaryLookup unaryLookupTable[] = {
	// begin students to add code for task 4.1
	// ...init key-value pairs
	{EXPR, nop},
	{PLUS, nop},
	{MINUS, neg},
	{NESTED, nop},
	{NUM, nop}
	// end students to add code
};

/// lookup table for binary operations
static struct binaryLookup binaryLookupTable[] = {
	// begin students to add code for task 4.1
	// ...init key-value pairs
	{ADD, add},
	{SUB, sub},
	{MUL, mul},
	{DIV, div}
	// end students to add code
};

double evaluateUnaryOp(char op, double right)
{
	// begin students to add code for task 4.1
	for(size_t i=0; i< sizeof(unaryLookupTable)/(sizeof(*unaryLookupTable)); i++){
		if(unaryLookupTable[i].op == op){
			return (*unaryLookupTable[i].unary_function)(right);
		}
	}
	// end students to add code
	assert(!"unexpected operator");
}

double evaluateBinaryOp(char op, double left, double right)
{
	// begin students to add code for task 4.1
	for(size_t i=0; i<sizeof(binaryLookupTable)/(sizeof(*binaryLookupTable)); i++){
		if(binaryLookupTable[i].op == op){
			double result = (*binaryLookupTable[i].binary_function)(left, right);
			return result;
		}
	}
	// end students to add code
	assert(!"unexpected operator");
}