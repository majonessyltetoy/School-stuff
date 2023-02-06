/*************************

 * Innlevering 2
 * Author: Jan Inge Lamo
 * File name: TilUtlaan.java
 * Date: 09.02.16

*************************/


interface TilUtlaan
{
    /**
     * Borrow this book instance to a person identified with a name.
     * 
     * @param name the name of the person who is going to borrow the book
     * @return     true if operation was successful, necessary for testing unit
     */
    public boolean borrowBook(String name);

    /**
     * Return the book from the borrower.
     *
     * @return  true if the operation was successful, for unit testing purposes
     */
    public boolean returnBook();
}