package Controller;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Pos;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ToggleButton;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.HBox;
import sun.audio.AudioStream;

import java.io.File;
import java.io.InputStream;

public class MenuRunner {

    @FXML private AnchorPane menuBase;

    private final ToggleButton audioButton = new ToggleButton("Mute");
    private final Button mainmenuButton = new Button("Main Menu");

    private static final File song = new File("resources/leaderboard.mp3"); // doesn't exist yet
    private static final double width = 400;
    private static final double height = 720;

    private AudioStream audio;
    private InputStream in;
    private boolean isMusicPlaying;


    public MenuRunner() {

    }


    //shamelessly stolen from highscore
    public void initialize() {
        HBox table = new HBox(2);
        table.setAlignment(Pos.TOP_CENTER);

        table.getChildren().addAll(mainmenuButton, audioButton);

        table.setStyle("-fx-background-color:GREY");
        menuBase.getChildren().add(table);


    }

    private void startGame(String levelPath) throws Exception{
        try {
            Scene game = FXMLLoader.load(getClass().getResource("game.fxml"));
            Main.changeScene(game);
            //TODO pass code to load the level file
        } catch (Exception e) {
            e.printStackTrace();
            System.exit(1);
        }
    }





}
