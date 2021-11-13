package application;

import application.Game.RatManager;
import javafx.scene.image.Image;
import java.util.ArrayList;

public class DeathRat extends Rat {
	private int killCount;
	private void setKillCount(int killCount) {
		this.killCount = killCount;
	}
	public DeathRat(ratTypes type, Image image, int[] pos) {
		super(type, image, pos);
		setKillCount(0);
	}
    public boolean move() {
		if (killCount < 5) {
			super.move();
			ArrayList<BasicRat> ratsToKill = checkRatCollision(pos);	
			//check if rat on current tile			
			if (ratsToKill != null) {
				//send kill request(s) for rat and increase killcount
				if (ratsToKill.size() + killCount <= 5) {
					RatManager.killBasicRatsAtPos(pos);
				} else { 
					//this should rarely ever be used
					//only send kill requests if they will not increase the killcount above 5
					while (ratsToKill.size() + killCount <= 5) {
						ratsToKill.remove(ratsToKill.size()-1);					
					}
					RatManager.killRatArray(ratsToKill);
				}				
			}					
		} else {
			RatManager.killSingleRat(this);
		}
		return true;
	}
}
