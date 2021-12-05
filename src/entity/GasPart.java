package entity;

import gameHandler.Game;
import gameHandler.Pos;
import javafx.scene.image.Image;

import java.util.Random;
import java.util.concurrent.ThreadLocalRandom;

public class GasPart extends Item {
	public GasPart(Pos position) {
		super(new Image("file:resources/gas.png"), position);
	}
	public void tick() {}
    public void ratCollision(Rat target) {
		//decrease hp by 25
		//ratmanager will check the hp of rats 
		//after every move and kill rats at 0 hp
		//now with 10 ticks, reducing by 5 instead
		if (!(target.getRatType() == RatTypes.DEATH)) { //death rats wear gas masks
			BasicRat targ = (BasicRat)target;
			targ.setHP(targ.getHP() - 5);
			System.out.println(targ.getHP());
		}	
	}
	public void itemCollision(Item target) {}
	public void onPlacement() {}
}