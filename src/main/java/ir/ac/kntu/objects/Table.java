package ir.ac.kntu.objects;

import ir.ac.kntu.logic.CellStack;
import ir.ac.kntu.logic.Game;
import ir.ac.kntu.logic.GameSettings;
import ir.ac.kntu.objects.capsule.Capsule;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

import java.util.ArrayList;
import java.util.Queue;
import java.util.Stack;

public class Table implements Runnable{

    private static final Table instance = new Table();

    private final Cell[][] board;

    private final int tableWith = 8;

    private final int tableLength = 16;

    private ImageView[][] imageViews;

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

    public void makeImageViews(){
        imageViews = new ImageView[Table.getInstance().getTableWith()][Table.getInstance().getTableLength()];
        for (int i = 0;i < tableWith;i++){
            for (int j = 0;j< tableLength;j++){
                imageViews[i][j] = new ImageView("images/table_images/null.png");
            }
        }
    }

    public void setImageViews(Image[][] images){
        for (int i = 0;i < tableWith;i++){
            for (int j = 0;j< tableLength;j++){
                imageViews[i][j].setImage(images[i][j]);
            }
        }
    }

    public ImageView[][] getImageViews() {
        return imageViews;
    }

    @Override
    public void run() {
        while (true) {
            try {
                Thread.sleep(GameSettings.getInstance().getGameSpeed()* 1000L);
            } catch (InterruptedException e){

            }
            checkForSameColors();
        }
    }

    private void checkForSameColors(){
        for (int i = 0;i < tableWith;i++){
            CellStack cellStack = new CellStack();
            for (int j = 0;j<tableLength;j++){
                cellStack.addToStack(Table.getInstance().getCell(i,j));
                if (cellStack.isSameColorStack()){
                    System.out.println("Same color in row");
                    sameColorStack(cellStack.getStack());
                }
            }
            cellStack.clearStack();
        }
        for (int j = 0;j < tableLength;j++){
            CellStack cellStack = new CellStack();
            for (int i = 0;i<tableWith;i++){
                cellStack.addToStack(Table.getInstance().getCell(i,j));
                if (cellStack.isSameColorStack()){
                    System.out.println("Same color in collemn");
                    sameColorStack(cellStack.getStack());
                }
            }
            cellStack.clearStack();
        }
    }


    private void sameColorStack(ArrayList<Cell> cells){
        Game.getInstance().getGameSet().getMovingCapsule().setStaticCapsule(true);
        //TODO animation clearing cell
        int virusCount = 0;
        int score = 0;
        for (Cell cell : cells) {
            if ((cell.getCellObjectType() == CellObjectType.NORMAL_CAPSULE_TAIL || cell.getCellObjectType() == CellObjectType.NORMAL_CAPSULE_HEAD) ||
                    cell.getCellObjectType() == CellObjectType.ENHANCEMENT_CAPSULE_TAIL || cell.getCellObjectType() == CellObjectType.ENHANCEMENT_CAPSULE_HEAD) {
                Capsule capsule = (Capsule) cell.getCellObject();
                if (cell.equals(capsule.getHead())){
                    HalfCapsule halfCapsule = new HalfCapsule(capsule,1);
                } else {
                    HalfCapsule halfCapsule = new HalfCapsule(capsule,0);
                }
                score+=2;
            } else if (cell.getCellObjectType() == CellObjectType.VIRUS){
                virusCount++;
                score+=4;
            } else if (cell.getCellObjectType() == CellObjectType.HALF_CAPSULE) {
                score+=2;
            }
            cell.clearCell();
            Game.getInstance().getGameSet().addScore(score);
            Game.getInstance().getGameSet().removeViruses(virusCount);
        }
    }
}
