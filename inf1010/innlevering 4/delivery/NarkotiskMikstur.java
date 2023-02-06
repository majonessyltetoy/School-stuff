/*************************

 * Innlevering 4
 * Author: Jan Inge Lamo
 * File name: NarkotiskMikstur.java
 * Date: 23.02.16

*************************/


class NarkotiskMikstur extends Narkotisk
{

    public NarkotiskMikstur(String navn, int pris, int id, int antallEnheter,
        int virkemiddelPerEnhet, int hvorNarkotisk)
    {
        super(navn, pris, id, antallEnheter, virkemiddelPerEnhet,
            hvorNarkotisk);
        super.navn = navn;
        super.pris = pris;
        super.id = id;
        super.totalVirkemiddel = antallEnheter * virkemiddelPerEnhet;
        super.antallEnheter = antallEnheter;
        super.virkemiddelPerEnhet = virkemiddelPerEnhet;
        super.hvorNarkotisk = hvorNarkotisk;

    }
}