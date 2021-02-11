package Model.Card;
/**
 * This class represents a simple card(3,5,6,8,9,12)
 * @author  alex
 */
public class SimpleNumberCard extends Card {
   private int number=0;

    /**
     * <b>Constructor </b>Creates a new Simple Number Card with moveValue Number
     * @param number
     */
    public SimpleNumberCard(int number){
        super(number,"Move +"+number+" spaces");
        this.number=number;
    }
    /**
     * <b>Accessor:<b/>
     * <b>PostCondition:</b>returns the cards move value.
     * @return number
     */

    @Override
    public int getMoveValue() {
        return number;
    }

    @Override
    public String getClassName() {
        return "card"+getMoveValue();
    }
}
