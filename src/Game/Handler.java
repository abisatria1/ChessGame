package Game;
import Piece.*;
import View.MainMenu;
import ViewHandler.MainMenuHandler;
import ViewHandler.UpgradeHandler;
import java.awt.Color;

import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.File;
import java.io.IOException;

import Game.*;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author Microsoft
 */

import java.util.*;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.sound.sampled.*;
import java.io.FileInputStream;
import java.io.InputStream;
import java.net.URL;
import javax.swing.JOptionPane;
import sun.audio.*;

public class Handler extends MouseAdapter{
    public static int turn = 0;
    private static Stack historyMove = new Stack();
    static boolean isRemis = false;
    
    public static Stack getHistoryMove() {
        return historyMove;
    }
    
    
    public static void addMove (int fromx , int fromy , int toX , int toY , Piece piece, String typeUndo) {
        Move newMove = new Move(fromx,fromy,toX,toY,piece,typeUndo);
        historyMove.push(newMove);
    }
    
    
    
    public static Move delMove () {
        //remove and return last add move
        return (Move) historyMove.pop();
    }
    
    
    public static void removegreencolor (List<Cell> possiblemoves) { // menghilangkan hightlight hijau possiblemoves
        
        if (possiblemoves.isEmpty() == false) {
            for (Cell d : possiblemoves) {
                d.removepossibledestination();
            }
        }
    }

   
    
    public static void addgreencolor (List<Cell> possiblemoves) { // memberi highlight hijau kepada possiblemoves
        for (Cell d : possiblemoves) {
            d.setpossibledestination();
        }
    }
    
    public static void validateKingMove (Cell board[][] , King king) { //memvalidasi gerak king ketika ada yang mau menyerang atau check
        Cell newBoardState[][];
        king.move(board, king.x, king.y);
        board[king.x][king.y].removePiece();
        Iterator<Cell> iter = king.possiblemoves.iterator();
        while (iter.hasNext()) {
            Cell cell = iter.next();
            if ( king.isDanger(board,cell.x,cell.y) == true) {
                iter.remove();
            }
        }
        board[king.x][king.y].setPiece(king);
    }
    
    public static void kingDanger (Cell board[][] , King king) { //membuat cell king menjadi merah ketika keadaan check
        
        //ketika king sedang di skak maka cell nya akan berwarna merah dan tidak bisa bergerak jika sedang danger
        Cell cellKing = board[king.x][king.y];
        cellKing.isCheck = true;
        cellKing.setCheck();
        king.getAttacker(Main.boardState);
        for(Cell k : king.attacker) {
            k.setAttacker();
        }
    }
    
    public void removeKingDanger (Cell board[][] , King king , Cell prev) {//mengembalikan cell king semula
        Cell cellKing = board[prev.x][prev.y];
        cellKing.isCheck = false;
        cellKing.removecheck();
        for (Cell a : king.attacker) {
            a.removeAttacker();
        }
    } 
    
    public static void removeKingDanger (Cell board[][] , King king) {//mengembalikan cell king semula
        Cell cellKing = board[king.x][king.y];
        cellKing.isCheck = false;
        cellKing.removecheck();
        for (Cell a : king.attacker) {
            a.removeAttacker();
        }
    } 
    
    //fungsi untuk validasi possiblemove jika ada langkah yang membahayakan raja
    public static void possibleMoveNotMakeKingCheck (Cell board[][] , Cell from , King king) throws CloneNotSupportedException{ 
        from.getPiece().move(board, from.x, from.y);
        Cell newBoardState[][] = new Cell[8][8];
        //pengisian board baru untuk mengecek
        
        Iterator<Cell> iter = from.getPiece().possiblemoves.iterator();
        while (iter.hasNext()) {
            for (int i = 0; i < 8; i++) {
                for (int j = 0; j < 8; j++) {
                    try {
                        newBoardState[i][j] = new Cell ( board[i][j] );
                    }catch (Exception e) {
                        System.out.println("Error in posible Move not make king check");
                    }
                }
            }
            
            Cell cell = iter.next();
            newBoardState[from.x][from.y].removePiece();
            newBoardState[cell.x][cell.y].setPiece(from.getPiece());
            if ( king.isDanger(newBoardState,king.x,king.y) == true) {
                iter.remove();
            }
            newBoardState[cell.x][cell.y].removePiece();
        }

    }
    
