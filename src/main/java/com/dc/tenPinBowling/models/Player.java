package com.dc.tenPinBowling;

/** Represents a player.
 * @author Daniel Coellar
 * @version 1.0
*/
public class Player
{
  private String _name;
  private Throw[] _throws;

  /** Gets the player's  name.
  * @return A string representing the player's name
  */
  public String getName()
  {
    return this._name;
  }

  /** Sets A Throw in the player's list of throws.
  * @param index The position of the Throw in the player's list of throws.
  * @param newThrow A Throw to be placed in the player's list of throws.
  */
  public void setThrow(Integer index, Throw newThrow)
  {
    this._throws[index] = newThrow;
  }
  
  /** Gets the player's list of throws.
  * @return The List of Throws of the player
  */
  public Throw[] getThrows()
  {
    return this._throws;
  }  

  /** Creates a player with the specified name.
  * @param name The players name.
  */
  public Player(String name)
  {
    _name = name;
    _throws = new Throw[10];
  }

  /** Creates a player with the specified name.
  * @param currentPoints The points so far.
  * @param index The order of the throw in the list of throws of this player.
  */
  private Integer getThrowPoints(Integer currentPoints, Integer index)
  {
      Throw currentThrow = _throws[index];
      if (currentThrow.getScores()[0] == 10) {
        if (index == 9) {
          // add 10 of this throw plus last 2 chances
          return currentPoints + 10 + _throws[index].getScores()[1] + _throws[index].getScores()[2];
        } else if (index == 8) {
          // add 10 of this throw plus first 2 chances of last throw
          return currentPoints + 10 + _throws[index + 1].getScores()[0] + _throws[index + 1].getScores()[1];
        } else if (_throws[index + 1].getScores()[0] == 10) {
          // add 10 of this throw plus 10 of next throw plus first chance of one after that
          return currentPoints + 10 + 10 + _throws[index + 2].getScores()[0];
        } else {
          // add 10 of this throw plus sum of both chances of next throw
          return currentPoints + 10 + _throws[index + 1].getScores()[0] + _throws[index + 1].getScores()[1];
        }
      } else if (currentThrow.getScores()[0] + currentThrow.getScores()[1] == 10) {
        // add 10 of this throw plus first chance of next throw
        return currentPoints + 10 + _throws[index + 1].getScores()[0];
      } else {
        // add sum of both chances of this throw
        return currentPoints + currentThrow.getScores()[0] + currentThrow.getScores()[1];
      }
  }

  /** Calculates the score of this player.
  */
  public void calculateScore()
  {
    Integer currentPoints = 0;
    for(Integer x = 0; x < 10; x++) {
      currentPoints = getThrowPoints(currentPoints, x);
      _throws[x].setPoints(currentPoints);
    }
  }

}