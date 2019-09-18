package com.dc.tenPinBowling;

/** Represents a chance.
 * @author Daniel Coellar
 * @version 1.0
*/
public class Chance {
  private Integer _index;
  private String _line;
  private String _player;
  private Integer _score;

  /** Gets the chance's index.
  * @return An Integer represents the postion of the chance in the file.
  */
  public Integer getIndex()
  {
    return _index;
  }

  /** Gets the chance's line.
  * @return A String represents line of the chance in the file.
  */
  public String getLine()
  {
    return _line;
  }

  /** Gets the chance's player name.
  * @return A String represents the player name of the chance.
  */
  public String getPlayer()
  {
    return _player;
  }

  /** Gets the chance's score.
  * @return A Integer represents the score of the chance.
  */
  public Integer getScore()
  {
    return this._score;
  }

  /** Create a chance with the specified index, line, player and score.
  * @param index a Integer represents position of the chance in the file.
  * @param line a String represents the line of the chance in the file.
  * @param player a String represents the name of the player of the chance.
  * @param score a Integer represents the score of the chance.
  */
  public Chance(Integer index, String line, String player, Integer score)
  {
    _index = index;
    _line = line;
    _player = player;
    _score = score;
  }

  /** Factory methos to create a chance from a line in the file.
  * @param index a Integer represents position of the chance in the file.
  * @param line a String represents the line of the chance in the file.
  */
  public static Chance createFromLine(Integer index, String line)
    throws IncorrectFormatException
  {
    String[] splittedLine = line.split(" "); // Split line on white space to obtain player name and score
    if (splittedLine.length == 2 && splittedLine[0].length() > 0 && splittedLine[1].toUpperCase().matches("[0-9]|10|F")) {
      // Succesfull validation of a chance, create the chance
      return new Chance(index, line, splittedLine[0], splittedLine[1].equals("F") ? 0 : Integer.parseInt(splittedLine[1]));
    } else {
      if (splittedLine.length != 2) {
        // If not the expected 2 params
        throw new IncorrectFormatException(String.format("Incorrect definition on line %d: \"%s\", expected 2 parameters receive %d", index, line, splittedLine.length));
      } else {
        if (splittedLine[0].length() <= 0) {
          // If name is empty
          throw new IncorrectFormatException(String.format("Incorrect definition on line %d: \"%s\", player name is required", index + 1, line));
        } else if (!splittedLine[1].toUpperCase().matches("[0-9]|10|F")) {
          // If score not formatted correctly
          throw new IncorrectFormatException(String.format("Incorrect definition on line %d: \"%s\", chance has to be a number from 0 to 10 or F, found: %s", index + 1, line, splittedLine[1]));
        } else {
          // Any other case
          throw new IncorrectFormatException(String.format("Incorrect definition on line %d: \"%s\", unknow error", index + 1, line));
        }
      }
    }
  }
}