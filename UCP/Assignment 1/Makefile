##---------------------------------------------------------------------------
#FILE: Makefile
#AUTHOR: Justin Liang(19821986)
#UNIT: COMP1000
#LAST MOD: 10/09/2021
#PURPOSE: used to compile c files for the maze program.
##---------------------------------------------------------------------------

CC = gcc
EXEC = maze
CFLAGS = -Wall -ansi -pedantic 
OBJ = main.o game.o map.o generatemap.o movement.o terminal.o display.o

ifdef DARK
CFLAGS += -D DARK
DARK : clean $(EXEC)
endif

$(EXEC) : $(OBJ)
	$(CC) $(OBJ) -o $(EXEC)

main.o : main.c main.h game.h
	$(CC) $(CFLAGS) -c main.c

game.o : game.c game.h map.h generatemap.h movement.h terminal.h display.h
	$(CC) $(CFLAGS) -c game.c

map.o : map.c map.h
	$(CC) $(CFLAGS) -c map.c

generatemap.o : generatemap.c generatemap.h
	$(CC) $(CFLAGS) -c generatemap.c

movement.o : movement.c movement.h 
	$(CC) $(CFLAGS) -c movement.c

terminal.o : terminal.c terminal.h
	$(CC) $(CFLAGS) -c terminal.c

display.o : display.c display.h
	$(CC) $(CFLAGS) -c display.c

clean : 
	rm -f $(EXEC) $(OBJ)

