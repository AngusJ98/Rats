package application;

import application.Game.TileManager;
import javafx.scene.image.Image;

enum ratTypes {
	MALE, FEMALE, BABY, DEATH;
}
enum Direction {
	NORTH, EAST, SOUTH, WEST
}
public abstract class Rat extends Entity {
	private boolean canMate;
	private boolean canMove;
	private ratTypes ratType;
	private int moveSpeed;	
	private int[] pos;
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
	public ratTypes getRatType() {
		return ratType;
	}
	public void setRatType(ratTypes type) {
		this.ratType = type;
	}
	public int getMoveSpeed() {
		return moveSpeed;
	}
	public void setMoveSpeed(int moveSpeed) {
		this.moveSpeed = moveSpeed;
	}
	public int[] getPos() {
		return pos;
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
			setPos(directionTiles[moveDirection.ordinal()]);
			movesLeft --;
			//    move one square
			/**   rats with a higher movespeed (baby rats or rats on crack will loop and 
			 *    go through this process again so they do not end up inside walls) */		
		}
		return true;
	}

	public Rat(ratTypes type, Image image, int[] pos) {
		super(image);
		setPos(pos);
		setRatType(type);
		switch (type) {						
		case BABY:
			setMoveStatus(true);
			setMateStatus(true);
			setMoveSpeed(2);
			break;
		case DEATH:
			setMoveStatus(true);
			setMateStatus(true);
			setMoveSpeed(1);
			break;	
		default:
			setMoveStatus(true);
			setMateStatus(true);
			setMoveSpeed(1);
			break;	
		}		
	}
	
}
