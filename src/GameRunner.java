import javafx.application.Application;
import javafx.stage.Stage;

public class GameRunner extends Application {
    public static void main(String[] args) {
        launch(args);
    }

    public void start(Stage menu) {
        menu.setTitle("RATS!");
        menu.show();
    }
}
