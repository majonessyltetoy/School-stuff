int distance_between(char *s, char c) {
	int total = 0;
	char x = 0; // boolean if we found first instance of c
	
	while (*s++) {
		if (x)
			total++;
		
		if (*(s - 1) == c) {
			if (x)
				return total;
			x = 1;
		}
	}
	return -1;
}
