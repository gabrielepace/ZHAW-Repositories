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
#ifndef _EVALUATE_H_
#define _EVALUATE_H_

enum {
	// special end-of-text token
	EOT     = 1,
	// special lookup table key
	EXPR,
	// lookup table keys and tokens
	NUM,
	NESTED,
	ADD     = '+',
	SUB     = '-',
	MUL     = '*',
	DIV     = '/',
	PLUS    = '+',
	MINUS   = '-',
};

/**
 * @brief        Evaluate binary operation.
 */
double evaluateBinaryOp(char op, double left, double right);

/**
 * @brief        Evaluate unary operation.
 */
double evaluateUnaryOp(char op, double right);

#endif
