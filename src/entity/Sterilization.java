package entity;

import javafx.scene.image.Image;

public class Sterilization extends Entity{
    private int range;

    public Sterilization(int[] position) {
        super(new Image(""), CollisionType.ITEM, position);
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
        target.setMateStatus(false);
    }
}

