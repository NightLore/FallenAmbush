package sprites;

import java.awt.Graphics;
import java.awt.Point;
import java.io.Serializable;
import java.util.ArrayList;

import main.Constants;

public class SpriteGroup extends ArrayList<MovableSprite> implements Serializable
{
    private static final long serialVersionUID = 1L;

    public SpriteGroup() {}
    
    
    public void setAllDeltas( int d )
    {
        for ( MovableSprite s : this ) {
            s.setDelta( d );
        }
    }
    
    public void setAllSpeeds( int newSpeed ) {
        for ( MovableSprite s : this ) {
            s.setSpeed( newSpeed );
        }
    }
    
    public void moveAll()
    {
        for ( MovableSprite s : this ) {
            s.move();
        }
    }
    
    public void moveAll( SpriteGroup[] sprites, SpriteGroup terrain )
    {
        for ( MovableSprite s : this ) {
            s.move( sprites, terrain );
        }
    }
    
//    public void moveAll( int x, int y )
//    {
//        for ( MovableSprite s : this ) {
//            s.move( x, y );
//        }
//    }
    
    public void paintAll( Graphics g )
    {
        for ( MovableSprite s : this ) {
            s.paint( g );
        }
    }
    
    public void continueAnimation()
    {
        for ( MovableSprite s : this ) {
            s.continueAnimation();
        }
    }
    
//    public void keyReleased( KeyEvent e)
//    {
//        for ( MovableSprite s : this ) {
//            s.keyReleased( e );
//        }
//    }
//    
//    public void keyPressed( KeyEvent e)
//    {
//        for ( MovableSprite s : this ) {
//            s.keyPressed( e );
//        }
//    }
    
    private boolean isAtPoint( Point p )
    {
        Point center = this.getCenter();
        return ( center != null ) 
            && ( center.getX() == p.getX() ) 
            && ( center.getY() == p.getY() );
    }
    
    public boolean isAtCenter()
    {
        return isAtPoint( new Point( Constants.WINDOW_WIDTH / 2, 
                                     Constants.WINDOW_HEIGHT / 2 ) );
    }
    
    public Point getCenter()
    {
        if ( this.size() > 0 ) {
            int x = 0;
            int y = 0;
            for ( MovableSprite s : this ) {
                x += s.getX();
                y += s.getY();
                // System.out.println( s.getX() + ", " + s.getY() );
            }
            x /= this.size();
            y /= this.size();
            // System.out.println( x + ", " + y );
            return new Point( x, y );
        }
        else return null;
    }

}
