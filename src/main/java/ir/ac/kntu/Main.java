package ir.ac.kntu;

import ir.ac.kntu.graphic.GraphicEngine;
import javafx.application.Application;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;

public class Main {

    public static void main(String[] args) {
        GraphicEngine graphicEngine = new GraphicEngine();
        graphicEngine.setFps(15);
        Thread graphicEngineThread = new Thread(graphicEngine);
        graphicEngineThread.start();
    }

}