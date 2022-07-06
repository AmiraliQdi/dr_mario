package ir.ac.kntu.objects.capsule;

import ir.ac.kntu.exeptions.CellWasFull;
import ir.ac.kntu.logic.GameSettings;
import ir.ac.kntu.objects.Cell;
import ir.ac.kntu.objects.CellObjectType;
import ir.ac.kntu.objects.Table;
import ir.ac.kntu.util.CapsuleMove;

public class EnhancementCapsule extends Capsule {

    public EnhancementCapsule(){
        super();
        syncWithCell(Table.getInstance().getStartingCell1(),Table.getInstance().getStartingCell2());
    }

    @Override
    public void activeStaticMode() {
        //TODO enhancement capsule static mode
    }

    @Override
    public void syncWithCell(Cell head,Cell tail){
        head.setCellObject(this);
        head.setCellObjectType(CellObjectType.ENHANCEMENT_CAPSULE_HEAD);
        head.setColor(getHeadColor());
        this.setHead(head);
        tail.setCellObject(this);
        tail.setCellObjectType(CellObjectType.ENHANCEMENT_CAPSULE_TAIL);
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
