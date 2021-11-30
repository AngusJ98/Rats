package entity;

import javafx.scene.image.Image;
enum CollisionType {
    RAT, ITEM, TILE, NONE
}
public abstract class Entity {
    protected Image image;
    protected CollisionType collisionGroup; //what this entity is
    //protected pos;?




    public Entity(Image image, CollisionType collisionGroup) {
        this.image = image;
        this.collisionGroup = collisionGroup;
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
}