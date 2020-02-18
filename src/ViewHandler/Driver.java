/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ViewHandler;
import ViewHandler.*;
import javax.swing.UIManager;

/**
 *
 * @author Microsoft
 */
public class Driver {
    public static void main(String[] args) {
        try {
           UIManager.setLookAndFeel(new com.jtattoo.plaf.graphite.GraphiteLookAndFeel());
        }catch (Exception e) {
            //
        }
        WelcomeMenuHandler h1 = new WelcomeMenuHandler();
    }
}
