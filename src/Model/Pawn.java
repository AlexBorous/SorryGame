package Model;



import Model.Square.Square;

import javax.swing.*;

/**
 * Represents the Pawn class
 */
public class Pawn extends JButton {
    private String player;
    boolean canBeSafe =true;
    boolean isSafe=false;
    boolean isOver=false;
    int position;
    /**
     * <b>Constructor:</b>Creates a pawn
     * and sets a color
     * @param player
     */
    public Pawn(String player){
        this.player=player;

    }
    /**
     * <b>Transformer:</b>Sets if pawn has finished
     * <p>Post Condition</p>The pawn is finished, no input will be accepted
     * @param over
     */
    public void setOver(boolean over) {
        isOver = over;
    }
    /**
     * <b>Accessor:</b>Gets if pawn is over
     * @return true if is finished , false otherwise
     */
    public boolean isOver() {
        return isOver;
    }

    /**
     * <b>Transformer:</b>Sets if pawn is in safe area
     * <p>Post Condition</p>sets  Is safe
     * @param on
     */
    public void setCanBeSafe(boolean on) {
        canBeSafe = on;
    }

    /**
     * <b>Observer:</b>Gets if the pawn is safe
     * @return  true if pawn isSafe
     * false otherwise
     */
    public boolean isCanBeSafe() {
        return canBeSafe;
    }

    /**
     * <b>Transformer:</b>Sets pawn's position
     * <p>Post Condition:</p>Pawn has a new position set
     */

    public void setPosition(int position) {
        this.position = position;
    }
    /**
     * <b>Accessor:</b>Gets pawn's position
     * @return pawn's position
     */
    public int getPosition() {
        return position;
    }
    /**
     * <b>Transformer:</b>Sets if pawn has entered safe zone
     * @param safe
     */
    public void setSafe(boolean safe) {
        isSafe = safe;
    }
    /**
     * <b>Accessor:</b>Gets if pawn is in safe zone
     * @return true if is inside safe zone , false otherwise
     */
    public boolean isSafe()
    {
        return isSafe;
    }
}
