import java.util.ArrayList;

public class RatManager implements Manager{
    private ArrayList<Rat> ratList = new ArrayList<>();
    public Rat getRat() {
        return ratList.get(0);
    }

    public void addRat(Rat rat) {
        ratList.add(rat);
    }
    public void removeRat(Rat rat) {
        this.ratList.remove(rat);
    }
    public void run() {
        for (Rat r : ratList) {
            r.ratActions();
        }
    }
}
