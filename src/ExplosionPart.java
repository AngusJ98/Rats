public class ExplosionPart extends Entity {
    onCollision(Entity target) {
        switch (target.getCollisionGroup) {
            case RAT:
                target.kill;
                break;
            case Item:
                target.destroy;
                break;
            case None:
                break;
            default:
                break
        }
    }
}