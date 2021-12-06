package Controller;

import entity.*;
import gameHandler.Game;
import gameHandler.GameFileHandler;
import gameHandler.Inventory;
import gameHandler.Pos;
import javafx.event.EventHandler;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ToggleButton;
import javafx.scene.control.ToggleGroup;
import javafx.scene.text.Text;
import tiles.*;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.image.ImageView;
import javafx.scene.layout.*;


import java.util.HashMap;

import static Controller.Main.WINDOW_HEIGHT;
import static Controller.Main.WINDOW_WIDTH;
import static java.lang.Math.min;

/**
 * <p> 1. File-name: GameRenderer.java</p>
 * <p> 2. Creation Date: 26/11/21 </p>
 * <p> 3. Last modification date: 5/12/21 </p>
 * <p> 4. Purpose of the program: To render the game </p>
 *
 * @author Gus
 */

public class GameRenderer {

    @FXML
    private Text bombCount;
    @FXML
    private Text gasCount;
    @FXML
    private Text sterileCount;
    @FXML
    private Text noEntCount;
    @FXML
    private Text maleCount;
    @FXML
    private Text femaleCount;
    @FXML
    private Text deathCount;
    @FXML
    private Text poisonCount;
    @FXML
    private AnchorPane base;
    @FXML
    private ToggleGroup itemToggle;
    @FXML
    private StackPane board;

    private final double pixelWidth = 600; //this is how wide the board section of the game is
    private int tilePixelSize = 1; //Set as default, will be changed later
    private GridPane entityBoard;
    private GridPane buttonBoard;
    private GridPane tileBoard;

   /*
                                    xxxxxxx
                               x xxxxxxxxxxxxx x
                            x     xxxxxxxxxxx     x
                                   xxxxxxxxx
                         x          xxxxxxx          x
                                     xxxxx
                        x             xxx             x
                                       x
                       xxxxxxxxxxxxxxx   xxxxxxxxxxxxxxx
                        xxxxxxxxxxxxx     xxxxxxxxxxxxx
                         xxxxxxxxxxx       xxxxxxxxxxx
                          xxxxxxxxx         xxxxxxxxx
                            xxxxxx           xxxxxx
                              xxx             xxx
                                  x         x
                                       x
                        BAD CODE WARNING, DO NOT LOOK HERE
    */

    /**
     * Constructor. adds a reference to itself to game class.
     * <p> big side-effects</p>
     * <p> not referentially transparent</p>
     */
    public GameRenderer() {
        Game.setRunner(this); //I hate doing this but is what it is
    }

    /**
     * Initialises graphical components
     */
    public void initialize() {
        this.tileBoard = new GridPane();
        this.entityBoard = new GridPane();
        this.buttonBoard = new GridPane();
        board.getChildren().addAll(tileBoard, entityBoard, buttonBoard);
        tileBoard.setGridLinesVisible(true);
    }

    /**
     * The normal drawing actions done in a tick.
     *
     * @param entities the entities to be drawn
     */
    public void normalTickDrawing(Entity[] entities) {
        this.updateCount();
        this.redrawEntities(entities);
    }

    /**
     * Draws the tiles and the entities on it
     *
     * @param entities the entities to be drawn
     */
    public void drawBoard(Entity[] entities) {
        drawTiles();
        redrawEntities(entities);

    }


    /**
     * Redraws the entire board, used at the start of the game
     *
     * @param entities
     */
    public void redrawBoard(Entity[] entities) {
        this.entityBoard.getChildren().clear();
        this.drawBoard(entities);
    }

    /**
     * removes old graphics of entities and updates them
     *
     * @param entities
     */
    public void redrawEntities(Entity[] entities) {
        this.removeEntities();
        this.drawEntities(entities);
    }


