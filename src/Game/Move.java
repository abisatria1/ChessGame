/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Game;
import Piece.*;


/**
 *
 * @author Microsoft
 */
public class Move {
    public int fromX,fromY,toX,toY;
    public Piece piece = null;
    public String typeUndo;

    public Move(int fromX, int fromY, int toX, int toY, Piece piece , String type) {
        this.fromX = fromX;
        this.fromY = fromY;
        this.toX = toX;
        this.toY = toY;
        if (piece != null) 
            this.piece = piece;
        this.typeUndo = type;
    }

   

    public Piece getPiece() {
        return piece;
    }
    
    public void printMove() {
        if ( piece == null ){
            System.out.print(fromX + " " + fromY + " || " + toX + " " + toY + " || " + null);
        }else {
             System.out.print(fromX + " " + fromY + " || " + toX + " " + toY + " || " + piece.getId());
        }
    }
    
    
}
