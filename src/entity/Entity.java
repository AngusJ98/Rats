package entity;

import gameHandler.Pos;
import javafx.scene.image.Image;
enum CollisionType { //remove at some point pls
    RAT, ITEM, TILE, NONE
}
//poggers
public abstract class Entity {
    protected Image image;
    protected CollisionType collisionGroup; //what this entity is
    protected Pos pos;//protected pos;?




    public Entity(Image image, CollisionType collisionGroup, Pos position) {
        this.image = image;
        this.collisionGroup = collisionGroup;
        this.pos = position;
    }

    public boolean isRat() {
        return collisionGroup == CollisionType.RAT;
    }
    public Image getImage() {
        return image;
    }
    public CollisionType getCollisionGroup() {
        return collisionGroup;
    }

    public void draw() {
        //draw the entity
        //or don't idm
    }


    public abstract void onCollision(Entity t);

    public void setPosition(Pos position) {
        this.pos = position;
    }

    public Pos getPosition() {
        return pos;
    };
}