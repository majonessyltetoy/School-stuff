int stringsum(char *s) {
	int total = 0;
	char c;
	
	while ((c = *s++)) {
		if (c >= 'a' && c <= 'z')
			total += c - 'a' + 1;
		else if (c >= 'A' && c <= 'Z')
			total += c - 'A' + 1;
		else
			return -1;
	}
	return total;
}
