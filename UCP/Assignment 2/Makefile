##---------------------------------------------------------------------------
#FILE: Makefile
#AUTHOR: Justin Liang(19821986)
#UNIT: COMP1000
#LAST MOD: 09/10/2021
#PURPOSE: used to compile c files for the maze program.
#COMMENTS: linking stage requires -lm for Math.h 
##---------------------------------------------------------------------------

CC = gcc 
EXEC = maze
CFLAGS = -Wall -ansi -pedantic -Werror 
OBJ = main.o game.o map.o movement.o free.o terminal.o LinkedList.o


$(EXEC) : $(OBJ)
	$(CC) $(OBJ) -o $(EXEC) -lm

main.o : main.c main.h game.h
	$(CC) $(CFLAGS) -c main.c

game.o : game.c game.h map.h movement.h free.h terminal.h LinkedList.h
	$(CC) $(CFLAGS) -c game.c

map.o : map.c map.h
	$(CC) $(CFLAGS) -c map.c

movement.o : movement.c movement.h map.h LinkedList.h
	$(CC) $(CFLAGS) -c movement.c

free.o : free.c free.h map.h LinkedList.h
	$(CC) $(CFLAGS) -c free.c

terminal.o : terminal.c terminal.h
	$(CC) $(CFLAGS) -c terminal.c

LinkedList.o : LinkedList.c LinkedList.h
	$(CC) $(CFLAGS) -c LinkedList.c


clean : 
	rm -f $(EXEC) $(OBJ)

