package Model.Card;

import javax.swing.*;

/**
 * This class represents an 1 card
 * @author  alex
 */
public class NumberOneCard extends Card {
    final  String options[]={"Move 1 space forward","Rescue a pawn from the Start"};
    /**
     * Constructor.
     * <b>PostCondition</b>
     * Creates a new 1 card with moveValue 1
     *
     */
    public NumberOneCard()
    {
        super(1," Rescue a pawn from Start or Move +1 ");

    }
    /**
     * <b>Accessor:</b>Shows a message dialog to the user for the choice he wish to make
     * <p>Post Condition:</p>Returns 1 if player wants to move 1 space forward
     * or -10 if wants to rescue a pawn from the Start.
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
        if(nextMove.equals("Move 1 space forward"))
       return 1;
        else
            return -10;
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
        return "NumberOneCard";
    }
}
