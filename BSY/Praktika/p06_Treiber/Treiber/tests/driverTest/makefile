# Author:   M. Thaler
# Version:  v.fs20

CMP=		gcc
CMPFLAGS=       -std=gnu99
EXENAM=		driverTest
LIBNAME=

compile:	$(EXENAM)

$(EXENAM):	$(EXENAM).o
		$(CMP)  $(CMPFLAG) $(EXENAM).o -o $@.e

.c.o:
		$(CMP) -c $(CMPFLAGS) $<

clean:
		rm -f *.o *.e

all:
		touch *.c*
		make
