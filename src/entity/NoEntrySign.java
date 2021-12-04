package entity;

import gameHandler.Pos;
import javafx.scene.image.Image;

public class NoEntrySign extends Item {
    public static final int MAX_HP = 4;
    private int hp;

    public NoEntrySign(Pos position) {
        super(new Image("file:resources/noEnt.png"), position);
        hp = MAX_HP;
    }

    public void ratCollision(Rat target) {
    	/*
		//invert the direction the rat is moving		
		Direction newDirection = target.getInverseMoveDirection();
		target.forceMove(1, newDirection);
		//lower my hp
		hp--;
		if (hp <= 0) {
			ItemManager.killItem(this);
		}

    	 */
	}
	public void itemCollision(Item target) {}
	public void onPlacement() {}	
	public void tick() {}
}
