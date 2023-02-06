import java.util.Scanner;

class Metoder {
    public static void main(String[] args) {
        
        // Loop the function three times
        for (int i=0;i<3;i++) {
            askName();
        }
    }
    
    // My method
    static void askName() {
       
        // We only need one name and place variable
        String name;
        String place;

        // Making the scanner
        Scanner scan = new Scanner(System.in);

        // Asking too many questions 
        System.out.print("Skriv inn navn:\n> ");
        name = scan.nextLine();

        System.out.print("Skriv inn bosted:\n> ");
        place = scan.nextLine();
        
        System.out.println("Hei, " + name + "! Du er fra " + place + ".\n");
           
    }
}
