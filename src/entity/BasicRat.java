package entity;

import java.util.ArrayList;
import java.util.concurrent.ThreadLocalRandom;

import gameHandler.Game;
import gameHandler.Pos;
import javafx.scene.image.Image;

/**
 *
 <p> 1. File-name: Basic Rat.java</p>
 <p> 2. Creation Date: (N/A) </p>
 <p> 3. Last modification date:</p>
 <p> 4. Purpose of the program: Basic Rat Implementation</p>
 * @author Andrew
 */

public class BasicRat extends Rat {
    private static final int MIN_GROWTH_TIME = 100;
    private static final int MAX_GROWTH_TIME = 200;
    private static final int ADULT_MAX_HP = 100;
    private static final int BABY_MAX_HP = 50;
    private static final int PREGNANCY_COOLDOWN = 100;
    private static final int MIN_CHILD = 3;
    private static final int MAX_CHILD = 4;
    private static final int BIRTH_TIMER = 100;
    private static final int SCORE_BASE = 10;
    private static final int DEFAULT_SPEED = 2;
	protected int hitPoints;
	private int timeToGrowth; 
	private int numChildren; //if this is above zero, implies the rat is pregnant
    private int timeToBirth;
    private boolean sterile = false;

    /**
     * 1st Constuctor, intializes a basic rat in the Rat Class with
     * it's type and position depending on the case.
     * <p> no side-effects</p>
     * <p> not referentially transparent</p>
     * @param type takes in the rat type of basic rat.
     * @param image takes in the image of the rat
     * @param pos takes coordinates of the position of the rat.
     */
    public BasicRat(RatTypes type,  Pos pos) {
        super(type, pos);
        setHP(ADULT_MAX_HP);
        setTimeToBirth(0);
        switch (type) {
            case BABY:
                timeToGrowth = ThreadLocalRandom.current().nextInt(MIN_GROWTH_TIME, MAX_GROWTH_TIME); //ms to growth (min 1000, max 5000)
                setTimeToGrowth(timeToGrowth);
                setHP(BABY_MAX_HP);
                break;
            case FEMALE:
                setNumChildren(0);
                break;
            case MALE:
                break;
            default:
                break;
        }
    }

    public BasicRat(RatTypes type, Pos pos, int hitPoints, int timeToGrowth, int numChildren,int moveSpeed,
                    int timeToBirth, boolean canMate, boolean canMove) {
        super(type, pos);
        this.hitPoints = hitPoints;
        this.canMate = canMate;
        this.canMove = canMove;
        this.moveSpeed = moveSpeed;
        switch (type) {
            case BABY:
                setTimeToGrowth(timeToGrowth);
                break;
            case FEMALE:
                setNumChildren(numChildren);
                setTimeToBirth(timeToBirth);
                break;
            case MALE:
                break;
            default:
                break;
        }
    }

    public void updateImage() {
        switch (this.getRatType()) {
            case BABY:
                this.image = new Image("file:resources/babyRat.png");
                break;
            case FEMALE:
                this.image = new Image("file:resources/femaleRat.png");
                break;
            case MALE:
                this.image = new Image("file:resources/maleRat.png");
                break;
        }
    }

    public boolean isSterile() {
        return sterile;
    }

    public void setSterile(boolean sterile) {
        this.sterile = sterile;
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
     * gets score
     * @return score
     */
    public int getScore() {
        return SCORE_BASE + 10 * this.getNumChildren();
    }

    public void ratActions() {

        switch (ratType) {
            case BABY:
                babyRatActions();
                break;
            case FEMALE:
                femaleRatActions();
                break;
        }

    }

    private void babyRatActions() {
        if (this.timeToGrowth <= 0) {
            this.setMoveSpeed(DEFAULT_SPEED);
            if (Math.random() < 0.5) {
                this.setGender(RatTypes.FEMALE);
            } else {
                this.setGender(RatTypes.MALE);
            }
        }
        timeToGrowth--;
    }

    private void femaleRatActions() {
        if (this.timeToBirth < -PREGNANCY_COOLDOWN && !this.sterile) {
            this.canMate = true;
        }
        if (this.timeToBirth < 0 && numChildren > 0) {
            for (int i = 0; i < numChildren; i++) {
                Game.RatManager.addRat(new BasicRat(RatTypes.BABY, this.pos));
            }
            this.numChildren = 0;
        }
        timeToBirth--;

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
        entities.remove(this);
		for (Entity entity : entities) {
			//check if rat/cast to rat
			if (entity.isRat()) {
				Rat rat = (Rat) entity;
				if (rat.getRatType().equals(RatTypes.DEATH)) {
					rat.checkCurrentTile(); //death rat eats this rat
				} else if (!rat.getRatType().equals(this.ratType) && !rat.getRatType().equals(RatTypes.BABY)) {
					//check if adult rat of opposing gender
					if (rat.getMateStatus() && this.getMateStatus()) {
						//if not pregnant/sterile/baby, reproduce
						BasicRat basicRat = (BasicRat) rat;
						numChildren = ThreadLocalRandom.current().nextInt(MIN_CHILD, MAX_CHILD+1);
						if (this.ratType.equals(RatTypes.FEMALE)) {
							this.canMate = false;
							this.setNumChildren(numChildren);
							this.setTimeToBirth(BIRTH_TIMER);
						} else {
							basicRat.canMate = false;
							basicRat.setNumChildren(numChildren);
                            basicRat.setTimeToBirth(BIRTH_TIMER);
						}
					}
				}			
			} else {
				try {

                    Item item = (Item) entity;
                    item.onCollision(this);
				} catch (Exception e) {
					System.out.println("Rat tried to activate an item but could not cast to Item");
				}
			}
		}	
	}
}
