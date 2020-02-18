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
public class King extends Piece{
    public int x, y;
    public boolean rookadeable = true;
    public int countMove =0 ,countCheck = 0;
    public ArrayList<Cell> attacker = new ArrayList<>();
    
    public King(String id, String path , int color, int x , int y) {
        super(color, id, path);
        this.x = x;
        this.y = y;
    }

    public boolean isRookadeable() {
        return rookadeable;
    }
    
    

    @Override
    public ArrayList<Cell> move(Cell[][] pos, int x, int y) {
        possiblemoves.clear();
        int tempx[]={x,x,x+1,x+1,x+1,x-1,x-1,x-1};
        int tempy[]={y-1,y+1,y-1,y,y+1,y-1,y,y+1};
        for(int i=0;i<8;i++){
            if((tempx[i]>=0&&tempx[i]<8&&tempy[i]>=0&&tempy[i]<8))
                if(( pos[tempx[i]][tempy[i]].getPiece()==null || pos[tempx[i]][tempy[i]].getPiece().getColor()!=this.getColor()))
                    possiblemoves.add(pos[tempx[i]][tempy[i]]);
        }
        return possiblemoves;
    }
    
    
    @Override
    public ArrayList<Cell> check(Cell[][] pos, int x, int y) {
        checkMove.clear();
        int tempx[]={x,x,x+1,x+1,x+1,x-1,x-1,x-1};
        int tempy[]={y-1,y+1,y-1,y,y+1,y-1,y,y+1};
        for(int i=0;i<8;i++){
            if((tempx[i]>=0&&tempx[i]<8&&tempy[i]>=0&&tempy[i]<8))
                if(( pos[tempx[i]][tempy[i]].getPiece()==null || pos[tempx[i]][tempy[i]].getPiece().getColor()!=this.getColor() || pos[tempx[i]][tempy[i]].getPiece().getColor()==this.getColor()))
                    checkMove.add(pos[tempx[i]][tempy[i]]);
        }
        return checkMove;
    }
    
    
    public boolean isDanger (Cell board[][] , int x , int y) {
        //x y berfungsi untuk mengecek posisi x dan y
        boolean status = false;
        for (int i = 0; i < 8; i++) {
            for (int j = 0; j < 8; j++) {
                if (board[i][j].getPiece() != null) {
                    if ( board[i][j].getPiece().getColor() != this.getColor() ) {
                        //mengecek apakah possible destination dari piece lawan mengenai posisi dari raja
                        board[i][j].getPiece().check(board, i,j);
                        for (Cell m : board[i][j].getPiece().checkMove) {
                            if (m.x == x && m.y == y) {
                                status = true;
                                return true;
                            }
                        }
                    }
                }
            }
        }
        return status;
    }
    
    
    public ArrayList<Cell> getAttacker (Cell board[][]) {
        this.attacker.clear();
        for (int i = 0; i < 8; i++) {
            for (int j = 0; j < 8; j++) {
                if (board[i][j].getPiece() != null) {
                    if ( board[i][j].getPiece().getColor() != this.getColor() ) {
                        //mengecek apakah possible destination dari piece lawan mengenai posisi dari raja
                        board[i][j].getPiece().check(board, i,j);
                        for (Cell m : board[i][j].getPiece().checkMove) {
                            if (m.x == x && m.y == y){
                                attacker.add(board[i][j]);
                            }
                        }
                    }
                }
            }
        }
        
        return attacker;
    } 

    
    
    

    
    
}
