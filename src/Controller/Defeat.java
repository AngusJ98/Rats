package Controller;

import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;

import static Controller.Main.WINDOW_HEIGHT;
import static Controller.Main.WINDOW_WIDTH;

/**
 *
 <p> 1. File-name: Defeat.java</p>
 <p> 2. Creation Date: (N/A) </p>
 <p> 3. Last modification date:6/12/21</p>
 <p> 4. Purpose of the program: Display defeat message</p>
 * @author Gus
 */

public class Defeat {

    /**
     * returns to the menu scene
     */
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
}
