package entity;

import javafx.scene.image.Image;

public class FemaleGenderChange extends Entity {

    public FemaleGenderChange(Image image) {
        super(image, CollisionType.ITEM);
    }

    public void onCollision(BasicRat target) {
    	target.setGender(RatTypes.FEMALE);
    }

    public void onCollision(Entity target) {

    }
}
