package Model.Card;

import javax.swing.*;

/**
 * This class represents a 7 card
 * @author  alex
 */
public class NumberSevenCard extends Card {
    final  String options[]={"Move one pawn 7 spaces forward","Move 2 pawns"};
    /**
     * Constructor.
     * <b>PostCondition</b>
     * Creates a new 7 card with moveValue 7
     *
     */
    public NumberSevenCard(){
        super(7,"Move 1 Pawn +7 or 2 Pawns +7 (in Total)");

    }
    /**
     * <b>Accessor:</b>Shows a message dialog to the user for the choice he wish to make
     * <p>Post Condition:</p>Returns 7 if player wants to move 7 spaces forward with 1 pawn
     * or -20  if he wants to move 2 pawns.(will be monitored in move() function)
     */
    public int act(){
        JFrame frame = new JFrame("Input Dialog Next Move");
        String nextMove = (String) JOptionPane.showInputDialog(frame,
                "What is your next move?",
                "Your move",
                JOptionPane.QUESTION_MESSAGE,
                null,
                options,
                options[0]);
        if(nextMove.equals("Move one pawn 7 spaces forward"))
            return 7;
        else
            return -20;

    }

    /**
     * <b>Accessor:<b/>
     * <b>PostCondition:</b>returns the cards move value.
     * @return actValue
     */
    @Override
    public int getMoveValue() {
        return act();
    }
    public String getClassName()
    {
        return "NumberSevenCard";
    }
}
