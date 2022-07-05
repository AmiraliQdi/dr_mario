package ir.ac.kntu.game;

import ir.ac.kntu.exeptions.OutOfBoard;
import ir.ac.kntu.objects.Cell;
import ir.ac.kntu.objects.Table;
import ir.ac.kntu.util.RandomHelper;

public class GameSettings {

    private final Cell startingCell = Table.getInstance().getStartingCell();

    private final int table_with = 8;

    private final int table_length = 16;

    private static final GameSettings instance = new GameSettings();

    private GameSettings(){

    }

    public static GameSettings getInstance() {
        return instance;
    }

    public int getTable_length() {
        return table_length;
    }

    public int getTable_with() {
        return table_with;
    }

    public Cell getStartingCell() {
        return startingCell;
    }

    public double getRandomVirusRatio() {
        return RandomHelper.getInstance().getChance();
    }
}
