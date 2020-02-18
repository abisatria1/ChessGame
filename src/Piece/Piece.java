package Piece;

import Game.Cell;
import java.util.ArrayList;

public abstract class Piece implements Cloneable{
    private int color;
    private String id;
    private String path;
    public ArrayList<Cell> possiblemoves = new ArrayList<>();
    public ArrayList<Cell> checkMove = new ArrayList<>();
 
    public Piece(int color, String id, String path) {
        this.color = color;
        this.id = id;
        this.path = path;
    }

    public abstract ArrayList<Cell> move (Cell pos[][] , int x , int y);
    public abstract ArrayList<Cell> check (Cell pos[][] , int x , int y);

    public int getColor() {
        return color;
    }

    public String getId() {
        return id;
    }

    public String getPath() {
        return path;
    }
    

    public Piece getcopy() throws CloneNotSupportedException {
        return (Piece) this.clone();
    }
}
