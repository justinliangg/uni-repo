##---------------------------------------------------------------------------
#FILE: Makefile
#AUTHOR: Justin Liang(19821986)
#UNIT: COMP2006
#LAST MOD: 09/05/2022
#PURPOSE: used to compile c files for simulator program.
##---------------------------------------------------------------------------

CC = gcc 
EXEC = simulator
CFLAGS = -Wall -pedantic -Werror -g
OBJ = simulator.o FileIO.o


$(EXEC) : $(OBJ)
	$(CC) $(OBJ) -o $(EXEC) -pthread 

simulator.o : simulator.c simulator.h FileIO.h
	$(CC) $(CFLAGS) -c simulator.c

FileIO.o : FileIO.c FileIO.h
	$(CC) $(CFLAGS) -c FileIO.c

clean : 
	rm -f $(EXEC) $(OBJ)

valgrind: $(EXEC)
	valgrind --leak-check=full --track-origins=yes ./$(EXEC)

helgrind: $(EXEC)
	valgrind --tool=helgrind ./$(EXEC)
