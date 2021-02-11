package Model.Card;

import javax.swing.*;

/**
 * This class represents an 11 card
 * @author  alex
 */
public class NumberElevenCard extends Card {
    final  String options[]={"Move 11 spaces forward","Swap"};
    /**
     * Constructor.
     * <b>PostCondition</b>
     * Creates a new 11 card
     */
    public NumberElevenCard(){
        super(11,"Move a pawn 11 spaces Forward or Swap");

    }

    /**
     * <b>Accessor:</b>Shows a message dialog to the user for the choice he wish to make
     * <p>Post Condition:</p>Returns 11 if player wants to move 11 spaces forward
     * or 0 if wants to swap pawns.
     */
     int act(){
         JFrame frame = new JFrame("Input Dialog Next Move");
         String nextMove = (String) JOptionPane.showInputDialog(frame,
                 "What is your next move?",
                 "Your move",
                 JOptionPane.QUESTION_MESSAGE,
                 null,
                 options,
                 options[0]);
         if(nextMove.equals("Move 11 spaces forward"))
         return 11;
         else
             return 0;

    }
    public String getClassName()
    {
        return "NumberElevenCard";
    }
    /**
     * <b>Accessor:<b/>
     * <b>PostCondition:</b>
     * @return
     */
    @Override
    public int getMoveValue() {
        return act();
    }
}
