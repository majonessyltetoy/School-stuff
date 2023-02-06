import java.util.Scanner;

class HeiStudent {
    public static void main(String[] args) {

        // creating variables
        String navn = "Jan Inge Lamo";
        Scanner in = new Scanner(System.in);
        String lesNavn;
        int dx, dy, ex, ey;
        dx = 5;
        dy = 2;
        
        // a)
        System.out.println("Hei Student!");
        
        // b)
        System.out.println("Hei " + navn);
        
        // c)
        System.out.print("Skriv et navn\n> ");
        lesNavn = in.nextLine();
        System.out.println("Hello " + lesNavn);
        
        // d)
        System.out.println("Summen av 5 og 2 er: " + (dx + dy));
        
        // e)
        System.out.print("Skriv inn to tall\n> ");
        ex = Integer.parseInt(in.nextLine());
        System.out.print("> ");
        ey = Integer.parseInt(in.nextLine());
        System.out.println("Summen av de to tallen er: " + (ex + ey));
    }
}
