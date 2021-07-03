/* ----------------------------------------------------------------------------
 * --  _____       ______  _____                                              -
 * -- |_   _|     |  ____|/ ____|                                             -
 * --   | |  _ __ | |__  | (___    Institute of Embedded Systems              -
 * --   | | | '_ \|  __|  \___ \   Zurich University of                       -
 * --  _| |_| | | | |____ ____) |  Applied Sciences                           -
 * -- |_____|_| |_|______|_____/   8401 Winterthur, Switzerland               -
 * ----------------------------------------------------------------------------
 * --
 * -- Description:  
 * --               Samples 3 pins of GPIOB (0, 4 and 5), and shows the sampled
 * --               values on 8 LEDs each.
 * --               At the same time, these pins are the pwm outputs of timer 3,
 * --               so this module acts as a 'pwm duty cycle visualizer', where 
 * --               the duty cycle is mapped to the brightness of the LEDs.
 * --
 * -- $Id: pwm_sampler.h 2020-03-19 10:08:37Z kocb $
 * ------------------------------------------------------------------------- */

/* re-definition guard */
#ifndef _PWM_SAMPLER_H
#define _PWM_SAMPLER_H

/* standard includes */
#include <stdint.h>


/* -- Public function declarations
 * ------------------------------------------------------------------------- */

/*
 * samples the pwm pins and drive the LEDs chosen for visualisation.
 */
void pwm_sampler(void);

#endif
