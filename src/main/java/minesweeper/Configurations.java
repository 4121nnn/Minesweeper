package minesweeper;

public class Configurations {
    private int width = 800, height = 500, topBarHeight = 35, padding = 20,  row = 10, col = 10,  numMines = 10;

    public int getWidth(){ return width; }
    public int getHeight(){ return height; }
    public int getTopBarHeight(){ return topBarHeight; }
    public int getPadding(){ return padding; }
    public int getRow(){ return row; }
    public int getCol(){ return col; }
    public int getNumMines(){ return numMines; }

    public void setWidth(int width){ this.width = width; }
    public void setHeight(int height){ this.height = height; }
    public void setTopBarHeight(int topBarHeight){ this.topBarHeight = topBarHeight; }
    public void setPadding(int padding){ this.padding = padding; }
    public void setRow(int row){ this.row = row; }
    public void setCol(int col){ this.col = col; }
    public void setNumMines(int numMines){ this.numMines = numMines; }

}
