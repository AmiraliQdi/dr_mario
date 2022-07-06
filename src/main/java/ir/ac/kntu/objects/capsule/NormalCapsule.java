package ir.ac.kntu.objects.capsule;

import ir.ac.kntu.exeptions.CellWasFull;
import ir.ac.kntu.logic.GameSettings;
import ir.ac.kntu.logic.Color;
import ir.ac.kntu.objects.Cell;
import ir.ac.kntu.objects.CellObjectType;
import ir.ac.kntu.objects.Table;
import ir.ac.kntu.util.CapsuleMove;

public class NormalCapsule extends Capsule {

    public NormalCapsule(){
        super();
        setRandomColors();
        syncWithCell(Table.getInstance().getStartingCell1(),Table.getInstance().getStartingCell2());
    }

    @Override
    public void activeStaticMode() {

    }

    private void setRandomColors(){
        double random = Math.random();
        if (random<0.3) {
            setHeadColor(Color.RED);
        } else if (random<0.6){
            setHeadColor(Color.BLUE);
        } else {
            setHeadColor(Color.YELLOW);
        }
        double random2 = Math.random();
        if (random2<0.3){
            setTailColor(Color.RED);
        } else if (random2<0.6){
            setTailColor(Color.BLUE);
        } else {
            setTailColor(Color.YELLOW);
        }
    }

    @Override
    public void syncWithCell(Cell head,Cell tail) {
        head.setCellObject(this);
        head.setCellObjectType(CellObjectType.NORMAL_CAPSULE_HEAD);
        head.setColor(getHeadColor());
        this.setHead(head);
        tail.setCellObject(this);
        tail.setCellObjectType(CellObjectType.NORMAL_CAPSULE_TAIL);
        tail.setColor(getTailColor());
        this.setTail(tail);
    }

    @Override
    public void move(MoveType moveType) {
        try {
            switch (moveType) {
                case A -> CapsuleMove.moveLeft(this);
                case D -> CapsuleMove.moveRight(this);
                case S -> CapsuleMove.moveDown(this);
                case V -> CapsuleMove.rotate(this);
            }
        } catch (CellWasFull e){
            e.getMessage();
        }
    }
}
