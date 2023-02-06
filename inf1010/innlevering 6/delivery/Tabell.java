/*************************

 * Innlevering 6
 * Author: Jan Inge Lamo
 * File name: Tabell.java
 * Date: 12.03.16

*************************/


class Tabell<E> implements AbstraktTabell<E>
{
    private E[] tableArray;

    /**
     * Constructor for this generic class
     * 
     * @param  i length of the array
     */
    public Tabell(int i)
    {
        tableArray = (E[])new Object[i];
    }

    /**
     * Put object into the nth place in the array
     * 
     * @param  e object to be inserted
     * @param  n index where we want to place the object
     * @return   true if the operation was successful false otherwise
     */
    public boolean put(E e, int n)
    {
        if (0 > n || n >= tableArray.length)
        {
            return false;
        }
        else if (tableArray[n] != null)
        {
            return false;
        }
        else
        {
            tableArray[n] = e;
            return true;
        }
    }

    /**
     * Return object in the nth place in the array
     * 
     * @param  n index in the array
     * @return   object in that place
     */
    public E find(int n)
    {
        if (n > -1 && tableArray.length > n)
        {
            return tableArray[n];
        }
        return null;
    }

    /**
     * Iterate through the array
     */
    public void iterate()
    {
        for (int n=0; n<tableArray.length; n++)
        {
            if (tableArray[n] != null)
            {
                System.out.println("#" + n + " name: " +
                    tableArray[n].toString());
            }
        }
    }
    
}