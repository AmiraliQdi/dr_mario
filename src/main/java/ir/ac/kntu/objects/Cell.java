package ir.ac.kntu.objects;

import ir.ac.kntu.logic.Color;
import ir.ac.kntu.objects.capsule.CapsuleStanding;
import ir.ac.kntu.objects.capsule.EnhancementCapsule;
import ir.ac.kntu.objects.capsule.NormalCapsule;
import javafx.scene.image.Image;

public class Cell {

    private int posX;

    private int posY;

    private CellObject cellObject = null;

    private CellObjectType cellObjectType = CellObjectType.NULL;

    private Color color = null;

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

    public String getImageAddress(){
        switch (cellObjectType) {
            case VIRUS -> {
                Virus virus = (Virus) cellObject;
                if (virus.getColor() == Color.RED) {
                    return "images/table_images/virus/red_virus.png";
                } else if (virus.getColor() == Color.YELLOW) {
                    return "images/table_images/virus/yellow_virus.png";
                } else {
                    return "images/table_images/virus/blue_virus.png";
                }
            }
            case HALF_CAPSULE -> {
                HalfCapsule halfCapsule = (HalfCapsule) cellObject;
                if (halfCapsule.getColor() == Color.RED) {
                    return "images/table_images/half_capsule/red_half_capsule.png";
                } else if (halfCapsule.getColor() == Color.YELLOW) {
                    return "images/table_images/half_capsule/yellow_half_capsule.png";
                } else {
                    return "images/table_images/half_capsule/blue_half_capsule.png";
                }
            }
            case NORMAL_CAPSULE_HEAD -> {
                NormalCapsule normalCapsule = (NormalCapsule) cellObject;
                if (normalCapsule.getHeadColor() == Color.RED) {
                    if ( (normalCapsule.getCapsuleStanding() == CapsuleStanding.VERTICAL)) {
                        return "images/table_images/red_capsule/red_capsule_D.png";
                    } else {
                        return "images/table_images/red_capsule/red_capsule_L.png";
                    }
                } else if (normalCapsule.getHeadColor() == Color.YELLOW) {
                    if ( (normalCapsule.getCapsuleStanding() == CapsuleStanding.VERTICAL)) {
                        return "images/table_images/yellow_capsule/yellow_capsule_D.png";
                    } else {
                        return "images/table_images/yellow_capsule/yellow_capsule_L.png";
                    }
                } else {
                    if ( (normalCapsule.getCapsuleStanding() == CapsuleStanding.VERTICAL)) {
                        return "images/table_images/blue_capsule/blue_capsule_D.png";
                    } else {
                        return "images/table_images/blue_capsule/blue_capsule_L.png";
                    }
                }
            }
            case NORMAL_CAPSULE_TAIL -> {
                NormalCapsule normalCapsule = (NormalCapsule) cellObject;
                if (normalCapsule.getTailColor() == Color.RED) {
                    if ( (normalCapsule.getCapsuleStanding() == CapsuleStanding.VERTICAL)) {
                        return "images/table_images/red_capsule/red_capsule_U.png";
                    } else {
                        return "images/table_images/red_capsule/red_capsule_R.png";
                    }
                } else if (normalCapsule.getTailColor() == Color.YELLOW) {
                    if ( (normalCapsule.getCapsuleStanding() == CapsuleStanding.VERTICAL)) {
                        return "images/table_images/yellow_capsule/yellow_capsule_U.png";
                    } else {
                        return "images/table_images/yellow_capsule/yellow_capsule_R.png";
                    }
                } else {
                    if ( (normalCapsule.getCapsuleStanding() == CapsuleStanding.VERTICAL)) {
                        return "images/table_images/blue_capsule/blue_capsule_U.png";
                    } else {
                        return "images/table_images/blue_capsule/blue_capsule_R.png";
                    }
                }
            }
            case ENHANCEMENT_CAPSULE_HEAD -> {
                EnhancementCapsule enhancementCapsule = (EnhancementCapsule) cellObject;
                if (enhancementCapsule.getHeadColor() == Color.RED) {
                    return "X";
                } else if (enhancementCapsule.getHeadColor() == Color.YELLOW) {
                    return "Y";
                } else {
                    return "Z";
                }
            }
            case ENHANCEMENT_CAPSULE_TAIL -> {
                EnhancementCapsule enhancementCapsule = (EnhancementCapsule) cellObject;
                if (enhancementCapsule.getTailColor() == Color.RED) {
                    return "x";
                } else if (enhancementCapsule.getTailColor() == Color.YELLOW) {
                    return "y";
                } else {
                    return "z";
                }
            }
            case NULL -> {
                return "images/table_images/null.png";
            }
            default -> {
                return "!";
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

    public void setColor(Color color) {
        this.color = color;
    }

    public Color getColor() {
        return color;
    }

    public CellObject getCellObject() {
        return cellObject;
    }

    public void clearCell(){
        cellObject = null;
        cellObjectType = CellObjectType.NULL;
        color = null;
    }

    public int getPosX() {
        return posX;
    }

    public int getPosY() {
        return posY;
    }
}
