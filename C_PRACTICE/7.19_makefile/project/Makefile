
O_NAME := app
OBJS = main.o hello.o world.o
CC = gcc
CFLAGS = -o -c


test: $(OBJS)
	$(CC) $(OBJS) -o $(O_NAME)

main.o: main.c hello.h world.h
	$(CC) -c main.c -o main.o
hello.o: hello.c
	$(CC) -c hello.c -o hello.o
world.o: world.c
	$(CC) -c world.c -o world.o

clean:
	rm *o $(O_NAME)
