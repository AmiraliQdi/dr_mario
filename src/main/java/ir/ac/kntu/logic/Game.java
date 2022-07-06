package ir.ac.kntu.logic;

import ir.ac.kntu.graphic.GraphicEngine;
import ir.ac.kntu.objects.Table;
import ir.ac.kntu.resources.User;
import javafx.scene.Group;

public class Game {

    private static Game instance = new Game();

    private Thread graphicEngineThread;

    private Thread tableThread;

    private Thread gameSetThread;

    private Thread playerTwo;

    private Player playerOne;

    private GameSet gameSet;

    private User loggedUser;

    private Game(){
        GraphicEngine graphicEngine = new GraphicEngine();
        graphicEngine.setFps(15);
        graphicEngineThread = new Thread(graphicEngine,"GraphicEngineThread");
        tableThread = new Thread(Table.getInstance(),"TableThread");
    }

    public void pauseGame() {
        try {
            tableThread.wait();
            gameSetThread.wait();
        } catch (InterruptedException e){
            System.out.println(e);
        }
    }

    public void resumeGame(){
        tableThread.notify();
        gameSetThread.notify();
    }

    public void closeThreads(){
        try {
            graphicEngineThread.stop();
            tableThread.stop();
            gameSetThread.stop();
        } catch (NullPointerException e){

        }
    }

    public void makePlayerOne(){
        playerOne = Player.getInstance();
    }

    public static Game getInstance() {
        return instance;
    }

    public void start() {
        graphicEngineThread.start();
    }

    public void setLoggedUser(User loggedUser) {
        this.loggedUser = loggedUser;
    }

    public User getLoggedUser() {
        return loggedUser;
    }

    public void makeGameSet(){
        tableThread.start();
        this.gameSet = new GameSet(Table.getInstance(),GameSettings.getInstance().getGameLevel()*GameSettings.VIRUS_PER_LEVEL);
        System.out.println("Started a game with diff : " + GameSettings.getInstance().getGameSpeed() + " and level : " + GameSettings.getInstance().getGameLevel());
        gameSetThread = new Thread(gameSet);
        gameSetThread.start();
    }

    public GameSet getGameSet() {
        return gameSet;
    }
}
