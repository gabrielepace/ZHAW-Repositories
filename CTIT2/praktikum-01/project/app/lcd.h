/* ------------------------------------------------------------------
 * --  _____       ______  _____                                    -
 * -- |_   _|     |  ____|/ ____|                                   -
 * --   | |  _ __ | |__  | (___    Institute of Embedded Systems    -
 * --   | | | '_ \|  __|  \___ \   Zurich University of             -
 * --  _| |_| | | | |____ ____) |  Applied Sciences                 -
 * -- |_____|_| |_|______|_____/   8401 Winterthur, Switzerland     -
 * ------------------------------------------------------------------
 * --
 * -- Description:  Interface of module lcd
 * --               The hardware abstraction layer for the lcd of the
 * --               CT board
 * --
 * -- $Id: lcd.h 3011 2016-02-16 13:26:09Z feur $
 * ------------------------------------------------------------------
 */

/* re-definition guard */
#ifndef _LCD_H
#define _LCD_H

/* standard includes */
#include <stdint.h>

/* function declarations */

/*
 * Writes 'value' to the indicated 'position' on the lcd
 * position on second line: 76 54 32 10
 */
void lcd_write(uint8_t position, uint8_t value);
#endif
