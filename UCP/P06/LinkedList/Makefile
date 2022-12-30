C = gcc
EXEC = LinkedListTest
CFLAGS = -Wall -ansi -pedantic -Werror
OBJ = LinkedListTest.o LinkedList.o

$(EXEC) : $(OBJ)
	$(C) $(OBJ) -o $(EXEC)

LinkedListTest.o : LinkedListTest.c LinkedList.h
	$(C) -g $(CFLAGS) -c LinkedListTest.c

LinkedList.o : LinkedList.c LinkedList.h
	$(C) -g $(CFLAGS) -c LinkedList.c

clean : 
	rm -f $(EXEC) $(OBJ)

valgrind : $(EXEC) 
	valgrind --leak-check=full --track-origins=yes -s ./$(EXEC)
