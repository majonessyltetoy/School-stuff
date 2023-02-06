public class Bil {
    
    // Variables
    private int distanceDriven = 0;
    private double availableFuel = 60;
    final private double maximumFuel = 60;
    final private double kilometerPerLiter = 6.34;
    
    
    // Nok bensin? Kjoor "km" kilometer.
    public void kjorTur(int km) {
        // Check if there is enough fuel for a ride
        if (0 <= (availableFuel - (km / kilometerPerLiter))) {
            distanceDriven += km;
            availableFuel -= (km / kilometerPerLiter);
        }
        // Print error message if too low fuel
        else {
            System.out.println("Du kan ikke kjore mer en du har bensin for.");
        }
    }
    
    
    // Fyll tanken dersom det er plass til spesifisert mengde bensin.
    public void fillTank(double liter) {
        // Check if the tank will be overfilled
        if (maximumFuel >= (availableFuel + liter)) {
            availableFuel += liter;
        }
        // Print error message if you try to fill the tank too much
        else {
            System.out.println("Du kan ikke fylle tanken over kapasitet.");
        }
    }
    
    
    // Hent maksimal kjooredistanse, gitt bensinmengde paa tanken.
    public double hentMaksDistance() {
        return (availableFuel * kilometerPerLiter);
    }
    
    
    // Hent bilens totale kilometerstand.
    public int hentKilometerstand() {
        return distanceDriven;
    }
}
