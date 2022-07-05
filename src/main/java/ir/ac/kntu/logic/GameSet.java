package ir.ac.kntu.logic;

import ir.ac.kntu.exeptions.CellWasFull;
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

    private int virusCounts;

    private boolean gameOver = false;

    private Capsule stackCapsule;

    private CapsuleType stackCapsuleType;

    private int score;

    private int maxScoreOfUser;

    public GameSet(Table table,int virusCounts) {
        this.table = table;
        this.virusCounts = virusCounts;
        makeRandomViruses();
        stackCapsuleType = CapsuleType.NORMAL;
        this.score = 0;
    }

    private void makeRandomViruses(){
        int counter = virusCounts;
        int virusRatio = (int) (Table.getInstance().getTableWith()*
                GameSettings.getInstance().getRandomVirusRatio());
        if (virusRatio<4){
            virusRatio = 4;
        }
        for (int i = Table.getInstance().getTableLength()-1;i>0;i--){
            int cellsWithVirusCount = RandomHelper.getInstance().getBelow(virusRatio);
            if (cellsWithVirusCount > counter) {
                cellsWithVirusCount = counter;
            }
            counter-=cellsWithVirusCount;
            ArrayList<Integer> cellsWithVirus = RandomHelper.getInstance().makeRandomNumbersBelow(8,cellsWithVirusCount);
            for (Integer withVirus : cellsWithVirus) {
                Virus newVirus = new Virus();
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
                movingCapsule.activeStaticMode();
                System.out.println("new Static");
                if (movingCapsule.isStaticCapsule()){
                    break;
                }
            }
            try {
                Thread.sleep(GameSettings.getInstance().getGameSpeed()* 1000L);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
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
        //TODO game over
        if (score > Game.getInstance().getLoggedUser().getMaxScore()) {
            Game.getInstance().getLoggedUser().setMaxScore(score);
        }
        gameOver = true;
    }

    private boolean isStartingCellEmpty(){
        if (Table.getInstance().getStartingCell1().isEmpty() && Table.getInstance().getStartingCell2().isEmpty()) {
            return true;
        } else {
            return false;
        }
    }

    public int getScore() {
        return score;
    }
}
