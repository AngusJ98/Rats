package application;

public class DeathRat extends Rat {
	private int killCount;
	private void setKillCount(int killCount) {
		this.killCount = killCount;
	}
	public DeathRat(ratTypes type) {
		super(type);
		setKillCount(0);
	}
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
