package application;

public class Game {
    private Char[] map;
    private int timeLeft;
    private Board board;

    public Game(Char[] map, int timeLeft) {
        this.map = map;
        this.timeLeft = timeLeft;
    }

    private loadBoard() {
        this.board = new Board(this.map)
    }

    //calls all object managers here
    private tick() {

    }

    public Entity[] getEntities() {
        
    }
}