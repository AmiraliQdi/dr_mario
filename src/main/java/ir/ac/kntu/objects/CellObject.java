package ir.ac.kntu.objects;

import ir.ac.kntu.exeptions.OutOfBoard;
import ir.ac.kntu.objects.capsule.MoveType;

public interface CellObject {

    public void syncWithCell(Cell cell) throws OutOfBoard;

    public void move(MoveType moveType);
}
