/*************************

 * Innlevering 2
 * Author: Jan Inge Lamo
 * File name: GenHylle.java
 * Date: 09.02.16

*************************/


interface GenHylle<E>
{
    /**
     * Checks the size of the shelf.
     * 
     * @return the size
     */
    public int getSize();

    /**
     * Put a book on the shelf.
     * 
     * @param t     the generic type, probably a book
     * @param space where to put the book
     * @return      true if the operation was successful, false otherwise
     */
    public boolean putBook(E t, int space);

    /**
     * Check if space on the shelf is vacant.
     * @param  space the space we want to check
     * @return       true if it is vacant, false otherwise
     */
    public boolean isVacant(int space);

    /**
     * Take a book from the shelf.
     * @param  space the space where the book we will take is
     * @return       the generic type object, probably a Bok object
     */
    public E takeBook(int space);
}