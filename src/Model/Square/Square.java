package Model.Square;



/**
 * Contains methods for creating a square(of any kind)
 */
public abstract class Square {
    boolean occupied=false;
    String button_position;

    /**
     * <b>Constructor</b>Creates a square
     * @param
     */
    public Square(String position)
    {
        this.button_position=position;
    }

    /**
     * <b>Transformer:</b>Sets the state of the square
     * <p>Post condition:</p>The square is occupied or not
     * @param state
     */
    public void setOccupied(boolean state){
        this.occupied=state;
    }

    /**
     * <b>Observer:</b></b>Gets the state of the square
     * <b>Pre Condition:</b>The square to been set in a state
     * @return true if the square is occupied , false otherwise
     */

    public boolean IsOccupied(){
        return occupied;
    }

    public String getButton_position() {
        return button_position;
    }
}
