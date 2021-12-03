package gameHandler;

import java.util.Objects;

public class Position {
    public int x;
    public int y;
    public Position(int x, int y) {
        this.x = x;
        this.y = y;
    }

    public boolean equals(Object o) {
        Position p = (Position) o;
        return this.x == p.x && this.y == p.y;
    }

    public int hashCode() {
        return Objects.hash(x,y);
    }
}
