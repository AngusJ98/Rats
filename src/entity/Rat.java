package entity;

import gameHandler.Game;
import gameHandler.Game.*;
import gameHandler.Pos;
import javafx.scene.image.Image;

import java.util.ArrayList;

/**
 *
 <p> 1. File-name: Rat.java</p>
 <p> 2. Creation Date: (N/A) </p>
 <p> 3. Last modification date:</p>
 <p> 4. Purpose of the program: Rat Implementation</p>
 * @author Andrew
 */

/**
 * Types of directions rats can move in.
 * North, Eat, South, West
 */

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


	/**
	 *
	 * @return inverse move direction
	 */
	public Direction getInverseMoveDirection() {
		return Direction.values()[(moveDirection.ordinal()+2) % 4];
	}

	/**
	 *
	 * @return (Not Sure what this does)
	 */
	public boolean move() {	


		//   if moveDirection = null, pick a direction from directions enum at random
		if (moveDirection.equals(null)) {
			moveDirection = Direction.values()[((int)(Math.random() * 4))];
		}		

		if (Game.getTimeLeft() % (int)(5/this.moveSpeed) == 0) {

			//	  check if there is no path in the direction of travel, if this is the case turn right and try again. 
			//	  keep turning until a path is found (if all paths are blocked the rat will keep turning right indefinitely and it will be funny)

	
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
			ArrayList<Direction> availablePaths = new ArrayList<Direction>();
			//add directions to a list if they are passable
			if (TileManager.getPassableTile(getPosFromDir(moveDirection, this.pos))) {
				availablePaths.add(moveDirection);	
			}

			if (TileManager.getPassableTile(getPosFromDir(leftDir, this.pos))) {
				availablePaths.add(leftDir);	
			}
			if (TileManager.getPassableTile(getPosFromDir(rightDir, this.pos))) {
				availablePaths.add(rightDir);	
			}
			Direction chosenDirection = this.moveDirection;
			if (availablePaths.size() == 0) {	
				//if no paths ahead, turn around
				if (TileManager.getPassableTile(getPosFromDir(getInverseMoveDirection(), this.pos))) {
					chosenDirection = getInverseMoveDirection();
				} else {
					System.out.println("A rat got stuck and can't move");
				}	
			} else if (availablePaths.size() == 1) {
				//if only one path is available, take it
				chosenDirection = availablePaths.get(0);
			} else {
				//randomly chose a direction from the options available
				chosenDirection = availablePaths.get((int) (Math.random() * (availablePaths.size())));
			}
			moveDirection = chosenDirection;
			setPosition(getPosFromDir(moveDirection,pos));
			//    move one square
			/**   rats with a higher movespeed (baby rats or rats under the influence of speedtiles will loop and 
			 *    go through this process again so they do not end up inside walls) */				
			checkCurrentTile();	
		}
		return true;
	}

	/**
	 * Gets the new position as a result of the current positon and direction
	 *
	 * @param dir takes the direction to be looking towards
	 * @param pos takes the current coordinates
	 * @return returns the new coordinates
	 */
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

	/**
	 * Used by other entities to move rats in a different way.
	 * @param movesLeft takes how many moves are needed for moving
	 * @param direction takes direction to move in
	 * @return (?)
	 */
	public boolean forceMove(int movesLeft, Direction direction) {
		//forces the entity to move when it is not the rat's turn to move
		//used by noEntrySign
		moveDirection = direction;
		while (movesLeft > 0) {
			Pos northTile = new Pos(this.pos.x, this.pos.y - 1);
			Pos eastTile = new Pos(pos.x + 1, pos.y);
			Pos southTile = new Pos(pos.x, pos.y + 1);
			Pos westTile = new Pos(pos.x-1, pos.y);
			Pos[] directionTiles = {northTile, eastTile, southTile, westTile};
			setPosition(directionTiles[moveDirection.ordinal()]);
			movesLeft--;
		}
		return true; //Idk i just want to compile
	}

	/**
	 * Checks Current Tile
	 * Work in progress?
	 */
	public abstract void checkCurrentTile();

	/**
	 * 1st Constructor, creates an instance of a rat
	 * @param type takes in the type of rat
	 * @param pos takes in the position of the rat
	 */
	public Rat(RatTypes type, Pos pos) {
		super(new Image("File:resources/male.png"), EntityType.RAT, pos);
		setScore(0);
		setRatType(type);
		setMoveStatus(true);
		moveDirection = Direction.NORTH;
		switch (type) {						
		case BABY:
			setMateStatus(false);
			setMoveSpeed(2);
			this.image = new Image("File:resources/babyRat.png");
			break;
		case DEATH:
			setMateStatus(false);
			setMoveSpeed(1);
			this.image = new Image("File:resources/deathRat.png");
			break;
		case MALE:
			setMateStatus(true);
			setMoveSpeed(1);
			this.image = new Image("File:resources/maleRat.png");
			break;
		case FEMALE:
			setMateStatus(true);
			setMoveSpeed(1);
			this.image = new Image("File:resources/femaleRat.png");
			break;
		default:			
			setMateStatus(true);
			setMoveSpeed(1);
			this.image = new Image("File:resources/male.png");
			break;	
		}		
	}

	/**
	 * gets score
	 * @return score
	 */
	public int getScore() {
		return score;
	}

	/**
	 * set score
	 * @param score
	 */
	public void setScore(int score) {
		this.score = score;
	}
}

