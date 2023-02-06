/*************************

 * Innlevering 6
 * Author: Jan Inge Lamo
 * File name: Node.java
 * Date: 13.03.16

*************************/


public class Node<E extends Comparable<E> & Lik>
{
    private E element;
    private Node next;

    public Node(E e, Node n)
    {
        element = e;
        next = n;
    }

    public E data()
    {
        return element;
    }
    public Node nextNode()
    {
        return next;
    }
    public boolean hasNext()
    {
        if (next != null)
        {
            return true;
        }
        return false;
    }
    public E next()
    {
        return (E)next.data();
    }
    public void remove()
    {
        System.out.println("This should not have been");
    }
    public Node lastNode()
    {
        if (next == null)
        {
            return this;
        }
        else
        {
            return next.lastNode();
        }
    }
    public void appendNode(Node n)
    {
        next = n;
    }
    public void putSorted(E e)
    {
        if (next == null)
        {
            next = new Node(e, null);
        }
        else if (e.compareTo((E)next.data()) >= 0)
        {
            next = new Node(e, next);
        }
        else
        {
            next.putSorted(e);
        }
    }
    public E findInNode(String s) throws ObjectNonExistingException
    {
        if (next == null)
        {
            throw new ObjectNonExistingException();
        }
        if (next.data() instanceof Resepter)
        {
            if (Integer.parseInt(s) == ((Resepter)next.data()).returnID())
            {
                return (E)next.data();
            }
            else
            {
                return (E)next.findInNode(s);
            }
        }
        else
        {
            if (s.compareTo(next.data().toString()) == 0)
            {
                return (E)next.data();
            }
            else
            {
                return (E)next.findInNode(s);
            }
        }
    }

    public void removeNode(String s) throws ObjectNonExistingException
    {
        if (next == null)
        {
            throw new ObjectNonExistingException();
        }
        if (next.data() instanceof Resepter)
        {
            if (Integer.parseInt(s) == ((Resepter)next.data()).returnID())
            {
                next = next.nextNode();
            }
            else
            {
                next.removeNode(s);
            }
        }
        else
        {
            if (s.compareTo(next.data().toString()) == 0)
            {
                next = next.nextNode();
            }
            else
            {
                next.removeNode(s);
            }
        }
    }

    public void iterateNodes()
    {
        if (next != null)
        {
            System.out.println("name: " + next.data().toString());
            next.iterateNodes();
        }
    }
}
