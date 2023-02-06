CC=gcc
CFLAGS=-Wall -Wextra -std=c99 -g

.PHONY: run clean

all: socket server client

socket: socket.c
	$(CC) $(CFLAGS) $^ -c

server: server.c socket
	$(CC) $(CFLAGS) $^ -o $@
	
client: client.c socket
	$(CC) $(CFLAGS) $^ -o $@



clean:
	rm -f hello
