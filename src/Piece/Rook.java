package Piece;


import Game.Cell;
import java.util.ArrayList;
/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author Microsoft
 */
public class Rook extends Piece{
    public int countMove = 0;
    
    public Rook(String id, String path , int color) {
        super(color, id, path);
    }

    @Override
    public ArrayList<Cell> move(Cell[][] pos, int x, int y) {
        possiblemoves.clear();
        
        // gerak ke utara 
        int tempy = y;
        int tempx = x-1;
        while (tempx >= 0 && tempy==y ) {
            if (pos[tempx][tempy].getPiece() == null) {
                possiblemoves.add(pos[tempx][tempy]);
            }else if (pos[tempx][tempy].getPiece().getColor() == this.getColor()) {
                break;
            }else if (pos[tempx][tempy].getPiece().getColor() != this.getColor()) {
                possiblemoves.add(pos[tempx][tempy]);
                break;
            }
            tempx--;
        }
        
        //gerak ke selatan
        tempx = x+1;
        while (tempx <= 7 && tempy==y ) {
            if (pos[tempx][tempy].getPiece() == null) {
                possiblemoves.add(pos[tempx][tempy]);
            }else if (pos[tempx][tempy].getPiece().getColor() == this.getColor()) {
                break;
            }else if (pos[tempx][tempy].getPiece().getColor() != this.getColor()) {
                possiblemoves.add(pos[tempx][tempy]);
                break;
            }
            tempx++;
        }
        
        //gerak ke barat
        tempx  = x;
        tempy = y-1;
        while (tempx ==x && tempy>=0 ) {
            if (pos[tempx][tempy].getPiece() == null) {
                possiblemoves.add(pos[tempx][tempy]);
            }else if (pos[tempx][tempy].getPiece().getColor() == this.getColor()) {
                break;
            }else if (pos[tempx][tempy].getPiece().getColor() != this.getColor()) {
                possiblemoves.add(pos[tempx][tempy]);
                break;
            }
            tempy--;
        }
        
        //gerak ke timur
        tempx = x;
        tempy = y+1;
        while (tempx == x && tempy<=7 ) {
            if (pos[tempx][tempy].getPiece() == null) {
                possiblemoves.add(pos[tempx][tempy]);
            }else if (pos[tempx][tempy].getPiece().getColor() == this.getColor()) {
                break;
            }else if (pos[tempx][tempy].getPiece().getColor() != this.getColor()) {
                possiblemoves.add(pos[tempx][tempy]);
                break;
            }
            tempy++;
        }
        
        return possiblemoves;
    }

    @Override
    public ArrayList<Cell> check(Cell[][] pos, int x, int y) {
        checkMove.clear();
        
        // gerak ke utara 
        int tempy = y;
        int tempx = x-1;
        while (tempx >= 0 && tempy==y ) {
            if (pos[tempx][tempy].getPiece() == null) {
                checkMove.add(pos[tempx][tempy]);
            }else if (pos[tempx][tempy].getPiece().getColor() == this.getColor()) {
                checkMove.add(pos[tempx][tempy]);
                break;
            }else if (pos[tempx][tempy].getPiece().getColor() != this.getColor()) {
                checkMove.add(pos[tempx][tempy]);
                break;
            }
            tempx--;
        }
        
        //gerak ke selatan
        tempx = x+1;
        while (tempx <= 7 && tempy==y ) {
            if (pos[tempx][tempy].getPiece() == null) {
                checkMove.add(pos[tempx][tempy]);
            }else if (pos[tempx][tempy].getPiece().getColor() == this.getColor()) {
                checkMove.add(pos[tempx][tempy]);
                break;
            }else if (pos[tempx][tempy].getPiece().getColor() != this.getColor()) {
                checkMove.add(pos[tempx][tempy]);
                break;
            }
            tempx++;
        }
        
        //gerak ke barat
        tempx  = x;
        tempy = y-1;
        while (tempx ==x && tempy>=0 ) {
            if (pos[tempx][tempy].getPiece() == null) {
                checkMove.add(pos[tempx][tempy]);
            }else if (pos[tempx][tempy].getPiece().getColor() == this.getColor()) {
                checkMove.add(pos[tempx][tempy]);
                break;
            }else if (pos[tempx][tempy].getPiece().getColor() != this.getColor()) {
                checkMove.add(pos[tempx][tempy]);
                break;
            }
            tempy--;
        }
        
        //gerak ke timur
        tempx = x;
        tempy = y+1;
        while (tempx == x && tempy<=7 ) {
            if (pos[tempx][tempy].getPiece() == null) {
                checkMove.add(pos[tempx][tempy]);
            }else if (pos[tempx][tempy].getPiece().getColor() == this.getColor()) {
                checkMove.add(pos[tempx][tempy]);
                break;
            }else if (pos[tempx][tempy].getPiece().getColor() != this.getColor()) {
                checkMove.add(pos[tempx][tempy]);
                break;
            }
            tempy++;
        }
        
        return checkMove;
    }
    
}
