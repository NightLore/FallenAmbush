package main;


import java.awt.event.KeyEvent;


public final class Constants
{
//    public static final Listener LISTENER = new Listener();
    public static long gameTime = 0;
    

    public static enum GameState { MENU, GAME };
    public static GameState gameState = GameState.MENU;
    public static String[] menuButtons = { "Play", "Console" };
    
    public static boolean FRIENDLY_FIRE = true;
    
    // Directional values
    public final static int NORTH = 0;
    public final static int WEST = 1;
    public final static int SOUTH = 2;
    public final static int EAST = 3;
    public final static int NUMDIR = 4;
    
    // Attack indexes
    public final static int ATTACK1 = 4;
    public final static int ATTACK2 = 5;
    public final static int ATTACK3 = 6; // TODO find purpose or remove
    public final static int NUMATK = 3;
    
    // Sprite Array constants
    public static final int PLAYERS = 0;
    public static final int TERRAIN = 1;
    public static final int ENEMIES = 2;
    public static final int WEAPONS = 3;
    public static final int SPRITE_LENGTH = 4;

    // Stat values
    public final static int LEVEL = 0;
    public final static int EXP = 1;
    public final static int UPGDPTS = 2;
    public final static int ATK = 3;
    public final static int DEF = 4;
    public final static int HP = 5;
    public final static int MAXHP = 6;
    public final static int MANA = 7;
    public final static int MAXMANA = 8;
    public final static int ACC = 9;
    public final static int LUCK = 10;
    public final static int SPEED = 11;
    public final static int DIR = 12;
    public final static int TEAM = 13;
    public final static int STATLENGTH = 14;
    
    // Collision
    public static final boolean PIXEL_PERFECT = false;
    
    // Window
    public static final long SERIAL_ID = 1L;
//    public static final String PACKAGE = "";
    public static final String PACKAGE = "images/";
    public static final java.awt.Insets INSETS = new java.awt.Insets( 25, 3, 3, 3 );
    public static final int WINDOW_WIDTH = 500;
    public static final int WINDOW_HEIGHT = 500;
    public static final java.awt.Point CENTER = 
                    new java.awt.Point( WINDOW_WIDTH / 2, WINDOW_HEIGHT / 2 );
    // private static final int wOffSet = 5;
    // private static final int hOffSet = 25;// Note: if (resizable == true), add 10
    
    // Default Controls
    /**
     * Array [up,left,down,right, comma,period,slash]
     */
    public static final int[] ARROW_KEYS = { KeyEvent.VK_UP, KeyEvent.VK_LEFT, 
                                             KeyEvent.VK_DOWN, KeyEvent.VK_RIGHT, 
                                             KeyEvent.VK_COMMA, 
                                             KeyEvent.VK_PERIOD, 
                                             KeyEvent.VK_SLASH };
    /**
     * Array [w,a,s,d, z,x,c]
     */
    public static final int[] WASD_KEYS = { KeyEvent.VK_W, KeyEvent.VK_A, 
                                            KeyEvent.VK_S, KeyEvent.VK_D, 
                                            KeyEvent.VK_Z, 
                                            KeyEvent.VK_X, 
                                            KeyEvent.VK_C };
    /**
     * Array [i,j,k,l, t,y,u]
     */
    public static final int[] IJKL_KEYS = { KeyEvent.VK_I, KeyEvent.VK_J, 
                                            KeyEvent.VK_K, KeyEvent.VK_L, 
                                            KeyEvent.VK_T, 
                                            KeyEvent.VK_Y, 
                                            KeyEvent.VK_U };
    /**
     * Array [8,4,5,6, 1,2,3]
     */
    public static final int[] NUMPAD_KEYS = { KeyEvent.VK_NUMPAD8, KeyEvent.VK_NUMPAD4, 
                                              KeyEvent.VK_NUMPAD5, KeyEvent.VK_NUMPAD6, 
                                              KeyEvent.VK_NUMPAD1, 
                                              KeyEvent.VK_NUMPAD2, 
                                              KeyEvent.VK_NUMPAD3 };
    /**
     * Array [5,1,2,3, 7,8,9]
     */
    public static final int[] NUMREV_KEYS = { KeyEvent.VK_NUMPAD5, KeyEvent.VK_NUMPAD1, 
                                              KeyEvent.VK_NUMPAD2, KeyEvent.VK_NUMPAD3, 
                                              KeyEvent.VK_NUMPAD7, 
                                              KeyEvent.VK_NUMPAD8, 
                                              KeyEvent.VK_NUMPAD9 };
    /**
     * Array [Home,Delete,End,Pg-down, leftBracket,rightBracket,backSlash]
     */
    public static final int[] NAVGAT_KEYS = { KeyEvent.VK_HOME, KeyEvent.VK_DELETE, 
                                              KeyEvent.VK_END, KeyEvent.VK_PAGE_DOWN, 
                                              KeyEvent.VK_BRACELEFT, 
                                              KeyEvent.VK_BRACERIGHT, 
                                              KeyEvent.VK_BACK_SLASH };
}
