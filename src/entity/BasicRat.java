/**
 *
 <p> 1. File-name: Basic Rat.java</p>
 <p> 2. Creation Date: (N/A) </p>
 <p> 3. Last modification date:</p>
 <p> 4. Purpose of the program: Basic Rat Implementation</p>
 * @author Andrew
 */

package entity;

import java.util.ArrayList;

import gameHandler.Game;
import gameHandler.Pos;
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

    /**
     * 1st Constuctor, intializes a basic rat in the Rat Class with
     * it's type and position depending on the case.
     * <p> no side-effects</p>
     * <p> not referentially transparent</p>
     * @param type takes in the rat type of basic rat.
     * @param image takes in the image of the rat
     * @param pos takes coordinates of the position of the rat.
     */
    public BasicRat(RatTypes type, Image image, Pos pos) {
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

    /**
     * 2nd Constucter, initializes a basic rat in the Rat Class with
     * it's type, MateStatus, MoveStatus, MoveSpeed, Time to Grow,
     * No of Children and time to give birth.
     * <p> no side-effects</p>
     * <p> not referentially transparent</p>
     * @param type takes in the rat type of basic rat.
     * @param canMate takes in boolean for whether or not it can mate.
     * @param canMove takes in boolean for whether or not it can move.
     * @param moveSpeed takes it's movement speed.
     * @param timeToGrowth stores it's time of growth.
     * @param numChildren stores the number of Children this rat has.
     * @param timeToBirth stores the time to birth for the rat.
     * @param hp stores the rat's hp.
     * @param position stores the current position of the rat.
     * @param image stores the image of the rat.
     */
    public BasicRat(RatTypes type, boolean canMate, boolean canMove,
                    int moveSpeed, int timeToGrowth, int numChildren,
                    int timeToBirth, int hp, Pos position, Image image) {
        super(type, image, position);
        setMateStatus(canMate);
        setMoveStatus(canMove);
        setMoveSpeed(moveSpeed);
        setTimeToGrowth(timeToGrowth);
        setNumChildren(numChildren);
        setTimeToBirth(timeToBirth);
    }

    /**
     * Setter for Time to Growth
     * @param timeToGrowth
     */
	private void setTimeToGrowth(int timeToGrowth) {
		this.timeToGrowth = timeToGrowth;
	}

    /**
     * Setter for Num of Children
     * @param numChildren
     */
	private void setNumChildren(int numChildren) {
		this.numChildren = numChildren;
	}

    /**
     * Setter for Time To Birth
     * @param timeToBirth
     */
	private void setTimeToBirth(int timeToBirth){
        this.timeToBirth = timeToBirth;
    }

    /**
     * Setter for Gender
     * @param gender
     */
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

    /**
     * Setter for HP
     * @param hitPoints
     */
    public void setHP(int hitPoints) {
        this.hitPoints = hitPoints;
    }

    /**
     * Getter for Time to Growth
     * @return the time it takes for the rat to grow.
     */
    public int getTimeToGrowth() {
        return timeToGrowth;
    }

    /**
     * Getter for Num of Children
     * @return the number of children the female rat has.
     */
	public int getNumChildren() {
		return numChildren;
	}

    /**
     * Getter for Hit Points
     * @return the number of hit points the rats have.
     */
    public int getHP() {
        return hitPoints;
    }

    /**
     * Getter for Time To Birth
     * @return the time of birth
     */
    public int getTimeToBirth() {
        return timeToBirth;
    }

    /**
     * Checks the current tile if any other entities are in it,
     * then relevant actions are made if neccessary.
     */
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
				try {
				Item item = (Item) entities.get(i);
				item.activateItem();
				} catch (Exception e) {
					System.out.println("Rat tried to activate an item but could not cast to Item")
				}
			}
		}	
	}
}
