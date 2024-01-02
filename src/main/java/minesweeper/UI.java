package minesweeper;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.input.MouseButton;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;

public class UI {
    Pane root;
    GridPane gridPane;
    Engine engine;

    Pane topBar;
    Pane main;
    private int width = 800, height = 500, topBarHeight = 35,  row = 10, col = 10, padding = 20;
    public UI(Pane root, GridPane gridPane){
        this.root = root;
        root.setMinWidth(width);
        root.setMinHeight(height);
        gridPane.setLayoutY(topBarHeight);
        this.gridPane = gridPane;
        this.topBar = (Pane) root.lookup("#topBar");
        this.main = (Pane) root.lookup("#main");
        setTopMenu();
        setMain();

    }

    public void setTopMenu(){
        topBar.setMinWidth(width);
        topBar.setMinHeight(topBarHeight);
        Button exitButton = (Button) topBar.lookup("#exitButton");
        exitButton.setMinWidth(topBarHeight);
        exitButton.setMinHeight(topBarHeight);
        exitButton.setLayoutX(width - topBarHeight);
    }
    public void setMain(){
        main.setMinHeight(height - topBarHeight);
        main.setMinWidth(width);
        main.setMaxHeight(height - topBarHeight);
        main.setLayoutY(topBarHeight);

    }





    public void createGrid(Cell[][] cells, int row, int col){
        int btnSize =  (height - topBarHeight-padding*2)/ row;
        for(int i = 0; i < row; i++){
            for(int j = 0; j < col; j++){
                Button btn = createButton(i, j, btnSize);
                cells[i][j].setButton(btn);
                gridPane.add(btn, j, i);
            }
        }

        gridPane.setLayoutX((width - btnSize * col) / 2);
        gridPane.setLayoutY(topBarHeight- padding);

    }
    public Button createButton(int row, int col, int size){
        Button btn = new Button();
        btn.setMinWidth(size);
        btn.setMinHeight(size);
        btn.getStyleClass().add("gridBtn");

        btn.setOnMouseClicked(event -> {

            if (event.getButton() == MouseButton.PRIMARY) {
                engine.buttonLeftClick(row, col);
            } else if (event.getButton() == MouseButton.SECONDARY) {
                engine.buttonRightClick(row, col);
            }
        });
        return btn;


    }
    public int getRow(){ return row; }
    public int getCol(){ return col; }
    public void setEngine(Engine engine){ this.engine = engine; }


}
