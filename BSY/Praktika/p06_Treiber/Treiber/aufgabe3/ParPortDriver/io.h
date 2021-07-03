#ifndef REDEFINITION_IO_OPERATIONS
#define REDEFINITION_IO_OPERATIONS

/************************************************************************
* Author:   M. Thaler 
* Purpose:  2010/2014 redefine in()/out() functions to emulated versions 
* Version:  v.fs20
***********************************************************************<*/

extern void outbEmul(char byte, int addr);
extern char inbEmul(int addr);

// tha, 11/2018: need to undef to avoid warnings
#undef inb_p
#undef outb_p
#undef inb
#undef outb

#define inb_p inb
#define outb_p outb
#define inb(x) inbEmul(x)
#define outb(x,y) outbEmul(x,y)

/* ignore the following functions */

#define request_region(x, y, z) (struct resource *)1
#define check_region(x, y, z)   (struct resource *)1
#define release_region(x, y)    {}


#endif
