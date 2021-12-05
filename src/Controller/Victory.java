package Controller;

import gameHandler.Game;
import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.scene.text.Text;


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
            Main.changeScene(Main.getMenuScene());

        } catch (Exception e) {
            e.printStackTrace();
            System.exit(1);
        }
    }

    public void initialize() {
        points.setText(Game.score + " POINTS!!");
    }
}
