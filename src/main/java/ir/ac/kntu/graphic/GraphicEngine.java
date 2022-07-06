package ir.ac.kntu.graphic;

import com.sun.javafx.iio.gif.GIFDescriptor;
import ir.ac.kntu.logic.Game;
import ir.ac.kntu.logic.GameSet;
import ir.ac.kntu.logic.GameSettings;
import ir.ac.kntu.logic.Player;
import ir.ac.kntu.objects.Cell;
import ir.ac.kntu.objects.Table;
import ir.ac.kntu.resources.User;
import javafx.animation.Animation;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.HPos;
import javafx.geometry.Insets;
import javafx.geometry.VPos;
import javafx.scene.Group;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.Tab;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseDragEvent;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.stage.Stage;
import javafx.stage.WindowEvent;
import javafx.util.Duration;

import java.io.IOException;


public class GraphicEngine extends Application implements Runnable {

    private static final int STARTING_MENU_WITH = 640;
    private static final int STARTING_MENU_HEIGHT = 598;
    private static final int MAIN_MENU_WITH = 400;
    private static final int MAIN_MENU_HEIGHT = 110;
    private static final int GAME_SETTING_MENU_WITH = 529;
    private static final int GAME_SETTING_MENU_HEIGHT = 442;
    private static final int GAME_OVER_MENU_WITH = 206;
    private static final int GAME_OVER_MENU_HEIGHT = 237;

    private int fps;

    private Stage stage;

    private Scene startingMenu;

    private Scene mainMenu;

    private Scene gameSettingScene;

    private Scene gameScene;

    private Scene gameOverScene;

    private Timeline gameSceneTimeLine;

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
        this.stage = stage;
        setMainStage();

        startingMenu = startMenu();
        mainMenu = mainMenu();
        gameSettingScene = makeGameSettingScene();

        stage.setScene(startingMenu);

