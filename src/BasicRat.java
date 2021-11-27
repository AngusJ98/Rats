import javafx.scene.image.Image;

public class BasicRat extends Rat {
	private boolean isBaby;
	private boolean canMate;
	private boolean canMove;
	private Image image;
	private int timeToGrowth;
	private int timeToBirth;
	private int numChildren;
	private int moveSpeed;
	private int hp;
	private int[] position;
	private ratTypes gender;
	public ratTypes getGender() {
		return gender;
	}
	public void ratActions() {}
    public void onRatCollision() {}

    void kill() {

    }

    public void onCollision(Entity entity) {}
	public void setGender(ratTypes gender) {
		this.gender = gender;
	}
	public boolean isBaby() {
		return isBaby;
	}
	private void setIsBaby(boolean isBaby) {
		this.isBaby = isBaby;
	}
	private int getTimeToGrowth() {
		return timeToGrowth;
	}
	private void setTimeToGrowth(int timeToGrowth) {
		this.timeToGrowth = timeToGrowth;
	}
	private void setNumChildren(int numChildren) {
		this.numChildren = numChildren;
	}
	public int getNumChildren() {
		return numChildren;
	}
	public BasicRat(ratTypes type, Image image) {
        super(type, image);
		switch (type) {
		case BABY:
			int minGrowthTime = 1000;
			int growthMultiplier = 4000;
			timeToGrowth = (int) (Math.random() * growthMultiplier) + minGrowthTime; //ms to growth (min 1000, max 5000)
			setTimeToGrowth(timeToGrowth);
			setIsBaby(true);
			break;
		case FEMALE:
			setIsBaby(false);
			setGender(ratTypes.FEMALE);
			setNumChildren(0);
			break;
		default:
			setIsBaby(false);
			setGender(ratTypes.MALE);
			break;
		}
	}

	public BasicRat(ratTypes gender, boolean isBaby, boolean canMate,
                    boolean canMove, int moveSpeed, int timeToGrowth,
                    int numChildren, int timeToBirth, int hp, int[] position,
                    Image image) {
	    super(gender, image);
	    this.gender = gender;
	    this.isBaby = isBaby;
	    this.canMate = canMate;
	    this.canMove = canMove;
	    this.moveSpeed = moveSpeed;
	    this.timeToGrowth = timeToGrowth;
	    this.numChildren = numChildren;
	    this.timeToBirth = timeToBirth;
	    this.hp = hp;
	    this.position = position;
    }
}
