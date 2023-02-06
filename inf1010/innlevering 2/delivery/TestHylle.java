/*************************

 * Innlevering 2
 * Author: Jan Inge Lamo
 * File name: TestHylle.java
 * Date: 09.02.16

*************************/


class TestHylle
{
    public static void main(String[] args)
    {
        int shelfSize = 100;
        Hylle<Bok> testShelf = new Hylle<Bok>(shelfSize);
        Bok testBook1 = new Bok("Dorian Gray", "Oscar Wilde");
        Bok testBook2 = new Bok("Lord of the Rings", "Tolkien");
        Bok testBook3 = new Bok("House of Leaves", "Danielewski");

        System.out.println("Test if the shelf is the correct size");
        testUnit(shelfSize, testShelf.getSize());

        testShelf.putBook(testBook1, 43);
        System.out.println("Put book on shelf and check if that space is"
            + " taken");
        testUnit(false, testShelf.isVacant(43));

        testShelf.takeBook(43);
        System.out.println("Take the book and check if the space is now"
            + " vacant");
        testUnit(true, testShelf.isVacant(43));

        testShelf.putBook(testBook1, 10);
        System.out.println("We try to put a book in a space that is already"
            + " taken");
        testUnit(false, testShelf.putBook(testBook2, 10));

        testShelf.putBook(testBook3, 12);
        System.out.println("We check if the object we put on the shelf is"
            + " indeed the same object");
        testUnit(testBook3, testShelf.takeBook(12));

        System.out.println("We will try (and fail) to put a book outside the"
            + " shelf");
        testUnit(false, testShelf.putBook(testBook2, 124));

        System.out.println("We try again to put a book outside the shelf, this"
            + " time on the negative side");
        testUnit(false, testShelf.putBook(testBook2, -32));

        System.out.println("Take a book that does not exist");
        testUnit(null, testShelf.takeBook(0));
    }

    /**
     * This is a simple unit tester for the Hylle class. The method takes two
     * arguments, what is expected and what the program actually outputs, based
     * on this it prints if the operation was succesful or not.
     * 
     * Example output
     * 
     * Expected:    true
     * Output:      false
     * Result:      Fail!
     *
     * 
     * @param  expect expected output
     * @param  output output the program produces
     */
    private static<T> void testUnit(T expect, T output)
    {
        System.out.println("Expected:\t" + expect + "\nOutput:\t\t" + output);
        if (expect == output)
        {
           System.out.println("Result:\t\tSuccess!\n");
        }
        else
        {
           System.out.println("Result:\t\tFail!\n");
        }
    }
}