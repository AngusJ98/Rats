package entity;

import gameHandler.Game;
import gameHandler.Pos;
import javafx.scene.image.Image;

/**
 * <p> 1. File-name: FemaleGenderChange.java</p>
 * <p> 2. Creation Date: (N/A) </p>
 * <p> 3. Last modification date:</p>
 * <p> 4. Purpose of the program: Female fender change implementation</p>
 *
 * @author
 */

public class FemaleGenderChange extends Item {
    private static final int DEFAULT_TIME = 4000;
    private static final int RANGE = 1;
    private int timer;

    /**
     * 1st Constructor.
     * <p> side-effects</p>
     * <p> not referentially transparent</p>
     *
     * @param pos takes coordinates of male gender change to be placed.
     */

    public FemaleGenderChange(Pos position) {
        super(new Image("File:resources/female.png"), position);
        timer = DEFAULT_TIME;
    }

    /**
     * Check if rat has touched change gender item
     * <p> no side-effects</p>
     * <p> not referentially transparent</p>
     *
     * @param takes the spesific rat in to change its gender
     *              if all conditions are met.
     */

    public void ratCollision(Rat target) {
        if (target.getRatType() != RatTypes.DEATH && target.getRatType() != RatTypes.BABY) {
            BasicRat targ = (BasicRat) target;
            targ.setGender(RatTypes.FEMALE);
        }
        Game.ItemManager.killItem(this);
    }

    /**
     * Check if change gender item has touched a rat
     *
     * @param takes the change gender item in to collide with rat
     */

    public void itemCollision(Item target) {
    }

    public void onPlacement() {
    }

    public void tick() {
        //decrease a variable somewhere so this item is removed after a certain time idk
    }
}
