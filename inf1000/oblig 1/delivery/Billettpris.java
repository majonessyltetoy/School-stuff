import java.util.Scanner;

class Billettpris {
    public static void main(String[] args) {
        
        // making variables
        Scanner scan = new Scanner(System.in);
        int alder, billettPris;
        billettPris = 50;
        
        // capture user input
        System.out.print("Skriv inn din alder\n> ");
        alder = Integer.parseInt(scan.nextLine());

        //Siden det ikke er spesifisert aldergrenser skal
        //vaere mindre eller lik, eller bare mindre, gaar
        //jeg ut fra at det er bare mindre.
        if (12 > alder || 67 < alder)
            System.out.println("Billettprisen er " + (billettPris / 2) + "kr");
        else
            System.out.println("Billettprisen er " + billettPris + "kr");
    }
}
