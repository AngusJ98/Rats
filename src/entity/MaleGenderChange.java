package entity;

import gameHandler.Game;
import gameHandler.Pos;
import javafx.scene.image.Image;

public class MaleGenderChange extends Item {

    public MaleGenderChange(Pos position) {
        super(new Image("file:resources/male.png"), position);

    }



    public void ratCollision(Rat target) {
        if(target.getRatType() != RatTypes.DEATH && target.getRatType() != RatTypes.BABY) {
            BasicRat targ = (BasicRat) target;
            targ.setGender(RatTypes.MALE);
        }
        Game.ItemManager.killItem(this);
	}
	public void itemCollision(Item target) {}
	public void onPlacement() {}	
	public void tick() {
		
	}
}