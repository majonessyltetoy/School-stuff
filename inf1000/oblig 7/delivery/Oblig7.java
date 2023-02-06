import java.util.*;

class Oblig7 {
    
    private static DVDAdministrasjon redBox = new DVDAdministrasjon();
    private static Scanner input = new Scanner(System.in);
    
    public static void main(String[] args) throws Exception {
        
        redBox.readFile("dvdarkiv.txt");
        
        // Menu loop
        while (true) {
            menu();
        }
    }
    
    /**
     * Calls regPerson to register a new person to the system.
     * Checks if the person is already registered.
     */
    private static void register() {
        
        System.out.println();
        System.out.println("Hva heter den nye personen?");
        System.out.print("> ");
        String name = input.nextLine();
        
        if (redBox.personExist(name)) {
            System.out.println();
            System.out.println(name + " is already registered.");
            System.out.println();
        }
        else {
            redBox.regPerson(name);
        }
    }
    
    /**
     * Calls buyDVD only if the person exist in the system and the person does
     * not already own the DVD
     */
    private static void buy() {
        
        System.out.println();
        System.out.println("Hvem har kjopt DVD-en?");
        System.out.print("> ");
        String name = input.nextLine();
        
        if (redBox.personExist(name)) {
            System.out.println();
            System.out.println("Hva tittelen paa DVD-en?");
            System.out.print("> ");
            String DVDName = input.nextLine();
            
            if (redBox.ownsDVD(name, DVDName)) {
                System.out.println();
                System.out.println(name + " already owns " + DVDName);
                System.out.println();
            }
            else {
                redBox.buyDVD(name, DVDName);

            }
        }
        else {
            System.out.println();
            System.out.println(name + " is not in the database.");
            System.out.println();
        }
    }
    
    /**
     * Calls rentDVD if the owner of the DVD exist in the system, the person who
     * want to rent exist in the system, and the DVD is available from the owner
     */
    private static void borrow() {
        
        System.out.println();
        System.out.println("Hvem vil laane DVD-en?");
        System.out.print("> ");
        String name = input.nextLine();
        
        if (redBox.personExist(name)) {
            
            System.out.println();
            System.out.println("Hvem skal DVD-en laanes fra?");
            System.out.print("> ");
            String from = input.nextLine();
            
            if (redBox.personExist(from) && !name.equals(from)) {
                System.out.println();
                System.out.println("Hva tittelen paa DVD-en?");
                System.out.print("> ");
                String DVDName = input.nextLine();
                
                if (redBox.isDVDAvailable(from, DVDName)) {
                    redBox.rentDVD(name, from, DVDName);
                }
                else {
                    System.out.println();
                    System.out.println(DVDName + " is not available from " +
                    from);
                }
            }
            else {
                System.out.println();
                System.out.println(from + " is not in the database.");
                System.out.println();
            }
        }
        else {
            System.out.println();
            System.out.println(name + " is not in the database.");
            System.out.println();
        }
        
    }
    
    /**
     * Call printInfo or printInfoAll depending on user input
     */
    private static void show() {
        
        System.out.println();
        System.out.println("Hvilken person vil du se? (* for alle)");
        System.out.print("> ");
        String name = input.nextLine();
        
        if (name.equals("*")) {
            System.out.println();
            redBox.printInfoAll();
        }
        else {
            if (redBox.personExist(name)) {
                System.out.println();
                redBox.printInfo(name);
            }
            else {
                System.out.println();
                System.out.println(name + " is not in the database.");
                System.out.println();
            }
        }
    }
    
    /**
     * Calls printOverView on the DVDAdministrasjon object
     */
    private static void overview() { 
        
        System.out.println();
        redBox.printOverViewAll();
    }
    
    /**
     * Initiate return sequence of a DVD from the renter to the owner, if the
     * renter exist in the system and is renting said DVD
     */
    private static void retur() {
        
        System.out.println();
        System.out.println("Hvem vil returnere DVD-en?");
        System.out.print("> ");
        String name = input.nextLine();
        
        if (redBox.personExist(name)) {
            System.out.println();
            System.out.println("Hva tittelen paa DVD-en?");
            System.out.print("> ");
            String DVDName = input.nextLine();
            
            if (redBox.isBorrowing(name, DVDName)) {
                redBox.returnDVD(name, DVDName);
            }
            else {
                System.out.println();
                System.out.println(name + " is not renting " + DVDName);
                System.out.println();
            }
        }
        else {
            System.out.println();
            System.out.println(name + " is not in the database.");
            System.out.println();
        }
    }
    
    /**
     * Write the current system status to a file and exit the program with exit
     * code 0, meaning no errors
     */
    private static void quit() throws Exception {
        
        System.out.println();
        System.out.println("Writing to file and exiting...");
        System.out.println();
        
        redBox.saveToFile();
        System.exit(0);
    }
    
    /**
     * Menu method, lets users input commands and initiate corresponding methods
     */
    private static void menu() throws Exception {
        
        int choice;
        
        System.out.println();
        System.out.println("MENY FOR DVD-ADMINISTRASJON");
        System.out.println("1: Ny person.");
        System.out.println("2: Kjop.");
        System.out.println("3: Laan.");
        System.out.println("4: Vis.");
        System.out.println("5: Oversikt.");
        System.out.println("6: Retur.");
        System.out.println("7: Avslutt.");
        
        // Get user input
        System.out.print("> ");
        choice = Integer.parseInt(input.nextLine());
        
        switch(choice) {
            case 1: {
                register();
                break;
            }
            case 2: {
                buy();
                break;
            }
            case 3: {
                borrow();
                break;
            }
            case 4: {
                show();
                break;
            }
            case 5: {
                overview();
                break;
            }
            case 6: {
                retur();
                break;
            }
            case 7: {
                quit();
                break;
            }
            // Print error message
            default: {
                System.out.println("Please enter a number between 1 and 7\n");
            }
        }
    }
        
}

