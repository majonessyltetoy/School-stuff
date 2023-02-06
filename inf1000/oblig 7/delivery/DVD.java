class DVD {
    
    private String name;
    private Person eier;
    private Person laaner = null;

    /**
     * The constructor for DVD class
     * 
     * @param name  the DVD name
     * @param owner the name of the owner
     */
    DVD(String name, Person owner) {
        this.name = name;
        eier = owner;
    }
    
    /**
     * Returns the string name of the DVD
     */
    public String toString() {
        return name;
    }
    
    /**
     * Registers that the DVD instance is rented, calls receiveDVD to send this
     * DVD to the renters archive
     * 
     * @param borrower the Person object of the renter
     */
    public void borrow(Person borrower) {
        laaner = borrower;
        laaner.receiveDVD(this);
    }
    
    /**
     * Return this DVD to it's original owner
     */
    public void returnDVD() {
        eier.returnTo(this);
        laaner = null;
    }
    
    /**
     * Returns the Person object of who owns this DVD
     */
    public Person owner() {
        return eier;
    }
    
    /**
     * Returns the Person object of who rents this DVD
     * (default value is null)
     */
    public Person borrower() {
        return laaner;
    }
}
