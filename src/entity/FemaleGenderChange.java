package entity;

import gameHandler.Pos;
import javafx.scene.image.Image;

public class FemaleGenderChange extends Item {
    private static final int DEFAULT_TIME = 4000;
    private static final int RANGE = 1;
    private int timer;

    public FemaleGenderChange(Image image, Pos position) {
        super(image, EntityType.ITEM, position);
        timer = DEFAULT_TIME;
    }

    public FemaleGenderChange(Pos position, int timeLeft) {
        super(new Image(""), EntityType.ITEM, position);
        timer = timeLeft;
    }

    public void ratCollision(Rat target) {
		target.setGender(RatTypes.FEMALE);
	}
	public void itemCollision(Item target) {}
}
