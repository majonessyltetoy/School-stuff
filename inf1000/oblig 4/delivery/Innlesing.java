import java.util.*;
import java.io.*;


class Innlesing {
    public static void main(String[] args) throws Exception
    {
        int counter = 0;
        File fil = new File("winnie.txt");
        Scanner scan = new Scanner(System.in);
        Scanner in = new Scanner(fil);
        String line = new String("");
        
        // b)
        //String findThis = new String("Winnie-the-Pooh");
        
        // c)
        System.out.print("Enter word:\n> ");
        String findThis = new String(scan.nextLine());
        
        // Traverse the file
        while (in.hasNextLine())
        {
            line = in.nextLine();
            // These two replaceAll string objects removes all the symbols at
            // at the start and end of the string, preserving symbols in words
            // e.g. I'll, oak-tree, can't, etc.
            // Please excuse that they are split up, my regex skills are 
            // somewhat dull.
            line = line.replaceAll("^[-:();.,?\"\']+", "");
            line = line.replaceAll("[-:();.,?\"\']+$", "");
            // This could have been case insensitive with equalsIgnoreCase
            if (findThis.equals(line)) {
                counter++;
            }
        }
        
        in.close();
        
        // Don't blame the program for not finding the seventh occurence of 
        // "Winnie-the-Pooh". The input file was currupt; there was only
        // supposed to be one word per line.
        System.out.println(findThis + " was found " + counter + " times.");
    }
}
