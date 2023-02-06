CC=gcc
CFLAGS=-Wall -Wextra -std=c99 -g

.PHONY: run clean

struct: struct.c
	$(CC) $(CFLAGS) $^ -o $@

run: struct
	./struct

clean:
	rm -f struct
