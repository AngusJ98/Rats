package entity;

import gameHandler.Game;
import gameHandler.Pos;
import javafx.scene.image.Image;

/**
 * <p> 1. File-name: Poison.java</p>
 * <p> 2. Creation Date: 1/12/2021 </p>
 * <p> 3. Last modification date: 5/12/2021</p>
 * <p> 4. Purpose of the program: Poison Implementation</p>
 *
 * @author Andrew, Ethan
 */

public class Poison extends Item {
    private static final int DEFAULT_TIME = 4000;
    private static final int RANGE = 1;
    private int timer;

    /**
     * 1st Constructor, intializes the Poison
     *
     * @param position coordinates of the rat
     */
    public Poison(Pos position) {
        super(ItemType.Poison ,new Image("file:resources/poison.png"), position);
    }

    /**
     * For when poison collides with rats
     * rats are killed and item is removed as well
     * <p> side-effects</p>
     * <p> not referentially transparent</p>
     *
     * @param target the rat to be targetted
     */
    @Override
    protected void ratCollision(Rat target) {
        Game.RatManager.killSingleRat(target);
        Game.ItemManager.killItem(this);
    }

    /**
     * No Implementation needed for
     * itemCollision, onPlacement, tick
     */
    public void itemCollision(Item target) {
    }

    public void onPlacement() {
    }

    public void tick() {
    }
}


//What is mentioned in the Spec for entity.Poison:
//Once placed, poison will remain on the path until a rat
//runs into it. Once a rat runs into it the rat is killed and
//the poison is used up and removed from the path.