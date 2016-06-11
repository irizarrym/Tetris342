/*
    Michael Irizarry (miriza6@uic.edu)
    Tetris (Project 5)
    CS 342 (Software Design), Spring 2016
    University of Illinois at Chicago
*/
package tetris;

import static tetris.Tetromino.line;

/**
 * Tetromino subclass which represents the "O" tetromino
 */
public class TetrominoO extends Tetromino
{
    private static final char[][] O1 = {
        line("    "),
        line(" OO "),
        line(" OO "),
        line("    ")
    };
    
    private static final char[][][] pieceSet = { O1 };
    private static final TetrisCell cell = new TetrisCell('O', false);
    
    public TetrominoO()
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
