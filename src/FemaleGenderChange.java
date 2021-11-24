import javafx.scene.image.Image;

public class FemaleGenderChange extends Entity {

    public FemaleGenderChange(Image image) {
        super(image, CollisionType.ITEM);
    }

    void onCollision(BasicRat target) {
    	target.setGender(ratTypes.FEMALE);
    }

    void onCollision(Entity target) {

    }
}
