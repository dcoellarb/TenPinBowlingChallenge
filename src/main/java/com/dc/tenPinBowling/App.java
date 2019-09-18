package com.dc.tenPinBowling;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.ArrayList;
import java.util.HashMap;

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
        BufferedReader reader;
        try {
            // process file
            reader = new BufferedReader(new FileReader(args[0]));
            ArrayList<Chance> chances = processFile(reader);

            // group chances by players
            HashMap<String, ArrayList<Chance>> playersScores = mapChancesToPlayers(chances);

            // process players
            ArrayList<Player> players = new ArrayList<Player>();
            for (ArrayList<Chance> chancesPlayer : playersScores.values()) {
                Player player = processPlayer(chancesPlayer);
                player.calculateScore();
                players.add(player);
            }

            // print results
            printResults(players);
        } catch (IOException e) {
            System.out.printf("ERROR: %s\n", e.getMessage());
        } catch (IncorrectFormatException e) {
            System.out.printf("ERROR: %s\n", e.getMessage());
        } catch (IncorrectThrowException e) {
            System.out.printf("ERROR: %s\n", e.getMessage());
        }
    }

    /** Reads through the file and parse each line into chances.
    * @param reader BufferedReader to read the file form disk
    */
    private static ArrayList<Chance> processFile(BufferedReader reader)
        throws IOException, IncorrectFormatException
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

    /** Creates a map of players and their chance to supporn 1 to N players.
    * @param chances ArrayList of Chances with the list of chances read from the file.
    */
    private static HashMap<String, ArrayList<Chance>> mapChancesToPlayers(ArrayList<Chance> chances)
    {
        HashMap<String, ArrayList<Chance>> map = new HashMap<String, ArrayList<Chance>>();
        for (Chance chance : chances) {
            ArrayList<Chance> playerScores = map.get(chance.getPlayer());
            if (playerScores == null) {
                playerScores = new ArrayList<Chance>();
                map.put(chance.getPlayer(), playerScores);
            }
            playerScores.add(chance);
        }
        return map;
    }

    /** Creates a player out of a list of chacnes already grouped.
    * @param chances ArrayList of Chances with the list of chances of one player.
    */
    private static Player processPlayer(ArrayList<Chance> chances)
        throws IncorrectThrowException, IncorrectFormatException
    {
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

        return player;
    }

    /** Prints the scores of each player.
    * @param chances ArrayList of Player with the list of players and their scores.
    */
    private static void printResults(ArrayList<Player> players)
    {
        // print Header
        System.out.format("%-16s", "Frame");
        for(Integer x = 0; x < 10; x++) {
            if (x < 9) System.out.format("%-8d", x + 1);
            if (x == 9) System.out.format("%-8d\n", x + 1);
        }

        // print Players
        for(Player player : players) {
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
        }
    }
}
