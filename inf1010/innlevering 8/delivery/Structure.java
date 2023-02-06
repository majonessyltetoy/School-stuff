import java.util.ArrayList;

class Structure
{
    private ArrayList<Integer> exist = new ArrayList<Integer>();

    public void add(int i)
    {
        exist.add(i);
    }

    public ArrayList<Integer> taken()
    {
        return exist;
    }
}