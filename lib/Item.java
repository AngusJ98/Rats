package entity;

public abstract class Item {
	public abstract void onPlacement() {}	
	public abstract void tick() {}
    public void onCollision(Entity target) {
		if !(target == null) { 
			switch (target.getEntityType()) {
			case RAT:
				ratCollision((Rat) target);
				break;
			case ITEM:
				itemCollision((Item) target)
				break;
			}
		}
    }
    private abstract void ratCollision(Rat target);
	public abstract void itemCollision(Item target);
}