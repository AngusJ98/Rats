package entity;

import gameHandler.Pos;
import javafx.scene.image.Image;

public class Sterilization extends Item{
    private static final int DEFAULT_TIME = 4000; // idk how long it's meant to stay for
    private static final int RANGE = 3; //idk what range should be either
    private int timer;

    public Sterilization(Pos position) {
        super(new Image(""), position);
        timer = DEFAULT_TIME;
    }

    public Sterilization(Pos position, int timeLeft) {
        super(new Image(""), position);
        timer = timeLeft;
    }
	
	public void onPlacement() {
		//spawn a bunch of sterilizationparts (invisible) on all passable tiles in a radius x radius area
		//delete them all when the item expires
	}

    public void ratCollision(Rat target) {
		target.setMateStatus(false);
	}
	public void itemCollision(Item target) {}	
	public void tick() {}
}

