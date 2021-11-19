
public class FemaleGenderChange extends Entity {
	
    public FemaleGenderChange() {
        super(Image image, CollisionType.ITEM);
    }
    
    void onCollision(Entity target) {
    	target.setGender(ratTypes.Female);
    }
}
