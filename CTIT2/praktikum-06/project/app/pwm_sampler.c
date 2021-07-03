/* ----------------------------------------------------------------------------
 * --  _____       ______  _____                                              -
 * -- |_   _|     |  ____|/ ____|                                             -
 * --   | |  _ __ | |__  | (___    Institute of Embedded Systems              -
 * --   | | | '_ \|  __|  \___ \   Zurich University of                       -
 * --  _| |_| | | | |____ ____) |  Applied Sciences                           -
 * -- |_____|_| |_|______|_____/   8401 Winterthur, Switzerland               -
 * ----------------------------------------------------------------------------
 * --
 * -- Description:  Implementation of module pwm_sampler.
 * --
 * -- $Id: pwm_sampler.c 2020-03-19 10:08:37Z kocb $
 * ------------------------------------------------------------------------- */

/* standard includes */
#include <stdint.h>
#include <reg_stm32f4xx.h>
#include <reg_ctboard.h>

/* user includes */
#include "pwm_sampler.h"


/* -- Macros
 * ------------------------------------------------------------------------- */

#define GPIOB_SAMPLE_PIN1       (0x0010)
#define GPIOB_SAMPLE_PIN2       (0x0020)
#define GPIOB_SAMPLE_PIN3       (0x0001)
#define GPIOB_SAMPLE_PINMASK    (GPIOB_SAMPLE_PIN1 | GPIOB_SAMPLE_PIN2 | GPIOB_SAMPLE_PIN3)


/* Public function definitions
 * ------------------------------------------------------------------------- */


static uint16_t last_pwmsample = 0;

void pwm_sampler(void)
{
    // read 3 pins of GPIOB, which are at the same time pwm output pins of timer 3
    uint16_t pwmsample = GPIOB->IDR & GPIOB_SAMPLE_PINMASK;
    uint16_t pinchanged = pwmsample ^ last_pwmsample;
    
    if (pinchanged & GPIOB_SAMPLE_PIN1) { 
        // pin 1 has a new value..
        if (pwmsample & GPIOB_SAMPLE_PIN1) {
            // pin is set: turn on all 8 corresponding LEDs
            CT_LED->BYTE.LED7_0 = 0xff;
        } else {
            // pin is cleared: turn off all 8 corresponding LEDs
            CT_LED->BYTE.LED7_0 = 0x00;
        }
    }
        
    if (pinchanged & GPIOB_SAMPLE_PIN2) { 
        if (pwmsample & GPIOB_SAMPLE_PIN2) {
            CT_LED->BYTE.LED15_8 = 0xff;
        } else {
            CT_LED->BYTE.LED15_8 = 0x00;
        }
    }
        
    if (pinchanged & GPIOB_SAMPLE_PIN3) { 
        if (pwmsample & GPIOB_SAMPLE_PIN3) {
            CT_LED->BYTE.LED23_16 = 0xff;
        } else {
            CT_LED->BYTE.LED23_16 = 0x00;
        }
    }
    
    last_pwmsample = pwmsample;         // store current sampling value for next round..
}


