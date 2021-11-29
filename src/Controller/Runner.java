package Controller;
import entity.*;
import tiles.*;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.geometry.HPos;
import javafx.geometry.VPos;
import javafx.scene.Node;
import javafx.scene.control.Control;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.*;

import java.util.Stack;

import static java.lang.Math.max;
import static java.lang.Math.min;


public class Runner {

    @FXML
    private AnchorPane base;
    private GridPane board;
    private final double pixelWidth = 800;
    private StackPane[][] stackPaneArray = null; // a list of stackpanes indexed by row and col so we can add children later!
    private int tilePixelWidth = 1; //Set as default, will be changed later
    private int width = 0; //How many tiles wide the board is
    private int height = 0; //How many tiles high the board is

    private enum Items {
        Bomb, Gas, Sterilise, Poison, Male, Femaleri, NoEntry, DeathRat, No
    }

    private Items currentSelected;

    public Runner() {

    }

    public void setItemBomb(ActionEvent actionEvent) {

    }

    public void initialize() {
        board = new GridPane();
        board.setHgap(0);
        board.setVgap(0);
        base.getChildren().add(board);
        char[][] testTiles =
                {{'G', 'G', 'G', 'G', 'G', 'G', 'G', 'G', 'G', 'G', 'G', 'G', 'G', 'G', 'G'},
                        {'G', 'P', 'P', 'P', 'T', 'T', 'P', 'P', 'P', 'T', 'T', 'P', 'P', 'P', 'G'},
                        {'G', 'G', 'G', 'P', 'G', 'G', 'G', 'G', 'P', 'G', 'G', 'P', 'G', 'P', 'G'},
                        {'G', 'P', 'P', 'P', 'G', 'G', 'P', 'P', 'P', 'G', 'G', 'P', 'G', 'P', 'G'},
                        {'G', 'P', 'G', 'G', 'G', 'G', 'G', 'G', 'P', 'G', 'G', 'P', 'G', 'P', 'G'},
                        {'G', 'P', 'P', 'P', 'T', 'T', 'P', 'P', 'P', 'T', 'T', 'P', 'P', 'P', 'G'},
                        {'G', 'G', 'G', 'G', 'G', 'G', 'G', 'G', 'G', 'G', 'G', 'G', 'G', 'G', 'G'}};
        createBoard(testTiles);
    }


    public void createBoard(char[][] tiles) {
        this.width = tiles[0].length;
        this.height = tiles.length;
        Image grass = new Image("file:resources/grassBlock.png");
        Image path = new Image("file:resources/dirtBlock.png");
        Image test = new Image("file:resources/strightTun.png");
        this.tilePixelWidth = min((int) pixelWidth / this.width, (int) pixelWidth / this.height);//Min statement to account for rectangular board

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
                        tileType.setImage(test);
                        break;
                }
                tileType.setFitHeight(this.tilePixelWidth);
                tileType.setFitWidth(this.tilePixelWidth);
                tile.getChildren().add(tileType);
                board.add(tile, x, y);
            }
        }


        board.setHgap(0);
        board.setVgap(0);
    }


    public void drawBoard(Tile[][] tiles, Entity[] entities) {
        drawTiles(tiles);
        drawEntities(entities);
    }

    private void generateStackPaneArray() {
        this.stackPaneArray = new StackPane[width][height];
        for (Node pane : this.board.getChildren()) {
            this.stackPaneArray[GridPane.getRowIndex(pane)][GridPane.getColumnIndex(pane)] = (StackPane) pane;
        }
    }

    public void redrawBoard(Entity[] entities) {
        this.removeEntities();
        this.drawEntities(entities);
    }


    //This only needs to be called once... probably
    public void drawTiles(Tile[][] tiles) {
        for (int y = 0; y < tiles.length; y++) {
            for (int x = 0; x < tiles[y].length; x++) {
                StackPane tile = new StackPane();
                Tile gameTile = tiles[y][x];
                ImageView pic = new ImageView();
                pic.setImage(gameTile.getImage());
                pic.setFitHeight(this.tilePixelWidth);
                pic.setFitWidth(this.tilePixelWidth);
                tile.getChildren().add(pic);
                board.add(tile, x, y);
            }
        }
    }


    public void drawEntities(Entity[] entities) {
        for (Entity entity : entities) {
            ImageView pic = new ImageView();
            pic.setImage(entity.getImage());
            pic.setFitHeight(this.tilePixelWidth);
            pic.setFitWidth(this.tilePixelWidth);
            int x = entity.getXPosition(); //TODO use correct method here
            int y = entity.getYPosition(); //TODO use correct method here
            StackPane targetTile = this.stackPaneArray[x][y];
            targetTile.getChildren().add(pic);
        }
    }

    public void removeEntities() {
        for (Node node : board.getChildren()) {
            StackPane pane = (StackPane) node;
            int size = pane.getChildren().size();
            if (size > 1) {
                pane.getChildren().remove(1, size);
            }
        }
    }
}
