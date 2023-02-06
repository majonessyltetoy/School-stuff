/*************************

 * Innlevering 4
 * Author: Jan Inge Lamo
 * File name: Resepter.java
 * Date: 23.02.16

*************************/


class Resepter
{
    protected int id;
    protected Legemidler middel;
    protected String lege;
    protected String pasient;
    protected int reit;
    protected int pris;

    public Resepter(int id, Legemidler e, String lege, String pasient, int reit)
    {
        this.id = id;
        middel = e;
        this.lege = lege;
        this.pasient = pasient;
        this.reit = reit;
    }

    public int returnID()
    {
        return id;
    }

    public int returnPris()
    {
        return pris;
    }

    public Legemidler bruk(int saldo)
    {
        // sjekk pris og reit
        if ((saldo != pris) && (reit > 0))
        {
            return null;
        }
        reit = reit - 1;
        return middel;
    }
}