/* ----------------------------------------------------------------------------
 * --  _____       ______  _____                                              -
 * -- |_   _|     |  ____|/ ____|                                             -
 * --   | |  _ __ | |__  | (___    Institute of Embedded Systems              -
 * --   | | | '_ \|  __|  \___ \   Zurich University of                       -
 * --  _| |_| | | | |____ ____) |  Applied Sciences                           -
 * -- |_____|_| |_|______|_____/   8401 Winterthur, Switzerland               -
 * ----------------------------------------------------------------------------
 * --
 * -- Description:  Implementation of module state_machine.
 * --
 * -- $Id: state_machine.c 2020-04-20 kocb $
 * ------------------------------------------------------------------------- */

/* standard includes */
#include <stdint.h>
#include <reg_stm32f4xx.h>

/* user includes */
#include "state_machine.h"
#include "action_handler.h"
#include "reg_ctboard.h"
#include "timer.h"


/* -- Macros
 * ------------------------------------------------------------------------- */

#define SECURITY_DURATION    150u       // 150 * 10ms = 1.5s
#define SIGNAL_DURATION      100u       // 100 * 10ms = 1s

#define TEXT_F0_OPENED       "F0_OPENED"
#define TEXT_F0_CLOSED       "F0_CLOSED"
#define TEXT_F1_OPENED       "F1_OPENED"
#define TEXT_F1_CLOSED       "F1_CLOSED"
#define TEXT_MOVING_UP       "MOVING_UP"
#define TEXT_MOVING_DOWN     "MOVING_DOWN"
#define TEXT_BLINK_START 		 "BLINK_START"
#define TEXT_BLINK_STOP			 "BLINK_STOP"

/// STUDENTS: To be programmed
#define TRANSITION(T) do { state = (T); ah_show_state(#T); } while (0)



/// END: To be programmed


/* -- Type definitions
 * ------------------------------------------------------------------------- */

// definition of FSM states
typedef enum {
    /* task 3.1 */
    F0_OPENED,
    F0_CLOSED,
    
    /* task 3.2 */
    F1_OPENED,
    F1_CLOSED,
    MOVING_UP,
    MOVING_DOWN,

    /// STUDENTS: To be programmed
		BLINK_START,
		BLINK_STOP

	
    /// END: To be programmed
   
} state_t;


/* Module-wide variables & constants
 * ------------------------------------------------------------------------- */

// current FSM state 
static state_t state = F0_CLOSED;

// Task 3.3: maximal weight limit of the elevator


/* Public function definitions
 * ------------------------------------------------------------------------- */

/*
 * See header file
 */
void fsm_init(void)
{
    action_handler_init();
    ah_show_exception(NORMAL, "");
    
    // go to initial state & do initial actions
    
    /// STUDENTS: To be programmed
		
		ah_door(DOOR_CLOSE);
		TRANSITION(F0_CLOSED);

    /// END: To be programmed
}


/*
 * See header file
 */
void fsm_handle_event(event_t event)
{  
	/// STUDENTS: To be programmed
		switch (state) {
			case F0_CLOSED:
				switch(event) {
					case EV_DOOR0_OPENED:
						TRANSITION(F0_OPENED);
						ah_door(DOOR_OPEN);
						break;
					case EV_BUTTON_F1:
						ah_door(DOOR_LOCK);
						ah_motor(MOTOR_UP);						
						TRANSITION(MOVING_UP);
						break;
					default:
						break;
				}
				break;
			case F0_OPENED:
				switch (event){
					case EV_DOOR0_CLOSED:
						TRANSITION(F0_CLOSED);
						ah_door(DOOR_CLOSE);
						break;
					default:
						break;
				}
				break;
			case MOVING_UP:
				switch (event){
					case EV_F1_REACHED:
						TRANSITION(F1_CLOSED);
						ah_motor(MOTOR_OFF);
						ah_door(DOOR_UNLOCK);
						break;
					default:
						break;
				}		
			break;
			case F1_CLOSED:
				switch (event){
					case EV_BUTTON_F0:
						TRANSITION(MOVING_DOWN);
						ah_door(DOOR_LOCK);
						ah_motor(MOTOR_DOWN);
						break;
					case EV_DOOR1_OPENED:
						//TRANSITION(BLINK_START);	
						ah_signal(SIGNAL_ON);
						timer_start(200);	
						TRANSITION(BLINK_STOP);
						break;
				}
				break;
			case F1_OPENED: 
				switch (event){
					case EV_DOOR1_CLOSED:
						TRANSITION(F1_CLOSED);
						ah_door(DOOR_CLOSE);
						break;
					default: 
						break;
				}
				break;
			case MOVING_DOWN:
				switch (event) {
					case EV_F0_REACHED:
						TRANSITION(F0_CLOSED);
						ah_motor(MOTOR_OFF);
						ah_door(DOOR_UNLOCK);
						break;
					default:
						break;
				}
				break;
			case BLINK_START:
				switch (event){
					default:
						
						break;
				}
				break;
			case BLINK_STOP:
				switch(event) {
					case EV_TIMEOUT:
						timer_stop();
						ah_signal(SIGNAL_OFF);
						ah_door(DOOR_OPEN);
						TRANSITION(F1_OPENED);
					default:
						break;
				}
				break;
			default:
				break;
		}
    /// END: To be programmed
}
