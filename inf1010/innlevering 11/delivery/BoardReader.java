import java.io.File;
import java.util.Scanner;

class BoardReader
{
    private final int MAX_SUDOKU_SIZE = 64*64;
    private int length;
    private int width;
    private int size;

    /**
     * Parses a sudoku file and outputs a Brett object
     * 
     * @param  file      file to be read
     * @return           Brett version of the file
     * @throws Exception miscellaneous exceptions specified by assignment
     */
    public Brett lesFil(File file) throws Exception
    {
        Scanner read = new Scanner(file);

        String line = "";
        int v = 0; // value of character
        int p = 0; // place on board counter
        int l = 0; // line count


        width = Integer.parseInt(read.nextLine());
        length = Integer.parseInt(read.nextLine());

        size = width * length;

        if ((size*size)>MAX_SUDOKU_SIZE)
        {
            throw new FileExceedMaxSudokuSizeException();
        }

        Brett board = new Brett(width, length);

        while (read.hasNextLine())
        {
            l++;
            line = read.nextLine();
            if (l>=(size+1) || line.length()!=size)
            {
                throw new FileNotAdhereDefinitionException("size:"+size+"; x:"+line.length()+" y:"+l);
            }
            for (int n=0; n<size; n++)
            {
                v = tegnTilVerdi(line.charAt(n));

                if (v>size)
                {
                    throw new ValueOutsideLegalRangeException(""+v);
                }

                board.place(p).setValue(v);
                p++;
            }
        }
        if (l!=size)
        {
            throw new FileNotAdhereDefinitionException("size:"+size+"; x:"+line.length()+" y:"+l);
        }
        read.close();
        return board;
    }

    /**
     * Provided method
     */
    public int tegnTilVerdi(char tegn) throws Exception
    {
        if (tegn == '.')
        {
            return 0;
        }
        else if ('1' <= tegn && tegn <= '9')
        {
            return tegn - '0';
        }
        else if ('A' <= tegn && tegn <= 'Z')
        {
            return tegn - 'A' + 10;
        }
        else if (tegn == '@')
        {
            return 36;
        }
        else if (tegn == '#')
        {
            return 37;
        }
        else if (tegn == '&')
        {
            return 38;
        }
        else if ('a' <= tegn && tegn <= 'z')
        {
            return tegn - 'a' + 39;
        }
        else
        {
            throw new InValidCharException("" + tegn);
        }
    }
}