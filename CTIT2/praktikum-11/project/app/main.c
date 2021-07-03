/* ----------------------------------------------------------------------------
 * --  _____       ______  _____                                              -
 * -- |_   _|     |  ____|/ ____|                                             -
 * --   | |  _ __ | |__  | (___    Institute of Embedded Systems              -
 * --   | | | '_ \|  __|  \___ \   Zuercher Hochschule Winterthur             -
 * --  _| |_| | | | |____ ____) |  (University of Applied Sciences)           -
 * -- |_____|_| |_|______|_____/   8401 Winterthur, Switzerland               -
 * ----------------------------------------------------------------------------
 * --
 * -- Project     : CT2 lab - Elevator FSM
 * -- Description : Main program.
 * --
 * -- $Id: main.c 4772 2020-04-14 kocb $
 * ------------------------------------------------------------------------- */

/* standard includes */
#include <stdint.h>
#include <reg_stm32f4xx.h>

/* user includes */
#include "event_handler.h"
#include "state_machine.h"
#include "timer.h"


/* -- M A I N
 * ------------------------------------------------------------------------- */

int main(void)
{
    /// STUDENTS: To be programmed
		event_t event;
		fsm_init();
		timer_init();
		while (1) {
			event = eh_get_event();
			if (event != EV_NO_EVENT) {
				fsm_handle_event(event);
		}
}

    /// END: To be programmed
}