    // memvalidasi gerakan ketika check maka keadaan yang diperbolehkan hanya menjalankan king
    // atau keadaan yang membuat king selamat
    public static void validateCheck (Cell board[][]) { 
        King white = Main.wk;
        King black = Main.bk;
        
        
        if (white.isDanger(board, white.x, white.y) || black.isDanger(board, black.x, black.y)) {
            if (white.isDanger(board, white.x, white.y)) {
                Main.wk.countCheck++;
                kingDanger(board,white);
                white.getAttacker(board);
                for (Cell a : white.attacker) {
                    a.setAttacker();
                }
            }else {
                Main.bk.countCheck++;
                kingDanger(board,black);
                black.getAttacker(board);
                for (Cell a : black.attacker) {
                    a.setAttacker();
                }
            }
            
        }else {
            removeKingDanger(board,white);
            removeKingDanger(board,black);
        }
    }
    
    public static boolean checkMate (Cell board[][]) throws CloneNotSupportedException {
        if (countPiece(1) == 1 && countPiece(0) == 1) {
            isRemis = true;
            JOptionPane.showMessageDialog(null , "Remis");
            GameEnd(MainMenuHandler.getBlackplayer(), MainMenuHandler.getWhiteplayer(), "remis");
            return false;
        }else {
            for (int i = 0; i < 8; i++) {
                for (int j = 0; j < 8; j++) {
                    if (board[i][j].getPiece() != null ) {
                        if (board[i][j].getPiece().getColor() == turn) {
                            board[i][j].getPiece().move(board, i, j);
                            possibleMoveNotMakeKingCheck(board,board[i][j],Main.getKing(turn));
                            if (board[i][j].getPiece() instanceof King) {
                                validateKingMove(board, (King) board[i][j].getPiece());
                            }
                            if (board[i][j].getPiece().possiblemoves.isEmpty() == false) {
                                return false;
                            }
                        }
                    }
                }
            } 
            if (Main.getKing(turn).isDanger(board, Main.getKing(turn).x,Main.getKing(turn).y)) {
                if (turn == 0) {
                    JOptionPane.showMessageDialog(null, "Game End! Black Player wins" );
                    GameEnd(MainMenuHandler.getBlackplayer() , MainMenuHandler.getWhiteplayer(),"checkmate");
                }else {
                    JOptionPane.showMessageDialog(null, "Game End! White Player wins" );
                    GameEnd(MainMenuHandler.getWhiteplayer() , MainMenuHandler.getBlackplayer(),"checkmate");
                }
                return true;
            }else {
                isRemis = true;
                JOptionPane.showMessageDialog(null , "Remis");
                GameEnd(MainMenuHandler.getBlackplayer(), MainMenuHandler.getWhiteplayer(), "remis");
                return false;
            }
        }
    }
    
     public static void giliran () { // mengganti giliran
        if (turn == 0) {
            turn=1;
            Controller.canUndo = true;
            MainMenu.getTurnInfo().setText("BLACK PLAYER");
        }else if (turn == 1) {
            turn=0;
            Controller.canUndo = true;
            MainMenu.getTurnInfo().setText("WHITE PLAYER");
        }
    }
     
    public static int countPiece (int color) {
        int total = 0;
        for (int i = 0; i < 8; i++) {
            for (int j = 0; j < 8; j++) {
                if (Main.boardState[i][j].getPiece() != null) {
                    if (Main.boardState[i][j].getPiece().getColor() == color) {
                        total++;
                    } 
                }
            }
        }
        return total;
    }
     
