class NegativeTall {
    public static void main(String[] args) {
     
        // Variables
        int teller = 0;
        int neg_teller = 0;
        // int arrayen
        int[] heltall = {1, 4, 5, -2, -4, 6, 10, 3, -2};
     
        // a)
        // lets find out how many negatives there are!
        while (teller != heltall.length) {
            if (heltall[teller] < 0)
                neg_teller++;
            teller++;
        }
        System.out.println("Det var " + neg_teller + " negative tall");
        
        
        // b)
        teller = 0;
        while (teller != heltall.length) {
            if (heltall[teller] < 0) 
                // replace the negative integer with its position
                heltall[teller] = teller;
            teller++;
        }
        
        
        // c)
        System.out.print("Heltall arrayen ser naa slik ut:\n{ ");
        for (int value : heltall)
            System.out.print(value + " ");
        System.out.print("}\n");
    }
}
