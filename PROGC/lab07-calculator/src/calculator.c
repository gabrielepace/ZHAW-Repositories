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
#include <ctype.h>
#include <string.h>
#include "calculator.h"
#include "evaluate.h"

static double parseAndEvaluateExpression();


static char *input = NULL;

static void error(const char message[])
{
	fprintf(stderr, "ERROR: %s (at '%s')\n", message ? message : "<unknown error>", input ? input : "<end>");
	exit(EXIT_FAILURE);
}

static char currToken()
{
	if (!input) return EOT;
	if (!*input) return EOT;
	if (isdigit(*input)) return NUM;
	return *input;
}

static char skipSpaces()
{
	while(input && isspace(*input)) input++;
	return currToken();
}

static char nextToken()
{
	if (input && *input) input++;
	return skipSpaces();
}

static const char *optionalOpAndNext(const char ops[])
{
	const char *op = strchr(ops, currToken());
	if (op) nextToken();
	return op;
}

static double parseAndEvaluateUnaryOp(double (*parse)(void), const char ops[])
{
	const char* op = optionalOpAndNext(ops);
	if (op) return evaluateUnaryOp(*op, parseAndEvaluateUnaryOp(parse, ops));
	return (*parse)();
}

static double parseAndEvaluateBinaryOp(double (*parse)(void), const char ops[])
{
	double value = (*parse)();
	const char* op = optionalOpAndNext(ops);
	while (op) {
		value = evaluateBinaryOp(*op, value, (*parse)());
		op = optionalOpAndNext(ops);
	}
	return value;
}

static double parseAndEvaluateNumber()
{
	double value = 0.0;
	char token = currToken();
	if (NUM != token) error("number expected");
	while (isdigit(*input)) {
		value *= 10.0;
		value += *input-'0';
		input++;
	}
	if (*input == '.') {
		input++;
		if (!isdigit(*input)) error("fraction number expected");
		double factor = 1.0;
		while (isdigit(*input)) {
			factor *= 10.0;
			value += (*input-'0')/factor;
			input++;
		}
	}
	skipSpaces();
	return evaluateUnaryOp(NUM, value);
}

static double parseAndEvaluateNestedExpression()
{
	if ('(' != currToken()) error("number or '(' ... ')' expected");
	if (nextToken() == EOT) error("expression expected");
	double value = evaluateUnaryOp(NESTED, parseAndEvaluateExpression());
	if (currToken() != ')') error("closing ')' expected");
	nextToken();
	return value;

}

static double parseAndEvaluatePrimary()
{
	if (NUM == currToken()) return parseAndEvaluateNumber();
	return parseAndEvaluateNestedExpression();
}

static double parseAndEvaluateFactor() { return parseAndEvaluateUnaryOp(parseAndEvaluatePrimary, "+-"); }

static double parseAndEvaluateTerm() { return parseAndEvaluateBinaryOp(parseAndEvaluateFactor, "*/"); }

static double parseAndEvaluateExpression() { return parseAndEvaluateBinaryOp(parseAndEvaluateTerm, "+-"); }

double calculate(char text[])
{
	input = text;
	if (skipSpaces() == EOT) error("missing expression text");
	double value = evaluateUnaryOp(EXPR, parseAndEvaluateExpression());
	if (EOT != currToken()) error("excess expression text found");
	return value;
}
