/*************************

 * Innlevering 4
 * Author: Jan Inge Lamo
 * File name: Vanlig.java
 * Date: 23.02.16

*************************/


class Vanlig extends Legemidler
{

    public Vanlig(String navn, int pris, int id, int antallEnheter,
        int virkemiddelPerEnhet)
    {
        super(navn, pris, id, antallEnheter, virkemiddelPerEnhet);
        super.navn = navn;
        super.pris = pris;
        super.id = id;
        super.totalVirkemiddel = antallEnheter * virkemiddelPerEnhet;
        super.antallEnheter = antallEnheter;
        super.virkemiddelPerEnhet = virkemiddelPerEnhet;
    }
}
