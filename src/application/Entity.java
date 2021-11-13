package application;

import java.util.ArrayList;

import application.Game.RatManager;
import javafx.scene.image.Image;

public abstract class Entity {
    protected Image image;
    protected char type;
    public Entity(Image image) {
        this.image = image;
        
    }
    public void draw() {
    	//graphics go brr
    }
   
    public ArrayList<BasicRat> checkRatCollision(int[] pos) {
    	return (RatManager.getRatsAtPos(pos));    	
    }
}
