package ir.ac.kntu.util;

import ir.ac.kntu.exeptions.CellWasFull;
import ir.ac.kntu.objects.Cell;
import ir.ac.kntu.objects.CellObjectType;
import ir.ac.kntu.objects.Table;
import ir.ac.kntu.objects.capsule.Capsule;
import ir.ac.kntu.objects.capsule.CapsuleStanding;

public class CapsuleMove {

    private static boolean THREAD_INSIDE = false;

    public static void moveLeft(Capsule capsule) throws CellWasFull {
        while (THREAD_INSIDE) {
        }
        THREAD_INSIDE = true;
        Cell oldHead = capsule.getHead();
        Cell oldTail = capsule.getTail();
        Cell newHead = null;
        Cell newTail = null;
        switch (capsule.getDegree()) {
            case 0, 270 -> {
                if (oldHead.getPosX()-1<0) {
                    THREAD_INSIDE = false;
                    return;
                }
                if (Table.getInstance().getCell(oldHead.getPosX()-1,oldHead.getPosY()).isEmpty() &&
                    Table.getInstance().getCell(oldTail.getPosX()-1,oldTail.getPosY()).isEmpty()) {
                    newHead = Table.getInstance().getCell(oldHead.getPosX()-1,oldHead.getPosY());
                    newTail = Table.getInstance().getCell(oldTail.getPosX()-1,oldTail.getPosY());
                    oldHead.clearCell();
                    oldTail.clearCell();
                } else {
                    THREAD_INSIDE = false;
                    return;
                }
            }
            case 180 -> {
                if (oldHead.getPosX()-1<0){
                    THREAD_INSIDE = false;
                    return;
                }
                if (Table.getInstance().getCell(oldHead.getPosX()-1,oldHead.getPosY()).isEmpty()){
                    newHead = Table.getInstance().getCell(oldHead.getPosX()-1,oldHead.getPosY());
                    newTail = oldHead;
                    oldTail.clearCell();
                } else {
                    THREAD_INSIDE = false;
                    return;
                }
            }
            case 360 -> {
                if (oldTail.getPosX()-1<0){
                    THREAD_INSIDE = false;
                    return;
                }
                if (Table.getInstance().getCell(oldTail.getPosX()-1,oldTail.getPosY()).isEmpty()){
                    newHead = oldTail;
                    newTail = Table.getInstance().getCell(oldTail.getPosX()-1,oldTail.getPosY());
                    oldHead.clearCell();
                } else {
                    THREAD_INSIDE = false;
                    return;
                }
            }
        }
        capsule.syncWithCell(newHead,newTail);
        THREAD_INSIDE = false;
    }

    public static void moveRight(Capsule capsule) throws CellWasFull {
        while (THREAD_INSIDE) {
        }
        THREAD_INSIDE = true;
        Cell oldHead = capsule.getHead();
        Cell oldTail = capsule.getTail();
        Cell newHead = null;
        Cell newTail = null;
        switch (capsule.getDegree()) {
            case 0, 270 -> {
                if (oldHead.getPosX()+1>=8){
                    THREAD_INSIDE = false;
                    return;
                }
                if (Table.getInstance().getCell(oldHead.getPosX()+1,oldHead.getPosY()).isEmpty() &&
                        Table.getInstance().getCell(oldTail.getPosX()+1,oldTail.getPosY()).isEmpty()){
                    newHead = Table.getInstance().getCell(oldHead.getPosX()+1,oldHead.getPosY());
                    newTail = Table.getInstance().getCell(oldTail.getPosX()+1,oldTail.getPosY());
                    oldHead.clearCell();
                    oldTail.clearCell();
                } else {
                    THREAD_INSIDE = false;
                    return;
                }
            }
            case 180 -> {
                if(oldTail.getPosX()+1>=8){
                    THREAD_INSIDE = false;
                    return;
                }
                if (Table.getInstance().getCell(oldTail.getPosX()+1,oldTail.getPosY()).isEmpty()){
                    newHead = oldTail;
                    newTail = Table.getInstance().getCell(oldTail.getPosX()+1,oldTail.getPosY());
                    oldHead.clearCell();
                } else {
                    THREAD_INSIDE = false;
                    return;
                }
            }
            case 360 -> {
                if (oldHead.getPosX()+1>=8){
                    THREAD_INSIDE = false;
                    return;
                }
                if (Table.getInstance().getCell(oldHead.getPosX()+1,oldHead.getPosY()).isEmpty()){
                    newHead = Table.getInstance().getCell(oldHead.getPosX()+1,oldHead.getPosY());
                    newTail = oldHead;
                    oldTail.clearCell();
                } else {
                    THREAD_INSIDE = false;
                    return;
                }
            }
        }
        capsule.syncWithCell(newHead,newTail);
        THREAD_INSIDE = false;
    }

