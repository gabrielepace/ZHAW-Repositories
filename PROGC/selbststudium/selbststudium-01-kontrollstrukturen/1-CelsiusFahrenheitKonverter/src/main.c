/**
* Selbststudium 1 - Kontrollstrukturen
* Aufgabe 1: Konvertierung Celsius ↔ Fahrenheit
*
* @author Gabriele Pace (pacegab1)
*/

#include <stdio.h>
#include <stdlib.h>

#define STARTVALUE -100
#define ENDVALUE 200

/**
* @brief Converts Fahrenheit to Celsius
* @param fahrenheit
* @return Temperature in Celsius
*/
double calculateFahrenheitToCelsius(int fahrenheit) {
    return (5*(fahrenheit-32))/9.0;    
    }

/**
* @brief Calculates Celsius temperatures from -100 Fahrenheit to 200 Fahrenheit
    in 20 degrees steps and prints out F'heit/Celsius table
* @return EXIT_SUCCESS
*/
int main(void) {
    printf("F\'heit    Celsius\n");
    printf("-----------------\n");
    
    //Prints the fahrenheit/celsius table
    for(int fahrenheit = STARTVALUE; fahrenheit <= ENDVALUE; (fahrenheit+=20)){
        printf("%6d %10.2f\n", fahrenheit, calculateFahrenheitToCelsius(fahrenheit)); 
    }
       
    return EXIT_SUCCESS;
    
}

