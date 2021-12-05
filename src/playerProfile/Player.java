package playerProfile;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;

import java.io.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * <p> 1. File-name: playerProfile.Player.java</p>
 * <p> 2. Creation Date: 19/11/21 </p>
 * <p> 3. Last modification date: 03/12/21 </p>
 * <p> 4. Purpose of the program: Creation of players</p>
 *
 * @author Isaac
 */

public class Player {

    private int[] scores;
    private String playerName;
    private int maxLevelUnlocked;

    /**
     * Constructor used to format a player`s information.
     * <p> no side-effects</p>
     * <p> not referentially transparent</p>
     *
     * @param playerName       the name of this player
     * @param scores            the score of this player
     * @param maxLevelUnlocked the maximum level unlocked by this player
     */
    public Player(String playerName, int[] scores, int maxLevelUnlocked) {
        this.playerName = playerName;
        this.scores = scores;
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
        return playerName + " " + Arrays.toString(scores) + " " + maxLevelUnlocked;
    }

    /**
     * Method used to return the score of the player it is called on.
     * <p> no side-effects</p>
     * <p> not referentially transparent</p>
     *
     * @return the score of the player
     */
    public int[] getScores() {
        return scores;
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

    public String getPlayerName() {
        return playerName;
    }


    public JSONObject makeJSON() {
        JSONObject jo = new JSONObject();
        JSONArray scoreNew = new JSONArray();
        for (int score: scores) {
            scoreNew.add(score);
        }

        jo.put("name",this.playerName);
        jo.put("scores", scoreNew);
        jo.put("maxLevel", this.maxLevelUnlocked);
        return jo;
    }
}