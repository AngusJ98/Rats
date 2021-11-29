package entity;

import javafx.scene.image.Image;
public class ExplosionPart extends Entity {
    private Image image;
    public ExplosionPart(Image image) {
        super(image, CollisionType.ITEM);
    }

    @Override
    public void onCollision(Entity target) {
        switch (target.getCollisionGroup()) {
            case RAT:
                ratCollision((Rat)target);
                break;
            case ITEM:
                itemCollision(target);
                break;
        }
    }

    private void ratCollision(Rat target) {
        // Could potentially use a damage/destroy method for entity
        // that's then used on both rats and items? I guess killing a rat and
        // destroying an item are pretty similar.
        target.kill();
    }

    private void itemCollision(Entity target) {
        // Guessing we'll need a damage/destroy method in entity.Entity maybe?
    }
}