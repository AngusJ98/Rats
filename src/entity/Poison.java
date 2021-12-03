package entity;

import gameHandler.Game;
import gameHandler.Pos;
import javafx.scene.image.Image;

public class Poison extends Entity{
    private static final int DEFAULT_TIME = 4000;
    private static final int RANGE = 1;
    private int timer;

    public Poison(Pos position) {
        super(new Image(""), CollisionType.ITEM, position);
        timer = DEFAULT_TIME;
    }

    public Poison(Pos position, int timeLeft) {
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
        Game.RatManager.killSingleRat(target);        //Still needs the part where the poison is used up and removed from the path.
    }
}


//What is mentioned in the Spec for entity.Poison:
//Once placed, poison will remain on the path until a rat
//runs into it. Once a rat runs into it the rat is killed and
//the poison is used up and removed from the path.