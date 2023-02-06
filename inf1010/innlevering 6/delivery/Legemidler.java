/*************************

 * Innlevering 6
 * Author: Jan Inge Lamo
 * File name: Legemidler.java
 * Date: 12.03.16

*************************/


class Legemidler implements Lik, Comparable<Leger>
{
    protected String name;
    protected int id;

    public Legemidler(String s, int i)
    {
        name = s;
        id = i;
    }

    public String toString()
    {
        return name;
    }

    public int returnID()
    {
        return id;
    }
    public boolean same(String s)
    {
        return name.equals(s);
    }

    public int compareTo(Leger other)
    {
        return other.toString().compareTo(this.toString());
    }
}

