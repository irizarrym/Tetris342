/*
    Michael Irizarry (miriza6@uic.edu)
    Tetris (Project 5)
    CS 342 (Software Design), Spring 2016
    University of Illinois at Chicago
*/
package tetris;

import java.io.*;
import javax.swing.*;

/**
 * Static class which pre-loads all the graphics for use in the GUI
 */
public final class Graphics
{
    /**
     * Private constructor prevents creating new instances of this class
     */
    private Graphics(){ }
    
    // Relative path to the img folder
    private static final String basePath =
        "." + File.separator + "img" + File.separator;
    
    // Image for an empty block in the grid
    public static final ImageIcon BLANK     = new ImageIcon(basePath + "blank.png");
    
    // Image for a highlighted cell for the drop shadow in the grid
    public static final ImageIcon HIGHLIGHT = new ImageIcon(basePath + "highlight.png");
    
    // Images for the individual blocks of a tetromino ordered by integer ID
    public static final ImageIcon[] BLOCKS = new ImageIcon[]{
        new ImageIcon(basePath + "OB.png"),
        new ImageIcon(basePath + "IB.png"),
        new ImageIcon(basePath + "SB.png"),
        new ImageIcon(basePath + "ZB.png"),
        new ImageIcon(basePath + "LB.png"),
        new ImageIcon(basePath + "JB.png"),
        new ImageIcon(basePath + "TB.png")
    };
    
    // Images for tetrominoes ordered by integer ID
    public static final ImageIcon[] PIECES = new ImageIcon[]{
        new ImageIcon(basePath + "O.png"),
        new ImageIcon(basePath + "I.png"),
        new ImageIcon(basePath + "S.png"),
        new ImageIcon(basePath + "Z.png"),
        new ImageIcon(basePath + "L.png"),
        new ImageIcon(basePath + "J.png"),
        new ImageIcon(basePath + "T.png")
    };
    
    // Images for digits used by GuiCounter
    public static final ImageIcon[] DIGITS = new ImageIcon[]{
        new ImageIcon(basePath + "digit0.png"),
        new ImageIcon(basePath + "digit1.png"),
        new ImageIcon(basePath + "digit2.png"),
        new ImageIcon(basePath + "digit3.png"),
        new ImageIcon(basePath + "digit4.png"),
        new ImageIcon(basePath + "digit5.png"),
        new ImageIcon(basePath + "digit6.png"),
        new ImageIcon(basePath + "digit7.png"),
        new ImageIcon(basePath + "digit8.png"),
        new ImageIcon(basePath + "digit9.png")
    };
    
    /**
     * Retrieve an image for a single block for use in GuiGrid
     * 
     * @param id    Integer ID of the block
     * @return      ImageIcon of a single block
     * @throws Exception 
     */
    public static ImageIcon getBlock(int id) throws Exception
    {
        if(id == -1) return BLANK;
        if(id == -2) return HIGHLIGHT;
        
        // Throws an exception if id is out of range
        TetrisCell.indexToChar(id);
        
        return BLOCKS[id];
    }
    
    /**
     * Retrieve an image for a single block for use in GuiGrid
     * 
     * @param c     Character ID of the block
     * @return      ImageIcon of a single block
     * @throws Exception 
     */
    public static ImageIcon getBlock(char c) throws Exception
    {
        if(c == ' ') return BLANK;
        if(c == '*') return HIGHLIGHT;
        int index = TetrisCell.charToIndex(c);
        return BLOCKS[index];
    }
    
    /**
     * Retrieve an image for a tetromino for use in next piece preview
     * 
     * @param id        Integer ID of the tetromino
     * @return          ImageIcon of the tetromino
     * @throws Exception 
     */
    public static ImageIcon getPiece(int id) throws Exception
    {
        TetrisCell.indexToChar(id);
        return PIECES[id];
    }
    
    /**
     * Retrieve an image for a tetromino for use in next piece preview
     * 
     * @param c         Character ID of the tetromino
     * @return          ImageIcon of the tetromino
     * @throws Exception 
     */
    public static ImageIcon getPiece(char c) throws Exception
    {
        int index = TetrisCell.charToIndex(c);
        return PIECES[index];
    }
}