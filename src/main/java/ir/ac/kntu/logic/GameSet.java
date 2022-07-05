package ir.ac.kntu.logic;

import ir.ac.kntu.exeptions.CellWasFull;
import ir.ac.kntu.game.GameSettings;
import ir.ac.kntu.objects.HalfCapsule;
import ir.ac.kntu.objects.Table;
import ir.ac.kntu.objects.Virus;
import ir.ac.kntu.objects.capsule.Capsule;
import ir.ac.kntu.objects.capsule.CapsuleType;
import ir.ac.kntu.objects.capsule.EnhancementCapsule;
import ir.ac.kntu.objects.capsule.NormalCapsule;
import ir.ac.kntu.util.CapsuleMove;
import ir.ac.kntu.util.RandomHelper;

import java.util.ArrayList;

public class GameSet implements Runnable{

    private Table table;

    private int gameSpeed;

    private int virusCounts;

    private boolean gameOver = false;

    private Capsule stackCapsule;

    private CapsuleType stackCapsuleType;

    private int score;

    public GameSet(Table table,int gameSpeed,int virusCounts) {
        this.table = table;
        this.gameSpeed = gameSpeed;
        this.virusCounts = virusCounts;
        makeRandomViruses();
        stackCapsuleType = CapsuleType.NORMAL;
        this.score = 0;
    }

    private void makeRandomViruses(){
        int counter = virusCounts;
        int virusRatio = (int) (GameSettings.getInstance().getTable_with()*
                GameSettings.getInstance().getRandomVirusRatio());
        for (int i = GameSettings.getInstance().getTable_length();i>0;i--){
            int cellsWithVirusCount = RandomHelper.getInstance().getBelow(virusRatio);
            if (cellsWithVirusCount > counter) {
                cellsWithVirusCount = counter;
            }
            counter-=cellsWithVirusCount;
            ArrayList<Integer> cellsWithVirus = RandomHelper.getInstance().makeRandomNumbersBelow(virusRatio,cellsWithVirusCount);
            for (Integer withVirus : cellsWithVirus) {
                Virus newVirus = new Virus();
                //TODO make sure for stacked same color spawn
                newVirus.syncWithCell(Table.getInstance().getCell(withVirus, i));
            }
        }
    }

    @Override
    public void run() {
        boolean isStarted = false;
        while (!gameOver) {
            Capsule movingCapsule;
            if (!isStartingCellEmpty()) {
                gameOver();
            }
            if (!isStarted) {
                movingCapsule = spawnCapsule(0);
                isStarted = true;
            } else {
                movingCapsule = spawnCapsule(1);
            }
            makeStackCapsule();
            moving(movingCapsule);
        }
    }

    private void moving(Capsule movingCapsule){
        while (!movingCapsule.isStaticCapsule()) {
            try {
                CapsuleMove.moveDown(movingCapsule);
            } catch (CellWasFull e) {
                e.getMessage();
                checkForStackColor();
                if (movingCapsule.isStaticCapsule()){
                    break;
                }
            }
            try {
                Thread.sleep(gameSpeed* 1000L);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    private void checkForStackColor(){
        //TODO complete checkForStackColor() methode
    }

    private Capsule spawnCapsule(int i) {
        if (i == 0) {
            return new NormalCapsule();
        } else {
            return stackCapsule;
        }
    }

    private void makeStackCapsule(){
        switch (stackCapsuleType) {
            case NORMAL -> stackCapsule = new NormalCapsule();
            case ENHANCEMENT -> stackCapsule = new EnhancementCapsule();
        }
    }

    private void gameOver(){
        gameOver = true;
    }

    private boolean isStartingCellEmpty(){
        if (GameSettings.getInstance().getStartingCell().isEmpty() &&
                Table.getInstance().getCell(GameSettings.getInstance().getStartingCell().getPosX()+1,
                        GameSettings.getInstance().getStartingCell().getPosY()).isEmpty()) {
            return true;
        } else {
            return false;
        }
    }
}
