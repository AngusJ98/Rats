import java.io.*;

/**
 * <p> 1. File-name: Player.java</p>
 * <p> 2. Creation Date: 19/11/21 </p>
 * <p> 3. Last modification date: 03/12/21 </p>
 * <p> 4. Purpose of the program: Creation of players</p>
 *
 * @author Isaac
 */

public class Player {

    private int score;
    private String playerName;
    private int maxLevelUnlocked;

    /**
     * Constructor used to format a player`s information.
     * <p> no side-effects</p>
     * <p> not referentially transparent</p>
     *
     * @param playerName       the name of this player
     * @param score            the score of this player
     * @param maxLevelUnlocked the maximum level unlocked by this player
     */
    Player(String playerName, int score, int maxLevelUnlocked) {
        this.playerName = playerName;
        this.score = score;
        this.maxLevelUnlocked = maxLevelUnlocked;
    }

    /**
     * Method used to provide an output string for the class
     * <p> no side-effects</p>
     * <p> not referentially transparent</p>
     *
     * @return playerName, score and maxLevelUnlocked neatly formatted
     */
    public String toString() {
        return playerName + " " + score + " " + maxLevelUnlocked;
    }

    /**
     * Method used to return the score of the player it is called on.
     * <p> no side-effects</p>
     * <p> not referentially transparent</p>
     *
     * @return the score of the player
     */
    public int getScore() {
        return score;
    }

    /**
     * Method used to return the max level in which the player has unlocked.
     * <p> no side-effects</p>
     * <p> not referentially transparent</p>
     *
     * @return the max level unlocked of a player
     */

    public int getMaxLevelUnlocked() {
        return maxLevelUnlocked;
    }

    /**
     * Method used to save a score to the end of profile.txt
     */
    public void saveScore(){
        Writer output = null;
        File file = new File("profile.txt");
        output = new BufferedWriter(new FileWriter(file,true));
        output.write(playerName);
        output.write(",");
        output.write(maxLevelUnlocked);
        output.write(",");
        output.write("" + score);
        output.write("\n");
        output.close();
        }

}