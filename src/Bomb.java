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
        if () {
            target.kill
        };
    }
}

