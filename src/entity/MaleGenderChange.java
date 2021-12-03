package entity;

import gameHandler.Pos;
import javafx.scene.image.Image;

public class MaleGenderChange extends Item {
    private static final int DEFAULT_TIME = 4000;
    private static final int RANGE = 1;
    private int timer;

    public MaleGenderChange(Image image, Pos position) {
        super(image, CollisionType.ITEM, position);
        timer = DEFAULT_TIME;
    }

    public MaleGenderChange(Pos position, int timeLeft) {
        super(new Image(""), CollisionType.ITEM, position);
        timer = timeLeft;
    }

    void onCollision(BasicRat target) {
    	target.setGender(RatTypes.MALE);
    }

    public void onCollision(Entity target) {

    }
}