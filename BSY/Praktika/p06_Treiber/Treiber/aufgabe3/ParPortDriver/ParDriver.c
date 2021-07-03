/**************************************************************************
* Parport Device Driver
* 
* Author:		M. Thaler, A. Schmid
* File:			ParDriver.c
* Grundversion:	17.9.1999	A. Schmid
* Erweiterung, 
* Anpassungen:	25.11.2001	M. Thaler
*				27.08.2004	M. Thaler	-->> Kernel 2.6
*				- slab.h
*				- use_count: global variable
*				- include files adapated
*               18.02.2009  M. Thaler
*               - include "io.h" for par port emulation
*               20.01.2011  M. Thaler
*               - removed autoconf.h
*               23.05.20013 M. Thaler
*                - removed: <asm/system.h>, try_get_module() and module_put()
*               14.03.2016  M. Thaler
*                - printk(KERN_ALERT "..") to printk(KERN_ALERT "...)
*               05.05.2018  M. Thaler: new -> #include <linux/uaccess.h>
*               08.11.2018 M. Thaler 
*                - <asm/io.h> -> <linux/io.h>
* Version:      v.fs20
***************************************************************************/

#include <linux/module.h>
#include <linux/kernel.h>
#include <linux/init.h>
#include <linux/slab.h>
#include <linux/fs.h>
#include <linux/uaccess.h>

//#include <linux/io.h>     // uncomment, when using with parallel port
#include "io.h"           // comment to use with parallel port emulation

MODULE_LICENSE("GPL");

/*---------------------------------------------------------------------------*/
int use_count;
/*---------------------------------------------------------------------------*/

#define BASE_ADDR 0x378		/* IO base addr of lp1 */

#define MY_MAJOR 121		/* experimental MAJOR: 120 - 127*/

int 	ParOpen(struct inode*, struct file*);
int 	ParRelease(struct inode*, struct file*);
ssize_t ParRead(struct file *filp, char *buf, size_t count, loff_t *anything);
ssize_t ParWrite(struct file *filp, const char *buf, 
                                            size_t count, loff_t *anything);

/*---------------------------------------------------------------------------*/

/* using the tagged method -> portable */

struct file_operations ParFops = {
  .open=	ParOpen,
  .release=	ParRelease,
  .read=	ParRead,
  .write=	ParWrite,
};

/*---------------------------------------------------------------------------*/

int init_module(void){
  int res;
  printk(KERN_ALERT "Try to Register the Driver\n");
  use_count = 0;
  res = register_chrdev(MY_MAJOR, "ParDriver", &ParFops);
  if (res < 0)
    printk(KERN_ALERT "cannot register Driver");
  return (res);
}

void cleanup_module(void){
  printk(KERN_ALERT "Close Driver\n");
  unregister_chrdev(MY_MAJOR, "ParDriver");
}

/*---------------------------------------------------------------------------*/

int ParOpen(struct inode *inode, struct file *filp){
  printk(KERN_ALERT "we are in the OPEN\n");
  if (use_count != 0) /* check if not already in use */
    return (-EBUSY);   /* if yes return with error    */
  else
    use_count++;
  return (0);
}

int ParRelease(struct inode *inode, struct file *filp){
  printk(KERN_ALERT "we are in RELEASE\n");
  use_count--;
  return (0);
}

ssize_t ParRead(struct file *filp, char *buf, size_t count, loff_t *offset){
  char byte;
  printk(KERN_ALERT "we are in the READ\n");
  byte = inb(BASE_ADDR + 1);
  if (copy_to_user(buf, &byte, count) != 0)
    count = -1;
  return (count);
}

ssize_t ParWrite(struct file *filp, const char *buf,
                 size_t count, loff_t *offset){
  char byte;
  printk(KERN_ALERT "we are in the WRITE");
  if (copy_from_user(&byte, buf, count) != 0)
    count = -1;
  outb(byte, BASE_ADDR);
  return (count);
}

/*****************************************************************************/
