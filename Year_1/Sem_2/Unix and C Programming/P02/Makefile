
CC  = gcc
EXEC = main
CFLAGS = -Wall -ansi -pedantic
OBJ = bounds.o powers.o

ifdef DEBUG
CFLAGS += -D DEBUG
DEBUG : clean $(EXEC)
endif


$(EXEC) : $(OBJ)
	$(CC) $(OBJ) -o $(EXEC)

bounds.o : bounds.c bounds.h powers.h macros.h
	$(CC) $(CFLAGS) -c bounds.c

powers.o : powers.c powers.h
	$(CC) $(CFLAGS) -c powers.c


clean:
	rm -f $(EXEC) $(OBJ)
