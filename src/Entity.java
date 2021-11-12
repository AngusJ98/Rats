package application;

public abstract class Entity {
    protected Image image;
    //protected pos;?

    public Entity(Image image) {
        this.image = image;
    }

    public draw() {
        //draw the entity
        //or don't idm
    }
}
