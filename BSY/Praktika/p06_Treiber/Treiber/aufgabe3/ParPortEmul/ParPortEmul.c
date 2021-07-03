/******************************************************************************
*
* Parallel port emulator
* Author:  M. Thaler
* File:    ParPortEmul.c
* Version: 2/2009
* 
*          23.05.2013 M. Thaler
*          - removed: <asm/system.h>, try_get_module() and module_put()
*          18.05.2018 M. Thaler
*          - addded: ioctl  to test if module is correctly load and works
*          08.11.2018 M. Thaler 
*          -  <asm/uaccess.h> -> <linux/uaccess.h>, <asm/io.h> -> <linux/io.h>
******************************************************************************/

#include <linux/module.h>
#include <linux/kernel.h>
#include <linux/init.h>
#include <linux/slab.h>
#include <linux/fs.h>
#include <linux/uaccess.h>
#include <linux/io.h>

MODULE_LICENSE("GPL");

/*--------------------------------------------------------------------------*/
/* export global functions to kernel symbol table                           */

void outbEmul(char data, int addr);
char inbEmul(int addr);

EXPORT_SYMBOL_GPL(outbEmul);
EXPORT_SYMBOL_GPL(inbEmul);

/*--------------------------------------------------------------------------*/
/* global variables */

static char dataPort[3] = {0,0,0};            /* output register            */
static char statPort[3] = {0,0,0};            /* status register            */
static char cntlPort[3] = {0,0,0};            /* control register           */

static int  use_count[3] = {0,0,0};

/*--------------------------------------------------------------------------*/

#define THIS_MAJOR 126        /* experimental THIS_MAJOR: 120 - 127         */

int     ParPortEmulOpen(struct inode*, struct file*);
int     ParPortEmulRelease(struct inode*, struct file*);
ssize_t ParPortEmulRead(struct file *filp, char *buf, 
                                        size_t count, loff_t *anything);
ssize_t ParPortEmulWrite(struct file *filp, const char *buf, 
                                        size_t count, loff_t *anything);
long ParPortEmulIoctl(struct file *filp, unsigned int cmd, unsigned long arg);

/*--------------------------------------------------------------------------*/
/* using struct intializer (C99 and GNU) -> portable */

struct file_operations ParPortEmulFops = {
  .owner    = THIS_MODULE,
  .open     = ParPortEmulOpen,
  .release  = ParPortEmulRelease,
  .read     = ParPortEmulRead,
  .write    = ParPortEmulWrite,
  .unlocked_ioctl = ParPortEmulIoctl,
};

/*--------------------------------------------------------------------------*/
/* retrieve minor number from file                                          */

int getMINOR(struct file *fp) {
    int    minor;
    struct inode *inodePtr;
    inodePtr = fp->f_path.dentry->d_inode;
    minor = MINOR(inodePtr->i_rdev);
    return(minor);
}

/*--------------------------------------------------------------------------*/
/* initialize and register module                                           */

int init_module(void) {
    int res;
    use_count[0] = use_count[1] = use_count[2] = 0;
    res = register_chrdev(THIS_MAJOR, "ParPortEmulModul", &ParPortEmulFops);
    if (res < 0)
        printk(KERN_ALERT "cannot register parallel port emulator");
    else
        printk(KERN_ALERT "parallel port emulator loaded\n");
    return(res);
}

void cleanup_module(void) {
    unregister_chrdev(THIS_MAJOR, "ParPortEmulModul");
    printk(KERN_ALERT "parallel port emulator successfully unloaded\n");
}

/*--------------------------------------------------------------------------*/
/* open()                                                                   */

int ParPortEmulOpen(struct inode *inode, struct file *filp) {

    int minor;

    minor = getMINOR(filp);

    if ((minor < 0) || (minor > 2))
        return(-1);
    
    if (use_count[minor] != 0)           /* check if not already in use     */
        return(-EBUSY);                  /* if yes return with error        */
    else
        use_count[minor]++;
    return(0);
}

/*--------------------------------------------------------------------------*/
/* close()                                                                  */

int ParPortEmulRelease(struct inode *inode, struct file *filp) {
    int minor;
    minor = getMINOR(filp);
    if (use_count[minor] > 0) {
        use_count[minor]--;
    }
    return(0);
}

/*--------------------------------------------------------------------------*/
/* read()                                                                   */

ssize_t ParPortEmulRead(struct file *filp, char *buf, 
                                            size_t count, loff_t *offset) {
    int  minor;
    long rv;
    size_t lcount;
    char test[2];
    minor = getMINOR(filp);
    test[0] = (char)dataPort[minor];
    test[1] = (char)cntlPort[minor];
    lcount = 2;
    rv = copy_to_user(buf, test, lcount);
    return(lcount);
}


/*--------------------------------------------------------------------------*/
/* write()                                                                  */

ssize_t ParPortEmulWrite(struct file *filp, const char *buf, 
                                            size_t count, loff_t *offset) {
    int  minor;
    long rv;
    char test[1];
    minor = getMINOR(filp);
    rv = copy_from_user(test, buf, count);
    statPort[minor] = (char)test[0]^0x80;            /* invert MSB */
    return(count);
}

/*--------------------------------------------------------------------------*/
/* ioctl()                                                                  */

long ParPortEmulIoctl(struct file *filp, unsigned int cmd, 
                                                        unsigned long arg) {
    switch (cmd) {
        case 10:    dataPort[0] = statPort[0]^0x80;
                    dataPort[1] = statPort[1]^0x80;
                    dataPort[2] = statPort[2]^0x80;
                    break;
        case 11:    dataPort[0] = dataPort[1] = dataPort[2] = (char)cmd;
                    break;
        case 12:    dataPort[0] = dataPort[1] = dataPort[2] = (char)cmd;
                    cntlPort[0] = cntlPort[1] = cntlPort[2] = (char)cmd;
        default:    break;
    }
    
    return 0;
}

/*--------------------------------------------------------------------------*/
/* emulate outb() resp. outb_p() @ lp0, lp1 and lp2                         */

void outbEmul(char data, int addr) {
    switch (addr) {
        case 0x3BC:    dataPort[0] = data;
                    break;
        case 0x3BE:    cntlPort[0] = (data ^ 0x0B) & 0x0F; 
                    break;
        case 0x378:    dataPort[1] = data;
                    break;
        case 0x37A:    cntlPort[1] = (data ^ 0x0B) & 0x0F; 
                    break;
        case 0x278:    dataPort[2] = data;
                    break;
        case 0x27A:    cntlPort[2] = (data ^ 0x0B) & 0x0F; 
                    break;
        default:    break;
    }
}
    
/*--------------------------------------------------------------------------*/
/* emulate inb() resp. inb_p() @ lp0, lp1 and lp2                           */

char inbEmul(int addr) {
    char data = 0;
    switch (addr) {
        case 0x3BD:    data = statPort[0];
                    break;
        case 0x379:    data = statPort[1];
                    break;
        case 0x279:    data = statPort[2];
                    break;
        default:    break;
    }
    return(data);        
}

/*--------------------------------------------------------------------------*/
