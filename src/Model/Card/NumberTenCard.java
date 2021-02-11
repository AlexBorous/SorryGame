package Model.Card;

import javax.swing.*;

/**
 * This class represents a 10 card
 * @author  alex
 */

public class NumberTenCard extends Card {

    final  String options[]={"Move 10 spaces forward","Move 1 backwards"};
    /**
     * Constructor.
     * <b>PostCondition</b>
     * Creates a new 10 card with moveValue 10
     *
     */
    public NumberTenCard(){
        super(10,"Move  10 spaces forward or 1 backwards");
    }
    /**
     * <b>Accessor:</b>Shows a message dialog to the user for the choice he wish to make
     * <p>Post Condition:</p>Returns 10 if player wants to move 10 spaces forward
     * or -1 if he wants to move 1 BACKWARDS.(will be monitored in move() function)
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
        if(nextMove.equals("Move 10 spaces forward"))
            return 10;
        else
            return -1;

    }
    public String getClassName()
    {
        return "NumberTenCard";
    }

    @Override
    public int getMoveValue() {
        return act();
    }
}
