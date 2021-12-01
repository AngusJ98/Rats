package Controller;
import entity.*;
import gameHandler.Game;
import javafx.event.EventHandler;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ToggleButton;
import javafx.scene.control.ToggleGroup;
import javafx.scene.text.Text;
import tiles.*;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.*;


import java.util.HashMap;
import java.util.Map;

import static java.lang.Math.min;


public class Runner {

    @FXML private Text bombCount;
    @FXML private Text gasCount;
    @FXML private Text sterileCount;
    @FXML private Text noEntCount;
    @FXML private Text maleCount;
    @FXML private Text femaleCount;
    @FXML private AnchorPane base;
    @FXML private ToggleGroup itemToggle;

    private GridPane board;
    private final double pixelWidth = 600; //this is how wide the board section of the game is
    private StackPane[][] stackPaneArray = null; // a list of stackpanes indexed by row and col so we can add children later!
    private int tilePixelSize = 1; //Set as default, will be changed later

    public Runner() {
        Game.setRunner(this); //I hate doing this but is what it is
    }

    public void initialize() {

        board = new GridPane();
        board.setHgap(0);
        board.setVgap(0);
        base.getChildren().add(board);

    }

    public void normalTickDrawing(Entity[] entities) {
        this.updateCount();
        this.redrawEntities(entities);
    }

    /*
    This method should not be used, see drawBoard instead
    public void createBoardFromChar(char[][] tiles) {
        this.width = tiles[0].length;
        this.height = tiles.length;
        Image grass = new Image("file:resources/grassBlock.png");
        Image path = new Image("file:resources/dirtBlock.png");
        Image tun = new Image("file:resources/strightTun.png");
        Image speed = new Image("file:resources/speedTile.png");
        this.tilePixelSize = min((int) pixelWidth / this.width, (int) pixelWidth / this.height);//Min statement to account for rectangular board

        for (int y = 0; y < tiles.length; y++) {
            for (int x = 0; x < tiles[y].length; x++) {
                StackPane tile = new StackPane();
                ImageView tileType = new ImageView();
                switch (tiles[y][x]) {

                    case 'G':
                        tileType.setImage(grass);
                        break;
                    case 'P':
                        tileType.setImage(path);
                        //add a button on top to place items
                        break;
                    case 'T':
                        tileType.setImage(tun);
                        break;
                    case 'S':
                        tileType.setImage(speed);
                        break;
                }
                tileType.setFitHeight(this.tilePixelSize);
                tileType.setFitWidth(this.tilePixelSize);
                tile.getChildren().add(tileType);
                board.add(tile, x, y);
            }
        }


        board.setHgap(0);
        board.setVgap(0);
    }*/


    public void drawBoard(Entity[] entities) {
        drawTiles();
        redrawEntities(entities);
    }

    private void generateStackPaneArray() {
        this.stackPaneArray = new StackPane[Game.TileManager.getNumTileWidth()][Game.TileManager.getNumTileHeight()];
        for (Node pane : this.board.getChildren()) {
            this.stackPaneArray[GridPane.getRowIndex(pane)][GridPane.getColumnIndex(pane)] = (StackPane) pane;
        }
    }

    //Completely redraws board, don't think it's needed
    public void redrawBoard(Entity[] entities) {
        this.board.getChildren().clear();
        this.drawBoard(entities);
    }

    public void redrawEntities(Entity[] entities) {
        this.removeEntities();
        this.drawEntities(entities);
    }


    //This only needs to be called once... probably
    public void drawTiles() {
        this.tilePixelSize = min((int) pixelWidth / Game.TileManager.getNumTileWidth(), (int) pixelWidth / Game.TileManager.getNumTileWidth());//Min statement to account for rectangular board
        HashMap<int[], Tile> tiles = Game.getTiles();
        for (HashMap.Entry<int[], Tile> tileEntry : Game.getTiles().entrySet()) {
            Tile tile = tileEntry.getValue();
            int[] tilePos = tileEntry.getKey();
            int x = tilePos[0];
            int y = tilePos[1];
            StackPane paneToAdd = new StackPane();
            ImageView pic = new ImageView();
            pic.setImage(tile.getImage());
            pic.setFitHeight(this.tilePixelSize);
            pic.setFitWidth(this.tilePixelSize);
            paneToAdd.getChildren().add(pic);
            board.add(paneToAdd, x, y);

            //add a transparent button on top so we can add items
            Button b = new Button();
            b.setMaxSize(this.tilePixelSize,this.tilePixelSize);
            b.setPrefSize(this.tilePixelSize,this.tilePixelSize);
            b.setStyle(
                    "    -fx-border-color: transparent;\n" +
                    "    -fx-border-width: 0;\n" +
                    "    -fx-background-radius: 0;\n" +
                    "    -fx-background-color: transparent;\n" +
                    "    -fx-text-fill: #828282;"
            );

            //set button action if items can be placed
            if (tile.areItemsPlaceable()) {
                b.setOnAction(new EventHandler<ActionEvent>() {
                    @Override public void handle(ActionEvent e) {
                        int buttonX = GridPane.getRowIndex(b.getParent());
                        int buttonY = GridPane.getColumnIndex(b.getParent());
                        ToggleButton selectedButton = (ToggleButton)itemToggle.getSelectedToggle();
                        String itemString = selectedButton.getId();
                        ItemManager.tryPlace(itemString, buttonX ,buttonY);
                        //TODO Correct to item placement method
                    }
                });
            }
            paneToAdd.getChildren().add(b);
        }
    }



    public void drawEntities(Entity[] entities) {
        for (Entity entity : entities) {
            ImageView pic = new ImageView();
            pic.setImage(entity.getImage());
            pic.setFitHeight(this.tilePixelSize);
            pic.setFitWidth(this.tilePixelSize);
            int[] position = entity.getPosition();
            int x = position[0];
            int y = position[1];
            StackPane targetTile = this.stackPaneArray[x][y];
            targetTile.getChildren().add(pic);
        }
    }

    public void removeEntities() {
        for (Node node : board.getChildren()) {
            StackPane pane = (StackPane) node;
            int size = pane.getChildren().size();
            if (size > 1) {
                pane.getChildren().remove(2, size); //first 2 are tile and button for item placement
            }
        }
    }

    public void updateCount() {
        this.bombCount.setText(String.valueOf(Inventory.getBombCount()));
        this.gasCount.setText(String.valueOf(Inventory.getgasCount()));
        this.sterileCount.setText(String.valueOf(Inventory.getsterileCount()));
        this.noEntCount.setText(String.valueOf(Inventory.getnoEntryCount()));
        this.maleCount.setText(String.valueOf(Inventory.getmaleCount()));
        this.femaleCount.setText(String.valueOf(Inventory.getfemaleCount()));
    }

    public void returnToMenu() {
        try {
            Main.changeScene(Main.getMenuScene());
        } catch (Exception e) {
            e.printStackTrace();
            System.exit(1);
        }
    }
}
