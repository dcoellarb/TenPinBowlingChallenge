package com.dc.tenPinBowling;

import java.io.IOException;

/** Abstract Class to represent any Game.
 * @author Daniel Coellar
 * @version 1.0
*/
public  abstract class Game {
  String _inputFile;

  /** All games are created with a file of the results to process
  * @param inputFile String with the name of the file to process
  */
  public Game(String inputFile) {
    _inputFile = inputFile;
  };
  
  /** Abstract methos all games need to implement to process the files
  */
  public abstract void processResults() throws IOException, IncorrectFormatException, IncorrectThrowException;

  /** Abstract methos all games need to implement to print the results
  */
  public abstract void printResults();
}