/*************************

 * Innlevering 1
 * Author: Jan Inge Lamo
 * File name: Rotte.java
 * Date: 24.01.16

*************************/


class Rotte extends Mus
{
    private boolean hurt;
    
    /**
     * The Rotte constructor, gives the rat a name and sets its state
     * @param name the name of the rat
     */
    Rotte(String name)
    {
        super(name);
        this.name = name;
        living = true;
        hurt = false;
    }
    
    /**
     * Method that the rat is attacked
     */
    public void attack() // the cat is attacking, not the rat...
    {
        if (hurt)
        {
            living = false;
            System.out.println("Rotta " + name + " gikk fra aa vaere skadet"
            + " til aa vaere dod.");
        }
        else
        {
            hurt = true;
            System.out.println("Rotta " + name + " gikk fra aa vaere levende"
            + " til aa vaere skadet");
        }
    }
}
