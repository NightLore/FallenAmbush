package sprites;

import static main.Constants.*;
/**
 *  Class is spawned by a Sprite's attack
 *
 *  @author  Nathan Lui
 *  @version Dec 24, 2014
 */
public class Weapon extends StatsSprite
{

    /**
     * 
     */
    private static final long serialVersionUID = SERIAL_ID;
    
    private boolean active = true;
    private ImageSprite mySprite;

    public Weapon( ImageSprite s )
    {
        this( s, PACKAGE + "Sword.png" );
    }
    
    public Weapon( ImageSprite s, String imgFile )
    {
        super( imgFile );
        mySprite = s;
    }
    
//    public Weapon( ImageSprite s, String imgFile, int[] stats )
//    {
//        super( imgFile, stats );
//        mySprite = s;
//    }

    @Override
    public int getSpeed() // TODO problem with array
    {
        System.out.println( "Weapon Speed: "+ super.getSpeed() );
        // return super.getSpeed();
        return 4;
    }
    @Override
    public void paint( java.awt.Graphics g )
    {
        if ( isActive() ) super.paint( g );
    }

    @Override
    public void move( SpriteGroup[] sprites )
    {
        if ( this.getRealX() < -this.getWidth() || this.getRealX() > WINDOW_WIDTH 
          || this.getRealY() < -this.getHeight() || this.getRealY() > WINDOW_HEIGHT )
            setActive( false );
        if ( !isActive() )
            return;
        super.move( sprites );
        // if ( colliding( sprites ) );
    }
    
    @Override
    public boolean collidesWith( ImageSprite sprite, boolean pixelPerfect )
    {
        if ( sprite != mySprite && super.collidesWith( sprite, pixelPerfect )  )
        {
            StatsSprite s = (StatsSprite)sprite;
            if ( FRIENDLY_FIRE || s.getStats()[TEAM] != this.getStats()[TEAM] ) // TODO check friendly fire
            {
                s.getStats()[HP] -= 1;
                setActive( false );
            }
        }
        return false;
    }

    public boolean isActive()
    {
        return active;
    }

    public void setActive( boolean active )
    {
        this.active = active;
    }
}
