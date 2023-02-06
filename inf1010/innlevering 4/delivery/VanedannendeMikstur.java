/*************************

 * Innlevering 4
 * Author: Jan Inge Lamo
 * File name: VanedannendeMikstur.java
 * Date: 23.02.16

*************************/


class VanedannendeMikstur extends Vanedannende
{

    public VanedannendeMikstur(String navn, int pris, int id, int antallEnheter,
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
}