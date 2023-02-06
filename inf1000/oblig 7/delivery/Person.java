import java.util.*;

class Person {
    
    private String name;
    private HashMap<String, DVD> arkiv = new HashMap<String, DVD>();
    private HashMap<String, DVD> laante = new HashMap<String, DVD>();
    private HashMap<String, DVD> utlaante = new HashMap<String, DVD>();
    
    /**
     * Constructor for the Person class
     * 
     * @param name the name of the Person in this instance
     */
    Person(String name) {
        this.name = name;
    }
    
    /**
     * Returns the String value of this Person objects name
     */
    public String toString() {
        return name;
    }
    
    /**
     * Adds a DVD object to the arkiv HashMap
     * 
     * @param DVDName the name of the DVD
     */
    public void addDVD(String DVDName) {
        arkiv.put(DVDName, new DVD(DVDName, this));
    }
    
    /**
     * Puts a DVD object in the utlaante HashMap and calls borrow on the DVD
     * object
     * 
     * @param borrower the Person object of the borrower
     * @param DVDName  the name of the DVD
     */
    public void rent(Person borrower, String DVDName) {
        utlaante.put(DVDName, arkiv.get(DVDName));
        utlaante.get(DVDName).borrow(borrower);
    }
    
    /**
     * Puts a DVD object in the laante HashMap signifing this Person instance
     * is only borrowing the DVD, not owning it
     * 
     * @param DVDName the DVD object
     */
    public void receiveDVD(DVD DVDName) {
        laante.put(DVDName.toString(), DVDName);
    }
    
    /**
     * Calls returnDVD on the DVD object and removes it from the laante HashMap
     * 
     * @param DVDName the name of the DVD
     */
    public void returnDVD(String DVDName) {
        laante.get(DVDName).returnDVD();
        laante.remove(DVDName.toString());
    }
    
    /**
     * Removes the DVD object from the utlaante HashMap, signifing it is no
     * longer rented and has been returned to its owner
     * 
     * @param DVDName the DVD object
     */
    public void returnTo(DVD DVDName) {
        utlaante.remove(DVDName.toString());
    }
    
    /**
     * Print information about this Person instance
     * 
     * Example execution:
     * 
     * Person: Per
     * DVD-er Per eier:
     * The hobbit 1
     * The music man
     * DVD-er Per laaner bort:
     * The music man til Kari
     * Per laaner ingen DVD-er.
     */
    public void printPersonInfo() {
        
        System.out.println("Person: " + name);
        
        if (arkiv.size() == 0) {
            System.out.println(name + " eier ingen DVD-er.");
        }
        else {
            System.out.println("DVD-er " + name + " eier:");
            for (String d : arkiv.keySet()) {
                System.out.println(arkiv.get(d).toString());
            }
        }
        
        if (utlaante.size() == 0) {
            System.out.println(name + " laaner ikke bort noen DVD-er.");
        }
        else {
            System.out.println("DVD-er " + name + " laaner bort:");
            for (String d : utlaante.keySet()) {
                System.out.println(utlaante.get(d).toString() + " til " + 
                utlaante.get(d).borrower().toString());
            }
        }
        
        if (laante.size() == 0) {
            System.out.println(name + " laaner ingen DVD-er.");
        }
        else {
            System.out.println("DVD-er " + name + " laaner:");
            for (String d : laante.keySet()) {
                System.out.println(laante.get(d).toString() + " fra " + 
                laante.get(d).owner().toString());
            }
        }
    }
    
    /**
     * Print overview over this instance
     * 
     * Example execution:
     * 
     * Person: Per
     * Eier: 2
     * Laant: 0
     * Utlaant: 1
     */
    public void printOverView() {
        
        System.out.println("Person: "  + name);
        System.out.println("Eier: "    + arkiv.size());
        System.out.println("Laant: "   + laante.size());
        System.out.println("Utlaant: " + utlaante.size());
    }
    
    /**
     * Returns true if this instance has the DVD key in the arkiv HashMap
     * 
     * @param DVDName the name of the DVD
     */
    public boolean hasInArchive(String DVDName) {
        if (arkiv.get(DVDName) != null) {
            return true;
        }
        return false;
    }
    
    /**
     * Returns true if this instance has the DVD key in both arkiv and utlaante
     * 
     * @param DVDName the DVD name
     */
    public boolean isBorrowed(String DVDName) {
        if (arkiv.get(DVDName) != null && utlaante.get(DVDName) != null) {
            return true;
        }
        return false;
    }
    
    /**
     * Returns true if the laante HashMap has DVDName as a key
     * 
     * @param DVDName the name of the DVD
     */
    public boolean isBorrowingDVD(String DVDName) {
        if (laante.get(DVDName) != null) {
            return true;
        }
        return false;
    }
    
    /**
     * Returns the string of a DVD collection, DVDs that are rented has a "*"
     * in front of the name and the name of the renter in the line under
     */
    public String DVDCollectionString() {
        String returnString = "";
        for (String key : arkiv.keySet()) {
            returnString += "\n";
            if (utlaante.get(key) != null) {
                returnString += "*" + key;
                returnString += "\n";
                returnString += utlaante.get(key).borrower().toString();
                
            }
            else {
                returnString += key;
            }
        }
        return returnString;
    }
}
