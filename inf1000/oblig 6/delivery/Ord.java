public class Ord {
    
    // Variables
    
    private int wordCount;
    private String instanceWord = "";
    
    
    // Konstruktør:
    
    /**
     * Constructs a word with word count 1 and string value equal the parameter
     * @param tekst the string value of the word instance
    */
    Ord(String tekst) {
        wordCount = 1; // default word count on a new word
        instanceWord = tekst; // saves the instance word
    }
    
    
    // Metoder:
    
    /**
     * Returns the string value of the word
    */
    public String toString()  {
        return instanceWord;
    }
    
    /**
     * Returns the word count on the word
    */
    public int hentAntall() {
        return wordCount;
    }
    
    /**
     * Adds one to the word count on the word
    */
    public void oekAntall() {
        wordCount++;
    }
    
}
