void stringsum2(char *s, int *res) {
	*res = 0;
	char c;
	
	while ((c = *s++)) {
		if (c >= 'a' && c <= 'z')
			*res += c - 'a' + 1;
		else if (c >= 'A' && c <= 'Z')
			*res += c - 'A' + 1;
		else {
			*res = -1;
			break;
		}
	}
}
