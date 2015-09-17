package sprites;
import static main.Constants.*;

import java.awt.Point;
import java.util.Random;


/**
 *  Class manages the map
 *
 *  @author  Nathan Lui
 *  @version Dec 24, 2014
 */
public class Map
{
    public static final Random RANDOM = new Random();
    private String[] maps = { "background.png", "background.png" };
    private int[] seed = { 0 };
    private SpriteGroup floor;
    private SpriteGroup terrain;
    private int tileWidth;
    private int tileHeight;
    

    public Map()
    {
        floor = new SpriteGroup();
        terrain = new SpriteGroup();
        for ( int i = 0; i < 9; i++ ) {
            MovableSprite m = new MovableSprite( PACKAGE + maps[seed[i % seed.length] % maps.length] );
            if ( i % 2 == 0 ) 
                m.flipHorizontal();
            else 
                m.flipVertical();
            tileWidth = m.getWidth();
            tileHeight = m.getHeight();
            m.setRefPixel( tileWidth / 2, tileHeight / 2 );
            m.setPosition( -tileWidth + ( i % 3 ) * tileWidth, 
                           -tileHeight + ( i / 3 ) * tileHeight );
            floor.add( m );
        }
        int n = RANDOM.nextInt( 10 ) + 10;
        for ( int i = 0; i < n; i++ )
        {
            MovableSprite m = new MovableSprite( PACKAGE + "DarkGreen.png" );
            m.setRefPixel( tileWidth / 2, tileHeight / 2 );
            m.setPosition( RANDOM.nextInt( WINDOW_WIDTH ), RANDOM.nextInt( WINDOW_HEIGHT ) );
            terrain.add( m );
        }
    }
    
    public void paint( java.awt.Graphics g ) 
    {
        floor.paintAll( g );
        terrain.paintAll( g );
    }
    
    public void update()
    {
        floor.moveAll();
        terrain.moveAll();
        Point mCenter = floor.getCenter();
        for ( MovableSprite m : floor ) {
            adjustMapSprite( m, mCenter );
        }
        for ( MovableSprite m : terrain ) {
            adjustMapSprite( m, mCenter );
        }
    }
    
    private void adjustMapSprite( MovableSprite m, Point mCenter )
    {
        if ( mCenter.getX() > CENTER.getX() + tileWidth * 3 / 4 ) { // Too far east
            if ( m.getX() > mCenter.getX() + tileWidth / 2 ) {
                m.setPosition( mCenter.x - 2 * tileWidth + 1, m.getY()  );
            }
        }
        else if ( mCenter.getX() < CENTER.getX() - tileWidth * 3 / 4 ) { // Too far west
            if ( m.getX() < mCenter.getX() - tileWidth / 2 ) {
                m.setPosition( mCenter.x + 2 * tileWidth - 1, m.getY() );
            }
        }
        if ( mCenter.getY() > CENTER.getY() + tileHeight * 3 / 4 ) { // Too far south
            if ( m.getY() > mCenter.getY() + tileHeight / 2 ) {
                m.setPosition( m.getX(), mCenter.y - 2 * tileHeight + 1 );
            }
        }
        else if ( mCenter.getY() < CENTER.getY() - tileHeight * 3 / 4 ) { // Too far north
            if ( m.getY() < mCenter.getY() - tileHeight / 2 ) {
                m.setPosition( m.getX(), mCenter.y + 2 * tileHeight - 1 );
            }
        }
    }
    
    public void setDirections( int x, int y )
    {
        stop();
        int d;
        d = ( x > 0 ) ? EAST : ( x < 0 ) ? WEST : -1;
        floor.setAllDeltas( d );
        terrain.setAllDeltas( d );

        d = ( y > 0 ) ? SOUTH : ( y < 0 ) ? NORTH : -1;
        floor.setAllDeltas( d );
        terrain.setAllDeltas( d );
    }
    
    private void stop()
    {
        for ( MovableSprite m : floor ) {
            m.stop();
        }
        for ( MovableSprite m : terrain ) {
            m.stop();
        }
    }

    public void changeMapSeed( int[] seed )
    {
        this.seed = seed;
    }
    
    public SpriteGroup getTerrain()
    {
        return terrain;
    }
}
