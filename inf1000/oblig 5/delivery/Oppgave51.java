class Oppgave51 {
    public static void main(String[] args) {
        
        Bil porsche = new Bil();
        
        System.out.println("Maks distanse med bensin paa tanken: " + 
                            porsche.hentMaksDistance() + " km.");
        
        // Take the car for a spinn
        System.out.println();
        porsche.kjorTur(120);
        System.out.println("Du kjoorte 120 km");
        
        // Check how many kilometers you can drive on your tank
        System.out.println();
        System.out.println("Maks distanse med bensin paa tanken: " + 
                            porsche.hentMaksDistance() + " km.");

        // Try to take a long ride
        System.out.println();
        System.out.println("Du proover aa kjoore 300 km.");
        porsche.kjorTur(300);
        
        // Try to overfill the tank
        System.out.println();
        System.out.println("Du proover aa fylle tanken med 20 liter");
        porsche.fillTank(20);
        
        // Fill the tank
        System.out.println();
        System.out.println("Du fyller paa 10 liter.");
        porsche.fillTank(10);
        
        // Take the car for another spin
        System.out.println();
        porsche.kjorTur(300);
        System.out.println("Du kjoorer 300 km.");
        
        // Total distance driven
        System.out.println();
        System.out.println("Du har kjoort " + porsche.hentKilometerstand() + 
                           " km totalt.");
    }
}
