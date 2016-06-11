/*
    Michael Irizarry (miriza6@uic.edu)
    Tetris (Project 5)
    CS 342 (Software Design), Spring 2016
    University of Illinois at Chicago
*/
package tetris;

/**
 * Abstract base class for a single Tetromino piece
 */
public abstract class Tetromino
{
    // Instance variables
    private final char[][][] pieceSet;
    private final TetrisCell cell;
    private int pieceIndex = 0;
    
    /**
     * Constructor which initializes a new instance of this class
     * 
     * @param pieceSet  A three-dimensional array of characters which represent
     *                  the tetromino and all of it's rotations
     * @param cell      A single cell which represents the state of each filled
     *                  cell in the tetromino
     */
    Tetromino(char[][][] pieceSet, TetrisCell cell)
    {
        this.pieceSet = pieceSet;
        this.cell = cell;
    }
    
    /**
     * Retrieve the width of the char array used to represent the tetromino
     * 
     * @return  Width of grid in internal tetromino representation
     */
    public abstract int getWidth();
    
    /**
     * Retrieve the height of the char array used to represent the tetromino
     * 
     * @return  Height of grid in internal tetromino representation
     */
    public abstract int getHeight();
    
    /**
     * Retrieve the filled cell used to represent each filled cell in this
     * Tetromino
     * 
     * @return  filled TetrisCell instance
     */
    public TetrisCell getCell()
    {
        return cell;
    }
    
    /**
     * Retrieve a cell in the internal representation of the tetromino grid
     * based on the current rotation
     * 
     * @param x     x coordinate of cell
     * @param y     y coordinate of cell
     * @return      Instance of TetrisCell which is either filled or empty
     */
    public TetrisCell getCell(int x, int y)
    {
        char c = pieceSet[pieceIndex][y][x];
        
        if(c == ' ')
        {
            return TetrisCell.EMPTY_CELL;
        }
        else
        {
            return cell;
        }
    }
    
    /**
     * Retrieves the character ID of this tetromino
     * 
     * @return  character ID
     */
    public char getID()
    {
        return cell.getID();
    }
    
    /**
     * Rotate the tetromino counter-clockwise
     */
    public void rotateLeft()
    {
        pieceIndex += 1;
        pieceIndex %= pieceSet.length;
    }

    /**
     * Rotate the tetromino clockwise
     */
    public void rotateRight()
    {
        pieceIndex -= 1;
        if(pieceIndex < 0)
        {
            pieceIndex += pieceSet.length;
        }
    }
    
    /**
     * Iterates over each filled cell in the tetromino and passes it to the 
     * specified function
     * 
     * @param delegate  function which gets called for each cell
     */
    public void forEach(TetrominoCallable delegate)
    {
        for(int x = 0; x < getWidth(); ++x) for(int y = 0; y < getHeight(); ++y)
        {
            TetrisCell cell = getCell(x, y);
            if(!cell.isEmpty())
            {
                delegate.call(x, y, cell);
            }
        }
    }
    
    /**
     * Interface used as the delegate for forEach()
     */
    public interface TetrominoCallable
    {
        public void call(int x, int y, TetrisCell cell);
    }
    
    /**
     * Used internally by Tetromino sub-classes for constructing arrays of chars
     * for each tetromino
     * 
     * @param s String to convert to array of char
     * @return  array containing characters of string s
     */
    protected static char[] line(String s)
    {
        char[] result = new char[s.length()];
        for(int i = 0; i < s.length(); ++i)
        {
            result[i] = s.charAt(i);
        }
        return result;
    }
}
