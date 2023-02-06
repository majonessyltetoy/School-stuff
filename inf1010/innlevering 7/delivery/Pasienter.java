/*************************

 * Innlevering 4
 * Author: Jan Inge Lamo
 * File name: Pasienter.java
 * Date: 16.03.16

*************************/


class Pasienter
{
    private String navn;
    private long persNr;
    private String adresse;
    private int postNr;

    private YngsteForstReseptListe pasientResepter = new YngsteForstReseptListe();

    public Pasienter(String navn, long n, String adr, int p)
    {
        this.navn = navn;
        persNr = n;
        adresse = adr;
        postNr = p;
    }

    public String toString()
    {
        return navn;
    }
    public int returnID()
    {
        return 0;
    }

    public long returnPersNr()
    {
        return persNr;
    }

    public int returnPostNr()
    {
        return postNr;
    }

    public void mottaResept(Resepter resept)
    {
        pasientResepter.put(resept);
    }

    public boolean brukResept(int reseptNr) throws ObjectNonExistingException
    {
        if (pasientResepter.find(reseptNr).brukResept())
        {
            Resepter bruktResept = pasientResepter.find(reseptNr);
            Legemidler hentetMiddel = bruktResept.returnLegemiddel();
            System.out.println("Legemiddel: " + hentetMiddel.toString());
            System.out.println("Legemiddel nummer: " + hentetMiddel.returnID());
            System.out.println("Antall: " + hentetMiddel.returnAntall());
            System.out.println("Virkestoff: " + hentetMiddel.returnVirkestoff());
            System.out.print("Type: ");
            if (hentetMiddel instanceof NarkotiskPiller)
            {
                System.out.println("narkotisk piller");
            }
            else if (hentetMiddel instanceof NarkotiskMikstur)
            {
                System.out.println("narkotisk mikstur");
            }
            else if (hentetMiddel instanceof VanedannendePiller)
            {
                System.out.println("vanedannende piller");
            }
            else if (hentetMiddel instanceof VanedannendeMikstur)
            {
                System.out.println("vanedannende mikstur");
            }
            else if (hentetMiddel instanceof VanligPiller)
            {
                System.out.println("vanlig piller");
            }
            else
            {
                System.out.println("vanlig mikstur");
            }

            if (bruktResept instanceof BlaaResepter)
            {
                System.out.println("Pris: 0");
            }
            else
            {
                System.out.println("Pris: " + hentetMiddel.returnPris());
            }

            System.out.println("Lege: " + bruktResept.returnLege());
            System.out.println("Pasient: " + navn);
            System.out.println("Gjenstaande reit: " + bruktResept.returnReit());
            return true;
        }
        else
        {
            System.out.println("Ugyldig resept!");
            return false;
        }
    }
}
    