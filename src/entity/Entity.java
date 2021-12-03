package entity;

import gameHandler.Pos;
import javafx.scene.image.Image;
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
        return EntityType == entityType.RAT;
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