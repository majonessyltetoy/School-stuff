/*************************

 * Innlevering 4
 * Author: Jan Inge Lamo
 * File name: VanedannendePiller.java
 * Date: 23.02.16

*************************/


class VanedannendePiller extends Vanedannende implements Pille
{

    public VanedannendePiller(String navn, int pris, int id, int antall,
        int virkestoff, int styrke)
    {
        super(navn, pris, id, antall, virkestoff, styrke);
        super.navn = navn;
        super.pris = pris;
        super.id = id;
        super.antall = antall;
        super.virkestoff = virkestoff;
        super.styrke = styrke;
    }

    public int antallPiller()
    {
        return antall;
    }

    public double virkmiddelPerPille()
    {
        return virkestoff / antall;
    }
}