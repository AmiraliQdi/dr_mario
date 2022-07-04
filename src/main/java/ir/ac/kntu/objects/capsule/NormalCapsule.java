package ir.ac.kntu.objects.capsule;

import ir.ac.kntu.exeptions.CellWasFull;
import ir.ac.kntu.exeptions.OutOfBoard;
import ir.ac.kntu.game.GameSettings;
import ir.ac.kntu.logic.Color;
import ir.ac.kntu.objects.Cell;
import ir.ac.kntu.objects.Table;
import ir.ac.kntu.util.CapsuleMove;

public class NormalCapsule extends Capsule {

    public NormalCapsule(){
        super();
        setRandomColors();
        syncWithCell(GameSettings.getInstance().getStartingCell());
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
    public void syncWithCell(Cell cell) {
        cell.setCellObject(this);
        this.setHead(cell);
        Cell tail;
        switch (getCapsuleStanding()){
            case VERTICAL : {
                tail = Table.getInstance().getCell(cell.getPosX(),cell.getPosY()+1);
                tail.setCellObject(this);
                setTail(tail);
            }
            case HORIZONTAL: {
                tail = Table.getInstance().getCell(cell.getPosX()+1,cell.getPosY());
                tail.setCellObject(this);
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
