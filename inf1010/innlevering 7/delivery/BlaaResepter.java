/*************************

 * Innlevering 4
 * Author: Jan Inge Lamo
 * File name: BlaaResepter.java
 * Date: 23.02.16

*************************/


class BlaaResepter extends Resepter
{
    public BlaaResepter(int id, Legemidler e, String lege, String pasient,
        int reit)
    {
        super(id, e, lege, pasient, reit);
        super.id = id;
        super.middel = e;
        super.lege = lege;
        super.pasient = pasient;
        super.reit = reit;
    }
}