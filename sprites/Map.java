package sprites;
import static main.Constants.*;

import java.awt.Point;


/**
 *  Class manages the map
 *
 *  @author  Nathan Lui
 *  @version Dec 24, 2014
 */
public class Map extends SpriteGroup
{
    
    /**
     * 
     */
    private static final long serialVersionUID = 1L;
    
    
    private String[] maps = { "background.png", "background.png" };
    private int[] seed = { 0 };

    public Map()
    {
        for ( int i = 0; i < 9; i++ ) {
            MovableSprite m = new MovableSprite( PACKAGE + maps[seed[i % seed.length] % maps.length] );
            if ( i % 2 == 0 ) 
                m.flipHorizontal();
            else 
                m.flipVertical();
            m.setRefPixel( 0, 0 );
            int w = m.getWidth();
            int h = m.getHeight();
            m.setPosition( -w + ( i % 3 ) * w, 
                           -h + ( i / 3 ) * h );
            m.setRefPixel( w / 2, h / 2 );
            add( m );
        }
    }
    
    public void update()
    {
        this.moveAll();
        Point mCenter = getCenter();
        if ( mCenter.getX() > CENTER.getX() + this.get( 0 ).getWidth() ) { // Too far east
            for ( MovableSprite m : this ) {
                if ( m.getX() > mCenter.getX() + m.getWidth() / 2 ) {
                    m.setPosition( (int)CENTER.getX() - m.getWidth() + 1, m.getY()  );
                }
            }
        }
        else if ( mCenter.getX() < CENTER.getX() - this.get( 0 ).getWidth() ) { // Too far west
            for ( MovableSprite m : this ) {
                if ( m.getX() < mCenter.getX() - m.getWidth() / 2 ) {
                    m.setPosition( (int)CENTER.getX() + m.getWidth() - 1, m.getY() );
                }
            }
        }
        if ( mCenter.getY() > CENTER.getY() + this.get( 0 ).getHeight() ) { // Too far south
            for ( MovableSprite m : this ) {
                if ( m.getY() > mCenter.getY() + m.getHeight() / 2 ) {
                    m.setPosition( m.getX(), (int)CENTER.getY() - m.getHeight() + 1 );
                }
            }
        }
        else if ( mCenter.getY() < CENTER.getY() - this.get( 0 ).getHeight() ) { // Too far north
            for ( MovableSprite m : this ) {
                if ( m.getY() < mCenter.getY() - m.getHeight() / 2 ) {
                    m.setPosition( m.getX(), (int)CENTER.getY() + m.getHeight() - 1 );
                }
            }
        }
    }
    
    public void setDirections( int x, int y )
    {
        stop();
        if ( x > 0 ) 
            this.setAllDeltas( EAST );
        else if ( x < 0 ) 
            this.setAllDeltas( WEST );
        
        if ( y > 0 )
            this.setAllDeltas( SOUTH );
        else if ( y < 0 )
            this.setAllDeltas( NORTH );
    }
    
    private void stop()
    {
        for ( MovableSprite m : this ) {
            m.stop();
        }
    }

    public void changeMapSeed( int[] seed )
    {
        this.seed = seed;
    }
}
