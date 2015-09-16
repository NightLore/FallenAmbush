package main;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;

public class Painter
{
    private static Font font = new Font( "", Font.ROMAN_BASELINE, 30 );
    public static void paintMenu( Graphics g, boolean hasEntered )
    {
        Color c = g.getColor();
        Font f = g.getFont();
        int w = Constants.WINDOW_WIDTH / 4;
        int h = Constants.WINDOW_HEIGHT / 4;
        g.setColor( Color.LIGHT_GRAY );
        g.fillRect( 0, 0, w * 4, h * 4 );
        g.setColor( Color.DARK_GRAY );
        if ( !hasEntered )
        {
            g.drawString( "Press Any Key to Continue", w, h * 3 );
        }
        else
        {
            g.setFont( font );
            g.drawString( "Play", w * 3, h * 3 );
        }
        g.setFont( font );
        g.drawString( "OpenEnded", w, h );
        g.setFont( f );
        g.setColor( c );
    }
    
    public static void paint( Graphics g, String file )
    {
        
    }
}
