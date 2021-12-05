package gameHandler;

import Controller.GameRenderer;
import entity.*;
import javafx.application.Platform;
import org.json.simple.parser.ParseException;
import tiles.Tile;

import java.io.IOException;
import java.lang.reflect.Array;
import java.util.*;

public class Game {	
	//this is a pretty static way of doing things, but it just works
	private static ArrayList<BasicRat> rats = new ArrayList<BasicRat>();
	private static String levelPath;
	private static GameRenderer runner;
	public static int score;

	private static Timer timer = new Timer();
	private static int timeLeft = -1;
	public Game() {

	}

	public static int getTimeLeft() {
		return timeLeft;
	}

	public static ArrayList<BasicRat> getRats() {
		return rats;
	}

	public static Timer getTimer() {
		return timer;
	}
	public static void setTimer(Timer timer) {
		Game.timer = timer;
	}
	public static void setRunner(GameRenderer runner) {
		Game.runner = runner;
	}
	public static void addScore(int addition) {
		Game.score += addition;
	}

	public void start() {
		Game.runner.redrawBoard(this.createCombinedEntityList());
		Game.score = 0;
		System.out.println(this.rats.size());
		System.out.println(Arrays.toString(this.createCombinedEntityList()));
		this.items = new ArrayList<>();
		this.startTimer();
    }
    private void startTimer() {
		Game.timer = new Timer();
		TimerTask task = new TimerTask() {
			@Override
			public void run() {
				RatManager.performRatActions();
				ItemManager.performItemActions();
				Platform.runLater(() -> runner.normalTickDrawing(createCombinedEntityList()));
				Game.checkVictory();
				timeLeft--;
			}
		};
		timer.scheduleAtFixedRate(task, 0, 100);
	}
	public static void checkVictory() {
		if (rats.size() == 0) {
			System.out.println("VICTORY!!");
			//TODO updatePlayerStats();
			Game.cleanUp();
			Platform.runLater(() -> Game.runner.victoryScreen());
		} else if (timeLeft <= 0) {
			System.out.println("DEFEAT :c");
			Game.cleanUp();
			Platform.runLater(() -> Game.runner.lossScreen());
		}
	}

	public static void cleanUp() {
		Game.timer.cancel();
		Game.timer = null;
		Game.items = new ArrayList<>(); //clear off old items
		Platform.runLater(() -> Game.runner.removeEntities());
	}
	private Entity[] createCombinedEntityList() {
		ArrayList<Entity> entities = new ArrayList<>();
		entities.addAll(Game.rats);
		entities.addAll(Game.items);
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
				Game.addScore(rat.getScore());
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
			ArrayList<BasicRat> ratsToKill = new ArrayList<>();
			for (BasicRat rat : rats) {
				rat.move();
				rat.ratActions();
				rat.updateImage();
				rat.updateScore();
				if (rat.getHP() <= 0) {
					ratsToKill.add(rat);
					//killSingleRat(rat);
				}	
			}
			//Have to do this to avoid concurrency modification error
			for (Rat toKill: ratsToKill) {
				killSingleRat(toKill);
			}
			for (DeathRat deathRat : deathRats) {
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
			ArrayList<Entity> entities = new ArrayList<>();
			entities.addAll(Game.rats);
			entities.addAll(Game.items);
			ArrayList<Entity> entitiesOnPos = new ArrayList<>();
			for (Entity e : entities) {
				if (e.getPosition().equals(pos)) {
					entitiesOnPos.add(e);
				}
			}
			return entitiesOnPos;
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
		public static Pos getPosFromDir(Direction dir, Pos pos){
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
	}
	
	
	private static ArrayList<Item> items = new ArrayList<Item>(); //should've made this an Arraylist before sorry
	public static class ItemManager {
	    private static ArrayList<Item> itemsToRemove = new ArrayList<>();
		private static ArrayList<Item> itemsToAdd = new ArrayList<>();
		public static void tryPlace(String itemString, Pos pos) {
			if (true /*TODO Stock check here*/) {
				Item placedItem = null;
				System.out.println(itemString);
				switch (itemString) {
					//TODO: call onPlacement() whenever an item is placed					
					case "bomb":
						placedItem = new Bomb(pos);
						items.add(placedItem);
						break;
					case "gas":
						placedItem = new Gas(pos);
						items.add(placedItem);
						break;
					case "sterile":
						placedItem = new Sterilization(pos);
						items.add(placedItem);
						break;
					case "noEnt":
						placedItem = new NoEntrySign(pos);
						items.add(placedItem);
						break;
					case "male":
						placedItem = new MaleGenderChange(pos);
						items.add(placedItem);
						break;
					case "female":
						placedItem = new FemaleGenderChange(pos);
						items.add(placedItem);
						break;
					case "deathRat":
						//not using placedItem here cause deathrat doesn't inherit from entity
						RatManager.deathRats.add(new DeathRat(pos));
						break;
				}
				if (!(placedItem == null)) {
					placedItem.onPlacement();
				}
			}


		}
		public static void addItem(Item item) {
			itemsToAdd.add(item);
		}	
		public static void killItem(Item item) {
			if (items.contains(item)) {
				itemsToRemove.add(item);
			}	
		}
		public static void performItemActions() {
			for (Item item : items) {
				item.tick();				
			}



			//remove items here to avoid concorrentModification exception
            for (Item item : itemsToRemove) {
                Game.items.remove(item);
            }
            itemsToRemove.clear();

            //add items at the end so they can be drawn next tick
			for (Item item : itemsToAdd) {
				Game.items.add(item);
			}
			itemsToAdd.clear();
		}	
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
		setUpLevelStats(gameObjects.getFourth());
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

	private void setUpLevelStats(HashMap<String, Integer> stats) {
		this.timeLeft = stats.get("timeLeft") * 10;
	}
}
