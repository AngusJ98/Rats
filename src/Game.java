import entity.Rat;
import tiles.Tile;

import java.util.ArrayList;

public class Game {
    private char[][] map;
    private int timeLeft;
    private Board board;

    public static class TileManager {
        private Tile[][] board;


    }

    public static class RatManager {
        private ArrayList<Rat> rats = new ArrayList<>();
        private Rat[][][] ratsByPosition;

        private void indexRatsByPosition() {
            for (Rat rat : this.rats) {
                int[] pos = rat.getPos();

            }
        }
    }

}
