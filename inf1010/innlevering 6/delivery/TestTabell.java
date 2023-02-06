/*************************

 * Innlevering 6
 * Author: Jan Inge Lamo
 * File name: TestTabell.java
 * Date: 12.03.16

*************************/


class TestTabell extends TestUnit
{
    public static void main(String[] args)
    {
        System.out.println("### TESTING Tabell ###");

        Tabell<Legemidler> tabell = new Tabell<Legemidler>(50);
        Legemidler coughSyrup = new Legemidler("Cough Syrup", 0);
        Legemidler aspirin = new Legemidler("Aspirin", 1);
        Legemidler methadone = new Legemidler("Methadone", 2);

        testUnit("Testing put()", true, tabell.put(coughSyrup,
            coughSyrup.returnID()));
        tabell.put(aspirin, aspirin.returnID());
        tabell.put(methadone, methadone.returnID());
        testUnit("Testing put() on occupied slot", false,
            tabell.put(aspirin, 0));
        testUnit("Testing put() outside the array size", false,
            tabell.put(aspirin, 50));
        testUnit("Testing find()", aspirin, tabell.find(aspirin.returnID()));
        testUnit("Testing find() non-existing drug", null, tabell.find(34));
        testUnit("Visual test iterate(), three drugs in the list", null, null);
        tabell.iterate();
    }
}