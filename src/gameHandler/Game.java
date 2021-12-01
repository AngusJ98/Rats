package gameHandler;

import Controller.Runner;
import entity.*;
import org.json.simple.parser.ParseException;
import tiles.Tile;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;

public class Game {	
	//this is a pretty static way of doing things, but it's very functional
	private static ArrayList<BasicRat> rats = new ArrayList<BasicRat>();
	private static String levelPath;
	private Runner runner;

	public Game() {
	    runner = new Runner();
    }

    public void start() {
    }


    public static class RatManager {
		private static ArrayList<DeathRat> deathRats = new ArrayList<DeathRat>();
		public static ArrayList<BasicRat> getRatsAtPos(int[] pos) {	
			ArrayList<BasicRat> ratsAtPos = new ArrayList<BasicRat>();
			for (int i = 0; i < rats.size(); i++) {
				BasicRat currentRat = rats.get(i);
				if (currentRat.getPosition() == pos) {
					ratsAtPos.add(currentRat);
				}
			}
			return ratsAtPos;
		}
		public static boolean killBasicRatsAtPos(int[] pos) {
			ArrayList<BasicRat> ratsToKill = getRatsAtPos(pos);
			if (ratsToKill!= null) {
				rats.removeAll(ratsToKill); //currently kills all basicrats on a square
				return true;
			}
			return false;
		}		
		public static boolean killSingleRat(Rat rat) { //so i made this method for when we need to kill individual rats
			if (rats.contains(rat)) {
				rats.remove(rat);
				return true;
			} else if (deathRats.contains(rat)) {
				deathRats.remove(rat);
				return true;
			} 
			System.out.println("Tried to kill a rat that did not exist");
			return false;			
		}
		public static void killRatArray(ArrayList<BasicRat> ratsToKill) { //this one kills all basicrats in the array it's passed
			rats.removeAll(ratsToKill);
		}
		public static void performRatActions() {
			for (int i = 0; i < rats.size(); i++) {
				//do rat shit
				//draw rats in list
				//move rats in list
				//check if rats in list can mate
				//check if rats in list should die
				//check if bay rats should grow up
			}
			for (int i = 0; i < deathRats.size(); i++) {
				//do metal rat shit
				//draw death rat
				//move
				//process rat kill requests
			}
		}		
	}
	private static HashMap<int[], Tile> tiles = new HashMap<int[], Tile>();
	public static class TileManager {
		//main purpose is to store tiles and allow entities to access them
		public static Tile getTile(int[] pos) {
			return tiles.get(pos);
		}
		public static boolean getPassableTile(int[] pos) {
			return tiles.get(pos).isPassable();
		}	
		public static ArrayList<Entity> getEntities(int[] pos) {
			return tiles.get(pos).getItems();
		}
	}
	public static void main(String[] args) {
		Game game = new Game();
		Runner runner = new Runner();
        try {
            game.setUp();
        } catch (ParseException e) {
            //handle here
            e.printStackTrace();
        } catch (IOException e) {
            //handle here
            e.printStackTrace();
        }
    }
	public void setUp() throws ParseException, IOException {
		//file reader class goes here, reads file and passes data to this method
        Tuple<BasicRat[], Entity[][], char[][], int[], int[], int[]>
            gameObjects = GameFileHandler.newGame(levelPath);
        constructTileMap(gameObjects.getThird());
		constructRatList();
		otherShit();
	}
	public static String getLevelPath() {
		return levelPath;
	}

	public static void setLevelPath(String levelPath) {
		Game.levelPath = levelPath;
	}
	public void tick() {

	}
	public void constructTileMap(char[][] map) {
        runner.createBoardFromChar(map);

    }
	public void constructRatList() {}	
	public void otherShit() {}
}
