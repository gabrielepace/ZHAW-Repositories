/* ----------------------------------------------------------------------------
 * --  _____       ______  _____                                              -
 * -- |_   _|     |  ____|/ ____|                                             -
 * --   | |  _ __ | |__  | (___    Institute of Embedded Systems              -
 * --   | | | '_ \|  __|  \___ \   Zurich University of                       -
 * --  _| |_| | | | |____ ____) |  Applied Sciences                           -
 * -- |_____|_| |_|______|_____/   8401 Winterthur, Switzerland               -
 * ------------------------------------------------------------------------- */
/**
 *  \brief  Interface of module hal_gpio.
 *
 *  The hardware abstraction layer for the GPIO periphery.
 *
 *  \file   hal_gpio.h
 *  $Id: hal_gpio.h 2553 2015-09-22 15:43:30Z stfc $
 * ------------------------------------------------------------------------- */

/* re-definition guard */
#ifndef _HAL_GPIO_H
#define _HAL_GPIO_H

/* user includes */
#include "reg_stm32f4xx.h"
#include "hal_common.h"


/* type definitions -------------------------------------------------------- */

typedef enum {
    HAL_GPIO_MODE_IN = 0x00,      // input
    HAL_GPIO_MODE_OUT = 0x01,     // output
    HAL_GPIO_MODE_AF = 0x02,      // alternate function
    HAL_GPIO_MODE_AN = 0x03       // analog
} hal_gpio_mode_t;

typedef enum {
    HAL_GPIO_OUT_SPEED_2MHZ = 0x00,     // 2 MHz
    HAL_GPIO_OUT_SPEED_10MHZ = 0x01,    // 10 MHz
    HAL_GPIO_OUT_SPEED_50MHZ = 0x02,    // 50 MHz
    HAL_GPIO_OUT_SPEED_100MHZ = 0x03    // 100 MHz
} hal_gpio_out_speed_t;

typedef enum {
    HAL_GPIO_OUT_TYPE_PP = 0x00,      // push-pull
    HAL_GPIO_OUT_TYPE_OD = 0x01       // open-drain
} hal_gpio_out_type_t;

typedef enum {
    HAL_GPIO_PUPD_NOPULL = 0x00,
    HAL_GPIO_PUPD_UP = 0x01,
    HAL_GPIO_PUPD_DOWN = 0x02
} hal_gpio_pupd_t;

typedef enum {
    /* AF0 */
    HAL_GPIO_AF_RTC50HZ = 0x00,
    HAL_GPIO_AF_MCO = 0x00,
    HAL_GPIO_AF_TAMPER = 0x00,
    HAL_GPIO_AF_SWJ = 0x00,
    HAL_GPIO_AF_TRACE = 0x00,
    /* AF 1 */
    HAL_GPIO_AF_TIM1 = 0x01,
    HAL_GPIO_AF_TIM2 = 0x01,
    /* AF 2 */
    HAL_GPIO_AF_TIM3 = 0x02,
    HAL_GPIO_AF_TIM4 = 0x02,
    HAL_GPIO_AF_TIM5 = 0x02,
    /* AF 3 */
    HAL_GPIO_AF_TIM8 = 0x03,
    HAL_GPIO_AF_TIM9 = 0x03,
    HAL_GPIO_AF_TIM10 = 0x03,
    HAL_GPIO_AF_TIM11 = 0x03,
    /* AF 4 */
    HAL_GPIO_AF_I2C1 = 0x04,
    HAL_GPIO_AF_I2C2 = 0x04,
    HAL_GPIO_AF_I2C3 = 0x04,
    /* AF 5 */
    HAL_GPIO_AF_SPI1 = 0x05,
    HAL_GPIO_AF_SPI2 = 0x05,
    HAL_GPIO_AF_SPI4 = 0x05,
    HAL_GPIO_AF_SPI5 = 0x05,
    HAL_GPIO_AF_SPI6 = 0x05,
    /* AF 6 */
    HAL_GPIO_AF_SPI3 = 0x06,
    HAL_GPIO_AF_SAI1 = 0x06,
    /* AF 7 */
    HAL_GPIO_AF_USART1 = 0x07,
    HAL_GPIO_AF_USART2 = 0x07,
    HAL_GPIO_AF_USART3 = 0x07,
    HAL_GPIO_AF_I2S3ext = 0x07,
    /* AF 8 */
    HAL_GPIO_AF_UART4 = 0x08,
    HAL_GPIO_AF_UART5 = 0x08,
    HAL_GPIO_AF_USART6 = 0x08,
    HAL_GPIO_AF_UART7 = 0x08,
    HAL_GPIO_AF_UART8 = 0x08,
    /* AF 9 */
    HAL_GPIO_AF_CAN1 = 0x09,
    HAL_GPIO_AF_CAN2 = 0x09,
    HAL_GPIO_AF_TIM12 = 0x09,
    HAL_GPIO_AF_TIM13 = 0x09,
    HAL_GPIO_AF_TIM14 = 0x09,
    /* AF 10 */
    HAL_GPIO_AF_OTG_FS = 0x0a,
    HAL_GPIO_AF_OTG_HS = 0x0a,
    /* AF 11 */
    HAL_GPIO_AF_ETH = 0x0b,
    /* AF 12 */
    HAL_GPIO_AF_FMC = 0x0c,
    HAL_GPIO_AF_OTG_HS_FS = 0x0c,
    HAL_GPIO_AF_SDIO = 0x0c,
    /* AF 13 */
    HAL_GPIO_AF_DCMI = 0x0d,
    /* AF 14 */
    HAL_GPIO_AF_LTDC = 0x0e,
    /* AF 15 */
    HAL_GPIO_AF_EVENTOUT = 0x0f
} hal_gpio_af_t;

/**
 *  \brief  Initialisation structure for gpio input mode.
 */
typedef struct {
    uint16_t pins;              // the GPIO pins to be configured
    hal_gpio_pupd_t pupd;
} hal_gpio_input_t;

/**
 *  \brief  Initialisation structure for gpio output and af mode.
 */
typedef struct {
    uint16_t pins;              // the GPIO pins to be configured
    hal_gpio_pupd_t pupd;
    hal_gpio_out_speed_t out_speed;
    hal_gpio_out_type_t out_type;
} hal_gpio_output_t;



/* public function declarations -------------------------------------------- */

/**
 *  \brief  Resets gpio port to default values.
 *  \param  port : Defines port to reset.
 */
void hal_gpio_reset(reg_gpio_t *port);

#endif
