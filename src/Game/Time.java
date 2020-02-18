/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Game;
import Game.*;
import View.MainMenu;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.Timer;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

/**
 *
 * @author Microsoft
 */
public class Time {
    public static JLabel label;
    Timer countdownTimer;
    int Timerem;

    public Time(){
       countdownTimer = new Timer(1000, new CountdownTimerListener());
       Timerem=Main.timeRemaining;
    }
    
    //A function that starts the timer
    public void start(){
    	countdownTimer.start();
    }
    
    public void stop() {
        countdownTimer.stop();
    }
    
    //A function that resets the timer
    public void reset(){
    	Timerem=Main.timeRemaining;
    }
       
    class CountdownTimerListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e){
            int min,sec;

            if (Timerem > 0)
            {
                   min=Timerem/60;
                   sec=Timerem%60;
               MainMenu.getTimer().setText(String.valueOf(min)+":"+(sec>=10?String.valueOf(sec):"0"+String.valueOf(sec)));
               Timerem--;
            }
            else
            {
                
               if (Main.getKing(Handler.turn).isDanger(Main.boardState, Main.getKing(Handler.turn).x, Main.getKing(Handler.turn).y)){//ketika check dan habis waktunya
                   JOptionPane.showMessageDialog(null, "Game Selesai");
                   MainMenu.getTimer().setText("Time's up!");
                   countdownTimer.stop();
               }else {
                    for (int i = 0; i < 8; i++) {
                        for (int j = 0; j < 8; j++) {
                            if (Main.boardState[i][j].getPiece() != null ) {
                                Handler.removegreencolor(Main.boardState[i][j].getPiece().possiblemoves);
                                Main.boardState[i][j].removecheck();
                            }
                        }
                    }
                    Main.prev = null;
                    try {
                        Handler.timeOver();
                    }catch (Exception ex ){
                        System.out.println("Error sound");
                    }
                    MainMenu.getTimer().setText("Time's up!");
                    System.out.println("Berhasil");
                    Handler.giliran();
                    reset();
                    start();
               }
           }
        }
    }
}
