/*************************

 * Innlevering 6
 * Author: Jan Inge Lamo
 * File name: Leger.java
 * Date: 16.03.16

*************************/


class Leger implements Lik, Comparable<Leger>
{
    protected String name;

    private EldsteForstReseptListe legeResepter = new EldsteForstReseptListe();

    public Leger(String s)
    {
        name = s;
    }

    public String toString()
    {
        return name;
    }

    public boolean same(String s)
    {
        return name.equals(s);
    }

    public int compareTo(Leger other)
    {
        return other.toString().compareTo(this.toString());
    }

    public void givePrescription(Pasienter p, Legemidler drug)
    {
        p.receivePrescription(new Resepter(4, drug)); // 4 is a random unique
        legeResepter.put(new Resepter(4, drug)); //number, elected by the elders
    }
    
}