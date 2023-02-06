/*************************

 * Innlevering 4
 * Author: Jan Inge Lamo
 * File name: NarkotiskPiller.java
 * Date: 23.02.16

*************************/


class NarkotiskPiller extends Narkotisk implements Pille
{

    public NarkotiskPiller(String navn, int pris, int id, int antallEnheter,
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

    public int antallPiller()
    {
        return antallEnheter;
    }

    public int virkmiddelPerPille()
    {
        return virkemiddelPerEnhet;
    }
}