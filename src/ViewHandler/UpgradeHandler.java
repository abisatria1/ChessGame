/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ViewHandler;
import Game.*;
import View.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.ImageIcon;
import javax.swing.JFrame;

/**
 *
 * @author Microsoft
 */
public class UpgradeHandler implements ActionListener{
    private String choosePiece;
    private int color;
    private Upgrade view;
    private final MainMenu menu;
    public int x,y;
    
    public UpgradeHandler (JFrame parent , boolean modal , MainMenu menu , int color , int x,int y) {
        view = new Upgrade(parent,modal);
        this.menu = menu;
        menu.disable();
        view.addActionListener(this);
        setGambar(color);
        this.color = color;
        this.x = x;
        this.y = y;
    }
    
    public String getChoosePiece() {
        return choosePiece;
    }
    
    public int getColor() {
        return this.color;
    }
    
    public void setGambar (int color) {
        //queen 
        if (color == 0) {
            ImageIcon icon =  new ImageIcon(this.getClass().getResource("/ChessGame/images/White_Queen.png"));
            view.getQueen().setIcon(icon);
        }else {
            ImageIcon icon =  new ImageIcon(this.getClass().getResource("/ChessGame/images/Black_Queen.png"));
            view.getQueen().setIcon(icon);
        }
        
        //rook 
        if (color == 0) {
            ImageIcon icon =  new ImageIcon(this.getClass().getResource("/ChessGame/images/White_Rook.png"));
            view.getRook().setIcon(icon);
        }else {
            ImageIcon icon =  new ImageIcon(this.getClass().getResource("/ChessGame/images/Black_Rook.png"));
            view.getRook().setIcon(icon);
        }
        
        //bishop 
        if (color == 0) {
            ImageIcon icon =  new ImageIcon(this.getClass().getResource("/ChessGame/images/White_Bishop.png"));
            view.getBishop().setIcon(icon);
        }else {
            ImageIcon icon =  new ImageIcon(this.getClass().getResource("/ChessGame/images/Black_Bishop.png"));
            view.getBishop().setIcon(icon);
        }
        
        //knight 
        if (color == 0) {
            ImageIcon icon =  new ImageIcon(this.getClass().getResource("/ChessGame/images/White_Knight.png"));
            view.getKnight().setIcon(icon);
        }else {
            ImageIcon icon =  new ImageIcon(this.getClass().getResource("/ChessGame/images/Black_Knight.png"));
            view.getKnight().setIcon(icon);
        }
        
        //pawn 
        if (color == 0) {
            ImageIcon icon =  new ImageIcon(this.getClass().getResource("/ChessGame/images/White_Pawn.png"));
            view.getPawn().setIcon(icon);
        }else {
            ImageIcon icon =  new ImageIcon(this.getClass().getResource("/ChessGame/images/Black_Pawn.png"));
            view.getPawn().setIcon(icon);
        }
    }

    @Override
    public void actionPerformed(ActionEvent ae) {
        Object source = ae.getSource();
        if (source.equals(view.getQueen())){
            choosePiece = "queen";
            menu.enable();
            view.dispose();
            Handler.doUpgrade(choosePiece,x,y,color);
        }else if (source.equals(view.getRook())) {
            choosePiece = "rook";
            menu.enable();
            view.dispose();
            Handler.doUpgrade(choosePiece,x,y,color);
        }else if (source.equals(view.getBishop())) {
            choosePiece = "bishop";
            menu.enable();
            view.dispose();
            Handler.doUpgrade(choosePiece,x,y,color);
        }else if (source.equals(view.getKnight())) {
            choosePiece = "knight";
            menu.enable();
            view.dispose();
            Handler.doUpgrade(choosePiece,x,y,color);
        }else if (source.equals(view.getPawn())) {
            choosePiece = "pawn";
            menu.enable();
            view.dispose();
            Handler.doUpgrade(choosePiece,x,y,color);
        }
    }
    
    

   
}
