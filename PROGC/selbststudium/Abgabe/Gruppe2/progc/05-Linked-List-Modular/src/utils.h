/**
* Selbststudium 5 - Linked-List-Modular
* Aufgabe 1: Personenverwaltung
*
* @author Gruppe 2: Gabriele Pace (pacegab1), Omar Shakir (shakioma), Vincent Schmid (schmivin)
*
* @file utils.h
*/

#ifndef UTILS_H
#define UTILS_H
/// @brief The max. allowed input length for names (firstname / name)
#define MAX_NAME_LENGTH 20
/// @brief The size of the input buffer used to save input in
#define BUFFER_SIZE 255
void clear_input_buffer(void);
char get_char_input(void);
char* get_string_input(void);
int get_unsigned_int_input(void);
void print_instructions(void);
void print_welcome_message(void);
#endif