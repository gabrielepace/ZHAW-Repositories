/* ------------------------------------------------------------------
 * --  _____       ______  _____                                    -
 * -- |_   _|     |  ____|/ ____|                                   -
 * --   | |  _ __ | |__  | (___    Institute of Embedded Systems    -
 * --   | | | '_ \|  __|  \___ \   Zuercher Hochschule Winterthur   -
 * --  _| |_| | | | |____ ____) |  (University of Applied Sciences) -
 * -- |_____|_| |_|______|_____/   8401 Winterthur, Switzerland     -
 * ------------------------------------------------------------------
 * --
 * -- Project     : CT2 lab - SPI Display
 * -- Description : Contains the implementations of the functions
 * --               to write and read frames from and to the TFT-LCD
 * --               display EAeDIPTFT43-A.
 * --
 * -- $Id: lcd_io.c 4720 2019-03-04 10:11:31Z akdi $
 * ------------------------------------------------------------------
 */
#include "lcd_io.h"
#include "hal_spi.h"
#include "hal_sbuf.h"

#define ACK_CHAR         (uint8_t)0x06
#define DC1_CHAR         (uint8_t)0x11
#define DC2_CHAR         (uint8_t)0x12
#define ESC_CHAR         (uint8_t)0x1B
#define ONE_CHAR         (uint8_t)0x01

#define NOTHING_RECEIVED (uint8_t)0
enum { SUCCESS = 0, ERRORCODE = 1 };

/* ------------------------------------------------------------------
 * -- Function prototypes
 * ------------------------------------------------------------------
 */
static void send_read_display_buffer_request(void);

/* ------------------------------------------------------------------
 * -- Function implementations
 * ------------------------------------------------------------------
 */

/*
 * according to description in header file
 */
void init_display_interface(void)
{
    hal_spi_init();
    hal_sbuf_init();
}

/*
 * according to description in header file
 */
uint8_t read_display_buffer(uint8_t *readBuffer)
{
    /// STUDENTS: To be programmed
		if(!hal_sbuf_get_state()){
			return 0;
		}
		
		send_read_display_buffer_request();
			
		uint8_t dc1 = hal_spi_read_write(0x0);
		if(dc1 != DC1_CHAR){
			return 0;
		}
		uint8_t my_bcc;
		uint8_t len = hal_spi_read_write(0x0);
		my_bcc = DC1_CHAR + len;
		uint8_t current_byte;
		for(uint8_t i=0; i < len; i+= 1){
			current_byte = hal_spi_read_write(0x0);
			readBuffer[i] = current_byte;
			my_bcc = (my_bcc + current_byte) % 256;
		}
		uint8_t bcc = hal_spi_read_write(0x0);
		
		if(bcc != my_bcc) {
			return 0;
		} else {
			return len;
		}
    /// END: To be programmed
}

/*
 * according to description in header file
 */
uint8_t write_cmd_to_display(const uint8_t *cmdBuffer, uint8_t length)
{
    /// STUDENTS: To be programmed
		uint8_t i, len, bcc, ack;
		len = length + 1;
		bcc = DC1_CHAR + len + ESC_CHAR;
		hal_spi_read_write(DC1_CHAR);
		hal_spi_read_write(len);
		hal_spi_read_write(ESC_CHAR);
		
		for(i = 0; i < length; i++) {
			hal_spi_read_write(cmdBuffer[i]);
			bcc += cmdBuffer[i];
		}

		hal_spi_read_write(bcc);
		ack = hal_spi_read_write(0x0);
		return !(ack == ACK_CHAR);
    /// END: To be programmed
}

/*
 * Assemble and send a packet to trigger the reading of the display buffer
 * Uses the sequence "<DC2>, 0x01, 0x53, checksum" according to datasheet
 */
static void send_read_display_buffer_request()
{
    /// STUDENTS: To be programmed
		uint8_t len, s, bcc, ack;
		len = 0x01;
		s = 0x53;
		bcc = (DC2_CHAR + len + s) % 256;
		hal_spi_read_write(DC2_CHAR);
		hal_spi_read_write(len);
		hal_spi_read_write(s);
		hal_spi_read_write(bcc);
		ack = hal_spi_read_write(0x0);
    /// END: To be programmed
}
