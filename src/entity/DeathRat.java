package entity;

import gameHandler.Game.RatManager;
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
    public void checkCurrentTile() {
    	if (killCount < 5) {
			ArrayList<BasicRat> ratsToKill = RatManager.getRatsAtPos(pos); //checkRatCollision(pos);	
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
			//kill the death rat after 5 kills
			RatManager.killSingleRat(this);
		}
    }
    
}
