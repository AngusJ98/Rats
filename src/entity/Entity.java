package entity;

import javafx.scene.image.Image;
enum CollisionType {
    RAT, ITEM, TILE, NONE
}
public abstract class Entity {
    protected Image image;
    protected CollisionType collisionGroup; //what this entity is
    protected int[] position;//protected pos;?




    public Entity(Image image, CollisionType collisionGroup, int[] position) {
        this.image = image;
        this.collisionGroup = collisionGroup;
        this.position = position;
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

    public void setPosition(int[] position) {
        this.position = position;
    }

    public int[] getPosition() {
        return position;
    };
}