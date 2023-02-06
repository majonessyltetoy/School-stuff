import java.util.*;
import java.io.*;


class Temperatur {
    public static void main(String[] args) throws Exception
    {
        // Variables
        int counter = 0;
        File fil = new File("temperatur.txt");
        int[] temp = new int[12];
        Scanner in = new Scanner(fil);
        
        // Traverse the file
        while (in.hasNextLine())
        {
            temp[counter] = Integer.parseInt(in.nextLine());
            counter++;
        }
        
        in.close();
        
        // Print the average temperature
        System.out.println("The average temperature was: " + findAverage(temp));
    }
    
    
    // The average might be a real number that is why findAverage returns a
    // string
    private static String findAverage(int[] intArray)
    {
        double totalInt = 0;
        for (int i=0; i < intArray.length; i++)
        {
            totalInt += intArray[i];
        }
        // Math.round rounds to the nearest integer. There is a work around that
        // multiply by 100, rounds then divides by 100 leaving two decimal.
        // But this String.format hack is really neat.
        return String.format("%.3g%n", (totalInt/intArray.length));
    }
}
