CC=gcc
CFLAGS=-Wall -Wextra -std=c99 -g

.PHONY: run clean

hello: hello.c
	$(CC) $(CFLAGS) $^ -o $@

run: hello
	./hello

clean:
	rm -f hello
