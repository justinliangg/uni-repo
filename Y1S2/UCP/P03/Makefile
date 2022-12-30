
C = gcc
EXEC = main
CFLAGS = -Wall -ansi -pedantic
OBJ = user_input.o order.o numbers.o

ifdef DEBUG
CFLAGS += -D DEBUG
DEBUG : clean $(EXEC)
endif


$(EXEC) : $(OBJ)
	$(CC) $(OBJ) -o $(EXEC)

numbers.o : numbers.c numbers.h order.h user_input.h
	$(CC) $(CFLAGS) -c numbers.c

user_input.o: user_input.c user_input.h
	$(CC) $(CFLAGS) -c user_input.c

order.o : order.c order.h
	$(CC) $(CFLAGS) -c order.c

clean:
	rm -f $(EXEC) $(OBJ)
