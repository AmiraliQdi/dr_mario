package ir.ac.kntu.objects;

import ir.ac.kntu.exeptions.OutOfBoard;
import ir.ac.kntu.game.GameSettings;

public class Table {

    private static final Table instance = new Table();

    private final Cell[][] board =  new Cell[GameSettings.getInstance().getTable_with()][GameSettings.getInstance().getTable_length()];

    private Table(){
    }

    public static Table getInstance() {
        return instance;
    }

    public void clearCells(){
        for (int x = 0;x<GameSettings.getInstance().getTable_with();x++){
            for (int y = 0;y<GameSettings.getInstance().getTable_length();y++){
                board[x][y].clearCell();
            }
        }
    }

    public Cell getStartingCell(){
        return board[GameSettings.getInstance().getTable_with()/2 + 1][0];
    }

    public Cell getCell(int x,int y) throws OutOfBoard {
        if ((x < 0 || x > GameSettings.getInstance().getTable_with())) {
            if ( x < 0){
                throw new OutOfBoard(-1);
            } else {
                throw new OutOfBoard(1);
            }
        } else if ((y < 0 || y > GameSettings.getInstance().getTable_length())) {
            if (y < 0){
                throw new OutOfBoard(-2);
            } else {
                throw new OutOfBoard(2);
            }
        }
        return board[x][y];
    }

    public void clearCell(int x,int y){
        board[x][y].clearCell();
    }
}
