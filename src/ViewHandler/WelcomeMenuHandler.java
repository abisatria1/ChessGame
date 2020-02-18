package ViewHandler;

import Game.*;
import View.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.*;
import java.util.ArrayList;
import java.util.Iterator;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author Microsoft
 */
public class WelcomeMenuHandler implements ActionListener{
    WelcomePanel view;
    ArrayList<Player> dataPlayer = new ArrayList<>();

    public WelcomeMenuHandler() {
        view = new WelcomePanel();
        view.setVisible(true);
        dataPlayer.clear();
        loadDataPlayer();
        saveData();
        view.addActionListener(this);
    }
    
    public void Validate(String mode) {
        String name1 = view.getPlayer1Nama().getText();
        String name2 = view.getPlayer2Nama().getText();
        if (!name1.isEmpty() && !name2.isEmpty()) {
            name1 = name1.toUpperCase();
            name2 = name2.toUpperCase();
        }else {
            name1 = "";
            name2 = "";
        }
        if ( name1.equals("") || name2.equals("")) {
            view.pesan("Data Player belum lengkap ", "Error");
        }else if (name1.equals(name2)) {
            view.pesan("Player tidak boleh sama !" , "Error");
        }else {
            Player p1 = searchDataPlayer(name1);
            Player p2 = searchDataPlayer(name2);
            if (p1 == null) {
                p1 = new Player(name1,0,0);
                createPlayer(p1);
            }
            if (p2 == null) {
                p2 = new Player(name2,0,0);
                createPlayer(p2);
            }
            System.out.println("size : " + dataPlayer.size());
            if (p1 != null && p2 != null) {
                System.out.println(p1.getNama() + " vs " + p2.getNama());
                System.out.println("statistik");
                System.out.println(p1.getWon()+ " vs " + p2.getWon());
                System.out.println(p1.getGamesPlayed() +" vs " +p2.getGamesPlayed());
                startGame(p1,p2,mode);
            }else {
                view.pesan("Error Mulai", "Error");
                return;
            }
        }
    }
    
    public void createPlayer(Player p) {
        dataPlayer.add(p);
        saveData();
        loadDataPlayer();
    }
    
    public void saveData() {
        try{
            FileOutputStream fos = new FileOutputStream("newData.txt");
            ObjectOutputStream oos = new ObjectOutputStream(fos);
            oos.writeObject(dataPlayer);
            oos.close();
            fos.close();
        }catch(IOException e) {
            System.out.println("Error add player : " + e.getMessage());
        }
    }
   
    public void loadDataPlayer(){
        try{
            dataPlayer.clear();
            ArrayList<Player> p = new ArrayList<>();
            FileInputStream fis = new FileInputStream("newData.txt");
            ObjectInputStream ois = new ObjectInputStream(fis);
            try{
                p = (ArrayList<Player>)ois.readObject();
                dataPlayer = p;
                ois.close();
                fis.close();
            }catch(EOFException e){
                ois.close();
            }
        }catch(FileNotFoundException e) {
//            JOptionPane.showMessageDialog(null,"Error load file, file tidak ditemukan! , silahkan ");
            System.out.println("Error io exception load data player " + e);
        }catch (Exception eex) {
            eex.printStackTrace();
            System.out.println("error load data player");
        }
        
    }
    
    public Player searchDataPlayer (String nama) {
        if (dataPlayer.isEmpty()) {
            return null;
        }else {
            Iterator iterator = dataPlayer.iterator(); 
            Player p;
            while (iterator.hasNext()) {
                p = (Player) iterator.next();
                if (p.getNama().equals(nama)) {
                    return p;
                }
            }
            return null;
        }
    } 
    
    public void startGame(Player p1,Player p2,String mode) {
        view.dispose();
        MainMenuHandler m1 = new MainMenuHandler(p1, p2 , mode);
    }
    
    public void gameEnd(Player won , Player lose,String status) {
        //menerima parameter player mana yang menang
        if (status.equals("checkmate")){
            searchDataPlayer(won.getNama()).updateGameWon();
        }
        searchDataPlayer(won.getNama()).updateGamesPlayed();
        searchDataPlayer(lose.getNama()).updateGamesPlayed();
        saveData();
    }
    
    @Override
    public void actionPerformed(ActionEvent ae) {
        Object source = ae.getSource();
        if (source.equals(view.getClassicButton())) {
            //classic button mode
            System.out.println("Classic btn");
            Validate("classic");
        }
        if (source.equals(view.getProButton())) {
            //pro button mode
            Validate("pro");
        }
        if (source.equals(view.getRankBtn())){
            RankHandler r = new RankHandler(dataPlayer);
            view.dispose();
        }
    }
    
}
