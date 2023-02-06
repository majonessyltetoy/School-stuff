import java.util.HashMap;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Scanner;
import java.io.File;

class Oblig3 {

	public static void main(String[] args) throws Exception {
		if (args.length != 2) {
			System.out.println("Error! Program require two filenames.");
			System.exit(1);
		}
		File needle_file = new File(args[0]);
		File hay_file = new File(args[1]);
		if (!needle_file.isFile() || !hay_file.isFile()) {
			System.out.println("Error! File does not exist.");
			System.exit(1);
		}
		boolean found_match = false;
		String line = "";
		String needle = "";
		String hay = "";
		try {
			needle = new Scanner(needle_file).useDelimiter("\\Z").next();
			hay = new Scanner(hay_file).useDelimiter("\\Z").next();
		}
		catch (Exception e) {
			System.out.println("Empty file.");
			System.exit(0);
		}
		BMH bmh = new BMH();
		HashMap<Integer,String> m = new HashMap<Integer,String>();
		ArrayList<Integer> keys = new ArrayList<Integer>();
		
		// Differenciate when needle contains newline.
		if (needle.contains("\n")) {
			m = bmh.find(needle,hay,false);
			for (Integer key : m.keySet()) {
				keys.add(key);
			}
			Collections.sort(keys); // sorting for easier reading
			for (Integer key : keys) {
				found_match = true;
				System.out.println("Found match at character index " + key + ":");
				System.out.println(m.get(key) + "\n");
			}
			if (m.equals(bmh.find(needle,hay,true))) {
				System.out.println("Found all matches!");
			}
			else { // this should never happen
				System.out.println("Didn't match all needles!");
				m = bmh.find(needle,hay,true);
				for (Integer key : m.keySet()) {
					keys.add(key);
				}
				Collections.sort(keys); // sorting for easier reading
				for (Integer key : keys) {
					found_match = true;
					System.out.println("Found match at character index " + key + ":");
					System.out.println(m.get(key) + "\n");
				}
			}
		}
		else {
			Scanner s = new Scanner(hay);
			int line_count = 0; // start line counting with 1, feel more natural
			while (s.hasNextLine()) {
				line = s.nextLine();
				line_count++;
				keys.clear();
				m = bmh.find(needle,line,false);
				for (Integer key : m.keySet()) {
					keys.add(key);
				}
				Collections.sort(keys);
				if (keys.size() > 0) {
					found_match = true;
					System.out.println("\nFound match in line " + line_count);
				}
				for (Integer key : keys) {
					System.out.print("index " + key + ": ");
					System.out.println(m.get(key));
				}
				if (m.equals(bmh.find(needle,line,true)) && m.size() != 0) {
					System.out.println("Found all matches!");
				}
				// this should never happen
				else if (bmh.find(needle,line,true).size() != 0) {
					System.out.println("\nDidn't match all needles on line "+line_count+".");
					m = bmh.find(needle,line,true);
					for (Integer key : m.keySet()) {
						keys.add(key);
					}
					Collections.sort(keys);
					if (keys.size() > 0) {
						found_match = true;
					}
					for (Integer key : keys) {
						System.out.print("index " + key + ": ");
						System.out.println(m.get(key));
					}
				}
			}
		}
		if (!found_match) {
			System.out.println("No matches found.");
		}
	}
}
