package application;
public class BasicRat extends Rat {
	private boolean isBaby;
	private Image image;
	private int timeToGrowth; 
	private int numChildren;
	private ratTypes gender;
	public ratTypes getGender() {
		return gender;
	}
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
	public BasicRat(ratTypes type) {
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
		super(type, image);
	}
}
