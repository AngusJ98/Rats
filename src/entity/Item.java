package entity;

import gameHandler.Game;
import gameHandler.Pos;
import javafx.scene.image.Image;

public abstract class Item extends Entity {
	
	public Item(Image image, Pos pos) {
        super(image, EntityType.ITEM, pos);
	}	
	public abstract void onPlacement();
	public abstract void tick();
    public void onCollision(Entity target) {
		if (!(target == null)) {
			System.out.println(target.getEntityType());
			switch (target.getEntityType()) {
				case RAT:
					ratCollision((Rat) target);
					break;
				case ITEM:
					itemCollision((Item) target);
					break;
			}
		}
    }
    protected abstract void ratCollision(Rat target);
	public abstract void itemCollision(Item target);
}