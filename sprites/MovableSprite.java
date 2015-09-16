package sprites;

// import java.awt.image.BufferedImage;

import static main.Constants.*;

import java.util.Scanner;


/**
 *  Sprite that can move
 *
 *  @author  Nathan Lui
 *  @version Dec 24, 2014
 */
public class MovableSprite extends ImageSprite
{

    /**
     * 
     */
    private static final long serialVersionUID = SERIAL_ID;

    public int[] delta = new int[NUMDIR];
    private String name = "";
    
    public MovableSprite( String imgFile )
    {
        super( makeTransparent( toImage( imgFile ), java.awt.Color.WHITE ) );
    }

    @Override
    public void paint(java.awt.Graphics g) {
        g.setColor( java.awt.Color.GRAY );
        g.drawString( getName(), getX() - getWidth() / 2, 
                                getY() - getHeight() / 2 );
        super.paint( g );
    }
    
    /**
     * Speed of the sprite
     * @return
     */
    public int getSpeed()
    {
        return 1;
    }

    /**
     * Moves according to dx() and dy()
     */
    public void move()
    {
        this.move( dx(), dy() );
    }
    
    /**
     * Moves according to dx and dy
     * @param dx change in x
     * @param dy change in y
     */
    public void move( int dx, int dy )
    {
        // System.out.print( "Player Move: " + getX() + ", " + getY() );
        this.setPosition( this.getX() + dx, this.getY() + dy );
        // System.out.println( " - " + dx + ", " + dy + " = " + getX() + ", " + getY() );
    }
    
    public void move( SpriteGroup[] sprites )
    {
        move( dx(), 0 );
        if ( colliding( sprites ) )
            move( -dx(), 0 );
        move( 0, dy() );
        if ( colliding( sprites ) )
            move( 0, -dy() );
    }
    
    protected boolean colliding( SpriteGroup[] sprites )
    {
      for ( int i = 0; i < WEAPONS; i++ )
      {
          for ( MovableSprite s : sprites[i] )
          {
              // System.out.println( s.getRealX() + ", " + this.collidesWith( s, true ) );
              if ( s != this 
                && this.collidesWith( s, PIXEL_PERFECT ) )
              {
                  return true;
              }
          }
      }
      return false;
    }

    /**
     * Return change in x
     * @return delta[EAST] - delta[WEST]
     */
    public int dx()
    {
        return delta[EAST] - delta[WEST];
    }
    
    /**
     * Return change in y
     * @return delta[SOUTH] - delta[NORTH]
     */
    public int dy()
    {
        return delta[SOUTH] - delta[NORTH];
    }
    
    /**
     * Sets either dx() or dy() to 0 depending on direction and then sets 
     * delta[d] to speed
     * Precondition: d is one of the directional values
     * @param d
     */
    public void setDelta( int d )
    {
        stop( d );
        // System.out.println( getName() + ", setDelta: " + d +" " + delta[d] );
        delta[d] = getSpeed();
    }
    
    /**
     * Stop in a direction
     * @param d
     */
    public void stop( int d )
    {
        if ( d < 0 || d > NUMDIR ) return;
        if ( d % 2 == 0 ) {
            delta[NORTH] = 0;
            delta[SOUTH] = 0;
        }
        else { // if ( d % 2 == 1 )
            delta[WEST] = 0;
            delta[EAST] = 0;
        }
    }
    
    /**
     * Stop completely
     */
    public void stop()
    {
        stop( NORTH );
        stop( WEST );
        // for ( int i = 0; i < NUMDIR; i++ ) delta[i] = 0;
    }
    
    /**
     * Sets name
     * @param newName
     */
    public void setName( String newName )
    {
        name = newName;
    }
    
    /**
     * Returns name of sprite
     * @return
     */
    public String getName()
    {
        return name;
    }
    
    /**
     * Return BufferedImage object of a picture file
     * @param fileName
     * @return buffered image
     */
    public static java.awt.image.BufferedImage toImage( String fileName )
    {
        // System.out.println( System.getProperty( "user.dir" ) + " " + fileName );
        try { 
            return javax.imageio.ImageIO.read( new java.io.File( fileName ) );
        } catch ( java.io.IOException e ) { 
            System.out.println( "Cannot find: " + fileName );
            e.printStackTrace(); 
            @SuppressWarnings("resource")
            Scanner scanIn = new Scanner( System.in );
            System.out.print( "Input file: " );
            return toImage( scanIn.nextLine() );
        }
        // return null;
    }
}
