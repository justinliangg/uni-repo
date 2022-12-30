C = gcc
EXEC = readjournal
CFLAGS = -Wall -ansi -pedantic -Werror
OBJ = readjournal.o LinkedList.o

$(EXEC) : $(OBJ)
	$(C) $(OBJ) -o $(EXEC)

readjournal.o : readjournal.c readjournal.h LinkedList.h
	$(C) -g $(CFLAGS) -c readjournal.c

LinkedList.o : LinkedList.c LinkedList.h
	$(C) -g $(CFLAGS) -c LinkedList.c

clean : 
	rm -f $(EXEC) $(OBJ)

valgrind : $(EXEC) 
	valgrind --leak-check=full --track-origins=yes -s ./$(EXEC) journal 3
