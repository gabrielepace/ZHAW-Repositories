/* ----------------------------------------------------------------------------
 * --  _____       ______  _____                                              -
 * -- |_   _|     |  ____|/ ____|                                             -
 * --   | |  _ __ | |__  | (___    Institute of Embedded Systems              -
 * --   | | | '_ \|  __|  \___ \   Zurich University of                       -
 * --  _| |_| | | | |____ ____) |  Applied Sciences                           -
 * -- |_____|_| |_|______|_____/   8401 Winterthur, Switzerland               -
 * ------------------------------------------------------------------------- */
/**
 *  \brief  Implementation of module hal_gpio.
 *
 *  The hardware abstraction layer for the GPIO periphery.
 *
 *  \file   hal_gpio.c
 *  $Id: hal_gpio.c 3130 2016-03-08 07:33:16Z ruan $
 * ------------------------------------------------------------------------- */

/* user includes */
#include "hal_gpio.h"

/* internal function declarations ------------------------------------------ */
static uint32_t create_pattern_mask(uint32_t pin_mask,
                                    uint32_t pattern_per_pin,
                                    uint8_t pattern_bit_width);

static uint16_t intercept_overwrite_register(reg_gpio_t *port, uint16_t pins);

/* public function definitions --------------------------------------------- */

/*
 * See header file
 */
void hal_gpio_reset(reg_gpio_t *port)
{
    if (port == GPIOA) {
        /* Reset GPIOA specific values */
        port->MODER = 0xa8000000;       // mode register (gpio or others)
        port->OSPEEDR = 0x00000000;     // output speed register
        port->PUPDR = 0x64000000;       // pull resistor register
    } else if (port == GPIOB) {
        /* Reset GPIOB specific values */
        port->MODER = 0x00000280;
        port->OSPEEDR = 0x000000c0;
        port->PUPDR = 0x00000100;
    } else {
        /* Reset other GPIO */
        port->MODER = 0x00000000;
        port->OSPEEDR = 0x00000000;
        port->PUPDR = 0x00000000;
    }

    port->OTYPER = 0x00000000;          // type register (pp / f. gate)
    port->AFRL = 0x00000000;
    port->AFRH = 0x00000000;
    port->ODR = 0x00000000;             // output data register
}


/* internal functions definitions ------------------------------------------ */

/**
 *  \brief  Creates a pattern based on specified pins.
 *
 * E.g. if a register has N bits per pin to be cleared or set by a specific
 *      pattern, you can use this function as follows:
 *
 *      assumption for this example: pin number 3, set pattern 0b1011,
 *      bit pattern witdh per pin in this register is 4:
 *
 *      pin_mask = (1u << 3);
 *      // clears the respective 4 bits... (produces 0x0000F000)
 *      port->reg &= ~create_pattern_mask(pin_mask, ~0u,  4u);
 *      // ...and then sets the pattern    (produces 0x0000B000)
 *      port->reg |=  create_pattern_mask(pin_mask, 0xB, 4u);
 *
 *  \param pin_mask:          each set bit marks an affected pin (32 pins)
 *  \param pattern_per_pin:   the pattern to produce per pin (max 31 bits)
 *  \param pattern_bit_width: width of the pattern per pin (0...31)
 *
 *  Note: patterns that go beyond the left boundary are silently clipped
 *        (maybe even partially clipped).
 *
 *  Note: width of larger than 31 bits is treated as width = 0.
 *
 */
static uint32_t create_pattern_mask(uint32_t pin_mask,
                                    uint32_t pattern_per_pin,
                                    uint8_t pattern_bit_width)
{
    const uint32_t pins = sizeof(pin_mask) << 3;         // number of bytes * 8
    const uint32_t bits = pattern_bit_width < pins ? pattern_bit_width : 0;
    const uint32_t msb = 1u << (pins - 1);
    const uint32_t pattern = pattern_per_pin & ~((~0u) << bits);    // clipping

    uint32_t mask = 0u;
    uint32_t pin = pins;
    while (pin-- > 0) {
        mask <<= bits;
        if (pin_mask & msb) {
            mask |= pattern;
        }
        pin_mask <<= 1;
    }
    return mask;
}

/*
 * See header file
 */
void hal_gpio_init_alternate(reg_gpio_t *port,
                             hal_gpio_af_t af_mode,
                             hal_gpio_output_t init)
{
    /* treat like output */
    init.pins = intercept_overwrite_register(port, init.pins);
  
    /* treat like output */
    /* process mode */
    port->MODER &= ~create_pattern_mask(init.pins, 0x3, 2u);
    port->MODER |= create_pattern_mask(init.pins, HAL_GPIO_MODE_OUT, 2u);

    port->PUPDR &= ~create_pattern_mask(init.pins, 0x3, 2u);
    port->PUPDR |= create_pattern_mask(init.pins, init.pupd, 2u);
  
    /* treat like output */
    /* process port speed */
    port->OSPEEDR &= ~create_pattern_mask(init.pins, 0x3, 2u);
    port->OSPEEDR |= create_pattern_mask(init.pins, init.out_speed, 2u);
  
    /* treat like output */
    /* process output typ  */
    port->OTYPER &= ~init.pins;
    if (init.out_type == HAL_GPIO_OUT_TYPE_OD) {
        port->OTYPER |= init.pins;
    }
    

    /* change mode */
    port->MODER &= ~create_pattern_mask(init.pins, 0x3, 2u);
    port->MODER |= create_pattern_mask(init.pins, HAL_GPIO_MODE_AF, 2u);

    /* process af type */
    port->AFRL &= ~create_pattern_mask(init.pins, 0xf, 4u);
    port->AFRL |= create_pattern_mask(init.pins, af_mode, 4u);
    port->AFRH &= ~create_pattern_mask((init.pins >> 8), 0xf, 4u);
    port->AFRH |= create_pattern_mask((init.pins >> 8), af_mode, 4u);
}

/*
 *  On GPIOA and GPIOB only pins 11 down to 0 are available to the user.
 *  Pins 15 down to 12 are used for system functions of the discovery board,
 *  e.g. connection of the debugger.
 *  These pins must not be reconfigured. Otherwise the debugger cannot be used anymore
 *  This function ensures that these sensitive pins are not reconfigured.
 */
static uint16_t intercept_overwrite_register(reg_gpio_t *port, uint16_t pins)
{
    if (port == GPIOA || port == GPIOB) {
        pins &= 0x0FFF;
    }
    return pins;
}
