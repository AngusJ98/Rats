/**
 * <p> 1. File-name: Tile.java</p>
 * <p> 2. Creation Date: XX/XX/XX </p>
 * <p> 3. Last modification date: XX/XX/XX </p>
 * <p> 4. Purpose of the program: Implementation of different tile types</p>
 *
 * @author ??
 */
//whoever`s class this is please fill in missing information in Javadoc
package tiles;

import java.util.ArrayList;

import entity.Entity;
import entity.Rat;
import javafx.scene.image.Image;

public class Tile { // Does tile need to be an entity.Entity? Tiles don't
    // move or perform any actions etc.
    private boolean isPassable;
    private boolean hidesRats;
    private TileTypes type;
    private ArrayList<Entity> items;
    private ArrayList<Rat> rats;
    private Image image = new Image("file:resources/error.png");

    /**
     * Constructor used to create a new Tile.
     * Uses a switch statement to initialise a tile based on the case it satisfies
     * and executes specific things respective of the case it satisfies.
     * <p> no side-effects</p>
     * <p> not referentially transparent</p>
     *
     * @param type the tile type which is being created
     */
    public Tile(TileTypes type) {
//		super(image, type);
        setItems(new ArrayList<Entity>());
        switch (type) {
            case TUNNEL:
                setHidesRats(true);
                setPassable(true);
                setType(type);
                this.image = new Image("file:resources/strightTun.png");
                break;
            case GRASS:
                setHidesRats(false);
                setPassable(false);
                setType(type);
                this.image = new Image("file:resources/grassBlock.png");
                break;
            case SPEEDTILE:
                //need to figure out if I want each tile type to be a seperate class, might merge into default case
                setHidesRats(false);
                setPassable(true);
                setType(type);
                this.image = new Image("file:resources/speedTile.png");
                break;
            case PATH:
                setHidesRats(false);
                setPassable(true);
                setType(type);
                this.image = new Image("file:resources/dirtBlock.png");
                break;
            default:
                setHidesRats(false);
                setPassable(true);
                setType(type);
                System.out.println("Error in getting tile type: " + type);
                this.image = new Image("file:resources/cornTun.png");
                break;
        }
    }

    /**
     * Method used to return the image of a tile
     * <p> no side-effects</p>
     * <p> not referentially transparent</p>
     *
     * @return the image of any given tile
     */
    public Image getImage() {
        return image;
    }

    /**
     * Method used to return the type of tile
     * <p> no side-effects</p>
     * <p> not referentially transparent</p>
     *
     * @return the type of tile
     */
    public TileTypes getType() {
        return type;
    }

    /**
     * Method used to set the type of tile
     * <p> no side-effects</p>
     * <p> not referentially transparent</p>
     *
     * @param type the type of tile
     */
    private void setType(TileTypes type) {
        this.type = type;
    }

    /**
     * Method used to check if the tile is passable
     * <p> no side-effects</p>
     * <p> not referentially transparent</p>
     *
     * @return true if the tile is passable, else false
     */
    public boolean isPassable() {
        return isPassable;
    }

    /**
     * Method used to set a tile to be Passable
     * <p> no side-effects</p>
     * <p> not referentially transparent</p>
     *
     * @param isPassable whether a tile is passable eg.T/F
     */
    private void setPassable(boolean isPassable) {
        this.isPassable = isPassable;
    }

    /**
     * @return
     */
    public ArrayList<Entity> getItems() {
        return items;
    }

    /**
     * @param items
     */
    public void setItems(ArrayList<Entity> items) {
        this.items = items;
    }

    /**
     *
     * @param item
     */
    public void addItem(Entity item) {
        this.items.add(item);
    }

    /**
     *
     * @param item
     */
    public void removeItem(Entity item) {
        this.items.remove(item);
    }

    /**
     *
     * @return
     */
    public boolean getHidesRats() {
        return hidesRats;
    }

    /**
     *
     * @param hidesRats
     */
    private void setHidesRats(boolean hidesRats) {
        this.hidesRats = hidesRats;
    }

    /*
    public void checkCollision() {
        ArrayList<Entity> occupants = new ArrayList<>(this.rats);
        occupants.addAll(this.items);
        if (occupants.size() >= 2) {
            for (Entity o : occupants) {
                for (Entity t : occupants) {
                    o.onCollision(t);
                }
            }
        }
    }
    */
    public boolean areItemsPlaceable() {
        return this.getType() == TileTypes.PATH;
    }

    public static Tile createTileFromLetter(char typeLetter) {
        TileTypes tileType = TileTypes.UNKNOWN;
        switch (typeLetter) {
            case 'P':
                tileType = TileTypes.PATH;
                break;
            case 'G':
                tileType = TileTypes.GRASS;
                break;
            case 'S':
                tileType = TileTypes.SPEEDTILE;
                break;
            case 'T':
                tileType = TileTypes.TUNNEL;
                break;
            default:
                break;
        }
        return new Tile(tileType);
    }
}
