import java.util.ArrayList;

class Rute
{
    private int value;
    private int place;
    private Brett b;
    private Boks box;
    private Rad row;
    private Kolonne column;
    private int size;
    private SudokuBeholder c;
    private boolean full = false;

    /**
     * Constructor
     * This is called when initializing the empty board
     * 
     * @param  place place on the sudoku board
     */
    public Rute(int p)
    {
        place = p;
    }

    /**
     * Constructor for cloning a solution to this value
     * @param  v value
     * @param  p place on the board
     */
    public Rute(int v, int p)
    {
        value = v;
        place = p;
    }

    /**
     * Constructor for multithreading
     * 
     * @param  v value
     * @param  p place
     * @param  s size of the row/box/column
     * @param  b board this Rute is a part of
     * @param  c container for solved sudoku boards
     */
    public Rute(int v, int p, int s, Brett b, SudokuBeholder c)
    {
        value = v;
        place = p;
        size  = s;
        this.b = b;
        this.c = c;
    }

    /**
     * @return value of this object
     */
    public int value()
    {
        return value;
    }

    /**
     * @return place on the sudoku board
     */
    public int place()
    {
        return place;
    }

    /**
     * Set value of this Rute object
     * Also updates the structure with its new value
     * 
     * @param value Value for this object
     */
    public void setValue(int value)
    {
        this.value = value;

        if (value != 0)
        {
            box.add(value);
            row.add(value);
            column.add(value);
        }
    }

    /**
     * Unsets the value from this object and the structure
     */
    public void unsetValue()
    {
        box.unset(value);
        row.unset(value);
        column.unset(value);
        
        value = 0;
    }

    /**
     * Set the sudoku structure this Rute object is related with.
     * 
     * @param b Boks relationship
     * @param r Rad relationship
     * @param k Kolonne relationship
     */
    public void setStructure(Boks b, Rad r, Kolonne k)
    {
        box = b;
        row = r;
        column = k;

        if (value != 0)
        {
            box.add(value);
            row.add(value);
            column.add(value);
        }
    }  

    /**
     * Finds and return all possible values for this place
     *
     * @return  int array of possible numbers
     */
    public int[] finnAlleMuligeTall()
    {
        if (value == 0)
        {
            ArrayList<Integer> possibleList = new ArrayList<Integer>();
            for (int n=1; n<=size; n++)
            {
                if (!box.taken().contains(n) && !row.taken().contains(n)
                     && !column.taken().contains(n))
                {
                    possibleList.add(n);
                }
            }

            // Remaking the array
            int[] tmp = new int[possibleList.size()];
            int count = 0;
            for (Integer i : possibleList)
            {
                tmp[count++] = i;
            }
            return tmp;
        }
        else
        {
            return new int[0];
        }
    }

    /**
     * Brute force algorithm for solving this sudoku board
     *
     * This algorithm start guessing the places with the fewest possible values.
     * It also does multithreading IF the first place it guesses on has both odd
     * and even numbers as possible values.
     * 
     * @param odd   teels the thread to brute force odd or even
     * @param first keep track that this is the first guess
     * @param n     possibility length we're looking for
     */
    public boolean fyllUtDenneRuteOgResten(boolean odd, boolean first, int n)
    {
        int[] pos = finnAlleMuligeTall();

        if (place == (size*size)-1)
        {
            if (finishedBoard())
            {
                if (value == 0)
                {
                    setValue(pos[0]);
                }
                full = c.settInn(new Brett(b));
                unsetValue();
            }
            else if (possibleLeft())
            {
                full = b.place(0).fyllUtDenneRuteOgResten(odd,false,lowestPossible());
            }
        }
        else
        {
            if (value == 0 && pos.length <= n)
            {
                for (int i=0; i<pos.length; i++)
                {
                    if (first)
                    {
                        if (odd ^ (pos[i] % 2 == 0))
                        {
                            setValue(pos[i]);
                            full = b.place(place+1).fyllUtDenneRuteOgResten(odd,false,lowestPossible());
                            unsetValue();
                            if (full)
                            {
                                break;
                            }
                        }
                    }
                    else
                    {
                        setValue(pos[i]);
                        full = b.place(place+1).fyllUtDenneRuteOgResten(odd,false,n);
                        unsetValue();
                        if (full)
                        {
                            break;
                        }
                    }
                }
            }
            else
            {
                full = b.place(place+1).fyllUtDenneRuteOgResten(odd,first,n);
            }
        }
        return full;
    }

    public int lowestPossible()
    {
        int lowest = size;
        int tmp = 0;

        for (int i=1; i<(size*size)-1; i++)
        {
            tmp = b.place(i).finnAlleMuligeTall().length;
            if (tmp < lowest && tmp != 0)
            {
                lowest = tmp;
            }
        }
        return lowest;
    }
    private boolean finishedBoard()
    {
        for (int i=0; i<(size*size)-1; i++)
        {
            if (b.place(i).value() == 0)
            {
                return false;
            }
        }
        return true;
    }
    private boolean possibleLeft()
    {
        for (int i=0; i<(size*size)-1; i++)
        {
            if (b.place(i).value() == 0 && b.place(i).finnAlleMuligeTall().length > 0)
            {
                return true;
            }
        }
        return false;
    }
}