class Oppgave54 {
    public static void main(String[] args) {
        
        Isbod ansatt = new Isbod();
        
        // Fire the lazy bastard
        System.out.println("Du proover aa sparke den siste ansatte.");
        ansatt.giSistemannSparken();
        
        // You forgot that you already fired him last week!
        // You need to hire fresh blood.
        System.out.println();
        System.out.println("Du ansetter 3 personer fra gata.");
        ansatt.ansett("Robert Baratheon");
        ansatt.ansett("Tywin Lannister");
        ansatt.ansett("Estaban Rodriguez");
        
        // Read the employee list because you forgot their names
        System.out.println();
        System.out.println("Liste over de ansatte:");
        ansatt.printAlleAnsatte();
        
        // Fire that last one because he's not a Song of Ice and Fire character
        System.out.println();
        System.out.println("Du sparker den siste ansatte ansatte.");
        ansatt.giSistemannSparken();
        
        // Gloat over your employee names
        System.out.println();
        System.out.println("Liste over de gjenvaerende ansatte:");
        ansatt.printAlleAnsatte();
        
    }
}
