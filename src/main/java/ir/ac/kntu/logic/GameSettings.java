package ir.ac.kntu.logic;

import ir.ac.kntu.objects.Cell;
import ir.ac.kntu.objects.Table;
import ir.ac.kntu.util.RandomHelper;

public class GameSettings {

    private final Cell startingCell1 = Table.getInstance().getStartingCell1();

    private final Cell startingCell2 = Table.getInstance().getStartingCell2();

    public static final int VIRUS_PER_LEVEL = 8;

    private static final GameSettings instance = new GameSettings();

    private int gameSpeed;

    private int gameLevel;

    private GameSettings(){

    }

    public static GameSettings getInstance() {
        return instance;
    }

    public double getRandomVirusRatio() {
        return RandomHelper.getInstance().getChance();
    }

    public int getGameSpeed() {
        return gameSpeed;
    }

    public void setGameSpeed(int gameSpeed) {
        this.gameSpeed = gameSpeed;
    }

    public void setGameLevel(int gameLevel) {
        this.gameLevel = gameLevel;
    }

    public int getGameLevel() {
        return gameLevel;
    }

}
