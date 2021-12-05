package entity;

import gameHandler.*;

import javafx.scene.image.Image;
import java.util.ArrayList;

public class Sterilization extends Item{
    private static final int DEFAULT_TIME = 50; // idk how long it's meant to stay for. 5 seconds?
    private static final int RANGE = 2; //idk what range should be either
    private int timer;

    public Sterilization(Pos position) {
        super(new Image("file:resources/sterile.png"), position);
        timer = DEFAULT_TIME;
    }

    public Sterilization(Pos position, int timeLeft) {
        super(new Image("file:resources/sterile.png"), position);
        timer = timeLeft;
    }
	
	public void onPlacement() {}
    public void ratCollision(Rat target) {}
	public void itemCollision(Item target) {}	
	public void tick() {
		for (int yoffset = -RANGE; yoffset < RANGE; yoffset++) {
			for (int xoffset = -RANGE; xoffset < RANGE; xoffset++) {
				ArrayList<BasicRat> rats = Game.RatManager.getRatsAtPos(new Pos(this.pos.x + xoffset, this.pos.y + yoffset));
				if (!(rats == null) && rats.size() > 0) {
					for (BasicRat rat : rats) {
						rat.setMateStatus(false);
						rat.setSterile(true);
					}	
				}	
			}
		}	

		if (timer <= 0 ) {
			Game.ItemManager.killItem(this);
		}
		timer--;
	}
}

