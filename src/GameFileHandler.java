import java.io.FileReader;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.*;

public class GameFileHandler {
    public static final String ERROR_MSG_FILE_NOT_FOUND = "Could not find %s.";
    public static final String SAVE_PATH = "../saveFiles/";
    public static final String LEVEL_PATH = "../levelFiles/";

    private GameFileHandler() {}

    private static JSONObject readSaveFile(String fileName, boolean isLevel)
            throws FileNotFoundException, ParseException, IOException {
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

    private static void writeSaveFile(String saveString) {

    }

    private static ArrayList<Object> parseSaveString(String saveString) {
         ArrayList<Object> objects = new ArrayList<>();
         return objects;
    }

    private static String generateSaveString(ArrayList<Object> objects) {
        String saveString = "";
        return saveString;
    }

    public static void saveGame() {

    }

    public static void loadGame() {

    }

    public static void newGame() {

    }

}
