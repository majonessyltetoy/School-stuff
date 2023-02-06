/**
 * File: MinOppgave5.java
 * Author: Jan Inge Lamo
 * Date: 29.09.2015
 * 
 * Exercise 5.5
 * In this exercise you will make a class called Sleep.
 * You will compute hours slept each day of an arbitrary week.
 * Make a pointer called thomas that points to the Sleep object.
 * 
 * Use thomas with the following interface:
 * hoursSleep(int hours, int day) - submit hours slept that day
 * printHoursSlept(int day) - print hours slept on given day
 * averageSleep() - print average hours slept this week
 * sleepAnalysis() - print sleep analysis of every day of the week
 * 
 * Fill a whole week worth of sleep data and test the object interface.
 * 
 */


class MinOppgave5 {
    public static void main(String[] args) {
                
        Sleep thomas = new Sleep();
        
        // Input hours slept with corresponding day
        thomas.hoursSleep(8, 1);
        thomas.hoursSleep(4, 2);
        thomas.hoursSleep(5, 3);
        thomas.hoursSleep(6, 4);
        thomas.hoursSleep(0, 5);
        thomas.hoursSleep(9, 6);
        thomas.hoursSleep(10, 7);
        
        // Test that the program handles illogical user input
        System.out.println("Du prover aa gi programmet data for dag 8 i uka");
        thomas.hoursSleep(6, 8);
        
        // Print hours slept on the first day of the week
        System.out.println();
        System.out.println(thomas.printHoursSlept(1));
        
        // Print average hours sleep over the week
        System.out.println();
        System.out.println("Gjennomsnittet timer sovn denne uken er " + 
                           thomas.averageSleep());
                           
        // Print the sleep analysis of the week
        System.out.println();
        thomas.sleepAnalysis();
        
    }
}



class Sleep {
    
    // Varibles
    int daysSlept = 0;
    private int[] week = {-1, -1, -1, -1, -1, -1, -1};
    final private  String[] weekDay = {"mandag ", "tirsdag", "onsdag ",
                                       "torsdag", "fredag ", "lordag ",
                                       "sondag "};
    
    
    // Submit hours sleep that day
    public void hoursSleep(int sleep, int day) {
        if (day > 0 && day < 8) {
            week[(day - 1)] = sleep;
            daysSlept++;
        }
        else {
            System.out.println("Du maa velge en dag mellom 1 og 7.");
        }
    }
    
    
    // Get hours slept on a specific day
    public String printHoursSlept(int day) {
        if (day > 0 && day < 8) {
            if (week[(day - 1)] != -1) {
                return String.format("Du sov " + week[(day - 1)] +
                                     " timer paa " + weekDay[(day - 1)]);
            }
            else {
                return "Ingen data for den dagen";
            }
        }
        else {
            return "Angi en gyldig dag";
        }
    }
    
    
    // Find the average sleeping time this week
    public double averageSleep() {
        double totalSleep = 0;
        for (int i=0; i<week.length; i++) {
            if (week[i] > -1) {
                totalSleep += week[i];
            }
        }
        return (double)Math.round(totalSleep * 100 / daysSlept) / 100;
    }
    
    
    // Week analysis of hours slept
    public void sleepAnalysis() {
        for (int i=0; i<week.length; i++) {
            System.out.print(weekDay[i] + "  ");
            if (week[i] == -1) {
                System.out.println(printHoursSlept(i + 1));
            }
            else if (week[i] > 5 && week[i] < 10) {
                System.out.println("Du sov " + week[i] + " timer. " +
                                   "Det er optimalt.");
            }
            // Special mention to insomniacs
            else if (week[i] == 0) {
                System.out.println("Du sov ikke i det hele tatt. " +
                                   "Gaa og legg deg!!");
            }
            else if (week[i] < 6) {
                System.out.println("Du sov " + week[i] + " timer. " +
                                   "Prov aa sov mer.");
            }
            else {
                System.out.println("Du sov " + week[i] + " timer. " +
                                   "Det er litt i meste laget");
            }
        }
    }
}
