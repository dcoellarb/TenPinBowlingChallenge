package com.dc.tenPinBowling;

import junit.framework.Test;
import junit.framework.TestCase;
import junit.framework.TestSuite;

/**
 * Unit test for simple App.
 */
public class PlayerTest
    extends TestCase
{
    /**
     * Create the test case
     *
     * @param testName name of the test case
     */
    public PlayerTest( String testName )
    {
        super( testName );
    }

    /**
     * @return the suite of tests being tested
     */
    public static Test suite()
    {
        return new TestSuite( PlayerTest.class );
    }

    /**
     * Test calculate points
     */
    public void testPlayerSuccess()
    {
        try {
          Player player = new Player("Jeff");
          
          Throw testThrow = new Throw(0);
          testThrow.setScore(0, Chance.createFromLine(0, "Jeff 10"));
          player.setThrow(0, testThrow);

          testThrow = new Throw(1);
          testThrow.setScore(0, Chance.createFromLine(1, "Jeff 7"));
          testThrow.setScore(1, Chance.createFromLine(2, "Jeff 3"));
          player.setThrow(1, testThrow);

          testThrow = new Throw(2);
          testThrow.setScore(0, Chance.createFromLine(3, "Jeff 9"));
          testThrow.setScore(1, Chance.createFromLine(4, "Jeff 0"));
          player.setThrow(2, testThrow);

          testThrow = new Throw(3);
          testThrow.setScore(0, Chance.createFromLine(5, "Jeff 10"));
          player.setThrow(3, testThrow);

          testThrow = new Throw(4);
          testThrow.setScore(0, Chance.createFromLine(6, "Jeff 0"));
          testThrow.setScore(1, Chance.createFromLine(7, "Jeff 8"));
          player.setThrow(4, testThrow);

          testThrow = new Throw(5);
          testThrow.setScore(0, Chance.createFromLine(8, "Jeff 8"));
          testThrow.setScore(1, Chance.createFromLine(9, "Jeff 2"));
          player.setThrow(5, testThrow);

          testThrow = new Throw(6);
          testThrow.setScore(0, Chance.createFromLine(10, "Jeff F"));
          testThrow.setScore(1, Chance.createFromLine(11, "Jeff 6"));
          player.setThrow(6, testThrow);

          testThrow = new Throw(7);
          testThrow.setScore(0, Chance.createFromLine(12, "Jeff 10"));
          player.setThrow(7, testThrow);

          testThrow = new Throw(8);
          testThrow.setScore(0, Chance.createFromLine(13, "Jeff 10"));
          player.setThrow(8, testThrow);

          testThrow = new Throw(9);
          testThrow.setScore(0, Chance.createFromLine(14, "Jeff 10"));
          testThrow.setScore(1, Chance.createFromLine(15, "Jeff 8"));
          testThrow.setScore(2, Chance.createFromLine(16, "Jeff 1"));
          player.setThrow(9, testThrow);

          player.calculateScore();

          assertTrue(player.getThrows()[9].getPoints() == 167);
        } catch (IncorrectThrowException e) { }
    }
}
