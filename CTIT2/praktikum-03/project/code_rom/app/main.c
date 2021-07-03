/* ------------------------------------------------------------------
 * --  _____       ______  _____                                    -
 * -- |_   _|     |  ____|/ ____|                                   -
 * --   | |  _ __ | |__  | (___    Institute of Embedded Systems    -
 * --   | | | '_ \|  __|  \___ \   Zurich University of             -
 * --  _| |_| | | | |____ ____) |  Applied Sciences                 -
 * -- |_____|_| |_|______|_____/   8401 Winterthur, Switzerland     -
 * ------------------------------------------------------------------
 * --
 * -- Application for testing external memory
 * --
 * -- $Id: main.c 3647 2016-09-27 12:37:36Z kesr $
 * ------------------------------------------------------------------
 */

/* standard includes */
#include <stdint.h>
#include "hal_ct_buttons.h"
#include <reg_ctboard.h>
#define MEM_ADR 			0x64000400

/// STUDENTS: To be programmed




/// END: To be programmed

int main(void){
    /// STUDENTS: To be programmed
		while(1) {
			uint8_t currentByte = 0;
			uint16_t i = 0;
			for(i=0; i<256; i++) {
				currentByte = *((volatile uint8_t*) MEM_ADR+i);				
				CT_LED->BYTE.LED23_16 = currentByte;
				CT_LED->BYTE.LED7_0 = i;	
				
				if(currentByte != i) {					
					CT_SEG7->RAW.BYTE.DS0 = currentByte;				
					while(!hal_ct_button_is_pressed(HAL_CT_BUTTON_T0)){}					
				}										
			}
		}
    /// END: To be programmed
}

