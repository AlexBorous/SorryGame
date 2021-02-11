package Model.Card;

import javax.swing.*;

/**
 * This class represents an 2 card
 * @author  alex
 */
public class NumberTwoCard extends Card {
    final  String options[]={"Move 2 spaces forward","Rescue a pawn from the start"};
    /**
     * <b>Constructor</b>
     * Creates a Number 2 Card with move value 2 and sets chooseAgain true for the player to play again.
     */
    public NumberTwoCard(){
        super(2,"Rescue or Move +2 !You will play again!");
        setChooseAgain(true);
    }
    /**
     * <b>Accessor:</b>Shows a message dialog to the user for the choice he wish to make .
     * <p>Post Condition:</p>Returns 2 if player wants to move 2 space forward
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
        if(nextMove.equals("Move 2 spaces forward"))
            return 2;
        else
            return -10;

    }

    @Override
    public int getMoveValue() {
        return act();
    }

    public String getClassName()
    {
        return "NumberTwoCard";
    }
}
