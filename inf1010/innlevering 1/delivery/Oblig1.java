/*************************

 * Innlevering 1
 * Author: Jan Inge Lamo
 * File name: Oblig1.java
 * Date: 24.01.16

*************************/


class Oblig1
{
    public static void main(String[] args)
    {
        // Create the mouse and rat nests
        Bol<Mus> musebol = new Bol<Mus>();
        Bol<Rotte> rottebol = new Bol<Rotte>();
        
        // We add the cat Tom
        Katt tomKatt = new Katt("Tom");
        
        // Tom goes hunting, but finds nothing
        tomKatt.hunt(musebol, rottebol);
        
        // We add the rat Ronny
        Rotte ronnyRotte = new Rotte("Ronny");
        rottebol.moveIn(ronnyRotte);
        
        // Tom goes hunting for Ronny, hurting him
        tomKatt.hunt(musebol, rottebol);
        
        // We add the mouse Jerry for Tom to kill :)
        Mus jerryMus = new Mus("Jerry");
        musebol.moveIn(jerryMus);
        
        // We try to add another mouse but the mouse nest is already full
        Mus mickeyMus = new Mus("Mickey");
        musebol.moveIn(mickeyMus);
        
        // Tom goes hunting for Jerry
        tomKatt.hunt(musebol, rottebol);
    }
}
