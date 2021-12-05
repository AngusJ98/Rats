package entity;

import gameHandler.Game;
import gameHandler.Pos;
import javafx.scene.image.Image;

import java.util.ArrayList;

/**
 * <p> 1. File-name: ExplosivePart.java</p>
 * <p> 2. Creation Date: 29/11/2021</p>
 * <p> 3. Last modification date: 5/12/2021</p>
 * <p> 4. Purpose of the program: Creates the explosion effect</p>
 * @author Andrew
 */
public class ExplosionPart extends Item {
    private Image image;
    private Direction direction;

    /**
     * Method used to track where the explosion is and where it is heading
     * <p> no side-effects</p>
     * <p> not referentially transparent</p>
     * @param position  the position of the current explosion part
     * @param direction the direction the explosion is travelling in
     */
    public ExplosionPart(Pos position, Direction direction) {
        super(new Image("file:resources/explosionPart.png"), position);
        this.direction = direction;

    }

    /**
     * ticks for the explosion part of the bomb
     * <p> has side-effects</p>
     * <p> referentially transparent</p>
     */
    public void tick() {
        //get the next tile in <direction> direction
        Pos newPos = Game.TileManager.getPosFromDir(direction, this.pos);
        //if passable move myself
        if (Game.TileManager.getPassableTile(newPos)) {
            this.setPosition(newPos);
            //check for entities at my new position
            ArrayList<Entity> entities = Game.TileManager.getEntities(newPos);
            entities.remove(this);
            if (entities.size() > 0) {
                for (int i = 0; i < entities.size(); i++) {
                    //call onCollision for each entity
                    //to decide what to do with it
                    super.onCollision(entities.get(i));
                }
            }
        } else {
            //if not passable kill me
            Game.ItemManager.killItem(this);
        }
    }

    // Could potentially use a damage/destroy method for entity
    // that's then used on both rats and items? I guess killing a rat and
    // destroying an item are pretty similar.

    // Rat deaths are handled by Ratmanager and Item deaths are handled by ItemManager,
    // so i would reccomend against that

    /**
     * collsion of explosion Part on rats
     * <p> no side-effects</p>
     * <p> not referentially transparent</p>
     * @param target the rat to be colliding with
     */
    public void ratCollision(Rat target) {
        Game.RatManager.killSingleRat(target);
    }

    /**
     * item colliding with explosion part
     * <p> side-effects</p>
     * <p> not referentially transparent</p>
     * @param target the item being collided with
     */
    public void itemCollision(Item target) {
        Game.ItemManager.killItem(target);
    }

    public void onPlacement() {
    }
}