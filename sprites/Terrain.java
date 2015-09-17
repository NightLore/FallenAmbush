package sprites;

import static main.Constants.*;

/**
 *  Class acts as a terrain
 *
 *  @author  Nathan Lui
 *  @version Dec 24, 2014
 */
public class Terrain extends StatsSprite
{

    /**
     * 
     */
    private static final long serialVersionUID = SERIAL_ID;

    public Terrain( String imgFile )
    {
        super( imgFile );
        int[] stat = new int[STATLENGTH];
//        for ( int i = 0; i < STATLENGTH; i++ ) stat[i] = TERRAIN;
        setStats( stat );
    }
    
}
