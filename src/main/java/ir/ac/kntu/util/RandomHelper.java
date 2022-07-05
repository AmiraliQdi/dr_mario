package ir.ac.kntu.util;

import ir.ac.kntu.logic.Color;

import java.util.ArrayList;
import java.util.Random;

public class RandomHelper {

    private static final RandomHelper instance = new RandomHelper();

    Random random = new Random(300);

    private RandomHelper(){

    }

    public static RandomHelper getInstance() {
        return instance;
    }

    public double getChance(){
        double newRandom = random.nextInt(10);
        return newRandom/10;
    }

    public int getBelow(int number){
        return random.nextInt(number);
    }

    public ArrayList<Integer> makeRandomNumbersBelow(int number,int count){
        if (count >= number){
            return null;
        }
        ArrayList<Integer> generated = new ArrayList<>();
        boolean started = false;
        while (generated.size()!=count){
            int newRandom;
            newRandom = random.nextInt(number);
            if (started){
                while (generated.contains(newRandom)){
                    newRandom = random.nextInt(number);
                }
            } else {
                started = true;
            }
            generated.add(newRandom);
        }
        return generated;
    }

    public Color randomColor(){
        int newRandom = random.nextInt(3);
        switch (newRandom) {
            case 0 -> {
                return Color.BLUE;
            }
            case 1 -> {
                return Color.YELLOW;
            }
            case 2 -> {
                return Color.RED;
            }
            default -> {
                return null;
            }
        }
    }
}
