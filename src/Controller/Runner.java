package Controller;

import javafx.fxml.FXML;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.ClipboardContent;
import javafx.scene.input.Dragboard;
import javafx.scene.input.TransferMode;


public class Runner {
    @FXML
    private ImageView pbomb;

    //Image bombimg = new Image("../resources/bomb.png");
    public Runner() {

    }

    public void dragDetected(javafx.scene.input.MouseEvent mouseEvent) {
        Dragboard db = pbomb.startDragAndDrop(TransferMode.MOVE);
        ClipboardContent content = new ClipboardContent();
        content.putImage(pbomb.getImage());
        db.setContent(content);
        mouseEvent.consume();
    }
}