C = gcc
CFLAGS = -Wall -ansi -pedantic -Werror -g

top : display generate

display : display.o plot.o
	$(C) display.o plot.o -o display

generate : generate.o randomarray.o
	$(C) generate.o randomarray.o -o generate

display.o : display.c display.h plot.h
	$(C) -g $(CFLAGS) -c display.c

plot.o : plot.c plot.h
	$(C) -g $(CFLAGS) -c plot.c

generate.o : generate.c generate.h randomarray.h
	$(C) -g $(CFLAGS) -c generate.c

randomarray.o : randomarray.c randomarray.h
	$(C) -g $(CFLAGS) -c randomarray.c

clean : 
	rm -f display.o plot.o generate.o randomarray.o generate display 

