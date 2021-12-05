package entity;

import gameHandler.Game;
import gameHandler.Pos;
import java.util.ArrayList;
import java.util.Random;
import java.util.concurrent.ThreadLocalRandom;

import javafx.scene.image.Image;

/**
 *
 <p> 1. File-name: Gas.java</p>
 <p> 2. Creation Date: (N/A) </p>
 <p> 3. Last modification date:</p>
 <p> 4. Purpose of the program: Gas item implementation</p>
 * @author Andrew
 */

public class Gas extends Item {
	int gasNum = 0;
	private final int RANGE = 2;
	private final int PLACE_INTERVAL = 10;
	private final int MAX_GAS = 8;
	ArrayList<Pos> unvisitedTiles = new ArrayList<Pos>();
	GasPart[] gasArray = new GasPart[MAX_GAS];

    public Gas(Pos position) {
        super(new Image("file:resources/gasCan.png"), position);
		//gas will have no sprite and be responsible for the spread of gasparts
		//gasparts will be visible and deal damage to rats
    }
	public void tick() { 
		//if there are less than <6> gas clouds, this method will attempt to place one each game tick
		if (gasNum < MAX_GAS && Game.getTimeLeft() % PLACE_INTERVAL == 0) {

			//simplified code by checking positions at start
			if (unvisitedTiles.size() > 0) {
				int randomPlace = ThreadLocalRandom.current().nextInt(0, unvisitedTiles.size());
				gasArray[gasNum] = new GasPart(unvisitedTiles.get(randomPlace));
				Game.ItemManager.addItem(gasArray[gasNum]);
				unvisitedTiles.remove(randomPlace);
				gasNum++;
			}
		} else if (gasNum >= MAX_GAS) {
			Game.ItemManager.killItem(this);
		}
		
	}
	@Override
    public void ratCollision(Rat target) {}
	public void itemCollision(Item target) {}
	public void onPlacement() {
		for (int x = -RANGE; x <= RANGE; x++) {
			for (int y = -RANGE; y <= RANGE; y++) {
				Pos maybe = new Pos(this.pos.x + x,this.pos.y + y);
				if (Game.TileManager.getTile(maybe) != null) {
					unvisitedTiles.add(maybe);
				}
			}
		}
	}
}