    public static void moveDown(Capsule capsule) throws CellWasFull {
        while (THREAD_INSIDE) {
        }
        THREAD_INSIDE = true;
        Cell oldHead = capsule.getHead();
        Cell oldTail = capsule.getTail();
        Cell newHead = null;
        Cell newTail = null;
        switch (capsule.getDegree()) {
            case 0 -> {
                if (oldHead.getPosY()+1>=16) {
                    capsule.setStaticCapsule(true);
                    THREAD_INSIDE = false;
                    return;
                }
                if (Table.getInstance().getCell(oldHead.getPosX(),oldHead.getPosY()+1).isEmpty()){
                    newHead = Table.getInstance().getCell(oldHead.getPosX(),oldHead.getPosY()+1);
                    newTail = oldHead;
                    oldTail.clearCell();
                } else {
                    capsule.setStaticCapsule(true);
                    THREAD_INSIDE = false;
                    return;
                }
            }
            case 180, 360 -> {
                if (oldHead.getPosY()+1>=16){
                    capsule.setStaticCapsule(true);
                    THREAD_INSIDE = false;
                    return;
                }
                if (Table.getInstance().getCell(oldHead.getPosX(),oldHead.getPosY()+1).isEmpty() &&
                        Table.getInstance().getCell(oldTail.getPosX(),oldTail.getPosY()+1).isEmpty()){
                    newHead = Table.getInstance().getCell(oldHead.getPosX(),oldHead.getPosY()+1);
                    newTail = Table.getInstance().getCell(oldTail.getPosX(),oldTail.getPosY()+1);
                    oldHead.clearCell();
                    oldTail.clearCell();
                } else {
                    capsule.setStaticCapsule(true);
                    THREAD_INSIDE = false;
                    return;
                }
            }
            case 270 -> {
                if (oldTail.getPosY()+1>=16){
                    capsule.setStaticCapsule(true);
                    THREAD_INSIDE = false;
                    return;
                }
                if (Table.getInstance().getCell(oldTail.getPosX(),oldTail.getPosY()+1).isEmpty()){
                    newTail = Table.getInstance().getCell(oldTail.getPosX(),oldTail.getPosY()+1);
                    newHead = oldTail;
                    oldHead.clearCell();
                } else {
                    capsule.setStaticCapsule(true);
                    THREAD_INSIDE = false;
                    return;
                }
            }
        }
        capsule.syncWithCell(newHead,newTail);
        THREAD_INSIDE = false;
    }

    public static void rotate(Capsule capsule) throws CellWasFull {
        while (THREAD_INSIDE) {
        }
        THREAD_INSIDE = true;
        Cell oldHead = capsule.getHead();
        Cell oldTail = capsule.getTail();
        Cell newHead = null;
        Cell newTail = null;
        switch (capsule.getDegree()) {
            case 0 -> {
                capsule.setCapsuleStanding(CapsuleStanding.HORIZONTAL);
                if (oldHead.getPosX()+1>=8){
                    THREAD_INSIDE = false;
                    return;
                }
                if (Table.getInstance().getCell(oldHead.getPosX()+1,oldHead.getPosY()).isEmpty()){
                    newTail = Table.getInstance().getCell(oldHead.getPosX()+1,oldHead.getPosY());
                    capsule.rotate();
                } else {
                    THREAD_INSIDE = false;
                    return;
                }
            }
            case 180 -> {
                capsule.setCapsuleStanding(CapsuleStanding.VERTICAL);
                if (oldHead.getPosY()+1>=16){
                    capsule.setStaticCapsule(true);
                    THREAD_INSIDE = false;
                    return;
                }
                if (Table.getInstance().getCell(oldHead.getPosX(),oldHead.getPosY()+1).isEmpty()){
                    newTail = Table.getInstance().getCell(oldHead.getPosX(),oldHead.getPosY()+1);
                    capsule.rotate();
                } else {
                    THREAD_INSIDE = false;
                    return;
                }
            }
            case 270 -> {
                capsule.setCapsuleStanding(CapsuleStanding.HORIZONTAL);
                if (oldHead.getPosX()-1<0){
                    THREAD_INSIDE = false;
                    return;
                }
                if (Table.getInstance().getCell(oldHead.getPosX()-1,oldHead.getPosY()).isEmpty()){
                    newTail = Table.getInstance().getCell(oldHead.getPosX()-1,oldHead.getPosY());
                    capsule.rotate();
                } else {
                    THREAD_INSIDE = false;
                    return;
                }
            }
            case 360 -> {
                if (oldHead.getPosY()+1>=16){
                    capsule.setStaticCapsule(true);
                    THREAD_INSIDE = false;
                    return;
                }
                capsule.setCapsuleStanding(CapsuleStanding.VERTICAL);
                if (Table.getInstance().getCell(oldHead.getPosX(),oldHead.getPosY()-1).isEmpty()){
                    newTail = Table.getInstance().getCell(oldHead.getPosX(),oldHead.getPosY()-1);
                    capsule.rotate();
                } else {
                    THREAD_INSIDE = false;
                    return;
                }
            }
        }
        oldTail.clearCell();
        newHead = oldHead;
        capsule.syncWithCell(newHead,newTail);
        THREAD_INSIDE = false;
    }
}
