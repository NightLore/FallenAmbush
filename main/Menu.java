package main;

import java.awt.Graphics;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;

import static main.Constants.*;

public class Menu 
{
    private JLabel background;
//    private BufferedImage menuImage;
    private boolean hasStarted = false;
    private JButton[] buttons = new JButton[menuButtons.length];
    public Menu() {
//        menuImage = MovableSprite.toImage( "" );
        background = new JLabel();
//        background.setIcon( new ImageIcon("images/background.png") );
        for ( int i = 0; i < buttons.length; i++ )
        {
            buttons[i] = new JButton( menuButtons[i] );
//            System.out.println( buttons[i].getActionCommand() );
        }
    }
    
    public void paint( Graphics g )
    {
        /*if ( background == null )*/ Painter.paintMenu( g, hasStarted );
//        else background.paintComponents( g );
//        if ( hasStarted() ) 
//            for ( JButton b : getButtons() )
//                background.add( b );
//            g.drawImage( menuImage, 0, 0, null );
    }
    
    public JLabel getBackground()
    {
        return background;
    }
    
    public JButton[] getButtons()
    {
        return buttons;
    }
    
    public boolean hasStarted()
    {
        return hasStarted;
    }
    
    public void setHasStarted( boolean b )
    {
        hasStarted = b;
    }
}
