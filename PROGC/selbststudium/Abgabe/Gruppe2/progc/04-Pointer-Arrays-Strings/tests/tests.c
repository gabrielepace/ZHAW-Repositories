/**
* Selbststudium 4 - Pointer-Arrays-Strings
* Aufgabe 1: Woerter sortieren
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
#include "wordlist.h"

/// @brief The name of the STDOUT text file.
#define OUTFILE "stdout.txt"
/// @brief The name of the STDERR text file.
#define ERRFILE "stderr.txt"
/// @brief The input file with test words
#define INFILE_TEST_COMPLETE "testdata-words.input"
/// @brief The input file with a word exceeding the length-limit
#define INFILE_TEST_ERROR_LENGTH "testdata-error-maxlength.input"
/// @brief The input file with a word exceeding the length-limit
#define INFILE_TEST_ERROR_EMPTY "testdata-error-empty.input"
/// @brief The input file with a word exceeding the length-limit
#define INFILE_TEST_ERROR_WHITESPACE "testdata-error-whitespace.input"
/// @brief The input file with more than 100 words (exceeding word-list length)
#define INFILE_TEST_WORD_LIMIT "testdata-limit-reached.input"

#ifndef TARGET // must be given by the make file --> see test target
#error missing TARGET define
#endif

// setup & cleanup
static int setup(void) {
	remove_file_if_exists(OUTFILE);
	remove_file_if_exists(ERRFILE);
	return 0; // success
}

static int teardown(void) {
	// Do nothing.
	// Especially: do not remove result files - they are removed in int setup(void) *before* running a test.
	return 0; // success
}

// tests
static void test_save_word(void) {
	//arrange
	char *wordlist[2];
	char word_one[6] = "hello";
	char word_two[6] = "world";
	// act
	saveWord(wordlist, word_one, 0);
	saveWord(wordlist, word_two, 1);
	// assert
	CU_ASSERT_EQUAL(wordlist[0], word_one);
	CU_ASSERT_EQUAL(wordlist[1], word_two);
}

static void test_sort_wordlist(void) {
	// arrange
	char *wordlist[6] = {
		"hello",
		"this",
		"is",
		"testing",
		"our",
		"function"
	};

	char *wordlist_sorted[6] = {
		"function",
		"hello",
		"is",
		"our",
		"testing",
		"this"
	};
	// act
	sort_wordlist(wordlist, 6);
	// assert
	for (size_t i = 0; i < 6; i++) {
		CU_ASSERT_EQUAL(wordlist[i], wordlist_sorted[i]);
	}
}

static void test_complete(void) {
	// arrange
	const char *out_txt[] = {
		"Please enter any words (one by one / max. " XSTR(MAX_WORD_LENGTH) " chars per word / max. " XSTR(WORDLIST_LENGTH) " words). Enter " ABORT_STRING " to stop.\n",
		"\n",
		"\n",
		"Word Nr.1:\n",
		"\n",
		"Word Nr.2:\n",
		"\n",
		"Word Nr.3:\n",
		"\n",
		"Word Nr.4:\n",
		"\n",
		"Word Nr.5:\n",
		"\n",
		"Word Nr.6:\n",
		"\n",
		"Word Nr.7:\n",
		"\n",
		"Word Nr.8:\n",
		"\n",
		"Word Nr.9:\n",
		"\n",
		"\n",
		"Wordlist:\n",
		"---------\n",
		"hello\n",
		"is\n",
		"our\n",
		"programm\n",
		"sorting\n",
		"testing\n",
		"this\n",
		"word\n"
	};
	// act
	int exit_code = system(XSTR(TARGET) " 1>" OUTFILE " 2>" ERRFILE " <" INFILE_TEST_COMPLETE);
	// assert
	CU_ASSERT_EQUAL(exit_code, 0);
	assert_lines(OUTFILE, out_txt, sizeof(out_txt)/sizeof(*out_txt));
}

static void test_length_error(void) {
	// arrange
	const char *out_txt[] = {
		"Please enter any words (one by one / max. " XSTR(MAX_WORD_LENGTH) " chars per word / max. " XSTR(WORDLIST_LENGTH) " words). Enter " ABORT_STRING " to stop.\n",
		"\n",
		"\n",
		"Word Nr.1:\n",
		"Invalid input [LENGTH] --> max. 20 chars\n"
		"\n",
		"Word Nr.1:\n",
		"\n",
		"Word Nr.2:\n",
		"\n",
		"\n",
		"Wordlist:\n",
		"---------\n",
		"test_error_maxlength\n"
	};
	// act
	int exit_code = system(XSTR(TARGET) " 1>" OUTFILE " 2>" ERRFILE " <" INFILE_TEST_ERROR_LENGTH);
	// assert
	CU_ASSERT_EQUAL(exit_code, 0);
	assert_lines(OUTFILE, out_txt, sizeof(out_txt)/sizeof(*out_txt));
}

static void test_empty_error(void) {
	// arrange
	const char *out_txt[] = {
		"Please enter any words (one by one / max. " XSTR(MAX_WORD_LENGTH) " chars per word / max. " XSTR(WORDLIST_LENGTH) " words). Enter " ABORT_STRING " to stop.\n",
		"\n",
		"\n",
		"Word Nr.1:\n",
		"Invalid input [EMPTY] --> Please enter at least 1 character\n"
		"\n",
		"Word Nr.1:\n",
		"\n",
		"Word Nr.2:\n",
		"\n",
		"\n",
		"Wordlist:\n",
		"---------\n",
		"test_empty\n"
	};
	// act
	int exit_code = system(XSTR(TARGET) " 1>" OUTFILE " 2>" ERRFILE " <" INFILE_TEST_ERROR_EMPTY);
	// assert
	CU_ASSERT_EQUAL(exit_code, 0);
	assert_lines(OUTFILE, out_txt, sizeof(out_txt)/sizeof(*out_txt));
}

static void test_whitespace_error(void) {
	// arrange
	const char *out_txt[] = {
		"Please enter any words (one by one / max. " XSTR(MAX_WORD_LENGTH) " chars per word / max. " XSTR(WORDLIST_LENGTH) " words). Enter " ABORT_STRING " to stop.\n",
		"\n",
		"\n",
		"Word Nr.1:\n",
		"Invalid input [WHITESPACE] --> no whitespace allowed\n"
		"\n",
		"Word Nr.1:\n",
		"\n",
		"Word Nr.2:\n",
		"\n",
		"\n",
		"Wordlist:\n",
		"---------\n",
		"test_whitespace\n"
	};
	// act
	int exit_code = system(XSTR(TARGET) " 1>" OUTFILE " 2>" ERRFILE " <" INFILE_TEST_ERROR_WHITESPACE);
	// assert
	CU_ASSERT_EQUAL(exit_code, 0);
	assert_lines(OUTFILE, out_txt, sizeof(out_txt)/sizeof(*out_txt));
}

static void test_word_limit(void) {
	// arrange
	const char *out_txt[] = {
		"Please enter any words (one by one / max. " XSTR(MAX_WORD_LENGTH) " chars per word / max. " XSTR(WORDLIST_LENGTH) " words). Enter " ABORT_STRING " to stop.\n",
		"\n",
		"\n",
		"Word Nr.1:\n",
		"\n",
		"Word Nr.2:\n",
		"\n",
		"Word Nr.3:\n",
		"\n",
		"Word Nr.4:\n",
		"\n",
		"Word Nr.5:\n",
		"\n",
		"Word Nr.6:\n",
		"\n",
		"Word Nr.7:\n",
		"\n",
		"Word Nr.8:\n",
		"\n",
		"Word Nr.9:\n",
		"\n",
		"Word Nr.10:\n",
		"\n",
		"Word Nr.11:\n",
		"\n",
		"Word Nr.12:\n",
		"\n",
		"Word Nr.13:\n",
		"\n",
		"Word Nr.14:\n",
		"\n",
		"Word Nr.15:\n",
		"\n",
		"Word Nr.16:\n",
		"\n",
		"Word Nr.17:\n",
		"\n",
		"Word Nr.18:\n",
		"\n",
		"Word Nr.19:\n",
		"\n",
		"Word Nr.20:\n",
		"\n",
		"Word Nr.21:\n",
		"\n",
		"Word Nr.22:\n",
		"\n",
		"Word Nr.23:\n",
		"\n",
		"Word Nr.24:\n",
		"\n",
		"Word Nr.25:\n",
		"\n",
		"Word Nr.26:\n",
		"\n",
		"Word Nr.27:\n",
		"\n",
		"Word Nr.28:\n",
		"\n",
		"Word Nr.29:\n",
		"\n",
		"Word Nr.30:\n",
		"\n",
		"Word Nr.31:\n",
		"\n",
		"Word Nr.32:\n",
		"\n",
		"Word Nr.33:\n",
		"\n",
		"Word Nr.34:\n",
		"\n",
		"Word Nr.35:\n",
		"\n",
		"Word Nr.36:\n",
		"\n",
		"Word Nr.37:\n",
		"\n",
		"Word Nr.38:\n",
		"\n",
		"Word Nr.39:\n",
		"\n",
		"Word Nr.40:\n",
		"\n",
		"Word Nr.41:\n",
		"\n",
		"Word Nr.42:\n",
		"\n",
		"Word Nr.43:\n",
		"\n",
		"Word Nr.44:\n",
		"\n",
		"Word Nr.45:\n",
		"\n",
		"Word Nr.46:\n",
		"\n",
		"Word Nr.47:\n",
		"\n",
		"Word Nr.48:\n",
		"\n",
		"Word Nr.49:\n",
		"\n",
		"Word Nr.50:\n",
		"\n",
		"Word Nr.51:\n",
		"\n",
		"Word Nr.52:\n",
		"\n",
		"Word Nr.53:\n",
		"\n",
		"Word Nr.54:\n",
		"\n",
		"Word Nr.55:\n",
		"\n",
		"Word Nr.56:\n",
		"\n",
		"Word Nr.57:\n",
		"\n",
		"Word Nr.58:\n",
		"\n",
		"Word Nr.59:\n",
		"\n",
		"Word Nr.60:\n",
		"\n",
		"Word Nr.61:\n",
		"\n",
		"Word Nr.62:\n",
		"\n",
		"Word Nr.63:\n",
		"\n",
		"Word Nr.64:\n",
		"\n",
		"Word Nr.65:\n",
		"\n",
		"Word Nr.66:\n",
		"\n",
		"Word Nr.67:\n",
		"\n",
		"Word Nr.68:\n",
		"\n",
		"Word Nr.69:\n",
		"\n",
		"Word Nr.70:\n",
		"\n",
		"Word Nr.71:\n",
		"\n",
		"Word Nr.72:\n",
		"\n",
		"Word Nr.73:\n",
		"\n",
		"Word Nr.74:\n",
		"\n",
		"Word Nr.75:\n",
		"\n",
		"Word Nr.76:\n",
		"\n",
		"Word Nr.77:\n",
		"\n",
		"Word Nr.78:\n",
		"\n",
		"Word Nr.79:\n",
		"\n",
		"Word Nr.80:\n",
		"\n",
		"Word Nr.81:\n",
		"\n",
		"Word Nr.82:\n",
		"\n",
		"Word Nr.83:\n",
		"\n",
		"Word Nr.84:\n",
		"\n",
		"Word Nr.85:\n",
		"\n",
		"Word Nr.86:\n",
		"\n",
		"Word Nr.87:\n",
		"\n",
		"Word Nr.88:\n",
		"\n",
		"Word Nr.89:\n",
		"\n",
		"Word Nr.90:\n",
		"\n",
		"Word Nr.91:\n",
		"\n",
		"Word Nr.92:\n",
		"\n",
		"Word Nr.93:\n",
		"\n",
		"Word Nr.94:\n",
		"\n",
		"Word Nr.95:\n",
		"\n",
		"Word Nr.96:\n",
		"\n",
		"Word Nr.97:\n",
		"\n",
		"Word Nr.98:\n",
		"\n",
		"Word Nr.99:\n",
		"\n",
		"Word Nr.100:\n",
		"\n",
		"You entered 100 words, limit reached\n",
		"\n",
		"\n",
		"Wordlist:\n",
		"---------\n",
		"and\n",
		"and\n",
		"and\n",
		"and\n",
		"and\n",
		"and\n",
		"and\n",
		"and\n",
		"and\n",
		"and\n",
		"and\n",
		"and\n",
		"and\n",
		"and\n",
		"and\n",
		"and\n",
		"and\n",
		"and\n",
		"and\n",
		"and\n",
		"and\n",
		"and\n",
		"and\n",
		"and\n",
		"and\n",
		"and\n",
		"and\n",
		"and\n",
		"and\n",
		"and\n",
		"and\n",
		"and\n",
		"and\n",
		"and\n",
		"and\n",
		"and\n",
		"and\n",
		"and\n",
		"and\n",
		"and\n",
		"and\n",
		"and\n",
		"and\n",
		"and\n",
		"and\n",
		"and\n",
		"and\n",
		"and\n",
		"and\n",
		"and\n",
		"more\n",
		"more\n",
		"more\n",
		"more\n",
		"more\n",
		"more\n",
		"more\n",
		"more\n",
		"more\n",
		"more\n",
		"more\n",
		"more\n",
		"more\n",
		"more\n",
		"more\n",
		"more\n",
		"more\n",
		"more\n",
		"more\n",
		"more\n",
		"more\n",
		"more\n",
		"more\n",
		"more\n",
		"more\n",
		"more\n",
		"more\n",
		"more\n",
		"more\n",
		"more\n",
		"more\n",
		"more\n",
		"more\n",
		"more\n",
		"more\n",
		"more\n",
		"more\n",
		"more\n",
		"more\n",
		"more\n",
		"more\n",
		"more\n",
		"more\n",
		"more\n",
		"more\n",
		"more\n",
		"more\n",
		"more\n",
		"more\n",
		"more\n"
	};
	// act
	int exit_code = system(XSTR(TARGET) " 1>" OUTFILE " 2>" ERRFILE " <" INFILE_TEST_WORD_LIMIT);
	// assert
	CU_ASSERT_EQUAL(exit_code, 0);
	assert_lines(OUTFILE, out_txt, sizeof(out_txt)/sizeof(*out_txt));
}

/**
 * @brief Registers and runs the tests.
 */
int main(void) {
	// setup, run, teardown
	TestMainBasic(
		"Selbststudium 4 - Pointer-Arrays-Strings", setup, teardown,
		test_save_word,
		test_sort_wordlist,
		test_complete,
		test_length_error,
		test_empty_error,
		test_whitespace_error,
		test_word_limit
	);
}