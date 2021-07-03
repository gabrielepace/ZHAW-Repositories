/* ----------------------------------------------------------------------------
 * --  _____       ______  _____                                              -
 * -- |_   _|     |  ____|/ ____|                                             -
 * --   | |  _ __ | |__  | (___    Institute of Embedded Systems              -
 * --   | | | '_ \|  __|  \___ \   Zurich University of                       -
 * --  _| |_| | | | |____ ____) |  Applied Sciences                           -
 * -- |_____|_| |_|______|_____/   8401 Winterthur, Switzerland               -
 * ----------------------------------------------------------------------------
 * --
 * -- Description:  Implementation of module timer.
 * --
 * -- $Id: timer.c 4751 2019-03-20 13:00:11Z akdi $
 * ------------------------------------------------------------------------- */

/* standard includes */
#include <stdint.h>
#include <reg_stm32f4xx.h>

/* user includes */
#include "timer.h"


/* -- Macros
 * ------------------------------------------------------------------------- */

#define PERIPH_GPIOB_ENABLE (0x00000002)
#define PERIPH_TIM3_ENABLE  (0x00000002)
#define PERIPH_TIM4_ENABLE  (0x00000004)

/* Configuring pins used for PWM: PA.0, PA.4, PA.5 */
#define GPIOB_MODE_AF      (0x00000a02)
#define GPIOB_MODE_CLEAR   (0xfffff0f0)

#define GPIOB_SPEED_100MHZ (0x00000f03)
#define GPIOB_SPEED_CLEAR  (0xfffff0f0)

#define GPIOB_AF_TIM3      (0x00220002)
#define GPIOB_AF_CLEAR     (0xff00fff0)


/* -- Macros for timer configuration
 * ------------------------------------------------------------------------- */
#define NVIC_IRQ_TIM4 (0x40000000)                  // NVIC mask for interrupt
#define TIM4_IRQ_UIF  (0x00000001)                  // TIM4 mask for update irq flag

/* Add additional macros as used by your code */
/// STUDENTS: To be programmed




/// END: To be programmed


/* Public function definitions
 * ------------------------------------------------------------------------- */

/*
 * See header file
 */
void tim3_init(void)
{
    /* Enable peripheral clocks */

    RCC->APB1ENR |= PERIPH_TIM3_ENABLE;
    RCC->AHB1ENR |= PERIPH_GPIOB_ENABLE;

    /* Initialize GPIO pins */
    GPIOB->MODER &= GPIOB_MODE_CLEAR;
    GPIOB->MODER |= GPIOB_MODE_AF;

    GPIOB->OSPEEDR &= GPIOB_SPEED_CLEAR;
    GPIOB->OSPEEDR |= GPIOB_SPEED_100MHZ;

    GPIOB->AFRL &= GPIOB_AF_CLEAR;
    GPIOB->AFRL |= GPIOB_AF_TIM3;

    /* initialize timer registers to default values; the values are suitable
       for the basic timer modes used in this lab */

    TIM3->CR2 = 0x0000;
    TIM3->SMCR = 0x0000;                            	// selects CK_INT as source
    TIM3->DIER = 0x0000;                            	// disables all interrupts

    /* add specific configuration for timer3 */
    /// STUDENTS: To be programmed

		TIM3->CR1 = (0x0 << 4); 													//set dir to 0=up
		TIM3->PSC = (7-1);																//set PSC
		TIM3->ARR =  (60000-1);														//set ARR. t.verhältnis:= PSC*AAR
		
		// set outoput compare modes
		TIM3->CCMR1 = (0x6 <<12)| (0x6 << 4);   					// mode1 = 0110=0x6
		TIM3->CCMR2 = (0x6 << 4); 
		
		//uint16_t mask_enable_c1_toc3_output = 0x0111;  		// this maks sets the bits: 0,4,8,12
		TIM3->CCER = 0x0111;
		TIM3->CR1 = (0x1 << 0);    												// enable timer for CR1

    /// END: To be programmed
}


/*
 * See header file
 */
void tim4_init(void)
{
    /* Enable peripheral clock */
    RCC->APB1ENR |= PERIPH_TIM4_ENABLE;

    /* initialize timer registers to default values; the values are suitable
       for the basic timer modes used in this lab */
    TIM4->CR2 = 0x0000;
    TIM4->SMCR = 0x0000;                            // selects CK_INT as source

    /* add specific configuration for timer4 */
    /// STUDENTS: To be programmed
	
		TIM4->PSC =  (8400-1);
		TIM4->ARR =  (10000-1);
		TIM4->CR1 =  (0x1 << 4); // downcounter
		TIM4->DIER = (0x1 << 0);
		TIM4->CR1 =  (0x1 << 0);

    /// END: To be programmed

    /* Enable TIM4 interrupt in NVIC */
    NVIC->ISER0 |= NVIC_IRQ_TIM4;
}


/*
 * See header file
 */
void tim4_reset_uif(void)
{
    TIM4->SR &= ~TIM4_IRQ_UIF;
}


/*
 * See header file
 */
void tim3_set_compare_register(pwm_channel_t channel, uint16_t value)
{
    /// STUDENTS: To be programmed
	
	uint16_t duty_cycle_step = 4000;

		switch(channel) {
			case PWM_CH1:
							TIM3->CCR1 = value * duty_cycle_step;
							break;
			case PWM_CH2:
							TIM3->CCR2 = value * duty_cycle_step;
							break;
			case PWM_CH3:
							TIM3->CCR3 = value * duty_cycle_step;
							break;
			default: 
							break;
		}


    /// END: To be programmed
}

