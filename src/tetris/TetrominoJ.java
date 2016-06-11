/*
    Michael Irizarry (miriza6@uic.edu)
    Tetris (Project 5)
    CS 342 (Software Design), Spring 2016
    University of Illinois at Chicago
*/
package tetris;

import static tetris.Tetromino.line;

/**
 * Tetromino subclass which represents the "J" tetromino
 */
public class TetrominoJ extends Tetromino
{
    private static final char[][] J1 = {
        line("    "),
        line(" JJJ"),
        line("   J"),
        line("    ")
    };
    
    private static final char[][] J2 = {
        line("  JJ"),
        line("  J "),
        line("  J "),
        line("    ")
    };
    
    private static final char[][] J3 = {
        line(" J  "),
        line(" JJJ"),
        line("    "),
        line("    ")
    };
    
    private static final char[][] J4 = {
        line("  J "),
        line("  J "),
        line(" JJ "),
        line("    ")
    };
    
    private static final char[][][] pieceSet = { J3, J4, J1, J2 };
    private static final TetrisCell cell = new TetrisCell('J', false);
    
    public TetrominoJ()
    {
        super(pieceSet, cell);
    }
    
    @Override
    public int getWidth()
    {
        return 4;
    }

    @Override
    public int getHeight()
    {
        return 4;
    }
}
