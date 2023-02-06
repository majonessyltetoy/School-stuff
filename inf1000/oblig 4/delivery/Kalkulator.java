import java.util.*;

class Kalkulator {
    public static void main(String[] args)
    {
        int addSvar = addisjon(4, 3);
        int subSvar = subtraksjon(5, 2);
        int helDivSvar = heltallsdivisjon(10, 3); 
        double divSvar = divisjon(10, 3);
        
        System.out.println("addisjon: " + addSvar +
                           "\nsubtraksjon: " + subSvar +
                           "\nheltall divisjon: " + helDivSvar +
                           "\ndivisjon: " + divSvar);
    }
    
    
    private static int addisjon(int x, int y)
    {
        return (x + y);
    }    
    
    
    private static int subtraksjon(int x, int y)
    {
        return (x - y);
    }    
    
    
    private static int heltallsdivisjon(int x, int y)
    {
        return (x / y);
    }    
    
    
    private static double divisjon(double x, double y)
    {
        return (x / y);
    }
    
}
