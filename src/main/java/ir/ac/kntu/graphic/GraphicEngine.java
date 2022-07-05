package ir.ac.kntu.graphic;

import javafx.application.Application;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class GraphicEngine extends Application implements Runnable {

    private int fps;

    public GraphicEngine(){
    }

    public void setFps(int fps) {
        this.fps = fps;
    }

    @Override
    public void run() {
        Application.launch();
    }

    @Override
    public void start(Stage stage) throws Exception {
        stage.setTitle("Dr.Mario");
        startMenu(stage);
    }

    private void startMenu(Stage stage){
        Group root = new Group();
        Scene scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }
}
