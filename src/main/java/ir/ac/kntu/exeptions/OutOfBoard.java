package ir.ac.kntu.exeptions;

public class OutOfBoard extends Exception {

    private int i;

    public OutOfBoard(int i){
        this.i = i;
    }

    public int getI() {
        return i;
    }
}
