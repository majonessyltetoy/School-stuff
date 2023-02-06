class SudokuBeholder
{
    private Brett[] boardSolutions = new Brett[3500];
    private int countSolutions = 0;
    private int threadFinished = 0;
    private boolean finished = false;

    /**
     * Insert a solved board in the container
     * 
     * @param b solved board
     */
    synchronized public boolean settInn(Brett b)
    {
        if (3500 > countSolutions)
        {
            boardSolutions[countSolutions] = b;
            countSolutions++;
            return false;
        }
        return true;
    }

    /**
     * Return all boards in this container
     * 
     * @return board solutions
     */
    public Brett[] taUt()
    {
        return boardSolutions;
    }

    /**
     * @return number of boards in this container
     */
    public int hentAntallLosninger()
    {
        return countSolutions;
    }

    synchronized public void done()
    {
        if (++threadFinished == 2)
        {
            finished = true;
            notify();
        }
    }

    synchronized public void waitHere() throws Exception
    {
        while(!finished) {
            try {
                wait();
            }
            catch(InterruptedException e) {
                System.out.println("Exception: " + e);
                System.exit(1);
            }
        }
    }
}