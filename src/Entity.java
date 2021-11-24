package application;

import javafx.scene.image.Image;

public abstract class Entity {
    protected Image image;
    protected char type;
    protected boolean isRat;
    public boolean getIsRat() {
    	return isRat;
    }
    protected void setIsRat(boolean isRat) {
    	this.isRat = isRat;
    }
    public Entity(Image image) {
        this.image = image;       
    }
    public void draw() {
    	//graphics go brr
    }
}
