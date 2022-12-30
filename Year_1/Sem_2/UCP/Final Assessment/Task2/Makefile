##---------------------------------------------------------------------------
#FILE: Makefile
#AUTHOR: Justin Liang(19821986)
#UNIT: COMP1000
#LAST MOD: 07/11/2021
#PURPOSE: used to compile c files for the calculator program.
##---------------------------------------------------------------------------

CC = gcc 
EXEC = calculator
CFLAGS = -Wall -ansi -pedantic -Werror
OBJ = main.o calculator.o keypad.o display.o selector.o LinkedList.o terminal.o


$(EXEC) : $(OBJ)
	$(CC) $(OBJ) -o $(EXEC) 


main.o : main.c main.h 
	$(CC) $(CFLAGS) -c main.c

calculator.o : calculator.c calculator.h keypad.h display.h selector.h LinkedList.h terminal.h 
	$(CC) $(CFLAGS) -c calculator.c

keypad.o : keypad.c keypad.h 
	$(CC) $(CFLAGS) -c keypad.c

display.o : display.c display.h keypad.h
	$(CC) $(CFLAGS) -c display.c 

selector.o : selector.c selector.h keypad.h LinkedList.h
	$(CC) $(CFLAGS) -c selector.c 

terminal.o : terminal.c terminal.h
	$(CC) $(CFLAGS) -c terminal.c

LinkedList.o : LinkedList.c LinkedList.h
	$(CC) $(CFLAGS) -c LinkedList.c


clean : 
	rm -f $(EXEC) $(OBJ)

