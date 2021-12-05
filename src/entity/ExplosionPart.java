package entity;

import gameHandler.Game;
import gameHandler.Pos;
import javafx.scene.image.Image;

import java.util.ArrayList;

public class ExplosionPart extends Item {
    private Image image;
	private Direction direction;
    public ExplosionPart(Pos position, Direction direction) {
        super(new Image("file:resources/explosionPart.png"), position);
        this.direction = direction;

    }
	public void tick() {
		//get the next tile in <direction> direction
		Pos newPos = Game.TileManager.getPosFromDir(direction, this.pos);
		//if passable move myself
		if (Game.TileManager.getPassableTile(newPos)) {
			this.setPosition(newPos);
			//check for entities at my new position
			ArrayList<Entity> entities = Game.TileManager.getEntities(newPos);
			if (!(entities == null) && entities.size() > 0) {
				for (int i = 0; i < entities.size(); i++) {
					//call onCollision for each entity 
					//to decide what to do with it
					super.onCollision(entities.get(i));
				}
			}
		} else {
			//if not passable kill me
			Game.ItemManager.killItem(this);
		}		
	}	
	
	// Could potentially use a damage/destroy method for entity
    // that's then used on both rats and items? I guess killing a rat and
    // destroying an item are pretty similar.
		
	// Rat deaths are handled by Ratmanager and Item deaths are handled by ItemManager,
	// so i would reccomend against that
    public void ratCollision(Rat target) {       
        Game.RatManager.killSingleRat(target);
    }
    public void itemCollision(Item target) {
        Game.ItemManager.killItem(target);
    }
	public void onPlacement() {}	
}