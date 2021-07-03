/* ------------------------------------------------------------------
 * --  _____       ______  _____                                    -
 * -- |_   _|     |  ____|/ ____|                                   -
 * --   | |  _ __ | |__  | (___    Institute of Embedded Systems    -
 * --   | | | '_ \|  __|  \___ \   Zurich University of             -
 * --  _| |_| | | | |____ ____) |  Applied Sciences                 -
 * -- |_____|_| |_|______|_____/   8401 Winterthur, Switzerland     -
 * ------------------------------------------------------------------
 * --
 * -- Description:  Implementation of module lcd
 * --               The hardware abstraction layer for the lcd of the
 * --               CT board
 * --
 * -- $Id: lcd.c 4195 2017-08-16 13:08:37Z kesr $
 * ------------------------------------------------------------------
 */

/* standard includes */
#include "hal_ct_lcd.h"

/* user includes */
#include "lcd.h"

/* macros visible only inside of module */
#define LCD_ASCII_IF_REG      (*((volatile uint16_t *)(0x60000310)))
#define LCD_BINARY_IF_BASE    0x60000320
#define LCD_BUSY_BIT_MASK     0x8000
#define POSITION_PER_LCD_LINE 8
#define LCD_INVERT_POSITION   6

/* function definitions */

void lcd_convert_to_ascii(uint8_t *value)
{
    uint8_t ascii = *value;
    ascii = ascii % 16;
    if (ascii < 10) {
        ascii = (ascii + '0');
    }else{
        ascii = (ascii - 10 + 'A');
    }
    *value = ascii;
}

/*
 * see header file
 */
void lcd_write(uint8_t position, uint8_t value)
{
    char *c;
    lcd_convert_to_ascii(&value);
    c = (char *)&value;

    hal_ct_lcd_write(LCD_INVERT_POSITION - position, c);
}
