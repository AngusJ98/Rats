package entity;

import gameHandler.Game;
import gameHandler.Pos;
import javafx.scene.image.Image;

import java.util.Random;
import java.util.concurrent.ThreadLocalRandom;

public class GasPart extends Item {
	public GasPart(Pos position) {
		super(new Image("file:resources/gasPart.png"), position);
	}
	public void tick() {} 
    public void ratCollision(Rat target) {
		//decrease hp by 25
		//ratmanager will check the hp of rats 
		//after every move and kill rats at 0 hp
		if (!(target.getRatType() == RatTypes.DEATH)) {
			//target.setHP(target.getHP() - 25);

			//we haven't done hp yet and I don't think it's worth it
			int rand = ThreadLocalRandom.current().nextInt(0, 100 + 1);
			if(rand < 50) {
				Game.RatManager.killSingleRat(target);
			}
		}	
	}
	public void onCollision(){};
	public void itemCollision(Item target) {}
	public void onPlacement() {}
}