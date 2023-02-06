/*************************

 * Innlevering 4
 * Author: Jan Inge Lamo
 * File name: Vanedannende.java
 * Date: 23.02.16

*************************/


class Vanedannende extends Legemidler
{
    protected int styrke;

    public Vanedannende(String navn, int pris, int id, int antall,
        int virkestoff, int styrke)
    {
        super(navn, pris, id, antall, virkestoff);
        super.navn = navn;
        super.pris = pris;
        super.id = id;
        super.antall = antall;
        super.virkestoff = virkestoff;
        this.styrke = styrke;

    }
}