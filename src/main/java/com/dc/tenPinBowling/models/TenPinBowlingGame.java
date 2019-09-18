package com.dc.tenPinBowling;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.stream.*;

/** Class to represent a Ten Pin Bowling Game.
 * @author Daniel Coellar
 * @version 1.0
*/
public class TenPinBowlingGame extends Game {
  ArrayList<Player> _players;

  /** Creates the Game with the specified File.
  * @param inputFile String with the name of the file to be processed
  */
  public TenPinBowlingGame(String inputFile) {
    super(inputFile);
  };

  /** Helper Method - Reads through the file and parse each line into chances.
  * @param reader BufferedReader to read the file form disk
  */
  private ArrayList<Chance> processFile(BufferedReader reader)
      throws IOException
  {
      ArrayList<Chance> list = new ArrayList<Chance>();
      String line = reader.readLine();
      while (line != null) {
          // Create and chance per line and validate formating
          list.add(Chance.createFromLine(0, line));
          line = reader.readLine();
      }
      return list;
  }

  /** Helper Method - Creates a map of players and their chance to supporn 1 to N players.
  * @param chances ArrayList of Chances with the list of chances read from the file.
  */
  private static HashMap<String, ArrayList<Chance>> mapChancesToPlayers(ArrayList<Chance> chances)
  {
      HashMap<String, ArrayList<Chance>> map = new HashMap<String, ArrayList<Chance>>();
      chances.forEach(chance -> {
          ArrayList<Chance> playerScores = map.get(chance.getPlayer());
          if (playerScores == null) {
              playerScores = new ArrayList<Chance>();
              map.put(chance.getPlayer(), playerScores);
          }
          playerScores.add(chance);
      });
      return map;
  }

  /** Helper Method - Creates a player out of a list of chances already grouped.
  * @param chances ArrayList of Chances with the list of chances of one player.
  */
  private Player processPlayer(Map.Entry<String, ArrayList<Chance>> entry)
  {
        ArrayList<Chance> chances = entry.getValue();

        // create player
        Player player = new Player(chances.get(0).getPlayer());

        // setup temp variable for processing
        Integer throwIndex = 0;
        Throw currentThrow = new Throw(throwIndex);
        Integer chanceIndex = 0;

        for (Chance chance : chances) {
            // Every 2 chances reset throws.
            if (throwIndex < 9 && chanceIndex == 2) {
                // Set Throw in Player.
                player.setThrow(throwIndex, currentThrow);

                // Start newThrow.
                throwIndex++;
                currentThrow = new Throw(throwIndex);
                chanceIndex = 0;
            }

            // Update Throw.
            currentThrow.setScore(chanceIndex, chance);

            // Increment chances index.
            if (throwIndex < 9 && chanceIndex == 0 && chance.getScore() == 10) {
                chanceIndex = 2;
            } else {
                chanceIndex++;
            }
        }

        // set last throw
        player.setThrow(throwIndex, currentThrow);

        // validate that each player has 10 throws
        if (throwIndex != 9) {
            throw new IncorrectFormatException(String.format("Incorrect definition: each player has to have 10 throws"));
        }

        // Calculate Score
        player.calculateScore();

        return player;
  }

  /** Implements Abstract Class Method - Process the file and stores the result in an internal list of Players.
  */
  public void processResults()
    throws IOException
  {
    BufferedReader reader = new BufferedReader(new FileReader(_inputFile));
    _players = new ArrayList<Player>(
        mapChancesToPlayers(
            processFile(reader)
        )
        .entrySet()
        .stream()
        .map(this::processPlayer)
        .collect(Collectors.toList())
    );
  };

  /** Implements Abstract Class Method - Prints the scores of each player.
  */
  public void printResults()
  {
    // print Header
    System.out.format("%-16s", "Frame");
    for(Integer x = 0; x < 10; x++) {
        if (x < 9) System.out.format("%-8d", x + 1);
        if (x == 9) System.out.format("%-8d\n", x + 1);
    }

    // print Players
    _players.forEach(player -> {
        // print name
        System.out.format("%s\n", player.getName());

        // print Pitfalls
        System.out.format("%-16s", "Pitfalls");
        for(Integer x = 0; x < 10; x++) {
            System.out.print(player.getThrows()[x].printScores(x == 9));
        }

        // print Scores
        System.out.format("%-16s", "Score");
        for(Integer x = 0; x < 10; x++) {
            System.out.format("%-8d", player.getThrows()[x].getPoints());
        }
        System.out.print("\n");
    });
  }
}