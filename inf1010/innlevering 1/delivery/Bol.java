/*************************

 * Innlevering 1
 * Author: Jan Inge Lamo
 * File name: Bol.java
 * Date: 24.01.16

*************************/


class Bol<T>
{
    private T inhabitant;
    
    /**
     * Move a rodent into this nest, if the nest is already inhabited the method
     * will print an error saying this nest is full
     * @param rodent the mouse or rat in the nest
     */
    public void moveIn(T rodent)
    {
        if (inhabitant == null)
        {
            inhabitant = rodent;
            return;
        }
        System.out.println("Bolet er allered fullt.");
    }
    
    /**
     * Returns the rodent object residing in this nest
     */
    public T resident()
    {
        return inhabitant;
    }
}
    
    
