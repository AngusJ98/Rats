package entity;

import gameHandler.Pos;
import javafx.scene.image.Image;

public class Gas extends Item {
    public Gas(Pos position) {
        super(new Image(""), EntityType.ITEM, position);
    }
	public void tick() {
		
	}
	@Override
    public void ratCollision(Rat target) {
		//reduce rat hp by <25>
		//ratManager should check if any rat's hp is 0 after moving and kill those rats 
	}
	public void itemCollision(Item target) {}
	public void onPlacement() {}	
}
