package Model;

import Model.Card.*;
import Model.Square.*;
import java.util.ArrayList;
import java.util.Collections;

/**
 * Deck  initializes the cards and the board
 * implements function for player movement and card manipulation
 * @version 1.0.0
 * @author csd3808
 */
public class Deck  {
    Card lastCardPlayed;
    ArrayList<Card> gameCards = new ArrayList();
    public Square[] map = new Square[75];
     int turn=1;
    int nextCard=0;

    /**
     * <b>Transformer:</b>
     * Initialize the cards
     */
public void initCards(){

    for( int index = 0; index < 4; index++ )
    {
        gameCards.add( new SimpleNumberCard( 3) );
        gameCards.add( new SimpleNumberCard( 5) );
        gameCards.add( new SimpleNumberCard( 8) );
        gameCards.add( new SimpleNumberCard( 12));
        gameCards.add( new NumberOneCard() );
        gameCards.add( new NumberTwoCard() );
        gameCards.add( new NumberFourCard() );
        gameCards.add( new NumberSevenCard() );
        gameCards.add( new NumberTenCard() );
        gameCards.add( new NumberElevenCard() );
        gameCards.add( new SorryCard() );
    }
    System.out.println( "Deck created." );
}

    /**
     * <b>Transformer:</b>
     * Initialize the GameMap
     */
    public void initMap(){
        for(int i=0;i<=15;i++){
            if(i==2||i==11)
                map[i]=new EndSlideSquare("red");
            else if(i==3||i==4||i==5||i==12||i==13)
                map[i]=new SlideSquare("red");
            else if(i==6||i==14)
                map[i]=new StartSquare("red");
            else
                map[i]=new SimpleSquare("button"+i);

        }
        for(int i=15;i<=29;i++)
        {
            if(i==16||i==25)
                map[i]=new EndSlideSquare("blue");
            else if(i==17||i==18||i==19||i==26||i==27)
                map[i]=new SlideSquare("blue");
            else if(i==20||i==28)
                map[i]=new StartSquare("blue");
            else
                map[i]=new SimpleSquare("button"+i);

        }

        for(int i=30;i<=44;i++)
        {
            if(i==31||i==40)
                map[i]=new EndSlideSquare("yellow");
            else if(i==32||i==33||i==34||i==41||i==42)
                map[i]=new SlideSquare("yellow");
            else if(i==35||i==43)
                map[i]=new StartSquare("yellow");
            else
                map[i]=new SimpleSquare("button"+i);

        }

        for(int i=45;i<=61;i++)
        {
            if(i==47||i==56)
                map[i]=new EndSlideSquare("green");
            else if(i==48||i==49||i==50||i==57||i==58)
                map[i]=new SlideSquare("green");
            else if(i==51||i==59)
                map[i]=new StartSquare("green");
            else
                map[i]=new SimpleSquare("button"+i);

        }
        for(int i=62;i<=66;i++)
            map[i]=new SafetyZoneSquare("red"+i);

        for(int i=67;i<=71;i++)
            map[i]=new SafetyZoneSquare("yellow"+i);

        map[71]=new HomeSquare("redHome");
        map[72]=new HomeSquare("yellowHome");


    }
    /**
     * <b>Transformer:</b>
     *  Sets turn =1 if is player's 1 turn or 2 otherwise
     *  <p>Post condition:</p>Turn is set
     * @param turn
     */
    public void setTurn(int turn) {
        this.turn = turn;
    }
    /**
     * <b>Accessor:</b>
     *  Returns the turn value
     * @return turn
     */
    public int getTurn() {
        return turn;
    }
    /**
     * <b>Transformer:</b>
     *  Removes a card from the game Cards with specific index
     *  <p>Pre Condition:</p>Game cards must not be empty
     *  <p>Post condition:</p>Card is removed
     * @param index
     */
    public void remove(int index){
        gameCards.remove(index);
    }

    /**
     * <b>Accessor:</b>Gets the last card
     * <p>Pre Condition:</p>A card is set as lastCard
     * <p>Post Condition:</p>returns the last card played
     *
     * @return
     */
    public Card getLastCardPlayed() {
        return this.lastCardPlayed;
    }
    /**
     * <b>Transformer:</b>
     *  Sets a card  the last card played
     *  <p>Post condition:</p>Sets lastCardPlayed as the card in the parameter
     * @param lastCardPlayed
     */
    public void setLastCardPlayed(Card lastCardPlayed) {
        this.lastCardPlayed = lastCardPlayed;
}
    public int getNumberOfCards()
    {
        return gameCards.size();
    }
    /**
     * <b>Observer:</b>
     * Checks if there are available cards at the  cards ArrayList.
     * @return true if cards List is empty , false otherwise
     */
    public boolean isCardsOver( )
    {
        if(gameCards.size()==1) {
            initCards();
            randomizeCards();
            return true;
        }
        else
            return false;
    }
    /**
     * <b>Accessor:</b>Gets a card from the stack
     * @param index  to choose a card from.
     * @return Card at the index in the ArrayList of cards.
     */
    public Card get( int index )
    {
        if(isCardsOver())
        {
            return gameCards.get(0);
        }
        System.out.println(gameCards.size());
        return gameCards.get(index);
    }
    /**<b>Constructor</b>
     * Post Condition: Creates a deck.
     */
    public Deck(){ }
    /**
     * <b>Accessor:</b>Gets the next card
     * <p>Pre Condition:</p>Game cards are not empty
     * <p>Post Condition:</p>returns the card that is to be played
     *
     * @return Card
     */
    public Card getNextCard(){
        if(nextCard==0)
        nextCard++;
        else
            nextCard=0;
        if(isCardsOver())
            nextCard=0;
        Card temp= gameCards.get(nextCard);
        remove(nextCard);
        return temp;

    }
    public Square[] getMap() {
        return map;
    }
    /**
     * <b>Transformer:</b>Randomize the game cards
     * <p>Pre Condition:</p>Game cards must be initialized
     * <p>Post Condition:</p>returns the game cards in random order
     *
     * @return nothing
     */
    public void randomizeCards(){

        Collections.shuffle(gameCards);
    }
}

