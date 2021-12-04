//Yo Andrew, decided not to add copy right, and version history for the java docs.
//make neccessary changes if need be, regardless delete this comment after.
//Add a creation date if need be

package entity;

import gameHandler.Game.RatManager;
import gameHandler.Pos;
import javafx.scene.image.Image;
import java.util.ArrayList;

/**
 *
 <p> 1. File-name: DeathRat.java</p>
 <p> 2. Creation Date: (N/A) </p>
 <p> 3. Last modification date:</p>
 <p> 4. Purpose of the program: Death Rat implementation</p>
 * @author Andrew
 */

public class DeathRat extends Rat {
    private static final int MAX_KILLS = 5;
	private int killCount;

	/**
	 * 1st Constructor.
	 * <p> side-effects</p>
	 * <p> not referentially transparent</p>
	 * @param pos takes coordinates of death rat to be placed.
	 */
	public DeathRat(Pos pos) {
		super(RatTypes.DEATH, pos);
		setKillCount(0);
	}

	/**
	 * 2nd Constructor.
	 * <p> no side-effects</p>
	 * <p> not referentially transparent</p>
	 * @param pos takes coordinates of death rat to be placed.
	 * @param killCount takes kill count of death rat.
	 */
	public DeathRat(Pos pos, int killCount) {
		super(RatTypes.DEATH,  pos);
		this.killCount = killCount;
	}

	/**
	 *  Setting KillCount
	 *  <p> no side-effects</p>
	 * 	<p> not referentially transparent</p>
	 * @param killCount takes kill count of death rat.
	 */
    private void setKillCount(int killCount) {
        this.killCount = killCount;
	}

	/**
	 * Checks the current tile if the death rate is above 5 kills or more
	 * if so then the Death Rat Dies.
	 */
    public void checkCurrentTile() {
    	if (killCount < MAX_KILLS) {
			ArrayList<BasicRat> ratsToKill = RatManager.getRatsAtPos(pos); 
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
		//get entities on tile
		//check for no entry tiles
    }
}
