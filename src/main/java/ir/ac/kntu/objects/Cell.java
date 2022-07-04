package ir.ac.kntu.objects;

public class Cell {

    private int posX;

    private int posY;

    private CellObject cellObject;

    public Cell(int posX,int posY){
        this.posX = posX;
        this.posY = posY;
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
