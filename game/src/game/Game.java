/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package game;

import java.awt.Dimension;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.ArrayList;
import javax.swing.JFrame;
import javax.swing.JPanel;

/**
 *
 * @author fabi
 */
public class Game {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        
        
        JFrame gameWindow = new JFrame();
        gameWindow.setSize(new Dimension(830, 658));
        
        gameWindow.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        
        int padding = 22;
        LevelPanel content = new LevelPanel(832, 640, padding);
        gameWindow.add(content);
        content.setFocusable(true);
        gameWindow.requestFocus();
        gameWindow.getRootPane().setContentPane(content);
        gameWindow.setVisible(true);
        content.setVisible(true);
        
        

        for (int a = 0; a < 5; a++) {
            int x = Math.round((float)Math.random()*10);
            int y = Math.round((float)Math.random()*10);
            GameObject.add(new Enemy(x*64, y*64));
        }
        
        GameObject.add(new Enemy(3*64, 9*64));
        
        for (int i = 0; i < 14; i++) {
            int x = Math.round((float)Math.random()*10);
            int y = Math.round((float)Math.random()*10);
            GameObject.add(new Wall(x, y));
        }
        Wall test = new Wall(64,128);
        GameObject.add(test);
        
        Player.init(5, 5, padding);
    }

    
    
}
