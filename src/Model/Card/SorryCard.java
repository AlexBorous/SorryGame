package Model.Card;

import Model.Pawn;

/**
 * This class represents a Sorry card
 * @author  alex
 */
public class SorryCard extends Card {
    /**
     * <b>Constructor</b>
     * Creates a Sorry Card
     */
public SorryCard(){
    super(0,"Select a pawn on Start and Swap it");

}


    public String getClassName()
    {
        return "SorryCard";
    }

    @Override
    public int getMoveValue() {

        return -99;
    }
}
