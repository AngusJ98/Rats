package Controller;

public class Defeat {
    public void returnToMenu() {
        try {
            Main.changeScene(Main.getMenuScene());
        } catch (Exception e) {
            e.printStackTrace();
            System.exit(1);
        }
    }
}
