/*************************

 * Innlevering 6
 * Author: Jan Inge Lamo
 * File name: TestUnit.java
 * Date: 12.03.16

*************************/


class TestUnit
{
    /**
     * Primary test unit
     * Takes a description, expected output and the method we want to test.
     * If the expected output and the actual output are equal, than "OK" will be
     * printed, however if they are not equal then "FAIL!" and the two outputs
     * will be printed.
     * 
     * @param  descr  description of the test we are running, for readability
     * @param  expect expected output
     * @param  output method that returns the actual output
     */
    public static <T> void testUnit(String descr, T expect, T output)
    {
        System.out.println();
        System.out.println(descr);

        if (expect != null && expect.equals(output))
        {
            System.out.println("OK");
        }
        else if (expect == output)
        {
            System.out.println("OK");
        }
        else
        {
            System.out.println("FAIL!\tExpected: " + expect +
                               "\tOutput: " + output);
        }
    }

    /**
     * Test unit for exception throwing
     * Unlike testUnit() above is this method for testing if EnkelReseptListe
     * and it's subclasses can throw exceptions when Resepter objects are not 
     * found with find(). Try-catch has some hard coded EnkelReseptListe names
     * because that class was the only one that required throwing exceptions.
     * 
     * @param  descr  description of what we are testing
     * @param  i      ID number of the Resepter object
     * @param  output the EnkelReseptListe object
     */
    public static <T> void testUnitException(String descr, int i, T output)
    {
        boolean caught = false;
        System.out.println();
        System.out.println(descr);

        try
        {
            ((EnkelReseptListe)output).find(i);
        }
        catch (ObjectNonExistingException ex)
        {
            caught = true;
            System.out.println("OK");
        }
        if (!caught)
        {
            System.out.println("FAIL!\tDid not catch an exception");
        }

    }
}