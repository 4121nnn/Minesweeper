package minesweeper;

import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Pane;
import javafx.scene.layout.StackPane;


public class UI {
    Pane root;
    GridPane gridPane;
    Engine engine;
    Configurations config;
    Pane topBar;
    Pane main;
    Button newGame;
    private int width, height, topBarHeight, padding;
    public UI(Pane root, Configurations config){
        this.config = config;
        this.root = root;
        this.gridPane = (GridPane) root.lookup("#gridPane");
        this.topBar = (Pane) root.lookup("#topBar");
        this.main = (Pane) root.lookup("#main");
        this.width = config.getWidth();
        this.height = config.getHeight();
        this.topBarHeight = config.getTopBarHeight();
        this.padding = config.getPadding();
        this.newGame = (Button) root.lookup("#startNewGameButton");
        root.setMinWidth(width);
        root.setMinHeight(height);
        gridPane.setLayoutY(topBarHeight);
        setTopMenu();
        setMain();


    }

    public void setTopMenu(){
        topBar.setMinWidth(width);
        topBar.setMinHeight(topBarHeight);
        Button exitButton = (Button) topBar.lookup("#exitButton");
        //exitButton.setLayoutX(width - topBarHeight);
    }
    public void setMain(){
        main.setMinHeight(height - topBarHeight);
        main.setMinWidth(width);
        main.setMaxHeight(height - topBarHeight);
        main.setLayoutY(topBarHeight);

    }

    public void createGrid(Cell[][] cells, int row, int col){
        int btnSize =  (height - topBarHeight - padding * 2)/ row;
        for(int i = 0; i < row; i++){
            for(int j = 0; j < col; j++){
                Button btn = createButton(i, j, btnSize);
                cells[i][j].setButton(btn);
                gridPane.add(btn, j, i);
            }
        }

        gridPane.setLayoutX((double) (width - btnSize * col) / 2);
        gridPane.setLayoutY(topBarHeight- padding);

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
        Label res = (Label) root.lookup("#endGameResLabel");
        StackPane stackPane = (StackPane) root.lookup("#endGameResPane");
        stackPane.setVisible(true);
        if(gameRes){
            res.setText("YOU WIN!");
            res.setStyle("-fx-background-color: rgba(8,178,85, 0.5);");
        }else{
            res.setText("YOU LOSE!");
            res.setStyle("-fx-background-color: rgba(255,8,8, 0.5);");
        }

    }


    public void setEngine(Engine engine){ this.engine = engine; }


}
