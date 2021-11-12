package application;

import java.util.ArrayList;
import javafx.scene.image.Image;

enum tileTypes {
	PATH, TUNNEL, GRASS, SPEEDTILE
}
public class Tile extends Entity {
	private boolean isPassable;
	private boolean hidesRats;	
	private tileTypes type;
	private ArrayList<Entity> Items;
	
	public Tile(tileTypes type, Image image) {
		super(image);
		switch (type) {		
		case TUNNEL:
			setHidesRats(true);
			setPassable(true);
			setItems(new ArrayList<Entity>());
			setType(type);
			break;
		case GRASS:
			setHidesRats(false);
			setPassable(false);
			setItems(new ArrayList<Entity>());
			setType(type);
			break;
		case SPEEDTILE:
			//need to figure out if I want each tile type to be a seperate class, might merge into default case
			setHidesRats(false);
			setPassable(true);
			setItems(new ArrayList<Entity>());
			setType(type);
			break;
		default:
			setHidesRats(false);
			setPassable(true);
			setItems(new ArrayList<Entity>());
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
		return Items;
	}
	public void setItems(ArrayList<Entity> items) {
		Items = items;
	}
	public boolean getHidesRats() {
		return hidesRats;
	}
	private void setHidesRats(boolean hidesRats) {
		this.hidesRats = hidesRats;
	}
	
}
