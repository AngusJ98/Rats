package entity;

import gameHandler.Game;
import gameHandler.Pos;
import javafx.scene.image.Image;

import static entity.Direction.*;

/**
 * <p> 1. File-name: Bomb.java</p>
 * <p> 2. Creation Date: 28/11/2021</p>
 * <p> 3. Last modification date: 5/12/2021</p>
 * <p> 4. Purpose of the program: Bomb Implementation</p>
 *
 * @author Andrew, Ethan
 */

public class Bomb extends Item {
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
     *
     * @param position takes in the coordinates of the Bomb
     */
    public Bomb(Pos position) {
        super(ItemType.BOMB, new Image("file:resources/bomb.png"), position);
        timer = DEFAULT_BOMB_TIME;
    }

    /**
     * 2nd Constructor, Svaes Bomb via it's
     * Image and Coordinates, and sets a DEFAULT timer
     * for when it explodes
     *
     * @param position takes in the coordinates of the Bomb
     * @param timeLeft holds the time left
     */

    public Bomb(Pos position, int timeLeft) {
        super(ItemType.BOMB, new Image("file:resources/bomb.png"), position);
        timer = timeLeft;
    }


    // if anyone renames this method to something more sensible
    // i will personally feel very offended

    /**
     * Method for the explosion of the Bomb
     * <p> side-effects</p>
     * <p> not referentially transparent</p>
     */
    private void boom() {
        Game.ItemManager.addItem(new ExplosionPart(this.pos, NORTH));
        Game.ItemManager.addItem(new ExplosionPart(this.pos, EAST));
        Game.ItemManager.addItem(new ExplosionPart(this.pos, SOUTH));
        Game.ItemManager.addItem(new ExplosionPart(this.pos, WEST));
        Game.ItemManager.killItem(this);
    }

    /**
     * Method for the ticking of the bomb
     * Stars decremental timer,
     * if it reaches zero,
     * the bomb explodes.
     * <p> no side-effects</p>
     * <p> not referentially transparent</p>
     */
    public void tick() {
        timer -= 100;
        if (timer <= 0) {
            boom();
        }
        updateImage();
    }

    /**
     * Method for updating the image of the file throughout the bomb animation
     * <p> side-effects</p>
     * <p> not referentially transparent</p>
     */
    private void updateImage() {
        int secsLeft = 1 + timer / 1000;
        this.image = new Image("file:resources/bomb" + secsLeft + ".png");
    }

    //Each bomb should display a count down: 4, 3, 2, 1.
    // god damn i hate java so much look at this shit

    /**
     * Gets how many seconds are left
     * <p> no side-effects</p>
     * <p> not referentially transparent</p>
     *
     * @return the number of seconds remaining
     */
    public int getSeconds() {
        return (int) Math.ceil((float) timer / TICKS_PER_SECOND);
    }

    @Override
    public void ratCollision(Rat target) {
    }

    public void itemCollision(Item target) {
    }

    public void onPlacement() {
    }
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