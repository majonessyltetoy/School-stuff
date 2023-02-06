CC=gcc
CFLAGS=-Wall -Wextra -std=c99 -g

.PHONY: all run clean

all: oblig1_tests stringsum distance_between string_between split stringsum2

oblig1_tests: oblig1_tests.c stringsum distance_between string_between split stringsum2
	$(CC) $(CFLAGS) $< stringsum.o distance_between.o string_between.o split.o stringsum2.o -o oblig1_tests

stringsum: stringsum.c
	$(CC) $(CFLAGS) $^ -c
	
distance_between: distance_between.c
	$(CC) $(CFLAGS) $^ -c

string_between: string_between.c
	$(CC) $(CFLAGS) $^ -c

split: split.c
	$(CC) $(CFLAGS) $^ -c

stringsum2: stringsum2.c
	$(CC) $(CFLAGS) $^ -c

run: all
	./oblig1_tests

clean:
	rm -f oblig1_tests stringsum distance_between string_between split stringsum2 *.o
