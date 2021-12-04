package entity;

import gameHandler.*;

import javafx.scene.image.Image;
import java.util.ArrayList;

public class Sterilization extends Item{
    private static final int DEFAULT_TIME = 4000; // idk how long it's meant to stay for
    private static final int RANGE = 3; //idk what range should be either
    private int timer;
	private ArrayList<SterilizationPart> parts = new ArrayList<SterilizationPart>();

    public Sterilization(Pos position) {
        super(new Image(""), position);
        timer = DEFAULT_TIME;
    }

    public Sterilization(Pos position, int timeLeft) {
        super(new Image(""), position);
        timer = timeLeft;
    }
	
	public void onPlacement() {}
    public void ratCollision(Rat target) {}
	public void itemCollision(Item target) {}	
	public void tick() {
		for (int yoffset = -RANGE; yoffset < RANGE; yoffset++) {
			for (int xoffset = -RANGE; xoffset < RANGE; xoffset++) {
				ArrayList<BasicRat> rats = Game.RatManager.getRatsAtPos(new pos(this.pos.x + xoffset, this.pos.y + yoffset));
				if (!(rats == null) && rats.size() > 0) {
					for (BasicRat rat : rats) {
						rat.setMateStatus(false);
					}	
				}	
			}
		}	
		
	}
}

