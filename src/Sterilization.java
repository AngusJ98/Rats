public class Sterilization extends Entity{
    private int range;

    public Sterilization() {
        //Constructer
    }

    //public (not sure what data type to put here) sterilize() {


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
        target.setMateStatus(false);
    }
}

