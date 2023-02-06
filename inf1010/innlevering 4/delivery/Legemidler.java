/*************************

 * Innlevering 4
 * Author: Jan Inge Lamo
 * File name: Legemidler.java
 * Date: 23.02.16

*************************/


class Legemidler 
{
    protected String navn;
    protected int pris;
    protected int id; // unique number
    protected int totalVirkemiddel;
    protected int antallEnheter;   // I was having an existensial crisis whether
    protected int virkemiddelPerEnhet; // the pill count should be in the pill
                                       // class or if it should be more abstract
                                       // because an Legemiddel must have some
                                       // unit count, regardless what's in it.
                                       // That is why Legemidler require a unit
                                       // count.

    public Legemidler(String navn, int pris, int id, int antallEnheter,
        int virkemiddelPerEnhet)
    {
        this.navn = navn;
        this.pris = pris;
        this.id = id;
        this.totalVirkemiddel = antallEnheter * virkemiddelPerEnhet;
        this.antallEnheter = antallEnheter;
        this.virkemiddelPerEnhet = virkemiddelPerEnhet;
    }

    public int returnID()
    {
        return id;
    }


}

