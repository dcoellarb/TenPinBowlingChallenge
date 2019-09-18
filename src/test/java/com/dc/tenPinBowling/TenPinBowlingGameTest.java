package com.dc.tenPinBowling;

import java.io.IOException;

import junit.framework.Test;
import junit.framework.TestCase;
import junit.framework.TestSuite;

/**
 * Unit test for simple App.
 */
public class TenPinBowlingGameTest
    extends TestCase
{
    /**
     * Create the test case
     *
     * @param testName name of the test case
     */
    public TenPinBowlingGameTest( String testName )
    {
        super( testName );
    }

    /**
     * @return the suite of tests being tested
     */
    public static Test suite()
    {
        return new TestSuite( TenPinBowlingGameTest.class );
    }

    /**
     * Integrations tests
     */
    public void testTenPinBowlingTestSuccess()
    {
        try {
            System.out.println("Running sample.txt");
            Game game = new TenPinBowlingGame("sample.txt");
            game.processResults();
            game.printResults();
            assertTrue(true);

            System.out.println("Running perfectScore.txt");
            game = new TenPinBowlingGame("perfectScore.txt");
            game.processResults();
            game.printResults();
            assertTrue(true);

            System.out.println("Running zeroScore.txt");
            game = new TenPinBowlingGame("zeroScore.txt");
            game.processResults();
            game.printResults();
            assertTrue(true);

            System.out.println("Running perfectScoreOnePlayer.txt");
            game = new TenPinBowlingGame("perfectScoreOnePlayer.txt");
            game.processResults();
            game.printResults();
            assertTrue(true);

            System.out.println("Running zeroScoreOnePlayer.txt");
            game = new TenPinBowlingGame("zeroScoreOnePlayer.txt");
            game.processResults();
            game.printResults();
            assertTrue(true);
        } catch (IOException e) {
            System.out.printf("ERROR: %s\n", e.getMessage());
            assertTrue(false);
        } catch (IncorrectFormatException e) {
            System.out.printf("ERROR: %s\n", e.getMessage());
            assertTrue(false);
        } catch (IncorrectThrowException e) {
            System.out.printf("ERROR: %s\n", e.getMessage());
            assertTrue(false);
        }
    }  

/**
     * Integrations tests
     */
    public void testTenPinBowlingTestFail()
    {
        try {
            Game game = new TenPinBowlingGame("nonexisting.txt");
            game.processResults();
            game.printResults();
            assertTrue(false);
        } catch (IOException e) {
            System.out.printf("ERROR: %s\n", e.getMessage());
            assertTrue(true);
        } catch (IncorrectFormatException e) {
            System.out.printf("ERROR: %s\n", e.getMessage());
            assertTrue(false);
        } catch (IncorrectThrowException e) {
            System.out.printf("ERROR: %s\n", e.getMessage());
            assertTrue(false);
        }

        try {
            Game game = new TenPinBowlingGame("IncorrectFormatException.txt");
            game.processResults();
            game.printResults();
            assertTrue(false);
        } catch (IOException e) {
            System.out.printf("ERROR: %s\n", e.getMessage());
            assertTrue(false);
        } catch (IncorrectFormatException e) {
            System.out.printf("ERROR: %s\n", e.getMessage());
            assertTrue(true);
        } catch (IncorrectThrowException e) {
            System.out.printf("ERROR: %s\n", e.getMessage());
            assertTrue(false);
        } 

        try {
            Game game = new TenPinBowlingGame("IncorrectThrowException.txt");
            game.processResults();
            game.printResults();
            assertTrue(false);
        } catch (IOException e) {
            System.out.printf("ERROR: %s\n", e.getMessage());
            assertTrue(false);
        } catch (IncorrectFormatException e) {
            System.out.printf("ERROR: %s\n", e.getMessage());
            assertTrue(false);
        } catch (IncorrectThrowException e) {
            System.out.printf("ERROR: %s\n", e.getMessage());
            assertTrue(true);
        }                
    }      
}
