package ir.ac.kntu.logic;

import ir.ac.kntu.exeptions.CellWasFull;
import ir.ac.kntu.objects.capsule.Capsule;
import ir.ac.kntu.resources.User;
import ir.ac.kntu.util.CapsuleMove;

public class Player {

    private static Player instance = new Player();

    private Player(){}

    public static Player getInstance() {
        return instance;
    }

    public void onKeyPress(char key){
        Capsule movingCapsule = Game.getInstance().getGameSet().getMovingCapsule();
        try {
            switch (key) {
                case 'A' -> CapsuleMove.moveLeft(movingCapsule);
                case 'S' -> CapsuleMove.moveDown(movingCapsule);
                case 'D' -> CapsuleMove.moveRight(movingCapsule);
                case 'V' -> CapsuleMove.rotate(movingCapsule);
                }
        } catch (CellWasFull e){
            System.out.println("Could not move that way");
        }
    }

}
