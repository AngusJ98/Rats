package application;
//We should maybe think about moving rats to their own package
package enum ratTypes {
	MALE, FEMALE, BABY, DEATH;
}
package enum Direction {
	NORTH, EAST, SOUTH, WEST
}
public abstract class Rat extends Entity{
	private boolean canMate;
	private boolean canMove;
	private boolean isDeathRat;
	private int moveSpeed;
	private Direction moveDirection;
	
	public boolean getMateStatus() {
		return canMate;
	}
	public void setMateStatus(boolean canMate) {
		this.canMate = canMate;
	}
	public boolean getMoveStatus() {
		return canMove;
	}
	public void setMoveStatus(boolean canMove) {
		this.canMove = canMove;
	}
	public boolean getIsDeathRat() {
		return isDeathRat;
	}
	public void setIsDeathRat(boolean isDeathRat) {
		this.isDeathRat = isDeathRat;
	}
	public int getMoveSpeed() {
		return moveSpeed;
	}
	public void setMoveSpeed(int moveSpeed) {
		this.moveSpeed = moveSpeed;
	}
	public boolean move() {
		
		int movesLeft = moveSpeed;
		//   if directionoftravel = null, pick a direction from directions enum at random
		if (moveDirection.equals(null)) {
			moveDirection = Direction.values()[((int)(Math.random() * 5))];
		}		
		while (movesLeft > 0) {
			//	  get adjacent tiles  (REQUIRES TILEMANAGER CLASS)
			//    move moveSpeed squares or until a wall is hit
			//    when there is no path in the direction of travel, turn left and try again. 
			//    keep turning until a path is found
		}
		return true;
	}

	public Rat(ratTypes type) {
		switch (type) {						
		case BABY:
			setMoveStatus(true);
			setMateStatus(true);
			setIsDeathRat(false);
			setMoveSpeed(2);
			break;
		case DEATH:
			setMoveStatus(true);
			setMateStatus(true);
			setIsDeathRat(true);
			setMoveSpeed(1);
			break;	
		default:
			setMoveStatus(true);
			setMateStatus(true);
			setIsDeathRat(false);
			setMoveSpeed(1);
			break;	
		}		
	}

	abstract ratActions() //what the rat does when it's action is called, calls move or whatever else it needs to do.
}
