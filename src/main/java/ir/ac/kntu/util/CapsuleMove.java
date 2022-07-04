package ir.ac.kntu.util;

import ir.ac.kntu.exeptions.CellWasFull;
import ir.ac.kntu.game.GameSettings;
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
                    } else {
                        throw new CellWasFull(2);
                    }
                }
            }
            case HORIZONTAL: {
                if (head.getPosX()-1 >= 0) {
                    if (Table.getInstance().getCell(head.getPosX()-1,head.getPosY()).isEmpty()) {
                        capsule.syncWithCell(Table.getInstance().getCell(head.getPosX()-1,head.getPosY()));
                    } else {
                        throw new CellWasFull(2);
                    }
                }
            }
        }
    }

    public static void moveRight(Capsule capsule) throws CellWasFull {
        Cell head = capsule.getHead();
        switch (capsule.getCapsuleStanding()) {
            case HORIZONTAL : {
                if (head.getPosX() + 2 <= GameSettings.getInstance().getTable_with()) {
                    if (Table.getInstance().getCell(head.getPosX() + 2,head.getPosY()).isEmpty()) {
                        capsule.syncWithCell(capsule.getTail());
                    } else {
                        throw new CellWasFull(3);
                    }
                }
            }
            case VERTICAL: {
                if (head.getPosX() + 1 <= GameSettings.getInstance().getTable_with()) {
                    if (Table.getInstance().getCell(head.getPosX()+1, head.getPosY()).isEmpty()
                            && Table.getInstance().getCell(head.getPosX() + 1, head.getPosY() + 1).isEmpty()) {
                        capsule.syncWithCell(Table.getInstance().getCell(head.getPosX()+1,head.getPosY()));
                    } else {
                        throw new CellWasFull(3);
                    }
                }
            }
        }

    }

    public static void moveDown(Capsule capsule) throws CellWasFull {
        Cell head = capsule.getHead();
        switch (capsule.getCapsuleStanding()) {
            case VERTICAL : {
                if (head.getPosY() + 2 <= GameSettings.getInstance().getTable_length()) {
                    if (Table.getInstance().getCell(head.getPosX(),head.getPosY()+2).isEmpty()) {
                        capsule.syncWithCell(capsule.getTail());
                    } else {
                        capsule.setStaticCapsule(true);
                        throw new CellWasFull(4);
                    }
                }
            }
            case HORIZONTAL: {
                if (head.getPosY() + 1 <= GameSettings.getInstance().getTable_length()) {
                    if (Table.getInstance().getCell(head.getPosX(),head.getPosY() + 1).isEmpty() &&
                            Table.getInstance().getCell(head.getPosX() + 1, head.getPosY() + 1).isEmpty()) {
                        capsule.syncWithCell(Table.getInstance().getCell(head.getPosX(),head.getPosY()+1));
                    } else {
                        capsule.setStaticCapsule(true);
                        throw new CellWasFull(4);
                    }
                }
            }
        }
    }

    public static void rotate(Capsule capsule) throws CellWasFull {
        switch (capsule.getCapsuleStanding()) {
            case VERTICAL: {
                if (!(capsule.getHead().getPosX() == GameSettings.getInstance().getTable_with())){
                    if (Table.getInstance().getCell(capsule.getHead().getPosX()+1,capsule.getHead().getPosY()).isEmpty()) {
                        capsule.setCapsuleStanding(CapsuleStanding.HORIZONTAL);
                    } else {
                        throw new CellWasFull(1);
                    }

                }
            }
            case HORIZONTAL: {
                if (Table.getInstance().getCell(capsule.getHead().getPosX(),capsule.getHead().getPosY()+1).isEmpty()){
                    capsule.setCapsuleStanding(CapsuleStanding.VERTICAL);
                } else {
                    throw new CellWasFull(1);
                }
            }
        }
    }
}
