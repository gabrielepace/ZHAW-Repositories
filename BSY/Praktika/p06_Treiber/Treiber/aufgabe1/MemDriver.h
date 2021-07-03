/******************************************************************************
* Memory Device Driver
* 
* Author:       M. Thaler,  A. Schmid
* File:         MemDriver.h
* Grundversion: 17.9.1999   A. Schmid
* Erweiterung, 
* Anpassungen:  25.11.2001  M. Thaler
*               14.02.2010  M. Thaler
*               20.01.2011  M. Thaler
*               23.05.2013  M. Thaler
*               20.05.2016  M. Thaler
* Version:      v.fs20
******************************************************************************/

#define MY_MAJOR 120        /* experimental MAJOR: 120 - 127*/

typedef struct {
    int  length;
    void *buffer;
} dbuf_t;


int     MemOpen(struct inode*, struct file*);
int     MemRelease(struct inode*, struct file*);
ssize_t MemRead(struct file *filp, char *buf, size_t count, loff_t *anything);
ssize_t MemWrite(struct file *filp, const char *buf, 
                                    size_t count, loff_t *anything);

/*****************************************************************************/
