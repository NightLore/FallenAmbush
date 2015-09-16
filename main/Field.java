package main;

import java.awt.Point;

import sprites.*;
import static main.Constants.*;
/**
 *  Class manages Sprite interactions
 *
 *  @author  Nathan Lui
 *  @version Dec 24, 2014
 */
public class Field
{
    public SpriteGroup[] sprites = new SpriteGroup[SPRITE_LENGTH];
    public Map map = new Map();
    
    public Field()
    {
        for ( int i = 0; i < sprites.length; i++ ) {
            sprites[i] = new SpriteGroup();
        }
    }
    
    /**
     * Add a sprite
     * @param s MovableSprite to add
     * @param i Team to add to: PLAYERS, ENEMIES, TERRAIN, WEAPONS
     */
    public void addSprite( MovableSprite s, int i )
    {
        sprites[i].add( s );
    }
    
    /**
     * Paints all sprites on field
     * @param g2d
     */
    public void paint(  java.awt.Graphics2D g2d )
    {
        map.paintAll( g2d );
        for ( SpriteGroup s : sprites )
        {
            s.paintAll( g2d );
            s.continueAnimation();
        }
    }
    
    /**
     * moves all sprites and spritegroups
     */
    public void move()
    {
//        setDeltas();
        // System.out.println( "Move" );
        for ( SpriteGroup s : sprites )
        {
            // System.out.println( "check" );
            s.moveAll( sprites );
        }
        if ( !sprites[PLAYERS].isAtCenter() )
        {
            Point p = getChange();
            map.setDirections( (int)p.getX(), (int)p.getY() );
            for ( SpriteGroup s : sprites )
                for ( MovableSprite sp : s )
                {
                    sp.move( (int)p.getX(), (int)p.getY() );
                    // System.out.println( sp/*.getName()*/ + ": " + sp.getRealX() + ", " + sp.getRealY() );
                }
            map.update();
        }
        for ( int i = 0; i < sprites[WEAPONS].size(); i++ )
        {
            Weapon w = (Weapon)sprites[WEAPONS].get( i );
            if ( !w.isActive() ) {
                sprites[WEAPONS].remove( w );
                i--;
            }
        }
    }
    
    /**
     * sets deltas of all player sprites according to keys
     */
    public void setDeltas()
    {
        for ( MovableSprite s : sprites[PLAYERS] )
        {
            Player p = (Player)s;
            p.checkKeys( sprites );
        }
    }
    
    /**
     * Returns a calculated change for map when map moves in order to keep 
     * player sprites in screen.
     * @return Point( change in x, change in y)
     */
    public Point getChange()
    {
        Point sCenter = sprites[PLAYERS].getCenter();
        // System.out.println( "s: " + sCenter.getX() + ", " + sCenter.getY() );
        // System.out.println( "m: " + mCenter.getX() + ", " + mCenter.getY() );
        int dx = (int)CENTER.getX() - (int)sCenter.getX();
        int dy = (int)CENTER.getY() - (int)sCenter.getY();
        if ( dx != 0 ) dx = ( dx > 0 ) ? 1 : -1;
        if ( dy != 0 ) dy = ( dy > 0 ) ? 1 : -1; 
        return new Point( dx, dy );
    }
}
