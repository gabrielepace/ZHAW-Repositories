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

#define ASCII_A 'A' ///< ASCII code of letter A
#define ASCII_1 '1' ///< ASCII code of digit 1

static void print_board(char board[8][8][3])
{
	// begin students to add code for task 4.1

	// print board (each field with leading space, including first field of each row).
	for(int i=0; i<8; i++){
        for(int j=0; j<8;j++){
                (void)printf(" ");
            for(int k=0; k<3;k++){
                printf("%c", board[i][j][k]);
            }
        }
        (void)printf("\n");
	}
	// end students to add code
}

/**
 * @brief Main entry point.
 * @returns Returns EXIT_SUCCESS (=0) on success.
 */
int main(void)
{
	// begin students to add code for task 4.1

	// define board variable (8x8 fields, each field has 3 chars)
	char board [8][8][3];
	// initialize each field according to task assignment description
	char character = 'A';
	char number = '1';
	// call print_board(board);
	for(int i=0; i<8; i++){
        for(int j=0; j<8; j++){
            board[i][j][0] = character + j;
            board[i][j][1] = number + i;
            board[i][j][2] = '\0';
        }
	}
	print_board(board);
	// end students to add code

	return EXIT_SUCCESS;
}
