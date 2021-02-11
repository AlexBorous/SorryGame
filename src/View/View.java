package View;

import Model.Card.Card;

import javax.swing.*;
import javax.swing.border.Border;
import javax.swing.border.LineBorder;
import java.awt.*;

public class View  {

    private JFrame frame;
    private Card currentCard;
    private int turn;
    JLabel turnLabel = new JLabel("");
    JLabel infoText = new JLabel("Info 1");
    public  JButton foldButton = new JButton("Fold Button");
    public JButton redPawn1 = new JButton("");
    public JButton yellowPawn1 = new JButton("");
    public  JButton yellowPawn2 = new JButton("");
    public JButton redPawn2 = new JButton("");
    public Button loadButton = new Button("Load");
    public Button newGame = new Button("New Game");
    JMenuBar menuBar = new JMenuBar();
    public Button quit = new Button("Quit");
    public Button saveGame = new Button("Save Game");
    JButton btnReceiveCard = new JButton("");
    public JButton btnCurrentCard = new JButton("");
    JLabel cards_left = new JLabel("44");
    JButton[] button =new JButton[100];
   public int getPawnHorizontalPos(JButton pawn){
        return pawn.getHorizontalTextPosition();
    }
    public int getPawnVerticalPos(JButton pawn){
        return pawn.getVerticalTextPosition();
    }
    public void setPawn(int horizontal , int vertical, JButton p){
        p.setHorizontalTextPosition(horizontal);
        p.setVerticalTextPosition(vertical);
    }




    /**
     * Create the application.
     */
    public View() {
        initialize();
        frame.setVisible(true);
    }

