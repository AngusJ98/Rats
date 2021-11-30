package entity;

import javafx.scene.image.Image;

public class MaleGenderChange extends Entity {

    public MaleGenderChange(Image image, int[] position) {
        super(image, CollisionType.ITEM, position);
    }

    void onCollision(BasicRat target) {
    	target.setGender(RatTypes.MALE);
    }

    public void onCollision(Entity target) {

    }
}