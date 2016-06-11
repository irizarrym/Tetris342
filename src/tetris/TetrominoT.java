/*
    Michael Irizarry (miriza6@uic.edu)
    Tetris (Project 5)
    CS 342 (Software Design), Spring 2016
    University of Illinois at Chicago
*/
package tetris;

import static tetris.Tetromino.line;

/**
 * Tetromino subclass which represents the "T" tetromino
 */
public class TetrominoT extends Tetromino
{
    private static final char[][] T1 = {
        line("    "),
        line(" TTT"),
        line("  T "),
        line("    ")
    };
    
    private static final char[][] T2 = {
        line("  T "),
        line("  TT"),
        line("  T "),
        line("    ")
    };
    
    private static final char[][] T3 = {
        line("  T "),
        line(" TTT"),
        line("    "),
        line("    ")
    };
    
    private static final char[][] T4 = {
        line("  T "),
        line(" TT "),
        line("  T "),
        line("    ")
    };
    
    private static final char[][][] pieceSet = { T3, T4, T1, T2 };
    private static final TetrisCell cell = new TetrisCell('T', false);
    
    public TetrominoT()
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
