package Controller;
import gameHandler.Game;
import highscore.*;
import javafx.event.ActionEvent;
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
    

    public MenuRunner() {

    }


    //shamelessly stolen from highscore
    public void initialize() {
        HBox table = new HBox(2);
        table.setAlignment(Pos.TOP_CENTER);


        table.setStyle("-fx-background-color:GREY");

        //add the table to the scene
        menuBase.getChildren().add(table);
    }

    private void startGame() throws Exception{
        try {
            //switch scene
            Parent gameScreen = FXMLLoader.load(getClass().getResource("game.fxml"));
            Scene gameScene = new Scene(gameScreen);
            Main.changeScene(gameScene);

            //Create new game object
            Game game = new Game();
            game.setUp();
            game.start();
            //TODO pass code to load the level file
        } catch (Exception e) {
            e.printStackTrace();
            System.exit(1);
        }
    }

    public void level1(ActionEvent actionEvent) throws Exception {
        Game.setLevelPath("testLevel");
        this.startGame();
    }

    public void level2(ActionEvent actionEvent) throws Exception {
        Game.setLevelPath("level2");
        this.startGame();
    }
}
