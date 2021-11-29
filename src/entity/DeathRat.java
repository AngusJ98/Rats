package entity;

import javafx.scene.image.Image;

public class DeathRat extends Rat {
	private int killCount;
	private void setKillCount(int killCount) {
		this.killCount = killCount;
	}
	public DeathRat(RatTypes type, Image image) {
		super(type, image);
		setKillCount(0);
	}
    public void ratActions() {}
    public void onRatCollision() {}

    void kill() {

    }

    public void onCollision(Entity entity) {}
    public boolean move() {
		if (killCount < 5) {
			super.move();
			//check if rat on current tile
			//send kill request(s) for rat and increase killcount
			//needs to only send kill requests if they will not increase the killcount above 5
		} else {
			//send kill request for death rat
		}
		return true;
	}
}
