/*************************

 * Innlevering 4
 * Author: Jan Inge Lamo
 * File name: VanedannendePiller.java
 * Date: 23.02.16

*************************/


class VanedannendePiller extends Vanedannende implements Pille
{

    public VanedannendePiller(String navn, int pris, int id, int antallEnheter,
        int virkemiddelPerEnhet, int hvorVanedannende)
    {
        super(navn, pris, id, antallEnheter, virkemiddelPerEnhet,
            hvorVanedannende);
        super.navn = navn;
        super.pris = pris;
        super.id = id;
        super.totalVirkemiddel = antallEnheter * virkemiddelPerEnhet;
        super.antallEnheter = antallEnheter;
        super.virkemiddelPerEnhet = virkemiddelPerEnhet;
        super.hvorVanedannende = hvorVanedannende;

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