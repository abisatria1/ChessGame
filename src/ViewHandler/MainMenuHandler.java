/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ViewHandler;
import Game.*;
import View.*;
import ViewHandler.*;

/**
 *
 * @author Microsoft
 */
public class MainMenuHandler {
    private static MainMenu view;
    private static Player whiteplayer,blackplayer;
    public static String mode;
    
    public MainMenuHandler(Player p1, Player p2 , String mode) {
        view = new MainMenu();
        whiteplayer= p1;
        blackplayer = p2;
        this.mode = mode;
        setNama(p1, p2);
        startGame();
    }

    public static MainMenu getView() {
        return view;
    }

    public void setView(MainMenu view) {
        this.view = view;
    }

    public static Player getWhiteplayer() {
        return whiteplayer;
    }

    public void setWhiteplayer(Player whiteplayer) {
        this.whiteplayer = whiteplayer;
    }

    public static Player getBlackplayer() {
        return blackplayer;
    }

    public void setBlackplayer(Player blackplayer) {
        this.blackplayer = blackplayer;
    }

    public String getMode() {
        return mode;
    }

    public void setMode(String mode) {
        this.mode = mode;
    }
    
    public void setNama(Player p1,Player p2){
        view.getWhiteNama().setText(p1.getNama());
        view.getWhiteWon().setText(Integer.toString(p1.getWon() ));
        view.getWhitePlayed().setText(Integer.toString(p1.getGamesPlayed()));
        
        view.getBlackNama().setText(p2.getNama());
        view.getBlackWon().setText(Integer.toString(p2.getWon() ));
        view.getBlackPlayed().setText(Integer.toString(p2.getGamesPlayed()));
    }
    
    public void startGame(){
        Main.newGame();
        Handler.turn = 0;
        if (mode.equals("pro")) {
            Main.timeRemaining = 180;
            Main.time = new Time();
            Main.time.start();
            view.getUndoBtn().setVisible(false);
        }else {
            view.getUndoBtn().setVisible(true);
        }
    }
    
    public static void gameEnd(Player win,Player lose,String status) {
        view.dispose();
        WelcomeMenuHandler h1 = new WelcomeMenuHandler();
        h1.gameEnd(win, lose,status);
    }
    
}
