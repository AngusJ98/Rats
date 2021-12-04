package entity;

import gameHandler.Pos;
import javafx.scene.image.Image;

public class FemaleGenderChange extends Item {
    private static final int DEFAULT_TIME = 4000;
    private static final int RANGE = 1;
    private int timer;

    public FemaleGenderChange(Pos position) {
        super(new Image(""), position);
        timer = DEFAULT_TIME;
    }

    public FemaleGenderChange(Pos position, int timeLeft) {
        super(new Image(""), position);
        timer = timeLeft;
    }

    public void ratCollision(Rat target) {
        if(target instanceof BasicRat) {
            target.setRatType(RatTypes.FEMALE);
        }
	}

	public void onCollision(){}
	public void itemCollision(Item target) {}
	public void onPlacement() {}	
	public void tick() {
		//decrease a variable somewhere so this item is removed after a certain time idk
	}
}
