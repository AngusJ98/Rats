package Controller;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import playerProfile.Player;

//This is a file
public class Main extends Application {

    private static Stage stg;
    public static final int WINDOW_WIDTH = 1000;
    public static final int WINDOW_HEIGHT = 800;
    private static int levelNum = 0;
    public static final int LEVEL_COUNT = 6;
    public static Player activePlayer = null;

    /**
     * Starts the game by launching the menu
     * @param primaryStage the static primary to be used
     * @throws Exception Exceptional
     */
    public void start(Stage primaryStage) throws Exception{
        stg = primaryStage;
        primaryStage.setResizable(false);

        Parent root = FXMLLoader.load(getClass().getResource("menu.fxml"));

        Scene menuScene = new Scene(root, WINDOW_WIDTH, WINDOW_HEIGHT);
        primaryStage.setScene(menuScene);
        primaryStage.show();
    }

    /**
     * LAUNCH DA GAME
     * @param args ARRRRRG I'm a pirate
     */
    public static void main(String[] args) {
        launch(args);
    }

    /**
     * used for switching between scenes
     * @param newScene the scene to switch to
     */
    public static void changeScene(Scene newScene) {
        stg.close();
        Main.stg.setScene(newScene);
        stg.show();
    }



}
