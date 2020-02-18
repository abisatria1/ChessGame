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
public class Pawn extends Piece {

    public Pawn(String id, String path , int color) {
        super(color, id, path);
    }

    @Override
    public ArrayList<Cell> move(Cell[][] pos, int x, int y) {
        possiblemoves.clear();
        int tempx,tempy;
        Cell cek;
        //pawn putih start
        if (x==6 && this.getColor() == 0) {
            tempx = x-1;
            tempy = y;
            while (tempx >= x-2) {
                cek = pos[tempx][tempy];
                if (cek.getPiece() == null) {
                    possiblemoves.add(cek);
                }else {
                    break;
                }
                tempx--;
            }
        }else if (x==1 && this.getColor() == 1) { // pawn hitam start
            tempx = x+1;
            tempy = y;
            while (tempx <= x+2) {
                cek = pos[tempx][tempy];
                if (cek.getPiece() == null) {
                    possiblemoves.add(cek);
                }else {
                    break;
                }
                tempx++;
            }
        }else { // selain posisi start
            if (this.getColor() == 0 ) {
                //saat pion putih
                tempx = x-1;
                tempy = y;
            }else {
                //saat pion hitam
                tempx = x+1;
                tempy = y;
            }
            if (tempx >= 0  && tempx <= 7){
                cek = pos[tempx][tempy];
                if (cek.getPiece()==null) {
                    possiblemoves.add(cek);
                }
            }
        }
        
        //posisi memakan pion lain 
        if ( this.getColor() == 0 ){
            //pawn putih
            tempx = x-1;
            tempy = y-1;
            while (tempx >= 0 && tempy <= y+1) {
                if (tempy <= 7 && tempy >= 0 && tempx >= 0 && tempx <= 7) {
                    cek = pos[tempx][tempy];
                    if (cek.getPiece() != null) {
                        if (cek.getPiece().getColor() != this.getColor()) {
                            possiblemoves.add(cek);
                        }
                    }
                }
                tempy = tempy +2;
            }
        }else {
            //pawn hitam
                tempx = x+1;
                tempy = y-1;
            while (tempx >= 0 && tempy <= y+1) {
                if (tempy <= 7 && tempy >= 0 && tempx >= 0 && tempx <= 7) {              
                    cek = pos[tempx][tempy];
                    if (cek.getPiece() != null) {
                        if (cek.getPiece().getColor() != this.getColor()) {
                            possiblemoves.add(cek);
                        }
                    }
                }
                tempy = tempy +2;
            }
        }
        return possiblemoves;
        
    }

    @Override
    public ArrayList<Cell> check(Cell[][] pos, int x, int y) {
        checkMove.clear();
        int tempx,tempy;
        Cell cek;
        //posisi memakan pion lain 
        if ( this.getColor() == 0 ){
            //pawn putih
            tempx = x-1;
            tempy = y-1;
            while (tempx >= 0 && tempy <= y+1) {
                if (tempy <= 7 && tempy >= 0 && tempx >= 0 && tempx <= 7) {
                    cek = pos[tempx][tempy];
                    if (cek.getPiece() != null) {
                        if (cek.getPiece().getColor() != this.getColor()) {
                            checkMove.add(cek);
                        }else if (cek.getPiece().getColor() == this.getColor()){
                            checkMove.add(cek);
                        }
                    }else {
                        checkMove.add(cek);
                    }
                }
                tempy = tempy +2;
            }
        }else {
            //pawn hitam
                tempx = x+1;
                tempy = y-1;
            while (tempx >= 0 && tempy <= y+1) {
                if (tempy <= 7 && tempy >= 0 && tempx >= 0 && tempx <= 7) {              
                    cek = pos[tempx][tempy];
                    if (cek.getPiece() != null) {
                        if (cek.getPiece().getColor() != this.getColor()) {
                            checkMove.add(cek);
                        }else if (cek.getPiece().getColor() == this.getColor()){
                            checkMove.add(cek);
                        }
                    }else {
                        checkMove.add(cek);
                    }
                }
                tempy = tempy +2;
            }
        }
        return checkMove;
    }
    
    
}
