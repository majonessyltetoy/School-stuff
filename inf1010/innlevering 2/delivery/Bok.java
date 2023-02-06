/*************************

 * Innlevering 2
 * Author: Jan Inge Lamo
 * File name: Bok.java
 * Date: 09.02.16

*************************/


class Bok implements TilUtlaan
{
    // Variables
    private String title;
    private String author;
    private String borrowName;
    private boolean available;

    /**
     * Constructor for the Bok class. Initiates each instance with the book
     * title and its author.
     * 
     * @param  title  the title of the book
     * @param  author the author of the book
     */
    public Bok(String title, String author)
    {
        this.title = title;
        this.author = author;
        available = true;
    }

    /**
     * Returns the title of the book.
     * 
     * @return the title
     */
    public String toString()
    {
        return title;
    }

    /**
     * Borrow this instance to a person identified with a name.
     * 
     * @param name the name of the person who is going to borrow the book
     * @return     true if the operation was successful, false otherwise
     */
    public boolean borrowBook(String name)
    {
        if (available)
        {
            borrowName = name;
            available = false;
            return true;
        }
        else
        {
            return false;
        }
    }

    /**
     * Return the book from the borrower.
     */
    public boolean returnBook()
    {
        if (available)
        {
            return false;
        }
        else
        {
            borrowName = "";
            available = true;
            return false;
        }
    }
}
