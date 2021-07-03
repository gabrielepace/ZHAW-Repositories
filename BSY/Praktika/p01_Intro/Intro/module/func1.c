/*******************************************************************************
* Beispiel:   Modulare Programmierung in C (und C++), 
* File:       func1.c
* Fach:       BSy 
* Autoren:    J.Zeman, M. Thaler
* Version:    v.fs20
*******************************************************************************/

#include "mydefs.h"
#include "func1.h"

myfloat_t flaeche(myfloat_t radius) {
   return (my_pi * radius * radius);
}