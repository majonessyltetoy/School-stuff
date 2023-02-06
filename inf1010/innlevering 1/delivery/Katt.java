/*************************

 * Innlevering 1
 * Author: Jan Inge Lamo
 * File name: Katt.java
 * Date: 24.01.16

*************************/


class Katt
{
    private String name;
    
    /**
     * Constructor for Katt object, gives the cat a name
     * @param name the name of the cat
     */
    Katt(String name)
    {
        this.name = name;
    }
    
    /**
     * Send the cat out hunting. The parameters are the nests it'll search
     * @param mouseNest a mouse nest
     * @param ratNest a rat nest
     */
    public void hunt(Bol<Mus> mouseNest, Bol<Rotte> ratNest)
    {
        if (mouseNest.resident() != null && mouseNest.resident().isAlive())
        {
            System.out.println("Katten " + name + " gjorde et angrep paa musen "
            + mouseNest.resident().returnName());
            mouseNest.resident().attack();
        }
        else if (ratNest.resident() != null && ratNest.resident().isAlive())
        {
            System.out.println("Katten " + name + " gjorde et angrep paa rotta "
            + ratNest.resident().returnName());
            ratNest.resident().attack();
        }
        else
        {
            System.out.println("Katten " + name + " fant ingen gnagere."
            + " Jakten avsuttes.");
        }
    }
}
