package minesweeper;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;

public class Controller {
    @FXML
    Pane root;
    @FXML
    GridPane gridPane;

    public void initialize(){
        UI UI = new UI(root, gridPane);
        Engine engine = new Engine(UI,35);
        UI.setEngine(engine);
        UI.createGrid(engine.board.cells, UI.getRow(), UI.getCol());

    }
    double xOffset, yOffset;

    @FXML
    private void handleMousePressed(MouseEvent event) {
        xOffset = event.getSceneX();
        yOffset = event.getSceneY();
    }
    @FXML
    private void handleMouseDragged(MouseEvent event) {
        Stage stage = (Stage) root.getScene().getWindow();
        stage.setX(event.getScreenX() - xOffset);
        stage.setY(event.getScreenY() - yOffset);
    }


    public void exit() { System.exit(0); }
}
