package minesweeper;

import javafx.animation.AnimationTimer;
import javafx.animation.KeyFrame;
import javafx.animation.PauseTransition;
import javafx.animation.Timeline;
import javafx.event.ActionEvent;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.MenuButton;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Pane;
import javafx.scene.layout.StackPane;
import javafx.util.Duration;


public class UI {
    Pane root;
    GridPane gridPane;
    Engine engine;
    Configurations config;
    Pane topBar;
    StackPane main;
    Button newGame;
    Label timerLabel;
    AnimationTimer timer;
    private int currentTime;
    private int width, height, topBarHeight, padding;
    public UI(Pane root, Configurations config){
        this.config = config;
        this.root = root;
        this.gridPane = (GridPane) root.lookup("#gridPane");
        this.topBar = (Pane) root.lookup("#topBar");
        this.main = (StackPane) root.lookup("#main");
        this.width = config.getWidth();
        this.height = config.getHeight();
        this.topBarHeight = config.getTopBarHeight();
        this.padding = config.getPadding();
        this.newGame = (Button) root.lookup("#startNewGameButton");
        this.timerLabel = (Label) root.lookup("#timerLabel");
        root.setMinWidth(width);
        root.setMinHeight(height);
        setTopMenu();
        setMain();
        setIcons(true);
        resetTimer();
        setTimer();

    }
    public void setTopMenu(){
        topBar.setMinWidth(width);
        topBar.setMinHeight(topBarHeight);
    }
    public void setIcons(boolean theme){
        if(theme){
            setIcon(newGame,"D:\\41n\\java\\Minesweeper\\src\\main\\resources\\minesweeper\\icons\\resetDark.png" );
        }

    }
    public void setMain(){
        main.setMinHeight(height - topBarHeight);
        main.setMinWidth(width);
        main.setMaxHeight(height - topBarHeight);
        main.setLayoutY(topBarHeight);

    }
    public void createGrid(Cell[][] cells, int row, int col){
        resetTimer();
        gridPane.getChildren().clear();
        int btnHeight =  (height - topBarHeight - padding * 2 )/ row;
        int btnWidth =  (width - padding * 2 )/ col ;
        int btnSize = Math.min(btnHeight, btnWidth);
        for(int i = 0; i < row; i++){
            for(int j = 0; j < col; j++){
                Button btn = createButton(i, j, btnSize);
                cells[i][j].setButton(btn);
                gridPane.add(btn, j, i);
            }
        }

        gridPane.setLayoutX((double) (width - btnSize * col) / 2);
        gridPane.setLayoutY(topBarHeight - padding);

    }
    public Button createButton(int row, int col, int size){
        Button btn = new Button();
        btn.setMinWidth(size);
        btn.setMinHeight(size);
        btn.getStyleClass().add("gridBtn");
        engine.setOnMouseClicked(btn, row, col);

        return btn;
    }
    public void gameEnd(boolean gameRes){
        stopTimer();
        Label res = (Label) root.lookup("#endGameResLabel");
        StackPane stackPane = (StackPane) root.lookup("#endGameResPane");
        stackPane.setVisible(true);
        if(gameRes){
            res.setText("YOU WIN!");
            res.setStyle("-fx-background-color: rgba(8,178,85, 0.7);");
        }else{
            res.setText("YOU LOSE!");
            res.setStyle("-fx-background-color: rgba(255,8,8, 0.7);");
        }

    }
    public void setEngine(Engine engine){
        this.engine = engine;
    }
    public void setIcon(Node node, String path){
        Image icon = new Image(path);
        ImageView imageView = new ImageView(icon);
        imageView.setFitWidth(20);
        imageView.setFitHeight(20);
        if(node instanceof Button){
            ((Button) node).setGraphic(imageView);
        }

    }
    public void setTimer(){
        timer = new AnimationTimer() {
            @Override
            public void handle(long now) {
                currentTime++;
                int seconds = currentTime / 60;
                int minutes = seconds / 60;
                int hours = minutes / 60;
                timerLabel.setText(String.format("%02d:%02d:%02d",hours, minutes, seconds % 60));
            }
        };
    }
    public void startTimer() {
        resetTimer();
        timer.start();
    }
    public void resetTimer() {
        currentTime = 0;
        timerLabel.setText("00:00:00");
    }
    public void stopTimer() {
        timer.stop();
    }



}
