package ir.ac.kntu.util;

import ir.ac.kntu.exeptions.CellWasFull;
import ir.ac.kntu.objects.Cell;
import ir.ac.kntu.objects.Table;
import ir.ac.kntu.objects.capsule.Capsule;
import ir.ac.kntu.objects.capsule.CapsuleStanding;

public class CapsuleMove {

    public static void moveLeft(Capsule capsule) throws CellWasFull {
        Cell head = capsule.getHead();
        Cell tail = capsule.getTail();
        switch (capsule.getCapsuleStanding()) {
            case VERTICAL : {
                if (head.getPosX()-1 >= 0) {
                    if (Table.getInstance().getCell(head.getPosX()-1,head.getPosY()).isEmpty() &&
                            Table.getInstance().getCell(head.getPosX()-1,head.getPosY()+1).isEmpty()){
                        capsule.syncWithCell(Table.getInstance().getCell(head.getPosX()-1,head.getPosY()));
                        head.clearCell();
                        tail.clearCell();
                    } else {
                        throw new CellWasFull(2);
                    }
                }
            }
            case HORIZONTAL: {
                if (head.getPosX()-1 >= 0) {
                    if (Table.getInstance().getCell(head.getPosX()-1,head.getPosY()).isEmpty()) {
                        capsule.syncWithCell(Table.getInstance().getCell(head.getPosX()-1,head.getPosY()));
                        tail.clearCell();
                    } else {
                        throw new CellWasFull(2);
                    }
                }
            }
        }
    }

    public static void moveRight(Capsule capsule) throws CellWasFull {
        Cell head = capsule.getHead();
        Cell tail = capsule.getTail();
        switch (capsule.getCapsuleStanding()) {
            case HORIZONTAL : {
                if (head.getPosX() + 2 <= Table.getInstance().getTableWith()) {
                    if (Table.getInstance().getCell(head.getPosX() + 2,head.getPosY()).isEmpty()) {
                        capsule.syncWithCell(capsule.getTail());
                        head.clearCell();
                    } else {
                        throw new CellWasFull(3);
                    }
                }
            }
            case VERTICAL: {
                if (head.getPosX() + 1 <= Table.getInstance().getTableWith()) {
                    if (Table.getInstance().getCell(head.getPosX()+1, head.getPosY()).isEmpty()
                            && Table.getInstance().getCell(head.getPosX() + 1, head.getPosY() + 1).isEmpty()) {
                        capsule.syncWithCell(Table.getInstance().getCell(head.getPosX()+1,head.getPosY()));
                        head.clearCell();
                        tail.clearCell();
                    } else {
                        throw new CellWasFull(3);
                    }
                }
            }
        }

    }

    public static void moveDown(Capsule capsule) throws CellWasFull {
        Cell head = capsule.getHead();
        Cell tail = capsule.getTail();
        switch (capsule.getCapsuleStanding()) {
            case VERTICAL : {
                if (head.getPosY() + 2 <= Table.getInstance().getTableLength()) {
                    if (Table.getInstance().getCell(head.getPosX(),head.getPosY()+2).isEmpty()) {
                        capsule.syncWithCell(capsule.getTail());
                        head.clearCell();
                    } else {
                        capsule.setStaticCapsule(true);
                        throw new CellWasFull(4);
                    }
                }
            }
            case HORIZONTAL: {
                if (head.getPosY() + 1 <= Table.getInstance().getTableLength()) {
                    if (Table.getInstance().getCell(head.getPosX(),head.getPosY() + 1).isEmpty() &&
                            Table.getInstance().getCell(head.getPosX() + 1, head.getPosY() + 1).isEmpty()) {
                        capsule.syncWithCell(Table.getInstance().getCell(head.getPosX(),head.getPosY()+1));
                        head.clearCell();
                        tail.clearCell();
                    } else {
                        capsule.setStaticCapsule(true);
                        throw new CellWasFull(4);
                    }
                }
            }
        }
    }

    public static void rotate(Capsule capsule) throws CellWasFull {
        Cell head = capsule.getHead();
        Cell tail = capsule.getTail();
        switch (capsule.getCapsuleStanding()) {
            case VERTICAL: {
                if (!(capsule.getHead().getPosX() == Table.getInstance().getTableWith())){
                    if (Table.getInstance().getCell(capsule.getHead().getPosX()+1,capsule.getHead().getPosY()).isEmpty()) {
                        capsule.setCapsuleStanding(CapsuleStanding.HORIZONTAL);
                        tail.clearCell();
                    } else {
                        throw new CellWasFull(1);
                    }

                }
            }
            case HORIZONTAL: {
                if (Table.getInstance().getCell(capsule.getHead().getPosX(),capsule.getHead().getPosY()+1).isEmpty()){
                    capsule.setCapsuleStanding(CapsuleStanding.VERTICAL);
                    tail.clearCell();
                } else {
                    throw new CellWasFull(1);
                }
            }
        }
    }
}
