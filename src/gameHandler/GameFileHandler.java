package gameHandler;

import java.io.FileReader;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;

import entity.BasicRat;
import entity.RatTypes;
import javafx.scene.image.Image;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.*;
import entity.*;
import tiles.TileTypes;

public class GameFileHandler {
    public static final String ERROR_MSG_FILE_NOT_FOUND = "Could not find %s.";
    public static final String SAVE_PATH = "saveFiles/";
    public static final String LEVEL_PATH = "levelFiles/";
    public static final String SAVE_FILE_EXT = ".json";

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

    private static Pos objToPos(JSONArray obj) {
        return new Pos(objToInt(obj, 0), objToInt(obj, 1));
    }

    private static Pos objToPos(JSONObject obj) {
        return objToPos((JSONArray) obj.get("position"));
    }

    private static char[][] parseMap(JSONObject json) {
        JSONObject mapObj = (JSONObject) json.get("map");
        JSONObject levelStatsObj = (JSONObject) json.get("levelStats");
        JSONArray dimensionsJObj = (JSONArray) mapObj.get("dimensions");
        String tilesString = (String) mapObj.get("tiles");

        int timeLeft = objToInt(levelStatsObj, "timeLeft");

        Pos dimensions = objToPos(dimensionsJObj);

        char[][] map = new char[dimensions.y][dimensions.x];
        String[] rows = tilesString.split("\n");
        for (int i = 0; i < rows.length; i++) {
            map[i] = rows[i].toCharArray();
        }
        System.out.println(map.length);

        return map;
    }

    private static BasicRat[] parseRats(JSONObject json) {
        Image image;
        String imagePath;
        RatTypes type;
        Pos position;
        JSONArray positionJObj;
        JSONArray ratsJArray = (JSONArray) json.get("rats");
        JSONObject rat;
        BasicRat[] rats = new BasicRat[ratsJArray.size()];
        for (int i = 0; i < rats.length; i++) {
            rat = (JSONObject) ratsJArray.get(i);
            switch ((String) rat.get("gender")) {
                case "MALE":
                    type = RatTypes.MALE;
                    break;
                case "BABY":
                    type = RatTypes.BABY;
                    break;
                default:
                    type = RatTypes.FEMALE;
                    break;
            }

            positionJObj = (JSONArray) rat.get("position");
            position = objToPos(positionJObj);
            rats[i] = new BasicRat(
                    type,
                    position,
                    objToInt(rat, "hp"),
                    objToInt(rat, "timeToGrowth"),
                    objToInt(rat, "numChildren"),
                    objToInt(rat, "moveSpeed"),
                    objToInt(rat, "timeToBirth"),
                    (boolean) rat.get("canMate"),
                    (boolean) rat.get("canMove")
            );
        }
        return rats;
    }

