package com.dc.tenPinBowling;

/** Represents a throw.
 * @author Daniel Coellar
 * @version 1.0
*/
public class Throw {
  private Integer _index;
  private Integer[] _scores;
  private Integer _points;

  /** Gets the throw's scores.
  * @return An array of integers representing the throw's scores
  */
  public Integer[] getScores()
  {
    return _scores;
  }

  /** Sets a scroe in the throw's scores.
  * @param index A Integer representing the position of the score in the list of throw's scores.
  * @param chance A Chance from where to obtain the score.
  */
  public void setScore(Integer index, Chance chance)
  {
    // Setup som temp variables for validations
    Integer lineIndex = chance.getIndex();
    String line = chance.getLine();
    Integer score = chance.getScore();

    Integer firstScore = _scores[0];
    Integer secondScore = _scores[1];
    Integer thirdScore = _scores[2];

    // Succesful validation adds the score to the list
    if (
      (_index < 9 && index <= 1 && (index == 0 || (firstScore < 10 && firstScore + score <= 10))) ||
      (_index >= 9 && index <= 2 && (index < 1 || (index == 1 && (firstScore == 10 || firstScore + score <= 10)) || (index == 2 && (firstScore == 10 || secondScore == 100 || firstScore + secondScore == 10)))) 
    ) {
      this._scores[index] = score;
    } else {
      // for throws 1 - 9 
      if (_index < 9) {
        if (index > 1) {
          // Only 2 chances per throw
          throw new IncorrectThrowException(String.format("Incorrect definition on line %d: \"%s\", there can be only 2 chances per throw", lineIndex + 1, line));
        } else if (index == 1 && firstScore == 10) {
          // Only 1 chances per throw if first chance waw 10
          throw new IncorrectThrowException(String.format("Incorrect definition on line %d: \"%s\", previous chance was already 10 there should not be a second chance", lineIndex + 1, line));
        } else if (index == 1 && firstScore + score > 10) {
          // Sum of chances can not add up to more than 10
          throw new IncorrectThrowException(String.format("Incorrect definition on line %d: \"%s\", the maximun sum of chances is 10, found %s + %s", lineIndex + 1, line, firstScore, score));
        } else {
          throw new IncorrectThrowException(String.format("Incorrect definition on line %d: \"%s\", unknow error", lineIndex + 1, line));
        }
      // for last throws
      } else {
        if (index > 2) {
          // Only 3 chances in last throw
          throw new IncorrectThrowException(String.format("Incorrect definition on line %d: \"%s\", there can be only 3 chances in the last throw", lineIndex + 1, line));
        } else if (index == 2 && firstScore < 10 && firstScore + secondScore < 10) {
          // Only 2 chances in last throw if neither first 2 chances were less than 10 or the sume of them is less than 10
          throw new IncorrectThrowException(String.format("Incorrect definition on line %d: \"%s\", player should not have a third chance in this last throw", lineIndex + 1, line));
        } else if (index == 2 && firstScore == 10 && secondScore < 10 && secondScore + score > 10) {
          // Last 2 chances can not add up to more than 10 unless the second one was 10
          throw new IncorrectThrowException(String.format("Incorrect definition on line %d: \"%s\", the last 2 chances can not sum more that 10", lineIndex + 1, line));
        } else if (index == 1 && firstScore < 10 && firstScore + score > 10) {
          // First 2 chances can not add up to more than 10 uless the first one was 10
          throw new IncorrectThrowException(String.format("Incorrect definition on line %d: \"%s\", the first 2 chances of the last throw can not sum more that 10 if the first chance was not 10", lineIndex + 1, line));
        } else {
          // Any other case
          throw new IncorrectThrowException(String.format("Incorrect definition on line %d: \"%s\", unknow error", lineIndex + 1, line));
        }      
      }
    }
  }

  /** Sets the points of the throw.
  * @param points A Integer representing the points of the throw.
  */
  public void setPoints(Integer points)
  {
    _points = points;
  }

  /** Gets the points of the throw.
  * @return A Integer representing the points of the throw.
  */
  public Integer getPoints()
  {
    return _points;
  }

  /** Creates a Throw with the specified index.
  * @param index A Integer representing the order of the throws.
  */
  public Throw(Integer index)
  {
    _index = index;
    _scores = new Integer[]{0, 0, 0};
    _points = 0;
  }

  /** Prints the scores of the Throw.
  * @param isLast A boolean to determine if it is the last throw.
  */
  public String printScores(Boolean isLast)
  {
    // setup temp variable for processing
    Integer firstScore = _scores[0];
    Integer secondScore = _scores[1];
    Integer thirdScore = _scores[2];
    
    // If is not the last throw
    if (isLast == false) {
      if (firstScore == 10) {
        // Print a single X if 10 in the first chance
        return String.format("%-4s%-4s", "", "X");
      } else if (firstScore + secondScore == 10) {
        // Print first score and / if both add up to 10
        return String.format("%-4d%-4s", firstScore, "/");
      } else {
        // Print both scores otherwise
        return String.format("%-4d%-4d", firstScore, secondScore);
      }
    // If is the last throw
    } else {
      if (firstScore == 10) {
        if (secondScore == 10 && thirdScore == 10) {
          // Print all X if all 3 chances where 10
          return String.format("%-4s%-4s%-4s\n", "X", "X", "X");
        } else if (secondScore == 10) {
          // Print 2 X for first 2 chances if they were 10 and score for the third score if it was less than 10
          return String.format("%-4s%-4s%-4d\n", "X", "X", thirdScore);
        } else if (secondScore + thirdScore == 10) {
          // Print X for first chance, second score and / for the third if second and third add up to 10
          return String.format("%-4s%-4d%-4s\n", "X", secondScore, "/");
        } else {
          // Print X for first chance, second and third score other wise
          return String.format("%-4s%-4d%-4d\n", "X", secondScore, thirdScore);
        }
      } else {
        if (secondScore == 10 && thirdScore == 10) {
          // Print first score then 2 X for second and third if the were 10.
          return String.format("%-4d%-4s%-4s\n", firstScore, "/", "X");
        } else if (firstScore + secondScore == 10 && thirdScore < 10) {
          // Print first score then / for second and third score if first and second add up to 10 and third is less than 10
          return String.format("%-4d%-4s%-4d\n", firstScore, "/", thirdScore);
        } else if (firstScore + secondScore == 10 && thirdScore == 10) {
          // Print first score then / for second and X for third score if first and second add up to 10 and third is 10
          return String.format("%-4d%-4s%-4s\n", firstScore, "/", "X");
        } else {
          // Print first and second scores if both were less than 10
          return String.format("%-4d%-4d\n", firstScore, secondScore);
        }
      }
    }
  }
}