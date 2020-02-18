package Game;

import Piece.*;
import View.MainMenu;
import java.io.IOException;
import javax.sound.sampled.UnsupportedAudioFileException;
import Game.*;


/**
 *
 * @author Microsoft
 */
public class Controller {
    public static boolean canUndo = true;
    
    public static void undoMove () throws UnsupportedAudioFileException, IOException {
        try {
            Handler.removegreencolor(Main.prev.getPiece().possiblemoves);
            Main.prev.deselect();
            Main.prev = null;
        }catch (Exception e) {
            System.out.println("null prev");
        }
        for (int i = 0; i < 8; i++) {
            for (int j = 0; j < 8; j++) {
                if (Main.boardState[i][j].getPiece()!= null ) {
                    Main.boardState[i][j].removecheck();
                }
            }
        }
        if (Handler.getHistoryMove().isEmpty() == false) {
            Move move = Handler.delMove();
            boolean ischeck = Main.getKing(Handler.turn).isDanger(Main.boardState, Main.getKing(Handler.turn).x, Main.getKing(Handler.turn).y);
            move.printMove();
            Piece temp = null;
            if (move.typeUndo.equals("normal")) {
                //remove piece di toX dan toY
                temp = Main.boardState[move.toX][move.toY].getPiece(); //Piece toXY
                Main.boardState[move.toX][move.toY].removePiece();
                if (Main.boardState[move.toX][move.toY].getPiece() instanceof King) {
                    Main.boardState[move.toX][move.toY].removePiece();
                }

                //add piece di fromx dan fromy
                Main.boardState[move.fromX][move.fromY].removePiece();
                Main.boardState[move.fromX][move.fromY].setPiece(temp);
                if (move.getPiece() != null) {
                    //set Piece toXY 
                    Main.boardState[move.toX][move.toY].setPiece(move.getPiece());
                    move.getPiece().move(Main.boardState, move.toX, move.toY);
                    Handler.addgreencolor(move.getPiece().possiblemoves);
                    Handler.removegreencolor(move.getPiece().possiblemoves);
                }

                if (ischeck == true) {
                    Handler.removeKingDanger(Main.boardState,Main.getKing(Handler.turn));
                }

                
                temp.move(Main.boardState, move.fromX, move.fromY);
                Handler.addgreencolor(temp.possiblemoves);
                Handler.removegreencolor(temp.possiblemoves);

                if (temp instanceof King) {
                    if (((King) temp).countMove == 1) {
                        ((King) temp).countMove--;
                        ((King) temp).rookadeable = true;
                    }else if (((King) temp).countCheck == 1) {
                        ((King) temp).countCheck--;
                        ((King) temp).rookadeable = true;
                    }
                    ( (King) temp ).x = move.fromX;
                    ( (King) temp ).y= move.fromY;
                }else if (temp instanceof Rook) {
                    if (((Rook) temp).countMove == 1) {
                        Main.getKing(Handler.turn).rookadeable = true;
                        ((Rook) temp).countMove--;
                    }
                }


                Handler.turn = temp.getColor();
                if (temp.getColor() == 0) {
                    MainMenu.getTurnInfo().setText("WHITE PLAYER");
                }else if (temp.getColor() == 1) {
                    MainMenu.getTurnInfo().setText("BLACK PLAYER");
                }
                canUndo = false;
                ischeck = Main.getKing(Handler.turn).isDanger(Main.boardState, Main.getKing(Handler.turn).x, Main.getKing(Handler.turn).y);
                if (ischeck) {
                    Handler.kingDanger(Main.boardState,Main.getKing(Handler.turn));
                }
                Handler.playSound();
            }else if (move.typeUndo.equals("rookade")) {
                Piece temp2 = null;
                int awalRookX = 0,awalRookY = 0;
                if (move.toY == 5) { //posisi rook di y = 4, posisi awal rook di y=7
                    //ambil king
                    temp = Main.boardState[move.toX][move.toY].getPiece();
                    Main.boardState[move.toX][move.toY].removePiece();
                    //ambil rook
                    temp2 = Main.boardState[move.toX][4].getPiece();
                    Main.boardState[move.toX][4].removePiece();
                    //setPosisi awal rook
                    awalRookX = move.toX;
                    awalRookY = 7;
                }else if (move.toY == 1){ // posisi rook di y = 2, posisi awal rook di y=0
                    //ambil king
                    temp = Main.boardState[move.toX][move.toY].getPiece();
                    Main.boardState[move.toX][move.toY].removePiece();
                    //ambil rook
                    temp2 = Main.boardState[move.toX][2].getPiece();
                    Main.boardState[move.toX][2].removePiece();
                    //setPosisi awal rook
                    awalRookX = move.toX;
                    awalRookY = 0;
                }
                if (temp != null && temp2 != null) {
                    //temp = raja, temp2 = rook
                    
                    //set piece di tempat awal
                    Main.boardState[move.fromX][move.fromY].setPiece(temp);
                    Main.boardState[awalRookX][awalRookY].setPiece(temp2);
                    
                    //kembalikan status move dan rookade
                    Main.getKing(Handler.turn).countMove--;
                    ((Rook)temp2).countMove--;
                    Main.getKing(Handler.turn).rookadeable = true;
                    
                    //gerakan
                    //raja
                    temp.move(Main.boardState, move.fromX, move.fromY);
                    Handler.addgreencolor(temp.possiblemoves);
                    Handler.removegreencolor(temp.possiblemoves);
                    
                    //rook
                    temp2.move(Main.boardState, move.fromX, awalRookY);
                    Handler.addgreencolor(temp2.possiblemoves);
                    Handler.removegreencolor(temp2.possiblemoves);
                }
                
                //set posisi raja baru
                ((King)temp).x = move.fromX;
                ((King)temp).y = move.fromY;
                Handler.turn = temp.getColor();
                if (temp.getColor() == 0) {
                    MainMenu.getTurnInfo().setText("WHITE PLAYER");
                }else if (temp.getColor() == 1) {
                    MainMenu.getTurnInfo().setText("BLACK PLAYER");
                }
                
                canUndo = false;
                ischeck = Main.getKing(Handler.turn).isDanger(Main.boardState, Main.getKing(Handler.turn).x, Main.getKing(Handler.turn).y);
                if (ischeck) {
                    Handler.kingDanger(Main.boardState,Main.getKing(Handler.turn));
                }
                Handler.playSound();
            }else if (move.typeUndo.equals("upgrade")){
                //remove piece di toX dan toY
                if (Handler.turn == 0 ) { 
                    temp = new Pawn("Black_Pawn" , "Black_Pawn.png" , 1);
                }else {
                    temp = new Pawn("White_Pawn" , "White_Pawn.png" , 0);
                }
                Main.boardState[move.toX][move.toY].removePiece();

                //add piece di fromx dan fromy
                Main.boardState[move.fromX][move.fromY].removePiece();
                Main.boardState[move.fromX][move.fromY].setPiece(temp);
                if (move.getPiece() != null) {
                    //set Piece toXY 
                    Main.boardState[move.toX][move.toY].setPiece(move.getPiece());
                    move.getPiece().move(Main.boardState, move.toX, move.toY);
                    Handler.addgreencolor(move.getPiece().possiblemoves);
                    Handler.removegreencolor(move.getPiece().possiblemoves);
                }

                if (ischeck == true) {
                    Handler.removeKingDanger(Main.boardState,Main.getKing(Handler.turn));
                }

                temp.move(Main.boardState, move.fromX, move.fromY);
                Handler.addgreencolor(temp.possiblemoves);
                Handler.removegreencolor(temp.possiblemoves);

                
                Handler.turn = temp.getColor();
                if (temp.getColor() == 0) {
                    MainMenu.getTurnInfo().setText("WHITE PLAYER");
                }else if (temp.getColor() == 1) {
                    MainMenu.getTurnInfo().setText("BLACK PLAYER");
                }
                canUndo = false;
                ischeck = Main.getKing(Handler.turn).isDanger(Main.boardState, Main.getKing(Handler.turn).x, Main.getKing(Handler.turn).y);
                if (ischeck) {
                    Handler.kingDanger(Main.boardState,Main.getKing(Handler.turn));
                }
                Handler.playSound();
            }
        }
    }
    
    
}
