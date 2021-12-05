package entity;

import gameHandler.Game;
import gameHandler.Pos;

import java.util.ArrayList;

import javafx.scene.image.Image;

/**
 * <p> 1. File-name: Gas.java</p>
 * <p> 2. Creation Date: 30/11/2021 </p>
 * <p> 3. Last modification date: 5/12/2021</p>
 * <p> 4. Purpose of the program: Gas item implementation</p>
 * @author Andrew
 */

public class Gas extends Item {
    int gasNum = 0;
    private final int PLACE_INTERVAL = 10;
    private final int MAX_GAS = 6;
    ArrayList<Pos> unvisitedTiles = new ArrayList<Pos>();
    GasPart[] gasArray = new GasPart[MAX_GAS];

    public Gas(Pos position) {
        super(new Image("file:resources/gasCan.png"), position);

        //gas will act like a gas canister and be responsible for the spread of gasparts
        //gasparts will be the entities that deal damage to rats

        //gas will have no sprite and be responsible for the spread of gasparts
        //gas parts will be visible and deal damage to rats
    }

    public void tick() {
        //if there are less than <6> gas clouds, this method will attempt to place one each game tick

        if (gasNum < MAX_GAS) {

            //simplified code by checking positions at start
            //the algorithm i'm using is similar to the start of dijkstra's algorithm and
            //works based around checking positions in real time rather than all at the beginning

            if (Game.getTimeLeft() % PLACE_INTERVAL == 0) {
                //if the list is empty, either the algorithm hasn't run or there
                //are less than 6 tiles available. If there are less than 6 tiles available
                //(which should never happen - who would design a map where one gas is enough to win?)
                //,this will just place multiple gasparts in one location, and will not break
                if (unvisitedTiles.size() == 0) {
                    //add my location to a list of possible locations
                    unvisitedTiles.add(pos);
                }
                //create a gasPart at the location specified by unvisitedTiles(gasNum)
                //this should stop tiles being removed from the array, then re-added by other tiles
                gasArray[gasNum] = new GasPart(unvisitedTiles.get(gasNum));
                Game.ItemManager.addItem(gasArray[gasNum]);
                //search nearby tiles (in a + shape) for tiles that are passable and add them to the list of possible locations
                //tiles must not be outside the board or be already in the list as that could lead to recursion
                //and multiple gasparts being created in the same location
                Pos newPos = unvisitedTiles.get(gasNum);
                if ((newPos.y - 1 >= 0) && Game.TileManager.getPassableTile(new Pos(newPos.x, newPos.y - 1))) {
                    if (!unvisitedTiles.contains(new Pos(newPos.x, newPos.y - 1))) {
                        unvisitedTiles.add(new Pos(newPos.x, newPos.y - 1));
                    }
                }
                if (newPos.y + 1 <= Game.TileManager.getNumTileHeight() && Game.TileManager.getPassableTile(new Pos(newPos.x, newPos.y + 1))) {
                    if (!unvisitedTiles.contains(new Pos(newPos.x, newPos.y + 1))) {
                        unvisitedTiles.add(new Pos(newPos.x, newPos.y + 1));
                    }
                }
                if ((newPos.x - 1 >= 0) && Game.TileManager.getPassableTile(new Pos(newPos.x - 1, newPos.y))) {
                    if (!unvisitedTiles.contains(new Pos(newPos.x - 1, newPos.y))) {
                        unvisitedTiles.add(new Pos(newPos.x - 1, newPos.y));
                    }
                }
                if (newPos.x + 1 <= Game.TileManager.getNumTileWidth() && Game.TileManager.getPassableTile(new Pos(newPos.x + 1, newPos.y))) {
                    if (!unvisitedTiles.contains(new Pos(newPos.x + 1, newPos.y))) {
                        unvisitedTiles.add(new Pos(newPos.x + 1, newPos.y));
                    }
                }
                gasNum++;
            }
        } else {
            Game.ItemManager.killItem(this);
        }
    }

    @Override
    public void ratCollision(Rat target) {
    }

    public void itemCollision(Item target) {
    }

    public void onPlacement() {
    }
}
