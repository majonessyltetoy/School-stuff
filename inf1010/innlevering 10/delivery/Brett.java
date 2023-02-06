import java.util.ArrayList;

class Brett extends Thread
{
    private int width;
    private int length;
    private int size;
    private int totalSize;
    private ArrayList<Rute> board = new ArrayList<Rute>();
    private SudokuBeholder container;
    private boolean odd;

    /**
     * Constructor the sudoku board
     * contains an arraylist for all the places on the board
     * 
     * @param  w width of the box
     * @param  l length of the box
     */
    public Brett(int w, int l)
    {
        width = w;
        length = l;
        size = width*length;
        totalSize = size*size;

        for (int i=0; i<totalSize; i++)
        {
            board.add(new Rute(i));
        }
        opprettDatastruktur();
    }

    /**
     * Constructor for cloning a solved board
     * 
     * @param  b board to clone
     */
    public Brett(Brett board)
    {
        width = board.width();
        length = board.length();
        size = width*length;
        totalSize = size*size;
        ArrayList<Rute> b = board.board();

        for (int i=0; i<totalSize; i++)
        {
            Rute r = b.get(i);
            this.board.add(new Rute(r.value(),r.place()));
        }
    }

    /**
     * Constructor multithreading
     * 
     * @param  board board we're cloning
     * @param  c     container for solved boards
     * @param  odd   determiner of this thread will search odd or even numbers
     */
    public Brett(Brett board, SudokuBeholder c, boolean odd)
    {
        width = board.width();
        length = board.length();
        size = width*length;
        totalSize = size*size;
        container = c;
        ArrayList<Rute> b = board.board();
        this.odd = odd;

        for (int i=0; i<totalSize; i++)
        {
            Rute r = b.get(i);
            this.board.add(new Rute(r.value(),r.place(), size, this,c));
        }
        opprettDatastruktur();
    }

    /**
     * Get the Rute object on a given place on the board
     * 
     * @param  place int from 0 to totalSize
     * @return       Rute object on given place
     */
    public Rute place(int place)
    {
        return board.get(place);
    }

    /**
     * (for cloning)
     * @return box width
     */
    public int width()
    {
        return width;
    }
    /**
     * (for cloning)
     * @return box length
     */
    public int length()
    {
        return length;
    }
    /**
     * (for cloning)
     * @return entire board arraylist
     */
    public ArrayList<Rute> board()
    {
        return board;
    }

    /**
     * Thread starter
     */
    @Override
    public void run()
    {
        board.get(0).fyllUtDenneRuteOgResten(odd, true, board.get(0).lowestPossible());
        container.done();

    }

    /**
     * Set structure for every place on the board
     */
    public void opprettDatastruktur()
    {
        Boks[] boxs = new Boks[size];
        Rad[] rows = new Rad[size];
        Kolonne[] columns = new Kolonne[size];

        for (int i=0; i<size; i++)
        {
            boxs[i] = new Boks();
            rows[i] = new Rad();
            columns[i] = new Kolonne();
        }

        int p = 0; // place on board
        int z; // box number

        for (int y=0; y<size; y++)
        {
            for (int x=0; x<size; x++)
            {
                z = ((y/width)*width)+(x/length);
                board.get(p++).setStructure(boxs[z], rows[y], columns[x]);
            }
        }
    }

    /**
     * Print this board
     */
    public void printBoard(int nr)
    {
        System.out.println();
        int p = 0;
        int t = 0;

        System.out.print(nr+": ");

        for (int x=0; x<size; x+=width)
        {
            for (int y=0; y<width; y++)
            {
                for (int n=0; n<size; n++)
                {
                    System.out.print(verdiTilTegn(board.get(p++).value()));
                }
                System.out.print("//");
            }
        }
        System.out.println();
    }

    /**
     * Print a pretty board
     */
    public void prettyPrintBoard()
    {
        System.out.println();
        int p = 0; // place on board counter
        int t = 0; // tmp counter
        char c;

        for (int x=0; x<size; x+=width)
        {
            for (int y=0; y<width; y++)
            {
                for (int n=0; n<size; n+=length)
                {
                    for (int m=0; m<length; m++)
                    {
                        c = verdiTilTegn(board.get(p++).value());
                        if (c == '.')
                        {
                            System.out.print(' ');
                        }
                        else
                        {
                            System.out.print(c);
                        }
                    }
                    if ((p % size) != 0)
                    {
                        System.out.print('|');
                    }
                    else
                    {
                        System.out.println();
                    }
                }
            }
            if (p != totalSize)
            {
                for (int n=0; n<size; n+=length)
                {
                    for (int m=0; m<length; m++)
                    {
                        System.out.print('-');
                        t++;
                    }
                    if ((t % size) != 0)
                    {
                        System.out.print('+');
                    }
                    else
                    {
                        System.out.println();
                    }
                }
            }
        }
        System.out.println();
    }

    /**
     * Provided method
     */
    public static char verdiTilTegn(int verdi)
    {
        if (verdi == 0)
        {
            return '.';
        }
        else if (1 <= verdi && verdi <= 9)
        {
            return (char) (verdi + '0');
        }
        else if (10 <= verdi && verdi <= 35)
        {
            return (char) (verdi + 'A' - 10);
        }
        else if (verdi == 36)
        {
            return '@';
        }
        else if (verdi == 37)
        {
            return '#';
        }
        else if (verdi == 38)
        {
            return '&';
        }
        else if (39 <= verdi && verdi <= 64)
        {
            return (char) (verdi + 'a' - 39);
        }
        else
        {
            // This code should never be executed.
            // Invalid characters are thrown in tegnTilVerdi().
            System.out.println("Internal error! <Brett>.verdiTilTegn()");
            return '.';
        }
    }
}