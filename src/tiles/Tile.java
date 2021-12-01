package tiles;

import java.util.ArrayList;

import entity.Entity;
import entity.Rat;
import javafx.scene.image.Image;

public abstract class Tile { // Does tile need to be an entity.Entity? Tiles don't
                             // move or perform any actions etc.
	private boolean isPassable;
	private boolean hidesRats;	
	private TileTypes type;
	private ArrayList<Entity> items;
	private ArrayList<Rat> rats;
	private Image image = new Image("file:resources/error.png");



	public Tile(TileTypes type, Image image) {
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
		default:
			setHidesRats(false);
			setPassable(true);
			setType(type);
			this.image = new Image("file:resources/cornTun.png");
			break;
		}
	}
	public Image getImage() {
		return image;
	}
	public TileTypes getType() {
		return type;
	}
	private void setType(TileTypes type) {
		this.type = type;
	}
	public boolean isPassable() {
		return isPassable;
	}
	private void setPassable(boolean isPassable) {
		this.isPassable = isPassable;
	}
	public ArrayList<Entity> getItems() {
		return items;
	}
	public void setItems(ArrayList<Entity> items) {
		this.items = items;
	}
	public void addItem(Entity item) {
		this.items.add(item);
	}
	public void removeItem(Entity item) {
		this.items.remove(item);
	}
	public boolean getHidesRats() {
		return hidesRats;
	}
	private void setHidesRats(boolean hidesRats) {
		this.hidesRats = hidesRats;
	}
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
	public boolean areItemsPlaceable() {
		return this.getType() == TileTypes.PATH;
	}
}
