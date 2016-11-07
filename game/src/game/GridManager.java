/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package game;

import com.sun.istack.internal.Nullable;
import java.awt.Point;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author fabi
 */
public class GridManager {
    
    private static int gridWidth = 12;
    //10 because enemies can spawn in a range of 0-10 although field is only at a [9]
    private static int gridHeight = 10;
    private static ArrayList<ArrayList<Integer>> grid = new ArrayList<>();
    private static boolean init = false;
    
    public static void update() {
        if (!init)
            nullGrid();
        clearGrid();
        List<GameObject> o = GameObject.getGameObjects();
        for (int a = 0; a < o.size(); a++) {
            if (o.get(a).getX() > 0) {
                int x = getGridX(o.get(a).getX());
                int y = getGridY(o.get(a).getY());

                if (o.get(a) instanceof Enemy)
                    grid.get(y).set(x, 2);
                else
                    grid.get(y).set(x, 1);
            } else {
               
            }
            
        }
    }
    
    private static void clearGrid() {
        for (int a = 0; a < grid.size(); a++) {
            for (int i = 0; i < grid.get(a).size(); i++) {
                grid.get(a).set(i, 0);
            }
        }
    }
    
    private static void nullGrid() {
        for (int a = 0; a < gridHeight; a++) {
            
            ArrayList<Integer> line = new ArrayList<>();
            for (int i = 0; i < gridWidth; i++) {
                line.add(0);
            }
            
            grid.add(line);
        }
           
        System.out.println("falsed grid"+grid.toString());
        init = true;
        
    }
    
    @Nullable
    public static boolean canMoveTop(Point a) {
        if (a.y-1 < 0)
            return false;
        int b = grid.get(a.y-1).get(a.x);
        if (b == 0)
            return true;
        if (b == 1) {
            return false;
        }
        if (b == 2) {
            for (Enemy e : Enemy.enemyList) {
                int ey = GridManager.getGridX(e.getX());
                if (ey == a.y-1) {
                    Enemy.killEnemyByID(e.getID());
                    System.out.println("killed enemy");
                }
            }
            
            return true;
        }
        return true;
    }
    
    public static boolean canMoveDown(Point a) {
        if (a.y+1 > gridHeight-1)
            return false;
        int b = grid.get(a.y+1).get(a.x);
        if (b == 0)
            return true;
        if (b == 1) {
            return false;
        }
        if (b == 2) {
            for (Enemy e : Enemy.enemyList) {
                int ey = GridManager.getGridY(e.getY());
                if (ey == a.y+1) {
                    Enemy.killEnemyByID(e.getID());
                    System.out.println("killed enemy");
                }
            }
            
            return true;
        }
        return true;
    }
    
    public static boolean canMoveLeft(Point a) {
        if (a.x-1 < 0)
            return false;
        int b = grid.get(a.y).get(a.x-1);
        if (b == 0)
            return true;
        if (b == 1) {
            return false;
        }
        if (b == 2) {
            for (Enemy e : Enemy.enemyList) {
                int ex = GridManager.getGridX(e.getX());
                if (ex == a.x-1) {
                    Enemy.killEnemyByID(e.getID());
                    System.out.println("killed enemy");
                }
            }
            
            return true;
        }
        return true;
    }
    
    public static boolean canMoveRight(Point a) {
        if (a.x+1 > gridWidth)
            return false;
        int b = grid.get(a.y).get(a.x+1);
        if (b == 0)
            return true;
        if (b == 1) {
            return false;
        }
        if (b == 2) {
            for (Enemy e : Enemy.enemyList) {
                int ex = GridManager.getGridX(e.getX());
                if (ex == a.x+1) {
                    Enemy.killEnemyByID(e.getID());
                    System.out.println("killed enemy");
                }
            }
            
            return true;
        }
        return true;
    }
    
    
    
    
    public static boolean enemyCanMoveTop(int x, int y) {
        return true;
    }
    public static boolean enemyCanMoveDown(int x, int y) {
        return true;
    }
    public static boolean enemyCanMoveRight(int x, int y) {
        return true;
    }
    public static boolean enemyCanMoveLeft(int x, int y) {
        return true;
    }
    
    
    
    public static int getGridX(int x) {
        int correctX = (Integer) (x/64);
        return correctX;
    }
    
    public static int getGridY(int y) {
        int correctY = (Integer) (y/64);
        return correctY;
    }
    
    public static int correctXPosition(int x) {
        int correctX = (Integer) (x/64);
        return correctX * 64;
    }
    
    public static int correctYPosition(int y) {
        int correctY = (Integer) (y/64);
        return correctY * 64;
    }
    
}
