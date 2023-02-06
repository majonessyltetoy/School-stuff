class Deloppgave61 {
    public static void main(String[] args) {
        Ord forsteOrd = new Ord("hello");
        Ord andreOrd = new Ord("world");
        System.out.println("Forste ord antall: " + forsteOrd.hentAntall());
        System.out.println("Andre ord antall: " + andreOrd.hentAntall());       
        System.out.println("Forste ord tekst: " + forsteOrd.toString());
        System.out.println("Andre ord tekst: " + andreOrd.toString());
        System.out.println("Oek andre ord");
        andreOrd.oekAntall();
        System.out.println("Andre ord antall: " + andreOrd.hentAntall());       
    }
}

