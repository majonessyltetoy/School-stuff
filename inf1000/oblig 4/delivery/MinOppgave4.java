/**
 * File: MinOppgave4.java
 * Author: Jan Inge Lamo
 * Date: 21.09.2015
 * 
 * Note: This program prints a whole terminal screen at a time. Normally a prog-
 * ammer would utilize a curses library (e.g. lanterna) to deal with terminal
 * output a little bit more delicately. However I felt this would far extend the
 * expectations from this assignment and discarded the idea. Using only what we
 * have learned thus far; the program clears the terminal screen, writes the
 * cellular automata world, then clears the screen again, repeating the process.
 * This isn't necessarily bad, but it floods the scrollback history in the 
 * terminal because it writes thousands of lines a minute. Any prior output in
 * the terminal will be lost for ever. Of course I'm making a too big deal out
 * of this, but this is me saying that this is bad software practice and I am
 * aware of it.
 * 
 * 
 * Excercise 4.5
 * This week we will summon Satan in a cellular automata world! You have been
 * given devil.txt which contains the blueprint state from which the Dark Lord
 * will appear. You must use Conway's Game of Life automata rules which are the
 * following; Any live cell with less than two or more than three adjacent live
 * cells will perish, any dead cell with exactly three living adjacent cells
 * will be reincarnated. Implement a while loop that runs the animation, but 
 * pauses when the user presses enter. The user should also be able to save the
 * paused world and load the file again. You will also get a bonus point if you
 * implement an option to generate a completly random world, letting the user
 * choose how many percent of the world are active cells. A real cellular
 * automata has an infinite number of cells, but you will limit the boundries
 * to the standard 80x24 terminal dimensions.
 * 
 * The summoning takes approximently one and a half minute. Feel free to chant
 * hebrew or sacrifice a goat.
 */


import java.util.*;
import java.io.*;


