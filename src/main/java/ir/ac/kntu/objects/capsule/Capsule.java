package ir.ac.kntu.objects.capsule;

import ir.ac.kntu.logic.Color;
import ir.ac.kntu.objects.Cell;
import ir.ac.kntu.objects.CellObject;

import java.util.Objects;

public abstract class Capsule implements CellObject {

    private CapsuleStanding capsuleStanding;

    private CapsuleType capsuleType;

    private Cell head;

    private Cell tail;

    private int id;

    private static int ID = 0;

    Color headColor;

    Color tailColor;

    public Capsule(){
        capsuleStanding = CapsuleStanding.VERTICAL;
        id = ID;
        ID++;
    }

    public void setCapsuleType(CapsuleType capsuleType) {
        this.capsuleType = capsuleType;
    }

    public CapsuleType getCapsuleType() {
        return capsuleType;
    }

    public void setHead(Cell head) {
        this.head = head;
    }

    public void setTail(Cell tail) {
        this.tail = tail;
    }

    public Cell getHead() {
        return head;
    }

    public Cell getTail() {
        return tail;
    }

    public int getId() {
        return id;
    }

    public void setHeadColor(Color headColor) {
        this.headColor = headColor;
    }

    public void setTailColor(Color tailColor) {
        this.tailColor = tailColor;
    }

    public Color getHeadColor() {
        return headColor;
    }

    public Color getTailColor() {
        return tailColor;
    }

    public CapsuleStanding getCapsuleStanding() {
        return capsuleStanding;
    }

    public void setCapsuleStanding(CapsuleStanding capsuleStanding) {
        this.capsuleStanding = capsuleStanding;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        Capsule capsule = (Capsule) o;
        return id == capsule.id;
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }
}
