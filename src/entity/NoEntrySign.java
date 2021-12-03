package entity;

import gameHandler.Pos;
import javafx.scene.image.Image;

public class NoEntrySign extends Item {//?? acts like a tile so maybe it should inherit from tile instead 
    public static final int MAX_HP = 4;
    private int hp;

    public NoEntrySign(Pos position) {
        super(new Image(""), EntityType.ITEM, position);
        hp = MAX_HP;
    }

    public NoEntrySign(Pos position, int currentHp) {
        super(new Image(""), EntityType.ITEM, position);
        hp = currentHp;
    }
	
	@Override
    public void ratCollision(Rat target) {}
	public void itemCollision(Item target) {}
}
