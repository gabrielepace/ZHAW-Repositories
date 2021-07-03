ifneq ($(KERNELRELEASE),)
obj-m	:= ParPortEmul.o
 
else
KDIR	:= /lib/modules/$(shell uname -r)/build
PWD	:= $(shell pwd)

all:
		$(MAKE) -C $(KDIR) M=$(PWD) modules
		@rm -f *.mod.o *.mod.c *.o > /dev/null	

install:
		make all
		mknod /dev/ParPortEmul_lp0 c 126 0
		chmod 0766 /dev/ParPortEmul_lp0

		mknod /dev/ParPortEmul_lp1 c 126 1
		chmod 0766 /dev/ParPortEmul_lp1

		mknod /dev/ParPortEmul_lp2 c 126 2
		chmod 0766 /dev/ParPortEmul_lp2

		insmod ParPortEmul.ko

		make clean

uninstall:
		rmmod -f ParPortEmul
		rm -f  /dev/ParPortEmul_lp0
		rm -f  /dev/ParPortEmul_lp1
		rm -f  /dev/ParPortEmul_lp2
		@rm -f Module.symvers

reinstall:
		make uninstall
		make install

clean:
		@echo cleaning $(PWD)..
		@rm -f *.o core* *.ko *.cmd *. .*.ko .*.cmd *.mod.c > /dev/null
		@rm -fr .tmp_versions > /dev/null	
		@rm -f *.order
endif
