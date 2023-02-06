/*************************

 * Innlevering 6
 * Author: Jan Inge Lamo
 * File name: EldsteForstReseptListe.java
 * Date: 12.03.16

*************************/


class EldsteForstReseptListe extends EnkelReseptListe
{
    /**
     * Append a Resepter object to the last pair in the chain link
     * 
     * @param e the Resepter object
     */
    @Override
    public void put(Resepter e)
    {
        foran.lastNode().appendNode(new Node(e, null));
    }
}