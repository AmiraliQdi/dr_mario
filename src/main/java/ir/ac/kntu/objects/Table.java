package ir.ac.kntu.objects;

import ir.ac.kntu.logic.GameSettings;
import ir.ac.kntu.objects.capsule.Capsule;

public class Table implements Runnable{

    private static final Table instance = new Table();

    private final Cell[][] board;

    private final int tableWith = 8;

    private final int tableLength = 16;

    private Table(){
        board = new Cell[tableWith][tableLength];
        for (int i = 0;i<tableWith;i++){
            for (int j = 0;j<16;j++){
                board[i][j] = new Cell(i,j);
            }
        }
    }

    public static Table getInstance() {
        return instance;
    }

    public void clearAll(){
        for (int x = 0;x<tableWith;x++){
            for (int y = 0;y<tableLength;y++){
                board[x][y].clearCell();
            }
        }
    }

    public Cell getStartingCell1(){
        return board[tableWith/2][0];
    }

    public Cell getStartingCell2(){
        return board[tableWith/2 +1][0];
    }

    public Cell getCell(int x,int y) {
        return board[x][y];
    }

    public void clearCell(int x,int y){
        board[x][y].clearCell();
    }

    public int getTableLength() {
        return tableLength;
    }

    public int getTableWith() {
        return tableWith;
    }

    public void printTable(){
        System.out.println();
        for (int i = 0;i< Table.instance.tableLength;i++){
            for (int j = 0;j<Table.getInstance().tableWith;j++){
                Cell cell = Table.getInstance().getCell(j,i);
                System.out.print(cell.print());
            }
            System.out.println();
        }
    }

    @Override
    public void run() {
        while (true){
            try {
                Thread.sleep(500);
            } catch (InterruptedException e){

            }
            System.out.println("========");
            printTable();
        }
        //TODO Table Thread
    }
}
