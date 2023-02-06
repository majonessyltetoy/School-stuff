/*************************

 * Innlevering 6
 * Author: Jan Inge Lamo
 * File name: AbstraktTabell.java
 * Date: 12.03.16

*************************/


public interface AbstraktTabell<E>
{
    /**
     * Put an object into table in a specific place
     *
     * @param  e object we want to place into table
     * @param  n index where object will be placed
     * @return   true if object was successfully placed, false otherwise
     */
    boolean put(E e, int n);

    /**
     * Find the object on the nth place in the table
     * 
     * @param  n index where object is stored
     * @return   the object in that place in the table
     */
    E find(int n);


    /**
     * Iterate through the table
     */
    void iterate();
}