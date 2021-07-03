/**
* Selbststudium 2 - Struct-Enum-Funktionen
* Aufgabe 1: Berechnung des Folgetages
*
* @author Gruppe 2: Gabriele Pace (pacegab1), Omar Shakir (shakioma), Vincent Schmid (schmivin)
*
* @file main.c
*/

#include <stdio.h>
#include <stdlib.h>

//Jan=1 that counter beginns at 1 and not at 0
enum {Jan=1, Feb, Mar, Apr, May, Jun, Jul, Aug, Sep, Oct, Nov, Dec};

typedef struct { 
	int day; 
	int month; 
	int year;
} Date;

int is_leap_year(Date d);
int date_is_valid(Date d);
int is_last_day_of_month(Date d);
Date calculate_next_day(Date d);

/**
 * @brief Main entry point.
 * @returns Returns EXIT_SUCCESS (=0) on success,
 *                  EXIT_FAILURE (=1) if more than one argument is given.
 */
int main(void)
{	
	int day;
	int month;
	int year;
	Date d;
	int valid_date = 0;
	while(!valid_date){
	    // prompts user for input until he types a valid date
		(void)printf("Please enter a valid date (dd MM yyyy):\n");
		(void)scanf("%d%d%d", &day, &month, &year);
		d.day = day;
		d.month = month;
		d.year = year;

		valid_date = date_is_valid(d);
		if(!valid_date){
			(void)printf("Invalid date!\n");
		}
	}

	Date next_day = calculate_next_day(d);
	(void)printf("Next day: %d.%d.%d\n", next_day.day, next_day.month, next_day.year);
	return EXIT_SUCCESS;
}

/**
 * @brief calculates the following day of a given date
 * @param d the date given to calculate with.
 * @returns Returns Date of the following day
 */
Date calculate_next_day(Date d){
	Date next_day = d;
	if(is_last_day_of_month(d)){ 
		// day is the last of the month so increase month
		next_day.day = 1; // set day to the first of the following month
		if(next_day.month == Dec){ 
			// case SYLVESTER 
			next_day.month = Jan;
			next_day.year++; // increase year and party
		} else {
			// case month-change
			next_day.month++;
		}
	} else {
		next_day.day++;
	}
	return next_day;
}


/**
 * @brief checks if the given date is the last day of a month
 * @param d the date given to calculate with.
 * @returns Returns int for true/false
 */
int is_last_day_of_month(Date d){
	switch(d.month){
		case Jan:
		case Mar:
		case May:
		case Jul:
		case Aug:
		case Oct:
		case Dec:
            // check all months with 31 days
            if(d.day == 31){
                return 1;
            } else {
                return 0;
            }
            break; // unnecessary but clean
		case Apr:
		case Jun:
		case Sep:
		case Nov:
            // check all months with 30 days
            if (d.day == 30){
                return 1;
            } else {
                return 0;
            }
            break; // unnecessary but clean
		case Feb:
            // check special cases for february
            if (d.day == 29 || (d.day == 28 && is_leap_year(d))){
                return 1;
            } else {
                return 0;
            }
            break; // unnecessary but clean
	}
	return 0;
}

/**
 * @brief checks if the given date is is valid
 * @param d the date given to calculate with.
 * @returns Returns int for true/false
 */
int date_is_valid(Date d){
    //checks if given numbers are valid (negative, etc)
	if(!(d.year >= 1583) || d.month < 1 || d.month > 12 || d.day < 1){
		return 0;
	} 

	// check cases for the amount of days in the given month
	switch(d.month){
		case Jan:
		case Mar:
		case May:
		case Jul:
		case Aug:
		case Oct:
		case Dec:
		    // check all months with 31 days
            if(d.day > 31){
                return 0;
            } else {
                return 1;
            }
		    break; // unnecessary but clean
		case Apr:
		case Jun:
		case Sep:
		case Nov:
		    // checks all months with 30 days
            if(d.day > 30){
                return 0;
            } else {
                return 1;
            }
            break; // unnecessary but clean
		case Feb:
		    // checks special cases for february
            if (d.day > 29 || (d.day > 28 && is_leap_year(d))){
                return 0;
            } else {
                return 1;
            }
            break; // unnecessary but clean
	}
	return 1;
}

/**
 * @brief checks if the given date is a leap year
 * @param d the date given to calculate with.
 * @returns Returns int for true/false
 */
int is_leap_year(Date d){
	if((d.year % 4 == 0 && d.year % 100 != 100) || d.year % 400 == 0){ //condition for leap year
		return 0;
	} else {
		return 1;
	}
}