C = gcc
EXEC = main
CFLAGS = -Wall -ansi -pedantic -Werror
OBJ = Main.o Functions.o

$(EXEC) : $(OBJ)
	$(C) $(OBJ) -o $(EXEC)

Main.o : Main.c Main.h Functions.h
	$(C) -g $(CFLAGS) -c Main.c

Functions.o : Functions.c Functions.h
	$(C) -g $(CFLAGS) -c Functions.c

clean : 
	rm -f $(EXEC) $(OBJ)

valgrind : $(EXEC) 
	valgrind --leak-check=full --track-origins=yes ./$(EXEC)
