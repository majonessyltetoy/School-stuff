/*************************

 * Innlevering 3
 * Author: Jan Inge Lamo
 * File name: Personbil.java
 * Date: 14.02.16

*************************/


class Personbil extends Fossilbil
{
    private int passengerSeats;

    /**
     * Constructor for Personbil. Initiates variable values
     * @param  bilNr          License plate number
     * @param  carbonEmission C02 emission
     * @param  passengerSeats Number of passenger seats in the car
     */
    public Personbil(String bilNr, double carbonEmission, int passengerSeats)
    {
        super(bilNr, carbonEmission);
        super.bilNr = bilNr;
        super.carbonEmission = carbonEmission;
        this.passengerSeats = passengerSeats;
    }

    /**
     * Returns the number of passenger seats
     * @return number of passenger seats
     */
    public int returnPassengerSeats()
    {
        return passengerSeats;
    }
}