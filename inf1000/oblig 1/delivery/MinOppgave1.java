// Lag en passord evaluerer.
// Lengden på passordet skal vaere minst
// 13 tegn langt. Inneholde minst en
// liten bokstav, en stor, et tall.
// Programmet skal begynne med aa lese
// passordet fra terminalen og saa gi
// sin evaluering om den er god nok.

import java.util.Scanner;

class MinOppgave1 {
    public static void main(String[] args) {
        
        // creating variables
        Scanner scan = new Scanner(System.in);
        String passord;
        char tegn; // char variable, for specific characters within a string
        boolean litenBokstav, storBokstav, tall; // boolean; true or false
        litenBokstav = storBokstav = tall = false; // initiate them to false
        
        // capture user input
        System.out.print("Skriv inn ditt passord\n> ");
        passord = scan.nextLine();

        // check if the password is long enough
        if (passord.length() < 13)
            System.out.println("Passordet er for kort");
        else 
        {
            for (int i = 0; i < passord.length(); i++)
            { // check if the password is within the criteria
                tegn = passord.charAt(i);
                if (Character.isLowerCase(tegn))
                    litenBokstav = true;
                if (Character.isUpperCase(tegn))
                    storBokstav = true;
                if (Character.isDigit(tegn))
                    tall = true;
            } // give the final verdict
            if (litenBokstav == true && storBokstav == true && tall == true)
                    System.out.println("Passordet ditt er godkjent");
            else
                System.out.println("Passordet maa inneholde store og smaa bokstaver og minst et tall");
        }   
    }
}

