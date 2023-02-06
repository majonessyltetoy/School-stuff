/*************************

 * Innlevering 3
 * Author: Jan Inge Lamo
 * File name: Fossilbil.java
 * Date: 14.02.16

*************************/


class Fossilbil extends Bil
{
    protected double carbonEmission;

    /**
     * Constructor for Fossilbil. Initiates variable values
     * @param  bilNr          License plate number
     * @param  carbonEmission C02 emission
     */
    public Fossilbil(String bilNr, double carbonEmission)
    {
        super(bilNr);
        super.bilNr = bilNr;
        this.carbonEmission = carbonEmission;
    }

    /**
     * Returns the C02 emission from this car
     * @return C02 emission
     */
    public double returnEmission()
    {
        return carbonEmission;
    }
}