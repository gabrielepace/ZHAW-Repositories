/* ----------------------------------------------------------------------------
 * --  _____       ______  _____                                              -
 * -- |_   _|     |  ____|/ ____|                                             -
 * --   | |  _ __ | |__  | (___    Institute of Embedded Systems              -
 * --   | | | '_ \|  __|  \___ \   Zurich University of                       -
 * --  _| |_| | | | |____ ____) |  Applied Sciences                           -
 * -- |_____|_| |_|______|_____/   8401 Winterthur, Switzerland               -
 * ----------------------------------------------------------------------------
 * --
 * -- Description:  Implementation of module action_handler.
 * --
 * -- $Id: action_handler.c 2020-04-18 kocb $
 * ------------------------------------------------------------------------- */

/* standard includes */
#include <stdint.h>
#include <stdbool.h>
#include <reg_stm32f4xx.h>

/* user includes */
#include "action_handler.h"
#include "event_handler.h"
#include "reg_ctboard.h"
#include "hal_timer.h"
#include "hal_ct_lcd.h"


/* Module-wide constants, variables and declarations
 * ------------------------------------------------------------------------- */

static uint32_t timer_count =           0u;
static const uint32_t Frame_Rate =      1000u;     // yields 8 frames per second

static motor_cmd_t elevator_state =     MOTOR_OFF;
static door_cmd_t door_state =          DOOR_CLOSE;
static signal_cmd_t signal_state =      SIGNAL_OFF;



/* Public function definitions
 * ------------------------------------------------------------------------- */

/*
 * See header file
 */
void action_handler_init(void)
{
    hal_timer_base_init_t timer_init;

    TIM3_ENABLE();

    timer_init.prescaler = 100u - 1u;            // --> 840KHz
    timer_init.mode = HAL_TIMER_MODE_UP;
    timer_init.run_mode = HAL_TIMER_RUN_CONTINOUS;
    timer_init.count = 105u - 1u;                // --> 8KHz

    hal_timer_init_base(TIM3, timer_init);
    hal_timer_irq_set(TIM3, HAL_TIMER_IRQ_UE, ENABLED);

    hal_timer_start(TIM3);
}


/*
 * See header file
 */
void ah_motor(motor_cmd_t motor_cmd)
{
    if ((door_state != DOOR_LOCK) && (motor_cmd != MOTOR_OFF)) {
        ah_show_exception(ERROR, "Lock before moving!");
    }
    elevator_state = motor_cmd;
}


/*
 * See header file
 */
void ah_door(door_cmd_t door_cmd)
{
    switch (door_cmd)
    {
        case DOOR_OPEN:
            if (door_state == DOOR_LOCK) {
                ah_show_exception(ERROR, "Unlock door first!");
            }
            door_state = DOOR_OPEN;
            break;
        case DOOR_CLOSE:
            door_state = DOOR_CLOSE;
            break;
        case DOOR_LOCK:
            if (door_state != DOOR_CLOSE) {
                ah_show_exception(ERROR, "Close before locking");
            }
            door_state = DOOR_LOCK;
            break;
        case DOOR_UNLOCK:
            if (elevator_state != MOTOR_OFF) {
                ah_show_exception(ERROR, "Unlock while moving!");
            }
            door_state = DOOR_CLOSE;
            break;
    }
}


/*
 * See header file
 */
void ah_signal(signal_cmd_t signal_cmd)
{
    signal_state = signal_cmd;
}



/*
 * See header file
 */
void ah_show_state(char text[])
{
#ifndef CPPUTEST
    hal_ct_lcd_write(0, "                    ");
    hal_ct_lcd_write(0, text);
#endif
}


/*
 * See header file
 */
