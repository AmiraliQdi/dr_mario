package ir.ac.kntu.objects;

import ir.ac.kntu.logic.Color;
import ir.ac.kntu.objects.capsule.Capsule;
import ir.ac.kntu.objects.capsule.MoveType;

public class HalfCapsule implements CellObject{

    private Color color;

    private Cell cell;

    public HalfCapsule(Capsule capsule,int i){
        if (i == 0) {
            syncWithCell(capsule.getHead(),null);
            color = capsule.getHeadColor();
        } else {
            syncWithCell(capsule.getTail(),null);
            color = capsule.getTailColor();
        }
    }

    public Cell getCell() {
        return cell;
    }

    public void setCell(Cell cell) {
        this.cell = cell;
    }

    public Color getColor() {
        return color;
    }

    public void setColor(Color color) {
        this.color = color;
    }

    @Override
    public void syncWithCell(Cell head,Cell tail) {
        this.cell = head;
        head.setCellObject(this);
        head.setColor(color);
        head.setCellObjectType(CellObjectType.HALF_CAPSULE);
    }

    @Override
    public void move(MoveType moveType) {

    }

}
