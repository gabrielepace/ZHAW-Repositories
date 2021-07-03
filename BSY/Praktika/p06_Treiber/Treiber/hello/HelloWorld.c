/******************************************************************************
* Title:    Hello World Module 
* File:     HelloWorld.c
* Revision: 27.8.2004, tham: kernel 2.6
*           20.1.2012, tham: autoconf.h removed
*           14.03.2016 tham: printk("<0>..") to printk(KERN_ALERT "...)
* Version:  v.fs20
******************************************************************************/

#include <linux/kernel.h>
#include <linux/module.h>

MODULE_LICENSE("GPL");

int init_module(void) {
    printk(KERN_ALERT "*** Hello World from Module ***\n");
	return(0);
}

void cleanup_module(void) {
    printk(KERN_ALERT "*** Good Bye from Module ***\n");
}

/*****************************************************************************/
