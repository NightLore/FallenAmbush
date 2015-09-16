package sprites;

import java.util.HashMap;

import static main.Constants.*;

/**
 *  Controllable Moving Sprite
 *
 *  @author  Nathan Lui
 *  @version Dec 24, 2014
 */
public class Player extends FightingSprite
{
    public static HashMap<Integer, Boolean> KEYS = new HashMap<Integer, Boolean>();
//    public static boolean keysHasChanged = false;

    public int[] controls = new int[NUMDIR + NUMATK];
    
    /**
     * 
     */
    private static final long serialVersionUID = SERIAL_ID;
    
    /**
     * Constructs Player object with controls
     * @param imgFile name of Image file
     */
    public Player( String imgFile )
    {
        this( imgFile, ARROW_KEYS );
    }
    
    /**
     * Constructs Player with given controls
     * @param imgFile name of Image file
     * @param c controls
     */
    public Player( String imgFile, int[] c )
    {
        super( imgFile );
        setControls( c );
        this.getStats()[TEAM] = PLAYERS;
    }
    
    /**
     * Copies values of array c into controls array
     * @param c
     */
    public void setControls( int[] c )
    {
        for ( int i = 0; i < controls.length; i++ )
        {
            controls[i] = c[i];
            // System.out.println( "setControls:" + i + " " + c[i] + " " + KEYS.get( c[i] ) );
        }
    }
    
    /* (non-Javadoc)
     * @see sprites.MovableSprite#move(sprites.SpriteGroup[])
     */
    public void move( SpriteGroup[] sprites )
    {
        if ( this.isDead() ) return;
        move( dx(), 0 );
        if ( colliding( sprites ) || isOutside() )
            move( -dx(), 0 );
        move( 0, dy() );
        if ( colliding( sprites ) || isOutside() )
            move( 0, -dy() );
//        setPosition( 
//            Math.min( Math.max( getRefX(), getX() + dx() ), WINDOW_WIDTH - getWidth() + getRefX() ), 
//            Math.min( Math.max( getRefY(), getY() + dy() ), WINDOW_HEIGHT - getHeight() + getRefY() ) 
//        );
//        super.move( sprites );
        // System.out.println( getRealX() + ", " + getRealY() );
//        if ( isColliding( true ) )
//        {
//            x = tempX;
//            y = tempY;
//        }
    }
    
    
    /* (non-Javadoc)
     * @see sprites.FightingSprite#getWeapon(int)
     */
    public Weapon getWeapon( int i )
    {
        Weapon w;
        if ( i == ATTACK1 )
        {
            w = new Weapon( this, PACKAGE + "Sword.png" );
        }
        else if ( i == ATTACK2 )
        {
            w = new Weapon( this, PACKAGE + "Circle.png" );
            System.out.println( "Dir: " + this.getStats()[DIR] );
            w.setDelta( getStats()[DIR] );
        }
        else // if ( i == ATTACK3 )
        {
            w = new Weapon( this, PACKAGE + "Mine.png" );
        }
        w.setStats( this.getStats() );
        w.getStats()[SPEED] = 4;
        System.out.println( "InitSpeed: " + w.getStats()[SPEED] );
        w.setPosition( this.getX(), this.getY() );
        return w; // TODO 
    }
    
    /**
     * Checks the controls of this sprite
     * @param sprites array of sprites on field
     */
    public void checkKeys( SpriteGroup[] sprites )
    {
        for ( int i = 0; i < controls.length; i++ )
        {
            // System.out.println( "PlayerCheckKeys: " + KEYS.get( controls[i] ) );
            if ( KEYS.containsKey( controls[i] ) && KEYS.get( controls[i] ) )
            {
                if ( i < NUMDIR ) {
                    setDelta( i );
                    getStats()[DIR] = i;
                }
                else attack( sprites, i );
            }
            else
            {
                if ( i < NUMDIR )
                    delta[i] = 0;
                // TODO remove attack state
            }
        }
    }
    
    
    
}
