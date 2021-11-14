package application;
import java.util.ArrayList;

public class RatManager {
    private ArrayList<Rat> ratList = new ArrayList<>();

    public Rat getRat() {
        return ratList.get(0);
    }

    public void addRat(Rat rat) {
        ratList.add(rat);
    }

    public void ratActions() {
        for (Rat r : ratList) {
            r.ratActions();
        }
    }
}