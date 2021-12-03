package entity;

import gameHandler.Pos;
import javafx.scene.image.Image;

public class Gas extends Item {
    public Gas(Pos position) {
        super(new Image(""), CollisionType.ITEM, position);
    }



    @Override
    public void onCollision(Entity t) {

    }
}
