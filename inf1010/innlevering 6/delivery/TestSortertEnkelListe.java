/*************************

 * Innlevering 6
 * Author: Jan Inge Lamo
 * File name: TestSortertEnkelListe.java
 * Date: 12.03.16

*************************/


class TestSortertEnkelListe extends TestUnit
{
    public static void main(String[] args) throws ObjectNonExistingException
    {
        System.out.println("### TESTING SortertEnkelListe ###");

        SortertEnkelListe sorted = new SortertEnkelListe();
        Leger kim = new Leger("Kim");
        sorted.put(new Leger("Hans"));
        sorted.put(new Leger("Rolf"));
        sorted.put(kim);
        sorted.put(new Leger("Adolf"));
        sorted.put(new Leger("XYZ"));
        testUnit("Testing find() and indirectly put()", kim,
            sorted.find("Kim"));
        
        System.out.println("Test find() on non-existing element");
        boolean caught = false;
        try 
        {
            sorted.find("Olav");
        }
        catch (ObjectNonExistingException ex)
        {
            caught = true;
            System.out.println("OK");
        }
        if (!caught)
        {
            System.out.println("FAIL! Did not catch exception");
        }
        sorted.remove("Hans");
        testUnit("Visual test if doctors are printed alphabetical", null, null);
        sorted.iterate();
    }
}
