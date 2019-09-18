package com.dc.tenPinBowling;

import java.io.IOException;

/** Ten Pin Bowling Score application
 * @author Daniel Coellar
 * @version 1.0
*/
public class App 
{
    /** Staring point of the application.
    * @param args Array of string, recives only one with is the file name
    */
    public static void main( String[] args )
    {
        try {
            Game game = new TenPinBowlingGame(args[0]);
            game.processResults();
            game.printResults();
        } catch (IOException e) {
            System.out.printf("ERROR: %s\n", e.getMessage());
        } catch (IncorrectFormatException e) {
            System.out.printf("ERROR: %s\n", e.getMessage());
        } catch (IncorrectThrowException e) {
            System.out.printf("ERROR: %s\n", e.getMessage());
        }
    }
}
