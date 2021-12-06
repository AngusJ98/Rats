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

/**
 * <p> 1. File-name: MenuRunner.java</p>
 * <p> 2. Creation Date: 1/12/21 </p>
 * <p> 3. Last modification date:6/12/21</p>
 * <p> 4. Purpose of the program: Runs the menu</p>
 *
 * @author Gus
 */

public class MenuRunner {

    @FXML
    private AnchorPane menuBase;
    @FXML
    private TabPane highscore;
    @FXML
    private Menu profiles;
    @FXML
    private Text playerId;
    @FXML
    private Menu play;
    @FXML
    private Menu load;

    public MenuRunner() {
        Alert motd = new Alert(Alert.AlertType.INFORMATION);
        motd.setTitle("Message of the day!");
        motd.setHeaderText("");
        motd.setContentText(MessageOfTheDay.getMotd());
        motd.showAndWait();

    }


    /**
     * Runs code to set up the menu when it's initialised such
     * as updating highscore and dynamically creating buttons
     */
    public void initialize() {

        HBox table = new HBox(2);
        table.setAlignment(Pos.TOP_CENTER);
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
            playerId.setText(Main.activePlayer.getPlayerName());
        }


    }

    /**
     * starts the game, Switches scene and creates the game object
     *
     * @throws Exception exceptional
     */
    private void startGame() throws Exception {
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


    /**
     * creates a menu item for each profile
     */
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
                @Override
                public void handle(ActionEvent e) {
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


    /**
     * updates the high score using data from player profiles
     */
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

            table.getColumnConstraints().addAll(colCon, colCon);
            int j = 0;
            for (Player mini : highScorers) {
                Text name = new Text(mini.getPlayerName());
                Text score = new Text(mini.getScores()[i] + "");
                name.setFont(new Font(20));
                score.setFont(new Font(20));
                table.add(name, 0, j);
                table.add(score, 1, j);
                j++;
            }


            newTab.setContent(table);

            highscore.getTabs().add(newTab);

        }
    }


    /**
     * button method to start game at level 0
     *
     * @param actionEvent not used,
     * @throws Exception exceptional
     */
    public void level0(ActionEvent actionEvent) throws Exception {
        Game.setLevelPath("testLevel");
        Game.setLevelNum(0);
        this.startGame();
    }

    /**
     * button method to start game at level 1
     *
     * @param actionEvent not used,
     * @throws Exception exceptional
     */
    public void level1(ActionEvent actionEvent) throws Exception {
        Game.setLevelPath("level1");
        Game.setLevelNum(1);
        this.startGame();
    }

    /**
     * button method to start game at level 2
     *
     * @param actionEvent not used,
     * @throws Exception exceptional
     */
    public void level2(ActionEvent actionEvent) throws Exception {
        Game.setLevelPath("level2");
        Game.setLevelNum(2);
        this.startGame();
    }

    /**
     * button method to start game at the gus level
     *
     * @param actionEvent not used,
     * @throws Exception exceptional
     */
    public void gusLevel(ActionEvent actionEvent) throws Exception {
        Game.setLevelPath("gusSpeedway");
        Game.setLevelNum(4);
        this.startGame();
    }

    /**
     * button method to start game at the dylan level
     *
     * @param actionEvent not used,
     * @throws Exception exceptional
     */
    public void dylanLevel(ActionEvent actionEvent) throws Exception {
        Game.setLevelPath("DylanLevel");
        Game.setLevelNum(3);
        this.startGame();
    }


    /**
     * button method to start game at the liam level
     *
     * @param actionEvent not used,
     * @throws Exception exceptional
     */
    public void liam(ActionEvent actionEvent) throws Exception {
        Game.setLevelPath("liam");
        Game.setLevelNum(5);
        this.startGame();
    }


    /**
     * creates a dialog box to create a profile
     * then reloads the menu if yes is clicked
     */
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

    /**
     * button method to load save slot 1
     *
     * @param event not used
     * @throws Exception Exceptional
     */
    public void load1(ActionEvent event) throws Exception {
        load("1");
    }

    /**
     * button method to load save slot 1
     *
     * @param event not used
     * @throws Exception Exceptional
     */
    public void load2(ActionEvent event) throws Exception {
        load("2");
    }

    /**
     * button method to load save slot 1
     *
     * @param event not used
     * @throws Exception Exceptional
     */
    public void load3(ActionEvent event) throws Exception {
        load("3");
    }

    /**
     * starts the game by setting the game to be loaded and switching scene
     * , then calling the setup and start methods on game
     *
     * @param path the path to the save file
     */
    private void startGameFromSave(String path) {
        try {
            //switch scene
            Parent gameScreen = FXMLLoader.load(getClass().getResource("game.fxml"));
            Scene gameScene = new Scene(gameScreen);
            Main.changeScene(gameScene);

            //Create new game object
            Game game = new Game();
            game.setUpFromSave();
            game.startFromSave();
            //TODO pass code to load the level file
        } catch (Exception e) {
            e.printStackTrace();
            System.exit(1);
        }
    }

    /**
     * Sets up game to be loaded from a save file
     *
     * @param path the path to the save file
     * @throws Exception Exceptional
     */
    private void load(String path) throws Exception {
        Game.setLevelPath(path);
        Game.setLevelNum(8);
        this.startGameFromSave(path);
    }
}
