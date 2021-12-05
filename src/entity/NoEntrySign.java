package entity;

import gameHandler.Game;
import gameHandler.Pos;
import javafx.scene.image.Image;

/**
 *
 <p> 1. File-name: NoEntrySign.java</p>
 <p> 2. Creation Date: (N/A) </p>
 <p> 3. Last modification date:</p>
 <p> 4. Purpose of the program: No entry sign implementation</p>
 * @author
 */

public class NoEntrySign extends Item {
    public static final int MAX_HP = 4;
    private static Image noEntImg = new Image("file:resources/noEnt.png");
    private int hp;

	/**
	 * 1st Constructor.
	 * <p> side-effects</p>
	 * <p> not referentially transparent</p>
	 * @param pos takes coordinates of NoEntrySign to be placed.
	 */

    public NoEntrySign(Pos position) {
        super(noEntImg , position);
        hp = MAX_HP;
    }

	/**
	 * 2st Constructor for save file.
	 * <p> side-effects</p>
	 * <p> not referentially transparent</p>
	 * @param pos takes coordinates of NoEntrySign to be placed.
	 * @param takes in the current hp of a NoEntrySign
	 */

	public NoEntrySign(Pos position, int hp) {
		super(noEntImg , position);
		this.hp = hp;
	}

	/**
	 *  Check if rat has touched NoEntrySign
	 *  <p> no side-effects</p>
	 * 	<p> not referentially transparent</p>
	 * @param takes a spesific rat in to change its direction.
	 */

    public void ratCollision(Rat target) { 	
		//invert the direction the rat is moving		
		Direction newDirection = target.getInverseMoveDirection();
		target.forceMove(1, newDirection);
		//lower my hp
		this.hp--;
		if (this.hp <= 0) {
			Game.ItemManager.killItem(this);
		}  
	}
	/**
	 *  Check if NoEntrySign has touched rat
	 * @param takes a NoEntrySign in to check if it there.
	 */
	public void itemCollision(Item target) {}
	public void onPlacement() {}	
	public void tick() {}
}
