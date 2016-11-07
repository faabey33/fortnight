/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package game;

/**
 *
 * @author fabi
 */
public class GameManager {
    
    public final static boolean PLAYER = true;
    public final static boolean ENEMY = false;
    
    private static boolean currentTurn = PLAYER;
    /**
     * @return the currentTurn
     */
    public static boolean getCurrentTurn() {
        return currentTurn;
    }

    /**
     * @param aCurrentTurn the currentTurn to set
     */
    public static void setCurrentTurn(boolean aCurrentTurn) {
        currentTurn = aCurrentTurn;
    }
}
