package entity;

import gameHandler.Pos;
import javafx.scene.image.Image;

/**
 *
 <p> 1. File-name: Entity.java</p>
 <p> 2. Creation Date: (N/A) </p>
 <p> 3. Last modification date:</p>
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

    public Entity(Image image, EntityType entityType, Pos position) {
        this.image = image;
        this.entityType = entityType;
        this.pos = position;
    }

    public boolean isRat() {
        return this.getEntityType() == EntityType.RAT;
    }
    public Image getImage() {
        return image;
    }
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