/*
    Michael Irizarry (miriza6@uic.edu)
    Tetris (Project 5)
    CS 342 (Software Design), Spring 2016
    University of Illinois at Chicago
*/
package tetris;

import java.util.function.*;

/**
 * The bottommost layer of the grid which contains permanent blocks in the grid
 */
public class TetrisGrid
{
    // Instance variables
    private final int width, height;
    private final TetrisCell[][] grid;
    
    /**
     * Constructs a new instance of TetrisGrid with the specified parameters
     * 
     * @param width     Number of columns in grid
     * @param height    Number of rows in grid
     */
    public TetrisGrid(int width, int height)
    {
        this.width = width;
        this.height = height;
        grid = new TetrisCell[width][height];
        resetGrid();
    }
    
    /**
     * Copy constructor which references the state of another TetrisGrid
     * 
     * @param other     Reference to existing TetrisGrid instance
     */
    public TetrisGrid(TetrisGrid other)
    {
        this(other.width, other.height);
        other.forEach((x, y, cell) -> this.setCell(x, y, cell));
    }
    
    /**
     * Retrieve the number of columns in the grid
     * 
     * @return  number of columns
     */
    public int getWidth()
    {
        return width;
    }
    
    /**
     * Retrieve the number of rows in the grid
     * 
     * @return  number of rows
     */
    public int getHeight()
    {
        return height;
    }
    
    /**
     * Retrieve the state of a cell in the grid
     * 
     * @param x     x coordinate of cell
     * @param y     y coordinate of cell
     * @return      TetrisCell instance representing state of cell
     */
    public TetrisCell getCell(int x, int y)
    {
        return getCellFinal(x, y);
    }
    
    /**
     * The base version of getCell() which cannot be overriden by subclasses
     * 
     * @param x     x coordinate of cell
     * @param y     y coordinate of cell
     * @return      TetrisCell instance representing state of cell
     */
    private final TetrisCell getCellFinal(int x, int y)
    {
        return grid[x][y];
    }
    
    /**
     * Set the state of a cell in the grid
     * 
     * @param x     x coordinate of cell
     * @param y     y coordinate of cell
     * @param cell  the state to set this cell to
     */
    public void setCell(int x, int y, TetrisCell cell)
    {
        grid[x][y] = cell;
    }
    
    /**
     * Set all cells in the grid to the same state
     * 
     * @param cell  the state to set all cells to
     */
    public void setAll(TetrisCell cell)
    {
        forEach((x, y, ignore) -> setCell(x, y, cell));
    }
    
    /**
     * Clears the grid setting all cells to blank and empty
     */
    public void resetGrid()
    {
        setAll(TetrisCell.EMPTY_CELL);
    }
    
    /**
     * Clears full rows and moves all cells above full rows are moved down N
     * positions relative to the number of full rows that were cleared below 
     * that cell
     * 
     * @return  Number of full rows that were cleared
     */
    public int clearLines()
    {
        int count = 0;
        
        for(int y = 0; y < height; ++y)
        {
            boolean isFull = true;
            
            for(int x = 0; x < width; ++x)
            {
                if(grid[x][y].isEmpty())
                {
                    isFull = false;
                    break;
                }
            }
            
            if(isFull)
            {
                count += 1;
                
                for(int y2 = y; y2 >= 1; --y2)
                {
                    for(int x2 = 0; x2 < width; ++x2)
                    {
                        setCell(x2, y2, getCellFinal(x2, y2-1));
                    }
                }
                
                for(int x = 0; x < width; ++x)
                {
                    setCell(x, 0, TetrisCell.EMPTY_CELL);
                }
            }
        }
        
        return count;
    }
    
    /**
     * Indicates if two separate TetrisGrid instances have one or more filled
     * cells at the same coordinate.
     * 
     * NOTE this function may not be used anywhere
     * 
     * @param other A separate instance of a TetrisGrid object
     * @return      true if grids overlap; false otherwise
     */
    public boolean isOverlapping(TetrisGrid other)
    {
        final int width = Math.min(this.width, other.width);
        final int height = Math.min(this.height, other.height);
        for(int x = 0; x < width; ++x) for(int y = 0; y < height; ++y)
        {
            if(this.getCell(x, y).isEmpty() || other.getCell(x, y).isEmpty())
            {
                continue;
            }
            return true;
        }
        return false;
    }
    
    /**
     * Returns a new TetrisGrid instance containing a copy of the grid in
     * this instance
     * 
     * @return  new instance of TetrisGrid
     */
    public TetrisGrid clone()
    {
        return new TetrisGrid(this);
    }
    
    /**
     * Iterates over each cell in the grid and passes it to the specified function
     * 
     * @param delegate  function which gets called for each cell
     */
    public void forEach(ForEachCallable delegate)
    {
        for(int x = 0; x < width; ++x) for(int y = 0; y < height; ++y)
        {
            delegate.call(x, y, getCell(x, y));
        }
    }
    
    /**
     * Iterates over each cell in the grid and passes it to the specified
     * function but only for cells in which the predicate returns true
     * 
     * @param delegate  Function which gets called for each cell
     *                  based on the predicate
     * @param pred      A predicate function which returns true or false
     *                  for a TetrisCell
     */
    public void forEach(ForEachCallable delegate, Predicate<TetrisCell> pred)
    {
        this.forEach((x, y, cell) -> {
            if(pred.test(cell))
            {
                delegate.call(x, y, cell);
            }
        });
    }
    
    /**
     * Iterates over two TetrisGrid instances in conjunction and passes each
     * cell in both grids to the specified function
     * 
     * NOTE this function may not be used anywhere
     * 
     * @param other     A second instance of a TetrisGrid object
     * @param delegate  Function which gets called for each cell
     */
    public void forEachDual(TetrisGrid other, ForEachDualCallable delegate)
    {
        for(int x = 0; x < width; ++x) for(int y = 0; y < width; ++y)
        {
            delegate.call(x, y, this.getCell(x, y), other.getCell(x, y));
        }
    }
    
    /**
     * Iterates over two TetrisGrid instances in conjunction and passes each
     * cell in both grids to the specified function but only for cells in which
     * the predicate returns true
     * 
     * @param other     A second instance of a TetrisGrid object
     * @param delegate  Function which gets called for each cell
     * @param pred      A predicate function which returns true or false
     *                  for a pair of TetrisCell objects
     */
    public void forEachDual(TetrisGrid other, ForEachDualCallable delegate, BiPredicate<TetrisCell, TetrisCell> pred)
    {
        forEachDual(other, (x, y, cell1, cell2) ->
        {
            if(pred.test(cell1, cell2))
            {
                delegate.call(x, y, cell1, cell2);
            }
        });
    }
    
    /**
     * Interface used as the delegate for forEach()
     */
    public interface ForEachCallable
    {
        public void call(int x, int y, TetrisCell cell);
    }
    
    /**
     * Interface used as the delegate for forEachDual()
     */
    public interface ForEachDualCallable
    {
        public void call(int x, int y, TetrisCell cell1, TetrisCell cell2);
    }
    
    /**
     * Determine whether the specified x, y coordinates are within the bounds
     * of the grid
     * 
     * @param x     x coordinate
     * @param y     y coordinate
     * @return      true if x, y is within bounds of grid; false otherwise
     */
    protected boolean inBounds(int x, int y)
    {
        return 0 <= x && x < getWidth() && 0 <= y && y < getHeight();
    }
}
