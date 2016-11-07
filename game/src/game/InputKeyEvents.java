/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package game;

import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

/**
 *
 * @author fabi
 */
public class InputKeyEvents extends KeyAdapter{
    
    public void keyPressed(KeyEvent e) {
        int key = e.getKeyCode();
        switch (key) {
            case 39:
                //right
                Player.move('r');
                break;
            case 37:
                //left
                Player.move('l');
                break;
            case 38:
                //up
                Player.move('u');
                break;
            case 40:
                //down
                Player.move('d');
                break;
        }
    }
    
}
