// In this weeks assignment you will make a dating program.
// You will make use of arrays to store data about people,
// then compare interests to see if they are a potensial
// match. Homosexuals and lesbians are disregarded as not
// to make the program too complex. However the ancient age
// formula (men: age/2+7 = ideal; women: age < age+2 = ideal)
// is to be included.


import java.util.Scanner;

class MinOppgave3 {
    public static void main(String[] args) {
        
        Scanner scan = new Scanner(System.in);
        int registerPeople = 0;
        int valg = 0;
        int agePick = 0;
        int ageComp = 0;
        boolean foundMatch = false;
        // Ask how many people to register
        System.out.print("Hvor mange som skal registreres (minst 2)\n> ");
        registerPeople = Integer.parseInt(scan.nextLine());
        
        if (registerPeople < 2) {
            System.out.println("error too few people to register");
        }
        else {
            // Array to store person data
            String[][] peopleArray = new String[registerPeople][4];
            
            // Add data about the people
            for (int i=0;i<registerPeople;i++) {
                // Questions loop
                for (int n=0;n<4;n++) {
                    peopleArray[i][n] = askQuestions(n);
                }
                System.out.print("\n");
            }
            System.out.println("Finn en potensiel partner, velg et tall. 0 = exit");
            for (int i=0;i<registerPeople;i++) {
                System.out.print("\n[" + (i + 1) + "]\t" + peopleArray[i][0]);
            }
            System.out.print("\n> ");
            valg = Integer.parseInt(scan.nextLine()) - 1;
            if (valg == -1 || valg >= registerPeople)
                System.out.println("Exiting.....");
            else {
                for (int i=0;i<registerPeople;i++) {
                    // Make sure the match isn't him/her -self
                    if (i != valg) {
                        // Compare interests
                        if (peopleArray[i][3].equalsIgnoreCase(peopleArray[valg][3])) {
                            // Make sure they're opposite gender
                            if (!peopleArray[i][2].equalsIgnoreCase(peopleArray[valg][2])) {
                                foundMatch = true;
                                agePick = Integer.parseInt(peopleArray[valg][1]);
                                ageComp = Integer.parseInt(peopleArray[i][1]);
                                // Equalism hasn't touched preferred age yet
                                if (peopleArray[valg][2].equalsIgnoreCase("mann")) {
                                    if (((agePick / 2) + 7) == ageComp) {
                                        System.out.println(peopleArray[i][0] + " er en perfekt mach for deg!");
                                    }
                                    else {
                                        System.out.println(peopleArray[i][0] + " er en god mach for deg");
                                    }
                                }
                                else {
                                    if (agePick <= (ageComp - 2)) {
                                        System.out.println(peopleArray[i][0] + " er en perfekt mach for deg!");
                                    }
                                    else {
                                        System.out.println(peopleArray[i][0] + " er en god mach for deg");
                                    }
                                }
                            }
                        }
                    }
                }
                if (foundMatch == false) 
                    System.out.println("Unfortunately no suitable match were found");
            }
        }
    }
    
    
    // Question Method
    public static String askQuestions(int nr) {
        Scanner scan = new Scanner(System.in);
        if (nr == 0) {
            System.out.print("Skriv inn navn\n> ");
            return scan.nextLine();
        }
        else if (nr == 1) {
            System.out.print("Skriv inn alder\n> ");
            return scan.nextLine();
        }
        else if (nr == 2) {
            System.out.print("Skriv inn gender\n> ");
            return scan.nextLine();
        }
        else {
            System.out.print("Skriv inn interesse\n> ");
            return scan.nextLine();
        }
    }
}
