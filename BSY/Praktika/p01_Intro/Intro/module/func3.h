#ifndef MY_FUNC3_DEF    /* verhindert Mehrfachdeklarationen */
#define MY_FUNC3_DEF   

/*******************************************************************************
* Beispiel:   Modulare Programmierung in C (und C++), 
* File:       func3.h
* Fach:       BSy
* Autoren:    Gabriele Pace
* Version:    v.fs20
*******************************************************************************

    Diese Header-Datei enthaelt die Deklaration(nen) der Funktion(nen), die
    das Modul func3.c exportiert: die Funktion "volume".
    Sie muss mit der "include-Anweisung" in folgende Module implementiert
    werden:
        a) "func3.c": Funktionsimplementierung
        b) "main.c" : Verwendung der Funktion
    Damit wird sichergestellt, dass die Funktionssignatur konsistent ist und
    die Typenüberprüfung der Paramter und des Rückgabewertes bei der Compilation    
    garantiert ist.

*******************************************************************************/

#include "mydefs.h"

myfloat_t volumen(myfloat_t);

/******************************************************************************/

#endif
