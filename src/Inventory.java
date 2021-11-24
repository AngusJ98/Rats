import javafx.scene.image.Image;

public class Inventory { //Inventory just tracks items left etc, I don't think it needs to be an entity
    private int[] itemsLeft;

    public Inventory(Image image, CollisionType collisionGroup) {
//        super(image, collisionGroup);
    }

    public String toString() {
        String result = "The inventory:\n ";
        //gets information from the "ItemsLeft" Attribute
        //for (int i = 0; i< size.itemsLeft[] + 1; i++;
        //  result += itemsLeft[i];
        return result;
    }

    public void restock() {
        //restock
    }

    public void placeItem(int item) {
        //itemsLeft.add(item); ???
    }

}
	