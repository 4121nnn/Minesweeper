package minesweeper;

import java.util.HashSet;
import java.util.Random;

public class Engine {
    UI UI;
    int mines ;
    static boolean gameIsStarted ;
    Board board;
    public Engine(UI UI, int mines){
        this.UI = UI;
        this.mines = mines;
        gameIsStarted = false;
        board = new Board(UI.getRow(), UI.getCol(), mines);

    }


    public void addMines(Cell[][] cells, int mines, int selectedRow, int selectedCol){
        Random random = new Random();
        int addedMines = 0;
        HashSet<Integer> set = new HashSet<>();
        set.add(selectedRow * 100 + selectedCol - 1);
        set.add(selectedRow * 100 + selectedCol);
        set.add(selectedRow * 100 + selectedCol + 1);

        set.add((selectedRow -1) * 100 + selectedCol - 1);
        set.add((selectedRow -1) * 100 + selectedCol);
        set.add((selectedRow -1) * 100 + selectedCol +1);

        set.add((selectedRow + 1) * 100 + selectedCol - 1);
        set.add((selectedRow + 1) * 100 + selectedCol);
        set.add((selectedRow + 1) * 100 + selectedCol +1);

        int k = 0;
        while(addedMines < mines){
            int r = random.nextInt(cells.length);
            int c = random.nextInt(cells[0].length);
            if(!set.contains(r * 100 + c)) {
                if (!cells[r][c].isMine) {
                    cells[r][c].setMine();
                    addedMines++;
                    set.add((r * 100 + c));
                }
            }
        }
        for(int i = 0; i < cells.length; i++){
            for(int j = 0; j < cells[0].length; j++){
                cells[i][j].setAdjacentMines( countAdjacentMines(cells, i, j) );
            }
        }
    }
    public int countAdjacentMines(Cell[][] cells, int row, int col){
        int count = 0;
            for(int i = row-1; i <= row +1; i++){
                for(int j = col -1; j <= col + 1; j++){
                    if(i>= 0 && j >= 0 && i < cells.length && j < cells[0].length && cells[i][j].isMine) {
                        count++;
                    }
                }
            }

        return count;
    }
    public void buttonLeftClick(int row, int col){
        if(!gameIsStarted){
            addMines(board.cells,  mines, row, col);
            System.out.println("mines added");
            gameIsStarted = true;
            openCell(board.cells, row, col);
        }else{
            openCell(board.cells, row, col);
        }
    }
    public void openCell(Cell[][] cells, int row, int col){
        Cell cell = cells[row][col];
        if(cell.isMine){
            cell.getButton().setText("X");
            System.out.println("Game is over");
        }else if(!cell.isCovered){
            cell.setCovered();
            if(cell.getAdjacentMines() == 0) {
                for(int i = row - 1; i <= row + 1; i++){
                    for(int j = col -1; j <= col +1; j++){
                        if(i>= 0 && j >= 0 && i < cells.length && j < cells[0].length ) {
                            openCell(cells, i, j);
                        }
                    }
                }


            }
        }
    }

}