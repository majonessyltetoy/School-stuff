/*************************

 * Innlevering 4
 * Author: Jan Inge Lamo
 * File name: VanedannendeMikstur.java
 * Date: 23.02.16

*************************/


class VanedannendeMikstur extends Vanedannende
{

    public VanedannendeMikstur(String navn, int pris, int id, int antall,
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
}