void ah_show_exception(exception_t exception, char text[])
{
#ifndef CPPUTEST
    switch (exception)
    {
        case NORMAL:
            // set a friendly white background
            CT_LCD->BG.RED = 0xffff;
            CT_LCD->BG.GREEN = 0xa000;
            CT_LCD->BG.BLUE = 0xa000;
            break;
        case WARNING:
            // yellow/orange
            CT_LCD->BG.RED = 0xffff;
            CT_LCD->BG.GREEN = 0x3000;
            CT_LCD->BG.BLUE = 0x0;
            break;
        case ERROR:
            // red
            CT_LCD->BG.RED = 0xffff;
            CT_LCD->BG.GREEN = 0x0;
            CT_LCD->BG.BLUE = 0x0;
            break;
    }
    hal_ct_lcd_write(20, "                    ");
    hal_ct_lcd_write(20, text);
    
    // loop forever on error condition
    if (exception == ERROR) {
        while(1) {};
    }
    
#endif
}


/* Interrupt service routine & elevator/door animation
 * ------------------------------------------------------------------------- */


const uint16_t Elevator_Position_F0 = 0u;
const uint16_t Elevator_Position_F1 = 16u;
const uint32_t PWM_Period = 16u;
const uint32_t PWM_Elevator = 2u;

uint16_t elevator_position = 0u; 
uint32_t elevator_pattern = 0u;

const uint16_t Door_Position_Open = 7u;
const uint16_t Door_Position_Closed = 0u;
uint16_t door_position = Door_Position_Closed;
uint32_t door_pattern = 0u;

const uint32_t Signal_Pattern = 0xf00f0000;


void TIM3_IRQHandler(void)
{
    uint32_t led_pattern = 0u;
    uint32_t pwm_cycle = 0u;
    
    if (hal_timer_irq_status(TIM3, HAL_TIMER_IRQ_UE)) {
        hal_timer_irq_clear(TIM3, HAL_TIMER_IRQ_UE);

        timer_count = (timer_count + 1) % (Frame_Rate * PWM_Period);
        
        // adjust the elevator & door positions
        // and update patterns, if necessary. Check for bounds.
        if (!(timer_count % Frame_Rate)) {
            
            // elevator position
            switch (elevator_state)
            {
                case MOTOR_OFF:
                    break;

                case MOTOR_UP:
                    // generate an error, if the elevator is already in top position
                    if (elevator_position == Elevator_Position_F1) {
                        ah_show_exception(ERROR, "CRASH!! on floor F1");
                    } else {
                        // move elevator up & signal upper floor if reached
                        elevator_position++;
                        if (elevator_position == Elevator_Position_F1) {
                            flag_F1_Reached = true;
                        }
                    }
                    break;

                case MOTOR_DOWN:
                    // generate an error, if the elevator is already in base position
                    if (elevator_position == Elevator_Position_F0) {
                        ah_show_exception(ERROR, "CRASH!! on floor F0");
                    } else {
                        // move elevator down & signal lower floor if reached
                        elevator_position--;
                        if (elevator_position == Elevator_Position_F0) {
                            flag_F0_Reached = true;
                        }
                    }
                    break;
            }

            // door position
            if (door_state == DOOR_LOCK) {
                door_position = Door_Position_Closed;
            }
            else if (door_state == DOOR_CLOSE && door_position > Door_Position_Closed) {
                door_position -= 1;
            }
            else if (door_state == DOOR_OPEN && door_position < Door_Position_Open) {
                door_position += 1;
            }
            
            // patterns
            door_pattern =      ((0x0100 << door_position) | (0x0080 >> door_position)) 
                                    << elevator_position;
            elevator_pattern =  ((0xffff >> (7 - door_position)) & (0xffff << (7 - door_position))) 
                                    << elevator_position;
        }
        
        /* show PWM-assisted animation of the elevator & the doors on 32 LEDs */
        pwm_cycle = timer_count % PWM_Period;
        led_pattern = 0u;
        
        // elevator
        if (pwm_cycle < PWM_Elevator) {
            led_pattern |= elevator_pattern;
        }
        // doors
        if (door_state != DOOR_LOCK) {
            led_pattern |= door_pattern;
        }
        // Task 3.3: upper floor signal
        if (signal_state == SIGNAL_ON) {
            led_pattern |= Signal_Pattern;
        }
        
        // send current animation state to the CT-Board LEDs
        CT_LED->WORD = led_pattern;
        
    }
}


