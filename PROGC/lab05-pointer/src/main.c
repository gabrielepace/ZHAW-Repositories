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

/**
 * @brief   generic swap function.
 * @param[in]  a  pointer to 1st memory block.
 * @param[in]  b  pointer to 2nd memory block.
 * @param[in]  bytes number of bytes to swap between the memory blocks.
 */
void swap(void *a, void *b, size_t bytes)
{
	// begin students to add code for task 4.1
	char *left = a;
	char *right = b;
	if(bytes==1){
        char tmp = *left;
        *left = *right;
        *right = tmp;
	} else {
        for(int i=0; i<bytes; i++){
            swap(&left[i], &right[i], 1);
        }
	}
	// end students to add code
}

/**
 * @brief   print some array values.
 */
void print_values(const char name[], int array[], size_t n) {
	(void)printf("%s = ", name);
	for(size_t i = 0; i < n; i++) {
		(void)printf("%c ", i ? ',' : '{');
		(void)printf("%d", array[i]);
	}
	(void)printf(" };\n");
}

/**
 * @brief Main entry point.
 * @returns Returns EXIT_SUCCESS (=0) on success.
 */
int main(void)
{
	int values[5] = { 1, 5, 3, 6, 4 };
	int others[5] = { 0 };
	print_values("values", values, sizeof(values)/sizeof(*values));
	print_values("others", others, sizeof(others)/sizeof(*others));

	swap(values, others, sizeof(values));
	print_values("values", values, sizeof(values)/sizeof(*values));
	print_values("others", others, sizeof(others)/sizeof(*others));

	return EXIT_SUCCESS;
}