    /**
     * Draws the tiles on the map.
     */
    public void drawTiles() {
        this.tilePixelSize = min((int) pixelWidth / Game.TileManager.getNumTileWidth(), (int) pixelWidth / Game.TileManager.getNumTileHeight());//Min statement to account for rectangular board

        HashMap<Pos, Tile> tiles = Game.getTiles();
        for (Pos tilePos : Game.getTiles().keySet()) {
            Tile tile = tiles.get(tilePos);
            int x = tilePos.x;
            int y = tilePos.y;
            ImageView pic = new ImageView();
            pic.setImage(tile.getImage());
            pic.setFitHeight(this.tilePixelSize);
            pic.setFitWidth(this.tilePixelSize);

            tileBoard.add(pic, x, y);
            //add a transparent button on top so we can add items
            Button b = new Button();
            b.setMaxSize(this.tilePixelSize, this.tilePixelSize);
            b.setPrefSize(this.tilePixelSize, this.tilePixelSize);
            b.setMinSize(this.tilePixelSize, this.tilePixelSize);
            b.setStyle(
                    "    -fx-border-color: transparent;\n" +
                            "    -fx-border-width: 0;\n" +
                            "    -fx-background-radius: 10;\n" +
                            "    -fx-background-color: transparent\n"
            );

            //set button action if items can be placed
            if (tile.areItemsPlaceable()) {
                b.setOnAction(new EventHandler<ActionEvent>() {
                    @Override
                    public void handle(ActionEvent e) {
                        int buttonY = GridPane.getRowIndex(b);
                        int buttonX = GridPane.getColumnIndex(b);
                        ToggleButton selectedButton = (ToggleButton) itemToggle.getSelectedToggle();
                        if (selectedButton != null) {
                            String itemString = selectedButton.getId();
                            Game.ItemManager.tryPlace(itemString, new Pos(buttonX, buttonY));
                        }
                    }
                });
            }
            buttonBoard.add(b, x, y);

        }
    }

    /**
     * Draws the entities
     *
     * @param entities the entities to be drawn
     */
    public void drawEntities(Entity[] entities) {
        for (int x = 0; x < Game.TileManager.getNumTileWidth(); x++) {
            for (int y = 0; y < Game.TileManager.getNumTileHeight(); y++) {
                ImageView fill = new ImageView();
                fill.setFitHeight(this.tilePixelSize);
                fill.setFitWidth(this.tilePixelSize);
                entityBoard.add(fill, x, y);
            }
        }
        for (Entity entity : entities) {
            if (!Game.TileManager.getTile(entity.getPosition()).getHidesRats()) {
                ImageView pic = new ImageView();
                pic.setImage(entity.getImage());
                pic.setFitHeight(this.tilePixelSize);
                pic.setFitWidth(this.tilePixelSize);
                Pos position = entity.getPosition();
                int x = position.x;
                int y = position.y;

                if (entity instanceof Rat) {
                    Rat rat = (Rat) entity;
                    pic.setRotate(180 + rat.getMoveDirection().ordinal() * 90);
                }
                entityBoard.add(pic, x, y);
            }
        }
    }

    /**
     * Clears the board of entities
     */
    public void removeEntities() {
        entityBoard.getChildren().clear();
    }

    /**
     * updates the count of items displayed on the board
     */
    public void updateCount() {
        this.bombCount.setText(String.valueOf(Inventory.getBombCount()));
        this.gasCount.setText(String.valueOf(Inventory.getGasCount()));
        this.sterileCount.setText(String.valueOf(Inventory.getSterileCount()));
        this.noEntCount.setText(String.valueOf(Inventory.getNoEntryCount()));
        this.maleCount.setText(String.valueOf(Inventory.getMaleCount()));
        this.femaleCount.setText(String.valueOf(Inventory.getFemaleCount()));
        this.deathCount.setText(String.valueOf(Inventory.getDeathCount()));
        this.poisonCount.setText(String.valueOf(Inventory.getPoisonCount()));
    }

    /**
     * Method to return to the menu from the game
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

    /**
     * method to move to the loss screen
     */
    public void lossScreen() {
        try {
            Parent gameScreen = FXMLLoader.load(getClass().getResource("defeat.fxml"));
            Scene gameScene = new Scene(gameScreen);
            Main.changeScene(gameScene);
        } catch (Exception e) {
            e.printStackTrace();
            System.exit(1);
        }
    }

    /**
     * Method to move to the victory screen
     */
    public void victoryScreen() {
        try {
            Parent gameScreen = FXMLLoader.load(getClass().getResource("victory.fxml"));
            Scene gameScene = new Scene(gameScreen);
            Main.changeScene(gameScene);
        } catch (Exception e) {
            e.printStackTrace();
            System.exit(1);
        }
    }

    /**
     * action for save button
     *
     * @param event
     */
    public void save1(ActionEvent event) {
        save("1");
    }

    /**
     * action for save button
     *
     * @param event
     */
    public void save2(ActionEvent event) {
        save("2");
    }

    /**
     * action for save button
     *
     * @param event
     */
    public void save3(ActionEvent event) {
        save("3");
    }

    /**
     * Saves the current game state
     *
     * @param path the json file to save it in.
     */
    private void save(String path) {
        GameFileHandler.saveGame(path);
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
