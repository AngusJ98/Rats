import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ToggleButton;
import javafx.scene.layout.HBox;
import javafx.stage.Stage;
import sun.audio.AudioPlayer;

public class LeaderboardGUI {

    private final ToggleButton audioButton = new ToggleButton("Mute");
    private final Button mainmenuButton = new Button("Main Menu");

    private final String song = "leaderboard";
    private static double width = 400;
    private static double height = 720;

    public LeaderboardGUI(Stage leaderboardStage) {

        new AudioPlayer(song);

        HBox root = new HBox(2);
        root.setAlignment(Pos.TOP_CENTER);

        root.getChildren().addAll(mainmenuButton, audioButton);

        root.setStyle("-fx-background-color:GREY");
        Scene scene = new Scene(root, width, height);


        // Action for Buttons
        audioButton.setOnAction(e -> {
            switchMusic();
        });


        mainmenuButton.setOnAction(e -> {
            try {
                mainMenu(leaderboardStage);
            } catch (Exception e1) {
// TODO Auto-generated catch block
                e1.printStackTrace();
            }
        });
    }
}