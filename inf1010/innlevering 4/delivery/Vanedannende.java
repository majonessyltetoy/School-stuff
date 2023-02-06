/*************************

 * Innlevering 4
 * Author: Jan Inge Lamo
 * File name: Vanedannende.java
 * Date: 23.02.16

*************************/


class Vanedannende extends Legemidler
{
    protected int hvorVanedannende;

    public Vanedannende(String navn, int pris, int id, int antallEnheter,
        int virkemiddelPerEnhet, int hvorVanedannende)
    {
        super(navn, pris, id, antallEnheter, virkemiddelPerEnhet);
        super.navn = navn;
        super.pris = pris;
        super.id = id;
        super.totalVirkemiddel = antallEnheter * virkemiddelPerEnhet;
        super.antallEnheter = antallEnheter;
        super.virkemiddelPerEnhet = virkemiddelPerEnhet;
        this.hvorVanedannende = hvorVanedannende;

    }
}