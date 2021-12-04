package entity;

import gameHandler.Game;
import gameHandler.Game.*;
import gameHandler.Pos;
import javafx.scene.image.Image;

enum Direction {
	NORTH, EAST, SOUTH, WEST;
}
public abstract class Rat extends Entity {
	protected boolean canMate;
	protected boolean canMove;
	protected RatTypes ratType;
	protected int moveSpeed;
	protected Direction moveDirection;
	protected int score;
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
	public Direction getMoveDirection() {
		return moveDirection;
	}	
	public Direction getInverseMoveDirection() {
		return Direction.values()[(moveDirection.ordinal()+2) % 4]
	}	
	
	public boolean move() {	
		int movesLeft = moveSpeed;
		//   if moveDirection = null, pick a direction from directions enum at random
		if (moveDirection.equals(null)) {
			moveDirection = Direction.values()[((int)(Math.random() * 4))];
		}		
		while (movesLeft > 0) {
	
			/*						GUS'S MOVEMENT CODE	
			if (!TileManager.getPassableTile(getPosFromDir(moveDirection, pos))) {
				boolean tryLeftFirst = Math.random() < 0.5;
				Direction leftDir = Direction.values()[((moveDirection.ordinal() + 3) % 4)];
				Direction rightDir = Direction.values()[((moveDirection.ordinal() + 1) % 4)];
				Direction backDir = Direction.values()[((moveDirection.ordinal() + 2) % 4)];

				if (tryLeftFirst) {
					if(TileManager.getPassableTile(getPosFromDir(leftDir, pos))){
						this.moveDirection = leftDir;
					} else if (TileManager.getPassableTile(getPosFromDir(rightDir, pos))) {
						this.moveDirection = rightDir;
					} else if (TileManager.getPassableTile(getPosFromDir(backDir, pos))) {
						this.moveDirection = backDir;
					}
				} else {
					if(TileManager.getPassableTile(getPosFromDir(rightDir, pos))){
						this.moveDirection = rightDir;
					} else if (TileManager.getPassableTile(getPosFromDir(leftDir, pos))) {
						this.moveDirection = leftDir;
					} else if (TileManager.getPassableTile(getPosFromDir(backDir, pos))) {
						this.moveDirection = backDir;
					}
				}
			}		
			*/
			/*while (!TileManager.getPassableTile(directionTiles[moveDirection.ordinal()])) {
				if (moveDirection.ordinal() < 3) {
					moveDirection = Direction.values()[moveDirection.ordinal()+1];
				} else {
					moveDirection = Direction.NORTH;
				}
			}*/
			//only move if front tile is empty
			
			//      ANDREW'S MOVEMENT CODE (partially stolen from gus)
			// check in front, left and right
			Direction leftDir = Direction.values()[((moveDirection.ordinal() + 3) % 4)];
			Direction rightDir = Direction.values()[((moveDirection.ordinal() + 1) % 4)];
			ArrayList<Direction>() availablePaths = new ArrayList<Direction>();
			//add directions to a list if they are passable
			if (TileManager.getPassableTile(getPosFromDir(moveDirection))) {
				availablePaths.add(moveDirection);	
			}
			if (TileManager.getPassableTile(getPosFromDir(leftDir))) {
				availablePaths.add(leftDir);	
			}
			if (TileManager.getPassableTile(getPosFromDir(rightDir))) {
				availablePaths.add(rightDir);	
			}
			Direction chosenDirection;
			if (availablePaths.size() == 0) {	
				//if no paths ahead, turn around
				if (TileManager.getPassableTile(getPosFromDir(getInverseMoveDirection()))) {
					chosenDirection = getInverseMoveDirection()
				} else {
					System.out.println("A rat got stuck and can't move")
				}	
			} else if (availablePaths.size == 1) {
				//if only one path is available, take it
				chosenDirection = availablePaths.get(0)
			} else {
				//randomly chose a direction from the options available
				chosenDirection = availablePaths(math.random() * availablePaths.size())
			}
			moveDirection = chosenDirection;
			setPosition(getPosFromDir(moveDirection,pos));
			movesLeft--;
			//    move one square
			/**   rats with a higher movespeed (baby rats or rats under the influence of speedtiles will loop and 
			 *    go through this process again so they do not end up inside walls) */				
			checkCurrentTile();	
		}
		return true;
	}
	public Pos getPosFromDir(Direction dir, Pos pos){
		Pos newPos;
		switch (dir) {
			case NORTH:
				newPos = new Pos(pos.x, pos.y+1);
				break;
			case EAST:
				newPos = new Pos(pos.x+1, pos.y);
				break;
			case SOUTH:
				newPos = new Pos(pos.x, pos.y-1);
				break;
			case WEST:
				newPos = new Pos(pos.x-1, pos.y);
				break;
			default:
				newPos = new Pos(0,0);
				System.out.println("enum error");
				break;
		}
		return newPos;
	}
	public boolean forceMove(int movesLeft, Direction direction) {
		//forces the entity to move when it is not the rat's turn to move
		//used by noEntrySign
		moveDirection = direction;
		while (movesLeft > 0) {
			Pos northTile = new Pos(this.pos.x, this.pos.y + 1); 
			Pos eastTile = new Pos(pos.x + 1, pos.y);
			Pos southTile = new Pos(pos.x, pos.y - 1);
			Pos westTile = new Pos(pos.x-1, pos.y);
			Pos[] directionTiles = {northTile, eastTile, southTile, westTile};
			setPosition(directionTiles[moveDirection.ordinal()]);
			movesLeft--;
		}
		return true; //Idk i just want to compile
	}
	
	
	public abstract void checkCurrentTile();
	public Rat(RatTypes type, Image image, Pos pos) {
		super(image, EntityType.RAT, pos);
		setScore(0);
		setRatType(type);
		setMoveStatus(true);
		moveDirection = Direction.NORTH;
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
	
	public int getScore() {
		return score;
	}

	public void setScore(int score) {
		this.score = score;
	}
}
}
