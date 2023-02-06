/*************************

 * Innlevering 3
 * Author: Jan Inge Lamo
 * File name: Oblig3.java
 * Date: 14.02.16

*************************/


import java.io.*;
import java.util.*;

class Oblig3
{
    public static void main(String[] args) throws Exception
    {
        ArrayList<Bil> carList = new ArrayList<Bil>();
        String[] line;

	// The filename is hardcoded because the assignment spesified that the
	// program had to run it and give some specific output
        File carListFile = new File("car_list.txt");
        Scanner read = new Scanner(carListFile);


        while (read.hasNextLine())
        {
            line = read.nextLine().split(" ");

            // If you get compilation error that String is not accepted in
            // switch statements, then you should upgrade immediatly. Java 7 has
            // been available since 2011!
            switch (line[0])
            {
                case "BIL":
                    carList.add(new Bil(line[1]));
                    break;
                case "FOSSIL":
                    carList.add(new Fossilbil(line[1],
                        Double.parseDouble(line[2])));
                    break;
                case "EL": 
                    carList.add(new Elbil(line[1],
                        Integer.parseInt(line[2])));
                    break;
                case "PERSONFOSSILBIL":
                    carList.add(new Personbil(line[1],
                        Double.parseDouble(line[2]),
                        Integer.parseInt(line[3])));
                    break;
                case "LASTEBIL":
                    carList.add(new Lastebil(line[1],
                        Double.parseDouble(line[2]),
                        Double.parseDouble(line[3])));
                    break;
                // Print error message
                default:
                    System.out.println("Formatting error in input file!");
            }
        }

        // For-each loop
        for (Bil car : carList)
        {
            if (car instanceof Personbil)
            {
                System.out.println("License plate:\t\t" + car.returnBilNr());
                System.out.println("C02 emission:\t\t" 
                                   + ((Fossilbil) car).returnEmission());
                System.out.println("Passenger seats:\t"
                                   + ((Personbil) car).returnPassengerSeats());
                System.out.println();
            }
        }
    }
}
