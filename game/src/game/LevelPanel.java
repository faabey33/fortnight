/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package game;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.List;
import javax.swing.JPanel;
import javax.swing.Timer;
import javax.swing.border.EmptyBorder;

/**
 *
 * @author fabi
 */
public class LevelPanel extends JPanel implements ActionListener {
    
    private Timer timer;
    Color color = new Color(0,0,0,255);
    Color gridColor = new Color(40, 40, 40, 40);
    Color playerColor = new Color(0, 255, 0, 255);
    Color enemyColor = new Color(100, 0, 0, 255);
    Color wallColor = new Color(40, 40, 40, 40);
    private int width, height;
    private int padding = 22;
    private int gridSize = 64;
    private Graphics graphics;
    
    public LevelPanel(int width, int height, int padding) {
        //für tasteneingabe
        addKeyListener(new InputKeyEvents());
        
        this.width = width;
        this.height = height;
        this.padding = padding;
        timer = new Timer(10, this);
        timer.start();
    }
    
    
    @Override
    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        drawObjects(g);
        drawGrid(g);
        drawPlayer(g);
    }
    
    private void drawGrid(Graphics g) {
        g.setColor(gridColor);
        for (int i = padding; i < width; i = i + gridSize) {
            g.drawLine(i, padding, i, height-padding*2);
        }
        for (int a = padding; a < height; a = a + gridSize) {
            g.drawLine(padding, a, width-padding*2, a);
        }
    }
    
    private void drawPlayer(Graphics g) {
        g.setColor(playerColor);
        g.fillRect(Player.getX(), Player.getY(), Player.getWidth(), Player.getHeight());
    }
    
    private void drawObjects(Graphics g) {
        List<GameObject> list = GameObject.getGameObjects();
        
        for (GameObject o : list) {
            if (o instanceof Wall) {
                g.setColor(wallColor);
                g.fillRect(o.getX()+padding, o.getY()+padding, o.getWidth(), o.getHeight());;
            } else if (o instanceof Enemy) {
                g.setColor(enemyColor);
                if (o.alive)
                    g.fillRect(o.getX()+padding, o.getY()+padding, o.getWidth(), o.getHeight());;
            }
        }
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        repaint();
    }
    
}
