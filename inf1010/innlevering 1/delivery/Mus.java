/*************************

 * Innlevering 1
 * Author: Jan Inge Lamo
 * File name: Mus.java
 * Date: 24.01.16

*************************/


class Mus
{
    // Note: the variables are protected so they can be accessed from the sub
    // class Rotte
    protected String name;
    protected boolean living;
    
    /**
     * Constructs a Mus object, sets the name of the mouse and changes it's
     * state to living
     * @param name the name of the mouse
     */
    Mus(String name)
    {
        this.name = name;
        living = true;
    }
    
    /**
     * Returns the name of the object
     */
    public String returnName()
    {
        return name;
    }
    
    /**
     * Returns the state of the object
     */
    public boolean isAlive()
    {
        return living;
    }
    
    /**
     * Method that the mouse is attacked
     */
    public void attack() // the cat is attacking, not the mouse...
    {
        living = false;
        System.out.println("Musen " + name + " gikk fra aa vaere levend til aa"
        + " vaere dod");
    }
}
