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
public class Queen extends Piece{

    public Queen( String id, String path , int color) {
        super(color, id, path);
    }

    @Override
    public ArrayList<Cell> move(Cell[][] pos, int x, int y) {
        possiblemoves.clear();
        //gerak merupakan gabungan benteng dan peluncur
        //bishop
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
        
        //rook
        // gerak ke utara 
        tempy = y;
        tempx = x-1;
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
        //gerak merupakan gabungan benteng dan peluncur
        //bishop
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
        
        //rook
        // gerak ke utara 
        tempy = y;
        tempx = x-1;
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
