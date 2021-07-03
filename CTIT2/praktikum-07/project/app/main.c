/* ----------------------------------------------------------------------------
 * --  _____       ______  _____                                              -
 * -- |_   _|     |  ____|/ ____|                                             -
 * --   | |  _ __ | |__  | (___    Institute of Embedded Systems              -
 * --   | | | '_ \|  __|  \___ \   Zurich University of                       -
 * --  _| |_| | | | |____ ____) |  Applied Sciences                           -
 * -- |_____|_| |_|______|_____/   8401 Winterthur, Switzerland               -
 * ----------------------------------------------------------------------------
 * -- $Id: main.c 4800 2019-05-09 15:30:18Z ruan $
 * ------------------------------------------------------------------------- */

#include <stdint.h>
#include <stdio.h>
#include <reg_stm32f4xx.h>
#include "hal_ct_lcd.h"
#include "hal_timer.h"


/* -- defines
 * ------------------------------------------------------------------------- */
#define NUMBER_OF_TIMER_2_INTERRUPTS (uint32_t)500
#define RELOAD_VALUE_TIM2            (uint32_t)84000
#define COUNTER_VALUE                TIM2->CNT

/* set load (interrupt frequency of timer3) */
//#define LOAD_NONE
//#define LOAD_WITH_10KHZ
//#define LOAD_WITH_100KHZ
//#define LOAD_WITH_500KHZ
#define LOAD_WITH_1MHZ

#if defined (LOAD_NONE)
    #define RELOAD_VALUE_TIM3        (uint16_t)0
#elif defined (LOAD_WITH_10KHZ)
    #define RELOAD_VALUE_TIM3        (uint16_t)8400
#elif defined (LOAD_WITH_100KHZ)
    #define RELOAD_VALUE_TIM3        (uint16_t)840
#elif defined (LOAD_WITH_500KHZ)
    #define RELOAD_VALUE_TIM3        (uint16_t)168
#elif defined (LOAD_WITH_1MHZ)
    #define RELOAD_VALUE_TIM3        (uint16_t)84
#endif


/* -- functions with module-wide scope
 * ------------------------------------------------------------------------- */
static void print_results(void);
static uint8_t convert_uint32_t_to_string(char ret_val[], uint32_t value);


/* -- variables with module-wide scope
 * ------------------------------------------------------------------------- */
static volatile hal_bool_t measurement_done = FALSE;
static uint32_t tim3_interrupt_counter = 0;
static uint32_t tim2_interrupt_counter = 0;
static uint32_t min_latency = 100000;
static uint32_t max_latency = 0;
static uint32_t avg_latency = 0;
static uint32_t sum_latency = 0;
static uint32_t latency = 0;


/* -- M A I N
 * ------------------------------------------------------------------------- */

int main(void)
{
    hal_timer_base_init_t timer_init;
    
    // init display
    hal_ct_lcd_clear();
    hal_ct_lcd_color(HAL_LCD_RED, 0xffff);
    hal_ct_lcd_color(HAL_LCD_BLUE, 0u);
    hal_ct_lcd_color(HAL_LCD_GREEN, 0u);

    // init timer2 with a clock source frequency of 84MHz
    TIM2_ENABLE();
    TIM2_RESET();

    timer_init.mode = HAL_TIMER_MODE_UP;
    timer_init.run_mode = HAL_TIMER_RUN_CONTINOUS;
    timer_init.prescaler = 0u;
    timer_init.count = RELOAD_VALUE_TIM2;     //counter overflow every 1ms
    
    hal_timer_init_base(TIM2, timer_init);
    hal_timer_irq_set(TIM2, HAL_TIMER_IRQ_UE, ENABLE);

    // init timer3 with a clock source frequency of 84MHz
    TIM3_ENABLE();
    TIM3_RESET();

    timer_init.mode = HAL_TIMER_MODE_UP;
    timer_init.run_mode = HAL_TIMER_RUN_CONTINOUS;
    timer_init.prescaler = 0u;
    timer_init.count = RELOAD_VALUE_TIM3;
    
    hal_timer_init_base(TIM3, timer_init);
    hal_timer_irq_set(TIM3, HAL_TIMER_IRQ_UE, ENABLE);
		
		// Bis Aufg. 3.3 b):
		// set interrupt priority
    // NVIC->IP[28] = 0x40;      //set priority level of timer2 to 4
    // NVIC->IP[29] = 0x10;      //set priority level of timer3 to 1
		
		// Nur Aufg. 3.3 c):
    // set interrupt priority
    NVIC->IP[28] = 0x10;      //set priority level of timer2 to 1
    NVIC->IP[29] = 0x40;      //set priority level of timer3 to 4

    // start timer timer2 and timer3
    hal_timer_start(TIM2);
    #ifndef LOAD_NONE
    hal_timer_start(TIM3);
    #endif
    
    // wait for measurement to finish
    while(!measurement_done){
    }
    
    // print out measurement
    avg_latency = sum_latency / tim2_interrupt_counter;
    print_results();

    while (1){
    }
}

