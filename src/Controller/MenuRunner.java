package Controller;
import gameHandler.Game;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Pos;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.ColumnConstraints;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import messageOfTheDay.MessageOfTheDay;
import playerProfile.Player;
import playerProfile.ProfileReader;

import java.util.ArrayList;
import java.util.Optional;

public class MenuRunner {

    @FXML private AnchorPane menuBase;
    @FXML private TabPane highscore;
    @FXML private Menu profiles;
    @FXML private Text playerId;
    @FXML private Menu play;
    public MenuRunner() {

    }


    //shamelessly stolen from highscore
    public void initialize() {

        HBox table = new HBox(2);
        table.setAlignment(Pos.TOP_CENTER);
        Alert motd = new Alert(Alert.AlertType.INFORMATION);
        motd.setTitle("Message of the day!");
        motd.setHeaderText("");
        motd.setContentText(MessageOfTheDay.getMotd());
        motd.showAndWait();




        table.setStyle("-fx-background-color:GREY");
        updateHigh();
        createProfileButtons();
        //add the table to the scene
        menuBase.getChildren().add(table);

        for (int i = 0; i < play.getItems().size(); i++) {
            play.getItems().get(i).setDisable(false);
        }

        if (Main.activePlayer == null) {
            playerId.setText("Select a player profile before playing");
            for (MenuItem i : play.getItems()) {
                i.setDisable(true);
            }
        } else {
            for (int i = play.getItems().size() - 1; i > Main.activePlayer.getMaxLevelUnlocked(); i--) {
                play.getItems().get(i).setDisable(true);
            }
        }


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

    private void createProfileButtons() {
        while (profiles.getItems().size() > 1) {
            profiles.getItems().remove(1);
        }
        Player[] players = ProfileReader.retrieveAllPlayers();
        int i = 0;
        for (Player player : players) {
            MenuItem m = new MenuItem();
            m.setText(player.getPlayerName());
            m.setId(i + "");
            m.setOnAction(new EventHandler<ActionEvent>() {
                @Override public void handle(ActionEvent e) {
                    Player[] allPs = ProfileReader.retrieveAllPlayers();
                    Main.activePlayer = allPs[Integer.parseInt(m.getId())];
                    playerId.setText(Main.activePlayer.getPlayerName());
                    initialize();
                }
            });
            profiles.getItems().add(m);
            i++;
        }
    }



    private void updateHigh() {
        highscore.getTabs().clear();
        for (int i = 0; i < 6; i++) {
            Tab newTab = new Tab();
            newTab.setText("Level " + i);
            ArrayList<Player> highScorers = ProfileReader.getHighScores(i);

            GridPane table = new GridPane();
            table.setGridLinesVisible(true);
            ColumnConstraints colCon = new ColumnConstraints();
            colCon.setPercentWidth(20);

            table.getColumnConstraints().addAll(colCon,colCon);
            int j = 0;
            for (Player mini :highScorers) {
                Text name = new Text(mini.getPlayerName());
                Text score = new Text (mini.getScores()[i] + "");
                name.setFont(new Font(20));
                score.setFont(new Font(20));
                table.add(name,0,j);
                table.add(score, 1, j);
                j++;
            }




            newTab.setContent(table);

            highscore.getTabs().add(newTab);

        }
    }

    public void updateScore(ActionEvent actionEvent) throws Exception {
        this.initialize();
    }

    public void level0(ActionEvent actionEvent) throws Exception {
        Game.setLevelPath("testLevel");
        Game.setLevelNum(0);
        this.startGame();
    }

    public void level1(ActionEvent actionEvent) throws Exception {
        Game.setLevelPath("testLevel");
        Game.setLevelNum(1);
        this.startGame();
    }

    public void level2(ActionEvent actionEvent) throws Exception {
        Game.setLevelPath("level2");
        Game.setLevelNum(2);
        this.startGame();
    }

    public void gusLevel(ActionEvent actionEvent) throws Exception {
        Game.setLevelPath("gusSpeedway");
        Game.setLevelNum(4);
        this.startGame();
    }

    public void dylanLevel(ActionEvent actionEvent) throws Exception {
        Game.setLevelPath("DylanLevel");
        Game.setLevelNum(3);
        this.startGame();
    }



    public void liam(ActionEvent actionEvent) throws Exception {
        Game.setLevelPath("liam");
        Game.setLevelNum(5);
        this.startGame();
    }

    public void createProfile() {
        TextInputDialog t = new TextInputDialog("");
        t.setContentText("Enter your name:");
        t.setTitle("Create Profile");
        Optional<String> response = t.showAndWait();
        if (response.isPresent()) {
            ProfileReader.writeNewProfile(response.get());
        }
        this.initialize();
    }
}
