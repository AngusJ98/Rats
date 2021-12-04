package highscore;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Scanner;


/**
 *
 * @author dylan
 */
public class HighScore {
    public static void main(String[] args) {
        Scanner file = null;
        ArrayList<Integer> list = new ArrayList<Integer>();

        try {
            file = new Scanner(new File("profile.txt"));
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }

        while(file.hasNext()){
            if (file.hasNextInt()) list.add(file.nextInt());
            else file.next();
        }

        Collections.sort(list);
        Collections.reverse(list);
        for (Integer i: list) System.out.println(i);

    }
}
