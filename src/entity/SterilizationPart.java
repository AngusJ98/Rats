package entity;

import gameHandler.Pos;
import javafx.scene.image.Image;

public class SterilizationPart extends Item{
	public SterilizationPart(Pos pos) {
		super(new Image(""), pos);
	}
	public void onPlacement() {}	
	public void tick() {}
	public void activateItem(){};
    protected void ratCollision(Rat target) {}
	public void itemCollision(Item target) {}
}