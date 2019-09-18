package com.dc.tenPinBowling;

import junit.framework.Test;
import junit.framework.TestCase;
import junit.framework.TestSuite;

/**
 * Unit test for simple App.
 */
public class ChanceTest
    extends TestCase
{
    /**
     * Create the test case
     *
     * @param testName name of the test case
     */
    public ChanceTest( String testName )
    {
        super( testName );
    }

    /**
     * @return the suite of tests being tested
     */
    public static Test suite()
    {
        return new TestSuite( ChanceTest.class );
    }

    /**
     * Test create from line success
     */
    public void testChanceSuccess()
    {
        try {
          String line = "Jeff 10";
          Chance chance = Chance.createFromLine(0, line);
          assertEquals(chance.getPlayer(), "Jeff");
        } catch (IncorrectFormatException e) { }
    }

    /**
     * Test create from line  fails
     */
    public void testChanceFail()
    {
        Integer index = 0;
        String line = "Jeff";
        try {
          Chance chance = Chance.createFromLine(0, line);
        } catch (IncorrectFormatException e) {
            assertEquals(e.getMessage(), String.format("Incorrect definition on line %d: \"%s\", expected 2 parameters receive %d", index, line, 1));
        }

        line = " 10";
        try {
          Chance chance = Chance.createFromLine(0, line);
        } catch (IncorrectFormatException e) {
            assertEquals(e.getMessage(), String.format("Incorrect definition on line %d: \"%s\", player name is required", index + 1, line));
        }

        line = "Jeff 12";
        try {
          Chance chance = Chance.createFromLine(0, line);
        } catch (IncorrectFormatException e) {
            assertEquals(e.getMessage(), String.format("Incorrect definition on line %d: \"%s\", chance has to be a number from 0 to 10 or F, found: %s", index + 1, line, "12"));
        }
    }    
}
