/*************************

 * Innlevering 6
 * Author: Jan Inge Lamo
 * File name: SortertEnkelListe.java
 * Date: 13.03.16

*************************/


class SortertEnkelListe<E extends Comparable<E> & Lik>
implements AbstraktSortertEnkelListe<E>
{
    // our head, or base case (recursion)
    private Node<E> foran = new Node<E>(null, null);

    /**
     * Put an object into the list
     * 
     * @param e element put into the list
     */
    public void put(E e)
    {
        foran.putSorted(e);
    }

    /**
     * Find object, search by comparing String type
     * 
     * @param  s String key to the object
     * @return   object we searched for
     */
    public E find(String s) throws ObjectNonExistingException
    {
        return (E)foran.findInNode(s);
    }

    /**
     * Remove the node that matches String s
     * @param s key for the node we'll remove
     */
    public void remove(String s) throws ObjectNonExistingException
    {
        foran.removeNode(s);
    }

    /**
     * Iterate through the chain list
     */
    public void iterate()
    {
        foran.iterateNodes();
    }
}