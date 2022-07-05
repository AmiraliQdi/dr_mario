package ir.ac.kntu.objects;

import ir.ac.kntu.logic.Color;
import ir.ac.kntu.objects.capsule.EnhancementCapsule;
import ir.ac.kntu.objects.capsule.NormalCapsule;

public class Cell {

    private int posX;

    private int posY;

    private CellObject cellObject = null;

    private CellObjectType cellObjectType = CellObjectType.NULL;

    public Cell(int posX,int posY){
        this.posX = posX;
        this.posY = posY;
    }

    public boolean isEmpty(){
        if (cellObject == null){
            return true;
        } else {
            return false;
        }
    }

    public char print(){
        switch (cellObjectType) {
            case VIRUS: {
                Virus virus = (Virus) cellObject;
                if (virus.getColor() == Color.RED) {
                    return '.';
                } else if (virus.getColor() == Color.YELLOW){
                    return ',';
                } else {
                    return ';';
                }
            }
            case HALF_CAPSULE: {
                HalfCapsule halfCapsule = (HalfCapsule) cellObject;
                if (halfCapsule.getColor() == Color.RED) {
                    return '1';
                } else if (halfCapsule.getColor() == Color.YELLOW) {
                    return '2';
                } else {
                    return '3';
                }
            }
            case NORMAL_CAPSULE_HEAD: {
                NormalCapsule normalCapsule = (NormalCapsule) cellObject;
                if (normalCapsule.getHeadColor() == Color.RED) {
                    return 'A';
                } else if (normalCapsule.getHeadColor() == Color.YELLOW) {
                    return 'B';
                } else {
                    return 'C';
                }
            }
            case NORMAL_CAPSULE_TAIL: {
                NormalCapsule normalCapsule = (NormalCapsule) cellObject;
                if (normalCapsule.getTailColor() == Color.RED) {
                    return 'a';
                } else if (normalCapsule.getTailColor() == Color.YELLOW) {
                    return 'b';
                } else {
                    return 'c';
                }
            }
            case ENHANCEMENT_CAPSULE_HEAD: {
                EnhancementCapsule enhancementCapsule = (EnhancementCapsule) cellObject;
                if (enhancementCapsule.getHeadColor() == Color.RED) {
                    return 'X';
                } else if (enhancementCapsule.getHeadColor() == Color.YELLOW) {
                    return 'Y';
                } else {
                    return 'Z';
                }
            }
            case ENHANCEMENT_CAPSULE_TAIL: {
                EnhancementCapsule enhancementCapsule = (EnhancementCapsule) cellObject;
                if (enhancementCapsule.getTailColor() == Color.RED) {
                    return 'x';
                } else if (enhancementCapsule.getTailColor() == Color.YELLOW) {
                    return 'y';
                } else {
                    return 'z';
                }
            }
            case NULL: {
                return ' ';
            }
            default: {
                return '!';
            }
        }
    }

    public CellObjectType getCellObjectType() {
        return cellObjectType;
    }

    public void setCellObjectType(CellObjectType cellObjectType) {
        this.cellObjectType = cellObjectType;
    }

    public void setCellObject(CellObject cellObject) {
        this.cellObject = cellObject;
    }

    public CellObject getCellObject() {
        return cellObject;
    }

    public void clearCell(){
        cellObject = null;
        cellObjectType = CellObjectType.NULL;
    }

    public int getPosX() {
        return posX;
    }

    public int getPosY() {
        return posY;
    }
}
