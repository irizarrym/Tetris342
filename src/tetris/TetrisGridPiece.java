/*
    Michael Irizarry (miriza6@uic.edu)
    Tetris (Project 5)
    CS 342 (Software Design), Spring 2016
    University of Illinois at Chicago
*/
package tetris;

import java.util.logging.*;

/**
 * An extension of TetrisGrid which places and draws a Tetromino on top of
 * the base grid
 */
public class TetrisGridPiece extends TetrisGrid
{
    // Instance variables
    protected Tetromino piece = null;
    protected int pieceX = 0;
    protected int pieceY = 0;
    
    /**
     * Initializes the base grid based on an existing TetrisGrid instance
     * 
     * @param other 
     */
    public TetrisGridPiece(TetrisGrid other)
    {
        super(other);
    }
    
    /**
     * Retrieve the state of a cell in the grid; this is where the tetromino
     * is drawn on top of the base grid
     * 
     * @param x     x coordinate of cell
     * @param y     y coordinate of cell
     * @return      TetrisCell instance representing state of cell
     */
    @Override
    public TetrisCell getCell(int x, int y)
    {
        TetrisCell result = super.getCell(x, y);
        
        if(piece != null)
        {
            x -= pieceX;
            y -= pieceY;
            if(inBounds(x, y) && x < piece.getWidth() && y < piece.getHeight())
            {
                TetrisCell temp = piece.getCell(x, y);
                if(!temp.isEmpty())
                {
                    result = temp;
                }
            }
        }
        
        return result;
    }
    
    /**
     * Checks if the tetromino is overlapping a placed block
     * 
     * @return true if overlapping; false otherwise
     */
    public boolean isOverlapping()
    {
        boolean[] result = { false };
        piece.forEach((x, y, cell) ->
        {
            x += pieceX;
            y += pieceY;
            
            if(inBounds(x, y) && !super.getCell(x, y).isEmpty())
            {
                result[0] = true;
            }
        });
        return result[0];
    }
    
    
    
    //////////////////////////////////////
    //////////////////////////////////////
    //////////////////////////////////////
    
    
    
    /**
     * Set / change the current tetromino to place on the grid
     * 
     * @param piece     Tetromino to place on the grid
     */
    public void setTetromino(Tetromino piece)
    {
        setTetromino(piece, this.getWidth()/2 - piece.getWidth()/2, -piece.getHeight());
        while(!isInside() && moveDown()){ }
        while(isOverlapping()){ moveUp(); }
    }
    
    /**
     * Set / change the current tetromino and place it at the specified
     * x, y coordinates
     * 
     * @param piece     Tetromino to place on the grid
     * @param x         x coordinate of tetromino
     * @param y         y coordinate of tetromino
     */
    public void setTetromino(Tetromino piece, int x, int y)
    {
        this.piece = piece;
        pieceX = x;
        pieceY = y;
    }
    
    /**
     * Retrieve the current tetromino on the board
     * 
     * @return  current tetromino
     */
    public Tetromino getTetromino()
    {
        return piece;
    }
    
    /**
     * Pastes the blocks of the tetromino into the grid and checks whether
     * any of the blocks in the tetromino are outside of the grid or not. 
     * Pasting outside of the grid implies a game over condition.
     * 
     * @return  true if block in tetromino is outside grid bounds;
     *          false otherwise
     */
    public boolean pasteTetromino()
    {
        boolean[] outOfBounds = {false};
        piece.forEach((x, y, cell) -> 
        {
            x += pieceX;
            y += pieceY;
            if(inBounds(x, y))
            {
                setCell(x, y, cell);
            }
            else
            {
                outOfBounds[0] = true;
            }
        });
        
        return outOfBounds[0];
    }
    
    /**
     * Rotate the tetromino counter-clockwise;
     * the action may fail if the result causes the tetromino to overlap
     * a filled cell or lie outside of the bounds of the grid
     * 
     * @return  true if action was successful; false otherwise
     */
    public boolean rotateLeft()
    {
        piece.rotateLeft();
        if(isIllegalMove())
        {
            piece.rotateRight();
            return false;
        }
        return true;
    }
    
