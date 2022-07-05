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
        syncWithCell(Table.getInstance().getStartingCell1());
    }

    @Override
    public void activeStaticMode() {
        //TODO enhancement capsule static mode
    }

    @Override
    public void syncWithCell(Cell cell){
        cell.setCellObject(this);
        cell.setCellObjectType(CellObjectType.ENHANCEMENT_CAPSULE_HEAD);
        this.setHead(cell);
        Cell tail;
        switch (getCapsuleStanding()){
            case VERTICAL : {
                tail = Table.getInstance().getCell(cell.getPosX(),cell.getPosY()+1);
                tail.setCellObject(this);
                tail.setCellObjectType(CellObjectType.ENHANCEMENT_CAPSULE_TAIL);
                setTail(tail);
            }
            case HORIZONTAL: {
                tail = Table.getInstance().getCell(cell.getPosX()+1,cell.getPosY());
                tail.setCellObject(this);
                tail.setCellObjectType(CellObjectType.ENHANCEMENT_CAPSULE_TAIL);
                setTail(tail);
            }
        }
    }

    @Override
    public void move(MoveType moveType) {
        try {
            switch (moveType) {
                case A -> CapsuleMove.moveLeft(this);
                case D -> CapsuleMove.moveRight(this);
                case S -> CapsuleMove.moveDown(this);
                case SPACE -> CapsuleMove.rotate(this);
            }
        } catch (CellWasFull e){
            e.getMessage();
        }
    }
}
