/*
    Michael Irizarry (miriza6@uic.edu)
    Tetris (Project 5)
    CS 342 (Software Design), Spring 2016
    University of Illinois at Chicago
*/
package tetris;

import static tetris.Tetromino.line;

/**
 * Tetromino subclass which represents the "I" tetromino
 */
public class TetrominoI extends Tetromino
{
    private static final char[][] I1 = {
        line("    "),
        line("IIII"),
        line("    "),
        line("    ")
    };
    
    private static final char[][] I2 = {
        line("  I "),
        line("  I "),
        line("  I "),
        line("  I ")
    };
    
    private static final char[][][] pieceSet = { I2, I1 };
    private static final TetrisCell cell = new TetrisCell('I', false);
    
    public TetrominoI()
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
