import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ToggleButton;
import javafx.scene.layout.HBox;
import javafx.stage.Stage;
import sun.audio.AudioPlayer;
import sun.audio.AudioStream;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;

public class HighScoreTable {

    private final ToggleButton audioButton = new ToggleButton("Mute");
    private final Button mainmenuButton = new Button("Main Menu");

    private static final File song = new File("resources/leaderboard.mp3"); // doesn't exist yet
    private static final double width = 400;
    private static final double height = 720;

    private AudioStream audio;
    private InputStream in;
    private boolean isMusicPlaying;

    public HighScoreTable(Stage leaderboardStage) throws FileNotFoundException, java.io.IOException {

        //new AudioPlayer(song); // AudioPlayer is a static class so you can't init it

        in = new FileInputStream(song);
        this.audio = new AudioStream(in);
        startMusic();

        HBox root = new HBox(2);
        root.setAlignment(Pos.TOP_CENTER);

        root.getChildren().addAll(mainmenuButton, audioButton);

        root.setStyle("-fx-background-color:GREY");
        Scene scene = new Scene(root, width, height);


        // Action for Buttons
        audioButton.setOnAction(e -> {
            try {
                switchMusic();
            } catch (FileNotFoundException fileNotFoundException) {
                // TODO Auto-generated catch block
                fileNotFoundException.printStackTrace();
            }
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

    // just made these methods so it would compile
    private void switchMusic() throws FileNotFoundException {
        if (isMusicPlaying) {
            stopMusic();
        } else {
            startMusic();
        }
    }

    private void mainMenu(Stage leaderboardStage) {
    }

    private void startMusic() {
        AudioPlayer.player.start(audio);
        isMusicPlaying = true;
    }

    private void stopMusic() {
        AudioPlayer.player.start(audio);
        isMusicPlaying = false;
    }

}