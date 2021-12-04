package Controller;

public class Victory {

    public void returnToMenu() {
        try {
            Main.changeScene(Main.getMenuScene());
        } catch (Exception e) {
            e.printStackTrace();
            System.exit(1);
        }
    }
}
