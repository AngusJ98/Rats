package rats;
public abstract class Rat {

    protected int speed;
    protected boolean canMate;
    protected boolean canMove;
    
    Rat(int speed, boolean canMate, boolean canMove) {
        this.speed = speed;
        this.canMate = canMate;
        this.canMove = canMove;
    }

    //The actions a rat does each tick
    public abstract void ratActions();

    //Rats move along paths until they reach an intersection, then they choose a random path from there
    public void move() {

    }

}