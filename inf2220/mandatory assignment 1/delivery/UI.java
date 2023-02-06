import java.io.*;
import java.util.*;

class UI {
	public static Tree TreeDict = new Tree();
	public static void main(String[] args) {
		//Tree TreeDict = new Tree();
		
		if (args.length == 0) {
			noDictionary();
		}
		File dict = new File(args[0]);
		Scanner read = null;

		try {
			read = new Scanner(dict);
		}
		catch (FileNotFoundException e) {
			noDictionary();
		}
		
		// read file and insert in tree
		while (read.hasNextLine()) {
			TreeDict.insert(read.nextLine());
		}
		
		// start menu
		menu();
		System.out.print("\nGathering statistics");
		for (int i=0; i<5; i++) {
            System.out.print(".");
            wait(1);
       }
		
		// finish by printing statistics
		System.out.println("\n====================");
		System.out.println("Tree statistics\n");
		
		if (TreeDict.getRoot() == null) {
			System.out.println("Tree is missing root.");
			System.exit(0);
		}
		
		System.out.println("Max node depth: " + TreeDict.depth() + "\n");
		
		System.out.println("Nodes per depth:");
		for (int i=0; i<=TreeDict.depth(); i++) {
			System.out.println("depth: " + i + " nodes: " +
							   TreeDict.nodesInDepth(i));
		}
		System.out.println();
		
		System.out.println("Depth average: " + TreeDict.depthAvg() + "\n");
		
		System.out.println("Alphabetical order");
		System.out.println("First word: " + TreeDict.firstWord() + "\n" +
						   "Last word: " + TreeDict.lastWord());
	}
	
	private static void menu() {
		System.out.print("\u001b[2J" + "\u001b[H");
    	
    	String userInput = "";
        Scanner read = new Scanner(System.in);
        
        System.out.println("Choose one of the following:");
        System.out.println("[s]earch for word in dictionary");
        System.out.println("[r]emove word from dictionary");
        System.out.println("[i]nsert word");
        System.out.print("[q]uit\n> ");
        userInput = read.nextLine();
        // Exit if no value is given
        if (userInput.length() != 0) {
            // Cut away anything but the first letter
            userInput = userInput.substring(0, 1);
            if (userInput.equalsIgnoreCase("s")) {
            	System.out.print("\u001b[2J" + "\u001b[H");
            	System.out.println("search for q to quit");
            	searchMenu();
                menu();
            }
            else if (userInput.equalsIgnoreCase("r")) {
            	System.out.print("\u001b[2J" + "\u001b[H");
                System.out.print("Enter word to be removed\n> ");
                userInput = read.nextLine();
                if (userInput.length() != 0) {
                	if (TreeDict.remove(userInput)) {
                		System.out.print("Word successfully removed!");
                		for (int i=0; i<10; i++) {
            				System.out.print(".");
            				wait(1);
        				}
        			}
        			else {
        				System.out.println("\nError!");
                		System.out.print("Word does not exist in dictionary.");
                		for (int i=0; i<10; i++) {
            				System.out.print(".");
            				wait(1);
        				}
        			}
              	}
              	menu();
            }
            else if (userInput.equalsIgnoreCase("i")) {
            	System.out.print("\u001b[2J" + "\u001b[H");
                System.out.print("Enter word to be inserted\n> ");
                userInput = read.nextLine();
                if (userInput.length() != 0) {
                	if (TreeDict.insert(userInput)) {
                		System.out.print("Word successfully inserted!");
                		for (int i=0; i<10; i++) {
            				System.out.print(".");
            				wait(1);
        				}
        			}
        			else {
        				System.out.println("\nError!");
                		System.out.print("Word exist in dictionary.");
                		for (int i=0; i<10; i++) {
            				System.out.print(".");
            				wait(1);
        				}
        			}
              	}
              	menu();
            }
        }
    }
    
    private static void searchMenu() {
    	ArrayList<String> suggestWords = new ArrayList<String>();
    	String userInput = "";
        Scanner read = new Scanner(System.in);
    	
    	System.out.print("\nEnter word to search for\n> ");
        userInput = read.nextLine();
        
        if (userInput.equalsIgnoreCase("q") || userInput.equals("")) {
        	return;
        }
        
        if (TreeDict.search(userInput)) {
        		System.out.println("\"" + userInput + "\" is in the dictionary!");
        }
        else {
        	long startTime = System.nanoTime();
        	suggestWords = TreeDict.suggest(userInput);
        	long endTime = System.nanoTime();
        	long duration = (endTime - startTime);
        	System.out.println("It's not in the dictionary");
        	System.out.println("Found " + suggestWords.size() + " suggestions" +
        	" in " + duration/1000000 + "ms with " + TreeDict.getLookUps() +
        	" lookups.");
        	System.out.println(suggestWords);
        }
        searchMenu();
	}
        
    	
    	
	private static void noDictionary() {
		System.out.println("Error, no dictionary found.\n" +
						   "Execute like so: java UI dictionary.txt");
		System.exit(1);
	}
	
	private static void wait(int seconds) {
        try {
            Thread.sleep(seconds * 300);
        }
        catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
