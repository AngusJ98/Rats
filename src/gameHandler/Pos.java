package gameHandler;

import java.util.Objects;

/**
 *
 <p> 1. File-name: Pos.java</p>
 <p> 2. Creation Date: 17/11/21 </p>
 <p> 3. Last modification date: 05/12/21 </p>
 <p> 4. Purpose of the program: Makes a position on the map for entitys</p>
 * @author Gus
 */

public class Pos {
    public int x;
    public int y;
    public Pos(int x, int y) {
        this.x = x;
        this.y = y;
    }


    /**
     * method to check if position is the same on game and object
     * @param o: object
     * @return the x and y postions
     */
    public boolean equals(Object o) {
        Pos p = (Pos) o;
        return this.x == p.x && this.y == p.y;
    }

    /**
     * method to calculate the hash value
     * @return the hash value
     */

    public int hashCode() {
        int hash = 17;
        hash = hash * 31 + x;
        hash = hash * 31 + y;
        return hash;
    }

    /**
     * string method for checking the positions
     * @return a string of the x and y positions
     */

    @Override
    public String toString() {
        return "Pos{" +
                "x=" + x +
                ", y=" + y +
                '}';
    }
}
