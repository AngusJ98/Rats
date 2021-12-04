package entity;

import gameHandler.Game;
import gameHandler.Pos;
import javafx.scene.image.Image;

/**
 *
 <p> 1. File-name: Basic Rat.java</p>
 <p> 2. Creation Date: (N/A) </p>
 <p> 3. Last modification date: </p>
 <p> 4. Purpose of the program: Basic Rat Implementation</p>
 * @author ??
 */

public class Bomb extends Item{
	//=========================================IMPORTANT========================================
	// bomb shouldn't have the ability to kill, but it will spawn bombparts that kill on contact
    // bomb itsself is essentially just a glorified countdown timer
	// all it needs is the tick() and boom() methods
    //==========================================================================================	
    public static final int DEFAULT_BOMB_TIME = 4000;
    public static final int TICKS_PER_SECOND = 1000; 
	//1000 tps is very, very fast. For reference, minecraft uses 20 tps and 
	//i expect a tick rate of 1-5 ticks per second would suit the rat game better
	//unless you meant milliseconds per tick?
    private int range;
    private int timer;

    /**
     * 1st Constructor, initializes Bomb via it's
     * Image and Coordinates, and sets a DEFAULT timer
     * for when it explodes
     * @param position takes in the coordinates of the Bomb
     */
    public Bomb(Pos position) {
        super(new Image("file:resources/bomb.png"), position);
        timer = DEFAULT_BOMB_TIME;
    }

    /**
     * 2nd Constructor, intializes Bomb via it's Image and Coordinates,
     * and can set a specific custom timer that differs from the Default.
     * @param position takes in the coordinates of the Bomb
     * @param timeLeft takes in the time left for the bomb to explode
     */
    public Bomb(Pos position, int timeLeft) {
        super(new Image(""), position);
        timer = timeLeft;
    }



    // if anyone renames this method to something more sensible
    // i will personally feel very offended
    private void boom() {
        
    }

    public void tick() {
        timer--;
		if (timer <= 0) { //idk how you're envisioning this working but without this if statement the bomb detonates instantly
			boom();
		}		
    }

    //Each bomb should display a count down: 4, 3, 2, 1.
	// god damn i hate java so much look at this shit
    public int getSeconds() {      
        return (int) Math.ceil((float) timer / TICKS_PER_SECOND);
    }

	// shouldn't do anything
    @Override
    public void ratCollision(Rat target) {}
	public void itemCollision(Item target) {}
	public void onPlacement() {}	
}

//From the Spec:
//Once placed, a bomb will count down from 4 in 1 second increments.
//Once the bomb reaches zero a powerful explosion will extend vertically and horizontally
//(not diagonally) until it reaches grass in all directions (the
//explosion passes through tunnels). The grass prevents
//the explosion from continuing in a given direction. The
//explosion will kill any rats on these tiles and will also
//destroy any other items currently placed on these tiles. <<<<<<<<<<------------------------------------
//Each bomb should display a count down: 4, 3, 2, 1.