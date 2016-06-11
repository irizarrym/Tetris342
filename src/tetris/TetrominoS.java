/*
    Michael Irizarry (miriza6@uic.edu)
    Tetris (Project 5)
    CS 342 (Software Design), Spring 2016
    University of Illinois at Chicago
*/
package tetris;

/**
 * Tetromino subclass which represents the "S" tetromino
 */
public class TetrominoS extends Tetromino
{
    private static final char[][] S1 = {
        line("    "),
        line("  SS"),
        line(" SS "),
        line("    ")
    };
    
    private static final char[][] S2 = {
        line("  S "),
        line("  SS"),
        line("   S"),
        line("    ")
    };
    
    private static final char[][][] pieceSet = { S1, S2 };
    private static final TetrisCell cell = new TetrisCell('S', false);
    
    public TetrominoS()
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
