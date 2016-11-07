/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package game;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;
import javafx.util.Pair;
import javax.swing.Timer;

/**
 *
 * @author fabi
 */
public class Enemy extends GameObject implements ActionListener {
    
    private Timer timer = new Timer(10, this);
    private boolean nextMoveCalculated = false;
    private boolean nextMoveYAxis, nextMoveForward;
    private int nextMoveTarget;
    private int moveSpeed = 4;
    private static List<Boolean> moved = new ArrayList();
    public int id;
    private boolean alive = true;
    private static int count;
    private boolean wantsToMove;
    public static List<Enemy> enemyList = new ArrayList();
    
    public static void killEnemyByID(int id) {        
        for (Enemy a : enemyList) {
            if (a.getID() == id) {
                a.die();
                System.out.println("killed enemy "+id);
            }
        }
    }
    
    public int getID() {
        return id;
    }
    
    @Override
    public void actionPerformed(ActionEvent e) {

        if (canMove()) {
            //calculate next move if has not been done yet
            //distance in moves that have to be done
            if (!nextMoveCalculated) {
                calculateNextMove();
            }
            //move in direction that has been calculated
            if (!nextMoveYAxis) {
                if (nextMoveForward) {
                    if (getX() < nextMoveTarget) {
                        setX(getX() + moveSpeed);                     
                    } else {
                        setX(nextMoveTarget);   
                        doneMoving();
                    }
                } else {
                    if (getX() > nextMoveTarget) {
                        setX(getX() - moveSpeed);                     
                    } else {
                        setX(nextMoveTarget);       
                        doneMoving();
                    }
                }
            } else {
                if (nextMoveForward) {
                    if (getY() > nextMoveTarget) {
                        setY(getY() - moveSpeed);                     
                    } else {
                        setY(nextMoveTarget);    
                        doneMoving();
                    }
                } else {
                    if (getY() < nextMoveTarget) {
                        setY(getY() + moveSpeed);                     
                    } else {
                        setY(nextMoveTarget);      
                        doneMoving();
                    }
                }
            }
            
        }
    }
    
    private boolean canMove() {
        //negate result to return true if enemys move
        return !GameManager.getCurrentTurn();

    }
    
    public Enemy(int x, int y) {
        super();
        setX(x);
        setY(y);
        id = count;
        enemyList.add(this);
        System.out.println("Enemy created with id "+id);
        moved.add(false);
        this.count++;
        timer.start();
        calcWantsToMove();
    }
    
    
    private void calculateNextMove() {
        int xDistance = 0;
        int yDistance = 0;
        //left or right of player
        if (Player.getX() > this.getX()+this.getWidth()) {
            xDistance = (Integer)(Player.getX() - this.getX())/64;
        } else if (Player.getX()+Player.getWidth() < this.getX()) {
            xDistance = -(Integer)(this.getX() - Player.getX())/64-1;
        }
        //top or bottom of player
        if (Player.getY() > this.getY()) {
            yDistance = -(Integer) (Player.getY() - this.getY())/64;
        } else if (Player.getY() < this.getY()) {
            yDistance = (Integer) (this.getY() - Player.getY())/64+1;
        }
        System.out.println("Enemy at X = "+this.getX()/64+" , Y = "+this.getY()/64+" : dx = "+xDistance+" dy = "+yDistance);
        if (Math.abs(xDistance) > Math.abs(yDistance)) {
            nextMoveYAxis = false;
            if (xDistance > 0) {
                nextMoveForward = true;
                nextMoveTarget = this.getX() + 64;
            } else {
                nextMoveForward = false;
                nextMoveTarget = this.getX() - 64;
            }
        } else {
            nextMoveYAxis = true;
            if (yDistance > 0) {
                nextMoveForward = true;
                nextMoveTarget = this.getY() - 64;
            } else {
                nextMoveForward = false;
                nextMoveTarget = this.getY() + 64;
            }
        }
        //TODO check for other entities on target
        
        nextMoveCalculated = true;        
    }
    
    
    public void die() {
        kill();
        alive = false;
        moved.set(id, null);
        timer.stop();
    }

    //Player checks if he can move again
    public static boolean enemyMoveDone() {

        for (int i = 0; i < moved.size(); i++) {
            boolean t;
            try {
                t = moved.get(i);
            } catch (NullPointerException e) {
                t = true;
            }
            
            if (t == false) {
                return false;
            }
            
        } 
        System.out.println("Enemy Movement done");
        for (int i = 0; i < moved.size(); i++) {
            moved.set(i, false);
        } 
        return true;
    }

    private void doneMoving() {
        moved.set(id, true);
        nextMoveCalculated = false;
        setX(GridManager.correctXPosition(getX()));
        setY(GridManager.correctYPosition(getY()));
        //update collision model
        GridManager.update();
        //calculate if enemy will move next time again or not?
    }
    
    private void calcWantsToMove() {
        double random = Math.random();
        if (random < 0.4) {
            this.wantsToMove = false;
            doneMoving();
            return;
        }
        this.wantsToMove = true;
    }
    
    private boolean wantsToMove() {
        return this.wantsToMove;
    }
    
}
