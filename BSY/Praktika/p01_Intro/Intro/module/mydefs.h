#ifndef MY_MYDEFS_DEF    /* verhindert Mehrfachdeklarationen */
#define MY_MYDEFS_DEF   

/*******************************************************************************
* Beispiel:   Modulare Programmierung in C (und C++), 
* File:       func1.h
* Fach:       BS 
* Autoren:    J.Zeman, M. Thaler
* Version:    v.fs20 
********************************************************************************
   Diese Datei enthaelt die Deklarationen eigener Datentypen und Konstanten,
   von in mehreren (allen) Programmmodulen benoetigt werden. Die zentrale
   Verwaltung dieser Programmkomponeneten vereinfacht Aenderungen am Programm.

   Diese Datei wird von den Quellcode-Modulen, falls benötigt, mit der 
   "include-Anweisung" eingebunden:

        main.c, func1.c, func2.c, func3.c, sicherheitshalber auch in func1.h, func2.h, func3.h

*******************************************************************************/
/* gemeinsame Typen, Konstanten, Macros und globale Variablen-Deklarationen   */
#include <math.h>
#define my_pi M_PI

typedef double myfloat_t;

#endif
