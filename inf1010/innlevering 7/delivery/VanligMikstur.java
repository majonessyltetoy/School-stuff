/*************************

 * Innlevering 4
 * Author: Jan Inge Lamo
 * File name: VanligMikstur.java
 * Date: 23.02.16

*************************/


class VanligMikstur extends Vanlig
{

    public VanligMikstur(String navn, int pris, int id, int antall,
        int virkestoff)
    {
        super(navn, pris, id, antall, virkestoff);
        super.navn = navn;
        super.pris = pris;
        super.id = id;
        super.antall = antall;
        super.virkestoff = virkestoff;
    }
}
