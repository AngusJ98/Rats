package Controller;

import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;

public class MenuRunner {
    private Parent root = null;
    public MenuRunner() {

    }

    private void startGame() throws Exception{
        try {
            FXMLLoader.load(getClass().getResource("game.fxml"));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }


}
