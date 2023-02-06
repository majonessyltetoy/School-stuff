#include <stdio.h>

/*
 * A simple program that print a commandline argument.
 * This crashes when there are no argument, but exceptions are outside the spec.
 */
int main(int argc, char *msg[]) {
	argc = argc; // stop gcc complaining
    printf("%s\n", msg[1]);
    return 0;
}
