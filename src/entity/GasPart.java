package entity;

import gameHandler.Pos;
import javafx.scene.image.Image;

public class GasPart extends Item {
	public GasPart(Pos position) {
		super(new Image(""), EntityType.ITEM, position);
	}
	@Override
	public void tick() {} 
    public void ratCollision(Rat target) {
		//decrease hp by 25
		//ratmanager will check the hp of rats 
		//after every move and kill rats at 0 hp
		if (!target.getRatType() == RatTypes.DEATH) {
			target.setHP(target.getHP() - 25);
		}	
	}
	public void itemCollision(Item target) {}
	public void onPlacement() {}
}