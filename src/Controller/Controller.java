package Controller;


import Model.Card.*;
import Model.Deck;
import Model.Pawn;
import Model.Player;
import View.View;
import View.PopUpMessage;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.io.UnsupportedEncodingException;
import java.util.Scanner;


/**
 * Controller  controls all
 * of the operations executed and makes the "connection" between model and view
 * @version 1.0.0
 * @author csd3808
 */
public class    Controller implements ActionListener {
    Deck deck;
    View view;
    Player player1,player2;
    Pawn p1,p2,p3,p4;
    Card currentCard;
    /**
     * <b>Constructor:</b> Constructs a new Controller and starts a new game.<br />
     * <b>Post condition</b>: Create a new Controller
     *  with 2 players , an instance of Deck class
     * initializes cards and maps , makes the map visible
     */
    public Controller(){
        this.player1=new Player("Green","Player1");
        this.player2=new Player("Blue","Player2");
        this.p1=new Pawn("Player1");
        this.p2=new Pawn("Player1");
        this.p3=new Pawn("PLayer2");
        this.p4=new Pawn("Player2");
        this.view=new View();
        this.deck=new Deck();
        deck.initMap();
        setListeners();
        deck.initCards();
        deck.randomizeCards();
        view.btnCurrentCard.setIcon(new ImageIcon("NumberSevenCard.png"));
        this.currentCard=deck.get(0);
        view.setInfoText(currentCard.getDescription());
        if(currentCard instanceof SimpleNumberCard)
            view.btnCurrentCard.setIcon(new ImageIcon("card"+currentCard.getMoveValue()+".png"));
        else
            view.btnCurrentCard.setIcon(new ImageIcon(currentCard.getClassName()+".png"));
        this.p1.setPosition(71);
        this.p2.setPosition(71);
        this.p3.setPosition(72);
        this.p4.setPosition(72);
        String player1Name = JOptionPane.showInputDialog("Type 1st player's name");
        String player2Name = JOptionPane.showInputDialog("Type 2nd player's name");
        if(!player1Name.equals(""))
        player1.setName(player1Name);
        if(!player2Name.equals(""))
        player2.setName(player2Name);
        view.setTurnText(player1.getName());
    }
    /**
     * Launch the application.
     */
    public static void main(String[] args) {

        Controller c = new Controller();


    }

