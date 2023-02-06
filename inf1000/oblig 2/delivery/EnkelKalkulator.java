import java.util.Scanner;

class EnkelKalkulator {
    public static void main(String[] args) {
        
        // Variables
        int x, y;
        Scanner scan = new Scanner(System.in);

        // Scan input from user
        System.out.print("Skriv to tall\n> ");
        x = Integer.parseInt(scan.nextLine());
        System.out.print("> ");
        y = Integer.parseInt(scan.nextLine());

        // Call methods
        plusMetode(x, y);
        minusMetode(x, y);
        gangeMetode(x,y);
    }

    // a)
    static void plusMetode(int x, int y) {
        System.out.print(x + " + " + y + " = " + (x + y) + "\n\n");
    }

    // b)
    static void minusMetode(int x, int y) {
        System.out.print(x + " - " + y + " = " + (x - y) + "\n\n");
    }
    
    // c)
    static void gangeMetode(int x, int y) {
        System.out.print(x + " * " + y + " = " + (x * y) + "\n\n");
    }
}
