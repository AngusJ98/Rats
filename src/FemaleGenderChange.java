package application;

import javafx.scene.image.Image;

public class FemaleGenderChange extends Entity {

    public FemaleGenderChange(Image image) {
        super(image);
    }

    void onCollision(BasicRat target) {
    	if (!target.ratType.equals(ratTypes.BABY)) {
    		target.setRatType(ratTypes.FEMALE);
    	}
    }
}
