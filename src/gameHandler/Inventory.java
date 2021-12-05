package gameHandler;

import java.util.HashMap;

public class Inventory {

    private static int bombCount;
    private static int gasCount;
    private static int sterileCount;
    private static int noEntryCount;
    private static int maleCount;
    private static int femaleCount;
    private static int deathCount;

    private int bombRestockRate;
    private int gasRestockRate;
    private int sterileRestockRate;
    private int noEntRestockRate;
    private int maleRestockRate;
    private int femaleRestockRate;
    private int deathRestockRate;

    public Inventory(HashMap<String, Integer> stats) {
        this.bombRestockRate = stats.get("bomb");
        this.gasRestockRate = stats.get("gas");
        this.sterileRestockRate = stats.get("sterilise");
        this.noEntRestockRate = stats.get("noEntry");
        this.maleRestockRate = stats.get("mSexChange");
        this.femaleRestockRate = stats.get("fSexChange");
        this.deathRestockRate = stats.get("deathRat");
    }

    // TODO:
    public static int getBombCount() {
        return bombCount;
    }

    public static int getgasCount() {
        return gasCount;
    }

    public static int getsterileCount() {
        return sterileCount;
    }

    public static int getnoEntryCount() {
        return noEntryCount;
    }

    public static int getmaleCount() {
        return maleCount;
    }

    public static int getfemaleCount() {
        return femaleCount;
    }

    public static int getDeathCount() {
        return deathCount;
    }

    public static void setBombCount(int bombCount) {
        this.bombCount = bombCount;
    }

    public static void setGasCount(int gasCount) {
        this.gasCount = gasCount;
    }

    public static void setSterileCount(int sterileCount) {
        this.sterileCount = sterileCount;
    }

    public static void setNoEntryCount(int noEntryCount) {
        this.noEntryCount = noEntryCount;
    }

    public static void setMaleCount(int maleCount) {
        this.maleCount = maleCount;
    }

    public static void setFemaleCount(int femaleCount) {
        this.femaleCount = femaleCount;
    }

    public static void setDeathCount(int deathCount) {
        this.deathCount = deathCount;
    }

    public void restock() {
        if (Game.getTimeLeft() % bombRestockRate == 0) {
            bombCount += 1;
        }

        if (Game.getTimeLeft() % gasRestockRate == 0) {
            gasCount += 1;
        }

    }

    /*
    public String toString() {
        String result = "Remaining Items:\n";
        //gets information from the "ItemsLeft" Attribute
        for (int i = 0; i< itemsLeft.length + 1; i++) {
        	result += itemNames[i] + " "; 
        	result += itemsLeft[i] + "\n";
        }  
        return result;
    }

    
    public void placeItem(int itemArrayPos, int[] itemBoardPos) {
        //where itemPos is the id of the item's type, and also it's position in the array
    	if (itemsLeft[itemArrayPos] > 0) {
    		itemsLeft[itemArrayPos]--;
    		//send request to place item - probably needs access to board/game to set items or smth
    		//like a Board.SetItem(itemBoadPos, itemName[itemArrayPos]) type thing
    	} else {
    		System.out.println("Ran out of uses of " + itemNames[itemArrayPos]);
    		//not sure what javafx uses to print text but use that instead
    	}   	
    }
    */
}
	