/*************************

 * Innlevering 3
 * Author: Jan Inge Lamo
 * File name: Bil.java
 * Date: 14.02.16

*************************/


class Bil 
{
    protected String bilNr;

    /**
     * Constructor for Bil. Initiates the license plate variable
     * @param  bilNr License plate number
     */
    public Bil(String bilNr)
    {
        this.bilNr = bilNr;
    }

    /**
     * Returns the license plate number
     * @return License plate number
     */
    public String returnBilNr()
    {
        return bilNr;
    }
}