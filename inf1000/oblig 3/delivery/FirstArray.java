import java.util.Scanner;

class FirstArray {
    public static void main(String[] args) {
       
        // Variabler
        int teller = 0;
        Scanner scan = new Scanner(System.in);
        String[] navnArray = new String[5];


        // a)
        int[] intArray = {1, 2, 3, 2};
        
        
        // b)
        for (int i=0;i<4;i++)
            intArray[i] = i;

        
        // c)
        intArray[0] = intArray[(intArray.length - 1)] = 1337;

        
        // d)
        System.out.println("\nSkriv inn fem navn");
        for (int i=0;i<5;i++)
            navnArray[i] = scan.nextLine();


        // e)
        System.out.println("\nInnholdet i int-array:");

        while (teller != 4) {
            System.out.println(intArray[teller]);
            teller++;
        }

        teller = 0;

        System.out.println("\nNavnene du skrev inn:");

        while (teller != 5) {
            System.out.println(navnArray[teller]);
            teller++;
        }
    }
}
