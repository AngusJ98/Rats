package gameHandler;

import java.util.HashMap;

/**
 * <p> 1. File-name: Inventory.java</p>
 * <p> 2. Creation Date: 01/12/21 </p>
 * <p> 3. Last modification date: 05/12/21 </p>
 * <p> 4. Purpose of the program: Used to hold and restock items which can be placed</p>
 *
 * @author Gus
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
     * Constructor for Inventory that takes the rate at which item is spawned and
     * the starting quantity of items
     *
     * @param stats     hashmap declaring the frequency of items spawning
     * @param inventory an array of the items and their quantities
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
     * Method used to return the restock rate of Bomb
     *
     * @return restock rate of Bomb
     */
    public int getBombRestockRate() {
        return bombRestockRate;
    }

    /**
     * Method used to set the rate of which Bomb is restocked
     *
     * @param bombRestockRate restock rate of bomb
     */
    public void setBombRestockRate(int bombRestockRate) {
        this.bombRestockRate = bombRestockRate;
    }

    /**
     * Method used to return the restock rate of Gas
     *
     * @return Gas restock rate
     */
    public int getGasRestockRate() {
        return gasRestockRate;
    }

    /**
     * Method used to set the restock rate of Gas
     *
     * @param gasRestockRate restock rate of Gas
     */
    public void setGasRestockRate(int gasRestockRate) {
        this.gasRestockRate = gasRestockRate;
    }

    /**
     * Method used to return the restock rate of Sterilizations
     *
     * @return Sterilization restock rate
     */
    public int getSterileRestockRate() {
        return sterileRestockRate;
    }

    /**
     * Method used to set the rate at which Sterilizations restock
     *
     * @param sterileRestockRate restock rate of Sterilizations
     */
    public void setSterileRestockRate(int sterileRestockRate) {
        this.sterileRestockRate = sterileRestockRate;
    }

    /**
     * Method which returns the restock rate of No Entry Signs
     *
     * @return No Entry Signs restock rate
     */
    public int getNoEntRestockRate() {
        return noEntRestockRate;
    }

    /**
     * Method which returns the restock rate of No Entry Signs
     *
     * @param noEntRestockRate rate of restock for No Entry Signs
     */
    public void setNoEntRestockRate(int noEntRestockRate) {
        this.noEntRestockRate = noEntRestockRate;
    }

    /**
     * Method which returns Male Gender Change restock rate
     *
     * @return Male Gender Change restock rate
     */
    public int getMaleRestockRate() {
        return maleRestockRate;
    }

    /**
     * Method which sets the Male Gender Change restock rate
     *
     * @param maleRestockRate rate at which Male Gender Change is restocked
     */
    public void setMaleRestockRate(int maleRestockRate) {
        this.maleRestockRate = maleRestockRate;
    }

    /**
     * Method which returns the Female Gender Change restock rate
     *
     * @return Female Gender Change restock rate
     */
    public int getFemaleRestockRate() {
        return femaleRestockRate;
    }

    /**
     * Method which sets the Female Gender Change restock rate
     *
     * @param femaleRestockRate rate at which Female Gender Change is restocked
     */
    public void setFemaleRestockRate(int femaleRestockRate) {
        this.femaleRestockRate = femaleRestockRate;
    }

    /**
     * Method which returns the Death Rat restock rate
     *
     * @return Death Rat restock rate
     */
    public int getDeathRestockRate() {
        return deathRestockRate;
    }

    /**
     * Method which sets the Death Rat restock rate
     *
     * @param deathRestockRate rate at which the Death Rat restocks
     */
    public void setDeathRestockRate(int deathRestockRate) {
        this.deathRestockRate = deathRestockRate;
    }

    /**
     * Method which returns the restock rate of Poison
     *
     * @return restock rate of Poison
     */
    public int getPoisonRestockRate() {
        return poisonRestockRate;
    }

    /**
     * Method which sets the restock rate of Poison
     *
     * @param poisonRestockRate rate at which the Poison restocks
     */
    public void setPoisonRestockRate(int poisonRestockRate) {
        this.poisonRestockRate = poisonRestockRate;
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
	