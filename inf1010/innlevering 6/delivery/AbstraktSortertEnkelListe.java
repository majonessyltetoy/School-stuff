/*************************

 * Innlevering 6
 * Author: Jan Inge Lamo
 * File name: AbstraktSortertEnkelListe.java
 * Date: 12.03.16

*************************/


public interface AbstraktSortertEnkelListe<E extends Comparable<E> & Lik>
{
    /**
     * Put an object into the list
     * 
     * @param  e object we put into list
     * @return   true if the operation was successful, false otherwise
     */
    void put(E e);


    /**
     * Find the object based on input key
     * 
     * @param  key key used to find object
     * @return     the object that matches the key
     */
    E find(String key) throws ObjectNonExistingException;

    /**
     * Remove the node identified by String key
     *
     * @return  true if the operation was successful, false otherwise
     */
    void remove(String key) throws ObjectNonExistingException;


    /**
     * Iterate over the list
     */
    void iterate();
}