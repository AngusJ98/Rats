package Controller;

import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.TilePane;


public class Runner {
    @FXML
    private GridPane board;


    Image grass = new Image("file:src/controller/test.png");
    private enum Items {
        Bomb, Gas, Sterilise, Poison, Male, Female, NoEntry, DeathRat, No
    }
    private Items currentSelected;

    public Runner() {

    }

    public void setItemBomb(ActionEvent actionEvent) {

    }

    public void initialize() {
        char[][] testTiles = {{'G','G','G','G','G','G','G','G','G','G','G','G','G','G','G'}, {'G','G','G','G','G','G','G','G','G','G','G','G','G','G','G'}};
        createBoard(testTiles);
    }


    public void createBoard(char[][] tiles) {

        for (int y = 0; y < tiles.length; y++) {
            for (int x = 0; x < tiles[y].length; x++) {

                switch (tiles[x][y]) {
                    case 'G':
                        ImageView newView = new ImageView(grass);
                        board.add(newView,x,y);

                }
            }
        }
    }


}