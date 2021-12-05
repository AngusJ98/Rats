package entity;

import gameHandler.Game;
import gameHandler.Pos;
import javafx.scene.image.Image;

import java.util.Random;
import java.util.concurrent.ThreadLocalRandom;

/**
 * <p> 1. File-name: GasPart.java</p>
 * <p> 2. Creation Date: (N/A) </p>
 * <p> 3. Last modification date:</p>
 * <p> 4. Purpose of the program: Used in unison with Gas to create the smoke </p>
 *
 * @author
 */

public class GasPart extends Item {
    private final int LIFETIME = 50; //measured in ticks
    private int currentExist = 0;

    /**
     * Constructor used to create a Gas image on a specific position
     *
     * @param position the position of the gas being created
     */
    public GasPart(Pos position) {
        super(new Image("file:resources/gas.png"), position);
    }

    /**
     * Removes gas from the board after a certain duration
     * first to be placed is the first to be removed
     */
    public void tick() {
        if (this.currentExist > LIFETIME) {
            Game.ItemManager.killItem(this);
        }
        currentExist++;
    }

    public void ratCollision(Rat target) {
        //decrease hp by 25
        //ratmanager will check the hp of rats
        //after every move and kill rats at 0 hp
        //now with 10 ticks, reducing by 5 instead
        if (!(target.getRatType() == RatTypes.DEATH)) { //death rats wear gas masks
            BasicRat targ = (BasicRat) target;
            targ.setHP(targ.getHP() - 25);
            System.out.println(targ.getHP());
        }
    }

    public void itemCollision(Item target) {
    }

    public void onPlacement() {
    }
}