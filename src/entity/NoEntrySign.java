package entity;

import gameHandler.Pos;
import javafx.scene.image.Image;

public class NoEntrySign extends Entity {
    public static final int MAX_HP = 4;
    private int hp;

    public NoEntrySign(Pos position) {
        super(new Image(""), CollisionType.ITEM, position);
        hp = MAX_HP;
    }

    public NoEntrySign(Pos position, int currentHp) {
        super(new Image(""), CollisionType.ITEM, position);
        hp = currentHp;
    }

    @Override
    public void onCollision(Entity t) {

    }
}
