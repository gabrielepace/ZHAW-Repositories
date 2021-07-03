/**
* Selbststudium 2 - struct-enum-Funktionen
* Aufgabe 1: Berechnung des Folgetages
*
* @author Gabriele Pace (pacegab1)
*/

#include <stdio.h>
#include <stdlib.h>

typedef enum {Jan=1, Feb, Mar, Apr, May, Jun, Jul, Aug, Sep, Oct, Nov, Dec};
static int monthDays[13] = {0, 31, 28, 31, 30, 31, 30, 31, 31, 30, 31, 30, 31};

typedef struct  {
        int day;
        int month;
        int year;
    } Date;

Date inputDate;
Date followingDay;

/**
 * Verify if a year is a leap year (Schaltjahr) 
 */
int isLeapYear(){
    if((inputDate.year % 4 == 0) && (inputDate.year % 100 != 0)){
        return 1;
    }else if(inputDate.year % 400 == 0){
        return 1;
    }else{
        return 0;
    }
}
/**
 * Verifiy if a year is valid or. starting from 1583 (Gregorian calender start)
 */
int isValidYear(){
    if(inputDate.year >= 1583){
        return 1;
    }else{
        printf("The year entered is not valid. Year should be start from 1583\n");
        return 0;
    }  
}

/**
 * Verify if a month is valid or. within 01(=Jan) and 12(=Dec) 
 */
int isValidMonth(){
    if((inputDate.month >= 1) && (inputDate.month <= 12)){
        return 1;
    }else{
        printf("The month entered is not valid. Valid month are between 01(=Jan) and 12(=Dec)\n");
        return 0;
    }
}

/**
 * Verify if a day is valid for this specific month
 */
int isValidDay(){
    if((1 <= inputDate.day) && (inputDate.day <= monthDays[inputDate.month])){
        return 1;
    }else{
        printf("The day entered is not valid for this month. Check calender and type a new day\n");
        return 0;
    }
}

/**
 * Verify if all inputs are valid
 */
int isValidInput(){
    if(!isValidYear()){
        return 0;
    }

    if(!isLeapYear()){
        monthDays[2] = 29;
    }

    if(!isValidMonth()){
        return 0;
    }

    if(!isValidDay()){
        return 0;
    }else{
        return 1;
    }
}

/**
 * Calculates following day
 */
int calculateFollowingDay(){
    if(inputDate.day >= monthDays[inputDate.day]){
        followingDay.day = 1;
        followingDay.month += 1;
        if(followingDay.month > Dec){
            followingDay.month = 1;
            followingDay.year += 1;
        }
    }else{
        followingDay.day += 1;
    }
}

int main(void){
    printf("Enter a Date in this format: DDMMYYYY\n");
    while(scanf("2%d2%d4%d", &inputDate.day, &inputDate.month, &inputDate.year)){
        printf("Wrong format written. Please check correct format input\n");
        return EXIT_FAILURE;
    }
    if(isValidInput() != 1){
        return EXIT_FAILURE;
    }else{
        followingDay = inputDate;
        calculateFollowingDay();
        printf("The following day is: %d.%d.%d\n", followingDay.day, followingDay.month, followingDay.year);
        return EXIT_SUCCESS;
    }
}