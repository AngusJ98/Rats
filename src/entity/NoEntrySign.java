package entity;

import gameHandler.Game;
import gameHandler.Pos;
import javafx.scene.image.Image;

public class NoEntrySign extends Item {
    public static final int MAX_HP = 4;
    private static Image noEntImg = new Image("file:resources/noEnt.png");
    private int hp;

    public NoEntrySign(Pos position) {
        super(noEntImg , position);
        hp = MAX_HP;
    }
	public NoEntrySign(Pos position, int hp) {
		super(noEntImg , position);
		this.hp = hp;
	}

    public void ratCollision(Rat target) { 	
		//invert the direction the rat is moving		
		Direction newDirection = target.getInverseMoveDirection();
		target.forceMove(1, newDirection);
		//lower my hp
		this.hp--;
		if (this.hp <= 0) {
			Game.ItemManager.killItem(this);
		}  
	}
	public void itemCollision(Item target) {}
	public void onPlacement() {}	
	public void tick() {}
}
