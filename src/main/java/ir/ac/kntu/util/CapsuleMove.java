package ir.ac.kntu.util;

import ir.ac.kntu.objects.capsule.Capsule;
import ir.ac.kntu.objects.capsule.CapsuleStanding;
import ir.ac.kntu.objects.capsule.NormalCapsule;

public class CapsuleMove {

    public static void moveLeft(Capsule capsule) {

    }

    public static void moveRight(Capsule capsule){

    }

    public static void moveDown(Capsule capsule){

    }

    public static void rotate(Capsule capsule){
        switch (capsule.getCapsuleStanding()){
            case HORIZONTAL -> capsule.setCapsuleStanding(CapsuleStanding.VERTICAL);
            case VERTICAL -> capsule.setCapsuleStanding(CapsuleStanding.HORIZONTAL);
        }
    }
}
