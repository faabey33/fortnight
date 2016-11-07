/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package game;

import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author fabi
 */
public class GameObject {
    
    private int x;
    private int y;
    
    public boolean alive = true;
    
    private static ArrayList<GameObject> objects = new ArrayList();
    
    private int width = 64;
    private int height = 64;
    
    public void kill() {
        alive = false;
    }
    
    public GameObject() {
        add(this);
    }
    
    public static void add(GameObject o) {
        objects.add(o);
    }
    
    public static List<GameObject> getGameObjects() {
        return objects;
    }
    
    public GameObject(int x, int y) {
        setX(x);
        setY(y);
        add(this);
    }

    /**
     * @return the x
     */
    public int getX() {
        return x;
    }

    /**
     * @param x the x to set
     */
    public void setX(int x) {
        this.x = x;
    }

    /**
     * @return the y
     */
    public int getY() {
        return y;
    }

    /**
     * @param y the y to set
     */
    public void setY(int y) {
        this.y = y;
    }

    /**
     * @return the width
     */
    public int getWidth() {
        return width;
    }

    /**
     * @param width the width to set
     */
    public void setWidth(int width) {
        this.width = width;
    }

    /**
     * @return the height
     */
    public int getHeight() {
        return height;
    }

    /**
     * @param height the height to set
     */
    public void setHeight(int height) {
        this.height = height;
    }
    
}
