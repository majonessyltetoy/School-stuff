import java.util.HashMap;
import java.util.Arrays;

class BMH {

	final int CHAR_MAX = 256;
	
	/* Find needle in haystack with a Boyer-Moore-Horspool wildcard variant.
	 * If c is true then bad_shift is 1 for all characters, this is for
	 * comfirming that the algorithm indeed found all the matches.
	 *
	 * Parameters:
	 * String ne  -  needle we're looking for
	 * String ha  -  haystack we're searching through
	 * boolean c  -  debug mode flag
	 *
	 * Return:
	 * HashMap<Integer,String> where integer is index and string is the needle
	 * matched in the haystack.
	 */
	public HashMap<Integer,String> find(String ne, String ha, boolean c) {
		char[] needle = ne.toCharArray();
		char[] haystack = ha.toCharArray();
		HashMap<Integer,String> m = new HashMap<Integer,String>();
		
		if (needle.length > haystack.length) { return m; }
		int first_wildcard = (c)?1:needle.length;
		int last_wildcard = needle.length;
		int[] bad_shift = new int[CHAR_MAX]; // 256
		
		//find first wildcard
		for (int i=0; i<needle.length && !c; i++) {
			if (needle[i] == '_') {
				first_wildcard = (i==0)?1:i;
				break;
			}
		}
		
		// find last wildcard
		int count = 1;
		for (int i=needle.length-1; i>0; i--) {
			if (needle[i] == '_') {
				last_wildcard = count;
				break;
			}
			count++;
		}
		
		// assign default bad-shift value
		for (int i=0; i<CHAR_MAX; i++) {
			bad_shift[i] = (first_wildcard<last_wildcard)?first_wildcard:last_wildcard;
		}
		
		int offset = 0, scan = 0;
		int last = needle.length - 1;
		int maxoffset = haystack.length - needle.length;
		for (int i=0; i<last && !c; i++){
			
			bad_shift[needle[i]] = ((first_wildcard+1)<(last-i))?first_wildcard:(last-i);
		}
		while (offset<=maxoffset) {
			for (scan=last; needle[scan]==haystack[scan+offset] || needle[scan]=='_'; scan--) {
				if (scan == 0){ // match found!
					m.put(offset, String.valueOf(Arrays.copyOfRange(haystack, offset, (offset + needle.length))));
					break;
				}
			}
			offset += bad_shift[haystack[offset+last]];
		}
		return m;
	}
}
