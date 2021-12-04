package entity;

import gameHandler.Game;
import gameHandler.Pos;
import javafx.scene.image.Image;

public class Poison extends Item{
    private static final int DEFAULT_TIME = 4000;
    private static final int RANGE = 1;
    private int timer;

    public Poison(Pos position) {
        super(new Image(""), position);
        timer = DEFAULT_TIME;
    }

    public Poison(Pos position, int timeLeft) {
        super(new Image(""), position);
        timer = timeLeft;
    }

	@Override
    public void onCollision(){}
    private void ratCollision(Rat target) {
        Game.RatManager.killSingleRat(target);        //Still needs the part where the poison is used up and removed from the path.
    }
	public void itemCollision(Item target) {}
	public void onPlacement() {}	
	public void tick() {}
}


//What is mentioned in the Spec for entity.Poison:
//Once placed, poison will remain on the path until a rat
//runs into it. Once a rat runs into it the rat is killed and
//the poison is used up and removed from the path.