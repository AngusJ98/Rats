package entity;

import gameHandler.Pos;
import javafx.scene.image.Image;

/**
 *
 <p> 1. File-name: Entity.java</p>
 <p> 2. Creation Date: 29/11/2021 </p>
 <p> 3. Last modification date: 5/12/2021</p>
 <p> 4. Purpose of the program: To implement each item to the board</p>
 * @author Andrew
 */

enum EntityType { //nvm just needed refactoring
    RAT, ITEM
}

public abstract class Entity {
    protected Image image;
    protected EntityType entityType; //what this entity is
    protected Pos pos;//protected pos;?

    /**
     * 1st constructor, initializes an entity
     * @param image of entity
     * @param entityType of entity
     * @param position of entity
     */
    public Entity(Image image, EntityType entityType, Pos position) {
        this.image = image;
        this.entityType = entityType;
        this.pos = position;
    }

    /**
     * checks is entity is a rat
     * @return true if a rat, false otherwise
     */
    public boolean isRat() {
        return this.getEntityType() == EntityType.RAT;
    }

    /**
     * gets image
     * @return image
     */
    public Image getImage() {
        return image;
    }

    /**
     *
     * @return
     */
    public EntityType getEntityType() {
        return entityType;
    }

    public void draw() {
        //draw the entity
        //or don't idm
    }

    public void setPosition(Pos position) {
        this.pos = position;
    }

    public Pos getPosition() {
        return pos;
    };
}