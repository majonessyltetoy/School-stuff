/**
 * Monitor class for containing finished sorted arrays
 * 
 * @author Victoria Ariel Bjoernestad
 * @author Jan Inge Lamo
 */
public class Monitor {
    private int lengde;
    boolean ferdig;
    private String[] minArray;
    
    /**
     * Monitor constructor
     * 
     * @param  lengde total length of the list to sort
     */
    public Monitor(int lengde) {
        minArray=null;
        this.lengde=lengde;
    }
    /**
     * Recieve finished sorted arrays from threads
     * 
     * @param array sorted array
     */
    synchronized public void faaSortert(String[] array) {
        if(minArray==null) {
            minArray=array;
            if(minArray.length==lengde) {
                ferdig=true;
                notify();
            }
        } else {
            new Sorter(this, minArray, array).start();
            minArray=null;
        }
    }

    /**
     * Wait for the last sorted array
     * 
     * @throws Exception InterruptedException if thread is interrupted
     */
    synchronized public void vent() throws Exception {
        while(!ferdig) {
            try {
                wait();
            }
            catch(InterruptedException e) {
                System.out.println("Exception: " + e);
                System.exit(-1);
            }
        }
    }

    /**
     * Return the finished sorted array to the main class
     * 
     * @return sorted array
     */
    synchronized public String[] giFerdigSortert() {
        return minArray;
    }
    
}