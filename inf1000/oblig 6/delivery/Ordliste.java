import java.util.*;
import java.io.*;

public class Ordliste {
    
    // Variables
    
    ArrayList<Ord> wordList = new ArrayList<Ord>();


    // Metoder:
    
    /**
     * Reads through a file executing leggTilOrd() on each word
     * @param filnavn the file it reads
    */
    public void lesBok(String filnavn) throws Exception {
        
        File fil = new File(filnavn);
        Scanner read = new Scanner(fil);
        
        // Traverse the file
        while (read.hasNextLine()) {
            leggTilOrd(read.nextLine()); // add every word in the file
        }
    }
    
    
    /**
     * Counts the occurance of each word
     * @param ord the string value of the word it counts
    */
    private void leggTilOrd(String ord) {
        
        for (int i=0; i < wordList.size(); i++) {
            if(wordList.get(i).toString().equalsIgnoreCase(ord)) {
                wordList.get(i).oekAntall();
                return;
            }
        }
        // This is executed only if the word was not found in the loop above
        wordList.add(new Ord(ord));
    }
    
    
    /**
     * Returns the Ord object if it's in the arraylist
     * @param tekst the string value it looks for in the object
    */
    public Ord finnOrd(String tekst) {
        
        for (int i=0; i < wordList.size(); i++) {
            if(wordList.get(i).toString().equalsIgnoreCase(tekst)) {
                return wordList.get(i);
            }
        }
        return null;
    }
    
    
    /**
     * Returns the size/length of the arraylist
    */
    public int antallOrd() {
        return wordList.size();
    }
    
    
    /**
     * Returns the number of occurances of a certain word
     * @param tekst the string value it looks for in the object
    */
    public int antallForekomster(String tekst) {
        
        for (int i=0; i < wordList.size(); i++) {
            if(wordList.get(i).toString().equalsIgnoreCase(tekst)) {
                return wordList.get(i).hentAntall();
            }
        }
        return 0;
    }
    
    
    /**
     * Finds the most frequent word in the arraylist
    */
    public Ord vanligste() {
        
        int currentMax = 0;
        
        for (int i=0; i < wordList.size(); i++) {
            if (wordList.get(currentMax).hentAntall() <
                wordList.get(i).hentAntall()) {
                    currentMax = i;
            }
        }
        return wordList.get(currentMax);
    }
    
    
    /**
     * Finds the most frequent words in the arraylist
    */
    public Ord[] alleVanligste() {
        
        // We have one array for common words and one temporary array for when
        // the common words array need to grow.
        Ord[] commonList = {wordList.get(0)};
        Ord[] temp = new Ord[0];
        
        for (int i=1; i < wordList.size(); i++) {
            // If the next word is equally frequent as our most frequent word
            // thus far, then we need to grow the most common words array and
            // add the next word to it.
            if (commonList[0].hentAntall() == wordList.get(i).hentAntall()) {
                
                temp = new Ord[commonList.length];
                
                for (int n=0; n < commonList.length; n++) {
                    temp[n] = commonList[n];
                }
                commonList = new Ord[(temp.length + 1)];
                for (int n=0; n < temp.length; n++) {
                    commonList[n] = temp[n];
                }
                commonList[(commonList.length - 1)] = wordList.get(i);
            }
            // If the next word is more frequent than our most frequent word
            // thus far, then start a fresh new array with only the next word.
            else if (commonList[0].hentAntall() <
                     wordList.get(i).hentAntall()) {
                commonList = new Ord[1];
                commonList[0] = wordList.get(i);
            }
        }
        return commonList;
    }
    

}
