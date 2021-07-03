/******************************************************************************
* File:     ParaModule.c
* Revision: 27.08.2004 tham: Kernel 2.6
*           10.02.2008 tham: changed to module_param
*           14.03.2016 tham: printk("<0>..") to printk(KERN_ALERT "...)
* Version:  v.fs20
******************************************************************************/

#include <linux/module.h>
#include <linux/moduleparam.h>

MODULE_LICENSE("GPL");

int globalInt    = 10;
char* globalChar = "default text";

module_param(globalInt,  int,   S_IRUGO);
module_param(globalChar, charp, S_IRUGO);

int init_module(void) {
    printk(KERN_ALERT "*** ParaModule: %d - %s ***\n", globalInt, globalChar);
	return(0);
}

void cleanup_module(void) {
    printk(KERN_ALERT "*** Good Bye from ParaModule ***\n");
}

/******************************************************************************/
