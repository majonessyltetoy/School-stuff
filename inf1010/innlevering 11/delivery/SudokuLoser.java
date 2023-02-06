import java.io.FileNotFoundException;
import java.util.Scanner;
import java.io.File;

class SudokuLoser
{
    public static SudokuBeholder container = new SudokuBeholder();
    public static boolean pretty = false;

    public static void main(String[] args) throws Exception
    {
        BoardReader br = new BoardReader();
        Brett b = null;

        if (args.length < 0 || args.length > 2)
        {
            System.out.println("Please provide a sudoku file as an argument");
            System.out.println("example: $ java SudokuLoser sudoku.txt");
            System.exit(1);
        }
        else if (args.length == 2 && args[1].equals("pretty"))
        {
            pretty = true;
        }


        try
        {
            b = br.lesFil(new File(args[0]));
        }
        catch (FileNotFoundException e)
        {
            System.out.println("Caught exception: " + e);
            System.out.println("Please enter an existing file.");
            System.exit(1);
        }
        catch (InValidCharException e)
        {
            System.out.println("Caught exception: " + e);
            System.out.println("Found invalid character on sudoku board.");
            System.exit(1);
        }
        catch (NumberFormatException e)
        {
            System.out.println("Caught exception: " + e);
            System.out.println("Board size must be defined by integers.");
            System.exit(1);
        }
        catch (FileExceedMaxSudokuSizeException e)
        {
            System.out.println("Caught exception: " + e);
            System.out.println("Maximum sudoku size is 64x64");
            System.exit(1);
        }
        catch (FileNotAdhereDefinitionException e)
        {
            System.out.println("Caught exception: " + e);
            System.out.println("Board is not the correct size.");
            System.exit(1);
        }
        catch (ValueOutsideLegalRangeException e)
        {
            System.out.println("Caught exception: " + e);
            System.out.println("Character value is outside supposed range");
            System.exit(1);
        }

        System.out.println("Solving board:");
        b.prettyPrintBoard();

        // Start measure time
        long startTime = System.nanoTime();


        // starting the threads
        new Brett(b, container, true).start();
        new Brett(b, container, false).start();
        container.waitHere();

        // stop time measure
        long endTime = System.nanoTime();
        long duration = (endTime - startTime);

        System.out.println("Found "+container.hentAntallLosninger()
            +" solution in "+(duration/1000000)+ "ms");

        if (container.hentAntallLosninger() == 1)
        {
            if (pretty)
            {
                System.out.println("Solution #"+1);
                container.taUt()[0].prettyPrintBoard();
            }
            else
            {
                container.taUt()[0].printBoard(1);
            }
        }
        else if (container.hentAntallLosninger()>1)
        {
            menu();
        }

    }

    public static void menu()
    {
        Scanner input = new Scanner(System.in);
        String userInput;
        int choice = 0;

        System.out.println();
        System.out.println("Enter a number from 1 to "
            +container.hentAntallLosninger()+" to see that solution,");
        System.out.println("or 0 to print them all.");
        System.out.println("Enter 'quit' to exit");
        System.out.print("> ");

        userInput = input.nextLine().toLowerCase();

        if (userInput.equals("quit") || userInput.equals("'quit'")
            || userInput.equals("q") || userInput.equals("exit"))
        {
            System.exit(0);
        }

        System.out.println();
        try
        {
            choice = Integer.parseInt(userInput);
        }
        catch (NumberFormatException e)
        {
            System.out.println("Error! please enter an integer.");
            menu();
            System.exit(0);
        }

        if (choice<0 || choice>container.hentAntallLosninger())
        {
            System.out.println("Error! number is outside range");
            menu();
            System.exit(0);
        }

        if (choice == 0)
        {
            for (int i=0; i<container.hentAntallLosninger(); i++)
            {
                if (pretty)
                {
                    System.out.println("Solution #"+i);
                    container.taUt()[i].prettyPrintBoard();
                }
                else
                {
                    container.taUt()[i].printBoard(i);
                }
            }
        }
        else
        {
            if (pretty)
            {
                System.out.println("Solution #"+choice);
                container.taUt()[(choice-1)].prettyPrintBoard();
            }
            else
            {
                container.taUt()[(choice-1)].printBoard(choice);
            }
        }
        menu();
    }
}