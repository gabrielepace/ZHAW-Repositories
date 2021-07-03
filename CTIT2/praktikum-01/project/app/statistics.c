/* ------------------------------------------------------------------
 * --  _____       ______  _____                                    -
 * -- |_   _|     |  ____|/ ____|                                   -
 * --   | |  _ __ | |__  | (___    Institute of Embedded Systems    -
 * --   | | | '_ \|  __|  \___ \   Zurich University of             -
 * --  _| |_| | | | |____ ____) |  Applied Sciences                 -
 * -- |_____|_| |_|______|_____/   8401 Winterthur, Switzerland     -
 * ------------------------------------------------------------------
 * --
 * -- Description:  Implementation of module statistics
 * --               Collect and provide statistical data on throws
 * --               of the dice
 * --
 * -- $Id: statistics.c 2977 2016-02-15 16:05:50Z ruan $
 * ------------------------------------------------------------------
 */

/* user includes */
#include "statistics.h"

/* variables visible within the whole module*/

// number of throws for each digit
static uint8_t nr_of_throws[NR_OF_DICE_VALUES];
static uint8_t total_nr_of_throws = 0;

/* function definitions */
void stat_add_throw(uint8_t throw_value) {
	if(throw_value > 0 && throw_value <= 6) {
		nr_of_throws[throw_value-1] = nr_of_throws[throw_value-1] + 1;
		total_nr_of_throws = total_nr_of_throws + 1;
	}	
}

uint8_t stat_read(uint8_t dice_number) {
	if(dice_number == 0) return total_nr_of_throws;
	if(dice_number > NR_OF_DICE_VALUES) return ERROR_VALUE;
	
	return nr_of_throws[dice_number-1];
}
