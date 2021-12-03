package entity;

import gameHandler.Pos;
import javafx.scene.image.Image;

public class Sterilization extends Item{
    private static final int DEFAULT_TIME = 4000; // idk how long it's meant to stay for
    private static final int RANGE = 3; //idk what range should be either
    private int timer;

    public Sterilization(Pos position) {
        super(new Image(""), CollisionType.ITEM, position);
        timer = DEFAULT_TIME;
    }

    public Sterilization(Pos position, int timeLeft) {
        super(new Image(""), CollisionType.ITEM, position);
        timer = timeLeft;
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

