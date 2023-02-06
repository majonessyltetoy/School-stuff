/*************************

 * Innlevering 2
 * Author: Jan Inge Lamo
 * File name: Hylle.java
 * Date: 09.02.16

*************************/


import java.util.*;

class Hylle<T> implements GenHylle<T>
{
    private ArrayList<T> shelf = new ArrayList<T>();

    /**
     * Constructer for the Hylle class. Fills the array list with empty spaces
     *
     * @param size the size of the shelf
     */
    public Hylle(int size)
    {
        for (int i=0; i<size; i++)
        {
            shelf.add(null);
        }
    }

    /**
     * Checks the array list size to see this shelf instance size.
     *
     * @return the size of the array list
     */
    public int getSize()
    {
        return shelf.size();
    }

    /**
     * Put the generic type object in vacant space on the shelf. If the space is
     * not vacant or outside the shelf, then an error will be written.
     * 
     * @param t     a generic object, probably a Bok object
     * @param space the space where the object is to be put
     * @return      our test program require this method to return booleans
     */
    public boolean putBook(T t, int space)
    {
        if (isVacant(space) && withinShelf(space))
        {
            shelf.set(space, t);
            return true;
        }
        else
        {
            return false;
        }
    }

    /**
     * This is a check method to see if the space on the shelf is vacant.
     * 
     * @param  space which space on the shelf the method will check
     * @return       true if the space is vacant, false otherwise
     */
    public boolean isVacant(int space)
    {
        if (!withinShelf(space))
        {
            return false;
        }
        if (shelf.get(space) != null)
        {
            return false;
        }
        return true;
    }

    /**
     * Remove an object from the shelf
     * 
     * @param  space where the book is on the shelf
     * @return       generic object, probably a book. Null if there is no object
     *               in that space
     */
    public T takeBook(int space)
    {
        if (!isVacant(space) && withinShelf(space))
        {
            T tmp = shelf.get(space);
            shelf.set(space, null);
            return tmp;
        }
        else
        {
            return null;
        }
    }

    /**
     * Instance method to check if the space is within the shelf bounds.
     * 
     * @param  space the space we want to check is inside the shelf
     * @return       true if the space is inside the shelf, false otherwise
     */
    private boolean withinShelf(int space)
    {
        return (space>0 && space<=shelf.size());
    }
}