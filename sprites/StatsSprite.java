package sprites;

import static main.Constants.*;

public class StatsSprite extends MovableSprite
{
    /**
     * 
     */
    private static final long serialVersionUID = SERIAL_ID;
    private int[] stats = new int[STATLENGTH];

    public StatsSprite( String imgFile )
    {
        this( imgFile, null );
    }
    
    public StatsSprite( String imgFile, int[] stats )
    {
        super( imgFile );
        setStats( stats );
        setRefPixel( getWidth() / 2, getHeight() / 2 );
    }
    
    /**
     * Returns the stats of this sprite
     * @return int[]
     */
    public int[] getStats()
    {
        return stats;
    }
    
    @Override
    public int getSpeed()
    {
        return getStats()[SPEED];
    }
    
    /**
     * Sets stat of this sprite
     * Precondition: newStats.length <= this.stats.length
     * @param newStats
     */
    public void setStats( int[] newStats )
    {
        if ( newStats == null ) return;
        for ( int i = 0; i < newStats.length; i++ )
        {
            stats[i] = newStats[i];
        }
    }
    
    /**
     * Returns whether Player is outside of Window
     * @return true if Player is outside of Window
     */
    public boolean isOutside()
    {
        if ( getRefX() > getX() || WINDOW_WIDTH - getWidth() + getRefX() < getX()
          || getRefY() > getY() || WINDOW_HEIGHT - getHeight() + getRefY() < getY() )
            return true;
        return false;
    }
    
//    public int directionFacing()
//    {
//        return this.stats[DIR];
//    }
//    public void setDirection( int d )
//    {
//        this.stats[DIR] = d;
//    }

}