    /**
     * Rotate the tetromino clockwise;
     * the action may fail if the result causes the tetromino to overlap
     * a filled cell or lie outside of the bounds of the grid
     * 
     * @return  true if action was successful; false otherwise
     */
    public boolean rotateRight()
    {
        piece.rotateRight();
        if(isIllegalMove())
        {
            piece.rotateLeft();
            return false;
        }
        return true;
    }
    
    /**
     * Rotate the tetromino left one position;
     * the action may fail if the result causes the tetromino to overlap
     * a filled cell or lie outside of the bounds of the grid
     * 
     * @return  true if action was successful; false otherwise
     */
    public boolean moveLeft()
    {
        pieceX -= 1;
        if(isIllegalMove())
        {
            pieceX += 1;
            return false;
        }
        return true;
    }
    
    /**
     * Rotate the tetromino right one position;
     * the action may fail if the result causes the tetromino to overlap
     * a filled cell or lie outside of the bounds of the grid
     * 
     * @return  true if action was successful; false otherwise
     */
    public boolean moveRight()
    {
        pieceX += 1;
        if(isIllegalMove())
        {
            pieceX -= 1;
            return false;
        }
        return true;
    }
    
    /**
     * Rotate the tetromino up one position;
     * the action may fail if the result causes the tetromino to overlap
     * a filled cell or lie outside of the bounds of the grid
     * 
     * @return  true if action was successful; false otherwise
     */
    public boolean moveUp()
    {
        pieceY -= 1;
        if(isIllegalMove())
        {
            pieceY += 1;
            return false;
        }
        return true;
    }
    
    /**
     * Rotate the tetromino down one position;
     * the action may fail if the result causes the tetromino to overlap
     * a filled cell or lie outside of the bounds of the grid
     * 
     * @return  true if action was successful; false otherwise
     */
    public boolean moveDown()
    {
        pieceY += 1;
        if(isIllegalMove())
        {
            pieceY -= 1;
            return false;
        }
        return true;
    }
    
    /**
     * Move the tetromino down until it hits the bottom of the grid or a 
     * filled cell
     * 
     * @return  number of positions that tetromino moved downwards
     */
    public int drop()
    {
        int count = 0;
        while(moveDown()){ count += 1; }
        return count;
    }
    
    
    
    //////////////////////////////////////
    //////////////////////////////////////
    //////////////////////////////////////
    
    
    
    /**
     * Checks if the last change to the tetromino was illegal, i.e. it is now
     * overlapping a filled cell or lies outside of the bounds of the grid
     * 
     * @return true if illegal move; false otherwise
     */
    private boolean isIllegalMove()
    {
        return isOverlapping() || isOutside();
    }
    
    /**
     * Checks if the tetromino currently lies outside the bounds of the grid, 
     * whether partially or fully does not matter.
     * 
     * @return true if tetromino lies outside of grid; false otherwise
     */
    private boolean isOutside()
    {
        boolean[] result = { false };
        piece.forEach((x, y, cell) ->
        {
            int nx = x + pieceX;
            int ny = y + pieceY;
            if(nx < 0 || nx >= getWidth() ||
               ny >= getHeight())
            {
                result[0] = true;
            }
        });
        return result[0];
    }
    
    /**
     * Checks if the tetromino is FULLY inside the bounds of the grid and none
     * of it's cells lie outside
     * 
     * @return true if tetromino is within bounds of grid; false otherwise
     */
    private boolean isInside()
    {
        boolean[] result = { false };
        piece.forEach((x, y, cell) ->
        {
            int nx = x + pieceX;
            int ny = y + pieceY;
            if(0 <= nx && nx < getWidth() &&
               0 <= ny && ny < getHeight())
            {
                result[0] = true;
            }
        });
        return result[0];
    }
    
    /**
     * Checks if the tetromino is above the top of the grid, whether partially
     * or fully does not matter.
     * 
     * @return true if tetromino lies above the grid; false otherwise
     */
    private boolean isAbove()
    {
        boolean[] result = { false };
        piece.forEach((x, y, cell) ->
        {
            if(y + pieceY < 0)
            {
                result[0] = true;
            }
        });
        return result[0];
    }
}
