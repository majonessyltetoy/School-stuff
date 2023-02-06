class Oblig6 {
    public static void main(String[] args) throws Exception {
        
        Ordliste scarlet = new Ordliste();
        
        scarlet.lesBok("scarlet.text");
        System.out.println("Det forekommer " + scarlet.antallOrd() +
                           " unike ord i filen");
        System.out.println("Ordet \"Holmes\" forekommer " +
                           scarlet.antallForekomster("Holmes") + " ganger");
        System.out.println("Ordet \"elementary\" forekommer " +
                           scarlet.antallForekomster("elementary") + " ganger");
        System.out.println("Det ordet som forekommer flest ganger er \"" +
                           scarlet.vanligste() + "\"");
    }
}
