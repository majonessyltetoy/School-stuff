import java.util.Scanner;

class Beslutninger {
    public static void main(String[] args) {
        // creating varibles
        Scanner in = new Scanner(System.in);        
        int alder;

        // capture user input
        System.out.print("Skriv inn din alder\n> ");
        alder = Integer.parseInt(in.nextLine());
        
        // check if int is 18 or above
        if (alder >= 18)
            System.out.println("Du er myndig");
        else
            System.out.println("Du er ikke myndig");

    }
}