    private static Entity[] makeItemArray(JSONArray jItems, String key) {
        int size = jItems.size();
        Pos position;
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
                            position
                    );
                }
                break;
            case "mSexChange":
                itemArr = new MaleGenderChange[size];
                for (int i = 0; i < size; i++) {
                    jItem = (JSONObject) jItems.get(i);
                    position = objToPos(jItem);
                    itemArr[i] = new MaleGenderChange(position);
                }
                break;
            case "fSexChange":
                itemArr = new FemaleGenderChange[size];
                for (int i = 0; i < size; i++) {
                    jItem = (JSONObject) jItems.get(i);
                    position = objToPos(jItem);
                    itemArr[i] = new FemaleGenderChange(position);
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
        Entity[][] items = new Item[nItems][];
        String currentKey;

        for (int i = 0; i < nItems; i++) {
            currentKey = itemKeys[i];
            items[i] = makeItemArray((JSONArray) itemsOnMap.get(currentKey), currentKey);
        }

        return items;
    }

    private static HashMap<String, Integer> parseLevelStats(JSONObject json) {
        HashMap<String, Integer> levelStats = new HashMap<>();
        JSONObject jLevelStats = (JSONObject) json.get("levelStats");
        String[] keys = {
                "timeLeft", "ratLimit", "bombFreq", "gasFreq", "steriliseFreq",
                "mSexChangeFreq", "fSexChangeFreq", "noEntryFreq", "deathRatFreq", "poisonFreq" ,"loseAmount"
        };
        for (String key: keys) {
            levelStats.put(key, objToInt(jLevelStats, key));
        }
        return levelStats;
    }

    private static HashMap<String, HashMap<String, Integer>>
		parsePlayerStats(JSONObject json) {
        HashMap<String, HashMap<String, Integer>> player = new HashMap<>();
        HashMap<String, Integer> playerStats = new HashMap<>();
        JSONObject jPlayerStats = (JSONObject) json.get("playerStats");
        playerStats.put("score", objToInt(jPlayerStats, "score"));
        playerStats.put("maxLevel", objToInt(jPlayerStats, "maxLevel"));
        player.put((String) jPlayerStats.get("name"), playerStats);
        return player;
    }

    private static int[] parseInventory(JSONObject json) {
        JSONObject jInventory = (JSONObject) json.get("inventory");
        String[] keys = {
                "bomb", "gas", "sterilise",
                "mSexChange", "fSexChange", "noEntry", "deathRat", "poison"
        };
        int[] inventory = new int[keys.length];

        for (int i = 0; i < keys.length; i++) {
            inventory[i] = objToInt(jInventory, keys[i]);
        }

        return inventory;
    }   

    private static Tuple<BasicRat[], Entity[][], char[][], HashMap<String, Integer>,
        HashMap<String, HashMap<String, Integer>>, int[]>
		parseJSON(JSONObject json) {
        BasicRat[] rats = parseRats(json);
        Entity[][] items = parseItemsOnMap(json);
        char[][] map = parseMap(json);
        HashMap<String, Integer> levelStats = parseLevelStats(json);
        HashMap<String, HashMap<String, Integer>> playerStats = parsePlayerStats(json);
        int[] inventory = parseInventory(json);

        return new Tuple<>(rats, items, map, levelStats, playerStats, inventory);
    }

    private static String generateJSON(ArrayList<Object> objects) {
        String saveString = "";
        return saveString;
    }

    // loadGame() and newGame() will both return Tuples containing everything
    // needed to resume or start a game
    // void atm so it still compiles.
    public static Tuple<BasicRat[], Entity[][], char[][], HashMap<String, Integer>,
            HashMap<String, HashMap<String, Integer>>, int[]>
    loadGame(String name) throws ParseException, IOException {
        JSONObject json = readJSON(name, false);
        return parseJSON(json);
    }

    public static Tuple<BasicRat[], Entity[][], char[][], HashMap<String, Integer>,
            HashMap<String, HashMap<String, Integer>>, int[]>
    newGame(String name) throws ParseException, IOException {
        JSONObject json = readJSON(name, true);
        return parseJSON(json);
    }
    public static Tuple<BasicRat[], Entity[][], char[][], HashMap<String, Integer>,
            HashMap<String, HashMap<String, Integer>>, int[]>
    newGameFromSave(String name) throws ParseException, IOException {
        JSONObject json = readJSON(name, false);
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
	public static void saveGame(String path) {
		//construct map string
		String mapStr = "";
		for (int y = 0; y < Game.TileManager.getNumTileWidth();y++) {
			for (int x = 0; x < Game.TileManager.getNumTileHeight();x++) {
				TileTypes type = Game.TileManager.getTile(new Pos(x, y)).getType();
				switch (type) {
				case PATH:
					mapStr += "P";
					break;
                case TUNNEL:
					mapStr += "T";
					break;
				case GRASS:
					mapStr += "G";
					break;
				case SPEEDTILE:
					mapStr += "S";
					break;
				default:
					//Unknown Tile
					//not sure if this would ever trigger cause
					//errors should arise when the map is loaded but
					//it's here anyway and you can't stop me 
					mapStr += "U";
					break;
				}	
			}
			mapStr += "\\n"; //idk how to print \n without creating a new line but we should be literally appending \n aaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaa
		}
		//construct dimension string
		String dimStr = "[" +  Game.TileManager.getNumTileWidth() + "," + Game.TileManager.getNumTileHeight() + "],";
		String fullMapString = "{\n  map: {\n    \"dimensions\": " + dimStr + "\"tiles\": \"" + mapStr + "\"\n  },\n";
		
		//construct rat string
		String ratStr = "  \"rats\": [\n";
		ArrayList<BasicRat> rats = Game.RatManager.getRatList();
		for (int i = 0; i < rats.size(); i++) {
			BasicRat rat = rats.get(i);
			ratStr += "    {\n";
			ratStr += "      \"gender\": \"" + rat.getRatType() +"\",\n";
			ratStr += "      \"canMate\": " + rat.getMateStatus() + ",\n";
			ratStr += "      \"canMove\": " + rat.getMoveStatus() + ",\n";
			ratStr += "      \"moveSpeed\": " + rat.getMoveSpeed() + ",\n";
			ratStr += "      \"timeToGrowth\": " + rat.getTimeToGrowth() + ",\n";
			ratStr += "      \"numChildren\": " + rat.getNumChildren() + ",\n";
			ratStr += "      \"timeToBirth\": " + rat.getTimeToBirth() + ",\n";
			ratStr += "      \"hp\": " + rat.getHP() + ",\n";
			ratStr += "      \"position\": [" + rat.getPosition().x + ", " + rat.getPosition().y + "],\n";
			ratStr += "    }";
			if (!(i == rats.size()-1)) {
				ratStr += ",";
			}
			ratStr += "\n";
		}
		ratStr += "  ],";
		
		//construct item string
		String itemStr = "\n  \"itemsOnMap\": {\n    ";
		ArrayList<Item> items = Game.ItemManager.getItemList();
		for (Item item : items) {
			//kinda scuffed but jonny made this before itemType so i have no choice
			ItemType type = item.getType();
			String itemTypeStr = "";
			switch (type) {
				case BOMB:
				itemTypeStr = "bomb";
				break;
				case GAS:
				itemTypeStr = "gas";
				break;
                case STERILIZATION:
				itemTypeStr = "sterilize";
				break;
				case MALE:
				itemTypeStr = "mSexChange";
				break;
				case FEMALE:
				itemTypeStr = "fSexChange";
				break;
				case NOENTRYSIGN:
				itemTypeStr = "noEntry";
				break;
				case POISON:
				itemTypeStr = "poison";
				break;
			}
			itemStr += "\"" + itemTypeStr + "\": [" + item.getPosition().x  + ", " + item.getPosition().y + "]";
		}
		ArrayList<DeathRat> deathRats = Game.RatManager.getDeathRatList();
		int index = 0;
		for (DeathRat deathRat : deathRats) {
			itemStr += "\"deathRat\": [" + deathRat.getPosition().x + ", " + deathRat.getPosition().y + "]";
			if (index < items.size() -1) {
				itemStr += ", ";
			}	
			index++;
		}
		itemStr += "},";

		//construct level stats string
		String lvlStr = "\n  \"levelStats\": {";
		HashMap<String, Integer> lvlStats = Game.getLevelStats();
		lvlStr += "    \"timeLeft\": " + lvlStats.get("timeLeft") + ",";
		lvlStr += "    \"bombFreq\": " + lvlStats.get("bombFreq") + ",";
		lvlStr += "    \"gasFreq\": " + lvlStats.get("gasFreq") + ",";
		lvlStr += "    \"sterilizeFreq\": " + lvlStats.get("sterilizeFreq") + ",";
		lvlStr += "    \"mSexChangefreq\": " + lvlStats.get("mSexChangeFreq") + ",";
		lvlStr += "    \"fSexChangeFreq\": " + lvlStats.get("fSexChangeFreq") + ",";
		lvlStr += "    \"noEntryFreq\": " + lvlStats.get("noEntryFreq") + ",";
		lvlStr += "    \"deathRatFreq\": " + lvlStats.get("deathRatFreq") + ",";
		lvlStr += "    \"poisonFreq\": " + lvlStats.get("poisonFreq") + ",";
		lvlStr += "    \"loseAmount\": " + lvlStats.get("loseAmount") + ",\n  },";		
		
		//construct inventory string
		String inventoryStr = "\n  \"inventory\": {";
		inventoryStr += "    \"bomb\": " + Inventory.getBombCount() + ",";
		inventoryStr += "    \"gas\": " + Inventory.getGasCount() + ",";
		inventoryStr += "    \"sterilise\": " + Inventory.getSterileCount() + ",";
		inventoryStr += "    \"poison\": " + Inventory.getPoisonCount() + ",";
		inventoryStr += "    \"mSexChange\": " + Inventory.getMaleCount() + ",";
		inventoryStr += "    \"fSexChange\": " + Inventory.getFemaleCount() + ",";
		inventoryStr += "    \"noEntry\": " + Inventory.getNoEntryCount() + ",";
		inventoryStr += "    \"deathRat\": " + Inventory.getDeathCount() + "\n  }\n}";

		String playerStr = "  \"playerStats\": {\n" +
                "    \"name\": \"Dave\",\n" +
                "    \"score\": 0,\n" +
                "    \"maxLevel\": 3\n" +
                "  },";
	
		String fullGameString = fullMapString + ratStr + itemStr + lvlStr + playerStr + inventoryStr;
		
		writeSaveFile(fullGameString, path);
    }	
	private static void writeSaveFile(String saveString, String path) {
        try {
            FileWriter writer = new FileWriter(LEVEL_PATH + path + SAVE_FILE_EXT, false);
            writer.write(saveString);
            writer.close();
        } catch (Exception e) {
            System.out.println("Save unsuccessfool. OOp");
        }
    }
	
}