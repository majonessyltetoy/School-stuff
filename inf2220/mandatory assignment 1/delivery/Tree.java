import java.util.*;

class Tree {
	private Node root; // root node
	private long lookUpCounter;
	
	// Constructor
	public Tree() {
		root = null;
	}
	
	public boolean insert(String element) {
		if (root == null) {
			root = new Node(element, null, null);
			return true;
		}
		else {
			if (!search(element)) {
				root.insert(element);
				return true;
			}
			return false;
		}
	}
	
	public boolean search(String word) {
		lookUpCounter++; // count lookups
		if (root == null) {
			return false;
		}
		return root.search(word);
	}
	public long getLookUps() {
		return lookUpCounter;
	}
	
	public ArrayList<String> suggest(String word) {
		lookUpCounter = 0;
		ArrayList<String> arr = new ArrayList<String>();
		ArrayList<String> lookedUp = new ArrayList<String>();
		for (String s : similarOne(word)) {
			if (!lookedUp.contains(s) && search(s)) {
				arr.add(s);
			}
			lookedUp.add(s);
		}
		for (String s : similarTwo(word)) {
			if (!lookedUp.contains(s) && search(s)) {
				arr.add(s);
			}
			lookedUp.add(s);
		}
		for (String s : similarThree(word)) {
			if (!lookedUp.contains(s) && search(s)) {
				arr.add(s);
			}
			lookedUp.add(s);
		}
		for (String s : similarFour(word)) {
			if (!lookedUp.contains(s) && search(s)) {
				arr.add(s);
			}
			lookedUp.add(s);
		}
		return arr;
	}
	
	public boolean remove(String word) {
		if (depth() == 0 && word.compareTo(root.containingWord()) == 0) {
			root = null;
			return true;
		}
		else {
			if (search(word)) {
				root.remove(word, null);
				return true;
			}
			return false;
		}
	}
	
	/*
	 * Find the depth of all the nodes and stores them in the array list that
	 * is provided as a parameter
	 */
	public ArrayList<Integer> depthAllNodes() {
		return root.depthAllNodes(0, new ArrayList<Integer>());
	}
	
	public int depth() {
		return (int)Collections.max(depthAllNodes());
	}
	
	public int nodesInDepth(int n) {
		return root.inDepth(n, 0);
	}
	
	private int listSum(ArrayList<Integer> arr) {
		int sum = 0;
		for (int n : arr) {
			sum += n;
		}
		return sum;
	}
	
	public int depthAvg() {
		ArrayList<Integer> arr = depthAllNodes();
		return (int)Math.round((double)listSum(arr)/arr.size());
	}

	public String firstWord() {
		return root.firstAlphabetically();
	}
	
	public Node getRoot() {
		return root;
	}
	
	public String lastWord() {
		return root.lastAlphabetically();
	}
	
	public String[] similarOne(String word) {
		char[] word_array = word.toCharArray();
		char[] tmp;	
		String[] words = new String[word_array.length-1];
		for(int i = 0; i < word_array.length - 1; i++){	
			tmp = word_array.clone();
			words[i] = swap(i, i+1, tmp);
		}
		return words;
	}
	
	public String swap(int a, int b, char[] word){
		char tmp = word[a];
		word[a] = word[b];
		word[b] = tmp;
		return new String(word);
	}
	
	public ArrayList<String> similarTwo(String word) {
		char[] word_array = word.toCharArray();
		char[] alphabet = "abcdefghijklmnopqrstuvwxyz".toCharArray();
		char[] tmp;
		ArrayList<String> arr = new ArrayList<String>();
		for (int i=0; i<word_array.length; i++) {
			for (int n=0; n<alphabet.length; n++) {
				tmp = word_array.clone();
				tmp[i] = alphabet[n];
				arr.add(String.valueOf(tmp));
			}
		}
		return arr;
	}
	
	public ArrayList<String> similarThree(String word) {
		char[] word_array = word.toCharArray();
		char[] alphabet = "abcdefghijklmnopqrstuvwxyz".toCharArray();
		char[] tmp;
		ArrayList<String> arr = new ArrayList<String>();
		for (int i=0; i<word_array.length+1; i++) {
			tmp = charArrEmptyAt(word_array, i);
			for (int n=0; n<alphabet.length; n++) {
				tmp[i] = alphabet[n];
				arr.add(String.valueOf(tmp));
			}
		}
		return arr;
	}
	
	private char[] charArrEmptyAt(char[] arr, int n) {
		char[] tmp = new char[arr.length + 1];
		int count = 0;
		
		for (int i=0; i<tmp.length; i++) {
			if (i != n) {
				tmp[i] = arr[count++];
			}
		}
		return tmp;
	}
	
	public ArrayList<String> similarFour(String word) {
		char[] word_array = word.toCharArray();
		char[] tmp = new char[word_array.length - 1];
		ArrayList<String> arr = new ArrayList<String>();
		int count;
		for (int i=0; i<word_array.length; i++) {
			count = 0;
			for (int n=0; n<word_array.length; n++) {
				if (n != i) {
					tmp[count++] = word_array[n];
				}
			}
			arr.add(String.valueOf(tmp));
		}
		return arr;
	}
}
