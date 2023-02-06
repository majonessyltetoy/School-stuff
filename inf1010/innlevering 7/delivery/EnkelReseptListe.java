/*************************

 * Innlevering 6
 * Author: Jan Inge Lamo
 * File name: EnkelReseptListe.java
 * Date: 13.03.16

*************************/


public class EnkelReseptListe
{
    // our head, or base case (recursion)
    protected Node<Resepter> foran = new Node<Resepter>(null, null);

    /**
     * Put an object into the list
     * 
     * @param e element put into the list
     */
    public void put(Resepter e)
    {
        foran.appendNode(new Node(e, foran.nextNode()));
    }

    /**
     * Find object, search for the unique ID number Resepter objects contain
     * 
     * @param  i Resepter ID number we're looking for
     * @return   object we searched for
     */
    public Resepter find(int i) throws ObjectNonExistingException
    {
        return (Resepter)foran.findInNode(Integer.toString(i));
    }

    /**
     * Remove node from chain link based on Resepter ID number
     * 
     * @param  i ID number we're searching for
     */
    public void remove(int i) throws ObjectNonExistingException
    {
        foran.removeNode(Integer.toString(i));
    }

    /**
     * Iterate through the list
     */
    public void iterate()
    {
        foran.iterateNodes();
    }
}