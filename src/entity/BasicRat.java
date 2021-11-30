package entity;

import java.util.ArrayList;

import gameHandler.Game;
import javafx.scene.image.Image;

public class BasicRat extends Rat {
    private static final int MIN_GROWTH_TIME = 1000;
    private static final int GROWTH_MULTIPLIER = 4000;
    private static final int ADULT_MAX_HP = 100;
    private static final int BABY_MAX_HP = 50;
	protected int hitPoints;
	private int timeToGrowth; 
	private int numChildren; //if this is above zero, implies the rat is pregnant
    private int timeToBirth;
    public BasicRat(RatTypes type, Image image, int[] pos) {
        super(type, image, pos);
        setHP(ADULT_MAX_HP);
        setTimeToBirth(0);
        switch (type) {
            case BABY:
                timeToGrowth = (int) (Math.random() * GROWTH_MULTIPLIER) + MIN_GROWTH_TIME; //ms to growth (min 1000, max 5000)
                setTimeToGrowth(timeToGrowth);
                setHP(BABY_MAX_HP);
                break;
            case FEMALE:
                setNumChildren(0);
                break;
            default:
                break;
        }
    }

    @Override
    public void kill() {

    }

    public BasicRat(RatTypes type, boolean canMate, boolean canMove,
                    int moveSpeed, int timeToGrowth, int numChildren,
                    int timeToBirth, int hp, int[] position, Image image) {
        super(type, image, position);
        setMateStatus(canMate);
        setMoveStatus(canMove);
        setMoveSpeed(moveSpeed);
        setTimeToGrowth(timeToGrowth);
        setNumChildren(numChildren);
        setTimeToBirth(timeToBirth);
    }

	private void setTimeToGrowth(int timeToGrowth) {
		this.timeToGrowth = timeToGrowth;
	}
	private void setNumChildren(int numChildren) {
		this.numChildren = numChildren;
	}
	private void setTimeToBirth(int timeToBirth){
        this.timeToBirth = timeToBirth;
    }

    public void setGender(RatTypes gender) {
        // Changing to male won't affect existing males because they won't have
        // babies anyway. Changing to female doesn't alter babies or time to
        // birth (males won't be pregnant when they get changed to female)
        if (gender == RatTypes.MALE) {
            setNumChildren(0);
            setTimeToBirth(0);
            setRatType(gender);
        } else {
            setRatType(gender);
        }
    }

    public void setHP(int hitPoints) {
        this.hitPoints = hitPoints;
    }

    public int getTimeToGrowth() {
        return timeToGrowth;
    }

	public int getNumChildren() {
		return numChildren;
	}

    public int getHP() {
        return hitPoints;
    }

    public int getTimeToBirth() {
        return timeToBirth;
    }

	public void checkCurrentTile() {
		ArrayList<Entity> entities = Game.TileManager.getEntities(pos);
		for (int i = 0; i<entities.size(); i++) {
			//check if rat/cast to rat
			if (entities.get(i).isRat()) {
				Rat rat = (Rat) entities.get(i);
				if (rat.getRatType().equals(RatTypes.DEATH)) {
					rat.checkCurrentTile(); //death rat eats this rat
				} else if (!rat.getRatType().equals(this.ratType) && !rat.getRatType().equals(RatTypes.BABY)) {
					//check if adult rat of opposing gender
					if (rat.getMateStatus() && this.getMateStatus()) {
						//if not pregnant/sterile/baby, reproduce
						BasicRat basicRat = (BasicRat) rat;
						if (this.ratType.equals(RatTypes.FEMALE)) {
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

    @Override
    public void onCollision(Entity t) {

    }
}
