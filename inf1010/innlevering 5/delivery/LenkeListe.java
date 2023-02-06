/*************************

 * Innlevering 5
 * Author: Jan Inge Lamo
 * File name: LenkeListe.java
 * Date: 28.02.16

*************************/


class LenkeListe<E extends Comparable<E>>
{
    private Node foran = null;

    /**
     * Check if this container is empty
     * 
     * @return true if empty, false otherwise
     */
    public boolean tom()
    {
        return foran == null;
    }

    /**
     * Add a node in the chain list
     * 
     * @param e element in the new node
     */
    public void leggTil(E e)
    {
        // Add a starting node if this container is empty
        if (foran == null)
        {
            foran = new Node(e);
            return;
        }
        Node tmp = foran;
        while (tmp.hentNesteNode() != null)
        {
            tmp = tmp.hentNesteNode();
        }
        // Add a new node after the last node in the chain list
        tmp.leggTilNesteNode(new Node(e));
    }

    /**
     * Remove the node containing the smallest element as governed by
     * compareTo()
     * 
     * If we try to run this without any nodes, then we will get an NullPointer-
     * Exception. The user should check if the chain link is empty with tom()
     * before calling this method.
     * 
     * @return the smallest element
     */
    public E fjernMinste()
    {
        E minst = foran.hentData();
        Node tmp = foran;
        // Loop for finding the smallest element
        while (tmp != null)
        {
            if (minst.compareTo(tmp.hentData()) > 0)
            {
                minst = tmp.hentData();
            }
            tmp = tmp.hentNesteNode();
        }
        // If the first element is the smallest, then the next element is
        // assigned to be the first element
        if (minst.compareTo(foran.hentData()) == 0)
        {
            foran = foran.hentNesteNode();
            return minst;
        }
        tmp = foran;
        // Find the first node that has the smallest element, and remove it
        while (minst.compareTo(tmp.hentNesteNode().hentData()) != 0)
        {
            tmp = tmp.hentNesteNode();
        }
        tmp.fjernNesteNode();
        return minst;
    }

    /**
     * Check if any of the nodes in the chain list contain a specific element
     * 
     * @param  e the element we are looking for
     * @return   true if the element is present in the list, false otherwise
     */
    public boolean inneholder(E e)
    {
        Node tmp = foran;
        while (tmp != null)
        {
            if (tmp.hentData().compareTo(e) == 0)
            {
                return true;
            }
            tmp = tmp.hentNesteNode();
        }
        return false;
    }

    /**
     * Our node class
     *
     * The methods are pretty self explanatory due to their simplicity and
     * verbose naming convention. Therefore I will not write javadoc comments
     * for them.
     */
    private class Node
    {
        E data;
        Node neste = null;

        Node(E e)
        {
            data = e;
        }
        public E hentData()
        {
            return data;
        }
        public Node hentNesteNode()
        {
            return neste;
        }
        public void leggTilNesteNode(Node n)
        {
            neste = n;
        }
        public void fjernNesteNode()
        {
            neste = neste.hentNesteNode();
        }
    }
}