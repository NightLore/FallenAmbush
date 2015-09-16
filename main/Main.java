package main;

import java.awt.*;
import java.awt.event.*;
import java.awt.image.BufferedImage;

import javax.swing.*;

import sprites.MovableSprite;
import sprites.Player;
import static main.Constants.*;


public class Main extends JPanel implements ActionListener, KeyListener {

    private static final long serialVersionUID = 1L;
    private Timer inGameTime;
    private Timer timer;
    private Field field;

//    private BufferedImage menuImage;
//    boolean hasStarted = false;
    private Menu menu = new Menu();
    
    // private SpriteGroup sprites = new SpriteGroup();
    // private Map map;

    public Main() {

//        menuImage = MovableSprite.toImage( "images/background.png" );
        // addKeyListener(new TAdapter());
        addKeyListener(this);
        setFocusable(true);
        setBackground(Color.BLACK);
        setDoubleBuffered(true);
        this.setLayout( new BorderLayout() );
        this.add( menu.getBackground() );

        inGameTime = new Timer( 1000, this );
        inGameTime.setActionCommand( "Game Time" );
        
        timer = new Timer(5, this );
        timer.setActionCommand( "Update" );
        timer.start();
    }
    
    private void initField()
    {
        field = new Field();
        for ( MovableSprite s : Window.getTestSprites() )
        {
            field.addSprite( s, Constants.PLAYERS );
        }
    }


    public void paintComponent(Graphics g) {
        super.paintComponents(g);

        Graphics2D g2d = (Graphics2D)g;
        switch ( gameState )
        {
            case MENU:
                 menu.paint( g2d );
//                if ( menuImage == null ) Painter.paintMenu( g, hasStarted );
//                else 
//                 g.drawImage( menuImage, 0, 0, null );
                break;
            case GAME:
                field.paint( g2d );
                // Displays game time
                if ( inGameTime.isRunning() ) {
                    long h = gameTime / 360;
                    long m = gameTime / 60;
                    long s = gameTime % 60;
                    g2d.drawString( ( ( h < 10 ) ? "0" : "" ) + h
                            + ":" + ( ( m < 10 ) ? "0" : "" ) + m
                            + ":" + ( ( s < 10 ) ? "0" : "" ) + s, 
                            0, WINDOW_HEIGHT - 10 );
                }
                break;
        }
        

        Toolkit.getDefaultToolkit().sync();
        g.dispose();
        
    }


    public void actionPerformed(ActionEvent e) {
        if ( e.getActionCommand().equals( "Game Time" ) ) {
            gameTime++;
        }
        else if ( e.getActionCommand().equals( "Update" ) ) {
            if ( field != null )
            field.move();
            repaint();
        }
        else if ( e.getActionCommand().equals( menuButtons[0] ) )
        {
            System.out.println( "performed Action");
        }
    }
    
    public void quit()
    {
        timer.stop();
        inGameTime.stop();
        Player.KEYS.clear();
        String[] options = { "Yes", "No", "Cancel" };
        int choice = JOptionPane.showOptionDialog( this, 
            "Are you sure you want to quit?", "", 
            JOptionPane.YES_NO_CANCEL_OPTION, 
            JOptionPane.QUESTION_MESSAGE, (Icon)null, 
            options, 
            options[2] );
        if ( choice == JOptionPane.YES_OPTION ) System.exit( 0 );
        inGameTime.start();
        timer.start();
    }


    // private class TAdapter extends KeyAdapter {

        public void keyPressed(KeyEvent e) {
            if ( e.getKeyCode() == KeyEvent.VK_ESCAPE )
            {
                quit();
                return;
            }
            if ( menu.hasStarted() && gameState == GameState.MENU ) {
                initField();
                gameState = GameState.GAME;
            }
            if ( !menu.hasStarted() ) { 
                menu.setHasStarted( true );
//                for ( JButton b : menu.getButtons() )
//                {
//                    this.add( b );
//                }
            }
            if ( field != null ) {
                Player.KEYS.put( e.getKeyCode(), true );
                field.setDeltas();
            }
        }
        
        public void keyReleased(KeyEvent e) {
            if ( field != null ) {
                Player.KEYS.put( e.getKeyCode(), false );
                field.setDeltas();
            }
        }
        // System.out.println( "KeyR: " + e.getKeyCode() );


        @Override
        public void keyTyped( KeyEvent arg0 ) {}
}