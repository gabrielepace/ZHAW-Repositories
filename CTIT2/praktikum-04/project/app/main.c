/* ----------------------------------------------------------------------------
 * --  _____       ______  _____                                              -
 * -- |_   _|     |  ____|/ ____|                                             -
 * --   | |  _ __ | |__  | (___    Institute of Embedded Systems              -
 * --   | | | '_ \|  __|  \___ \   Zurich University of                       -
 * --  _| |_| | | | |____ ____) |  Applied Sciences                           -
 * -- |_____|_| |_|______|_____/   8401 Winterthur, Switzerland               -
 * ----------------------------------------------------------------------------
 * --
 * -- Project     : CT2 Lab4 GPIO
 * -- Description : Configure and use GPIO port B with a HAL (hal_gpio).
 * --               Init pins of GPIO port B as output an input in different
 * --               modes and use them for the addition GPIO board.
 * --
 * -- $Id: main.c 3659 2016-10-03 09:37:42Z kesr $
 * ------------------------------------------------------------------------- */

#include <stdint.h>
#include <reg_stm32f4xx.h>

#include "hal_ct_buttons.h"
#include "reg_ctboard.h"

/* user macros */

#define MASK_IN_PB9_PB5  0x03E0
#define MASK_OUT_PB4_PB0 0x001F
#define DIPSW_31         0x80

/* ----------------------------------------------------------------------------
 * Main
 * ------------------------------------------------------------------------- */

int main(void)
{
    uint16_t data_gpio_in;              // use to read input values from gpio
    uint16_t data_gpio_out;             // use to read output values from gpio
    uint8_t data_dip_switch;            // use to read values from dip switches

    GPIOB_ENABLE();                     // peripheral clk enable
  
    /* Reset GPIOB specific values */
    GPIOB->MODER = 0x00000280;           // mode register
    GPIOB->OSPEEDR = 0x000000c0;         // output speed register
    GPIOB->PUPDR = 0x00000100;           // pull resistor register
    GPIOB->OTYPER = 0x00000000;          // type register (pp / f. gate)
    GPIOB->AFRL = 0x00000000;
    GPIOB->AFRH = 0x00000000;
    GPIOB->ODR = 0x00000000;             // output data register
    

    /* configure GPIO pins
     * clear register bits: GPIOx->xxxx &= ~(clear_mask << bit_pos);
     * set register bits:   GPIOx->xxxx |=  (set_value << bit_pos);
     * On GPIOA and GPIOB only pins 11 down to 0 are available to the user. 
     * Pins 15 down to 12 are used for system functions of the discovery board, 
     * e.g. connection of the debugger.
     * These pins must not be reconfigured. Otherwise the debugger cannot be used any more!!!
     */
    /// STUDENTS: To be programmed




    /// END: To be programmed

    while (1) {
        /* if (S31 == 0), show additional lab, else regular */
        if (CT_DIPSW->BYTE.S31_24 & DIPSW_31) {
            /* implement tasks 5.1 to 5.4 below */
            /// STUDENTS: To be programmed




            /// END: To be programmed
          
        } else {
            /* implement tasks 5.5 below */
            /// STUDENTS: To be programmed




            /// END: To be programmed

            /* wait for button to be pressed */
            while (!(hal_ct_button_is_pressed(HAL_CT_BUTTON_T0))) {
            }
        }
    }
}

