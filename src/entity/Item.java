package entity;

import gameHandler.Game;
import gameHandler.Pos;
import javafx.scene.image.Image;

/**
 * <p> 1. File-name: Item.java</p>
 * <p> 2. Creation Date: 1/12/2021</p>
 * <p> 3. Last modification date: 5/12/2021</p>
 * <p> 4. Purpose of the program: Abstract class for the items implementation</p>
 *
 * @author Andrew
 */
public abstract class Item extends Entity {
    private ItemType type;

    /**
     * Constructor, initializes Item via it's
     * Image and Coordinates, and type
     *
     * @param type  takes type of item
     * @param image takes the image form files
     * @param pos   takes the position
     */

    public Item(ItemType type, Image image, Pos pos) {
        super(image, EntityType.ITEM, pos);
        this.type = type;
    }

    /**
     * Method to get the type
     *
     * @return type
     */

    public ItemType getType() {
        return type;
    }

    /**
     * Method to set the type
     *
     * @param type the type of rat
     */

    public void setType(ItemType type) {
        this.type = type;
    }

    public abstract void onPlacement();

    public abstract void tick();

    /**
     * Method to check if an item collides with an entity
     * or an entity collides with a item
     * or an item collides with a rat
     *
     * @param target: check if hit or not
     */

    public void onCollision(Entity target) {
        if (!(target == null)) {
            switch (target.getEntityType()) {
                case RAT:
                    ratCollision((Rat) target);
                    break;
                case ITEM:
                    itemCollision((Item) target);
                    break;
            }
        }
    }

    /**
     * Method for rat collition
     *
     * @param target: rat
     */

    protected abstract void ratCollision(Rat target);

    /**
     * Method for item collition
     *
     * @param target: item
     */

    public abstract void itemCollision(Item target);
}