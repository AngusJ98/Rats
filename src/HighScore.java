import java.io.*;
import java.security.*;
import java.util.Hashtable;

public class HighScore
{
    private String gameName;
    private File highScoreFile;

    public HighScore(String gameName)
    {
        this.gameName = gameName;

        AccessController.doPrivileged(new PrivilegedAction() {
            public Object run() {
                String path =
                        System.getProperty("user.home") +
                                File.separator +
                                ".highscore";

                highScoreFile = new File(path);
                return null;
            }
        });
    }

    public void setHighScore(final int score)
            throws IOException
    {
        //check permission first
        SecurityManager sm = System.getSecurityManager();
        if (sm != null) {
            sm.checkPermission(new HighScorePermission(gameName));
        }

        // need a doPrivileged block to manipulate the file
        try {
            AccessController.doPrivileged(new PrivilegedExceptionAction() {
                public Object run() throws IOException {
                    Hashtable scores = null;
                    // try to open the existing file. Should have a locking
                    // protocol (could use File.createNewFile).
                    try {
                        FileInputStream fis =
                                new FileInputStream(highScoreFile);
                        ObjectInputStream ois = new ObjectInputStream(fis);
                        scores = (Hashtable) ois.readObject();
                    } catch (Exception e) {
                        // ignore, try and create new file
                    }

                    // if scores is null, create a new hashtable
                    if (scores == null)
                        scores = new Hashtable(13);

                    // update the score and save out the new high score
                    scores.put(gameName, new Integer(score));
                    FileOutputStream fos = new FileOutputStream(highScoreFile);
                    ObjectOutputStream oos = new ObjectOutputStream(fos);
                    oos.writeObject(scores);
                    oos.close();
                    return null;
                }
            });
        } catch (PrivilegedActionException pae) {
            throw (IOException) pae.getException();
        }
    }

    /**
     * get the high score. return -1 if it hasn't been set.
     *
     */
    public int getHighScore()
            throws IOException, ClassNotFoundException
    {
        //check permission first
        SecurityManager sm = System.getSecurityManager();
        if (sm != null) {
            sm.checkPermission(new HighScorePermission(gameName));
        }

        Integer score = null;

        // need a doPrivileged block to manipulate the file
        try {
            score = (Integer) AccessController.doPrivileged(
                    new PrivilegedExceptionAction() {
                        public Object run()
                                throws IOException, ClassNotFoundException
                        {
                            Hashtable scores = null;
                            // try to open the existing file. Should have a locking
                            // protocol (could use File.createNewFile).
                            FileInputStream fis =
                                    new FileInputStream(highScoreFile);
                            ObjectInputStream ois = new ObjectInputStream(fis);
                            scores = (Hashtable) ois.readObject();

                            // get the high score out
                            return scores.get(gameName);
                        }
                    });
        } catch (PrivilegedActionException pae) {
            Exception e = pae.getException();
            if (e instanceof IOException)
                throw (IOException) e;
            else
                throw (ClassNotFoundException) e;
        }
        if (score == null)
            return -1;
        else
            return score.intValue();
    }



    public static void main(String args[])
            throws Exception
    {
        HighScore hs = new HighScore(args[1]);
        if (args[0].equals("set")) {
            hs.setHighScore(Integer.parseInt(args[2]));
        } else {
            System.out.println("score = "+ hs.getHighScore());
        }
    }
}