    //rule 
    public boolean cekRookade() {
        King k = Main.getKing(turn);
        boolean hasil1 = false,hasil2=false;
        int tempx,tempy;
        boolean status = true , status2 = true;
        if (k.rookadeable == true) {
            tempx = k.x;
            tempy = k.y + 1;
            while (tempx == k.x && tempy <=7 && status ==true) {
                if (Main.boardState[tempx][tempy].getPiece() instanceof Rook ) {
                    if ( ( (Rook) Main.boardState[tempx][tempy].getPiece()).countMove != 0 ) {
                        status=false;
                    }
                }else{
                    if (Main.boardState[tempx][tempy].getPiece() != null ) {
                        status = false;
                    }else if (tempy == 7 ){
                        status = false;
                    }
                }
                tempy++;
            }
            tempx = k.x;
            tempy = k.y - 1;
            while (tempx == k.x && tempy >=0 && status2 ==true) {
                if (Main.boardState[tempx][tempy].getPiece() instanceof Rook ) {
                    if ( ( (Rook) Main.boardState[tempx][tempy].getPiece()).countMove != 0 ) {
                        System.out.println("HEHEHHEEHEHE");
                        status2=false;
                    }
                }else {
                    if (Main.boardState[tempx][tempy].getPiece() != null ) {
                        status2 = false;
                    }else if (tempy == 0){
                        status2 = false;
                    }
                }
                tempy--;
            }
            System.out.println("Status = " + status);
            System.out.println("Status2 = " + status2);
            if (status == true){
                tempx = k.x;
                tempy = k.y + 2;
                if (k.isDanger(Main.boardState, tempx, tempy)) {
                    hasil1 = false;
                }else {
                    Main.boardState[k.x][7].setBackground(Color.yellow);
                    Main.boardState[k.x][k.y].setBackground(Color.yellow);
                    hasil1 = true;
                }
            }
            if(status2 == true) {
                tempx = k.x;
                tempy = k.y - 2;
                if (k.isDanger(Main.boardState, tempx, tempy)) {
                    hasil2 = false;
                }else {
                    Main.boardState[k.x][0].setBackground(Color.yellow);
                    Main.boardState[k.x][k.y].setBackground(Color.yellow);
                    hasil2 = true;
                }
            }
            System.out.println("Hasil 1 " + hasil1 + " \n hasil2 = " + hasil2 );
            return hasil1 == true || hasil2 == true;
        }else {
            return false;
        }
    }
    
    public void rookade(Cell rookPos){
        King k = Main.getKing(turn);
        Piece rook,king;
        int tempx = 0,tempy = 0;
        int posX = 0 , posY = 0;
        if (rookPos.y == 0) {
            tempx = k.x;
            tempy = k.y - 2;
            posX = k.x;
            posY = k.y - 1;
        }else if (rookPos.y == 7) {
            tempx = k.x;
            tempy = k.y + 2;
            posX = k.x;
            posY = k.y + 1;
        }
        if (k.isDanger(Main.boardState, tempx, tempy)) {
            return;
        }
        
        System.out.println("Posisi rook  :" + rookPos.y);
        
        //delete tempat sebelumnya
        rook = Main.boardState[rookPos.x][rookPos.y].getPiece();
        Main.boardState[rookPos.x][rookPos.y].removePiece();
        king = Main.boardState[k.x][k.y].getPiece();
        Main.boardState[k.x][k.y].removePiece();
        //pergantian tempat 
        Main.boardState[tempx][tempy].setPiece(king);
        Main.boardState[posX][posY].setPiece(rook);
        
        
        //set posisi baru king
        k.x = tempx;
        k.y = tempy;
        k.move(Main.boardState, k.x, k.y);
        addgreencolor(k.possiblemoves);
        removegreencolor(k.possiblemoves);
        giliran();
    }
    
    public boolean cekUpgradePawn(Piece p , int x , int y) {
        Pawn pawn = (Pawn) p;
        Cell[][] board = Main.boardState;
        if (p.getColor() == 0) {
            //untuk pion putih
            if (x == 0) {
                return true;
            }else {
                return false;
            }
        }else {
            //untuk pion hitam
            if (x==7) {
                return true;
            }else {
                return false;
            }
        }
    }
    
    public void upgrade(Piece p,int x,int y){
        Pawn pawn = (Pawn) p;
        MainMenu view = MainMenuHandler.getView();
        UpgradeHandler handler = new UpgradeHandler(view,false,view,turn,x,y);
    }
    
