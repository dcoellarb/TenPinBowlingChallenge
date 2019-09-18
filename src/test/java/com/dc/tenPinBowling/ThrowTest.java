package com.dc.tenPinBowling;

import junit.framework.Test;
import junit.framework.TestCase;
import junit.framework.TestSuite;

/**
 * Unit test for simple App.
 */
public class ThrowTest
    extends TestCase
{
    /**
     * Create the test case
     *
     * @param testName name of the test case
     */
    public ThrowTest( String testName )
    {
        super( testName );
    }

    /**
     * @return the suite of tests being tested
     */
    public static Test suite()
    {
        return new TestSuite( ThrowTest.class );
    }

    /**
     * Test set score success
     */
    public void testThrowSuccess()
    {
        try {
          Throw testThrow = new Throw(0);
          testThrow.setScore(0, Chance.createFromLine(0, "Jeff 10"));
          assertTrue(testThrow.getScores()[0] == 10);
        } catch (IncorrectThrowException e) { }
    }

    /**
     * Test set score fails
    */   
    public void testThrowFail()
    {
        Throw testThrow = new Throw(0);
        try {
            testThrow.setScore(2, Chance.createFromLine(2, "Jeff 10"));
        } catch (IncorrectThrowException e) {
            assertEquals(e.getMessage(), String.format("Incorrect definition on line %d: \"%s\", there can be only 2 chances per throw", 3, "Jeff 10"));
        }

        try {
            testThrow.setScore(0, Chance.createFromLine(0, "Jeff 10"));
            testThrow.setScore(1, Chance.createFromLine(1, "Jeff 3"));
        } catch (IncorrectThrowException e) {
            assertEquals(e.getMessage(), String.format("Incorrect definition on line %d: \"%s\", previous chance was already 10 there should not be a second chance", 2, "Jeff 3"));
        }

        try {
            testThrow.setScore(0, Chance.createFromLine(0, "Jeff 8"));
            testThrow.setScore(1, Chance.createFromLine(1, "Jeff 3"));
        } catch (IncorrectThrowException e) {
            assertEquals(e.getMessage(), String.format("Incorrect definition on line %d: \"%s\", the maximun sum of chances is 10, found %s + %s", 2, "Jeff 3", 8, 3));
        }

        testThrow = new Throw(9);
        try {
            testThrow.setScore(3, Chance.createFromLine(9, "Jeff 8"));
        } catch (IncorrectThrowException e) {
            assertEquals(e.getMessage(), String.format("Incorrect definition on line %d: \"%s\", there can be only 3 chances in the last throw", 10, "Jeff 8"));
        }

        try {
            testThrow.setScore(0, Chance.createFromLine(9, "Jeff 3"));
            testThrow.setScore(1, Chance.createFromLine(10, "Jeff 3"));
            testThrow.setScore(2, Chance.createFromLine(11, "Jeff 8"));
        } catch (IncorrectThrowException e) {
            assertEquals(e.getMessage(), String.format("Incorrect definition on line %d: \"%s\", player should not have a third chance in this last throw", 12, "Jeff 8"));
        }

        try {
            testThrow.setScore(0, Chance.createFromLine(9, "Jeff 10"));
            testThrow.setScore(1, Chance.createFromLine(10, "Jeff 3"));
            testThrow.setScore(2, Chance.createFromLine(11, "Jeff 8"));
        } catch (IncorrectThrowException e) {
            assertEquals(e.getMessage(), String.format("Incorrect definition on line %d: \"%s\", the last 2 chances can not sum more that 10", 12, "Jeff 8"));
        }

        try {
            testThrow.setScore(0, Chance.createFromLine(9, "Jeff 3"));
            testThrow.setScore(1, Chance.createFromLine(10, "Jeff 8"));
            testThrow.setScore(2, Chance.createFromLine(11, "Jeff 8"));
        } catch (IncorrectThrowException e) {
            assertEquals(e.getMessage(), String.format("Incorrect definition on line %d: \"%s\", the first 2 chances of the last throw can not sum more that 10 if the first chance was not 10", 11, "Jeff 8"));
        }   
    } 

/**
     * Test print
    */   
    public void testThrowPrint()
    {
        Throw testThrow = new Throw(0);
        testThrow.setScore(0, Chance.createFromLine(0, "Jeff 10"));
        assertEquals(testThrow.printScores(false), String.format("%-4s%-4s", "", "X"));

        testThrow = new Throw(0);
        testThrow.setScore(0, Chance.createFromLine(0, "Jeff 7"));
        testThrow.setScore(1, Chance.createFromLine(1, "Jeff 3"));
        assertEquals(testThrow.printScores(false), String.format("%-4d%-4s", 7, "/"));

        testThrow = new Throw(0);
        testThrow.setScore(0, Chance.createFromLine(0, "Jeff 7"));
        testThrow.setScore(1, Chance.createFromLine(1, "Jeff 2"));
        assertEquals(testThrow.printScores(false), String.format("%-4d%-4d", 7, 2));


        testThrow = new Throw(9);
        testThrow.setScore(0, Chance.createFromLine(0, "Jeff 10"));
        testThrow.setScore(1, Chance.createFromLine(1, "Jeff 10"));
        testThrow.setScore(2, Chance.createFromLine(1, "Jeff 10"));
        assertEquals(testThrow.printScores(true), String.format("%-4s%-4s%-4s\n", "X", "X", "X"));

        testThrow = new Throw(9);
        testThrow.setScore(0, Chance.createFromLine(0, "Jeff 10"));
        testThrow.setScore(1, Chance.createFromLine(1, "Jeff 10"));
        testThrow.setScore(2, Chance.createFromLine(1, "Jeff 7"));
        assertEquals(testThrow.printScores(true), String.format("%-4s%-4s%-4d\n", "X", "X", 7));

        testThrow = new Throw(9);
        testThrow.setScore(0, Chance.createFromLine(0, "Jeff 10"));
        testThrow.setScore(1, Chance.createFromLine(1, "Jeff 3"));
        testThrow.setScore(2, Chance.createFromLine(1, "Jeff 7"));
        assertEquals(testThrow.printScores(true), String.format("%-4s%-4d%-4s\n", "X", 3, "/"));

        testThrow = new Throw(9);
        testThrow.setScore(0, Chance.createFromLine(0, "Jeff 10"));
        testThrow.setScore(1, Chance.createFromLine(1, "Jeff 3"));
        testThrow.setScore(2, Chance.createFromLine(1, "Jeff 5"));
        assertEquals(testThrow.printScores(true), String.format("%-4s%-4d%-4d\n", "X", 3, 5));

        testThrow = new Throw(9);
        testThrow.setScore(0, Chance.createFromLine(0, "Jeff 0"));
        testThrow.setScore(1, Chance.createFromLine(1, "Jeff 10"));
        testThrow.setScore(2, Chance.createFromLine(1, "Jeff 10"));
        assertEquals(testThrow.printScores(true), String.format("%-4d%-4s%-4s\n", 0, "/", "X"));

        testThrow = new Throw(9);
        testThrow.setScore(0, Chance.createFromLine(0, "Jeff 7"));
        testThrow.setScore(1, Chance.createFromLine(1, "Jeff 3"));
        testThrow.setScore(2, Chance.createFromLine(1, "Jeff 5"));
        assertEquals(testThrow.printScores(true), String.format("%-4d%-4s%-4d\n", 7, "/", 5));

        testThrow = new Throw(9);
        testThrow.setScore(0, Chance.createFromLine(0, "Jeff 7"));
        testThrow.setScore(1, Chance.createFromLine(1, "Jeff 3"));
        testThrow.setScore(2, Chance.createFromLine(1, "Jeff 10"));
        assertEquals(testThrow.printScores(true), String.format("%-4d%-4s%-4s\n", 7, "/", "X"));

        testThrow = new Throw(9);
        testThrow.setScore(0, Chance.createFromLine(0, "Jeff 5"));
        testThrow.setScore(1, Chance.createFromLine(1, "Jeff 3"));
        assertEquals(testThrow.printScores(true), String.format("%-4d%-4d\n", 5, 3));
    }
}
