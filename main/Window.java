package main;

import static main.Constants.*;
import sprites.Player;

public class Window extends javax.swing.JFrame {

    /**
     * 
     */
    private static final long serialVersionUID = SERIAL_ID;
    
    public Window() {

        add(new Main());

        setWindow( this, "Open Ended" );
    }
    
    public static void setWindow( javax.swing.JFrame frame, String title )
    {
        frame.setDefaultCloseOperation( javax.swing.JFrame.EXIT_ON_CLOSE );
        frame.setSize( WINDOW_WIDTH + INSETS.left + INSETS.right, 
                      WINDOW_HEIGHT + INSETS.top + INSETS.bottom );
        frame.setLocationRelativeTo( null );
        frame.setTitle( title );
        frame.setResizable( false );
        frame.setVisible( true );
    }
    
    /**
     * Returns array of 2 test sprites
     * @return
     */
    public static Player[] getTestSprites()
    {
        Player[] sprite = new Player[2];
        for ( int j = 0; j < sprite.length; j++ )
        {
            sprite[j] = ( new Player( PACKAGE + "player.png" ) );
            Player s = sprite[j];
            s.splitSprite( 2, 3 );
            s.setRefPixel( s.getWidth() / 2, s.getHeight() / 2 );
            s.setAnimation( 100 );
            s.invokeTransparency( java.awt.Color.WHITE );
            s.setPosition( WINDOW_WIDTH / 2, WINDOW_HEIGHT / 2 );
            // s.getStats()[Stats.LEVEL] = 0;
            for ( int i = 0; i < STATLENGTH; i++ )
                s.getStats()[i] = 20;
            s.getStats()[HP] = s.getStats()[MAXHP] = 50;
            s.getStats()[SPEED] = 2; // TODO
            s.getStats()[DIR] = 0;
            s.setName( "Sprite" + j );
//             s.setFrameSequence( Sprite.move[0], "0" );
        }
        sprite[1].setControls( WASD_KEYS );
        sprite[1].setPosition( 5, 5 );
        sprite[1].getStats()[TEAM] = ENEMIES;
        return sprite;
    }

    public static void main( String[] args ) {
        javax.swing.SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                new Window();
            }
        });
    }
}