# Author    M. Thaler
# Version   v.fs20

ifneq ($(KERNELRELEASE),)
obj-m 	:= ParDriver.o

else
KDIR	:= /lib/modules/$(shell uname -r)/build
PWD	:= $(shell pwd)

all:
	cp ../ParPortEmul/Module.symvers .
	$(MAKE) -C $(KDIR) M=$(PWD) modules
	@rm -f *.mod.o *.mod.c *.o > /dev/null	

install:
	mknod /dev/ParDev c 121 0
	chmod 0766 /dev/ParDev
	insmod ParDriver.ko

uninstall:
	rm -f /dev/ParDev
	rmmod -f ParDriver
	
reinstall:
	make uninstall
	make install

clean:
	@echo cleaning $(PWD)..
	@rm -f *.o core* *.ko *.cmd *. .*.ko .*.cmd *.mod.c > /dev/null
	@rm -f Module.symvers
	@rm -fr .tmp_versions > /dev/null	
	@rm -f *.symvers *.order
endif
