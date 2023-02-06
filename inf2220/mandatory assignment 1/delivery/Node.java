import java.util.*;

class Node {

	private String containing;
	public Node left;
	public Node right;
	
	// Constructor
	public Node(String containingElement, Node l, Node r) {
		containing = containingElement;
		left = l;
		right = r;
	}
	
	public String containingWord() {
		return containing;
	}
	
	public void insert(String element) {
		if (containing.compareTo(element) > 0) {
			if (left == null) {
				left = new Node(element, null, null);
			}
			else {
				left.insert(element);
			}
		}
		else {
			if (right == null) {
				right = new Node(element, null, null);
			}
			else {
				right.insert(element);
			}
		}
	}
	
	public boolean search(String word) {
		if (containing.compareTo(word) == 0) {
			return true;
		}
		
		else if (containing.compareTo(word) > 0 && left != null) {
			return left.search(word);
		}
		else if (containing.compareTo(word) < 0 && right != null) {
			return right.search(word);
		}
		
		else {
			return false;
		}
	}
	
	public void remove(String word, Node previous) {
		if (containing.compareTo(word) == 0) {
			if (left == null && right == null) {
				previous.removeChild(this);
			}
			else if (left != null && right != null) {
				containing = left.lastAlphabetically();
				left.remove(containing, this);
			}
			else if (left != null) {
				previous.replaceNode(this, left);
			}
			else if (right != null) {
				previous.replaceNode(this, right);
			}
		}
		else if (containing.compareTo(word) > 0 && left != null) {
			left.remove(word, this);
		}
		else if (containing.compareTo(word) < 0 && right != null) {
			right.remove(word, this);
		}
	}
	// method to remove()
	private void removeChild(Node child) {
		if (right == child) {
			right = null;
		}
		else {
			left = null;
		}
	}
	// method to remove()
	private void replaceNode(Node oldNode, Node newNode) {
		if (right == oldNode) {
			right = newNode;
		}
		else {
			left = newNode;
		}
	}
	
	public String firstAlphabetically() {
		if (left == null) {
			return containing;
		}
		return left.firstAlphabetically();
	}
	
	public String lastAlphabetically() {
		if (right == null) {
			return containing;
		}
		return right.lastAlphabetically();
	}
	
	/*
	 * Find the depth of all nodes and store them in the array list that
	 * is provided as a parameter
	 */
	public ArrayList<Integer> depthAllNodes(int n, ArrayList<Integer> arr) {
		if (left == null && right == null) {
			arr.add(n);
		}
		if (left != null) {
			arr.add(n);
			left.depthAllNodes(n + 1, arr);
		}
		if (right != null) {
			arr.add(n);
			right.depthAllNodes(n + 1, arr);
		}
		return arr;
	}
	
	/*
	 * Calculate the sum of nodes in depth n
	 */
	public int inDepth(int n, int sum) {
		if (n == 0) {
			return sum + 1;
		}
		if (left != null && right != null) {
			return left.inDepth(n - 1, sum) + right.inDepth(n - 1, sum);
		}
		else if (left != null) {
			return left.inDepth(n - 1, sum);
		}
		else if (right != null) {
			return right.inDepth(n - 1, sum);
		}
		else {
			return 0;
		}
	}
}
