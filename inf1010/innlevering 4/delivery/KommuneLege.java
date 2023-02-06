/*************************

 * Innlevering 4
 * Author: Jan Inge Lamo
 * File name: KommuneLege.java
 * Date: 23.02.16

*************************/


class KommuneLege extends Leger
{
    private int avtalenummer;

    public KommuneLege(String navn, int avtalenummer)
    {
        super(navn);
        super.navn = navn;
        this.avtalenummer = avtalenummer;

    }

    public int hentAvtalenummer()
    {
        return avtalenummer;
    }
    
}