/* -- interrupt service routines
 * ------------------------------------------------------------------------- */

void TIM2_IRQHandler(void)
{
/// STUDENTS: To be programmed
	
		// called 1000 times per second
		// 1. read counter from TIM2 (Important: this is the first instruction since this is the indicator of the latency)
		// 2. if tim2_interrupt_counter >= NUMBER_OF_TIMER_2_INTERRUPTS
		// 2.1	set measurement_done = TRUE
		// 2.2 stop timer 2 by hal_timer_stop(TIM2);
		// 3. else
		// 3.1 tim_interrupt_counter++
		// 3.1 update min/max latency
		// 4. notify TIM2 that the ISR is completed: hal_timer_irq_clear(TIM2, HAL_TIMER_IRQ_UE);

	uint32_t value = COUNTER_VALUE;
	tim2_interrupt_counter++;
	
	hal_timer_irq_clear(TIM2, HAL_TIMER_IRQ_UE);
	
	if (tim2_interrupt_counter >= NUMBER_OF_TIMER_2_INTERRUPTS) {
		measurement_done = TRUE;
	}
  if(value < min_latency) {min_latency = value;}
	
  if(value > max_latency) {max_latency = value;}
	
  sum_latency += value;	
/// END: To be programmed
    
}

void TIM3_IRQHandler(void)
{

    hal_timer_irq_clear(TIM3, HAL_TIMER_IRQ_UE);
    tim3_interrupt_counter++; 

}


/* -- local function definition
 * ------------------------------------------------------------------------- */

/*
 * Prints the minimal, maximal and average interrupt latency and the number
 * of occured external interrupts to the display
 */
static void print_results(void)
{
  
    char label_min[] = "min:";
    char label_max[] = "max:";
    char label_avg[] = "avg:";
    char label_exti[] = "exti:";

    char ret_val[10];
    uint8_t length = 0;
    uint8_t pos = 0;

    // set lcd backlight green
    hal_ct_lcd_color(HAL_LCD_RED, 0u);
    hal_ct_lcd_color(HAL_LCD_GREEN, 0xffff);

    // write label "min"
    length = 4;
    hal_ct_lcd_write(pos, label_min);
    
    /* Add writing of min, max and average values to LCD, 
     * include the appropriate labels.
     * 
     * Add writing of the counted number of external interrupts to LCD,
     * include the appropriate label
     */
    
/// STUDENTS: To be programmed
	convert_uint32_t_to_string(ret_val, min_latency);
	hal_ct_lcd_write(4, ret_val);
	
	hal_ct_lcd_write(10, label_max);
	convert_uint32_t_to_string(ret_val, max_latency);
	hal_ct_lcd_write(14, ret_val);
	
	hal_ct_lcd_write(20, label_avg);
	convert_uint32_t_to_string(ret_val, avg_latency);
	hal_ct_lcd_write(24, ret_val);
/// END: To be programmed
}

/*
 * Converts the uint32_t value into a string. The result of the conversion is 
 * stored in ret_val[]. ret_val[] must be large enough to hold the string
 * The functions returns a negative value in the case of an error. Otherwise 
 * number of characters written is returned.
 */
static uint8_t convert_uint32_t_to_string(char ret_val[], uint32_t value)
{
    return sprintf(ret_val, "%d", value);
}
