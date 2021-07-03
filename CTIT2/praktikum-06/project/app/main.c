/* ----------------------------------------------------------------------------
 * --  _____       ______  _____                                              -
 * -- |_   _|     |  ____|/ ____|                                             -
 * --   | |  _ __ | |__  | (___    Institute of Embedded Systems              -
 * --   | | | '_ \|  __|  \___ \   Zuercher Hochschule Winterthur             -
 * --  _| |_| | | | |____ ____) |  (University of Applied Sciences)           -
 * -- |_____|_| |_|______|_____/   8401 Winterthur, Switzerland               -
 * ----------------------------------------------------------------------------
 * --
 * -- Project     : CT2 lab - Timer PWM
 * -- Description : Main program and interrupt service routine
 * --
 * --               Task 1: - Setup timer TIM4 to 1s.
 * --                       - Setup interrupt to toggle LED
 * --               Task 2: - Setup timer TIM3 to PWM mode
 * --                       - Read DIP switches to set duty cycles of channels
 * --               Task 3: - Use interrupt of TIM4 to create a transition
 * --                         from one colour to another
 * --
 * -- $Id: main.c 4750 2019-03-18 15:29:55Z akdi $
 * ------------------------------------------------------------------------- */

/* standard includes */
#include <stdint.h>
#include <reg_stm32f4xx.h>
#include <reg_ctboard.h>
#include "pwm_sampler.h"

/* user includes */
#include "timer.h"


/* -- further preprocessor directives used as by student code
 * ------------------------------------------------------------------------- */

/// STUDENTS: To be programmed




/// END: To be programmed

/* -- global variables visible only within this module
 * ------------------------------------------------------------------------- */

/* cyclic counter value between 0 and 0xF */
static uint16_t cycle_counter_4bit = 0;

/* -- M A I N
 * ------------------------------------------------------------------------- */

int main(void)
{
    /// STUDENTS: To be programmed	
	
		tim3_init();
		tim4_init();
		while(1){
			tim3_set_compare_register(PWM_CH1, CT_DIPSW->BYTE.S7_0 & 0xF);
			tim3_set_compare_register(PWM_CH2, CT_DIPSW->BYTE.S15_8 & 0xF);
			tim3_set_compare_register(PWM_CH3, CT_DIPSW->BYTE.S23_16 & 0xF);
			
			pwm_sampler();
		};
			
    /// END: To be programmed
}


/* -- Interrupt service routine
 * ------------------------------------------------------------------------- */

void TIM4_IRQHandler(void)
{
    /// STUDENTS: To be programmed
	
	  uint8_t mask = 0x80;
		uint8_t current_led_value = CT_LED->BYTE.LED31_24;
		CT_LED->BYTE.LED31_24 = current_led_value ^ mask;
	
		tim4_reset_uif();
	
    /// END: To be programmed
}
