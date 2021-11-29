package Controller;
import entity.*;
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

import static java.lang.Math.max;
import static java.lang.Math.min;


public class Runner {

    @FXML private AnchorPane base;
    private GridPane board;
    private final double pixelWidth = 800;

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
        base.getChildren().add(board);
        char[][] testTiles =
                {{'G','G','G','G','G','G','G','G','G','G','G','G','G','G','G'},
                {'G','P','P','P','T','T','P','P','P','T','T','P','P','P','G'},
                {'G','G','G','P','G','G','G','G','P','G','G','P','G','P','G'},
                {'G','P','P','P','G','G','P','P','P','G','G','P','G','P','G'},
                {'G','P','G','G','G','G','G','G','P','G','G','P','G','P','G'},
                {'G','P','P','P','T','T','P','P','P','T','T','P','P','P','G'},
                {'G','G','G','G','G','G','G','G','G','G','G','G','G','G','G'}};
        createBoard(testTiles);
    }


    public void createBoard(char[][] tiles) {
        int width = tiles[0].length;
        int height = tiles.length;
        Image grass = new Image("file:src/controller/grassBlock.png");
        Image path = new Image("file:src/controller/dirtBlock.png");
        Image test = new Image("file:src/controller/test.png");
        int tilePixelWidth = min((int)pixelWidth / width, (int)pixelWidth/height);

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
                tileType.setFitHeight(tilePixelWidth);
                tileType.setFitWidth(tilePixelWidth);
                tile.getChildren().add(tileType);
                board.add(tile, x, y);
            }
        }



        board.setHgap(0);
        board.setVgap(0);
    }

    //public void drawBoard(tiles.Tile[][]) {

    //}
}