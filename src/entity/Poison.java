package entity;

import javafx.scene.image.Image;

public class Poison extends Entity{
    private int range;

    public Poison() {
        super(new Image(""), CollisionType.ITEM);
    }

    @Override
    public void onCollision(Entity target) {
        switch (target.getCollisionGroup()) {
            case RAT:
                ratCollision((Rat)target);
                break;
            case ITEM:
                break;
        }
    }

    private void ratCollision(Rat target) {
        target.kill();
        //Still needs the part where the poison is used up and removed from the path.
    }
}


//What is mentioned in the Spec for entity.Poison:
//Once placed, poison will remain on the path until a rat
//runs into it. Once a rat runs into it the rat is killed and
//the poison is used up and removed from the path.