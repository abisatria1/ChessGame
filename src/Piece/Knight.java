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
public class Knight extends Piece {

    public Knight(String id, String path , int color) {
        super(color, id, path);
    }

    @Override
    public ArrayList<Cell> move(Cell[][] pos, int x, int y) {
        possiblemoves.clear();
        int tempx,tempy;
        Cell cek;
        //gerak 1
        tempx = x-2;
        tempy = y-1;
        if (tempx >= 0 && tempy >= 0 ) {
            cek = pos[tempx][tempy];
            if (cek.getPiece() == null ) {
                possiblemoves.add(cek);
            }else if (cek.getPiece().getColor() != this.getColor()) {
                possiblemoves.add(cek);
            }
        }
        
        //gerak 2
        tempx = x-2;
        tempy = y+1;
        if (tempx >= 0 && tempy <= 7 ) {
            cek = pos[tempx][tempy];
            if (cek.getPiece() == null ) {
                possiblemoves.add(cek);
            }else if (cek.getPiece().getColor() != this.getColor()) {
                possiblemoves.add(cek);
            }
        }
        
        //gerak 3
        tempx = x-1;
        tempy = y+2;
        if (tempx >= 0 && tempy <= 7 ) {
            cek = pos[tempx][tempy];
            if (cek.getPiece() == null ) {
                possiblemoves.add(cek);
            }else if (cek.getPiece().getColor() != this.getColor()) {
                possiblemoves.add(cek);
            }
        }
        
        //gerak 4
        tempx = x+1;
        tempy = y+2;
        if (tempx <= 7 && tempy <= 7 ) {
            cek = pos[tempx][tempy];
            if (cek.getPiece() == null ) {
                possiblemoves.add(cek);
            }else if (cek.getPiece().getColor() != this.getColor()) {
                possiblemoves.add(cek);
            }
        }
        
        //gerak 5
        tempx = x+2;
        tempy = y+1;
        if (tempx <= 7 && tempy <= 7 ) {
            cek = pos[tempx][tempy];
            if (cek.getPiece() == null ) {
                possiblemoves.add(cek);
            }else if (cek.getPiece().getColor() != this.getColor()) {
                possiblemoves.add(cek);
            }
        }
        
        //gerak 6
        tempx = x+2;
        tempy = y-1;
        if (tempx <= 7 && tempy >= 0 ) {
            cek = pos[tempx][tempy];
            if (cek.getPiece() == null ) {
                possiblemoves.add(cek);
            }else if (cek.getPiece().getColor() != this.getColor()) {
                possiblemoves.add(cek);
            }
        }
        
        //gerak 7
        tempx = x+1;
        tempy = y-2;
        if (tempx <= 7 && tempy >= 0 ) {
            cek = pos[tempx][tempy];
            if (cek.getPiece() == null ) {
                possiblemoves.add(cek);
            }else if (cek.getPiece().getColor() != this.getColor()) {
                possiblemoves.add(cek);
            }
        }
        
        //gerak 8
        tempx = x-1;
        tempy = y-2;
        if (tempx >= 0 && tempy >= 0 ) {
            cek = pos[tempx][tempy];
            if (cek.getPiece() == null ) {
                possiblemoves.add(cek);
            }else if (cek.getPiece().getColor() != this.getColor()) {
                possiblemoves.add(cek);
            }
        }
        
        return possiblemoves;
    }

    @Override
    public ArrayList<Cell> check(Cell[][] pos, int x, int y) {
        checkMove.clear();
        int tempx,tempy;
        Cell cek;
        //gerak 1
        tempx = x-2;
        tempy = y-1;
        if (tempx >= 0 && tempy >= 0 ) {
            cek = pos[tempx][tempy];
            if (cek.getPiece() == null ) {
                checkMove.add(cek);
            }else if (cek.getPiece().getColor() != this.getColor()) {
                checkMove.add(cek);
            }else {
                checkMove.add(cek);
            }
        }
        
        //gerak 2
        tempx = x-2;
        tempy = y+1;
        if (tempx >= 0 && tempy <= 7 ) {
            cek = pos[tempx][tempy];
            if (cek.getPiece() == null ) {
                checkMove.add(cek);
            }else if (cek.getPiece().getColor() != this.getColor()) {
                checkMove.add(cek);
            }else {
                checkMove.add(cek);
            }
        }
        
        //gerak 3
        tempx = x-1;
        tempy = y+2;
        if (tempx >= 0 && tempy <= 7 ) {
            cek = pos[tempx][tempy];
            if (cek.getPiece() == null ) {
                checkMove.add(cek);
            }else if (cek.getPiece().getColor() != this.getColor()) {
                checkMove.add(cek);
            }else {
                checkMove.add(cek);
            }
        }
        
        //gerak 4
        tempx = x+1;
        tempy = y+2;
        if (tempx <= 7 && tempy <= 7 ) {
            cek = pos[tempx][tempy];
            if (cek.getPiece() == null ) {
                checkMove.add(cek);
            }else if (cek.getPiece().getColor() != this.getColor()) {
                checkMove.add(cek);
            }else {
                checkMove.add(cek);
            }
        }
        
        //gerak 5
        tempx = x+2;
        tempy = y+1;
        if (tempx <= 7 && tempy <= 7 ) {
            cek = pos[tempx][tempy];
            if (cek.getPiece() == null ) {
                checkMove.add(cek);
            }else if (cek.getPiece().getColor() != this.getColor()) {
                checkMove.add(cek);
            }else {
                checkMove.add(cek);
            }
        }
        
        //gerak 6
        tempx = x+2;
        tempy = y-1;
        if (tempx <= 7 && tempy >= 0 ) {
            cek = pos[tempx][tempy];
            if (cek.getPiece() == null ) {
                checkMove.add(cek);
            }else if (cek.getPiece().getColor() != this.getColor()) {
                checkMove.add(cek);
            }else {
                checkMove.add(cek);
            }
        }
        
        //gerak 7
        tempx = x+1;
        tempy = y-2;
        if (tempx <= 7 && tempy >= 0 ) {
            cek = pos[tempx][tempy];
            if (cek.getPiece() == null ) {
                checkMove.add(cek);
            }else if (cek.getPiece().getColor() != this.getColor()) {
                checkMove.add(cek);
            }else {
                checkMove.add(cek);
            }
        }
        
        //gerak 8
        tempx = x-1;
        tempy = y-2;
        if (tempx >= 0 && tempy >= 0 ) {
            cek = pos[tempx][tempy];
            if (cek.getPiece() == null ) {
                checkMove.add(cek);
            }else if (cek.getPiece().getColor() != this.getColor()) {
                checkMove.add(cek);
            }else {
                checkMove.add(cek);
            }
        }
        
        return checkMove;
    }
    
}
