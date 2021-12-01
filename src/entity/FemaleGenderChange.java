package entity;

import javafx.scene.image.Image;

public class FemaleGenderChange extends Entity {
    private static final int DEFAULT_TIME = 4000;
    private static final int RANGE = 1;
    private int timer;

    public FemaleGenderChange(Image image, int[] position) {
        super(image, CollisionType.ITEM, position);
        timer = DEFAULT_TIME;
    }

    public FemaleGenderChange(int[] position, int timeLeft) {
        super(new Image(""), CollisionType.ITEM, position);
        timer = timeLeft;
    }

    public void onCollision(BasicRat target) {
    	target.setGender(RatTypes.FEMALE);
    }

    public void onCollision(Entity target) {

    }
}
