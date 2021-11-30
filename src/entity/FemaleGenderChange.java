package entity;

import javafx.scene.image.Image;

public class FemaleGenderChange extends Entity {

    public FemaleGenderChange(Image image, int[] position) {
        super(image, CollisionType.ITEM, position);
    }

    public void onCollision(BasicRat target) {
    	target.setGender(RatTypes.FEMALE);
    }

    public void onCollision(Entity target) {

    }
}
