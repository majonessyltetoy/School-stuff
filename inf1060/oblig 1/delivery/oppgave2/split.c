#include <stdlib.h>

char **split(char *s) {
	int i = 0; // word counter
	int n = 0; // letter counter
	char c;
	char x = 1; // boolean new word
	char **ss = malloc(sizeof(char *));
	
	while ((c = *s++)) {
		if (c == ' ') {
			if (x) {
				ss = realloc(ss, (8 * (i + 1)) + sizeof(char *));
				ss[i++] = malloc(1);
				ss[i - 1][n++] = '\0';
				n = 0;
			} else {
				x = 1;
				ss[i - 1] = realloc(ss[i - 1], n + 1);
				ss[i - 1][n++] = '\0';
				n = 0;
			}
		} else {
			if (x) {
				x = 0;
				ss = realloc(ss, (8 * (i + 1)) + sizeof(char *));
				ss[i++] = malloc(1);
				ss[i - 1][n++] = c;
			} else {
				ss[i - 1] = realloc(ss[i - 1], n + 1);
				ss[i - 1][n++] = c;
			}
		}
	}
	if (!x) {
		ss[i - 1] = realloc(ss[i - 1], n + 2);
		ss[i - 1][n++] = '\0';
	}
	ss[i] = NULL;
	return ss;
}
