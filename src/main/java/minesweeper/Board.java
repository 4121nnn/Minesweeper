package minesweeper;

public class Board {
    private int row, col;
    int mines;
    Cell[][] cells;
    public Board(int row, int col , int mines){
        this.row = row;
        this.col = col;
        this.mines = mines;
        cells = new Cell[row][col];

        for(int i = 0; i < row; i++){
            for(int j = 0; j < col; j++){
                cells[i][j] = new Cell();
            }
        }
    }


}