public class MinOppgave4 {
    public static void main(String[] args) throws Exception
    {
        // 80x24 dimension world, not big, but appearently large enough for the
        // devil and the beast (vi)
        int[][] world = new int[24][80];
        
        // Adding dramatic effect
        System.out.print("\u001b[2J" + "\u001b[H"); // clears the screen
        System.out.println("Note: Please use a terminal with at least 80x24 " +
        "dimensions.\nThe animation look best in a terminal emulator with " + 
        "double buffer support.");
        for (int i=0; i<25; i++)
        {
            System.out.print(".");
            wait(1);
        }
        System.out.print("\n");
        
        // Amplifying dramatic effect
        System.out.println("\n\nCaution: Starting the animation will flood " + 
        "the scrollback in the terminal.\nAny data you have not stored will " +
        "be lost once you run this program"); // #soundsWayWorseThanItIs
        for (int i=0; i<35; i++)
        {
            System.out.print(".");
            wait(1);
        }
        System.out.print("\n");
        System.out.print("\u001b[2J" + "\u001b[H"); // clears the screen again

        
        // Load world from file
        world = loadWorld();
        // Open the menu
        menu(world);
        
        // Good bye message
        System.out.print("Exiting");
        for (int i=0; i<8; i++)
        {
            System.out.print(".");
            wait(1);
        }
        System.out.print("\n");
    }
    
    
    private static int[][] loadWorld() throws Exception
     {
        // Declaring the world again because static arrays is a beach
        int[][] world = new int[24][80];
        int count = 0;
        String readLine = "";
        char bite;
        Scanner read = new Scanner(new File("devil.txt")); // Do you see the
                                                           // devil?
                                                           
        // Read world state from file and insert it into an array
        while (read.hasNext())
        {
            readLine = read.nextLine();
            for (int i=0; i < readLine.length(); i++)
            {
                // Any string is basically a char array
                bite = readLine.charAt(i);
                world[count][i] = Character.getNumericValue(bite);
            }
            count++;
        }
        // Closing the scanner
        read.close();
        
        System.out.print("Loading devil.txt");
        for (int i=0; i<8; i++)
        {
            System.out.print(".");
            wait(1);
        }
        System.out.print("\n");
        return world;
    }
    
    
    private static void printWorld(int[][] world)
    {
        for (int y=0; y<24; y++)
        {
            for (int x=0; x<80; x++)
            {
                if (world[y][x] != 0) {
                    System.out.print("@");
                }
                else {
                    System.out.print(" ");
                }
            }
            // So it will behave when the terminal is only 24 lines tall
            if (y != 23) {
                System.out.print("\n");
            }
        }
    }
    
    
    private static void menu(int[][] world) throws Exception
    {
        String choice = "";
        Scanner read = new Scanner(System.in);
        
        printWorld(world);
        
        System.out.println("\nThis is your current world!\n");
        
        // Printing the options and reading the user input
        System.out.println("Choose one of the following:");
        System.out.println("[S]tart animation (press enter to stop)");
        System.out.println("[P]reserve world (write current world to file)");
        System.out.println("[L]oad saved file");
        System.out.println("[R]andom world generation");
        System.out.print("[Q]uit\n> ");
        choice = read.nextLine();
        // Exit if no value is given
        if (choice.length() != 0)
        {
            // Cut away anything but the first letter
            choice = choice.substring(0, 1);
            if (choice.equalsIgnoreCase("s"))
            {
                start(world);
            }
            else if (choice.equalsIgnoreCase("p"))
            {
                preserve(world);
                menu(world);
            }
            else if (choice.equalsIgnoreCase("l"))
            {
                world = loadWorld();
                menu(world);
            }
            else if (choice.equalsIgnoreCase("r"))
            {
                world = generateWorld();
                menu(world);
            }
        }
    }
    
    
    private static void start(int[][] world) throws Exception 
    {
        Scanner scan = new Scanner(System.in);
        // Loops while the user has not pressed the enter key
        while (System.in.available() == 0)
        {
            // Clears the terminal and writes a new world every 600 milliseconds
            System.out.print("\u001b[2J" + "\u001b[H");
            System.out.flush();
            printWorld(world);
            wait(2);
            world = nextWorld(world);
        }
        // This catches the enter keypress
        scan.nextLine();
        menu(world);
    }
    
    
    private static void preserve(int[][] world) throws Exception
    {
        
        String stringWorld = "";
        PrintWriter out = new PrintWriter("devil.txt");
        
        // Loops the array and appends every cell to a string. Newlines are
        // added to improve the readability of the file.
        for (int y=0; y<24; y++)
        {
            for (int x=0; x<80; x++){
                stringWorld += Integer.toString(world[y][x]);
            }
            stringWorld += "\n";
        }
        
        // Writing to the file and closing it afterwards.
        out.print(stringWorld);
        out.close();
        
        // Added to improve interactivity. When the program writes to the file 
        // in a few milliseconds it feels like nothing has changed.
        System.out.print("Saving current world");
        for (int i=0; i<8; i++)
        {
            System.out.print(".");
            wait(1);
        }
        System.out.print("\n");
        System.out.println("The world was saved!!");
        wait(12);
    }
    
    
    private static int[][] generateWorld()
    {
        int[][] genWorld = new int[24][80];
        Scanner scan = new Scanner(System.in);
        Random generator = new Random();
        
        System.out.println("Generate random world!");
        System.out.print("Choose how many percent to be covered (0 - 100)\n> ");
        int randSeed = Integer.parseInt(scan.nextLine());
        
        // I'm totally addicted to these "print and wait" loops
        System.out.print("Generating random world");
        for (int i=0; i<10; i++)
        {
            System.out.print(".");
            wait(1);
        }
        System.out.print("\n");
        
        // Going through every cell in the array assigning state by calling the
        // random generator and comparing it to the seed
        for (int y=0; y<24; y++) {
            for (int x=0; x<80; x++)
            {
                if (randSeed > generator.nextInt(100)) {
                    genWorld[y][x] = 1;
                }
                else {
                    genWorld[y][x] = 0;            
                }
            }
        }
        return genWorld;
    }
    
    
    private static int[][] nextWorld(int[][] world)
    {
        int adjacent;
        int[][] nExtWorld = new int[24][80];
        for (int y=0; y<24; y++)
        {
            for (int x=0; x<80; x++)
            {
                adjacent = findAdjacent(world, y, x);
                if (world[y][x] == 1)
                {
                    // If a live cell has two or three adjacent live cells will
                    // it live through to the next world
                    if (adjacent == 2 || adjacent == 3) {
                        nExtWorld[y][x] = 1;
                    }
                }
                else
                {
                    // If a dead cell has three live cells surrounding it, will
                    // it be reborn in the next world
                    if (adjacent == 3) {
                        nExtWorld[y][x] = 1;
                    }
                    
                }
            }
        }
        // Normal people has pets, geeks has cellular automata and viruses
        return nExtWorld;
    }
    
    
    // As you no doubt see in this method lies the reason why I used an integer
    // array for storing the active cells. Intuetivly a boolean array comes to
    // mind because cellular automata cells are either living or dead. In fact
    // boolean might be more efficient at a lower level. But here in this method
    // we don't have to check if a state is true or false, we just add together
    // the content of each adjacent cell to find out how many active neighbours
    // a given cell has.
    private static int findAdjacent(int[][] world, int y, int x)
    {
        int totalAdj = 0;
        // Check if adjacent cells are within the world boundries and count the
        // active cells given a specific xy coordinate
        if (y != 0)
        {
            // Top left corner
            if (x != 0) {
                totalAdj += world[y - 1][x - 1];
            }
            // Top right corner
            if (x != 79) {
                totalAdj += world[y - 1][x + 1];
            }
            // Top
            totalAdj += world[y - 1][x];
        }
        
        if (x != 0) {
            // Left
            totalAdj += world[y][x - 1];
        }
            
        if (y != 23)
        {
            // Bottom left corner
            if (x != 0) {
                totalAdj += world[y + 1][x - 1];
            }
            // Bottom right corner
            if (x != 79) {
                totalAdj += world[y + 1][x + 1];
            }
            // Bottom
            totalAdj += world[y + 1][x];
        }
        if (x != 79) {
            // Right
            totalAdj += world[y][x + 1];
        }
            
        return totalAdj;
    }
    
    
    /**
    * Note: This methods original author is Ask Andreas Vargset or askav. I have
    * not asked permission to use this code snip. It was taken from a dating
    * simulator posted on piazza.
    */
    
    private static void wait(int seconds) {
        try {
            Thread.sleep(seconds * 300); // Originaly this was 1000
        }
        catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
