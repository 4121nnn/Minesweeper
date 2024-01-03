package minesweeper;

import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Pane;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;

public class Controller {
    @FXML
    Pane root;
    @FXML
    GridPane gridPane;

    public void initialize(){
        Configurations config = new Configurations();
        UI ui = new UI(root, config);
        Engine engine = new Engine(ui,config);
        ui.setEngine(engine);
        ui.createGrid(engine.board.cells, config.getRow(), config.getCol());
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
    public void startNewGame(){
        gridPane.getChildren().clear();
        StackPane stackPane = (StackPane) root.lookup("#endGameResPane");
        stackPane.setVisible(false);
        initialize();
    }
    public void exit() {
        System.exit(0);
    }
}