        stage.show();
    }

    private void setMainStage(){
        stage.setTitle("Dr.Mario");
        Image logo = new Image("images/logo.jpg");
        stage.getIcons().add(logo);
        stage.setResizable(false);
        stage.setOnCloseRequest(new EventHandler<WindowEvent>() {
            @Override
            public void handle(WindowEvent windowEvent) {
                System.out.println("Game closed");
                Game.getInstance().closeThreads();
            }
        });
    }

    private Scene startMenu(){
        Group root = new Group();
        Scene scene = new Scene(root,STARTING_MENU_WITH,STARTING_MENU_HEIGHT);
        Image backGround = new Image("images/StartingMenu.png");
        ImageView backGroundView = new ImageView(backGround);
        Circle circle = new Circle(185,430,6);
        circle.setStroke(Color.RED);
        circle.setFill(Color.RED);
        circle.setOpacity(0);
        backGroundView.setX(0);
        backGroundView.setY(0);
        root.getChildren().add(backGroundView);
        root.getChildren().add(makeOnePlayer(circle));
        root.getChildren().add(circle);
        return scene;
    }

    private Rectangle makeOnePlayer(Circle circle){
        Rectangle onePlayer = new Rectangle(180,419,300,20);
        onePlayer.setOpacity(0.1);
        onePlayer.setOnMouseEntered(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent mouseEvent) {
                circle.setOpacity(0.8);
            }
        });
        onePlayer.setOnMouseExited(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent mouseEvent) {
                circle.setOpacity(0);
            }
        });
        onePlayer.setOnMouseClicked(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent mouseEvent) {
                stage.setScene(mainMenu);
                Game.getInstance().makePlayerOne();
            }
        });
        return onePlayer;
    }

    private Scene mainMenu(){
        Group group = new Group();
        Image backGround = new Image("images/MainMenu.png");
        ImageView backGroundView = new ImageView(backGround);
        backGroundView.setOpacity(0.8);
        group.getChildren().add(backGroundView);
        GridPane root = new GridPane();
        root.setPadding(new Insets(20));
        root.setVgap(10);
        root.setHgap(25);
        Label enterUser = new Label("Enter your name :");
        enterUser.setFont(Font.font("COOP",FontWeight.BOLD,13));
        enterUser.setTextFill(Color.WHITE);
        GridPane.setConstraints(enterUser,0,0);
        TextField inputForName = new TextField();
        Label dataFromUser = new Label("-");
        dataFromUser.setFont(Font.font("COOP",FontWeight.BOLD,13));
        dataFromUser.setTextFill(Color.WHITE);
        GridPane.setConstraints(inputForName,1,0);
        Button enterButton = makeMainUserButton(inputForName,dataFromUser);
        enterButton.setFont(Font.font("COOP",FontWeight.BOLD,13));
        GridPane.setConstraints(enterButton,2,0);

        Label foundedUser = new Label("User : ");
        foundedUser.setFont(Font.font("COOP",FontWeight.BOLD,13));
        foundedUser.setTextFill(Color.WHITE);
        GridPane.setConstraints(foundedUser,0,1);

        GridPane.setConstraints(dataFromUser,1,1);
        Button continueButton = makeContinueButton(dataFromUser);
        continueButton.setFont(Font.font("COOP",FontWeight.BOLD,13));
        GridPane.setConstraints(continueButton,2,1);

        root.getChildren().addAll(enterUser,inputForName,enterButton,foundedUser,dataFromUser,continueButton);
        group.getChildren().add(root);
        Scene scene = new Scene(group,MAIN_MENU_WITH,MAIN_MENU_HEIGHT,Color.GREEN);
        return scene;
    }

    private Button makeMainUserButton(TextField input,Label dataFromUser){
        Button enterButton = new Button("Enter");
        GridPane.setHalignment(enterButton,HPos.RIGHT);
        enterButton.setOnMouseClicked(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent mouseEvent) {
                String userName = input.getText();
                try {
                    User foundedUser = User.loadUser(userName);
                    Game.getInstance().setLoggedUser(foundedUser);
                    System.out.println("Logged as user : " + foundedUser.getName());
                    dataFromUser.setText(foundedUser.toString());
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        });
        return enterButton;
    }

    private Button makeContinueButton(Label label){
        Button continueButton = new Button("Continue");
        continueButton.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {
                if (!label.getText().equals("-")){
                    stage.setScene(gameSettingScene);
                }
            }
        });
        return continueButton;
    }

    private Scene makeGameSettingScene(){
        Group root = new Group();
        Image backGround = new Image("images/GameSetting.png");
        ImageView backGroundView = new ImageView(backGround);
        Rectangle fast = new Rectangle(120,120,60,40);
        fast.setFill(Color.RED);
        fast.setOpacity(0.5);
        fast.setOnMouseClicked(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent mouseEvent) {
                if (fast.getOpacity() == 0.5){
                    fast.setOpacity(1);
                    GameSettings.getInstance().setGameSpeed(1);
                } else {
                    fast.setOpacity(0.5);
                    GameSettings.getInstance().setGameSpeed(0);
                }
            }
        });
        Rectangle medium = new Rectangle(236,120,60,40);
        medium.setFill(Color.ORANGE);
        medium.setOpacity(0.5);
        medium.setOnMouseClicked(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent mouseEvent) {
                if (medium.getOpacity() == 0.5){
                    medium.setOpacity(1);
                    GameSettings.getInstance().setGameSpeed(2);
                } else {
                    medium.setOpacity(0.5);
                    GameSettings.getInstance().setGameSpeed(0);
                }
            }
        });
        Rectangle slow = new Rectangle(355,120,56,40);
        slow.setFill(Color.BLUE);
        slow.setOpacity(0.5);
        slow.setOnMouseClicked(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent mouseEvent) {
                if (slow.getOpacity() == 0.5){
                    slow.setOpacity(1);
                    GameSettings.getInstance().setGameSpeed(1);
                } else {
                    slow.setOpacity(0.5);
                    GameSettings.getInstance().setGameSpeed(3);
                }
            }
        });
        root.getChildren().add(backGroundView);
        root.getChildren().add(makeGameSettingGridPaint());
        root.getChildren().addAll(slow,medium,fast);
        return new Scene(root,GAME_SETTING_MENU_WITH,GAME_SETTING_MENU_HEIGHT);
    }

    private Parent makeGameSettingGridPaint(){
        GridPane root = new GridPane();
        root.setPadding(new Insets(50,30,20,30));
        root.setVgap(20);
        root.setHgap(50);
        Label diffLabel = new Label("Select difficulty level :");
        diffLabel.setFont(Font.font("COOP",FontWeight.BOLD,15));
        diffLabel.setTextFill(Color.WHITE);
        GridPane.setConstraints(diffLabel,1,2);
        Label levelLabel = new Label("Select level :");
        levelLabel.setFont(Font.font("COOP",FontWeight.BOLD,15));
        levelLabel.setTextFill(Color.WHITE);
        GridPane.setConstraints(levelLabel,1,5);
        TextField level = new TextField();
        GridPane.setConstraints(level,1,6);
        Button continueButton = makeContinueButton(level);
        continueButton.setFont(Font.font("COOP",FontWeight.BOLD,15));
        GridPane.setConstraints(continueButton,1,7);
        root.getChildren().addAll(diffLabel,continueButton,levelLabel,level);
        return root;
    }

    private Button makeContinueButton(TextField textField){
        Button continueButton = new Button("Start");
        continueButton.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {
                int level = Integer.parseInt(textField.getText());
                if (level < 0) {
                    level = 1;
                } else if (level > 20) {
                    level = 20;
                }
                GameSettings.getInstance().setGameLevel(level);
                if (GameSettings.getInstance().getGameSpeed()==0){
                    GameSettings.getInstance().setGameSpeed(2);
                }
                Game.getInstance().makeGameSet();
                gameScene = makeGameScene();
                stage.setScene(gameScene);
            }
        });
        return continueButton;
    }

    private Scene makeGameScene(){
        Group group = new Group();
        group.getChildren().add(makeGameSceneBackGround());
        group.getChildren().add(makeGameSceneTexts());
        group.getChildren().add(makeTableGraphic());
        Scene scene = new Scene(group,800,700);
        scene.setOnKeyPressed(new EventHandler<KeyEvent>() {
            @Override
            public void handle(KeyEvent keyEvent) {
                String pressedKey = keyEvent.getCode().getChar();
                char pressChar = pressedKey.charAt(0);
                Player.getInstance().onKeyPress(pressChar);
            }
        });
        return scene;
    }

    private void update(Label actMaxScore,Label actScore,ImageView nextLevelImageView){
        Image[][] images = makeImages();
        Table.getInstance().setImageViews(images);
        actScore.setText(String.valueOf(Game.getInstance().getGameSet().getScore()));
        actMaxScore.setText(String.valueOf(Game.getInstance().getLoggedUser().getMaxScore()));
        if (Game.getInstance().getGameSet().isGameOver()){
            gameOverScene = makeGameOverScene();
            stage.setScene(gameOverScene);
        }
        if (Game.getInstance().getGameSet().getVirusCounts() == 0) {
            Game.getInstance().getGameSet().setNextLevelAccessible(true);
            nextLevelImageView.setOpacity(1);
            Game.getInstance().pauseGame();
        }
    }

    private Scene makeGameOverScene(){
        Group group = new Group();
        Image backGround = new Image("images/GameOver.png");
        ImageView backGroundView = new ImageView(backGround);
        group.getChildren().add(backGroundView);
        Label score = new Label("Score: " + Game.getInstance().getGameSet().getScore());
        score.setFont(Font.font("COB",FontWeight.BOLD,20));
        score.relocate(30,140);
        Label maxScore = new Label("Max score: "+ Game.getInstance().getLoggedUser().getMaxScore());
        maxScore.setFont(Font.font("COB",FontWeight.BOLD,20));
        maxScore.relocate(30,180);
        group.getChildren().addAll(score,maxScore);
        try {
            User.saveUsers();
        }catch (Exception e) {
        }
        return new Scene(group,GAME_OVER_MENU_WITH,GAME_OVER_MENU_HEIGHT);
    }

    private Parent makeGameSceneTexts(){
        Group root = new Group();
        Label score = new Label("Score : ");
        score.setFont(Font.font("COB",FontWeight.BOLD,25));
        score.relocate(63,120);
        Label maxScore = new Label("Max score : ");
        maxScore.setFont(Font.font("COB",FontWeight.BOLD,25));
        maxScore.relocate(63,210);
        root.getChildren().add(score);
        root.getChildren().add(maxScore);
        return root;
    }

    private void nextLevel(KeyEvent keyEvent) {
        GameSettings.getInstance().levelUp();
        gameScene = makeGameScene();
        Game.getInstance().resumeGame();
    }

    private ImageView makeGameSceneBackGround(){
        Image backGround = new Image("images/MainScene2.png");
        return new ImageView(backGround);
    }

    private Parent makeTableGraphic(){
        Table.getInstance().makeImageViews();
        Group group = new Group();
        addImageViewsToGroup(group);
        Image nextLevelImage = new Image("images/table_images/StageClear.png");
        ImageView nextLevelImageView = new ImageView(nextLevelImage);
        nextLevelImageView.relocate(600,480);
        nextLevelImageView.setOpacity(0);
        nextLevelImageView.setOnKeyPressed(this::nextLevel);
        Label actScore = new Label(String.valueOf(Game.getInstance().getGameSet().getScore()));
        actScore.setFont(Font.font("COB",FontWeight.BOLD,25));
        actScore.relocate(63,150);
        Label actMaxScore = new Label(String.valueOf(Game.getInstance().getLoggedUser().getMaxScore()));
        actMaxScore.setFont(Font.font("COB",FontWeight.BOLD,25));
        actMaxScore.relocate(63,240);
        group.getChildren().addAll(actMaxScore,actScore,nextLevelImageView);
        gameSceneTimeLine = new Timeline(new KeyFrame(Duration.millis(200),e -> update(actMaxScore,actScore,nextLevelImageView)));
        gameSceneTimeLine.setCycleCount(Animation.INDEFINITE);
        gameSceneTimeLine.play();
        return group;
    }

    private void addImageViewsToGroup(Group group){
        ImageView[][] imageViews = Table.getInstance().getImageViews();
        for (int i = 0;i<Table.getInstance().getTableWith();i++){
            for (int j = 0;j<Table.getInstance().getTableLength();j++){
                ImageView temp = imageViews[i][j];
                temp.relocate(305 + (i * 24),240 + (j * 24));
                group.getChildren().add(temp);
            }
        }
    }

    private Image[][] makeImages(){
        Image[][] result = new Image[Table.getInstance().getTableWith()][Table.getInstance().getTableLength()];
        for (int i = 0;i <Table.getInstance().getTableWith();i++){
            for (int j = 0;j <Table.getInstance().getTableLength();j++){
                result[i][j] = loadCellImage(i,j);
            }
        }
        return result;
    }

    private Image loadCellImage(int x, int y) {
        Cell targetCell = Table.getInstance().getCell(x,y);
        String imageAddress = targetCell.getImageAddress();
        Image image = new Image(targetCell.getImageAddress());
        return image;
    }
}
