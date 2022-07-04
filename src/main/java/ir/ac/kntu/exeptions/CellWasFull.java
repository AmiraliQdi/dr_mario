package ir.ac.kntu.exeptions;

public class CellWasFull extends Exception{

    public CellWasFull(int messageType){
        this.messageType = messageType;
    }

    private int messageType;

    @Override
    public String getMessage() {
        return switch (messageType) {
            case 1 -> "Cell was full could not rotate";
            case 2 -> "Cell was full could not move left";
            case 3 -> "Cell was full could not move right";
            case 4 -> "Cell was full could not move down";
            default -> "Cell was full could not move!";
        };
    }
}
