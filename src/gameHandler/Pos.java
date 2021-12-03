package gameHandler;

import java.util.Objects;

public class Pos {
    public int x;
    public int y;
    public Pos(int x, int y) {
        this.x = x;
        this.y = y;
    }

    public boolean equals(Object o) {
        Pos p = (Pos) o;
        return this.x == p.x && this.y == p.y;
    }

    public int hashCode() {
        return Objects.hash(x,y);
    }
}
