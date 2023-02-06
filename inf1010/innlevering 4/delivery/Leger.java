/*************************

 * Innlevering 4
 * Author: Jan Inge Lamo
 * File name: Leger.java
 * Date: 23.02.16

*************************/


class Leger implements Lik
{
    protected String navn;

    public Leger(String navn)
    {
        this.navn = navn;
    }

    public boolean samme(String name)
    {
        return this.navn.equals(navn);
    }
    
}