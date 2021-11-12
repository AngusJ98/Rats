package application;
public class BasicRat extends Rat {
	private boolean isBaby;
	private int timeToGrowth; 
	private int numChildren;
	private ratTypes gender;
	public BasicRat(ratTypes type) {
		super(type);
		switch (type) {
		case BABY:
			break;
		case FEMALE:
			break;
		default:
			break;
		}			
	}
}
