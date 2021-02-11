package Model.Card;


/**
 * Contains the methods signatures needed
 * for creating cards(simple or sorry ones)
 * @version 1.0.0
 * @author csd3808
 */
public abstract class Card {
    private int moveValue;
    private String description;
    boolean chooseAgain=false;
   public Card(int moveValue,String description){
        setMoveValue(moveValue);
        setDescription(description);
    }
    /**
     * <b>Transformer:</b>
     * @param description
     */
    public void setDescription(String description) {
        this.description = description;
    }

    /**
     * <b>Accesor:</b>returns Card's description
     * <b>PostCondition:</b>card's description has been returned
     * @return String value
     */
    public String getDescription() {
        return description;
    }

    /**
     * <b>Transformer:</b>
     */
    public void setMoveValue(int i) {
        this.moveValue=i;
    }

    /**
     * <b>Accesor:</b>returns Card's move value
     * <b>PostCondition:</b>card's move value has been returned
     * @return int move value
     */
    public abstract int getMoveValue();

    /**
     * <b>Transformer:</b>
     *
     */
    public void setChooseAgain(boolean chooseAgain) {
        this.chooseAgain = chooseAgain;
    }

    /**
     * <b>Observer:<b/>
     * @return true if the player can choose again , false otherwise
     */
    public boolean isChooseAgain() {
        return chooseAgain;
    }


    /**
     * Returns the String representation of a card
     * <p><b>PostCondition:</b>The string representation of a card is returned</p>
     * @return The String representation of a card
     */

    public String toString() {
        return this.getClass() + " Description:  " + this.description;
    }
    public String getClassName()
    {
        return "";
    }
}
