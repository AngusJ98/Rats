package entity;

import javafx.scene.image.Image;

public class Bomb extends Entity{
    public static final int DEFAULT_BOMB_TIME = 4000;
    public static final int TICKS_PER_SECOND = 1000;
    private int range;
    private int timer;

    public Bomb(int[] position) {
        super(new Image(""), CollisionType.ITEM, position);
        timer = DEFAULT_BOMB_TIME;
    }

    public Bomb(int[] position, int timeLeft) {
        super(new Image(""), CollisionType.ITEM, position);
        timer = timeLeft;
    }


    // touching the bomb shouldn't kill the rat.
    private void ratCollision(Rat target) {
        if (true) {
            target.kill();
        };
    }

    // if anyone renames this method to something more sensible
    // i will personally feel very offended
    private void boom() {
        // create ExplosionParts etc
    }

    public void tick() {
        timer--;
        boom();
    }

    //Each bomb should display a count down: 4, 3, 2, 1.
    public int getSeconds() {
        // god damn i hate java so much look at this shit
        return (int) Math.ceil((float) timer / TICKS_PER_SECOND);
    }

    @Override
    // shouldn't do anything
    public void onCollision(Entity target) {
        switch (target.getCollisionGroup()) {
            case RAT:
                ratCollision((Rat)target);
                break;
            case ITEM:
                break;
        }
    }
}

//From the Spec:
//Once placed, a bomb will count down from 4 in 1 second increments.
//Once the bomb reaches zero a powerful explosion will extend vertically and horizontally
//(not diagonally) until it reaches grass in all directions (the
//explosion passes through tunnels). The grass prevents
//the explosion from continuing in a given direction. The
//explosion will kill any rats on these tiles and will also
//destroy any other items currently placed on these tiles. <<<<<<<<<<------------------------------------
//Each bomb should display a count down: 4, 3, 2, 1.