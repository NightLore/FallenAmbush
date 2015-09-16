package sprites;

import static main.Constants.*;

import java.awt.Color;

public class FightingSprite extends StatsSprite
{

    /**
     * 
     */
    private static final long serialVersionUID = SERIAL_ID;

    public FightingSprite( String imgFile )
    {
        super( imgFile );
    }

    /* (non-Javadoc)
     * @see sprites.MovableSprite#paint(java.awt.Graphics)
     */
    @Override
    public void paint(java.awt.Graphics g) { // paints hp bar
        int hpX = getX() - getWidth() / 2;
        int hpY = getY() - getHeight() / 2;
        int hpW = getWidth();
        int hpH = getHeight() / 8;
        // double ratio = getStats()[HP] / getStats()[MAXHP];
        g.setColor( Color.GRAY );
        g.fillRect( hpX, hpY, hpW, hpH );
        g.setColor( Color.GREEN );
        g.fillRect( hpX + 1, 
                    hpY + 1, 
        /*Math.abs(*/ ( hpW - 2 ) * getStats()[HP] / getStats()[MAXHP], // TODO remove abs.
                    hpH - 2 );
        super.paint( g );
    }
    
    /**
     * Returns if this Sprite is dead
     * @return getStats()[HP] <= 0
     */
    public boolean isDead()
    {
        if (this.getStats()[HP] <= 0)
        {
            this.stopAnimation();
            return true;
        }
        return false;
    }
    
    /**
     * Puts this sprite's weapon in the weapon group
     * @param sprites
     * @param i type of attack
     */
    public void attack( SpriteGroup[] sprites, int i )
    {
        sprites[WEAPONS].add( getWeapon( i ) );
    }
    
    /**
     * Returns weapon, to be overridden
     * @param i
     * @return
     */
    public Weapon getWeapon( int i ) // TODO check if abstract is preferred.
    {
        return new Weapon( this, "Sword.png" );
    }

}
