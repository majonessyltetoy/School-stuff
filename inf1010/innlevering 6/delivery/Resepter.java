/*************************

 * Innlevering 6
 * Author: Jan Inge Lamo
 * File name: Resepter.java
 * Date: 12.03.16

*************************/


class Resepter implements Lik, Comparable<Resepter>
{
    protected int id;
    protected Legemidler drug;

    public Resepter(int i, Legemidler d)
    {
        id = i;
        drug = d;
    }

    public String toString()
    {
        return drug.toString() ;
    }

    public int returnID()
    {
        return id;
    }

    public boolean same(String s)
    {
        return id == Integer.parseInt(s);
    }

    public int compareTo(Resepter other)
    {
        if (id == other.returnID())
        {
            return 0;
        }
        else if (id > other.returnID())
        {
            return 1;
        }
        else
        {
            return -1;
        }
    }
}