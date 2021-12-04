package entity;

import gameHandler.Pos;
import javafx.scene.image.Image;

public abstract class Item extends Entity {

    public Item(Image image, Pos pos) {
        super(image, EntityType.ITEM, pos);
    }
    public abstract void onCollision();
    public abstract void activateItem();
}