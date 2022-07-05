package ir.ac.kntu.logic;

import ir.ac.kntu.graphic.GraphicEngine;
import ir.ac.kntu.objects.Table;
import ir.ac.kntu.resources.User;

public class Game {

    private static Game instance = new Game();

    private Thread graphicEngineThread;

    private Thread tableThread;

    private Thread gameSetThread;

    private Thread playerOneThread;

    private Thread playerTwo;

    private GameSet gameSet;

    private User loggedUser;

    private Game(){
        GraphicEngine graphicEngine = new GraphicEngine();
        graphicEngine.setFps(15);
        graphicEngineThread = new Thread(graphicEngine,"GraphicEngineThread");
        tableThread = new Thread(Table.getInstance(),"TableThread");
    }

    public void pauseGame() throws InterruptedException {
        tableThread.wait();
        gameSetThread.wait();
        playerOneThread.wait();
    }

    public void resumeGame(){
        tableThread.notify();
        gameSetThread.notify();
        playerOneThread.notify();
    }

    public void closeThreads(){
        graphicEngineThread.stop();
        tableThread.stop();
        playerOneThread.stop();
        gameSetThread.stop();
    }

    public void makePlayerOne(){
        playerOneThread = new Thread(new Player());
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

    public void startPlayerOneThread(){
        playerOneThread.start();
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
