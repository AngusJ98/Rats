package gameHandler;

import Controller.GameRenderer;
import entity.*;
import javafx.application.Platform;
import org.json.simple.parser.ParseException;
import tiles.Tile;

import java.io.IOException;
import java.util.*;

public class Game {	
	//this is a pretty static way of doing things, but it just works
	private static ArrayList<BasicRat> rats = new ArrayList<BasicRat>();
	private static String levelPath;
	private static GameRenderer runner;
	public Game() {

	}

	public static void setRunner(GameRenderer runner) {
		Game.runner = runner;
	}

	public void start() {
		Game.runner.redrawBoard(this.createCombinedEntityList());
		System.out.println(this.rats.size());
		System.out.println(Arrays.toString(this.createCombinedEntityList()));
		this.startTimer();
    }

    private void startTimer() {
		Timer timer = new Timer();
		TimerTask task = new TimerTask() {
			@Override
			public void run() {
				RatManager.performRatActions();
				Platform.runLater(() -> runner.redrawEntities(createCombinedEntityList()));
			}
		};
		timer.scheduleAtFixedRate(task, 1000, 1000);
	}


	private Entity[] createCombinedEntityList() {
		ArrayList<Entity> entities = new ArrayList<>();
		entities.addAll(Game.rats);
		Entity[] entityArray = new Entity[entities.size()];
		return entities.toArray(entityArray);
	}


    public static class RatManager {
		private static ArrayList<DeathRat> deathRats = new ArrayList<DeathRat>();
		public static ArrayList<BasicRat> getRatsAtPos(Pos pos) {
			ArrayList<BasicRat> ratsAtPos = new ArrayList<BasicRat>();
			for (int i = 0; i < rats.size(); i++) {
				BasicRat currentRat = rats.get(i);
				if (currentRat.getPosition() == pos) {
					ratsAtPos.add(currentRat);
				}
			}
			return ratsAtPos;
		}
		public static boolean killBasicRatsAtPos(Pos pos) {
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
			for (BasicRat rat : rats) {
				rat.move();
				if (rat.getHP() <= 0) {
					killSingleRat(rat)
				}	
			}
			for (DeathRat deathRat : rats) {
				deathRat.move();
			}
		}		
	}
	private static HashMap<Pos, Tile> tiles = new HashMap<Pos, Tile>();
	public static class TileManager {
		private static int numTileWidth = 0;
		private static int numTileHeight = 0;
		//main purpose is to store tiles and allow entities to access them
		public static Tile getTile(Pos pos) {
			return tiles.get(pos);
		}
		public static boolean getPassableTile(Pos pos) {
			return tiles.get(pos).isPassable();
		}	
		public static ArrayList<Entity> getEntities(Pos pos) {
			return tiles.get(pos).getItems();
		}
		public static int getNumTileWidth() {
			return numTileWidth;
		}
		public static void setNumTileWidth(int numTileWidth) {
			TileManager.numTileWidth = numTileWidth;
		}

		public static int getNumTileHeight() {
			return numTileHeight;
		}

		public static void setNumTileHeight(int numTileHeight) {
			TileManager.numTileHeight = numTileHeight;
		}
	}
	private static HashMap<Pos, Item> items = new HashMap<Pos, Item>();
	public static class ItemManager {
		//h
	}
		
	public static void main(String[] args) {
		Game game = new Game();
		// Runner runner = new Runner(); Runner is not needed and will be loaded as part of the javafx stuff so no need to worry about that
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

	public static HashMap<Pos, Tile> getTiles() { //no
		return tiles; //bad
	} //use TileManager.GetTile();

	public void setUp() throws ParseException, IOException {
		//file reader class goes here, reads file and passes data to this method
        Tuple<BasicRat[], Entity[][], char[][], HashMap<String, Integer>,
            HashMap<String, HashMap<String, Integer>>, int[]>
            gameObjects = GameFileHandler.newGame(levelPath);
        constructTileMap(gameObjects.getThird());
		constructRatList(gameObjects.getFirst());
		otherShit();
	}
	public static String getLevelPath() {
		return levelPath;
	}

	public static void setLevelPath(String levelPath) {
		Game.levelPath = levelPath;
	}

	public void constructTileMap(char[][] map) {
		Game.tiles = new HashMap<>();//set to new hashmap so we don't accidentally keep old boards
		TileManager.numTileWidth = map[0].length;
		TileManager.numTileHeight = map.length;
		System.out.println(Arrays.deepToString(map));
		System.out.println(TileManager.getNumTileHeight()+ "-" + TileManager.getNumTileWidth());
		for (int y = 0; y < TileManager.numTileHeight; y++) {
			for (int x = 0; x < TileManager.numTileWidth; x++) {
				Tile tileToAdd = Tile.createTileFromLetter(map[y][x]);
				Pos pos = new Pos(x,y);
				Game.tiles.put(pos, tileToAdd);
			}
		}
    }

	public void constructRatList(BasicRat[] rats) {
		Game.rats = new ArrayList<BasicRat>(Arrays.asList(rats));
	}
}
