# Kernel  Module Compilateion
# Module  BSy, M. Thaler, InIT/ZHAW, 2010, 2014
# Version v.fs20

KDIR	= /lib/modules/$(shell uname -r)/build
PWD	= $(shell pwd)
obj-m 	+= MemDriver.o

all:
	make -C $(KDIR) M=$(PWD) modules
	@rm -f *.mod.o *.mod.c *.o > /dev/null	
clean:
	@echo cleaning $(PWD)..
	@rm -f *.o core* *.ko *.cmd *. .*.ko .*.cmd *.mod.c > /dev/null
	@rm -fr .tmp_versions > /dev/null	
	@rm -f *.symvers *.order
#endif
