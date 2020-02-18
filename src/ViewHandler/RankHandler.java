/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ViewHandler;
import Game.*;
import View.*;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.Collections;
import javax.swing.table.DefaultTableModel;
import ViewHandler.*;
/**
 *
 * @author Microsoft
 */
public class RankHandler implements ActionListener{
    ArrayList<Player> DataPlayer;
    Rank view;
    
    public RankHandler(ArrayList<Player> data) {
        view = new Rank();
        view.setVisible(true);
        view.addActionListener(this);
        this.DataPlayer = data;
        sortData();
        setTable();
    }
    
    public void sortData() {
        Collections.sort(DataPlayer);
    }
    
    public void setTable() {
        DefaultTableModel model = new DefaultTableModel(new String[]{
            "No", 
            "Name", 
            "Win", 
            "Games Played"
        },0);
        ArrayList<Player> player = DataPlayer;
        int i = 1;
        for (Player m : player) {
            model.addRow(new Object[]{
                i, 
                m.getNama(), 
                m.getWon(), 
                m.getGamesPlayed()
            });
            i++;
        }
        view.setTable(model);
        view.getrankTable().getColumnModel().getColumn(0).setPreferredWidth(50);
        view.getrankTable().getColumnModel().getColumn(1).setPreferredWidth(400);
        view.getrankTable().getColumnModel().getColumn(2).setPreferredWidth(300);
        view.getrankTable().getColumnModel().getColumn(3).setPreferredWidth(300);
        view.getrankTable().getTableHeader().setFont(new Font ("Segoi UI" , Font.BOLD,20));
        view.getrankTable().setFont(new Font ("Segoi UI" , Font.BOLD , 15));
        
    }
    

    @Override
    public void actionPerformed(ActionEvent ae) {
        Object source = ae.getSource();
        if (source.equals(view.getBackBtn())){
            System.out.println("tes");
            WelcomeMenuHandler h = new WelcomeMenuHandler();
            view.dispose();
        }
    }
    
    
}
