import java.util.Scanner;

class SumTall {
    public static void main(String[] args) {

        // Varibles
        int input = -1; // -1 symbolizes that it is a dummy value
        int sum = 0;
        Scanner scan = new Scanner(System.in);
        
        // Do the loop
        while (input != 0) {
            input = Integer.parseInt(scan.nextLine());
            sum += input;
        }
        
        // b)
        System.out.println("Summen av tallene ble: " + sum);
    }
}
