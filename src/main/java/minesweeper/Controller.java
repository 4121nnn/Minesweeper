package minesweeper;

import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.MenuButton;
import javafx.scene.control.MenuItem;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Pane;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;
import javafx.util.Duration;

public class Controller {
    @FXML
    Pane root;
    @FXML
    GridPane gridPane;
    @FXML
    MenuButton settings;
    Configurations config;
    double xOffset, yOffset;
    String difficulty = "Easy";
    UI ui;
    public void initialize(){
        this.config = new Configurations(difficulty);
        this.ui = new UI(root, config);
        Engine engine = new Engine(ui,config);
        ui.setEngine(engine);
        ui.createGrid(engine.board.cells, config.getRow(), config.getCol());
        ui.stopTimer();
        ui.resetTimer();
        ui.setTimer();
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
    @FXML
    public void startNewGame(){
        StackPane stackPane = (StackPane) root.lookup("#endGameResPane");
        stackPane.setVisible(false);
        initialize();
    }
    @FXML
    private void settings(ActionEvent event) {

        MenuItem menuItem = (MenuItem) event.getSource();
        this.difficulty = menuItem.getText();
        MenuButton menuButton = (MenuButton) menuItem.getParentPopup().getOwnerNode();
        menuButton.setText(difficulty);
        initialize();
    }
    public void exit() {
        System.exit(0);
    }
}
