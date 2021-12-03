package entity;

import gameHandler.Pos;
import javafx.scene.image.Image;

public class Gas extends Entity {
    public Gas(Pos position) {
        super(new Image(""), CollisionType.ITEM, position);
    }



    @Override
    public void onCollision(Entity t) {

    }
}
