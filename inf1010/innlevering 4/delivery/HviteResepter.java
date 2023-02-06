/*************************

 * Innlevering 4
 * Author: Jan Inge Lamo
 * File name: HviteResepter.java
 * Date: 23.02.16

*************************/


class HviteResepter extends Resepter
{
    public HviteResepter(int id, Legemidler e, String lege, String pasient,
        int reit, int pris)
    {
        super(id, e, lege, pasient, reit);
        super.id = id;
        super.middel = e;
        super.lege = lege;
        super.pasient = pasient;
        super.reit = reit;
        super.pris = pris;
    }
}