package playerProfile;

import Controller.Main;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import java.io.*;
import java.util.*;

public class ProfileReader {
    public static final String ERROR_MSG_FILE_NOT_FOUND = "Could not find %s.";
    public static final String PROFILE_PATH = "profiles/profiles.json";

    private ProfileReader() {}

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

    private static int objToInt(JSONObject obj, String key) {
        return Math.toIntExact((long) obj.get(key));
    }

    private static int objToInt(JSONArray obj, int key) {
        return Math.toIntExact((long) obj.get(key));
    }


    //String playerName, int score, int maxLevelUnlocked
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

    public static Player createProfile(String name) {

        Player newP = new Player(name, new int[Main.LEVEL_COUNT],0);
        return newP;
    }

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
