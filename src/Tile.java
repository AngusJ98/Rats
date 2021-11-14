import java.util.ArrayList;
import javafx.scene.image.Image;

enum tileTypes {
	PATH, TUNNEL, GRASS, SPEEDTILE
}
public abstract class Tile extends Entity {
	private boolean isPassable;
	private boolean hidesRats;	
	private tileTypes type;
	private ArrayList<Entity> items;
	
	public Tile(tileTypes type, Image image) {
		super(image);
		setItems(new ArrayList<Entity>()); 
		switch (type) {		
		case TUNNEL:
			setHidesRats(true);
			setPassable(true);
			setType(type);
			break;
		case GRASS:
			setHidesRats(false);
			setPassable(false);
			setType(type);
			break;
		case SPEEDTILE:
			//need to figure out if I want each tile type to be a seperate class, might merge into default case
			setHidesRats(false);
			setPassable(true);
			setType(type);
			break;
		default:
			setHidesRats(false);
			setPassable(true);
			setType(type);
			break;
		}
	}
	public tileTypes getType() {
		return type;
	}
	private void setType(tileTypes type) {
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
	
}
