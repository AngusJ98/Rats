package playerProfile;

import Controller.Main;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import java.io.*;
import java.util.*;

/**
 * <p> 1. File-name: playerProfile.java</p>
 * <p> 2. Creation Date: 25/11/21 </p>
 * <p> 3. Last modification date: 05/12/21 </p>
 * <p> 4. Purpose of the program: Used to read and convert JSON player objects</p>
 *
 * @author Gus
 */
public class ProfileReader {
    public static final String ERROR_MSG_FILE_NOT_FOUND = "Could not find %s.";
    public static final String PROFILE_PATH = "profiles/profiles.json";

    private ProfileReader() {}

    /**
     * reads a json file as a json object
     * @return a json object from the file
     * @throws ParseException Exceptional
     * @throws IOException Exceptional
     */
    private static JSONObject readJSON()
            throws ParseException, IOException {
        String filePath = PROFILE_PATH;
        JSONParser jsonParser = new JSONParser();
        try {
            FileReader reader = new FileReader(filePath);
            return (JSONObject) jsonParser.parse(reader);
        } catch (FileNotFoundException e) {
            throw new FileNotFoundException(
                    String.format(ERROR_MSG_FILE_NOT_FOUND, filePath)
            );
        }
    }

    /**
     * Converts a JSON subobject to an int
     * @param obj the object containing the int
     * @param key the key
     * @return the int obtained from the object
     */
    private static int objToInt(JSONObject obj, String key) {
        return Math.toIntExact((long) obj.get(key));
    }

    /**
     * Converts a JSON subobject to an int
     * @param obj the object containing the int
     * @param key the key
     * @return the int obtained from the object
     */
    private static int objToInt(JSONArray obj, int key) {
        return Math.toIntExact((long) obj.get(key));
    }


    /**
     * takes a JSON object and converts it into a player object
     * @param json A JSOn object containing the attributes to make up a profile
     * @return a player object
     */
    private static Player parseProfile(JSONObject json) {
        String name = (String) json.get("name");
        JSONArray jScores = (JSONArray) json.get("scores");
        int[] scores = new int[jScores.size()];
        for (int i = 0; i < jScores.size(); i++) {
            scores[i] = objToInt(jScores, i);
        }
        int maxLevel = objToInt(json, "maxLevel");
        return new Player(name, scores, maxLevel);
    }

    /**
     * Reads a json array from a file and converts it into a list of players
     * @return an array of players
     */
    public static Player[] retrieveAllPlayers() {
        Player[] players = {};
        try {
            JSONArray jList = (JSONArray) readJSON().get("people");
            players = new Player[jList.size()];
            for (int i = 0; i < jList.size(); i++) {
                players[i] = parseProfile((JSONObject) jList.get(i));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return players;
    }

    /**
     * Calculates the 10 highest scores for a level
     * @param levelNum the level num to check score for
     * @return an arraylist of the 10 highest scoring players for that level
     */
    public static ArrayList<Player> getHighScores(int levelNum) {
        ArrayList<Player> players = new ArrayList<>(Arrays.asList(retrieveAllPlayers()));
        ArrayList<Player> goodPlayers = new ArrayList<>();
        for (int i = 0; i < 10; i++) {
            int max = 0;
            Player maxPlayer = null;
            for (Player player : players) {
                if (player.getScores()[levelNum] > max) {
                    max = player.getScores()[levelNum];
                    maxPlayer = player;
                }
            }
            if (maxPlayer != null) {
                goodPlayers.add(maxPlayer);
                players.remove(maxPlayer);
            }
        }

        return goodPlayers;
    }

    /**
     * creates a new profile from a name using default stats
     * @param name the name for the new profile
     * @return a new empty player profile
     */
    public static Player createProfile(String name) {

        Player newP = new Player(name, new int[Main.LEVEL_COUNT],0);
        return newP;
    }

    /**
     * writes a new profile if one with the same name does not exist
     * @param name the name of the new profile
     */
    public static void writeNewProfile(String name) {
        try {
            JSONObject file = readJSON();
            JSONArray people = (JSONArray) file.get("people");
            JSONObject newProf = createProfile(name).makeJSON();


            boolean action = true;
            for (int i = 0; i < people.size(); i++) {
                JSONObject jPlayer = (JSONObject) people.get(i);
                if (jPlayer.get("name").equals(newProf.get("name"))) {
                    action = false;
                }
            }
            if(action) {
                people.add(newProf);
                System.out.println("writing");
                FileWriter writer = new FileWriter(PROFILE_PATH, false);
                writer.write(file.toJSONString());
                writer.close();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * Method to update a profile using a new profile with the same name
     * @param updateProf the new version of the profile
     */
    public static void updateProfile(JSONObject updateProf) {
        try {
            JSONObject file = readJSON();
            JSONArray people = (JSONArray) file.get("people");
            for (int i = 0; i < people.size(); i++) {
                JSONObject jPlayer = (JSONObject) people.get(i);
                if (jPlayer.get("name").equals(updateProf.get("name"))) {
                    people.remove(jPlayer);
                    people.add(updateProf);
                }
            }
            FileWriter writer = new FileWriter(PROFILE_PATH, false);
            writer.write(file.toJSONString());
            writer.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }


}
