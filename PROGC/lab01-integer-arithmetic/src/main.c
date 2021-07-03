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
 * @brief Main entry point.
 * @param[in] argc  The size of the argv array.
 * @param[in] argv  The command line arguments
 *                  with argv[0] being the command call
 *                  argv[1] the 1st argument, ...
 *                  argv[argc-1] the last argument.
 * @returns Returns the rest of the calculation or
 *                  255 on failure
 */
int main(int argc, char* argv[])
{
	int rappen = 0;
	// begin students to add code for task 4.2

	/* Überprüft, ob eine Eingabe wurde gemacht */
	if(argc == 1){
		(void) printf("Eingabe fehlt!\n");
		exit(255);
	}
	int res = sscanf(argv[1], "%d", &rappen);
	
	/* Datentyp Prüfung bzw. ungültige Eingabe */
	if(res != 1){
		(void) printf("Ungueltige Eingabe! Bitte nur ein Zahl eingeben.\n");
		exit(255);
	}
	/* Datentyp Prüfung bzw. negative Werten */
	if(rappen <= 0){
		(void) printf("Negative Zahlen nicht moeglich! Eingabe muss groesser als 0 sein.\n");
		exit(255);
	}
	
	(void) printf("CHF %.2f:\n", rappen/100.0);
	int anzahl_fuenf_franken = 0;
	int anzahl_zwei_franken = 0;
	int anzahl_ein_franken = 0;
	int anzahl_fuenfzig_rappen = 0;
	int anzahl_zwanzig_rappen = 0;
	int anzahl_zehn_rappen = 0;
	int anzahl_fuenf_rappen = 0;
	
	anzahl_fuenf_franken = rappen/500;
	rappen = rappen-(anzahl_fuenf_franken*500.0);
	(void) printf("- %d x 5.00\n", anzahl_fuenf_franken);
	
	anzahl_zwei_franken = rappen/200;
	rappen = rappen-(anzahl_zwei_franken*200.0);
	(void) printf("- %d x 2.00\n", anzahl_zwei_franken);
	
	anzahl_ein_franken = rappen/100;
	rappen = rappen-(anzahl_ein_franken*100.0);
	(void) printf("- %d x 1.00\n", anzahl_ein_franken);
	
	anzahl_fuenfzig_rappen = rappen/50;
	rappen = rappen-(anzahl_fuenfzig_rappen*50.0);
	(void) printf("- %d x 0.50\n", anzahl_fuenfzig_rappen);
	
	anzahl_zwanzig_rappen = rappen/20;
	rappen = rappen-(anzahl_zwanzig_rappen*20.0);
	(void) printf("- %d x 0.20\n", anzahl_zwanzig_rappen);
	
	anzahl_zehn_rappen = rappen/10;
	rappen = rappen-(anzahl_zehn_rappen*10.0);
	(void) printf("- %d x 0.10\n", anzahl_zehn_rappen);
	
	anzahl_fuenf_rappen = rappen/5;
	rappen = rappen-(anzahl_fuenf_rappen*5.0);
	(void) printf("- %d x 0.05\n", anzahl_fuenf_rappen);
	
	if(rappen == 0){
		(void) printf("Kein Rest\n");
	}else{
		(void) printf("Rest = %.2f\n", rappen/100.0);
	}
	// end students to add code
	return rappen; // rest = 0 = success
}
