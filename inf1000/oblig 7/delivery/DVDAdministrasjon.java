import java.util.*;
import java.io.*;

class DVDAdministrasjon {
    
    private HashMap<String, Person> navneliste = new HashMap<String, Person>();
    private File fileName;
    
    /**
     * Reads through a file, parsing the names and DVDs
     * 
     * @param fileName the file it reads
    */
    public void readFile(String fileName) throws Exception {
        
        String line = "";
        String owner = ""; 
        this.fileName = new File(fileName);
        Scanner read = new Scanner(this.fileName);
        
        // This loop reads the names in the begining of the file, and adds them
        // to the HashMap
        while (read.hasNextLine()) {
            line = read.nextLine();
            
            
            if (line.equals("-")) {
                break;
            }
            else {
                navneliste.put(line, new Person(line));
            }
        }
        // This loop reads which DVDs a person owns and incidently whom is
        // borrowing them
        while (read.hasNextLine()) {
            line = read.nextLine();
            owner = line;
            while (read.hasNextLine()) {
                line = read.nextLine();
                if (line.equals("-")) {
                    break;
                }
                else {
                    if (line.substring(0, 1).equals("*")) {
                        buyDVD(owner, line.substring(1));
                        rentDVD(read.nextLine(), owner, line.substring(1));
                    }
                    else {
                        buyDVD(owner, line);
                    }
                }
            }
        }
    }
    
    /**
     * Adds a Person object to the HashMap navnliste
     * 
     * @param name the name of the person to register
    */
    public void regPerson(String name) {
        navneliste.put(name, new Person(name));
    }
    
    /**
     * Calls addDVD on a Person object
     * 
     * @param name    the name of the person who buys a DVD
     * @param DVDName the name of the DVD that a person buys
    */
    public void buyDVD(String name, String DVDName) {
        navneliste.get(name).addDVD(DVDName);
    }
    
    /**
     * Calls the method rent on a Person object
     * 
     * @param name    the name of the person who want to rent a dvd
     * @param from    the name of the DVD owner
     * @param DVDName the name of the DVD that the person rents
    */
    public void rentDVD(String name, String owner, String DVDName) {
        navneliste.get(owner).rent(navneliste.get(name), DVDName);
    }
    
    /**
     * Calls returnDVD on a Person object
     * 
     * @param name    the name of the person who want to return a rented DVD
     * @param DVDName the name of the DVD the person has rented
     */
    public void returnDVD(String name, String DVDName) {
        navneliste.get(name).returnDVD(DVDName);
    }
    
    /**
     * Calls the printPersonInfo method on a Person object
     * 
     * @param name the name of the person's info that will be printed
     */
    public void printInfo(String name) {
        navneliste.get(name).printPersonInfo();
    }
    
    /**
     * This method calls printInfo on all the objects in navneliste
     */
    public void printInfoAll() {
        for (String n : navneliste.keySet()) {
            System.out.println();
            printInfo(n);
        }
    }
    
    /**
     * Calls printOverView on all objects in navneliste
     */
    public void printOverViewAll() {
        for (String n : navneliste.keySet()) {
            System.out.println();
            navneliste.get(n).printOverView();
        }
    }
    
    /**
     * Returns true if the name is a key in the navneliste HashMap
     * 
     * @param name the name it's checking exists as a key
     */
    public boolean personExist(String name) {
        if (navneliste.get(name) != null) {
            return true;
        }
        return false;
    }
    
    /**
     * Returns true if the Person object has the DVD in it's archive
     * 
     * @param name    the name of the potential owner
     * @param DVDName the name of the DVD it's checking if is in the archive
     */
    public boolean ownsDVD(String name, String DVDName) {
        if (navneliste.get(name).hasInArchive(DVDName)) {
            return true;
        }
        return false;
    }
    
    /**
     * Returns true if a Person object owns a DVD and is not already renting it
     * out
     * 
     * @param name    the name of the DVD owner
     * @param DVDName the name of the DVD
     */
    public boolean isDVDAvailable(String name, String DVDName) {
        if (ownsDVD(name, DVDName)) {
            if (!navneliste.get(name).isBorrowed(DVDName)) {
                return true;
            }
        }
        return false;
    }
    
    /**
     * Returns true if a Person object is borrowing a DVD
     * 
     * @param name    the name of the person
     * @param DVDName the name of the DVD
     */
    public boolean isBorrowing(String name, String DVDName) {
        if (navneliste.get(name).isBorrowingDVD(DVDName)) {
            return true;
        }
        return false;
    }
    
    /**
     * Saves the current system to a file
     */
    public void saveToFile() throws Exception {
        
        String tempString = "";
        PrintWriter out = new PrintWriter("dvdarkiv.txt");
        
        for (String key : navneliste.keySet()) {
            tempString += navneliste.get(key);
            tempString += "\n";
        }
        
        // We need to remove the last new line in order to make these two for
        // loops play nice with each other
        tempString = tempString.substring(0, tempString.lastIndexOf("\n"));
        
        for (String key : navneliste.keySet()) {
            tempString += "\n-\n";
            tempString += key;
            tempString += navneliste.get(key).DVDCollectionString();
        }
        
        out.println(tempString);
        out.close();
    }
}

