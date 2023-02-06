/*************************

 * Innlevering 6
 * Author: Jan Inge Lamo
 * File name: TestEldsteForstReseptListe.java
 * Date: 12.03.16

*************************/


class TestEldsteForstReseptListe extends TestUnit
{
    public static void main(String[] args) throws ObjectNonExistingException
    {
        System.out.println("### TESTING EldsteForstReseptListe ###");

        EldsteForstReseptListe prescriptions = new EldsteForstReseptListe();
        Legemidler aspirin = new Legemidler("Aspeirin", 1);
        Legemidler cola = new Legemidler("Cola", 90);
        Legemidler syrup = new Legemidler("Cough Syrup", 34);
        Resepter prescription2 = new Resepter(3, aspirin);
        prescriptions.put(new Resepter(0, cola));
        prescriptions.put(prescription2);
        prescriptions.put(new Resepter(2, syrup));
        prescriptions.put(new Resepter(23, new Legemidler("p-pills", 2)));
        prescriptions.put(new Resepter(1, aspirin));
        prescriptions.remove(2);
        testUnit("Testing find() and indirectly put()", prescription2,
            prescriptions.find(1));
        testUnitException("Testing throw exception and that `prcesc3` was" +
            " removed", 2, prescriptions);
        testUnit("Visual test that it prints the oldest first", null, null);
        prescriptions.iterate();
    }
}
