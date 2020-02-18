package Piece;

import Game.Cell;
import java.util.ArrayList;
import Piece.*;

public class Bishop extends Piece {

    public Bishop(String id , String path , int color) {
        super(color, id, path);
    }

    @Override
    public final ArrayList<Cell> move(Cell pos[][], int x, int y) {
        possiblemoves.clear();
        // barat selatan
        int tempx = x+1;
        int tempy = y-1;
        while ( tempx <= 7 && tempy >= 0 ) {
            Piece cek = pos[tempx][tempy].getPiece();
            if (cek == null) {
                possiblemoves.add(pos[tempx][tempy]);
            }else if (cek.getColor() == this.getColor()) {
                break;
            }else {
                possiblemoves.add(pos[tempx][tempy]);
                break;
            }
            tempx++;
            tempy--;
        }
        // barat utara
        tempx = x-1;
        tempy = y-1;
        while ( tempx >=0 && tempy >= 0 ) {
            Piece cek = pos[tempx][tempy].getPiece();
            if (cek == null) {
                possiblemoves.add(pos[tempx][tempy]);
            }else if (cek.getColor() == this.getColor()) {
                break;
            }else {
                possiblemoves.add(pos[tempx][tempy]);
                break;
            }
            tempx--;
            tempy--;
        }
        // timur utara
        tempx = x-1;
        tempy = y+1;
        while ( tempx >=0 && tempy <= 7 ) {
            Piece cek = pos[tempx][tempy].getPiece();
            if (cek == null) {
                possiblemoves.add(pos[tempx][tempy]);
            }else if (cek.getColor() == this.getColor()) {
                break;
            }else {
                possiblemoves.add(pos[tempx][tempy]);
                break;
            }
            tempx--;
            tempy++;
        }
        // timur selatan
        tempx = x+1;
        tempy = y+1;
        while ( tempx <= 7 && tempy <= 7 ) {
            Piece cek = pos[tempx][tempy].getPiece();
            if (cek == null) {
                possiblemoves.add(pos[tempx][tempy]);
            }else if (cek.getColor() == this.getColor()) {
                break;
            }else {
                possiblemoves.add(pos[tempx][tempy]);
                break;
            }
            tempx++;
            tempy++;
        }
        return possiblemoves;
    }

    @Override
    public ArrayList<Cell> check(Cell[][] pos, int x, int y) {
        checkMove.clear();
        // barat selatan
        int tempx = x+1;
        int tempy = y-1;
        while ( tempx <= 7 && tempy >= 0 ) {
            Piece cek = pos[tempx][tempy].getPiece();
            if (cek == null) {
                checkMove.add(pos[tempx][tempy]);
            }else if (cek.getColor() == this.getColor()) {
                checkMove.add(pos[tempx][tempy]);
                break;
            }else {
                checkMove.add(pos[tempx][tempy]);
                break;
            }
            tempx++;
            tempy--;
        }
        // barat utara
        tempx = x-1;
        tempy = y-1;
        while ( tempx >=0 && tempy >= 0 ) {
            Piece cek = pos[tempx][tempy].getPiece();
            if (cek == null) {
                checkMove.add(pos[tempx][tempy]);
            }else if (cek.getColor() == this.getColor()) {
                checkMove.add(pos[tempx][tempy]);
                break;
            }else {
                checkMove.add(pos[tempx][tempy]);
                break;
            }
            tempx--;
            tempy--;
        }
        // timur utara
        tempx = x-1;
        tempy = y+1;
        while ( tempx >=0 && tempy <= 7 ) {
            Piece cek = pos[tempx][tempy].getPiece();
            if (cek == null) {
                checkMove.add(pos[tempx][tempy]);
            }else if (cek.getColor() == this.getColor()) {
                checkMove.add(pos[tempx][tempy]);
                break;
            }else {
                checkMove.add(pos[tempx][tempy]);
                break;
            }
            tempx--;
            tempy++;
        }
        // timur selatan
        tempx = x+1;
        tempy = y+1;
        while ( tempx <= 7 && tempy <= 7 ) {
            Piece cek = pos[tempx][tempy].getPiece();
            if (cek == null) {
                checkMove.add(pos[tempx][tempy]);
            }else if (cek.getColor() == this.getColor()) {
                checkMove.add(pos[tempx][tempy]);
                break;
            }else {
                checkMove.add(pos[tempx][tempy]);
                break;
            }
            tempx++;
            tempy++;
        }
        return checkMove;
    }
}
