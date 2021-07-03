/* ------------------------------------------------------------------
 * --  _____       ______  _____                                    -
 * -- |_   _|     |  ____|/ ____|                                   -
 * --   | |  _ __ | |__  | (___    Institute of Embedded Systems    -
 * --   | | | '_ \|  __|  \___ \   Zurich University of             -
 * --  _| |_| | | | |____ ____) |  Applied Sciences                 -
 * -- |_____|_| |_|______|_____/   8401 Winterthur, Switzerland     -
 * ------------------------------------------------------------------
 * --
 * -- Application for an electronic dice
 * --
 * -- $Id: main.c 4664 2019-02-08 08:24:50Z ruan $
 * ------------------------------------------------------------------
 */

/* standard includes */
#include <stdint.h>
#include <reg_ctboard.h>
#include <hal_ct_seg7.h>

/* user includes */
#include "statistics.h"
#include "dice_counter.h"
#include "lcd.h"

/* function definitions */

/*
 * Pushing button T0 displays a pseudo random dice value.
 * Throws are recorded and statistics are continuously displayed.
 */

int main(void)
{
    uint8_t dice_number;
    uint8_t i;
    uint8_t number_of_throws;
    uint8_t previous_keys_value = 0x0;
    uint8_t key_pressed;

    while (1) {
        // roll the dice ...
        uint8_t buttons = CT_BUTTON;
        key_pressed = ~previous_keys_value & buttons;
        previous_keys_value = buttons;
        if (key_pressed & 0x01) {
            dice_number = dice_counter_read();
            hal_ct_seg7_bin_write(dice_number);
            stat_add_throw(dice_number);
        }

        dice_counter_increment();

        // display statistics: total and per number
        for (i = 0; i <= NR_OF_DICE_VALUES; i++) {
            number_of_throws = stat_read(i);
            if (number_of_throws != ERROR_VALUE) {
                lcd_write(NR_OF_DICE_VALUES - i, number_of_throws);
            }
        }
    }
}
