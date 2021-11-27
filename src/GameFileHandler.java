import java.io.FileReader;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;

import javafx.scene.image.Image;
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

    private static int objToInt(JSONObject obj, String key) {
        return Math.toIntExact((long) obj.get(key));
    }

    private static int objToInt(JSONArray obj, int key) {
        return Math.toIntExact((long) obj.get(key));
    }


    private static char[][] parseMap(JSONObject json) {
        JSONObject mapObj = (JSONObject) json.get("map");
        JSONObject levelStatsObj = (JSONObject) json.get("levelStats");
        JSONArray dimensionsJObj = (JSONArray) mapObj.get("dimensions");
        String tilesString = (String) mapObj.get("tiles");

        int timeLeft = objToInt(levelStatsObj, "timeLeft");

        int[] dimensions = {
            objToInt(dimensionsJObj, 0),
            objToInt(dimensionsJObj, 1)
        };
        
        char[][] map = new char[dimensions[1]][dimensions[0]];
        String[] rows = tilesString.split("\n");
        for (int i = 0; i < rows.length; i++) {
            map[i] = rows[i].toCharArray();
        }

        println(Arrays.deepToString(map));

        return map;
    }

    private static BasicRat[] parseRats(JSONObject json) {
        Image image;
        String imagePath;
        ratTypes gender;
        boolean isBaby;
        int[] position = new int[2];
        JSONArray positionJObj;
        JSONArray ratsJArray = (JSONArray) json.get("rats");
        JSONObject rat;
        BasicRat[] rats = new BasicRat[ratsJArray.size()];
        for (int i = 0; i < rats.length; i++) {
            rat = (JSONObject) ratsJArray.get(i);
            gender = rat.get("gender") == "MALE" ? ratTypes.MALE : ratTypes.FEMALE;
            isBaby = (boolean) rat.get("isBaby");
            if(isBaby) {
                imagePath = "babyRat.png";
            } else {
                imagePath = gender == ratTypes.MALE ? "boyRat.png" : "girlRat.png";
            }
            image = new Image("resources/" + imagePath, true);
            positionJObj = (JSONArray) rat.get("position");
            position[0] = objToInt(positionJObj, 0);
            position[1] = objToInt(positionJObj, 1);
            rats[i] = new BasicRat(
                gender,
                (boolean) rat.get("isBaby"),
                (boolean) rat.get("canMate"),
                (boolean) rat.get("canMove"),
                objToInt(rat, "moveSpeed"),
                objToInt(rat, "timeToGrowth"),
                objToInt(rat, "numChildren"),
                objToInt(rat, "timeToBirth"),
                objToInt(rat, "hp"),
                position,
                image
            );
        }
        return rats;
    }

//    private static Entity[] parseItemsOnMap(JSONObject json) {
//
//    }
//
//    private static int[] parseLevelStats(JSONObject json) {
//
//    }
//
//    private static int[] parseInventory(JSONObject json) {
//
//    }
//
//    private static void writeSaveFile(String saveString) {
//
//    }

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
        // will need to check player's maxLevel to make sure they've unlocked it
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

        parseMap(json);


    }

}