    public static void doUpgrade(String choosePiece,int x,int y,int color) {
        
        Piece piece;
        switch(choosePiece) {
            case "queen" :
                if (color == 0) {
                    piece = new Queen("White_Queen" , "White_Queen.png" , 0);
                }else {
                    piece = new Queen("Black_Queen" , "Black_Queen.png" , 1);
                }
                break;
            case "rook" :
                if (color == 0) {
                    piece = new Rook("White_Rook" , "White_Rook.png" , 0);
                }else {
                    piece = new Rook("Black_Rook" , "Black_Rook.png" , 1);
                }
                break;
            case "bishop" : 
                if (color == 0) {
                    piece = new Bishop("White_Bishop" , "White_Bishop.png" , 0);
                }else {
                    piece = new Bishop("Black_Bishop" , "Black_Bishop.png" , 1);
                }
                break;
            case "knight" :
                if (color == 0) {
                    piece = new Knight("White_Knight" , "White_Knight.png" , 0);
                }else {
                    piece = new Knight("Black_Knight" , "Black_Knight.png" , 1);
                }
                break;
            case "pawn" : 
                if (color == 0) {
                    piece = new Pawn("White_Pawn" , "White_Pawn.png" , 0);
                }else {
                    piece = new Pawn("Black_Pawn" , "Black_Pawn.png" , 1);
                }
                break;
            default : 
                piece = null;
                break;
        }
        if (! ( piece instanceof Pawn)) {
            Main.boardState[x][y].removePiece();
            Main.boardState[x][y].setPiece(piece);
            piece.move(Main.boardState, x, y);
            addgreencolor(piece.possiblemoves);
            removegreencolor(piece.possiblemoves);
            validateCheck(Main.boardState);
            //mengecek apakah sudah checkmate atau belum
            try {
                if (checkMate(Main.boardState) == true) {
                    checkMateSound();
                }
            } catch (Exception e) {
                System.out.println("Sound error");
            }
        }
    }
     
    
    public static final void playSound() throws UnsupportedAudioFileException, IOException {
        InputStream move_sound;
        try {
            move_sound = new FileInputStream(new File("src/Game/sound_move.wav"));
            AudioStream audios = new AudioStream(move_sound);
            AudioPlayer.player.start(audios);
            
        }catch(Exception e) {
            System.out.println(e);
        }
    }
     
    public static final void checkMateSound() throws UnsupportedAudioFileException, IOException {
        InputStream checkMate;
        try {
            checkMate = new FileInputStream(new File("src/Game/checkmate.wav"));
            AudioStream audios = new AudioStream(checkMate);
            AudioPlayer.player.start(audios);
        }catch(Exception e) {
            System.out.println(e);
        }
    }
    
    public static final void timeOver() throws UnsupportedAudioFileException, IOException {
        InputStream timeover;
        try {
            timeover = new FileInputStream(new File("src/Game/timeover.wav"));
            AudioStream audios = new AudioStream(timeover);
            AudioPlayer.player.start(audios);
        }catch(Exception e) {
            System.out.println(e);
        }
    }
    
