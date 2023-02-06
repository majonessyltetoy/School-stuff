/*************************

 * Innlevering 4
 * Author: Jan Inge Lamo
 * File name: Pasienter.java
 * Date: 23.02.16

*************************/


class Pasienter
{
    private String navn;
    private int personNr;
    private String adresse;
    private int postNr;

    private int penger;
    HashMap<int,Legemidler> resepter = new HashMap<>();

    public Pasienter(String navn, int persNr, String adresse, int postNr)
    {
        this.navn = navn;
        personNr = persNr;
        this.adresse = adresse;
        this. postNr = postNr;
    }

    public Legemidler brukResept(int reseptID)
    {
        Legemidler e;
        e=resepter.get(reseptID).bruk(resepter.get(reseptID).returnPris())
        if (e != null)
        {
            penger = penger - resepter.get(reseptID).returnPris();
        }
        return e;
    }
}
    