package ir.ac.kntu.objects;

import ir.ac.kntu.game.GameSettings;

public class Cell {

    private int posX;

    private int posY;

    private CellObject cellObject;

    public Cell(int posX,int posY){
        this.posX = posX;
        this.posY = posY;
    }

    public boolean isEmpty(){
        if (cellObject == null){
            return true;
        } else {
            return false;
        }
    }

    public void setCellObject(CellObject cellObject) {
        this.cellObject = cellObject;
    }

    public CellObject getCellObject() {
        return cellObject;
    }

    public void clearCell(){
        cellObject = null;
    }

    public int getPosX() {
        return posX;
    }

    public int getPosY() {
        return posY;
    }
}
