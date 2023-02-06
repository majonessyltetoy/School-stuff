import java.util.ArrayList;

class Structure
{
    protected ArrayList<Integer> exist = new ArrayList<Integer>();

    /**
     * Add a set value to the structure
     * 
     * @param i value that is set
     */
    public void add(int i)
    {
        if (!exist.contains(i))
        {
            exist.add(i);
        }
        else
        {   // can't throw a real exception because of Thread.
            System.out.println("Error! Duplicate value detected: "+i);
            System.exit(1);
        }
    }

    /**
     * Unset a value from the structure
     * 
     * @param i value to be unset
     */
    public void unset(int i)
    {
        exist.remove(exist.indexOf(i));
    }

    /**
     * Returns values in the structure, for updatePossible()
     * 
     * @return all the values that are taken in this structure
     */
    public ArrayList<Integer> taken()
    {
        return exist;
    }
}