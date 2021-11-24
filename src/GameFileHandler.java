import java.io.FileReader;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.*;

public class GameFileHandler {
    public static final String ERROR_MSG_FILE_NOT_FOUND = "Could not find %s.";
    public static final String SAVE_PATH = "saveFiles/";
    public static final String LEVEL_PATH = "levelFiles/";

    private GameFileHandler() {}

    private static JSONObject readJSON(String fileName, boolean isLevel)
    throws ParseException, IOException {
        String filePath = isLevel ? LEVEL_PATH : SAVE_PATH;
        filePath = filePath + fileName;
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

    private static Game initGame(JSONObject json) {
        JSONObject mapObj = (JSONObject) json.get("map");
        JSONObject levelStatsObj = (JSONObject) json.get("levelStats");
        JSONArray dimensionsJObj = (JSONArray) mapObj.get("dimensions");
        String tilesString = (String) mapObj.get("tiles");

        int timeLeft = Math.toIntExact((long) levelStatsObj.get("timeLeft"));

        int[] dimensions = {
            Math.toIntExact((long) dimensionsJObj.get(0)),
            Math.toIntExact((long) dimensionsJObj.get(1))
        };
        
        char[][] map = new char[dimensions[1]][dimensions[0]];
        String[] rows = tilesString.split("\n");
        for (int i = 0; i < rows.length; i++) {
            map[i] = rows[i].toCharArray();
        }

        println(Arrays.deepToString(map));
        return new Game(map, timeLeft);



    }

    private static void writeSaveFile(String saveString) {

    }

    private static ArrayList<Object> parseJSON(JSONObject json) {
         ArrayList<Object> objects = new ArrayList<>();

         return objects;
    }


    private static String generateJSON(ArrayList<Object> objects) {
        String saveString = "";
        return saveString;
    }

    public static void saveGame() {

    }

    public static void loadGame() {

    }

    public static void newGame() {

    }

    // For testing //
    public static void print(Object in) {
        System.out.print(in);
    }

    public static void println(Object in) {
        System.out.println(in);
    }

    public static void main(String[] args) throws ParseException, IOException{
//        try {
//            System.out.println(readSaveFile("testLevel.json", true));
//        } catch (Exception e) {
//            System.out.println("poop");
//            System.out.println(Arrays.toString(e.getStackTrace()));
//        }
        JSONObject json = readJSON("testLevel.json", true);
        println(json + "\n");

        Game game = initGame(json);


    }

}
