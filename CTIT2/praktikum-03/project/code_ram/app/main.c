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
 * -- $Id: main.c 4438 2018-02-14 16:11:00Z kjaz $
 * ------------------------------------------------------------------
 */

/* standard includes */
#include <stdint.h>
#include "hal_rcc.h"
#include "hal_fmc.h"
#include "hal_ct_buttons.h"
#include <reg_ctboard.h>

#define MEM_ADR 			0x64000000

/* for peripheral init */
hal_fmc_sram_init_t init;
hal_fmc_sram_timing_t timing;

void intitialize_ram();
void check_ram();

int main(void)
{
    init.address_mux = DISABLE;                             // setup peripheral
    init.type = HAL_FMC_TYPE_SRAM;
    init.width = HAL_FMC_WIDTH_8B;
    init.write_enable = ENABLE;

    timing.address_setup = 0xFF;                            // all in HCLK
                                                            // cycles
    timing.address_hold = 0xFF;
    timing.data_setup = 0xFF;

    hal_fmc_init_sram(HAL_FMC_SRAM_BANK2, init, timing);    // init external bus
                                                            // bank 2 (NE2)
                                                            // asynch

    /// STUDENTS: To be programmed
		intitialize_ram();
		while(!hal_ct_button_is_pressed(HAL_CT_BUTTON_T0)){}
		check_ram();
    /// END: To be programmed
}

void intitialize_ram(){
		uint8_t i = 0xFF;
		uint8_t data = 0x00;
		volatile uint8_t* mem_pointer = (volatile uint8_t *) MEM_ADR;
		
		while(i <= 0x00) {
			data = 0xFF - i;
			
			*mem_pointer = data;
			
			
			// increment stuff
			i = i -1;
			mem_pointer = mem_pointer+1;			
		}
}

void check_ram() {
	uint8_t currentByte = 0;
	uint8_t i = 0;
	uint8_t data = 0x00;
	for(i=0; i<255; i++) {
		data = 0xFF - i;
		
		currentByte = *((volatile uint8_t*) MEM_ADR+i);				
		if(currentByte != data) {					
			CT_SEG7->RAW.BYTE.DS0 = currentByte;
			
			while(!hal_ct_button_is_pressed(HAL_CT_BUTTON_T0)){}					
		}
		
		CT_LED->BYTE.LED23_16 = currentByte;
		CT_LED->BYTE.LED7_0 = i;				
	}
}
