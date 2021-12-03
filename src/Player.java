public class Player{

    private int score;
    private String playerName;
    private int maxLevelUnlocked;

    Player(String playerName, int score, int maxLevelUnlocked) {
        this.playerName = playerName;
        this.score = score;
        this.maxLevelUnlocked = maxLevelUnlocked;
    }

    public String toString() {
        return playerName + " " + score + " " + maxLevelUnlocked;
    }

    public int getScore() {
        return score;
    }

    public int getMaxLevelUnlocked() {
        return maxLevelUnlocked;
    }

}