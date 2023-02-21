package SwitchBlub;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.ImageIcon;
//import javax.swing.SwingUtilities;

import java.awt.Color;
import java.awt.Rectangle;
import java.awt.CardLayout;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.awt.BorderLayout;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseAdapter;
@SuppressWarnings("serial")
public class Test extends JFrame implements Constants, ActionListener{
	JPanel cards;
    JPanel card1, card2;
    JPanel[] bulbPans = new JPanel[3];

    JButton goToRoom, back;
    JLabel[] switches = new JLabel[3];
    ImageIcon switchonIMG, switchoffIMG;
    JLabel[] bulbs = new JLabel[3];

    boolean[] switchstate = new boolean[3];

    public Test(String arg)
    {
        super(arg);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setResizable(false);

        init();

        add(cards);
    }
    public void init()
    { 
        initCard1();
        initCard2();
        setCards();
    }

    public void initCard1()
    {
        JPanel top = new JPanel(new FlowLayout(FlowLayout.CENTER));
        JPanel bot = new JPanel(new FlowLayout(FlowLayout.CENTER)); 

        card1 = new JPanel(new BorderLayout());

        switchoffIMG = new ImageIcon("switch1.jpg", "OFF switch");
        switchonIMG  = new ImageIcon("switch2.jpg", "ON switch ");

        for(int i=0; i<switches.length; i++)
            switches[i] = new JLabel(switchoffIMG);

        for(int i=0; i<switchstate.length; i++)
        {
            final int j = i;
            switches[j].addMouseListener(new MouseAdapter(){
                public void mouseClicked(MouseEvent e)
                {
                    if(switchstate[j])
                    {
                        if(ON_RECTANGLE.contains(e.getX(), e.getY()))
                            switchstate[j] = false;
                    }
                    else
                    {
                        if(OFF_RECTANGLE.contains(e.getX(), e.getY()))
                            switchstate[j] = true;
                    }
                    paintStuff();
                }
            });
        }

        top.setBackground(Color.BLACK);
        bot.setBackground(Color.BLACK);

        for(int i=0; i<switches.length; i++)
            top.add(switches[i]);

        goToRoom = new JButton("Go to room", new ImageIcon("door_closed.jpg"));
        goToRoom.addActionListener(this);
        goToRoom.setRolloverIcon(new ImageIcon("door_open.jpg"));

        bot.add(goToRoom);

        card1.add(top, BorderLayout.CENTER);
        card1.add(bot, BorderLayout.SOUTH);
    }

    public void initCard2()
    {
        JPanel top = new JPanel(new GridLayout(1, 0));
        JPanel bot = new JPanel(new FlowLayout(FlowLayout.CENTER));

        for(int i=0; i<bulbPans.length; i++)
        {
            bulbPans[i] = new JPanel();
            bulbPans[i].setBackground(Color.BLACK);
        }

        card2 = new JPanel(new BorderLayout());

        for(int i=0; i<bulbs.length; i++)
            bulbs[i] = new JLabel(new ImageIcon("bulb.jpg", "Image of a bulb"));

        bot.setBackground(Color.BLACK);

        for(int i=0; i<bulbPans.length; i++)
            top.add(bulbPans[i]);

        paintStuff();

        back = new JButton("Back to switches",new ImageIcon("door_closed.jpg"));
        back.addActionListener(this);
        back.setRolloverIcon(new ImageIcon("door_open.jpg"));

        bot.add(back);

        card2.add(top, BorderLayout.CENTER);
        card2.add(bot, BorderLayout.SOUTH);
    }

    public void setCards()
    {
        cards = new JPanel(new CardLayout()); 
        cards.add(card1, "CARD1");
        cards.add(card2, "CARD2");

    }

    public void paintStuff()
    {
        for(int i=0; i<switchstate.length; i++)
        {
            if(switchstate[i])
                bulbPans[i].add(bulbs[i]);
            else
                bulbPans[i].remove(bulbs[i]);
            switches[i].setIcon(switchstate[i]?switchonIMG:switchoffIMG);
        }
        repaint();
    }

    public void actionPerformed(ActionEvent evt) {
        CardLayout c = (CardLayout)(cards.getLayout());
        c.next(cards);
    }

}

interface Constants
{
    final static Rectangle OFF_RECTANGLE = new Rectangle(30, 75, 35, 40); 
    final static Rectangle ON_RECTANGLE  = new Rectangle(25, 35, 35, 30);
}