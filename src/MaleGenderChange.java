import javafx.scene.image.Image;

public class MaleGenderChange extends Entity {

    public MaleGenderChange(Image image) {
        super(image, CollisionType.ITEM);
    }

    void onCollision(BasicRat target) {
    	target.setGender(ratTypes.MALE);
    }

    void onCollision(Entity target) {

    }
}