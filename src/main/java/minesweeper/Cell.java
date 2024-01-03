package minesweeper;

import javafx.scene.control.Button;

public class Cell {
    boolean isMine, isFlagged, isUncovered;
    int adjacentMines;
    Button btn;
    public void setButton(Button button){
        btn = button;
    }
    public Button getButton(){
        return btn;
    }
    public void setFlag(){
        if(!isUncovered) isFlagged = !isFlagged;
    }
    public void setCovered(){
        isUncovered = true;
        if(getAdjacentMines() > 0) {
            btn.setText("" + getAdjacentMines());
        }
        btn.setStyle("-fx-background-color: #F4F4F4;");
    }
    public void setMine(){
        isMine = true;
    }
    public void setAdjacentMines(int n){
        adjacentMines = n;
    }
    public int getAdjacentMines(){
        return adjacentMines;
    }


}
