import java.io.*;
import java.util.*;

/**
 * Main class for initiating threads and the monitor
 * 
 * @author Victoria Ariel Bjoernestad
 * @author Jan Inge Lamo
 */
public class Oblig9 
{
    public static void main(String[] args) throws Exception
    {
        int antTraader = 0;

        // Test if there are three arguments
        if (args.length != 3)
        {
            System.out.println("Exception: arity mismatch!");
            System.exit(1);
        }

        // Test if first argument is a number
        try
        {
            antTraader = Integer.parseInt(args[0]);
        }
        catch (NumberFormatException e)
        {
            System.out.println("Exception: " + e);
            System.exit(1);
        }

        // File in and file out names
        String fileName = args[1];
        String fileOut = args[2];

        File fil = new File(fileName);
        Scanner read = null;

        // Test if in file exist
        try
        {
            read = new Scanner(fil);
        }
        catch (FileNotFoundException e)
        {
            System.out.println("Exception: " + e);
            System.exit(1);
        }

        int antOrd = Integer.parseInt(read.nextLine());
        Monitor monitor = new Monitor(antOrd);
        String[] wordList = new String[antOrd];

        int v = 0;

        // Copy file to string array
        while (read.hasNextLine())
        {
            if (v>(wordList.length-1))
            {
                System.out.println("Exception: word count mismatch!");
                System.exit(1);
            }
            wordList[v] = read.nextLine();
            v++;
        }

        // Test if word count matches actual word count
        if (v != antOrd)
        {
            System.out.println("Exception: word count mismatch!");
            System.exit(1);
        }

        // Test if last element is a null pointer
        if (wordList[antOrd - 1] == null)
        {
            System.out.println("Exception: last element is null!");
            System.exit(1);
        }

        int n = antOrd/antTraader;
        int m = antOrd % antTraader;
        int p = 0;
        String[] tmp;

        // Start measure time
        long startTime = System.nanoTime();

        // Create a new Sorter thread for each array piece
        for (int x=0; x<antTraader; x++)
        {
            if (x<m)
            {
                tmp = new String[n+1];
            }
            else
            {
                tmp = new String[n];
            }
            for (int y=0; y<tmp.length; y++)
            {
                tmp[y] = wordList[p];
                p++;
            }
            if (tmp.length != 0)
            {
                new Sorter(monitor, tmp).start();
            }
        }

        // Wait for the threads to finish
        monitor.vent();
        wordList = monitor.giFerdigSortert();

        // stop time measure
        long endTime = System.nanoTime();
        long duration = (endTime - startTime);

        // Test if list is sorted
        boolean isSorted = true;
        for (int i = 0; i < wordList.length - 1; i++) {
            if (wordList[i].toLowerCase().compareTo(wordList[i+1].toLowerCase()) > 0) {
                isSorted = false;
                break;
            }
        }
        if (!isSorted)
        {
            System.out.println("Exception: list is not sorted!");
            System.exit(1);
        }
        else
        {
            System.out.println("Listen er sortert!");
        }

        // Print sorted list to out file
        PrintWriter out = new PrintWriter(fileOut);
        out.println(antOrd);
        for (int i=0; i<wordList.length; i++)
        {
            out.println(wordList[i]);
        }
        out.close();

        System.out.println("Tidsbruk: " + duration/1000000 + "ms");
    }
}