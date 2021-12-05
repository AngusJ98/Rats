package gameHandler;

public class Inventory {
	//Inventory just tracks items left etc, I don't think it needs to be an entity
	//we can either just display text on the side of the screen 
	//or we could have lil icons which would be pretty neat
	//would require a hashmap of <screenCoordinate, image> then when an item is used
	//we can set screenCoordinate to a large number
    private static int[] itemsLeft;
    private static int[] itemNames;
    private static int bombRestockRate;
    private static int gasRestockRate;
    private static int sterileRestockRate;
    private static int noEntRestockRate;
    private static int maleRestockRate;
    private static int femaleRestockRate;
    private static int deathRestockRate;

    public Inventory() {
        
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

    public String toString() {
        String result = "Remaining Items:\n";
        //gets information from the "ItemsLeft" Attribute
        for (int i = 0; i< itemsLeft.length + 1; i++) {
        	result += itemNames[i] + " "; 
        	result += itemsLeft[i] + "\n";
        }  
        return result;
    }

    public void restock() {

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

}
	