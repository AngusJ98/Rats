package entity;

import gameHandler.*;

import javafx.scene.image.Image;

import java.util.ArrayList;

/**
 * <p> 1. File-name: Sterilization.java</p>
 * <p> 2. Creation Date: 1/12/2021 </p>
 * <p> 3. Last modification date: 5/12/2021</p>
 * <p> 4. Purpose of the program: Sterilization implementation</p>
 *
 * @author Andrew, Ethan
 */

public class Sterilization extends Item {
    private static final int DEFAULT_TIME = 50; // idk how long it's meant to stay for. 5 seconds?
    private static final int RANGE = 2; //idk what range should be either
    private int timer;

    /**
     * 1st Constructor.
     * <p> side-effects</p>
     * <p> not referentially transparent</p>
     *
     * @param pos takes coordinates of Sterilization to be placed.
     */

    public Sterilization(Pos position) {
        super(ItemType.STERILIZATION, new Image("file:resources/sterile.png"), position);
        timer = DEFAULT_TIME;
    }

    /**
     * 2nd Constructor for save file.
     * <p> side-effects</p>
     * <p> not referentially transparent</p>
     *
     * @param pos   takes coordinates of Sterilization to be placed.
     * @param takes in the current time left of a Sterilization
     */

    public Sterilization(Pos position, int timeLeft) {
        super(ItemType.STERILIZATION, new Image("file:resources/sterile.png"), position);
        timer = timeLeft;
    }

    public void onPlacement() {
    }

    public void ratCollision(Rat target) {
    }

    public void itemCollision(Item target) {
    }

    /**
     * Sterlizes the rats
     */
    public void tick() {
        for (int yoffset = -RANGE; yoffset < RANGE; yoffset++) {
            for (int xoffset = -RANGE; xoffset < RANGE; xoffset++) {
                ArrayList<BasicRat> rats = Game.RatManager.getRatsAtPos(new Pos(this.pos.x + xoffset, this.pos.y + yoffset));
                if (!(rats == null) && rats.size() > 0) {
                    for (BasicRat rat : rats) {
                        rat.setMateStatus(false);
                        rat.setSterile(true);
                    }
                }
            }
        }

        if (timer <= 0) {
            Game.ItemManager.killItem(this);
        }
        timer--;
    }
}

