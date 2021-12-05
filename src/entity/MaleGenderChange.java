package entity;

import gameHandler.Pos;
import javafx.scene.image.Image;

public class MaleGenderChange extends Item {
    private static final int DEFAULT_TIME = 4000;
    private static final int RANGE = 1;
    private int timer;

    public MaleGenderChange(Pos position) {
        super(new Image("file:resources/male.png"), position);
        timer = DEFAULT_TIME;
    }

    public MaleGenderChange(Pos position, int timeLeft) {
        super(new Image(""), position);
        timer = timeLeft;
    }

    public void ratCollision(Rat target) {
        if(target.getRatType() != RatTypes.DEATH) {
            target.setRatType(RatTypes.MALE);
        }
	}
	public void itemCollision(Item target) {}
	public void onPlacement() {}	
	public void tick() {
		
	}
}