package entity;

import gameHandler.Game;
import gameHandler.Pos;
import java.util.ArrayList;
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
	ArrayList<Pos> unvisitedTiles = new ArrayList<Pos>();
	GasPart[] gasArray = new GasPart[6];

    public Gas(Pos position) {
        super(new Image("file:resources/gasCan.png"), position);
		//gas will have no sprite and be responsible for the spread of gasparts
		//gasparts will be visible and deal damage to rats
    }
	public void tick() { 
		//if there are less than <6> gas clouds, this method will attempt to place one each game tick
		if (gasNum < 6) {
			//if the list is empty, either the algorithm hasn't run or there
			//are less than 6 tiles available. If there are less than 6 tiles available 
			//(which should never happen - who would design a map where one gas is enough to win?)
			//,this will just place multiple gasparts in one location, and will not break
			if (unvisitedTiles.size() == 0) {
				//add my location to a list of possible locations				
				unvisitedTiles.add(pos);
			}	
			//create a gasPart at the location in the first index of the locations array 
			gasArray[gasNum] = new GasPart(unvisitedTiles.get(0));
			Game.ItemManager.addItem(gasArray[gasNum]);
			//search nearby tiles (in a + shape) for tiles that are passable and add them to the list of possible locations
			//tiles must not be outside the board or be already in the list as that could lead to recursion 
			//and multiple gasparts being created in the same location
			if ((pos.y-1 >= 0) && Game.TileManager.getPassableTile(new Pos(pos.x, pos.y-1))) {
				if (!unvisitedTiles.contains(new Pos(pos.x, pos.y-1))) {
					unvisitedTiles.add(new Pos(pos.x, pos.y-1));
				}
			}
			if (pos.y+1 <= Game.TileManager.getNumTileHeight() && Game.TileManager.getPassableTile(new Pos(pos.x, pos.y+1))) {
				if (!unvisitedTiles.contains(new Pos (pos.x, pos.y+1))) {
					unvisitedTiles.add(new Pos(pos.x, pos.y+1));
				}
			}
			if ((pos.x-1 >= 0) && Game.TileManager.getPassableTile(new Pos(pos.x-1, pos.y))) {
				if (!unvisitedTiles.contains(new Pos(pos.x-1, pos.y))) {
					unvisitedTiles.add(new Pos(pos.x-1, pos.y));
				}
			}
			if (pos.x+1 <= Game.TileManager.getNumTileWidth() && Game.TileManager.getPassableTile(new Pos(pos.x+1, pos.y))) {
				if (!unvisitedTiles.contains(new Pos(pos.x+1, pos.y))) {
					unvisitedTiles.add(new Pos(pos.x+1, pos.y));
				}
			}
			//remove the tile we just placed a gasPart on from the list of possible tiles
			unvisitedTiles.remove(pos);
		}	
		
	}
	@Override
    public void ratCollision(Rat target) {}
	public void itemCollision(Item target) {}
	public void onPlacement() {}	
}
