package minesweeper;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
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
    @FXML
    Button settings;
    double xOffset, yOffset;
    public void initialize(){
        Configurations config = new Configurations();
        UI ui = new UI(root, config);
        Engine engine = new Engine(ui,config);
        ui.setEngine(engine);
        ui.createGrid(engine.board.cells, config.getRow(), config.getCol());
    }
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
    public void settingsButtonClick(){
        System.out.println("settings");
    }
    public void exit() {
        System.exit(0);
    }
}
