/* ----------------------------------------------------------------------------
 * --  _____       ______  _____                                              -
 * -- |_   _|     |  ____|/ ____|                                             -
 * --   | |  _ __ | |__  | (___    Institute of Embedded Systems              -
 * --   | | | '_ \|  __|  \___ \   Zurich University of                       -
 * --  _| |_| | | | |____ ____) |  Applied Sciences                           -
 * -- |_____|_| |_|______|_____/   8401 Winterthur, Switzerland               -
 * ----------------------------------------------------------------------------
 * --
 * -- Description:  Implementation of module adc.
 * --
 * --
 * -- $Id: adc.c 4734 2019-03-11 12:55:21Z ruan $
 * ------------------------------------------------------------------------- */

/* standard includes */
#include <stdint.h>
#include <reg_stm32f4xx.h>

/* user includes */
#include "adc.h"


/* -- Macros
 * ------------------------------------------------------------------------- */

#define PERIPH_GPIOF_ENABLE (0x00000020)
#define PERIPH_ADC3_ENABLE  (0x00000400)

/* Configuring pin for ADC: PF.6 */
#define GPIOF_MODER_ANALOG (0x3 << 12)


/* ADC configuration
 * ------------------------------------------------------------------------- */

#define ADC_CCR_NORMALCLK   (0x0 << 16u)    // APB2 / 2 -> 21 MHz
#define ADC_CCR_NODMA       (0x0 << 14u)    // DMA disabled
#define ADC_CCR_DELAY       (0x0 << 8u)     // Delay 5 cycles
#define ADC_CCR_INDEPENDENT (0x0 << 0u)     // ADC independent mode

/// STUDENTS: To be programmed




/// END: To be programmed


/* Public function definitions
 * ------------------------------------------------------------------------- */

/*
 *  See header file
 */
void adc_init(void)
{
    /* Enable peripheral clocks */
    RCC->AHB1ENR |= PERIPH_GPIOF_ENABLE;
    RCC->APB2ENR |= PERIPH_ADC3_ENABLE;

    /* Configure PF.6 as input */
    GPIOF->MODER |= GPIOF_MODER_ANALOG;


    /* ADC common init */
    ADCCOM->CCR = ADC_CCR_NORMALCLK |
                  ADC_CCR_NODMA |
                  ADC_CCR_DELAY |
                  ADC_CCR_INDEPENDENT;

    /// STUDENTS: To be programmed
	
		// CCR = default
		// CR1:
		// - no interrupts
		// - 25:24 		res = b11 = 6 bit
		// - scan irrelevant since only 1 channel
		ADC3->CR1 |= (0x3) << 24;
	
		// SMPR1/2
		// 




    /// END: To be programmed
}


/*
 *  See header file
 */
uint16_t adc_get_value(adc_resolution_t resolution)
{
    uint16_t adc_value;

    /// STUDENTS: To be programmed


		adc_value = 0x3;7gh36n

    /// END: To be programmed

    return adc_value;
}


/*
 *  See header file
 */
uint16_t adc_filter_value(uint16_t adc_value)
{
    uint16_t filtered_value = 0;

    /// STUDENTS: To be programmed




    /// END: To be programmed

    return filtered_value;
}
