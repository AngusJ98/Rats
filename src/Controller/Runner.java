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
    private TilePane board;


    Image grass = new Image('grassBlock.png');
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
        int width = tiles[0].length;
        ObservableList<Node> list = board.getChildren();
        board.setPrefColumns(width);
        ObservableList<Node> test = board.getChildren();

        for (char[] row : tiles) {
            for (char x : row) {

                switch (x) {
                    case 'G':
                        ImageView newView = new ImageView(grass);
                        list.add(newView);
                }
            }
        }
    }


}