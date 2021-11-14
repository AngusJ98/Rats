public class Game {
    private char[] map;
    private int timeLeft;
    private Board board;

    public Game(Char[] map, int timeLeft) {
        this.map = map;
        this.timeLeft = timeLeft;
    }

    private void loadBoard() {
        this.board = new Board(this.map);
    }

    //calls all object managers here
    private void tick() {

    }

    public Entity[] getEntities() {
        //don't think this method needs to exist
    }
}
