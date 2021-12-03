package entity;

import gameHandler.Game.*;
import javafx.scene.image.Image;

enum Direction {
	NORTH, EAST, SOUTH, WEST
}
public abstract class Rat extends Entity {
	protected boolean canMate;
	protected boolean canMove;
	protected RatTypes ratType;
	protected int moveSpeed;	
	protected int[] pos;
	protected Direction moveDirection;
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
	public RatTypes getRatType() {
		return ratType;
	}
	public void setRatType(RatTypes type) {
		this.ratType = type;
	}
	public int getMoveSpeed() {
		return moveSpeed;
	}
	public void setMoveSpeed(int moveSpeed) {
		this.moveSpeed = moveSpeed;
	}
	private void setPos(int[] pos) {
		this.pos = pos;
	}
	public boolean move() {	
		int movesLeft = moveSpeed;
		//   if moveDirection = null, pick a direction from directions enum at random
		if (moveDirection.equals(null)) {
			moveDirection = Direction.values()[((int)(Math.random() * 4))];
		}		
		while (movesLeft > 0) {
			//	  get adjacent tiles  (REQUIRES TILEMANAGER CLASS)
			int[] northTile = {pos[0], pos[1]-1}; //x, y, increasing number = further south/east on the board
			int[] eastTile = {pos[0]+1, pos[1]};
			int[] southTile = {pos[0], pos[1]+1};
			int[] westTile = {pos[0]-1, pos[1]};
			int[][] directionTiles = {northTile, eastTile, southTile, westTile};
			//	  check if there is no path in the direction of travel, if this is the case turn right and try again. 
			//	  keep turning until a path is found (if all paths are blocked the rat will keep turning right indefinitely and it will be funny)
			while (!TileManager.getPassableTile(directionTiles[moveDirection.ordinal()])) {
				if (moveDirection.ordinal() < 3) {
					moveDirection = Direction.values()[moveDirection.ordinal()+1];
				} else {
					moveDirection = Direction.NORTH;
				}
			}
			setPosition(directionTiles[moveDirection.ordinal()]);
			movesLeft --;
			//    move one square
			/**   rats with a higher movespeed (baby rats or rats on crack will loop and 
			 *    go through this process again so they do not end up inside walls) */				
			checkCurrentTile();	
		}
		return true;
	}
	public abstract void checkCurrentTile();
	public Rat(RatTypes type, Image image, int[] pos) {
		super(image, CollisionType.RAT, pos);
		setPos(pos);
		setRatType(type);
		setMoveStatus(true);
		switch (type) {						
		case BABY:
			setMateStatus(false);
			setMoveSpeed(2);
			break;
		case DEATH:
			setMateStatus(false);
			setMoveSpeed(1);
			break;	
		default:			
			setMateStatus(true);
			setMoveSpeed(1);
			break;	
		}		
	}

	public void kill() {
		RatManager.killSingleRat(this);
	}

}
