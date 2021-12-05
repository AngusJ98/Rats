package playerProfile;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
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
            JSONObject jList = (JSONObject) readJSON();
            Set<String> keys = jList.keySet();
            players = new Player[keys.size()];
            int i = 0;
            for (String key : keys) {
                players[i] = parseProfile((JSONObject) jList.get(key));
                i++;
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

}
