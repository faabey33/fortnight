/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package game;

import java.awt.Point;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;
import javax.swing.Timer;

/**
 *
 * @author fabi
 */
public class Player {
    
    private static Timer timer = new Timer(10, new ActionListener() {
        @Override
        public void actionPerformed(ActionEvent e) {
            Player.update();
        }
    });
    
    public static void init(int x, int y, int padding) {
        timer.start();
        setX(x*64+padding);
        setY(y*64+padding);
    }
    
    private static int hp;
    private static int score;
    
    private static int x, y;
    private static int width = 64;
    private static int height = 64;
    
    private static int moveDistance = 64;
    
    private static int startX = 0;
    private static int startY = 0;

    private static int speed = 4;
    private static void update() {
        if (isMoving) {
            //auf y achse bewegen
            if (onYAxis) {
                //nach unten
                if (forward) {
                    if (getY() < target) {
                        setY(getY() + speed);
                    } else {
                        setY(target);
                        doneMoving();
                    }
                } else {
                    //nach oben
                    if (getY() > target) {
                        setY(getY() - speed);
                    } else {
                        setY(target);
                        doneMoving();
                    }
                }
            //auf x achse bewegen
            } else {
                //nach rechts
                if (forward) {
                    if (getX() < target) {
                        setX(getX() + speed);
                    } else {
                        setX(target);
                        doneMoving();
                    }
                } else {
                    //nach links
                    if (getX() > target) {
                        setX(getX() - speed);
                    } else {
                        setX(target);
                        doneMoving();
                    }
                }
            }
        } else {
        //if not moving then check if he can move again after enemy movement
            if (Enemy.enemyMoveDone()) {
                System.out.println("Enemies done with moving");
                GameManager.setCurrentTurn(GameManager.PLAYER);
                isMoving = false;
            }
        }
        
    }
    
    private static void doneMoving() {
        GameManager.setCurrentTurn(GameManager.ENEMY);
        GridManager.update();
        isMoving = false;        
    }
    
    public static int getWidth() {
        return width;
    }
    
    public static int getHeight() {
        return height;
    }
    
    /**
     * @return the x
     */
    public static int getX() {
        return x;
    }

    /**
     * @param aX the x to set
     */
    public static void setX(int aX) {
        x = aX;
    }

    /**
     * @return the y
     */
    public static int getY() {
        return y;
    }

    /**
     * @param aY the y to set
     */
    public static void setY(int aY) {
        y = aY;
    }
    
    public Player(int hp) {
        super();
        setHp(hp);
        setX(startX);
        setY(startY);
    }
    
    /*
        target gibt zielkoordinate an in x oder y richtung
        current speichert momentane koordinate
        onYAxis gibt richtung an
        forward gibt die richtung nach vorne oder hinten an
            true ist nach vorne
        in der update funktion werden die werte benutzt
    */
    private static int target;
    private static int current;
    private static boolean onYAxis;
    private static boolean forward;
    /*
        isMoving blockiert das erneute klicken während dem bewegen
        //canMove wird true wenn der Gegner sich bewegt hat
        //oder auch nicht mal gucken ob man das so spielen kann
    */
    //private static boolean canMove = true;
    private static boolean isMoving = false;
    public static void move(char dir) {
        //move only if its players move
        if (!canMove()) {
            return;
        }
        //do nothing if is moving
        if (isMoving) {
            return;
        }
        //set player to moving
        isMoving = true;
        String debug = "a";
        switch (dir) {
            case 'r':
                if (!GridManager.canMoveRight(new Point(GridManager.getGridX(Player.getX())
                        , GridManager.getGridY(Player.getY())))) {
                    isMoving = false;
                    return;
                }
                onYAxis = false;
                current = getX();
                target = current + moveDistance;
                forward = true;
                debug = "rechts";
                break;
            case 'l':
                if (!GridManager.canMoveLeft(new Point(GridManager.getGridX(Player.getX())
                        , GridManager.getGridY(Player.getY())))) {
                    isMoving = false;
                    return;
                }
                onYAxis = false;
                current = getX();
                target = current - moveDistance;
                forward = false;
                debug = "left";
                break;
            case 'u':
                if (!GridManager.canMoveTop(new Point(GridManager.getGridX(Player.getX())
                        , GridManager.getGridY(Player.getY())))) {
                    isMoving = false;
                    return;
                }
                onYAxis = true;
                current = getY();
                target = current - moveDistance;
                forward = false;
                debug = "up";
                break;
            case 'd':
                if (!GridManager.canMoveDown(new Point(GridManager.getGridX(Player.getX())
                        , GridManager.getGridY(Player.getY())))) {
                    isMoving = false;
                    return;
                }
                onYAxis = true;
                current = getY();
                target = current + moveDistance;
                forward = true;
                debug = "down";
                break;
        }
        System.out.println(debug);
    }

    private static boolean canMove() {
        //returns true if players move
        return GameManager.getCurrentTurn();
    }
    
    /**
     * @return the hp
     */
    public static int getHp() {
        return hp;
    }

    /**
     * @param hp the hp to set
     */
    public static void setHp(int hp) {
        Player.hp = hp;
    }

    
    
}
