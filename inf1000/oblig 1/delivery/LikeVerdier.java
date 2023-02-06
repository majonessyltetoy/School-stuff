import java.util.Scanner;

class LikeVerdier {
    public static void main(String[] args) {
        
        // creating variables
        Scanner scan = new Scanner(System.in);
        int c, d;
        
        // capture user input
        System.out.print("Skriv to tall\n> ");
        c = Integer.parseInt(scan.nextLine());
        System.out.print("> ");
        d = Integer.parseInt(scan.nextLine());
        
        // check if the input is equal
        if (c == d)
            System.out.println("De to tallen er like");
        else
            System.out.println("De to tallene er ikke like");
    }
}
