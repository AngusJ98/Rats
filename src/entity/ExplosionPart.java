package entity;

import gameHandler.Game;
import gameHandler.Pos;
import javafx.scene.image.Image;
public class ExplosionPart extends Item {
    private Image image;
	private Direction direction;
    public ExplosionPart(Pos position, Direction direction) {
        super(new Image("file:resources/explosionPart.png"), position);
        this.direction = direction;

    }
	public void tick() {
		//bunch of switch cases to move the explosionpart in it's direction 
		//get entities from tilemanager
        //call super.oncollision(entities)		
	}	
    public void ratCollision(Rat target) {
        // Could potentially use a damage/destroy method for entity
        // that's then used on both rats and items? I guess killing a rat and
        // destroying an item are pretty similar.
        Game.RatManager.killSingleRat(target);
    }

    public void itemCollision(Item target) {
        // Guessing we'll need a damage/destroy method in entity.Entity maybe?
    }
	public void onPlacement() {}	
}