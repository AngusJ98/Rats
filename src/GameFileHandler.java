import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;

public class GameFileHandler {
    public static final String ERROR_MSG_FILE_NOT_FOUND = "Could not find %s.";
    public static final String SAVE_PATH = "../saveFiles/";
    public static final String LEVEL_PATH = "../levelFiles/";

    private GameFileHandler() {}

    private static String readSaveFile(String fileName, boolean isLevel) {
        String filePath = isLevel ? LEVEL_PATH : SAVE_PATH;
        filePath = filePath + fileName;
        Scanner in = null;
        File inputFile = new File(filePath);
        StringBuilder saveStringBuilder =  new StringBuilder();
        try {
			in = new Scanner(inputFile);
		} catch (FileNotFoundException e) {
			String errorMessage = String.format(ERROR_MSG_FILE_NOT_FOUND, filePath);
			System.out.println(errorMessage);
			System.exit(0);
		}

        while (in.hasNextLine()) {
            saveStringBuilder.append(in.nextLine());
        }

        return saveStringBuilder.toString();
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