    /**
     * <b>Transformer(mutative):<b/>
     * Sets all the listeners
     *<b>Post Condition:</b>All important objects have a listener
     */
    public void setListeners(){
        view.foldButton.addMouseListener(new PawnListener());
        view.yellowPawn1.addMouseListener(new PawnListener());
        view.yellowPawn2.addMouseListener(new PawnListener());
        view.redPawn1.addMouseListener(new PawnListener());
        view.redPawn2.addMouseListener(new PawnListener());
        view.btnCurrentCard.addMouseListener(new PawnListener());
        view.newGame.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                PopUpMessage.infoBox("Recreating the game...","New Game");
                view.setFrameVisible(false);
                new Controller();
            }
        });
        view.quit.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                PopUpMessage.infoBox("Bye","Quit");
                System.exit(0);
            }
        });
        view.loadButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                if(p1.getPosition()!=71&&p2.getPosition()!=71&&p3.getPosition()!=72&&p4.getPosition()!=72)
                {
                    PopUpMessage.infoBox("You cannot load a game , while playing another","Sorry");
                    return;
                }
                PopUpMessage.infoBox("Loaded!","Load Game");
                Scanner scanner = null;
                try {
                    scanner = new Scanner(new File("saveGame.txt"));
                } catch (FileNotFoundException e) {
                    e.printStackTrace();
                }
                int [] tall = new int [4];
                int i = 0;
                assert scanner != null;
                while(scanner.hasNextInt())
                {
                    tall[i] = scanner.nextInt();
                    i++;
                }
                System.out.println(tall[0]+" "+tall[1]+" "+tall[2]+" "+tall[3]);
                if(tall[0]<62) {
                    p1.setPosition(tall[0]);
                    ultimateMovePawn(p1.getPosition(), p1);
                }
                else if(tall[0]==73)
                {
                    p1.setOver(true);
                    p1.setPosition(73);
                    view.setPawnOver(view.redPawn1);
                }
                else if(tall[0]<67)
                {
                    view.updatePawn(tall[0],view.redPawn1);
                    p1.setPosition(tall[0]);
                    p1.setSafe(true);
                }
                if(tall[1]<62) {
                    p2.setPosition(tall[1]);
                    ultimateMovePawn(p2.getPosition(), p2);
                }
                else if(tall[1]==73)
                {
                    p2.setOver(true);
                    p2.setPosition(73);
                    view.setPawnOver(view.redPawn2);
                }
                else if(tall[1]<67)
                {
                    view.updatePawn(tall[1],view.redPawn2);
                    p2.setPosition(tall[1]);
                    p2.setSafe(true);
                }
                if(tall[2]<62)
                {
                    p3.setPosition(tall[2]);
                    ultimateMovePawn(p3.getPosition(),p3);
                }
                else if(tall[2]==74)
                {
                    p3.setOver(true);
                    p3.setPosition(74);
                    view.setPawnOver(view.yellowPawn1);
                }
                else if(tall[2]<72)
                {
                    view.updatePawn(tall[2],view.yellowPawn1);
                    p3.setPosition(tall[2]);
                    p3.setSafe(true);
                }

                if(tall[3]<62)
                {
                    p4.setPosition(tall[3]);
                    ultimateMovePawn(p4.getPosition(),p4);
                }
                else if(tall[3]==74)
                {
                    p4.setOver(true);
                    p4.setPosition(74);
                    view.setPawnOver(view.yellowPawn2);
                }
                else if(tall[3]<72)
                {
                    view.updatePawn(tall[3],view.yellowPawn2);
                    p4.setPosition(tall[3]);
                    p4.setSafe(true);
                }

            }
        });
        view.saveGame.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {

                PrintWriter writer = null;
                try {
                    writer = new PrintWriter("saveGame.txt", "UTF-8");
                } catch (FileNotFoundException | UnsupportedEncodingException e) {
                    e.printStackTrace();
                }
                writer.println(p1.getPosition()+" "+p2.getPosition());
                writer.println(p3.getPosition()+" "+p4.getPosition());
                writer.close();
                PopUpMessage.infoBox("Game saved!!!","All good");
            }
        });

    }
    /**
     * <b>Transformer:</b>
     *  Checks if the pawn is eligible to move there and moves the pawn
     *  <p>Post condition:</p>The pawn moves there
     * @param p A pawn object
     *
     */
    public void ultimateMovePawn(int index,Pawn p)
    {

        if(p.isSafe())
        {
                if((p==p1||p==p2) &&p.getPosition()<73&&index>61&&index<68)
                {

                    if(deck.map[index].IsOccupied())
                        return;
                    deck.map[p.getPosition()].setOccupied(false);
                    p.setPosition(index);
                    if(p.getPosition()==67)
                    {
                        p.setPosition(73);
                        if(p==p1)
                        {
                            view.setPawnOver(view.redPawn1);
                            p1.setOver(true);
                            if(p2.isOver())
                                gameOver();
                        }
                        else
                        {
                            view.setPawnOver(view.redPawn2);
                            p2.setOver(true);
                            if(p1.isOver())
                                gameOver();
                        }
                        return;
                    }

                    deck.map[p.getPosition()].setOccupied(true);
                    if(p==p1)
                    view.updatePawn(index,view.redPawn1);
                    else
                        view.updatePawn(index,view.redPawn2);
                }
                else if((p==p3||p==p4) &&p.getPosition()<74&&index>66&&index<73)
                {



                    if(deck.map[index].IsOccupied())
                        return;


            deck.map[p.getPosition()].setOccupied(false);
            p.setPosition(index);

            if(p.getPosition()==72)
            {
                p.setPosition(74);
                if(p==p3)
                {
                    view.setPawnOver(view.yellowPawn1);
                    p3.setOver(true);
                    if(p4.isOver())
                        gameOver();
                }
                else
                {
                    view.setPawnOver(view.yellowPawn2);
                    p4.setOver(true);
                    if(p3.isOver())
                        gameOver();
                }
                return;
            }
            deck.map[p.getPosition()].setOccupied(true);

            if(p==p3)
                view.updatePawn(index,view.yellowPawn1);
            else
                view.updatePawn(index,view.yellowPawn2);
            return;
        }
            //checks if the other player's pawn has an available move
                    if(p==p1)
                    {
                        if(p2.getPosition()<61)
                            ultimateMovePawn(currentCard.getMoveValue()+p2.getPosition(),p2);
                    }
                    else if(p==p2){
                        if(p1.getPosition()<61)
                            ultimateMovePawn(currentCard.getMoveValue()+p1.getPosition(),p1);
                    }

                if(p==p3)
                {
                    if(p4.getPosition()<61)
                        ultimateMovePawn(currentCard.getMoveValue()+p4.getPosition(),p4);
                }
                else {
                    if (p3.getPosition() < 61)
                        ultimateMovePawn(currentCard.getMoveValue() + p3.getPosition(), p3);
                }

        return;
        }

        if(index>61)
        {
            index=index-60;
        }
        if(index<0)
            index=60+index;
        else if(index==61)
            index=0;
        //set method for safety zone

            if(p.isCanBeSafe())
            {
                if((p==p1||p==p2) && index==3)
                {
                    deck.map[p.getPosition()].setOccupied(false);
                    p.setPosition(62);
                    if(deck.map[62].IsOccupied())
                    {
                        p.setCanBeSafe(false);
                        ultimateMovePawn(index,p);
                        return;
                    }
                    deck.map[62].setOccupied(true);
                    p.setSafe(true);
                    if(p==p1)
                    view.updatePawn(62,view.redPawn1);
                    else
                        view.updatePawn(62,view.redPawn2);
                    return;
            }
                else if((p==p3||p==p4)&&index==33)
                {
                    deck.map[p.getPosition()].setOccupied(false);
                    p.setPosition(67);
                    if(deck.map[67].IsOccupied())
                    {
                        p.setCanBeSafe(false);
                        ultimateMovePawn(index,p);
                        return;
                    }
                    deck.map[67].setOccupied(true);
                    p.setSafe(true);
                    if(p==p3)
                        view.updatePawn(67,view.yellowPawn1);
                    else
                        view.updatePawn(67,view.yellowPawn2);
                    return;
                }

            }

        //set methods for slides
        if(!deck.map[index].IsOccupied()) {
            if(p==p1)
            {
                deck.map[p1.getPosition()].setOccupied(false);
                p1.setPosition(index);
                deck.map[p1.getPosition()].setOccupied(true);
                view.updatePawn(index,view.redPawn1);
                if(index==31)
                {
                    //yellowStartSquare(3)
                    ultimateMovePawn(p1.getPosition()+4,p);
                    //check if there are other pawns in between or at the end
                    if(p3.getPosition()==32||p3.getPosition()==34||p3.getPosition()==35)
                    {
                        deck.map[p3.getPosition()].setOccupied(false);
                        p3.setPosition(72);
                        view.setPawnHome(view.yellowPawn1);
                        return;
                    }
                    else if(p4.getPosition()==32||p4.getPosition()==34||p4.getPosition()==35)
                    {
                        deck.map[p4.getPosition()].setOccupied(false);
                        p4.setPosition(72);
                        view.setPawnHome(view.yellowPawn2);
                        return;
                    }

                }
                else if(index==40)
                {
                    ultimateMovePawn(p1.getPosition()+3,p);
                    //yellowStartSquare(2)
                    if(p3.getPosition()==41||p3.getPosition()==42)
                    {
                        deck.map[p3.getPosition()].setOccupied(false);
                        p3.setPosition(72);
                        view.setPawnHome(view.yellowPawn1);
                        return;
                    }
                    else if(p4.getPosition()==41||p4.getPosition()==42)
                    {
                        deck.map[p4.getPosition()].setOccupied(false);
                        p4.setPosition(72);
                        view.setPawnHome(view.yellowPawn2);
                        return;
                    }
                }
                else if(index==16)
                {
                    ultimateMovePawn(p1.getPosition()+4,p);
                    //blueStartSquare(3)
                    if(p3.getPosition()==17||p3.getPosition()==18||p3.getPosition()==19)
                    {
                        deck.map[p3.getPosition()].setOccupied(false);
                        p3.setPosition(72);
                        view.setPawnHome(view.yellowPawn1);
                        return;
                    }
                    else if(p4.getPosition()==17||p4.getPosition()==18||p4.getPosition()==19)
                    {
                        deck.map[p4.getPosition()].setOccupied(false);
                        p4.setPosition(72);
                        view.setPawnHome(view.yellowPawn2);
                        return;
                    }
                }
                else if(index==25)
                {
                    ultimateMovePawn(p1.getPosition()+3,p);
                    if(p3.getPosition()==26||p3.getPosition()==27)
                    {
                        deck.map[p3.getPosition()].setOccupied(false);
                        p3.setPosition(72);
                        view.setPawnHome(view.yellowPawn1);
                        return;
                    }
                    else if(p4.getPosition()==26||p4.getPosition()==27)
                    {
                        deck.map[p4.getPosition()].setOccupied(false);
                        p4.setPosition(72);
                        view.setPawnHome(view.yellowPawn2);
                        return;
                    }
                }
                else if(index==47)
                {
                    ultimateMovePawn(p1.getPosition()+4,p);
                    if(p3.getPosition()==48||p3.getPosition()==49||p3.getPosition()==50)
                    {
                        deck.map[p3.getPosition()].setOccupied(false);
                        p3.setPosition(72);
                        view.setPawnHome(view.yellowPawn1);
                        return;
                    }
                    else if(p4.getPosition()==48||p4.getPosition()==49||p4.getPosition()==50)
                    {
                        deck.map[p4.getPosition()].setOccupied(false);
                        p4.setPosition(72);
                        view.setPawnHome(view.yellowPawn2);
                        return;
                    }
                }
                else if(index==56)
                {
                    ultimateMovePawn(p1.getPosition()+3,p);
                    if(p3.getPosition()==57||p3.getPosition()==58)
                    {
                        deck.map[p3.getPosition()].setOccupied(false);
                        p3.setPosition(72);
                        view.setPawnHome(view.yellowPawn1);
                        return;
                    }
                    else if(p4.getPosition()==57||p4.getPosition()==58)
                    {
                        deck.map[p4.getPosition()].setOccupied(false);
                        p4.setPosition(72);
                        view.setPawnHome(view.yellowPawn2);
                        return;
                    }
                }
            }
            else if(p==p2)
            {

                deck.map[p2.getPosition()].setOccupied(false);
                p2.setPosition(index);
                deck.map[p2.getPosition()].setOccupied(true);
                view.updatePawn(index,view.redPawn2);
                if(index==31)
                {
                    //yellowStartSquare(3)
                    ultimateMovePawn(p2.getPosition()+4,p);
                    //check if there are other pawns in between or at the end
                    if(p3.getPosition()==32||p3.getPosition()==34||p3.getPosition()==35)
                    {
                        deck.map[p3.getPosition()].setOccupied(false);
                        p3.setPosition(72);
                        view.setPawnHome(view.yellowPawn1);
                        return;
                    }
                    else if(p4.getPosition()==32||p4.getPosition()==34||p4.getPosition()==35)
                    {

                        deck.map[p4.getPosition()].setOccupied(false);
                        p4.setPosition(72);
                        view.setPawnHome(view.yellowPawn2);
                        return;
                    }

                }
                else if(index==40)
                {
                    //yellowStartSquare(2)
                    ultimateMovePawn(p2.getPosition()+3,p);
                    if(p3.getPosition()==41||p3.getPosition()==42)
                    {
                        deck.map[p3.getPosition()].setOccupied(false);
                        p3.setPosition(72);
                        view.setPawnHome(view.yellowPawn1);
                        return;
                    }
                    else if(p4.getPosition()==41||p4.getPosition()==42)
                    {
                        deck.map[p4.getPosition()].setOccupied(false);
                        p4.setPosition(72);
                        view.setPawnHome(view.yellowPawn2);
                        return;
                    }
                }
                else if(index==16)
                {
                    //blueStartSquare(3)
                    ultimateMovePawn(p2.getPosition()+4,p);
                    if(p3.getPosition()==17||p3.getPosition()==18||p3.getPosition()==19)
                    {
                        deck.map[p3.getPosition()].setOccupied(false);
                        p3.setPosition(72);
                        view.setPawnHome(view.yellowPawn1);
                        return;
                    }
                    else if(p4.getPosition()==17||p4.getPosition()==18||p4.getPosition()==19)
                    {
                        deck.map[p4.getPosition()].setOccupied(false);
                        p4.setPosition(72);
                        view.setPawnHome(view.yellowPawn2);
                        return;
                    }
                }
                else if(index==25)
                {
                    ultimateMovePawn(p2.getPosition()+3,p);
                    if(p3.getPosition()==26||p3.getPosition()==27)
                    {
                        deck.map[p3.getPosition()].setOccupied(false);
                        p3.setPosition(72);
                        view.setPawnHome(view.yellowPawn1);
                        return;
                    }
                    else if(p4.getPosition()==26||p4.getPosition()==27)
                    {
                        deck.map[p4.getPosition()].setOccupied(false);
                        p4.setPosition(72);
                        view.setPawnHome(view.yellowPawn2);
                        return;
                    }
                }
                else if(index==47)
                {
                    ultimateMovePawn(p2.getPosition()+4,p);
                    if(p3.getPosition()==48||p3.getPosition()==49||p3.getPosition()==50)
                    {
                        deck.map[p3.getPosition()].setOccupied(false);
                        p3.setPosition(72);
                        view.setPawnHome(view.yellowPawn1);
                        return;
                    }
                    else if(p4.getPosition()==48||p4.getPosition()==49||p4.getPosition()==50)
                    {
                        deck.map[p4.getPosition()].setOccupied(false);
                        p4.setPosition(72);
                        view.setPawnHome(view.yellowPawn2);
                        return;
                    }
                }
                else if(index==56)
                {
                    ultimateMovePawn(p2.getPosition()+3,p);
                    if(p3.getPosition()==57||p3.getPosition()==58)
                    {
                        deck.map[p3.getPosition()].setOccupied(false);
                        p3.setPosition(72);
                        view.setPawnHome(view.yellowPawn1);
                        return;
                    }
                    else if(p4.getPosition()==57||p4.getPosition()==58)
                    {
                        deck.map[p4.getPosition()].setOccupied(false);
                        p4.setPosition(72);
                        view.setPawnHome(view.yellowPawn2);
                        return;
                    }
                }
            }
            else if(p==p3)
            {
                deck.map[p3.getPosition()].setOccupied(false);
                p3.setPosition(index);
                deck.map[p3.getPosition()].setOccupied(true);
                view.updatePawn(index,view.yellowPawn1);
                if(index==2)
                {
                    //redStartSquare(3)
                    ultimateMovePawn(p3.getPosition()+4,p);
                    if(p1.getPosition()==3||p1.getPosition()==4||p1.getPosition()==5)
                    {
                        deck.map[p1.getPosition()].setOccupied(false);
                        p1.setPosition(71);
                        view.setPawnHome(view.redPawn1);
                        return;
                    }
                    else if(p2.getPosition()==3||p2.getPosition()==4||p2.getPosition()==5)
                    {
                        deck.map[p2.getPosition()].setOccupied(false);
                        p2.setPosition(71);
                        view.setPawnHome(view.redPawn2);
                        return;
                    }
                }
                else if(index==11)
                {
                    //redStartSquare(2)
                    ultimateMovePawn(p3.getPosition()+3,p);
                    if(p1.getPosition()==12||p1.getPosition()==13)
                    {
                        deck.map[p1.getPosition()].setOccupied(false);
                        p1.setPosition(71);
                        view.setPawnHome(view.redPawn1);
                        return;
                    }
                    else if(p2.getPosition()==12||p2.getPosition()==13)
                    {
                        deck.map[p2.getPosition()].setOccupied(false);
                        p2.setPosition(71);
                        view.setPawnHome(view.redPawn2);
                        return;
                    }
                }
                else if(index==16)
                {
                    //blueStartSquare(3)
                    ultimateMovePawn(p3.getPosition()+4,p);
                    if(p1.getPosition()==17||p1.getPosition()==18||p1.getPosition()==19)
                    {
                        deck.map[p1.getPosition()].setOccupied(false);
                        p1.setPosition(71);
                        view.setPawnHome(view.redPawn1);
                        return;
                    }
                    else if(p2.getPosition()==17||p2.getPosition()==18||p2.getPosition()==19)
                    {
                        deck.map[p2.getPosition()].setOccupied(false);
                        p2.setPosition(71);
                        view.setPawnHome(view.redPawn2);
                        return;
                    }
                }
                else if(index==25)
                {
                    ultimateMovePawn(p3.getPosition()+3,p);
                    if(p1.getPosition()==26||p1.getPosition()==27)
                    {
                        deck.map[p1.getPosition()].setOccupied(false);
                        p1.setPosition(71);
                        view.setPawnHome(view.redPawn1);
                        return;
                    }
                    else if(p2.getPosition()==26||p2.getPosition()==27)
                    {
                        deck.map[p2.getPosition()].setOccupied(false);
                        p2.setPosition(71);
                        view.setPawnHome(view.redPawn2);
                        return;
                    }
                }
                else if(index==47)
                {
                    ultimateMovePawn(p3.getPosition()+4,p);
                    if(p1.getPosition()==48||p1.getPosition()==49||p1.getPosition()==50)
                    {
                        deck.map[p1.getPosition()].setOccupied(false);
                        p1.setPosition(71);
                        view.setPawnHome(view.redPawn1);
                        return;
                    }
                    else if(p2.getPosition()==48||p2.getPosition()==49||p2.getPosition()==50)
                    {
                        deck.map[p2.getPosition()].setOccupied(false);
                        p2.setPosition(71);
                        view.setPawnHome(view.redPawn2);
                        return;
                    }
                }
                else if(index==56)
                {
                    ultimateMovePawn(p3.getPosition()+3,p);
                    if(p1.getPosition()==57||p1.getPosition()==58)
                    {
                        deck.map[p1.getPosition()].setOccupied(false);
                        p1.setPosition(71);
                        view.setPawnHome(view.redPawn1);
                        return;
                    }
                    else if(p2.getPosition()==57||p2.getPosition()==58)
                    {
                        deck.map[p2.getPosition()].setOccupied(false);
                        p2.setPosition(71);
                        view.setPawnHome(view.redPawn2);
                        return;
                    }
                }
            }
            else if(p==p4)
            {
                deck.map[p4.getPosition()].setOccupied(false);
                p4.setPosition(index);
                deck.map[p4.getPosition()].setOccupied(true);
                view.updatePawn(index,view.yellowPawn2);
                if(index==2)
                {
                    //redStartSquare(3)
                    ultimateMovePawn(p4.getPosition()+4,p);
                    if(p1.getPosition()==3||p1.getPosition()==4||p1.getPosition()==5)
                    {
                        deck.map[p1.getPosition()].setOccupied(false);
                        p1.setPosition(71);
                        view.setPawnHome(view.redPawn1);
                        return;
                    }
                    else if(p2.getPosition()==3||p2.getPosition()==4||p2.getPosition()==5)
                    {
                        deck.map[p2.getPosition()].setOccupied(false);
                        p2.setPosition(71);
                        view.setPawnHome(view.redPawn2);
                        return;
                    }
                }
                else if(index==11)
                {
                    //redStartSquare(2)
                    ultimateMovePawn(p4.getPosition()+3,p);
                    if(p1.getPosition()==12||p1.getPosition()==13)
                    {
                        deck.map[p1.getPosition()].setOccupied(false);
                        p1.setPosition(71);
                        view.setPawnHome(view.redPawn1);
                        return;
                    }
                    else if(p2.getPosition()==12||p2.getPosition()==13)
                    {
                        deck.map[p2.getPosition()].setOccupied(false);
                        p2.setPosition(71);
                        view.setPawnHome(view.redPawn2);
                        return;
                    }
                }
                else if(index==16)
                {
                    //blueStartSquare(3)
                    ultimateMovePawn(p4.getPosition()+4,p);
                    if(p1.getPosition()==17||p1.getPosition()==18||p1.getPosition()==19)
                    {
                        deck.map[p1.getPosition()].setOccupied(false);
                        p1.setPosition(71);
                        view.setPawnHome(view.redPawn1);
                        return;
                    }
                    else if(p2.getPosition()==17||p2.getPosition()==18||p2.getPosition()==19)
                    {
                        deck.map[p2.getPosition()].setOccupied(false);
                        p2.setPosition(71);
                        view.setPawnHome(view.redPawn2);
                        return;
                    }
                }
                else if(index==25)
                {
                    ultimateMovePawn(p4.getPosition()+3,p);
                    if(p1.getPosition()==26||p1.getPosition()==27)
                    {
                        deck.map[p1.getPosition()].setOccupied(false);
                        p1.setPosition(71);
                        view.setPawnHome(view.redPawn1);
                        return;
                    }
                    else if(p2.getPosition()==26||p2.getPosition()==27)
                    {
                        deck.map[p2.getPosition()].setOccupied(false);
                        p2.setPosition(71);
                        view.setPawnHome(view.redPawn2);
                        return;
                    }
                }
                else if(index==47)
                {
                    ultimateMovePawn(p4.getPosition()+4,p);
                    if(p1.getPosition()==48||p1.getPosition()==49||p1.getPosition()==50)
                    {
                        deck.map[p1.getPosition()].setOccupied(false);
                        p1.setPosition(71);
                        view.setPawnHome(view.redPawn1);
                        return;
                    }
                    else if(p2.getPosition()==48||p2.getPosition()==49||p2.getPosition()==50)
                    {
                        deck.map[p2.getPosition()].setOccupied(false);
                        p2.setPosition(71);
                        view.setPawnHome(view.redPawn2);
                        return;
                    }
                }
                else if(index==56)
                {
                    ultimateMovePawn(p4.getPosition()+3,p);
                    if(p1.getPosition()==57||p1.getPosition()==58)
                    {
                        deck.map[p1.getPosition()].setOccupied(false);
                        p1.setPosition(71);
                        view.setPawnHome(view.redPawn1);
                        return;
                    }
                    else if(p2.getPosition()==57||p2.getPosition()==58)
                    {
                        deck.map[p2.getPosition()].setOccupied(false);
                        p2.setPosition(71);
                        view.setPawnHome(view.redPawn2);
                        return;
                    }
                }
            }
            return;
        }

        if(p==p1){
            if (p2.getPosition() == index) {
                return;
            } else if (p3.getPosition() == index) {
                p3.setPosition(72);
                view.setPawnHome(view.yellowPawn1);
                p1.setPosition(index);
                view.updatePawn(index, view.redPawn1);

            } else {
                p4.setPosition(72);
                view.setPawnHome(view.yellowPawn2);
                p1.setPosition(index);
                view.updatePawn(index, view.redPawn1);
            }
            if(index==31)
            {
                //yellowStartSquare(3)
                ultimateMovePawn(p1.getPosition()+4,p);
                //check if there are other pawns in between or at the end
                if(p3.getPosition()==32||p3.getPosition()==34||p3.getPosition()==35)
                {
                    deck.map[p3.getPosition()].setOccupied(false);
                    p3.setPosition(72);
                    view.setPawnHome(view.yellowPawn1);
                    return;
                }
                else if(p4.getPosition()==32||p4.getPosition()==34||p4.getPosition()==35)
                {
                    deck.map[p4.getPosition()].setOccupied(false);
                    p4.setPosition(72);
                    view.setPawnHome(view.yellowPawn2);
                    return;
                }

            }
            else if(index==40)
            {
                ultimateMovePawn(p1.getPosition()+3,p);
                //yellowStartSquare(2)
                if(p3.getPosition()==41||p3.getPosition()==42)
                {
                    deck.map[p3.getPosition()].setOccupied(false);
                    p3.setPosition(72);
                    view.setPawnHome(view.yellowPawn1);
                    return;
                }
                else if(p4.getPosition()==41||p4.getPosition()==42)
                {
                    deck.map[p4.getPosition()].setOccupied(false);
                    p4.setPosition(72);
                    view.setPawnHome(view.yellowPawn2);
                    return;
                }
            }
            else if(index==16)
            {
                ultimateMovePawn(p1.getPosition()+4,p);
                //blueStartSquare(3)
                if(p3.getPosition()==17||p3.getPosition()==18||p3.getPosition()==19)
                {
                    deck.map[p3.getPosition()].setOccupied(false);
                    p3.setPosition(72);
                    view.setPawnHome(view.yellowPawn1);
                    return;
                }
                else if(p4.getPosition()==17||p4.getPosition()==18||p4.getPosition()==19)
                {
                    deck.map[p4.getPosition()].setOccupied(false);
                    p4.setPosition(72);
                    view.setPawnHome(view.yellowPawn2);
                    return;
                }
            }
            else if(index==25)
            {
                ultimateMovePawn(p1.getPosition()+3,p);
                if(p3.getPosition()==26||p3.getPosition()==27)
                {
                    deck.map[p3.getPosition()].setOccupied(false);
                    p3.setPosition(72);
                    view.setPawnHome(view.yellowPawn1);
                    return;
                }
                else if(p4.getPosition()==26||p4.getPosition()==27)
                {
                    deck.map[p4.getPosition()].setOccupied(false);
                    p4.setPosition(72);
                    view.setPawnHome(view.yellowPawn2);
                    return;
                }
            }
            else if(index==47)
            {
                ultimateMovePawn(p1.getPosition()+4,p);
                if(p3.getPosition()==48||p3.getPosition()==49||p3.getPosition()==50)
                {
                    deck.map[p3.getPosition()].setOccupied(false);
                    p3.setPosition(72);
                    view.setPawnHome(view.yellowPawn1);
                    return;
                }
                else if(p4.getPosition()==48||p4.getPosition()==49||p4.getPosition()==50)
                {
                    deck.map[p4.getPosition()].setOccupied(false);
                    p4.setPosition(72);
                    view.setPawnHome(view.yellowPawn2);
                    return;
                }
            }
            else if(index==56)
            {
                ultimateMovePawn(p1.getPosition()+3,p);
                if(p3.getPosition()==57||p3.getPosition()==58)
                {
                    deck.map[p3.getPosition()].setOccupied(false);
                    p3.setPosition(72);
                    view.setPawnHome(view.yellowPawn1);
                    return;
                }
                else if(p4.getPosition()==57||p4.getPosition()==58)
                {
                    deck.map[p4.getPosition()].setOccupied(false);
                    p4.setPosition(72);
                    view.setPawnHome(view.yellowPawn2);
                    return;
                }
            }
        }
        else if(p==p2)
        {
            if (p1.getPosition() == index) {
                return;
            } else if (p3.getPosition() == index) {
                p3.setPosition(72);
                view.setPawnHome(view.yellowPawn1);
                p2.setPosition(index);
                view.updatePawn(index, view.redPawn2);

            } else {
                p4.setPosition(72);
                view.setPawnHome(view.yellowPawn2);
                p2.setPosition(index);
                view.updatePawn(index, view.redPawn2);
            }
            if(index==31)
            {
                //yellowStartSquare(3)
                ultimateMovePawn(p2.getPosition()+4,p);
                //check if there are other pawns in between or at the end
                if(p3.getPosition()==32||p3.getPosition()==34||p3.getPosition()==35)
                {
                    deck.map[p3.getPosition()].setOccupied(false);
                    p3.setPosition(72);
                    view.setPawnHome(view.yellowPawn1);
                    return;
                }
                else if(p4.getPosition()==32||p4.getPosition()==34||p4.getPosition()==35)
                {

                    deck.map[p4.getPosition()].setOccupied(false);
                    p4.setPosition(72);
                    view.setPawnHome(view.yellowPawn2);
                    return;
                }

            }
            else if(index==40)
            {
                //yellowStartSquare(2)
                ultimateMovePawn(p2.getPosition()+3,p);
                if(p3.getPosition()==41||p3.getPosition()==42)
                {
                    deck.map[p3.getPosition()].setOccupied(false);
                    p3.setPosition(72);
                    view.setPawnHome(view.yellowPawn1);
                    return;
                }
                else if(p4.getPosition()==41||p4.getPosition()==42)
                {
                    deck.map[p4.getPosition()].setOccupied(false);
                    p4.setPosition(72);
                    view.setPawnHome(view.yellowPawn2);
                    return;
                }
            }
            else if(index==16)
            {
                //blueStartSquare(3)
                ultimateMovePawn(p2.getPosition()+4,p);
                if(p3.getPosition()==17||p3.getPosition()==18||p3.getPosition()==19)
                {
                    deck.map[p3.getPosition()].setOccupied(false);
                    p3.setPosition(72);
                    view.setPawnHome(view.yellowPawn1);
                    return;
                }
                else if(p4.getPosition()==17||p4.getPosition()==18||p4.getPosition()==19)
                {
                    deck.map[p4.getPosition()].setOccupied(false);
                    p4.setPosition(72);
                    view.setPawnHome(view.yellowPawn2);
                    return;
                }
            }
            else if(index==25)
            {
                ultimateMovePawn(p2.getPosition()+3,p);
                if(p3.getPosition()==26||p3.getPosition()==27)
                {
                    deck.map[p3.getPosition()].setOccupied(false);
                    p3.setPosition(72);
                    view.setPawnHome(view.yellowPawn1);
                    return;
                }
                else if(p4.getPosition()==26||p4.getPosition()==27)
                {
                    deck.map[p4.getPosition()].setOccupied(false);
                    p4.setPosition(72);
                    view.setPawnHome(view.yellowPawn2);
                    return;
                }
            }
            else if(index==47)
            {
                ultimateMovePawn(p2.getPosition()+4,p);
                if(p3.getPosition()==48||p3.getPosition()==49||p3.getPosition()==50)
                {
                    deck.map[p3.getPosition()].setOccupied(false);
                    p3.setPosition(72);
                    view.setPawnHome(view.yellowPawn1);
                    return;
                }
                else if(p4.getPosition()==48||p4.getPosition()==49||p4.getPosition()==50)
                {
                    deck.map[p4.getPosition()].setOccupied(false);
                    p4.setPosition(72);
                    view.setPawnHome(view.yellowPawn2);
                    return;
                }
            }
            else if(index==56)
            {
                ultimateMovePawn(p2.getPosition()+3,p);
                if(p3.getPosition()==57||p3.getPosition()==58)
                {
                    deck.map[p3.getPosition()].setOccupied(false);
                    p3.setPosition(72);
                    view.setPawnHome(view.yellowPawn1);
                    return;
                }
                else if(p4.getPosition()==57||p4.getPosition()==58)
                {
                    deck.map[p4.getPosition()].setOccupied(false);
                    p4.setPosition(72);
                    view.setPawnHome(view.yellowPawn2);
                    return;
                }
            }
        }
        else if(p==p3)
        {
            if(p4.getPosition()==index)
            {
                return;
            }
            else if(p2.getPosition()==index)
            {
                p3.setPosition(index);
                p2.setPosition(71);
                view.setPawnHome(view.redPawn2);
                view.updatePawn(index,view.yellowPawn1);
            }
            else if(p1.getPosition()==index)
            {
                p1.setPosition(71);
                p3.setPosition(index);
                view.setPawnHome(view.redPawn1);
                view.updatePawn(index,view.yellowPawn1);
            }
            if(index==2)
            {
                //redStartSquare(3)
                ultimateMovePawn(p3.getPosition()+4,p);
                if(p1.getPosition()==3||p1.getPosition()==4||p1.getPosition()==5)
                {
                    deck.map[p1.getPosition()].setOccupied(false);
                    p1.setPosition(71);
                    view.setPawnHome(view.redPawn1);
                    return;
                }
                else if(p2.getPosition()==3||p2.getPosition()==4||p2.getPosition()==5)
                {
                    deck.map[p2.getPosition()].setOccupied(false);
                    p2.setPosition(71);
                    view.setPawnHome(view.redPawn2);
                    return;
                }
            }
            else if(index==11)
            {
                //redStartSquare(2)
                ultimateMovePawn(p3.getPosition()+3,p);
                if(p1.getPosition()==12||p1.getPosition()==13)
                {
                    deck.map[p1.getPosition()].setOccupied(false);
                    p1.setPosition(71);
                    view.setPawnHome(view.redPawn1);
                    return;
                }
                else if(p2.getPosition()==12||p2.getPosition()==13)
                {
                    deck.map[p2.getPosition()].setOccupied(false);
                    p2.setPosition(71);
                    view.setPawnHome(view.redPawn2);
                    return;
                }
            }
            else if(index==16)
            {
                //blueStartSquare(3)
                ultimateMovePawn(p3.getPosition()+4,p);
                if(p1.getPosition()==17||p1.getPosition()==18||p1.getPosition()==19)
                {
                    deck.map[p1.getPosition()].setOccupied(false);
                    p1.setPosition(71);
                    view.setPawnHome(view.redPawn1);
                    return;
                }
                else if(p2.getPosition()==17||p2.getPosition()==18||p2.getPosition()==19)
                {
                    deck.map[p2.getPosition()].setOccupied(false);
                    p2.setPosition(71);
                    view.setPawnHome(view.redPawn2);
                    return;
                }
            }
            else if(index==25)
            {
                ultimateMovePawn(p3.getPosition()+3,p);
                if(p1.getPosition()==26||p1.getPosition()==27)
                {
                    deck.map[p1.getPosition()].setOccupied(false);
                    p1.setPosition(71);
                    view.setPawnHome(view.redPawn1);
                    return;
                }
                else if(p2.getPosition()==26||p2.getPosition()==27)
                {
                    deck.map[p2.getPosition()].setOccupied(false);
                    p2.setPosition(71);
                    view.setPawnHome(view.redPawn2);
                    return;
                }
            }
            else if(index==47)
            {
                ultimateMovePawn(p3.getPosition()+4,p);
                if(p1.getPosition()==48||p1.getPosition()==49||p1.getPosition()==50)
                {
                    deck.map[p1.getPosition()].setOccupied(false);
                    p1.setPosition(71);
                    view.setPawnHome(view.redPawn1);
                    return;
                }
                else if(p2.getPosition()==48||p2.getPosition()==49||p2.getPosition()==50)
                {
                    deck.map[p2.getPosition()].setOccupied(false);
                    p2.setPosition(71);
                    view.setPawnHome(view.redPawn2);
                    return;
                }
            }
            else if(index==56)
            {
                ultimateMovePawn(p3.getPosition()+3,p);
                if(p1.getPosition()==57||p1.getPosition()==58)
                {
                    deck.map[p1.getPosition()].setOccupied(false);
                    p1.setPosition(71);
                    view.setPawnHome(view.redPawn1);
                    return;
                }
                else if(p2.getPosition()==57||p2.getPosition()==58)
                {
                    deck.map[p2.getPosition()].setOccupied(false);
                    p2.setPosition(71);
                    view.setPawnHome(view.redPawn2);
                    return;
                }
            }
        }
        else if(p==p4)
        {
            if(p3.getPosition()==index)
            {
                return;
            }
            else if(p2.getPosition()==index)
            {
                p4.setPosition(index);
                p2.setPosition(71);
                view.setPawnHome(view.redPawn2);
                view.updatePawn(index,view.yellowPawn2);
            }
            else if(p1.getPosition()==index)
            {
                p1.setPosition(71);
                p4.setPosition(index);
                view.setPawnHome(view.redPawn1);
                view.updatePawn(index,view.yellowPawn2);
            }
            if(index==2)
            {
                //redStartSquare(3)
                ultimateMovePawn(p4.getPosition()+4,p);
                if(p1.getPosition()==3||p1.getPosition()==4||p1.getPosition()==5)
                {
                    deck.map[p1.getPosition()].setOccupied(false);
                    p1.setPosition(71);
                    view.setPawnHome(view.redPawn1);
                    return;
                }
                else if(p2.getPosition()==3||p2.getPosition()==4||p2.getPosition()==5)
                {
                    deck.map[p2.getPosition()].setOccupied(false);
                    p2.setPosition(71);
                    view.setPawnHome(view.redPawn2);
                    return;
                }
            }
            else if(index==11)
            {
                //redStartSquare(2)
                ultimateMovePawn(p4.getPosition()+3,p);
                if(p1.getPosition()==12||p1.getPosition()==13)
                {
                    deck.map[p1.getPosition()].setOccupied(false);
                    p1.setPosition(71);
                    view.setPawnHome(view.redPawn1);
                    return;
                }
                else if(p2.getPosition()==12||p2.getPosition()==13)
                {
                    deck.map[p2.getPosition()].setOccupied(false);
                    p2.setPosition(71);
                    view.setPawnHome(view.redPawn2);
                    return;
                }
            }
            else if(index==16)
            {
                //blueStartSquare(3)
                ultimateMovePawn(p4.getPosition()+4,p);
                if(p1.getPosition()==17||p1.getPosition()==18||p1.getPosition()==19)
                {
                    deck.map[p1.getPosition()].setOccupied(false);
                    p1.setPosition(71);
                    view.setPawnHome(view.redPawn1);
                    return;
                }
                else if(p2.getPosition()==17||p2.getPosition()==18||p2.getPosition()==19)
                {
                    deck.map[p2.getPosition()].setOccupied(false);
                    p2.setPosition(71);
                    view.setPawnHome(view.redPawn2);
                    return;
                }
            }
            else if(index==25)
            {
                ultimateMovePawn(p4.getPosition()+3,p);
                if(p1.getPosition()==26||p1.getPosition()==27)
                {
                    deck.map[p1.getPosition()].setOccupied(false);
                    p1.setPosition(71);
                    view.setPawnHome(view.redPawn1);
                    return;
                }
                else if(p2.getPosition()==26||p2.getPosition()==27)
                {
                    deck.map[p2.getPosition()].setOccupied(false);
                    p2.setPosition(71);
                    view.setPawnHome(view.redPawn2);
                    return;
                }
            }
            else if(index==47)
            {
                ultimateMovePawn(p4.getPosition()+4,p);
                if(p1.getPosition()==48||p1.getPosition()==49||p1.getPosition()==50)
                {
                    deck.map[p1.getPosition()].setOccupied(false);
                    p1.setPosition(71);
                    view.setPawnHome(view.redPawn1);
                    return;
                }
                else if(p2.getPosition()==48||p2.getPosition()==49||p2.getPosition()==50)
                {
                    deck.map[p2.getPosition()].setOccupied(false);
                    p2.setPosition(71);
                    view.setPawnHome(view.redPawn2);
                    return;
                }
            }
            else if(index==56)
            {
                ultimateMovePawn(p4.getPosition()+3,p);
                if(p1.getPosition()==57||p1.getPosition()==58)
                {
                    deck.map[p1.getPosition()].setOccupied(false);
                    p1.setPosition(71);
                    view.setPawnHome(view.redPawn1);
                    return;
                }
                else if(p2.getPosition()==57||p2.getPosition()==58)
                {
                    deck.map[p2.getPosition()].setOccupied(false);
                    p2.setPosition(71);
                    view.setPawnHome(view.redPawn2);
                    return;
                }
            }
        }
    }
    /**
     * <b>Transformer(mutative):<b/>
     * Change the player's turn
     * <b>Post Condition:</b>Change player's turn
     */

    public void changeTurn(){
        if(deck.getTurn()==1) {
            deck.setTurn(2);
            view.setTurnText(player2.getName());
            setNextCard();
        }
        else{
            deck.setTurn(1);
            view.setTurnText(player1.getName());
            setNextCard();
        }
    }
    /**
     * <b>Observer:<b/>
     * Checks if game can be over.
     * @return true if there is a winner (or game saved later)
     * or false otherwise
     */
    public boolean gameOver(){
        if(p1.isOver()&&p2.isOver())
        {
            PopUpMessage.infoBox("CONGRATS!! THE WINNER IS "+player1.getName(),"GAME OVER");
            System.exit(0);
        }
        else if(p3.isOver()&&p4.isOver()) {
            PopUpMessage.infoBox("CONGRATS!! THE WINNER IS " + player2.getName(), "GAME OVER");
            System.exit(0);
        }
        return false;
    }
    /**
     * <b>Transformer(mutative):<b/>
     */
    public void mouseClicked(MouseEvent e)
    {

    }
    /**
     * <b>Transformer(mutative):<b/>
     */
    public void mousePressed(MouseEvent e)
    {

    }
    /**
     * <b>Transformer:<b/>
     * Gets the next card from the deck , sets it on the view and checks if game Cards are over
     */
    public void setNextCard()
    {
        if(deck.getNumberOfCards()==0)
        {
            deck.initCards();
            deck.randomizeCards();
            currentCard=deck.get(0);
            if(currentCard instanceof SimpleNumberCard)
                view.btnCurrentCard.setIcon(new ImageIcon("card"+currentCard.getMoveValue()+".png"));
            else
                view.btnCurrentCard.setIcon(new ImageIcon(currentCard.getClassName()+".png"));
            view.setInfoText(currentCard.getDescription());
            return;
        }
        currentCard=deck.getNextCard();
        if(currentCard instanceof SimpleNumberCard)
            view.btnCurrentCard.setIcon(new ImageIcon("card"+currentCard.getMoveValue()+".png"));
        else
            view.btnCurrentCard.setIcon(new ImageIcon(currentCard.getClassName()+".png"));

        view.setInfoText(currentCard.getDescription());
        view.setCards_left(" " + deck.getNumberOfCards());
    }
    @Override
    public void actionPerformed(ActionEvent actionEvent) {
       Object object= actionEvent.getSource();
    }
    private class PawnListener implements MouseListener {

        @Override
        /**
         * <b>Transformer(mutative):<b/>
         * The "brain" of the program , take action if the mouse clicked an active button
         */
        public void mouseClicked(MouseEvent e) {
            Object but = ( e.getSource());


                if (but == view.redPawn1) {
                    //returns if a pawn has finished
                    if(p1.isOver())
                        return;
                    if(deck.getTurn()==1) {
                        int moveValue = currentCard.getMoveValue();
                        if(moveValue<0)
                            p1.setCanBeSafe(false);
                        else
                            p1.setCanBeSafe(true);
                        //If redPawn1 is inside Home
                        if (p1.getPosition() == 71) {
                            if (currentCard instanceof NumberOneCard) {
                                ultimateMovePawn(4, p1);
                                changeTurn();
                                return;

                            } else if (currentCard instanceof NumberTwoCard)
                            {
                                ultimateMovePawn(4, p1);
                                setNextCard();
                                return;
                            }
                            else if (currentCard instanceof SorryCard) {
                                if (p1.getPosition() == 71) {
                                    String input = JOptionPane.showInputDialog("Which pawn do you want to swap with?(write just the number)");
                                    int res = Integer.parseInt(input);
                                    if (res == 1 && p3.getPosition() <61) {
                                        p1.setPosition(p3.getPosition());
                                        p3.setPosition(72);
                                        view.setPawnHome(view.yellowPawn1);
                                        view.updatePawn(p1.getPosition(), view.redPawn1);
                                        if(p1.getPosition()==31) {
                                            ultimateMovePawn(35, p1);
                                        }
                                        else if(p1.getPosition()==40)
                                        {
                                            ultimateMovePawn(43,p1);
                                        }
                                        changeTurn();
                                    } else if (res == 2 && p4.getPosition() <61) {
                                        p1.setPosition(p4.getPosition());
                                        p4.setPosition(72);
                                        view.setPawnHome(view.yellowPawn2);
                                        view.updatePawn(p1.getPosition(), view.redPawn1);
                                        if(p1.getPosition()==31) {
                                            ultimateMovePawn(35, p1);
                                        }
                                        else if(p1.getPosition()==40)
                                        {
                                            ultimateMovePawn(43,p1);
                                        }
                                        changeTurn();
                                    } else {
                                        view.setInfoText("Invalid Number");
                                    }

                                }
                            } else
                                view.setInfoText("You cannot escape");
                            if(!p1.isCanBeSafe())
                                p1.setCanBeSafe(true);
                            return;
                        } else if (moveValue == -10 || moveValue == -20 || moveValue == 0 || moveValue == -99) {

                            switch (moveValue) {
                                case 0: //swap-- Show an extra message dialog with 2 options for player to choose opponents pawns
                                    final String options[] = {"Yellowpawn1", "Yellowpawn2"};
                                    JFrame frame = new JFrame("Input Dialog Swap Move");
                                    String nextMove = (String) JOptionPane.showInputDialog(frame,
                                            "Select opponent's pawn for swapping",
                                            "Select a pawn",
                                            JOptionPane.QUESTION_MESSAGE,
                                            null,
                                            options,
                                            options[0]);
                                    if (nextMove.equals("Yellowpawn1") && p3.getPosition() <61&&p1.getPosition()<61) {
                                        Rectangle bounds1 = view.yellowPawn1.getBounds();
                                        Rectangle bounds2 = view.redPawn1.getBounds();
                                        view.redPawn1.setBounds(bounds1);
                                        view.yellowPawn1.setBounds(bounds2);
                                        int position = p1.getPosition();
                                        p1.setPosition(p3.getPosition());
                                        p3.setPosition(position);
                                        if(p1.getPosition()==31)
                                            ultimateMovePawn(35,p1);
                                        else if(p1.getPosition()==40)
                                            ultimateMovePawn(43,p1);

                                        if(p3.getPosition()==2) {
                                            ultimateMovePawn(6, p3);
                                        }
                                        else if(p3.getPosition()==11)
                                        {
                                            ultimateMovePawn(14,p3);
                                        }
                                        changeTurn();

                                    } else if (nextMove.equals("Yellowpawn2") && p4.getPosition() <61&&p1.getPosition()<61) {
                                        Rectangle bounds1 = view.yellowPawn2.getBounds();
                                        Rectangle bounds2 = view.redPawn1.getBounds();
                                        int position = p1.getPosition();
                                        p1.setPosition(p4.getPosition());
                                        p4.setPosition(position);
                                        view.redPawn1.setBounds(bounds1);
                                        view.yellowPawn2.setBounds(bounds2);
                                        if(p1.getPosition()==31)
                                            ultimateMovePawn(35,p1);
                                        else if(p1.getPosition()==40)
                                            ultimateMovePawn(43,p1);

                                        if(p4.getPosition()==2) {
                                            ultimateMovePawn(6, p4);
                                        }
                                        else if(p4.getPosition()==11)
                                        {
                                            ultimateMovePawn(14,p4);
                                        }
                                        changeTurn();
                                    }
                                    break;
                                case -10:
                                    if(p2.getPosition()!=71)
                                    {
                                        return;
                                    }
                                    ultimateMovePawn(4, p2);
                                    if(currentCard instanceof NumberTwoCard)
                                    {
                                        setNextCard();
                                        return;
                                    }
                                    changeTurn();
                                    break;
                                case -20:
                                    String m = JOptionPane.showInputDialog("How many squares do you wish to move the redPawn 1?");
                                    int result = Integer.parseInt(m);
                                    if (result > 7 || result < 0 || p2.getPosition() == 71) {
                                        {
                                            PopUpMessage.infoBox("Wrong input , will just move redPawn1 7 spaces forward", "Sorry");
                                            ultimateMovePawn(p1.getPosition() + 7, p1);
                                            changeTurn();
                                            return;
                                        }
                                    } else {
                                        ultimateMovePawn(result + p1.getPosition(), p1);
                                        ultimateMovePawn(p2.getPosition() + (7 - result), p2);
                                        changeTurn();
                                    }
                                    break;
                                case -99:
                                    //Sorry Card
                                    view.setInfoText("The pawn must be in the Home Square");
                                    break;
                            }
                        } else {
                            ultimateMovePawn(p1.getPosition() + moveValue, p1);
                            if(moveValue==2)
                            {
                                setNextCard();
                                return;
                            }
                           changeTurn();
                        }

                    }
                } else if (but == view.redPawn2) {
                    //returns if a pawn has finished
                    if(p2.isOver())
                        return;
                    if(deck.getTurn()==1) {
                        int moveValue = currentCard.getMoveValue();
                        if(moveValue<0)
                            p2.setCanBeSafe(false);
                        else
                            p2.setCanBeSafe(true);
                        if (p2.getPosition() == 71) {
                            if (currentCard instanceof NumberOneCard || currentCard instanceof NumberTwoCard) {
                                ultimateMovePawn(4, p2);
                                if(currentCard instanceof NumberTwoCard)
                                {
                                    setNextCard();
                                    return;
                                }
                                changeTurn();
                            } else if (currentCard instanceof SorryCard) {
                                String input = JOptionPane.showInputDialog("Which pawn do you want to swap with?(write just the number)");
                                int res = Integer.parseInt(input);
                                if (res == 1 && p3.getPosition() < 61) {
                                    p2.setPosition(p3.getPosition());
                                    p3.setPosition(72);
                                    view.setPawnHome(view.yellowPawn1);
                                    view.updatePawn(p2.getPosition(), view.redPawn2);
                                    if(p2.getPosition()==31) {
                                        ultimateMovePawn(35, p2);
                                    }
                                    else if(p2.getPosition()==40)
                                    {
                                        ultimateMovePawn(43,p2);
                                    }
                                    changeTurn();
                                } else if (res == 2 && p4.getPosition() <61) {
                                    p2.setPosition(p4.getPosition());
                                    p4.setPosition(72);
                                    view.setPawnHome(view.yellowPawn2);
                                    view.updatePawn(p2.getPosition(), view.redPawn2);
                                    if(p2.getPosition()==31) {
                                        ultimateMovePawn(35, p2);
                                    }
                                    else if(p2.getPosition()==40)
                                    {
                                        ultimateMovePawn(43,p2);
                                    }
                                    changeTurn();
                                } else {
                                    view.setInfoText("Invalid Number");
                                }

                            } else
                                view.setInfoText("You cannot escape");

                            if(!p2.isCanBeSafe())
                                p2.setCanBeSafe(true);
                            return;
                        } else if (moveValue == -10 || moveValue == -20 || moveValue == 0 || moveValue == -99) {

                            switch (moveValue) {
                                case 0: //swap-- Show an extra message dialog with 2 options for player to choose opponents pawns
                                    final String options[] = {"Yellowpawn1", "Yellowpawn2"};
                                    JFrame frame = new JFrame("Input Dialog Swap Move");
                                    String nextMove = (String) JOptionPane.showInputDialog(frame,
                                            "Select opponent's pawn for swapping",
                                            "Select a pawn",
                                            JOptionPane.QUESTION_MESSAGE,
                                            null,
                                            options,
                                            options[0]);
                                    if (nextMove.equals("Yellowpawn1")&& p3.getPosition() <61&&p2.getPosition()<61) {
                                        Rectangle bounds1 = view.yellowPawn1.getBounds();
                                        Rectangle bounds2 = view.redPawn2.getBounds();
                                        view.redPawn2.setBounds(bounds1);
                                        view.yellowPawn1.setBounds(bounds2);
                                        int position = p2.getPosition();
                                        p2.setPosition(p3.getPosition());
                                        p3.setPosition(position);
                                        if(p2.getPosition()==31)
                                            ultimateMovePawn(35,p2);
                                        else if(p2.getPosition()==40)
                                            ultimateMovePawn(43,p2);

                                        if(p3.getPosition()==2) {
                                            ultimateMovePawn(6, p3);
                                        }
                                        else if(p3.getPosition()==11)
                                        {
                                            ultimateMovePawn(14,p3);
                                        }
                                        changeTurn();

                                    } else if (nextMove.equals("Yellowpawn2") && p4.getPosition() <61&&p2.getPosition()<61) {
                                        Rectangle bounds1 = view.yellowPawn2.getBounds();
                                        Rectangle bounds2 = view.redPawn2.getBounds();
                                        int position = p2.getPosition();
                                        p2.setPosition(p4.getPosition());
                                        p4.setPosition(position);
                                        view.redPawn2.setBounds(bounds1);
                                        view.yellowPawn2.setBounds(bounds2);
                                        if(p2.getPosition()==31)
                                            ultimateMovePawn(35,p2);
                                        else if(p2.getPosition()==40)
                                            ultimateMovePawn(43,p2);

                                        if(p4.getPosition()==2) {
                                            ultimateMovePawn(6, p4);
                                        }
                                        else if(p4.getPosition()==11)
                                        {
                                            ultimateMovePawn(14,p4);
                                        }
                                        changeTurn();
                                    }
                                    break;
                                case -10:
                                    if(p1.getPosition()!=71)
                                        return;
                                    ultimateMovePawn(4, p1);
                                    if(currentCard instanceof NumberTwoCard)
                                    {
                                        setNextCard();
                                        return;
                                    }
                                    changeTurn();
                                    break;
                                case -20:
                                    String m = JOptionPane.showInputDialog("How many squares do you wish to move the redPawn 2?");
                                    int result = Integer.parseInt(m);
                                    if (result > 7 || result < 0 || p1.getPosition() == 71) {
                                        PopUpMessage.infoBox("Wrong input , will just move redPawn1 7 spaces forward", "Sorry");
                                        ultimateMovePawn(p2.getPosition() + 7, p2);
                                        changeTurn();
                                        return;
                                    } else {
                                        ultimateMovePawn(result + p2.getPosition(), p2);
                                        ultimateMovePawn(p1.getPosition() + (7 - result), p1);
                                        changeTurn();
                                    }

                                    break;
                                case -99:
                                    //Sorry Card
                                    view.setInfoText("The pawn must be in the Home Square");

                                    break;
                            }
                        } else {
                            ultimateMovePawn(p2.getPosition() + moveValue, p2);
                            if(moveValue==2)
                            {
                                setNextCard();
                                return;
                            }
                            changeTurn();
                        }
                    }
                }



                if (but == view.yellowPawn1) {
                    //returns if a pawn has finished
                    if(p3.isOver())
                        return;
                    if(deck.getTurn()==2) {
                        int moveValue = currentCard.getMoveValue();
                        if(moveValue<0)
                            p3.setCanBeSafe(false);
                        else
                            p3.setCanBeSafe(true);
                        if (p3.getPosition() == 72) {
                            if (currentCard instanceof NumberOneCard || currentCard instanceof NumberTwoCard) {
                                ultimateMovePawn(34, p3);
                                if(currentCard instanceof NumberTwoCard)
                                {
                                    setNextCard();
                                    return;
                                }
                                changeTurn();
                            } else if (currentCard instanceof SorryCard) {
                                String input = JOptionPane.showInputDialog("Which pawn do you want to swap with?(write just the number)");
                                int res = Integer.parseInt(input);
                                if (res == 1 && p1.getPosition() <61) {
                                    p3.setPosition(p1.getPosition());
                                    p1.setPosition(71);
                                    view.setPawnHome(view.redPawn1);
                                    view.updatePawn(p3.getPosition(), view.yellowPawn1);
                                    if(p3.getPosition()==2) {
                                        ultimateMovePawn(6, p3);
                                    }
                                    else if(p3.getPosition()==11)
                                    {
                                        ultimateMovePawn(14,p3);
                                    }
                                    changeTurn();
                                } else if (res == 2 && p2.getPosition() <61) {
                                    p3.setPosition(p2.getPosition());
                                    p2.setPosition(71);
                                    view.setPawnHome(view.redPawn2);
                                    view.updatePawn(p3.getPosition(), view.yellowPawn1);
                                    if(p3.getPosition()==2) {
                                        ultimateMovePawn(6, p3);
                                    }
                                    else if(p3.getPosition()==11)
                                    {
                                        ultimateMovePawn(14,p3);
                                    }
                                    changeTurn();
                                } else {
                                    view.setInfoText("Invalid Number");
                                }

                            } else
                                view.setInfoText("You cannot escape");
                            if(!p3.isCanBeSafe())
                                p3.setCanBeSafe(true);
                            return;
                        } else if (moveValue == -10 || moveValue == -20 || moveValue == 0 || moveValue == -99) {

                            switch (moveValue) {
                                case 0: //swap-- Show an extra message dialog with 2 options for player to choose opponents pawns
                                    final String options[] = {"RedPawn1", "RedPawn2"};
                                    JFrame frame = new JFrame("Input Dialog Swap Move");
                                    String nextMove = (String) JOptionPane.showInputDialog(frame,
                                            "Select opponent's pawn for swapping",
                                            "Select a pawn",
                                            JOptionPane.QUESTION_MESSAGE,
                                            null,
                                            options,
                                            options[0]);
                                    if (nextMove.equals("RedPawn1") && p3.getPosition() <61&&p1.getPosition()<61) {
                                        Rectangle bounds1 = view.yellowPawn1.getBounds();
                                        Rectangle bounds2 = view.redPawn1.getBounds();
                                        view.redPawn1.setBounds(bounds1);
                                        view.yellowPawn1.setBounds(bounds2);
                                        int position = p1.getPosition();
                                        p1.setPosition(p3.getPosition());
                                        p3.setPosition(position);
                                        if(p1.getPosition()==31)
                                            ultimateMovePawn(35,p1);
                                        else if(p1.getPosition()==40)
                                            ultimateMovePawn(43,p1);

                                        if(p3.getPosition()==2) {
                                            ultimateMovePawn(6, p3);
                                        }
                                        else if(p3.getPosition()==11)
                                        {
                                            ultimateMovePawn(14,p3);
                                        }

                                        changeTurn();

                                    } else if (nextMove.equals("RedPawn2") && p3.getPosition() <61&&p2.getPosition()<61) {
                                        Rectangle bounds1 = view.redPawn2.getBounds();
                                        Rectangle bounds2 = view.yellowPawn1.getBounds();
                                        int position = p2.getPosition();
                                        p2.setPosition(p3.getPosition());
                                        p3.setPosition(position);
                                        view.yellowPawn1.setBounds(bounds1);
                                        view.redPawn2.setBounds(bounds2);
                                        if(p2.getPosition()==31)
                                            ultimateMovePawn(35,p2);
                                        else if(p2.getPosition()==40)
                                            ultimateMovePawn(43,p2);

                                        if(p3.getPosition()==2) {
                                            ultimateMovePawn(6, p3);
                                        }
                                        else if(p3.getPosition()==11)
                                        {
                                            ultimateMovePawn(14,p3);
                                        }
                                        changeTurn();
                                    }
                                    break;
                                case -10:
                                    if(p4.getPosition()!=72)
                                        return;
                                    ultimateMovePawn(34, p4);
                                    if(currentCard instanceof NumberTwoCard)
                                    {
                                        setNextCard();
                                        return;
                                    }
                                    changeTurn();
                                    break;
                                case -20:
                                    String m = JOptionPane.showInputDialog("How many squares do you wish to move the yellow Pawn 1?");
                                    int result = Integer.parseInt(m);
                                    if (result > 7 || result < 0 || p4.getPosition() == 72) {
                                        {
                                            PopUpMessage.infoBox("Wrong input , will just move yellow Pawn 1 7 spaces forward", "Sorry");
                                            ultimateMovePawn(p3.getPosition() + 7, p3);
                                            view.setTurnText(player1.getName());
                                            deck.setTurn(1);
                                            return;
                                        }
                                    } else {
                                        ultimateMovePawn(result + p3.getPosition(), p3);
                                        ultimateMovePawn(p4.getPosition() + (7 - result), p4);
                                        changeTurn();
                                    }
                                    break;
                                case -99:
                                    //Sorry Card
                                    view.setInfoText("The pawn must be in the Home Square");
                                    break;
                            }
                        } else {
                            ultimateMovePawn(p3.getPosition() + moveValue, p3);
                            if(moveValue==2)
                            {
                                setNextCard();
                                return;
                            }
                            changeTurn();
                        }
                    }
                } else if (but == view.yellowPawn2) {
                    //returns if a pawn has finished
                    if(p4.isOver())
                        return;
                    if(deck.getTurn()==2) {
                        int moveValue = currentCard.getMoveValue();
                        if(moveValue<0)
                            p4.setCanBeSafe(false);
                        else
                            p4.setCanBeSafe(true);
                        if (p4.getPosition() == 72) {
                            if (currentCard instanceof NumberOneCard || currentCard instanceof NumberTwoCard) {
                                ultimateMovePawn(34, p4);
                                if(currentCard instanceof NumberTwoCard)
                                {
                                    setNextCard();
                                    return;
                                }
                                changeTurn();
                            } else if (currentCard instanceof SorryCard) {
                                String input = JOptionPane.showInputDialog("Which pawn do you want to swap with?(write just the number)");
                                int res = Integer.parseInt(input);
                                if (res == 1 && p1.getPosition() < 61) {
                                    p4.setPosition(p1.getPosition());
                                    p1.setPosition(71);
                                    view.setPawnHome(view.redPawn1);
                                    view.updatePawn(p4.getPosition(), view.yellowPawn2);
                                    if(p1.getPosition()==31)
                                        ultimateMovePawn(35,p1);
                                    else if(p1.getPosition()==40)
                                        ultimateMovePawn(43,p1);

                                    if(p4.getPosition()==2) {
                                        ultimateMovePawn(6, p4);
                                    }
                                    else if(p4.getPosition()==11)
                                    {
                                        ultimateMovePawn(14,p4);
                                    }
                                    changeTurn();
                                } else if (res == 2 && p2.getPosition()< 61) {
                                    p4.setPosition(p2.getPosition());
                                    p2.setPosition(71);
                                    view.setPawnHome(view.redPawn2);
                                    view.updatePawn(p4.getPosition(), view.yellowPawn2);
                                    if(p2.getPosition()==31)
                                        ultimateMovePawn(35,p2);
                                    else if(p2.getPosition()==40)
                                        ultimateMovePawn(43,p2);
                                    if(p4.getPosition()==2) {
                                        ultimateMovePawn(6, p4);
                                    }
                                    else if(p4.getPosition()==11)
                                    {
                                        ultimateMovePawn(14,p4);
                                    }

                                    changeTurn();
                                } else {
                                    view.setInfoText("Invalid Number");
                                }

                            } else
                                view.setInfoText("You cannot escape");

                            if(!p4.isCanBeSafe())
                                p4.setCanBeSafe(true);

                            return;
                        } else if (moveValue == -10 || moveValue == -20 || moveValue == 0 || moveValue == -99) {

                            switch (moveValue) {
                                case 0: //swap-- Show an extra message dialog with 2 options for player to choose opponents pawns
                                    final String options[] = {"RedPawn1", "RedPawn2"};
                                    JFrame frame = new JFrame("Input Dialog Swap Move");
                                    String nextMove = (String) JOptionPane.showInputDialog(frame,
                                            "Select opponent's pawn for swapping",
                                            "Select a pawn",
                                            JOptionPane.QUESTION_MESSAGE,
                                            null,
                                            options,
                                            options[0]);
                                    if (nextMove.equals("RedPawn1") && p4.getPosition() <61&&p1.getPosition()<61) {
                                        Rectangle bounds1 = view.yellowPawn2.getBounds();
                                        Rectangle bounds2 = view.redPawn1.getBounds();
                                        view.redPawn1.setBounds(bounds1);
                                        view.yellowPawn2.setBounds(bounds2);
                                        int position = p1.getPosition();
                                        p1.setPosition(p4.getPosition());
                                        if(position==2) {
                                            ultimateMovePawn(6, p4);
                                            p4.setPosition(6);
                                        }
                                        else if(position==11)
                                        {
                                            ultimateMovePawn(14,p4);
                                            p4.setPosition(14);
                                        }
                                        else
                                            p4.setPosition(position);
                                        changeTurn();

                                    } else if (nextMove.equals("RedPawn2") &&p4.getPosition() <61&&p2.getPosition()<61) {
                                        Rectangle bounds1 = view.redPawn2.getBounds();
                                        Rectangle bounds2 = view.yellowPawn2.getBounds();
                                        int position = p2.getPosition();
                                        p2.setPosition(p4.getPosition());
                                        view.yellowPawn2.setBounds(bounds1);
                                        view.redPawn2.setBounds(bounds2);
                                        if(position==2) {
                                            ultimateMovePawn(6, p4);
                                        }
                                        else if(position==11)
                                        {
                                            ultimateMovePawn(14,p4);
                                        }
                                        else
                                        p4.setPosition(position);

                                       changeTurn();
                                    }
                                    break;
                                case -10:
                                    if(p3.getPosition()!=72)
                                        return;
                                    ultimateMovePawn(34, p3);
                                    if(currentCard instanceof NumberTwoCard)
                                    {
                                        setNextCard();
                                        return;
                                    }
                                    changeTurn();
                                    break;
                                case -20:
                                    String m = JOptionPane.showInputDialog("How many squares do you wish to move the yellow Pawn 2?");
                                    int result = Integer.parseInt(m);
                                    if (result > 7 || result < 0 || p3.getPosition() == 72) {
                                        {
                                            PopUpMessage.infoBox("Wrong input , will just move yellow Pawn 1 7 spaces forward", "Sorry");
                                            ultimateMovePawn(p4.getPosition() + 7, p4);
                                            changeTurn();
                                            return;
                                        }
                                    } else {
                                        ultimateMovePawn(result + p4.getPosition(), p4);
                                        ultimateMovePawn(p3.getPosition() + (7 - result), p3);
                                        changeTurn();
                                    }
                                    break;
                                case -99:
                                    //Sorry Card
                                    view.setInfoText("The pawn must be in the Home Square");
                                    break;
                            }
                        } else {
                            ultimateMovePawn(p4.getPosition() + moveValue, p4);
                            if(moveValue==2)
                            {
                                setNextCard();
                                return;
                            }
                            changeTurn();
                        }
                    }
                }

             if(but==view.foldButton)
            {
                //numberElevenCard
                if(currentCard instanceof NumberElevenCard)
                {
                    changeTurn();
                    return;
                }

                //checks if a pawn has finished
                if(p1.isOver()&&p2.getPosition()>61&&deck.getTurn()==1)
                {
                    if((currentCard instanceof NumberOneCard)||(currentCard instanceof NumberTwoCard))
                        return;
                    changeTurn();
                    return;
                }
                if(p2.isOver()&&p1.getPosition()>61&&deck.getTurn()==1)
                {
                    if((currentCard instanceof NumberOneCard)||(currentCard instanceof NumberTwoCard))
                        return;
                    changeTurn();
                    return;
                }
                if(p3.isOver()&&p4.getPosition()>61&&deck.getTurn()==2)
                {
                    if((currentCard instanceof NumberOneCard)||(currentCard instanceof NumberTwoCard))
                        return;
                    changeTurn();
                    return;
                }
                if(p4.isOver()&&p3.getPosition()>61&&deck.getTurn()==2)
                {
                    if((currentCard instanceof NumberOneCard)||(currentCard instanceof NumberTwoCard))
                        return;
                    changeTurn();
                    return;
                }
                //Sorry Card folds
                if(currentCard instanceof SorryCard) {
                    if (p1.getPosition() != 71 && p2.getPosition() != 71 && deck.getTurn() == 1)
                        changeTurn();


                    else if (deck.getTurn() == 1 && p3.getPosition() > 61 && p4.getPosition() > 61)
                        changeTurn();


                    else if (deck.getTurn() == 2 && p3.getPosition() !=72 && p4.getPosition() !=72)
                        changeTurn();

                    else if (deck.getTurn() == 2 && p1.getPosition() > 61 && p2.getPosition() > 61)
                        changeTurn();



                }
                //If 2 pawns are at Home
                    else if(deck.getTurn()==1&&p1.getPosition()==71&&p2.getPosition()==71 ) {
                        if((currentCard instanceof NumberOneCard)||(currentCard instanceof NumberTwoCard))
                            return;
                    changeTurn();
                    }
                    else if(deck.getTurn()==2&&p3.getPosition()==72&&p4.getPosition()==72) {
                        if((currentCard instanceof NumberOneCard)||(currentCard instanceof NumberTwoCard))
                            return;
                        changeTurn();
                    }


            }
             //button for the Card (not being used)
            else if(but==view.btnCurrentCard)
            {

            }


        }

        @Override
        public void mousePressed(MouseEvent e) {
        }

        @Override
        public void mouseReleased(MouseEvent e) {
        }

        @Override
        public void mouseEntered(MouseEvent e) {
        }

        @Override
        public void mouseExited(MouseEvent e) {
        }
    }
}
