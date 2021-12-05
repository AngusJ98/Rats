package Controller;

import gameHandler.Game;
import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.text.Text;

import static Controller.Main.WINDOW_HEIGHT;
import static Controller.Main.WINDOW_WIDTH;


/**
 *
 <p> 1. File-name: Victory.java</p>
 <p> 2. Creation Date: (N/A) </p>
 <p> 3. Last modification date:</p>
 <p> 4. Purpose of the program: Display victory message</p>
 * @author Gus
 */

public class Victory {

    @FXML
    Text points;
    public void returnToMenu() {
        try {
            Parent root = FXMLLoader.load(getClass().getResource("menu.fxml"));
            Scene menuScene = new Scene(root, WINDOW_WIDTH, WINDOW_HEIGHT);
            Main.changeScene(menuScene);

        } catch (Exception e) {
            e.printStackTrace();
            System.exit(1);
        }
    }

    public void initialize() {
        points.setText(Game.score + " POINTS!!");
    }
}
