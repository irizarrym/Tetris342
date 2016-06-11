/*
    Michael Irizarry (miriza6@uic.edu)
    Tetris (Project 5)
    CS 342 (Software Design), Spring 2016
    University of Illinois at Chicago
*/
package tetris;

import static tetris.Tetromino.line;

/**
 * Tetromino subclass which represents the "Z" tetromino
 */
public class TetrominoZ extends Tetromino
{
    private static final char[][] Z1 = {
        line("    "),
        line(" ZZ "),
        line("  ZZ"),
        line("    ")
    };
    
    private static final char[][] Z2 = {
        line("   Z"),
        line("  ZZ"),
        line("  Z "),
        line("    ")
    };
    
    private static final char[][][] pieceSet = { Z1, Z2 };
    private static final TetrisCell cell = new TetrisCell('Z', false);
    
    public TetrominoZ()
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
