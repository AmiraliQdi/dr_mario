package ir.ac.kntu.objects;

import ir.ac.kntu.logic.Color;
import ir.ac.kntu.objects.capsule.MoveType;

public class Virus implements CellObject{

    private Color color;

    private Cell cell;

    private int id;

    private static int ID = 0;

    public Virus(){
        id = ID;
        ID++;
        setRandomColor();
    }

    private void setRandomColor(){
        double random = Math.random();
        if (random < 0.3) {
            color = Color.RED;
        } else if (random < 0.6) {
            color = Color.BLUE;
        } else {
            color = Color.YELLOW;
        }
    }

    public Color getColor() {
        return color;
    }

    public void setColor(Color color) {
        this.color = color;
    }

    public Cell getCell() {
        return cell;
    }

    public void setCell(Cell cell) {
        this.cell = cell;
    }

    @Override
    public void syncWithCell(Cell cell) {
        this.cell = cell;
        cell.setCellObject(this);
        cell.setCellObjectType(CellObjectType.VIRUS);
    }

    @Override
    public void move(MoveType moveType) {

    }
}
