class Isbod {
    
    // Variables
    private String[] ansatte = new String[10];
    private int nrAnsatte = 0;
    
    
    // Method that hires employee.
    public void ansett(String navn) {
        if (nrAnsatte < 10) {
            ansatte[nrAnsatte] = navn;
            nrAnsatte++;
        }
        else {
            System.out.println("Ingen ledige stillinger.");
        }
    }
    
    
    // Fire the last employed employee
    public void giSistemannSparken() {
        if (nrAnsatte > 0) {
            nrAnsatte--;
            ansatte[nrAnsatte] = "";
        }
        else {
            System.out.println("Du har ingen ansatte.");
        }
    }
    
    
    // Print list of employees
    public void printAlleAnsatte() {
        for (int i=0; i<nrAnsatte; i++) {
            System.out.println(ansatte[i]);
        }
    }
}
