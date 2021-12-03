package entity;

import gameHandler.Pos;
import javafx.scene.image.Image;

public class MaleGenderChange extends Item {
    private static final int DEFAULT_TIME = 4000;
    private static final int RANGE = 1;
    private int timer;

    public MaleGenderChange(Image image, Pos position) {
        super(image, EntityType.ITEM, position);
        timer = DEFAULT_TIME;
    }

    public MaleGenderChange(Pos position, int timeLeft) {
        super(new Image(""), EntityType.ITEM, position);
        timer = timeLeft;
    }

    public void ratCollision(Rat target) {
		targer.setGender(RatTypes.MALE);
	}
	public void itemCollision(Item target) {}
	public void onPlacement() {}	
	public void tick() {
		
	}
}