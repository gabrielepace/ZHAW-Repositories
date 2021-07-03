/**
* Selbststudium 5 - Linked-List-Modular
* Aufgabe 1: Personenverwaltung
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
#include "list.h"
#include "person.h"
#include "utils.h"

/// @brief The name of the STDOUT text file.
#define OUTFILE "stdout.txt"
/// @brief The name of the STDERR text file.
#define ERRFILE "stderr.txt"

/// @brief The input file for insert testing
#define INFILE_TEST_INSERT "testdata-insert.input"
/// @brief The input file for sort testing
#define INFILE_TEST_SORT "testdata-sort.input"
/// @brief The input file for remove testing
#define INFILE_TEST_REMOVE "testdata-remove.input"
/// @brief The input file for remove -> no result testing
#define INFILE_TEST_REMOVE_NO_RESULT "testdata-remove-no-result.input"
/// @brief The input file for clear testing
#define INFILE_TEST_CLEAR "testdata-clear.input"
/// @brief The input file for invalid input testing
#define INFILE_TEST_INVALID_INPUT "testdata-invalid-inputs.input"

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
static void test_insert(void) {
	// arrange
	const char *out_txt[] = {
		"\n//////////////////////////////////////////\n"
		"//\t\t\t\t\t//\n"
		"//\tPeople Management System\t//\n"
		"//\t\t\t\t\t//\n"
		"//////////////////////////////////////////\n"
		"\n\nWhat do you want to do?:\n\n"
		"(I) -> Insert person in list\n"
		"(R) -> Remove person from list\n"
		"(S) -> Show entire list of persons\n"
		"(C) -> Clear person list\n"
		"(E) -> End the programm\n\n"
		"----------------\n"
		"\nPerson Data\n"
		"----------------\n"
		"\nName: "
		"\nFirstname: "
		"\nAge: "
		"\n\nWhat do you want to do?:\n\n"
		"(I) -> Insert person in list\n"
		"(R) -> Remove person from list\n"
		"(S) -> Show entire list of persons\n"
		"(C) -> Clear person list\n"
		"(E) -> End the programm\n\n"
		"----------------\n"
		"\nPerson Data\n"
		"----------------\n"
		"\nName: "
		"\nFirstname: "
		"\nAge: "
		"\n\nWhat do you want to do?:\n\n"
		"(I) -> Insert person in list\n"
		"(R) -> Remove person from list\n"
		"(S) -> Show entire list of persons\n"
		"(C) -> Clear person list\n"
		"(E) -> End the programm\n\n"
		"-------------------------\n"
		"\tPerson List:\n"
		"-------------------------\n"
		"Pitt Brad, 54\n"
		"Willis Bruce, 63\n"
		"\n\nWhat do you want to do?:\n\n"
		"(I) -> Insert person in list\n"
		"(R) -> Remove person from list\n"
		"(S) -> Show entire list of persons\n"
		"(C) -> Clear person list\n"
		"(E) -> End the programm\n\n"
		"\nClosing application...\n"
	};
	// act
	int exit_code = system(XSTR(TARGET) " 1>" OUTFILE " 2>" ERRFILE " <" INFILE_TEST_INSERT);
	// assert
	CU_ASSERT_EQUAL(exit_code, 0);
	assert_lines(OUTFILE, out_txt, sizeof(out_txt)/sizeof(*out_txt));
}

static void test_sort(void) {
	// arrange
	const char *out_txt[] = {
		"\n//////////////////////////////////////////\n"
		"//\t\t\t\t\t//\n"
		"//\tPeople Management System\t//\n"
		"//\t\t\t\t\t//\n"
		"//////////////////////////////////////////\n"
		"\n\nWhat do you want to do?:\n\n"
		"(I) -> Insert person in list\n"
		"(R) -> Remove person from list\n"
		"(S) -> Show entire list of persons\n"
		"(C) -> Clear person list\n"
		"(E) -> End the programm\n\n"
		"----------------\n"
		"\nPerson Data\n"
		"----------------\n"
		"\nName: "
		"\nFirstname: "
		"\nAge: "
		"\n\nWhat do you want to do?:\n\n"
		"(I) -> Insert person in list\n"
		"(R) -> Remove person from list\n"
		"(S) -> Show entire list of persons\n"
		"(C) -> Clear person list\n"
		"(E) -> End the programm\n\n"
		"----------------\n"
		"\nPerson Data\n"
		"----------------\n"
		"\nName: "
		"\nFirstname: "
		"\nAge: "
		"\n\nWhat do you want to do?:\n\n"
		"(I) -> Insert person in list\n"
		"(R) -> Remove person from list\n"
		"(S) -> Show entire list of persons\n"
		"(C) -> Clear person list\n"
		"(E) -> End the programm\n\n"
		"----------------\n"
		"\nPerson Data\n"
		"----------------\n"
		"\nName: "
		"\nFirstname: "
		"\nAge: "
		"\n\nWhat do you want to do?:\n\n"
		"(I) -> Insert person in list\n"
		"(R) -> Remove person from list\n"
		"(S) -> Show entire list of persons\n"
		"(C) -> Clear person list\n"
		"(E) -> End the programm\n\n"
		"-------------------------\n"
		"\tPerson List:\n"
		"-------------------------\n"
		"Willis Brad, 53\n"
		"Willis Brad, 54\n"
		"Willis Bruce, 63\n"
		"\n\nWhat do you want to do?:\n\n"
		"(I) -> Insert person in list\n"
		"(R) -> Remove person from list\n"
		"(S) -> Show entire list of persons\n"
		"(C) -> Clear person list\n"
		"(E) -> End the programm\n\n"
		"\nClosing application...\n"
	};
	// act
	int exit_code = system(XSTR(TARGET) " 1>" OUTFILE " 2>" ERRFILE " <" INFILE_TEST_SORT);
	// assert
	CU_ASSERT_EQUAL(exit_code, 0);
	assert_lines(OUTFILE, out_txt, sizeof(out_txt)/sizeof(*out_txt));
}

static void test_remove(void) {
	// arrange
	const char *out_txt[] = {
		"\n//////////////////////////////////////////\n"
		"//\t\t\t\t\t//\n"
		"//\tPeople Management System\t//\n"
		"//\t\t\t\t\t//\n"
		"//////////////////////////////////////////\n"
		"\n\nWhat do you want to do?:\n\n"
		"(I) -> Insert person in list\n"
		"(R) -> Remove person from list\n"
		"(S) -> Show entire list of persons\n"
		"(C) -> Clear person list\n"
		"(E) -> End the programm\n\n"
		"----------------\n"
		"\nPerson Data\n"
		"----------------\n"
		"\nName: "
		"\nFirstname: "
		"\nAge: "
		"\n\nWhat do you want to do?:\n\n"
		"(I) -> Insert person in list\n"
		"(R) -> Remove person from list\n"
		"(S) -> Show entire list of persons\n"
		"(C) -> Clear person list\n"
		"(E) -> End the programm\n\n"
		"----------------\n"
		"\nPerson Data\n"
		"----------------\n"
		"\nName: "
		"\nFirstname: "
		"\nAge: "
		"\n\nWhat do you want to do?:\n\n"
		"(I) -> Insert person in list\n"
		"(R) -> Remove person from list\n"
		"(S) -> Show entire list of persons\n"
		"(C) -> Clear person list\n"
		"(E) -> End the programm\n\n"
		"----------------\n"
		"\nPerson Data\n"
		"----------------\n"
		"\nName: "
		"\nFirstname: "
		"\nAge: "
		"\nPerson successfully removed\n"
		"\n\nWhat do you want to do?:\n\n"
		"(I) -> Insert person in list\n"
		"(R) -> Remove person from list\n"
		"(S) -> Show entire list of persons\n"
		"(C) -> Clear person list\n"
		"(E) -> End the programm\n\n"
		"-------------------------\n"
		"\tPerson List:\n"
		"-------------------------\n"
		"Willis Bruce, 63\n"
		"\n\nWhat do you want to do?:\n\n"
		"(I) -> Insert person in list\n"
		"(R) -> Remove person from list\n"
		"(S) -> Show entire list of persons\n"
		"(C) -> Clear person list\n"
		"(E) -> End the programm\n\n"
		"\nClosing application...\n"
	};
	// act
	int exit_code = system(XSTR(TARGET) " 1>" OUTFILE " 2>" ERRFILE " <" INFILE_TEST_REMOVE);
	// assert
	CU_ASSERT_EQUAL(exit_code, 0);
	assert_lines(OUTFILE, out_txt, sizeof(out_txt)/sizeof(*out_txt));
}

static void test_remove_no_result(void) {
	// arrange
	const char *out_txt[] = {
		"\n//////////////////////////////////////////\n"
		"//\t\t\t\t\t//\n"
		"//\tPeople Management System\t//\n"
		"//\t\t\t\t\t//\n"
		"//////////////////////////////////////////\n"
		"\n\nWhat do you want to do?:\n\n"
		"(I) -> Insert person in list\n"
		"(R) -> Remove person from list\n"
		"(S) -> Show entire list of persons\n"
		"(C) -> Clear person list\n"
		"(E) -> End the programm\n\n"
		"----------------\n"
		"\nPerson Data\n"
		"----------------\n"
		"\nName: "
		"\nFirstname: "
		"\nAge: "
		"\n\nWhat do you want to do?:\n\n"
		"(I) -> Insert person in list\n"
		"(R) -> Remove person from list\n"
		"(S) -> Show entire list of persons\n"
		"(C) -> Clear person list\n"
		"(E) -> End the programm\n\n"
		"----------------\n"
		"\nPerson Data\n"
		"----------------\n"
		"\nName: "
		"\nFirstname: "
		"\nAge: "
		"\n\nWhat do you want to do?:\n\n"
		"(I) -> Insert person in list\n"
		"(R) -> Remove person from list\n"
		"(S) -> Show entire list of persons\n"
		"(C) -> Clear person list\n"
		"(E) -> End the programm\n\n"
		"----------------\n"
		"\nPerson Data\n"
		"----------------\n"
		"\nName: "
		"\nFirstname: "
		"\nAge: "
		"\nNo matching person found\n"
		"\n\nWhat do you want to do?:\n\n"
		"(I) -> Insert person in list\n"
		"(R) -> Remove person from list\n"
		"(S) -> Show entire list of persons\n"
		"(C) -> Clear person list\n"
		"(E) -> End the programm\n\n"
		"-------------------------\n"
		"\tPerson List:\n"
		"-------------------------\n"
		"Pitt Brad, 54\n"
		"Willis Bruce, 63\n"
		"\n\nWhat do you want to do?:\n\n"
		"(I) -> Insert person in list\n"
		"(R) -> Remove person from list\n"
		"(S) -> Show entire list of persons\n"
		"(C) -> Clear person list\n"
		"(E) -> End the programm\n\n"
		"\nClosing application...\n"
	};
	// act
	int exit_code = system(XSTR(TARGET) " 1>" OUTFILE " 2>" ERRFILE " <" INFILE_TEST_REMOVE_NO_RESULT);
	// assert
	CU_ASSERT_EQUAL(exit_code, 0);
	assert_lines(OUTFILE, out_txt, sizeof(out_txt)/sizeof(*out_txt));
}

static void test_clear(void) {
	// arrange
	const char *out_txt[] = {
		"\n//////////////////////////////////////////\n"
		"//\t\t\t\t\t//\n"
		"//\tPeople Management System\t//\n"
		"//\t\t\t\t\t//\n"
		"//////////////////////////////////////////\n"
		"\n\nWhat do you want to do?:\n\n"
		"(I) -> Insert person in list\n"
		"(R) -> Remove person from list\n"
		"(S) -> Show entire list of persons\n"
		"(C) -> Clear person list\n"
		"(E) -> End the programm\n\n"
		"----------------\n"
		"\nPerson Data\n"
		"----------------\n"
		"\nName: "
		"\nFirstname: "
		"\nAge: "
		"\n\nWhat do you want to do?:\n\n"
		"(I) -> Insert person in list\n"
		"(R) -> Remove person from list\n"
		"(S) -> Show entire list of persons\n"
		"(C) -> Clear person list\n"
		"(E) -> End the programm\n\n"
		"----------------\n"
		"\nPerson Data\n"
		"----------------\n"
		"\nName: "
		"\nFirstname: "
		"\nAge: "
		"\n\nWhat do you want to do?:\n\n"
		"(I) -> Insert person in list\n"
		"(R) -> Remove person from list\n"
		"(S) -> Show entire list of persons\n"
		"(C) -> Clear person list\n"
		"(E) -> End the programm\n\n"
		"\nList successfully cleared.\n"
		"\n\nWhat do you want to do?:\n\n"
		"(I) -> Insert person in list\n"
		"(R) -> Remove person from list\n"
		"(S) -> Show entire list of persons\n"
		"(C) -> Clear person list\n"
		"(E) -> End the programm\n\n"
		"-------------------------\n"
		"\tPerson List:\n"
		"-------------------------\n"
		"\n\nWhat do you want to do?:\n\n"
		"(I) -> Insert person in list\n"
		"(R) -> Remove person from list\n"
		"(S) -> Show entire list of persons\n"
		"(C) -> Clear person list\n"
		"(E) -> End the programm\n\n"
		"\nClosing application...\n"
	};
	// act
	int exit_code = system(XSTR(TARGET) " 1>" OUTFILE " 2>" ERRFILE " <" INFILE_TEST_CLEAR);
	// assert
	CU_ASSERT_EQUAL(exit_code, 0);
	assert_lines(OUTFILE, out_txt, sizeof(out_txt)/sizeof(*out_txt));
}

static void test_invalid_inputs(void) {
	// arrange
	const char *out_txt[] = {
		"\n//////////////////////////////////////////\n"
		"//\t\t\t\t\t//\n"
		"//\tPeople Management System\t//\n"
		"//\t\t\t\t\t//\n"
		"//////////////////////////////////////////\n"
		"\n\nWhat do you want to do?:\n\n"
		"(I) -> Insert person in list\n"
		"(R) -> Remove person from list\n"
		"(S) -> Show entire list of persons\n"
		"(C) -> Clear person list\n"
		"(E) -> End the programm\n\n"
		"\nInvalid input, please choose one of the given options."
		"\n\nWhat do you want to do?:\n\n"
		"(I) -> Insert person in list\n"
		"(R) -> Remove person from list\n"
		"(S) -> Show entire list of persons\n"
		"(C) -> Clear person list\n"
		"(E) -> End the programm\n\n"
		"\nInvalid input, please choose one of the given options."
		"\n\nWhat do you want to do?:\n\n"
		"(I) -> Insert person in list\n"
		"(R) -> Remove person from list\n"
		"(S) -> Show entire list of persons\n"
		"(C) -> Clear person list\n"
		"(E) -> End the programm\n\n"
		"----------------\n"
		"\nPerson Data\n"
		"----------------\n"
		"\nName: "
		"No input, please try again:\n"
		"\nFirstname: "
		"\nAge: "
		"Must be a positive number. Please try again:\n"
		"Must be a positive number. Please try again:\n"
		"Something went wrong, please try again:\n"
		"\n\nWhat do you want to do?:\n\n"
		"(I) -> Insert person in list\n"
		"(R) -> Remove person from list\n"
		"(S) -> Show entire list of persons\n"
		"(C) -> Clear person list\n"
		"(E) -> End the programm\n\n"
		"-------------------------\n"
		"\tPerson List:\n"
		"-------------------------\n"
		"Willis Bruce, 63\n"
		"\n\nWhat do you want to do?:\n\n"
		"(I) -> Insert person in list\n"
		"(R) -> Remove person from list\n"
		"(S) -> Show entire list of persons\n"
		"(C) -> Clear person list\n"
		"(E) -> End the programm\n\n"
		"\nClosing application...\n"
	};
	// act
	int exit_code = system(XSTR(TARGET) " 1>" OUTFILE " 2>" ERRFILE " <" INFILE_TEST_INVALID_INPUT);
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
		"Selbststudium 5 - Linked-List-Modular", setup, teardown,
		test_insert,
		test_sort,
		test_remove,
		test_remove_no_result,
		test_clear,
		test_invalid_inputs
	);
}