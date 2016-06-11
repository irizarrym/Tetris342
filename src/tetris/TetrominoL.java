/*
    Michael Irizarry (miriza6@uic.edu)
    Tetris (Project 5)
    CS 342 (Software Design), Spring 2016
    University of Illinois at Chicago
*/
package tetris;

import static tetris.Tetromino.line;

/**
 * Tetromino subclass which represents the "L" tetromino
 */
public class TetrominoL extends Tetromino
{
    private static final char[][] L1 = {
        line("    "),
        line(" LLL"),
        line(" L  "),
        line("    ")
    };
    
    private static final char[][] L2 = {
        line("  L "),
        line("  L "),
        line("  LL"),
        line("    ")
    };
    
    private static final char[][] L3 = {
        line("   L"),
        line(" LLL"),
        line("    "),
        line("    ")
    };
    
    private static final char[][] L4 = {
        line(" LL "),
        line("  L "),
        line("  L "),
        line("    ")
    };
    
    private static final char[][][] pieceSet = { L3, L4, L1, L2 };
    private static final TetrisCell cell = new TetrisCell('L', false);
    
    public TetrominoL()
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
