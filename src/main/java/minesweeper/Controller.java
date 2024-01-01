package minesweeper;

import javafx.fxml.FXML;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Pane;

public class Controller {
    @FXML
    Pane root;
    @FXML
    GridPane gridPane;

    public void initialize(){
        UI UI = new UI(root, gridPane);
        Engine engine = new Engine(UI,10);
        UI.setEngine(engine);
        UI.createGrid(engine.board.cells, UI.getRow(), UI.getCol());

    }



}
