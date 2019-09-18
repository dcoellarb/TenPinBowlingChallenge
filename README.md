
A Java Challenge to create a scoring application fo the Ten Pin Bowling game.

# Requerimientos
Apache Maven 3.6.1
java 1.0

# To execute application

## 1.- Compile
mvn compile

## 2.- Execute
mvn exec:java -Dexec.mainClass="com.dc.tenPinBowling.App" -Dexec.args="sample.txt"

Remember to change the file name arguement to try other players and scores

# To run test
mvn test

# Description of the Solution
To resolve this challenge i model a few classes to represent distinct entities of the solution: TenPinBowlingGame, Player, Throw and Chance

TenPinBowlingGame implements the abstract class Game, the idea is that this solution could be extended to other Games; all games are created from a file of results, and need to implement processResults and printResults methods. For the processResults i used streams and lambdas so simplify the process which basically starts by reading the file and parsing each line into an instance of the Chance class, the i use this list of Chances to create a map to group them by player to support N players, i stream this map to create an instance of Player for each player and store those list of players in the Game instance. The printResults is much simpler it just prints a header and then goes through each player print its name, pitfalls and scores.

To test the application i created 7 files which cover different uses cases like perfectScore, zeroScore and exceptions.

Thanks for your consideration

Best regards

Daniel.