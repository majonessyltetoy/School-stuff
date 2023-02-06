/*************************

 * Innlevering 3
 * Author: Jan Inge Lamo
 * File name: Lastebil.java
 * Date: 14.02.16

*************************/


class Lastebil extends Fossilbil
{
    private double pullWeight;

    /**
     * Constructor for Lastebil. Initiates variable values
     * @param  bilNr          License plate number
     * @param  carbonEmission C02 emission
     * @param  pullWeight     Weight the truck can pull
     */
    public Lastebil(String bilNr, double carbonEmission, double pullWeight)
    {
        super(bilNr, carbonEmission);
        super.bilNr = bilNr;
        super.carbonEmission = carbonEmission;
        this.pullWeight = pullWeight;
    }

    /**
     * Returns the value of the weight this truck instance can pull
     * @return the pull weight
     */
    public double returnPullWeight()
    {
        return pullWeight;
    }
}