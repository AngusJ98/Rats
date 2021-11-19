import javafx.scene.image.Image;
public class ExplosionPart extends Entity {
    private Image image;
    public ExplosionPart() {
        super(this.image, CollisionType.ITEM);
    }

    @Override
    void onCollision(Entity target) {
        switch (target.getCollisionGroup()) {
            case CollisionType.RAT:
                target.kill();
                break;
            case ITEM:
                target.destroy();
                break;
        }
    }
}