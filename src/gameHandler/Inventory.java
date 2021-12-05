package gameHandler;

import java.util.HashMap;

/**
 * <p> 1. File-name: Inventory.java</p>
 * <p> 2. Creation Date: XX/XX/XX </p>
 * <p> 3. Last modification date: XX/XX/XX </p>
 * <p> 4. Purpose of the program: Used to hold and restock items which can be placed</p>
 *
 * @author
 */

public class Inventory {

    private static int bombCount;
    private static int gasCount;
    private static int sterileCount;
    private static int noEntryCount;
    private static int maleCount;
    private static int femaleCount;
    private static int deathCount;
    private static int poisonCount;

    private int bombRestockRate;
    private int gasRestockRate;
    private int sterileRestockRate;
    private int noEntRestockRate;
    private int maleRestockRate;
    private int femaleRestockRate;
    private int deathRestockRate;
    private int poisonRestockRate;

    /**
     * Constructor for Inventory
     *
     * @param stats 
     * @param inventory
     */
    public Inventory(HashMap<String, Integer> stats, int[] inventory) {
        this.bombRestockRate = stats.get("bombFreq");
        this.gasRestockRate = stats.get("gasFreq");
        this.sterileRestockRate = stats.get("steriliseFreq");
        this.noEntRestockRate = stats.get("noEntryFreq");
        this.maleRestockRate = stats.get("mSexChangeFreq");
        this.femaleRestockRate = stats.get("fSexChangeFreq");
        this.deathRestockRate = stats.get("deathRatFreq");
        this.poisonRestockRate = stats.get("poisonFreq");
        bombCount = inventory[0];
        gasCount = inventory[1];
        sterileCount = inventory[2];
        noEntryCount = inventory[3];
        maleCount = inventory[4];
        femaleCount = inventory[5];
        deathCount = inventory[6];
        poisonCount = inventory[7];
    }

    /**
     * Method which returns the number of Poison in the Inventory
     *
     * @return poison count
     */
    public static int getPoisonCount() {
        return poisonCount;
    }

    /**
     * Method which sets the amount of Poison in the Inventory
     *
     * @param poisonCount the amount of poison in the Inventory
     */
    public static void setPoisonCount(int poisonCount) {
        Inventory.poisonCount = poisonCount;
    }

    /**
     * Method which returns the number of Bombs in the Inventory
     *
     * @return bomb count
     */
    public static int getBombCount() {
        return bombCount;
    }

    /**
     * Method which returns the number of Gas` in the Inventory
     *
     * @return gas count
     */
    public static int getGasCount() {
        return gasCount;
    }

    /**
     * Method which returns the number of Sterilizations in the Inventory
     *
     * @return Sterilization count
     */
    public static int getSterileCount() {
        return sterileCount;
    }

    /**
     * Method which returns the number of No Entry Sings in the Inventory
     *
     * @return No Entry Sign count
     */
    public static int getNoEntryCount() {
        return noEntryCount;
    }

    /**
     * Method which returns the number of Male Gender Changes in the Inventory
     *
     * @return Male gender change count
     */
    public static int getMaleCount() {
        return maleCount;
    }

    /**
     * Method which returns the number of Female Gender Changes in the Inventory
     *
     * @return Female gender change count
     */
    public static int getFemaleCount() {
        return femaleCount;
    }

    /**
     * Method which returns the number of Death Rats in the Inventory
     *
     * @return Death rat count in Inventory
     */
    public static int getDeathCount() {
        return deathCount;
    }

    /**
     * Method which is used to set the amount of Bombs in Inventory
     *
     * @param bombCount number of Bombs
     */
    public static void setBombCount(int bombCount) {
        Inventory.bombCount = bombCount;
    }

    /**
     * Method which is used to set the amount of Gas in Inventory
     *
     * @param gasCount number of Gas
     */
    public static void setGasCount(int gasCount) {
        Inventory.gasCount = gasCount;
    }

    /**
     * Method which is used to set the amount of Sterilization in Inventory
     *
     * @param sterileCount number of sterilizers
     */
    public static void setSterileCount(int sterileCount) {
        Inventory.sterileCount = sterileCount;
    }

    /**
     * Method which is used to set the amount of No Entry Signs in Inventory
     *
     * @param noEntryCount number of No Entry Signs
     */
    public static void setNoEntryCount(int noEntryCount) {
        Inventory.noEntryCount = noEntryCount;
    }

    /**
     * Method which is used to set the amount of Male Gender Changes in Inventory
     *
     * @param maleCount number of Male Gender Changes
     */
    public static void setMaleCount(int maleCount) {
        Inventory.maleCount = maleCount;
    }

    /**
     * Method which is used to set the amount of Female Gender Changes in Inventory
     *
     * @param femaleCount number of Female Gender Changes
     */
    public static void setFemaleCount(int femaleCount) {
        Inventory.femaleCount = femaleCount;
    }

    /**
     * Method which is used to set the amount of Death Rats in Inventory
     *
     * @param deathCount number of Death Rats
     */
    public static void setDeathCount(int deathCount) {
        Inventory.deathCount = deathCount;
    }

    /**
     * Method used to restock individual items in the Inventory
     * after a certain amount of time
     */
    public void restock() {
        if (Game.getTimeLeft() % bombRestockRate == 0) {
            bombCount += 1;
        }

        if (Game.getTimeLeft() % gasRestockRate == 0) {
            gasCount += 1;
        }

        if (Game.getTimeLeft() % sterileRestockRate == 0) {
            sterileCount += 1;
        }

        if (Game.getTimeLeft() % noEntRestockRate == 0) {
            noEntryCount += 1;
        }

        if (Game.getTimeLeft() % maleRestockRate == 0) {
            maleCount += 1;
        }

        if (Game.getTimeLeft() % femaleRestockRate == 0) {
            femaleCount += 1;
        }
        if (Game.getTimeLeft() % deathRestockRate == 0) {
            deathCount += 1;
        }

        if (Game.getTimeLeft() % poisonRestockRate == 0) {
            poisonCount += 1;
        }
    }

}
	