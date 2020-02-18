package Game;
import Game.*;
/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

import java.io.*;
/**
 *
 * @author Microsoft
 */
public class Player implements Serializable , Comparable<Player>{
    private String nama;
    private int won;
    private int gamesPlayed;

    public Player(String nama, int won, int gamesPlayed) {
        this.nama = nama;
        this.won = won;
        this.gamesPlayed = gamesPlayed;
    }

    public String getNama() {
        return nama;
    }

    public void setNama(String nama) {
        this.nama = nama;
    }

    public int getWon() {
        return won;
    }

    public void setWon(int won) {
        this.won = won;
    }

    public int getGamesPlayed() {
        return gamesPlayed;
    }

    public void setGamesPlayed(int gamesPlayed) {
        this.gamesPlayed = gamesPlayed;
    }
    
    public void updateGamesPlayed() {
        gamesPlayed++;
    }
    
    public void updateGameWon() {
        won++;
    }

    @Override
    public int compareTo(Player t) {
        return t.getWon() - this.won; 
    }
    
    
}
