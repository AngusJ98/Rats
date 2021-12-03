/**
 *
  <p> 1. File-name: DeathRat.java</p>
  <p> 2. Creation Date: (N/A) </p>
  <p> 3. Last modification date:</p>
  <p> 4. Purpose of the program: Death Rat implementation</p>
* @author Andrew
*/

//Yo Andrew, decided not to add copy right, and version history for the java docs.
//make neccessary changes if need be, regardless delete this comment after.
//Add a creation date if need be

package entity;

import gameHandler.Game.RatManager;
import gameHandler.Pos;
import javafx.scene.image.Image;
import java.util.ArrayList;

public class DeathRat extends Rat {
    private static final int MAX_KILLS = 5;
	private int killCount;

	/**
	 *
	 * @param pos
	 */
	public DeathRat(Pos pos) {
		super(RatTypes.DEATH, new Image(""), pos);
		setKillCount(0);
	}

	public DeathRat(Pos pos, int killCount) {
		super(RatTypes.DEATH, new Image(""), pos);
		this.killCount = killCount;
	}

    private void setKillCount(int killCount) {
        this.killCount = killCount;
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
