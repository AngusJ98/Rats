package entity;

import gameHandler.Game;
import gameHandler.Pos;
import javafx.scene.image.Image;

public class FemaleGenderChange extends Item {
    private static final int DEFAULT_TIME = 4000;
    private static final int RANGE = 1;
    private int timer;

    public FemaleGenderChange(Pos position) {
        super(new Image("File:resources/female.png"), position);
        timer = DEFAULT_TIME;
    }


    public void ratCollision(Rat target) {
        if(target.getRatType() != RatTypes.DEATH && target.getRatType() != RatTypes.BABY) {
            BasicRat targ = (BasicRat) target;
            targ.setGender(RatTypes.FEMALE);
        }
        Game.ItemManager.killItem(this);
    }

	public void itemCollision(Item target) {}
	public void onPlacement() {}	
	public void tick() {
		//decrease a variable somewhere so this item is removed after a certain time idk
	}
}