    public static void GameEnd (Player win , Player lose,String status) {
        MainMenuHandler.gameEnd(win, lose,status);
        Main.time.stop();
    }
     
     
     
     
     
    
    @Override
    public void mouseClicked(MouseEvent me) { // semua kegiatan mouse diatur di fungsi ini
        System.out.println("Turn Kali ini " + turn);
        Cell c =(Cell)me.getSource(); // mendapatkan cell yang di klik
        King king = Main.getKing(turn);
        //validasi giliran
        int colorPiece = -1;
        try {
            colorPiece = Main.prev.getPiece().getColor();
        }catch (Exception e){
            if (c.getPiece() == null ) {
                System.out.println("null");
            }else {
                colorPiece = c.getPiece().getColor();
            }
        }
        //end of validasi
        
        if (colorPiece == turn) {
            validateCheck(Main.boardState);            
            if(c.getPiece() != null ) {
                if (king.isDanger(Main.boardState, king.x, king.y)){
                    king.rookadeable = false;
                }
                c.getPiece().move(Main.boardState, c.x, c.y);
                try {     
                    possibleMoveNotMakeKingCheck(Main.boardState , c , king);
                } catch (CloneNotSupportedException ex) {
                    Logger.getLogger(Handler.class.getName()).log(Level.SEVERE, null, ex);
                }
                
                if (Main.prev==null && c.isSelected==false) {
//                ketika gerak raja sedang dalam ancaman
                    if (c.getPiece() instanceof King) {
                        validateKingMove(Main.boardState, Main.getKing(c.getPiece().getColor()) );
                        if ( cekRookade() == true ) {
                            //
                        }else {
                            //
                        }
                    }
                    Main.prev= c;
                    c.select();
                    System.out.println("IF  1");
                    addgreencolor(c.getPiece().possiblemoves);
                    
                }else if (Main.prev == c && c.isSelected == true) {
                    if (c.getPiece() instanceof King) {
                        validateKingMove(Main.boardState, Main.getKing(c.getPiece().getColor()) );
                        if(cekRookade() == true) {
                            Main.boardState[king.x][c.y].removecheck();
                            Main.boardState[king.x][0].removecheck();
                            Main.boardState[king.x][7].removecheck();
                        }
                    }
                    c.deselect();
                    System.out.println("IF 2");
                    removegreencolor(c.getPiece().possiblemoves);
                    Main.prev = null;
                }else if (Main.prev.getPiece().getColor() == c.getPiece().getColor()) {
                    if(cekRookade() == true && c.getPiece() instanceof Rook) {
                        removegreencolor(Main.prev.getPiece().possiblemoves);
                        Main.boardState[king.x][king.y].removecheck();
                        Main.boardState[king.x][0].removecheck();
                        Main.boardState[king.x][7].removecheck();
                        king.countMove++;
                        ((Rook)c.getPiece()).countMove++;
                        //untuk undo
                        if (c.y == 0 ){
                            addMove(Main.prev.x,Main.prev.y,Main.prev.x,Main.prev.y-2,null,"rookade");
                        }else if (c.y == 7) {
                            addMove(Main.prev.x,Main.prev.y,Main.prev.x,Main.prev.y+2,null,"rookade");
                        }
                        rookade(c);
                        
                        try{
                            playSound();
                        }catch(Exception e) {
                            System.out.println("Sound error");
                        }
                        //masukan ke dalam move
                        Main.prev.deselect();
                        Main.prev = null;
                    }else {
                        if (!king.isDanger(Main.boardState, king.x, king.y)){
                            Main.boardState[king.x][king.y].removecheck();
                            Main.boardState[king.x][0].removecheck();
                            Main.boardState[king.x][7].removecheck();
                        }
                        removegreencolor(Main.prev.getPiece().possiblemoves);
                        if (c.getPiece() instanceof King) {
                            validateKingMove(Main.boardState,king);
                        }
                        c.select();
                        addgreencolor(c.getPiece().possiblemoves);
                        Main.prev.deselect();
                        Main.prev= c;
                    }
                    System.out.println("IF 3");
                    
                }else if (Main.prev.isSelected == true  && c.getPiece().getColor() != Main.prev.getPiece().getColor() ) { // -----------------gerak
                    //validasi memakan pion
                    boolean valid = false;
                    for ( Cell d : Main.prev.getPiece().possiblemoves ){
                        if (c.x == d.x && c.y == d.y) {
                            valid = true;
                            break;
                        }
                    }
                    if (valid == true) {
                        //saat memakan pion lain
                        System.out.println("IF 4");
                        //menyimpan piece di variabel

                        Piece prev;
                        prev = Main.prev.getPiece();
                        Piece temp = c.getPiece();

                        //menghapus pion
                        if (Main.prev.getPiece() instanceof King) {
                            King a =(King)Main.prev.getPiece();
                            removeKingDanger(Main.boardState , a );
                            
                        }
                        Main.prev.removePiece();
                        c.removePiece();
                        Main.boardState[Main.prev.x][Main.prev.y] = Main.prev;
                        Main.boardState[c.x][c.y] = c;


                        //menambahkan panel
                        c.setPiece(prev);
                        Main.boardState[c.x][c.y] = c;

                        //menghilangkan destlist 
                        Main.prev.isSelected = false;
                        Main.prev.deselect();
                        removegreencolor(c.getPiece().possiblemoves);
                        
                        //menambahkan posisi x dan y jika instanceof king
                        if ( prev instanceof King ) {
                            Main.boardState[king.x][0].removecheck();
                            Main.boardState[king.x][7].removecheck();
                            king.x = c.x;
                            king.y  = c.y;
                            king.countMove++;
                            king.rookadeable = false;
                        } 
                        if (prev instanceof Rook) {
                            ((Rook)prev).countMove++;
                        }
                        //masukan ke dalam history move
                        if (prev instanceof Pawn) {
                            if (cekUpgradePawn(prev, c.x, c.y)) {
                                //valid
                                upgrade(prev,c.x,c.y);
                                addMove(Main.prev.x , Main.prev.y , c.x,c.y ,temp,"upgrade");
                            }else {
                                addMove(Main.prev.x , Main.prev.y , c.x,c.y ,temp,"normal");
                            }
                        }else {
                            addMove(Main.prev.x , Main.prev.y , c.x,c.y ,temp,"normal");
                        }
                        
                        validateCheck(Main.boardState); 
                        //mengganti turn
                        giliran();
                        
                        //validasi check atau tidak untuk king
                        validateCheck(Main.boardState); 
                        //
                        
                        Main.prev= null;
                        //mengisi suara
                        try {
                            playSound();
                        }catch (Exception e) {
                            System.out.println("Error audio");
                        }
                        
                        //mengecek apakah sudah checkmate atau belum
                        try {
                            if (checkMate(Main.boardState) == true) {
                                checkMateSound();
                            }
                        } catch (Exception e) {
                            System.out.println("Sound error");
                        }
                       
                       if (MainMenuHandler.mode.equals("pro")){
                        Main.time.reset();
                        }
                        
                    }else {
                        //menghilangkan destlist 
                        Main.prev.isSelected = false;
                        Main.prev.deselect();
                        try {
                            removegreencolor(Main.prev.getPiece().possiblemoves);
                        }catch (Exception e) {
                            //
                        }
                        Main.prev= null;
                    }
                }      
            }else if (Main.prev != null) { // --------------------------- -------------------------------------------------------gerak
                //validasi gerak ke possiblemoves
                System.out.println("IF 5");
                boolean valid = false;
                for ( Cell d : Main.prev.getPiece().possiblemoves ){
                    if (c.x == d.x && c.y == d.y) {
                        valid = true;
                        break;
                    }
                }
                if (valid == true){
                    //posisi bergerak ke possiblemoves
                    Piece prev;
                    prev = Main.prev.getPiece();
                    Cell newCell = new Cell(c.x,c.y,prev);
                    Cell nullCell = new Cell(Main.prev.x,Main.prev.y,null);


                    //menghapus pion
                    if (Main.prev.getPiece() instanceof King) {
                        King a =(King)Main.prev.getPiece();
                        removeKingDanger(Main.boardState , a );
                    }
                    Main.prev.removePiece();
                    Main.boardState[Main.prev.x][Main.prev.y] = Main.prev;

                    //menambahkan panel
                    c.setPiece(prev);
                    Main.boardState[c.x][c.y] = c;

                    //menghilangkan destlist 
                    Main.prev.isSelected = false;
                    Main.prev.deselect();
                    removegreencolor(c.getPiece().possiblemoves);
                    
                    //menambahkan posisi x dan y jika instanceof king
                    if ( prev instanceof King ) {
                        Main.boardState[king.x][0].removecheck();
                        Main.boardState[king.x][7].removecheck();
                        king.x = c.x;
                        king.y  = c.y;
                        king.countMove++;
                        king.rookadeable = false;
                        
                    } 
                    
                    if (prev instanceof Rook) {
                        ((Rook)prev).countMove++;
                    }
                    //masukan ke dalam history move
                    if (prev instanceof Pawn) {
                        if (cekUpgradePawn(prev, c.x, c.y)) {
                            //valid
                            upgrade(prev,c.x,c.y);
                            addMove(Main.prev.x , Main.prev.y , c.x,c.y ,null,"upgrade");
                        }else {
                            addMove(Main.prev.x , Main.prev.y , c.x,c.y ,null,"normal");
                        }
                    }else {
                        addMove(Main.prev.x , Main.prev.y , c.x,c.y ,null,"normal");
                    }
                    
                    
                    validateCheck(Main.boardState); 
                    giliran();
                    
                    //mengganti turn
                    
                    validateCheck(Main.boardState); 
                    Main.prev= null;
                    
                    //play sound
                    try {
                        playSound();
                    }catch (Exception e) {
                        System.out.println("Error audio");
                    }
                    
                    //mengecek apakah sudah checkmate atau belum
                    try {
                        if (checkMate(Main.boardState) == true) {
                            checkMateSound();
                        }
                    } catch (Exception e) {
                        System.out.println("Sound error");
                    }
                    
                    
                    if (MainMenuHandler.mode.equals("pro")){
                        Main.time.reset();
                    }
                    
                    }else {
                        //menghilangkan destlist 
                        Main.prev.isSelected = false;
                        Main.prev.deselect();
                        try {
                            removegreencolor(Main.prev.getPiece().possiblemoves);
                        }catch (Exception e) {
                            //
                        }
                        Main.prev= null;
                    }
                }
            }
               
        
        try {
            System.out.println(c.x +" "+ c.y);
        }catch (Exception e) {
            System.out.println("Null");
        }
    }
}  
