/*************************

 * Innlevering 4
 * Author: Jan Inge Lamo
 * File name: Pasienter.java
 * Date: 16.03.16

*************************/


class Pasienter
{
    private String navn;

    private YngsteForstReseptListe pasientResepter = new YngsteForstReseptListe();

    public Pasienter(String navn)
    {
        this.navn = navn;
    }

    public void receivePrescription(Resepter newPrescription)
    {
        pasientResepter.put(newPrescription);
    }
}
    