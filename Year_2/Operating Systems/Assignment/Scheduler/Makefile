##---------------------------------------------------------------------------
#FILE: Makefile
#AUTHOR: Justin Liang(19821986)
#UNIT: COMP2006
#LAST MOD: 09/05/2022
#PURPOSE: used to compile c files for scheduler program.
##---------------------------------------------------------------------------

CC = gcc 
EXEC = scheduler
CFLAGS = -Wall -pedantic -Werror 
OBJ = scheduler.o FileIO.o


$(EXEC) : $(OBJ)
	$(CC) $(OBJ) -o $(EXEC) 

scheduler.o : scheduler.c scheduler.h FileIO.h
	$(CC) $(CFLAGS) -c scheduler.c

FileIO.o : FileIO.c FileIO.h
	$(CC) $(CFLAGS) -c FileIO.c

clean : 
	rm -f $(EXEC) $(OBJ)



