package entity;

import javafx.scene.image.Image;

public class Gas extends Entity {
    public Gas(int[] position) {
        super(new Image(""), CollisionType.ITEM, position);
    }



    @Override
    public void onCollision(Entity t) {

    }
}
