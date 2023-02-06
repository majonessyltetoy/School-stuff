#include <stdlib.h>

int distance_between(char *s, char c);

char *string_between(char *s, char c) {
	char y = 0; // boolean to stop while loop
	int n = distance_between(s, c);
	
	if (n == -1)
		return NULL;
	
	char *ss = malloc(n);
	
	while (*s++) {
		if (y && *(s - 1) != c) {
			*(ss++) = *(s - 1);
		}
		if (*(s - 1) == c) {
			if (y) {
				*ss = '\0';
				return ss - n + 1;
			}
			y = 1;
		}
	}
	return NULL; // this will never be reached
}
