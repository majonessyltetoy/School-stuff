/*************************

 * Innlevering 6
 * Author: Jan Inge Lamo
 * File name: TestYngsteForstReseptListe.java
 * Date: 12.03.16

*************************/


class TestYngsteForstReseptListe extends TestUnit
{
    public static void main(String[] args) throws ObjectNonExistingException
    {
        System.out.println("### TESTING YngsteForstReseptListe ###");

        YngsteForstReseptListe prescriptions = new YngsteForstReseptListe();
        Legemidler coughSyrup = new Legemidler("Cough Syrup", 0);
        Legemidler cocain = new Legemidler("Cocain", 53);
        Legemidler heroin = new Legemidler("Heroin", 23);
        Legemidler alch = new Legemidler("Alcholoh", 99);
        Resepter prescription2 = new Resepter(1, coughSyrup);
        prescriptions.put(new Resepter(0, cocain));
        prescriptions.put(prescription2);
        prescriptions.put(new Resepter(2, heroin));
        prescriptions.put(new Resepter(3, alch));
        prescriptions.put(new Resepter(4, new Legemidler("green tea", 8)));
        prescriptions.remove(3);
        testUnit("Testing find() and indirectly put()", prescription2,
            prescriptions.find(1));
        testUnitException("Testing throw exception",
            10, prescriptions);
        testUnit("Visual test that it prints the youngest first", null, null);
        prescriptions.iterate();
    }
}