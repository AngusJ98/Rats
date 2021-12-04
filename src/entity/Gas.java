package entity;

import gameHandler.Pos;
import java.util.ArrayList;
import javafx.scene.image.Image;

public class Gas extends Item {
	int gasNum = 0;
	GasPart[] gasArray = new GasPart[6];  
	ArrayList<Pos>() unvisitedTiles = new ArrayList<Pos>(); 
    public Gas(Pos position) {
        super(new Image(""), EntityType.ITEM, position);
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
			//search nearby tiles (in a + shape) for tiles that are passable and add them to the list of possible locations
			//tiles must not be outside the board or be already in the list as that could lead to recursion 
			//and multiple gasparts being created in the same location
			if ((pos.y-1 >= 0) && TileManager.getPassableTile(pos.x, pos.y-1)) {
				if (!unvisitedTiles.contains(pos.x, pos.y-1)) {
					unvisitedTiles.add(pos.x, pos.y-1);
				}
			}
			if (pos.y+1 <= TileManager.getNumTileHeight) && TileManager.getPassableTile(pos.x, pos.y+1)) {
				if (!unvisitedTiles.contains(pos.x, pos.y+1)) {
					unvisitedTiles.add(pos.x, pos.y+1);
				}
			}
			if (pos.x-1 >= 0) && TileManager.getPassableTile(pos.x-1, pos.y)) {
				if (!unvisitedTiles.contains(pos.x-1, pos.y)) {
					unvisitedTiles.add(pos.x-1, pos.y);
				}
			}
			if (pos.x+1 <= TileManager.getNumTileWidth) && TileManager.getPassableTile(pos.x+1, pos.y)) {
				if (!unvisitedTiles.contains(pos.x+1, pos.y)) {
					unvisitedTiles.add(pos.x+1, pos.y);
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
