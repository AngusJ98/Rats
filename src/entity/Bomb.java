package entity;

import gameHandler.Game;
import gameHandler.Pos;
import javafx.scene.image.Image;

public class Bomb extends Item{
    public static final int DEFAULT_BOMB_TIME = 4000;
    public static final int TICKS_PER_SECOND = 1000;
    private int range;
    private int timer;

    public Bomb(Pos position) {
        super(new Image(""), EntityType.ITEM, position);
        timer = DEFAULT_BOMB_TIME;
    }

    public Bomb(Pos position, int timeLeft) {
        super(new Image(""), position);
        timer = timeLeft;
    }



	//=========================================IMPORTANT========================================
	// bomb shouldn't have the ability to kill, but it will spawn bombparts that kill on contact
    // bomb itsself is essentially just a glorified countdown timer
	// all it needs is the tick() and boom() methods
    //==========================================================================================	



    // if anyone renames this method to something more sensible
    // i will personally feel very offended
    private void boom() {
        // create 4 explosionparts, 1 for each direction 
		// remove self from board
    }

    public void tick() {
        timer--;
        boom();
    }

    //Each bomb should display a count down: 4, 3, 2, 1.
    public int getSeconds() {
        // god damn i hate java so much look at this shit
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