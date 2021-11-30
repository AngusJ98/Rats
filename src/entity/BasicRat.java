package entity;

import java.util.ArrayList;

import gameHandler.Game;
import javafx.scene.image.Image;

public class BasicRat extends Rat {
	protected int hitPoints;
	private int timeToGrowth; 
	private int numChildren; //if this is above zero, implies the rat is pregnant
	public int getHP() {
		return hitPoints;
	}
	public void setHP(int hitPoints) {
		this.hitPoints = hitPoints;
	}
	private int getTimeToGrowth() {
		return timeToGrowth;
	}
	private void setTimeToGrowth(int timeToGrowth) {
		this.timeToGrowth = timeToGrowth;
	}
	private void setNumChildren(int numChildren) {
		this.numChildren = numChildren;
	}
	public int getNumChildren() {
		return numChildren;
	}
	public void checkCurrentTile() {
		ArrayList<Entity> entities = Game.TileManager.getEntities(pos);
		for (int i = 0; i<entities.size(); i++) {
			//check if rat/cast to rat
			if (entities.get(i).isRat()) {
				Rat rat = (Rat) entities.get(i);
				if (rat.getRatType().equals(ratTypes.DEATH)) {
					rat.checkCurrentTile(); //death rat eats this rat
				} else if (!rat.getRatType().equals(this.ratType) && !rat.getRatType().equals(ratTypes.BABY)) {
					//check if adult rat of opposing gender
					if (rat.getMateStatus() == true && this.getMateStatus() == true) {
						//if not pregnant/sterile/baby, reproduce
						BasicRat basicRat = (BasicRat) rat;
						if (this.ratType.equals(ratTypes.FEMALE)) {
							this.canMate = false;
							this.setNumChildren((int) (Math.random() * 10000)); 
						} else {
							basicRat.canMate = false;
							basicRat.setNumChildren((int) (Math.random() * 10000)); 
						}
					}
				}			
			} else {
				//assume item
				//code to activate item goes here
				//need all items to have a universal onCollision method 
				//-either an abstract method in entity or creating an abstract class under entity for items would work
			}
		}	
	}
	public BasicRat(ratTypes type, Image image, int[] pos) {
		super(type, image, pos);
		setHP(100);
		switch (type) {
		case BABY:
			int minGrowthTime = 1000;
			int growthMultiplier = 4000;
			timeToGrowth = (int) (Math.random() * growthMultiplier) + minGrowthTime; //ms to growth (min 1000, max 5000)
			setTimeToGrowth(timeToGrowth);
			break;
		case FEMALE:
			setNumChildren(0);
			break;
		default:
			break;
		}			
	}
}
