class Deloppgave62 {
    public static void main(String[] args) throws Exception {
        
        Ordliste liste = new Ordliste();
        
        liste.lesBok("scarlet.text");
        System.out.println("Ordet \"test\" forekom " + 
                           liste.finnOrd("test").hentAntall() + " ganger");
        System.out.println("Tilsammen er det " + liste.antallOrd() +
                           " unike ord");
        
        // This loop prints the most frequent word, if there are two or more
        // equally frequent words, then all of them will be printed.
        System.out.print("Det mest vanlige ordet er: ");
        for (int i=0; i<liste.alleVanligste().length; i++) {
            System.out.print(liste.alleVanligste()[i].toString());
            System.out.print(", ");
        }
        System.out.println();
        
    }
}
