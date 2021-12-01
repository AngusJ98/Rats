package entity;

import gameHandler.Game.RatManager;
import javafx.scene.image.Image;
import java.util.ArrayList;

public class DeathRat extends Rat {
    private static final int MAX_KILLS = 5;
	private int killCount;

	public DeathRat(int[] pos) {
		super(RatTypes.DEATH, new Image(""), pos);
		setKillCount(0);
	}

	public DeathRat(int[] pos, int killCount) {
		super(RatTypes.DEATH, new Image(""), pos);
		this.killCount = killCount;
	}

    private void setKillCount(int killCount) {
        this.killCount = killCount;
	}

    @Override
    public void kill() {

    }

    public void checkCurrentTile() {
    	if (killCount < MAX_KILLS) {
			ArrayList<BasicRat> ratsToKill = RatManager.getRatsAtPos(pos); //checkRatCollision(pos);	
			//check if rat on current tile			
			if (ratsToKill != null) {
				//send kill request(s) for rat and increase killcount
				if (ratsToKill.size() + killCount <= MAX_KILLS) {
					RatManager.killBasicRatsAtPos(pos);
				} else { 
					//this should rarely ever be used
					//only send kill requests if they will not increase the killcount above 5
					while (ratsToKill.size() + killCount <= MAX_KILLS) {
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

    @Override
    public void onCollision(Entity t) {

    }
}
