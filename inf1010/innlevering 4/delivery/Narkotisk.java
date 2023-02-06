/*************************

 * Innlevering 4
 * Author: Jan Inge Lamo
 * File name: Narkotisk.java
 * Date: 23.02.16

*************************/


class Narkotisk extends Legemidler
{
    protected int hvorNarkotisk;

    public Narkotisk(String navn, int pris, int id, int antallEnheter,
        int virkemiddelPerEnhet, int hvorNarkotisk)
    {
        super(navn, pris, id, antallEnheter, virkemiddelPerEnhet);
        super.navn = navn;
        super.pris = pris;
        super.id = id;
        super.totalVirkemiddel = antallEnheter * virkemiddelPerEnhet;
        super.antallEnheter = antallEnheter;
        super.virkemiddelPerEnhet = virkemiddelPerEnhet;
        this.hvorNarkotisk = hvorNarkotisk;

    }
}