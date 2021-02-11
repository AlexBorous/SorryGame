package Model.Card;

/**
 * This class represents an 4 card
 * @author  alex
 */
public class NumberFourCard extends Card {
    /**
     * <b>Constructor</b>Makes a new 4 card with move value -4
     */
    public NumberFourCard(){
        super(-4,"Move a pawn 4 spaces Backwards");
    }
    public String getClassName()
    {
        return "NumberFourCard";
    }

    /**
     * <b>Accessor:<b/>
     * <b>PostCondition:</b>returns the cards move value.
     * @return -4
     */
    @Override
    public int getMoveValue() {
        return -4;
    }
}
