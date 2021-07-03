/*******************************************************************************
* Beispiel:   Modulare Programmierung in C (und C++), 
* File:       main.c
* Fach:       BS 
* Autoren:    J.Zeman, M. Thaler
* Version:    v.fs20
*******************************************************************************/

#include <stdio.h>
#include <stdlib.h>
#include "mydefs.h"   /* Headerdatei mit eigenen Typen und Konstante */
#include "func1.h"    /* Headerdatei fuer Modul func1.c   */
#include "func2.h"    /* Headerdatei fuer Modul func2.c   */


/*----------------------------------------------------------------------------*/
/* Funktionsprototypen: (lokale Funktionen in diesem Modul)                   */

void eingabe(myfloat_t *x);

/*----------------------------------------------------------------------------*/
/* Hauptprogramm                                                              */

int main(void) {
    myfloat_t R, F, U;

    eingabe(&R);
    printf("\n\n Kreisberechnung");
    printf(" (berechnet mit pi = %lf)\n", my_pi);  /* aus mydefs.h */
    F = flaeche(R);
    U = umfang(R);
    printf("\n Radius =\t%lf \n Flaeche =\t%lf \n Umfang =\t%lf\n", R , F, U);
    getchar(); getchar();                       /* dummy input -> warte hier */
    exit(0);
}

/*----------------------------------------------------------------------------*/
/* lokale Funktion                                                            */

void eingabe(myfloat_t *x) {
    printf("\n Kreisberechnung: Radius = ? ");
    scanf("%lf", x);
}

/*----------------------------------------------------------------------------*/
