package minesweeper;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.control.Button;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Pane;

public class UI {
    Pane root;
    GridPane gridPane;
    Engine engine;
    private int width = 800, height = 500, topBarHeight = 25, gridPaneHeight = height - topBarHeight, row = 10, col = 10;
    public UI(Pane root, GridPane gridPane){
        root.setMinWidth(width);
        root.setMinHeight(height);
        gridPane.setLayoutY(topBarHeight);
        this.gridPane = gridPane;

    }

    public void createGrid(Cell[][] cells, int row, int col){
        int btnSize = gridPaneHeight  / row;
        for(int i = 0; i < row; i++){
            for(int j = 0; j < col; j++){
                Button btn = createButton(i, j, btnSize);
                cells[i][j].setButton(btn);
                gridPane.add(btn, j, i);
            }
        }

        gridPane.setLayoutX((width - btnSize * col) / 2);
        gridPane.setLayoutY(topBarHeight);

    }
    public Button createButton(int row, int col, int size){
        Button btn = new Button();
        btn.setMinWidth(size);
        btn.setMinHeight(size);
        btn.getStyleClass().add("gridBtn");

        btn.onActionProperty().set(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                engine.buttonLeftClick(row, col);
            }
        });
        return btn;
    }
    public int getRow(){ return row; }
    public int getCol(){ return col; }
    public void setEngine(Engine engine){ this.engine = engine; }


}
