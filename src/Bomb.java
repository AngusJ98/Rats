import javafx.scene.image.Image;

public class Bomb extends Entity{
    private int range;

    public Bomb() {
        super(new Image(""), CollisionType.ITEM);
    }

    @Override
    void onCollision(Entity target) {
        switch (target.getCollisionGroup()) {
            case RAT:
                ratCollision((Rat)target);
                break;
            case ITEM:
                break;
        }
    }

    private void ratCollision(Rat target) {
        if (true) {
            target.kill();
        };
    }
}

//From the Spec:
//Once placed, a bomb will count down from 4 in 1 second increments.
//Once the bomb reaches zero a powerful explosion will extend vertically and horizontally
//(not diagonally) until it reaches grass in all directions (the
//explosion passes through tunnels). The grass prevents
//the explosion from continuing in a given direction. The
//explosion will kill any rats on these tiles and will also
//destroy any other items currently placed on these tiles.
//Each bomb should display a count down: 4, 3, 2, 1.