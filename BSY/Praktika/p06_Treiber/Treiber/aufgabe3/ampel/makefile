# Author:   M. Thaler
# Version:  v.fs20

CMP=		gcc
CMPFLAGS=       -std=gnu99
EXENAM=		ampelSim
LIBNAME=

compile:	$(EXENAM)

$(EXENAM):	$(EXENAM).o
		$(CMP)  $(CMPFLAGS) $(EXENAM).o -o $@.e

.c.o:
		$(CMP) -c $(CMPFLAGS) $<

clean:
		rm -f *.o 
		rm -f *.e

all:
		touch *.c*
		make
