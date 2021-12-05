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

    public Item(Image image, Pos pos) {
        super(image, EntityType.ITEM, pos);
    }

    public abstract void onPlacement();

    public abstract void tick();

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

    protected abstract void ratCollision(Rat target);

    public abstract void itemCollision(Item target);
}