    /**
     * Initialize the contents of the frame.
     */
    private void initialize() {
        frame = new JFrame();
        frame.setBounds(200, 200, 1200, 1000);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setTitle("Sorry Game");
        Border thickBorder = new LineBorder(Color.BLACK, 2);
        Border redBorder = new LineBorder(Color.RED, 5);
        Border yellowBorder = new LineBorder(Color.YELLOW, 5);

        JDesktopPane layeredPane = new JDesktopPane();
        frame.getContentPane().add(layeredPane, BorderLayout.CENTER);
        layeredPane.setBackground(Color.CYAN);
        layeredPane.setLayout(null);


        for(int i=0;i<=15;i++)
        {
            button[i]=new JButton("");
            button[i].setForeground(Color.WHITE);
            button[i].setBackground(Color.WHITE);
            button[i].setBounds(50*i, 0, 50, 50);
            button[i].setBorder(thickBorder);
        if(i==2||i==11)
            button[i].setIcon(new ImageIcon("redSlideStart.png"));

        else if(i==3||i==4||i==5||i==12||i==13)
            button[i].setIcon(new ImageIcon("redSlideMedium.png"));
        else if(i==6||i==14)
            button[i].setIcon(new ImageIcon("redSlideEnd.png"));
        layeredPane.add(button[i]);

        }
        int counter=0;
        for(int i=15;i<=29;i++)
        {
            button[i]=new JButton("");
            button[i].setForeground(Color.WHITE);
            button[i].setBackground(Color.WHITE);
            button[i].setBounds(750, 50*counter, 50, 50);

            counter++;
            button[i].setBorder(thickBorder);
            if(i==16||i==25)
                button[i].setIcon(new ImageIcon("blueSlideStart.png"));
            else if(i==17||i==18||i==19||i==26||i==27)
                button[i].setIcon(new ImageIcon("blueSlideMedium.png"));
            else if(i==20||i==28)
                button[i].setIcon(new ImageIcon("blueSlideEnd.png"));
            layeredPane.add( button[i]);

        }
        counter=0;
        for(int i=30;i<=44;i++)
        {
            button[i]=new JButton("");
            button[i].setForeground(Color.WHITE);
            button[i].setBackground(Color.WHITE);
            button[i].setBounds(750-50*counter, 750, 50, 50);
            counter++;
            button[i].setBorder(thickBorder);
            if(i==31||i==40)
                button[i].setIcon(new ImageIcon("yellowSlideStart.png"));
            else if(i==32||i==33||i==34||i==41||i==42)
                button[i].setIcon(new ImageIcon("yellowSlideMedium.png"));
            else if(i==35||i==43)
                button[i].setIcon(new ImageIcon("yellowSlideEnd.png"));
            layeredPane.add( button[i]);

        }
        counter=0;
        for(int i=45;i<=60;i++)
        {
            button[i]=new JButton("");
            button[i].setForeground(Color.WHITE);
            button[i].setBackground(Color.WHITE);
            button[i].setBounds(0, 750-50*counter, 50, 50);
            button[i].setBorder(thickBorder);

            counter++;
            if(i==47||i==56)
                button[i].setIcon(new ImageIcon("greenSlideStart.png"));
            else if(i==48||i==49||i==50||i==57||i==58)
                button[i].setIcon(new ImageIcon("greenSlideMedium.png"));
            else if(i==51||i==59)
                button[i].setIcon(new ImageIcon("greenSlideEnd.png"));
            layeredPane.add( button[i]);

        }
        counter=1;
        for(int i=62;i<=66;i++)
        {
            button[i]=new JButton("");
            button[i].setIcon(new ImageIcon("red.jpg"));
            button[i].setBorder(thickBorder);
            button[i].setBounds(100, 50*counter, 50, 50);
            counter++;
            layeredPane.add(button[i]);
        }
        counter=1;
        for(int i=67;i<=71;i++)
        {
            button[i]=new JButton("");
            button[i].setIcon(new ImageIcon("yellow.jpg"));
            button[i].setForeground(Color.YELLOW);
            button[i].setBackground(Color.YELLOW);
            button[i].setBorder(thickBorder);
            button[i].setBounds(650, 750-50*counter, 50, 50);
            counter++;
            layeredPane.add(button[i]);
        }
        JLabel lblNewLabel = new JLabel("");
        lblNewLabel.setBounds(286, 377, 201, 68);
        lblNewLabel.setIcon(new ImageIcon("sorry.png"));
        layeredPane.add(lblNewLabel);

        JLabel lblNewLabel_1 = new JLabel("  HOME");
        lblNewLabel_1.setBackground(Color.WHITE);
        lblNewLabel_1.setFont(new Font("Arial Black", Font.BOLD, 15));
        lblNewLabel_1.setVerticalAlignment(SwingConstants.BOTTOM);
        lblNewLabel_1.setForeground(Color.BLACK);
        lblNewLabel_1.setBounds(89, 300, 77, 76);
        lblNewLabel_1.setBorder(redBorder);
        layeredPane.add(lblNewLabel_1);

        JPanel home_white1=new JPanel();
        home_white1.setBounds(90,300,75,70);
        layeredPane.add(home_white1);

        JLabel lblNewLabel_2 = new JLabel("       Start");
        layeredPane.setLayer(lblNewLabel_2, 0);
        lblNewLabel_2.setFont(new Font("Arial Black", Font.BOLD, 16));
        lblNewLabel_2.setVerticalAlignment(SwingConstants.BOTTOM);
        lblNewLabel_2.setBounds(175, 50, 112, 100);
        lblNewLabel_2.setBorder(redBorder);
        layeredPane.add(lblNewLabel_2);

        JLabel lblHome = new JLabel("  HOME");
        lblHome.setVerticalAlignment(SwingConstants.TOP);
        lblHome.setForeground(Color.BLACK);
        lblHome.setFont(new Font("Arial Black", Font.BOLD, 15));
        lblHome.setBackground(Color.WHITE);
        lblHome.setBorder(yellowBorder);
        lblHome.setBounds(637, 424, 77, 76);
        layeredPane.add(lblHome);
        JPanel home_white=new JPanel();
        home_white.setBounds(638,425,75,70);
        layeredPane.add(home_white);

        JPanel panel_2 = new JPanel();
        panel_2.setBounds(180, 52, 107, 98);
        layeredPane.add(panel_2);


        redPawn2.setBounds(190,60,35,30);
        layeredPane.add(redPawn2);
        redPawn2.setIcon(new ImageIcon("redPawn2.png"));
        layeredPane.setLayer(redPawn2, 1);


        redPawn1.setIcon(new ImageIcon("redPawn1.png"));
        redPawn1.setBounds(240,60,35,30);
        layeredPane.setLayer(redPawn1, 1);
        layeredPane.add(redPawn1);

        JPanel panel_4 = new JPanel();
        panel_4.setBounds(513, 650, 112, 100);
        panel_4.setBorder(yellowBorder);
        layeredPane.add(panel_4);

        JLabel lblNewLabel_6 = new JLabel("Start ");
        lblNewLabel_6.setFont(new Font("Arial Black", Font.BOLD, 16));
        lblNewLabel_6.setBackground(Color.WHITE);
        panel_4.add(lblNewLabel_6);


        layeredPane.add(yellowPawn2);
        layeredPane.setLayer(yellowPawn2, 1);
        yellowPawn2.setBounds(520,690,35,30);
        yellowPawn2.setVerticalAlignment(SwingConstants.BOTTOM);
        yellowPawn2.setIcon(new ImageIcon("yellowPawn2.png"));
        

        layeredPane.add(yellowPawn1);
        layeredPane.setLayer(yellowPawn1, 1);
        yellowPawn1.setVerticalAlignment(SwingConstants.BOTTOM);
        yellowPawn1.setBounds(570,690,35,30);
        yellowPawn1.setIcon(new ImageIcon("yellowPawn1.png"));

        JLabel lblNewLabel_7 = new JLabel("Receive Card");
        lblNewLabel_7.setFont(new Font("Arial Black", Font.BOLD, 10));
        lblNewLabel_7.setBounds(884, 300, 101, 13);
        layeredPane.add(lblNewLabel_7);

        JLabel lblNewLabel_8 = new JLabel("Current Card");
        lblNewLabel_8.setFont(new Font("Arial Black", Font.BOLD, 10));
        lblNewLabel_8.setBounds(1050, 300, 101, 13);
        layeredPane.add(lblNewLabel_8);


        foldButton.setFont(new Font("Arial Black", Font.BOLD, 10));
        foldButton.setIcon(null);
        foldButton.setForeground(Color.BLACK);
        foldButton.setBackground(Color.RED);
        foldButton.setBounds(960, 323, 128, 36);
        layeredPane.add(foldButton);

        JLabel lblNewLabel_10 = new JLabel("Turn:");
        lblNewLabel_10.setFont(new Font("Tahoma", Font.BOLD, 13));
        lblNewLabel_10.setBounds(869, 445, 84, 21);
        layeredPane.add(lblNewLabel_10);

        JLabel lblNewLabel_11 = new JLabel("Cards Left:");
        lblNewLabel_11.setFont(new Font("Tahoma", Font.BOLD, 13));
        lblNewLabel_11.setBounds(869, 476, 77, 21);
        layeredPane.add(lblNewLabel_11);


        turnLabel.setFont(new Font("Tahoma", Font.PLAIN, 13));
        turnLabel.setBounds(928, 449, 107, 13);
        layeredPane.add(turnLabel);

        infoText.setFont(new Font("Tahoma", Font.BOLD, 10));
        infoText.setBounds(880, 510, 280, 70);
        layeredPane.add(infoText);

        cards_left.setFont(new Font("Tahoma", Font.BOLD, 13));
        cards_left.setHorizontalAlignment(SwingConstants.RIGHT);
        cards_left.setBounds(914, 476, 71, 21);
        layeredPane.add(cards_left);


        btnReceiveCard.setIcon(new ImageIcon("backCard.png"));
        btnReceiveCard.setBounds(874, 100, 120, 200);
        layeredPane.add(btnReceiveCard);


        btnCurrentCard.setBounds(1040, 100, 120, 200);
        layeredPane.add(btnCurrentCard);



        JPanel panel = new JPanel();
        panel.setBounds(845, 400, 330, 200);
        panel.setBorder(thickBorder);
        layeredPane.add(panel);

        JLabel lblNewLabel_9 = new JLabel("Info Box");
        panel.add(lblNewLabel_9);
        lblNewLabel_9.setFont(new Font("Tahoma", Font.BOLD, 13));

        JLabel lblNewLabel_3 = new JLabel("");
        lblNewLabel_3.setIcon(new ImageIcon("bg.png"));
        lblNewLabel_3.setBounds(796, 300, 390, 479);
        layeredPane.add(lblNewLabel_3);

        JLabel lblNewLabel_4 = new JLabel("");
        lblNewLabel_4.setIcon(new ImageIcon("bg.png"));
        lblNewLabel_4.setBounds(796, 0, 390, 376);
        layeredPane.add(lblNewLabel_4);

        JLabel lblNewLabel_5 = new JLabel("");
        lblNewLabel_5.setIcon(new ImageIcon("background.png"));
        lblNewLabel_5.setBounds(0, 798, 801, 23);
        layeredPane.add(lblNewLabel_5);

        JLabel lblNewLabel_12 = new JLabel("");
        lblNewLabel_12.setIcon(new ImageIcon("bg.png"));
        lblNewLabel_12.setBounds(796, 721, 390, 100);
        layeredPane.add(lblNewLabel_12);

        JLabel lblNewLabel_13 = new JLabel("");
        lblNewLabel_13.setIcon(new ImageIcon("bg.png"));
        lblNewLabel_13.setBounds(0, 750, 300, 200);
        layeredPane.add(lblNewLabel_13);

        JLabel lblNewLabel_14 = new JLabel("");
        lblNewLabel_14.setIcon(new ImageIcon("bg.png"));
        lblNewLabel_14.setBounds(300, 750, 300, 200);
        layeredPane.add(lblNewLabel_14);

        JLabel lblNewLabel_15 = new JLabel("");
        lblNewLabel_15.setIcon(new ImageIcon("bg.png"));
        lblNewLabel_15.setBounds(600, 750, 300, 200);
        layeredPane.add(lblNewLabel_15);

        JLabel lblNewLabel_16 = new JLabel("");
        lblNewLabel_16.setIcon(new ImageIcon("bg.png"));
        lblNewLabel_16.setBounds(900, 750, 300, 200);
        layeredPane.add(lblNewLabel_16);

/**
 * Initialize the menu bar
 */
        frame.setJMenuBar(menuBar);
        menuBar.add(newGame);
        menuBar.add(saveGame);
        menuBar.add(quit);
        menuBar.add(loadButton);


    }
    public void setPawnOver(JButton pawn)
    {
        if (pawn.equals(redPawn1))
        {
            redPawn1.setBounds(100, 320, 30, 30);

        }
        else if(pawn.equals(redPawn2))
        {
            redPawn2.setBounds(132, 320, 30, 30);

        }
        else if(pawn.equals(yellowPawn1))
        {
            yellowPawn1.setBounds(638, 450, 30, 30);

        }
        else if(pawn.equals(yellowPawn2))
        {
            yellowPawn2.setBounds(670, 450, 30, 30);

        }
    }
    public void updatePawn(int index,JButton pawn)
    {
        if(index==61)
            index=0;
       pawn.setBounds(button[index].getBounds());
       frame.repaint();
    }
    public void setPawnHome(JButton pawn)
    {
        if (pawn.equals(redPawn1))
        {
            redPawn1.setBounds(240,60,35,30);
            return;
        }
        else if(pawn.equals(redPawn2))
        {
            redPawn2.setBounds(190,60,35,30);
            return;
        }
        else if(pawn.equals(yellowPawn1))
        {
            yellowPawn1.setBounds(570,690,35,30);
            return;
        }
        else
            yellowPawn2.setBounds(520,690,35,30);
    }
    public void setTurnText(String turnText)
    {
        turnLabel.setText(turnText);
        frame.repaint();
    }
    public void setCards_left(String cardsLeft)
    {
        cards_left.setText(cardsLeft);
        frame.repaint();
    }
    public void setInfoText(String text){infoText.setText(text);}
    public  void setFrameVisible(boolean visible)
    {
        this.frame.setVisible(visible);
        if(!visible)
            this.frame.dispose();
    }
}
