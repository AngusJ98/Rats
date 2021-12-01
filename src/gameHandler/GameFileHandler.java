package gameHandler;

import java.io.FileReader;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;

import com.sun.xml.internal.ws.policy.privateutil.PolicyUtils;
import entity.BasicRat;
import entity.RatTypes;
import javafx.scene.image.Image;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.*;
import entity.*;

public class GameFileHandler {
    public static final String ERROR_MSG_FILE_NOT_FOUND = "Could not find %s.";
    public static final String SAVE_PATH = "saveFiles/";
    public static final String LEVEL_PATH = "levelFiles/";
    public static final String SAVE_FILE_EXT = ".json";
    public static final String MALE_RAT_IMG = "boyRat.png";
    public static final String FEMALE_RAT_IMG = "girlRat.png";
    public static final String BABY_RAT_IMG = "babyRat.png";

    private GameFileHandler() {}

    private static JSONObject readJSON(String fileName, boolean isLevel)
    throws ParseException, IOException {
        String filePath = isLevel ? LEVEL_PATH : SAVE_PATH;
        filePath = filePath + fileName + SAVE_FILE_EXT;
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

    private static int[] objToPos(JSONArray obj) {
        return new int[] {objToInt(obj, 0), objToInt(obj, 1)};
    }

    private static int[] objToPos(JSONObject obj) {
        return objToPos((JSONArray) obj.get("position"));
    }

    private static char[][] parseMap(JSONObject json) {
        JSONObject mapObj = (JSONObject) json.get("map");
        JSONObject levelStatsObj = (JSONObject) json.get("levelStats");
        JSONArray dimensionsJObj = (JSONArray) mapObj.get("dimensions");
        String tilesString = (String) mapObj.get("tiles");

        int timeLeft = objToInt(levelStatsObj, "timeLeft");

        int[] dimensions = objToPos(dimensionsJObj);
        
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
        RatTypes type;
        int[] position = new int[2];
        JSONArray positionJObj;
        JSONArray ratsJArray = (JSONArray) json.get("rats");
        JSONObject rat;
        BasicRat[] rats = new BasicRat[ratsJArray.size()];
        for (int i = 0; i < rats.length; i++) {
            rat = (JSONObject) ratsJArray.get(i);
            switch ((String) rat.get("type")) {
                case "MALE":
                    type = RatTypes.MALE;
                    imagePath = MALE_RAT_IMG;
                    break;
                case "BABY":
                    type = RatTypes.BABY;
                    imagePath = BABY_RAT_IMG;
                    break;
                default:
                    type = RatTypes.FEMALE;
                    imagePath = FEMALE_RAT_IMG;
                    break;
            }

            image = new Image("resources/" + imagePath, true);
            positionJObj = (JSONArray) rat.get("position");
            position = objToPos(positionJObj);
            rats[i] = new BasicRat(
                type,
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

    private static Entity[] makeItemArray(JSONArray jItems, String key) {
        int size = jItems.size();
        int[] position = new int[2];
        JSONObject jItem;
        Entity[] itemArr;
        switch (key) {
            case "bomb":
                itemArr = new Bomb[size];
                for (int i = 0; i < size; i++) {
                    jItem = (JSONObject) jItems.get(i);
                    position = objToPos(jItem);
                    itemArr[i] = new Bomb(
                        position,
                        objToInt(jItem, "timeLeft")
                    );
                }
                break;
            case "gas":
                itemArr = new Gas[size];
                for (int i = 0; i < size; i++) {
                    jItem = (JSONObject) jItems.get(i);
                    position = objToPos(jItem);
                    itemArr[i] = new Gas(
                        position
                    );
                }
                break;
            case "sterilise":
                itemArr = new Sterilization[size];
                for (int i = 0; i < size; i++) {
                    jItem = (JSONObject) jItems.get(i);
                    position = objToPos(jItem);
                    itemArr[i] = new Sterilization(
                        position,
                        objToInt(jItem, "timeLeft")
                    );
                }
                break;
            case "poison":
                itemArr = new Poison[size];
                for (int i = 0; i < size; i++) {
                    jItem = (JSONObject) jItems.get(i);
                    position = objToPos(jItem);
                    itemArr[i] = new Poison(
                        position,
                        objToInt(jItem, "timeLeft")
                    );
                }
                break;
            case "mSexChange":
                itemArr = new MaleGenderChange[size];
                for (int i = 0; i < size; i++) {
                    jItem = (JSONObject) jItems.get(i);
                    position = objToPos(jItem);
                    itemArr[i] = new MaleGenderChange(
                        position,
                        objToInt(jItem, "timeLeft")
                    );
                }
                break;
            case "fSexChange":
                itemArr = new FemaleGenderChange[size];
                for (int i = 0; i < size; i++) {
                    jItem = (JSONObject) jItems.get(i);
                    position = objToPos(jItem);
                    itemArr[i] = new FemaleGenderChange(
                        position,
                        objToInt(jItem, "timeLeft")
                    );
                }
                break;
            case "noEntry":
                itemArr = new NoEntrySign[size];
                for (int i = 0; i < size; i++) {
                    jItem = (JSONObject) jItems.get(i);
                    position = objToPos(jItem);
                    itemArr[i] = new NoEntrySign(
                        position,
                        objToInt(jItem, "currentHp")
                    );
                }
                break;
            case "deathRat":
                itemArr = new DeathRat[size];
                for (int i = 0; i < size; i++) {
                    jItem = (JSONObject) jItems.get(i);
                    position = objToPos(jItem);
                    itemArr[i] = new DeathRat(
                        position,
                        objToInt(jItem, "killCount")
                    );
                }
                break;
            default:
                itemArr = new Entity[]{};
                break;
        }
        return itemArr;
    }

    // TODO: change this to Item[][] if we make an abstract Item class
    private static Entity[][] parseItemsOnMap(JSONObject json) {
        JSONObject itemsOnMap = (JSONObject)json.get("itemsOnMap");
        String[] itemKeys = {
            "bomb", "gas", "sterilise", "poison", "mSexChange",
            "fSexChange", "noEntry", "deathRat"
        };
        int nItems = itemKeys.length;
        Entity[][] items = new Entity[nItems][];
        String currentKey;

        for (int i = 0; i < nItems; i++) {
            currentKey = itemKeys[i];
            items[i] =
                makeItemArray((JSONArray) itemsOnMap.get(currentKey), currentKey);
        }

        return items;
    }

    private static int[] parseLevelStats(JSONObject json) {
        return new int[]{};
    }

    private static int[] parsePlayerStats(JSONObject json) {
        return new int[]{};
    }

    private static int[] parseInventory(JSONObject json) {
        return new int[]{};
    }

    private static void writeSaveFile(String saveString) {

    }

    private static Tuple<BasicRat[], Entity[][], char[][], int[], int[], int[]> parseJSON(JSONObject json) {
         BasicRat[] rats = parseRats(json);
         Entity[][] items = parseItemsOnMap(json);
         char[][] map = parseMap(json);
         int[] levelStats = parseLevelStats(json);
         int[] playerStats = parseLevelStats(json);
         int[] inventory = parseInventory(json);

         return new Tuple<>(rats, items, map, levelStats, playerStats, inventory);
    }

    private static String generateJSON(ArrayList<Object> objects) {
        String saveString = "";
        return saveString;
    }

    public static void saveGame() {

    }

    // loadGame() and newGame() will both return Tuples containing everything
    // needed to resume or start a game
    // void atm so it still compiles.
    public static Tuple<BasicRat[], Entity[][], char[][], int[], int[], int[]>
    loadGame(String name) throws ParseException, IOException {
        JSONObject json = readJSON(name, false);
        return parseJSON(json);
    }

    public static Tuple<BasicRat[], Entity[][], char[][], int[], int[], int[]>
    newGame(String name) throws ParseException, IOException {
        JSONObject json = readJSON(name, true);
        return parseJSON(json);
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
        JSONObject json = readJSON("testLevel", true);
        println(json + "\n");

        parseMap(json);


    }

}
