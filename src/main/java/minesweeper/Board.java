package minesweeper;

public class Board {
    Cell[][] cells;
    public Board(int row, int col , int mines){
        cells = new Cell[row][col];

        for(int i = 0; i < row; i++){
            for(int j = 0; j < col; j++){
                cells[i][j] = new Cell();
            }
        }
    }


}
