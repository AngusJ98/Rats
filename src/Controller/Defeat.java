package Controller;

/**
 *
 <p> 1. File-name: Defeat.java</p>
 <p> 2. Creation Date: (N/A) </p>
 <p> 3. Last modification date:</p>
 <p> 4. Purpose of the program: Display defeat message</p>
 * @author Gus
 */

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
