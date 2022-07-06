package ir.ac.kntu.logic;

import ir.ac.kntu.objects.Cell;
import ir.ac.kntu.objects.capsule.Capsule;

import java.util.ArrayList;
import java.util.Arrays;

public class CellStack {

    private ArrayList<Cell> cells;

    private static final int STACK_COUNT = 4;

    public CellStack(){
        cells = new ArrayList<>();
    }

    public void addToStack(Cell cell){
        if (cells.size()<4) {
            cells.add(cell);
        } else {
            cells.remove(0);
            cells.add(cell);
        }
    }

    public void clearStack(){
        cells.clear();
    }

    public ArrayList<Cell> getStack(){
        return cells;
    }

    public boolean isSameColorStack(){
        ArrayList<Color> colors = new ArrayList<>();
        System.out.println("size :" + cells.size());
        if (cells.size()!=4){
            return false;
        }
        for (int i = 0;i<4;i++){
            if (cells.get(i).getColor() == null){
                return false;
            }
        }
        for (int j = 0;j<4;j++){
            colors.add(cells.get(j).getColor());
        }
        System.out.println(colors);
        if (colors.get(0) == Color.RED && colors.get(1) == Color.RED
                && colors.get(2) == Color.RED && colors.get(3) == Color.RED){
            return true;
        } else if (colors.get(0) == Color.YELLOW && colors.get(1) == Color.YELLOW
                && colors.get(2) == Color.YELLOW && colors.get(3) == Color.YELLOW) {
            return true;
        } else return colors.get(0) == Color.BLUE && colors.get(1) == Color.BLUE
                && colors.get(2) == Color.BLUE && colors.get(3) == Color.BLUE;
    }
}
