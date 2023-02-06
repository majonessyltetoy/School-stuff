/*************************

 * Innlevering 2
 * Author: Jan Inge Lamo
 * File name: TestBok.java
 * Date: 09.02.16

*************************/


class TestBok
{
    static public void main(String[] args)
    {
        Bok testBook1 = new Bok("A Brief History of Time", "Hawking");
        Bok testBook2 = new Bok("1Q84", "Murakami");


        System.out.println("Test if the book return the correct title");
        testUnit("A Brief History of Time", testBook1.toString());

        System.out.println("Check that a book is borrow correctly");
        testUnit(true, testBook1.borrowBook("Tom"));

        System.out.println("A book that is borrow cannot be borrow again before"
            + " it is returned");
        testUnit(false, testBook1.borrowBook("Jenny"));

        testBook1.returnBook();
        System.out.println("We returned the book, and try to borrow it again");
        testUnit(true, testBook1.borrowBook("Zhen"));

        System.out.println("Fail to return a book that is not borrowed");
        testUnit(false, testBook2.returnBook());


    }
    /**
     * This is a simple unit tester for the Bok class. The method takes two
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