/*************************

 * Innlevering 3
 * Author: Jan Inge Lamo
 * File name: Elbil.java
 * Date: 14.02.16

*************************/


class Elbil extends Bil
{
    private int kW;

    /**
     * Constructor for Elbil. Initiates variable values
     * @param  bilNr License plate number
     * @param  kW    kilowatt
     */
    public Elbil(String bilNr, int kW)
    {
        super(bilNr);
        this.kW = kW;
        super.bilNr = bilNr;
    }

    /**
     * Returns the kilowatt for the electric car
     * @return kilowatt
     */
    public int returnkW()
    {
        return kW;
    }
}