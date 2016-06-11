/*
    Michael Irizarry (miriza6@uic.edu)
    Tetris (Project 5)
    CS 342 (Software Design), Spring 2016
    University of Illinois at Chicago
*/
package tetris;

/**
 * Adds a drop shadow to an existing TetrisGridPiece indicating where the
 * tetromino will land if dropped
 */
public class TetrisGridPieceHighlight extends TetrisGridPiece
{
    /**
     * Initializes the base grid based on an existing TetrisGrid instance
     * 
     * @param other 
     */
    TetrisGridPieceHighlight(TetrisGrid other)
    {
        super(other);
    }
    
    /**
     * Retrieve the state of a cell in the grid; this is where the drop shadow
     * is drawn on top of the base grid but below the tetromino itself
     * 
     * @param x     x coordinate of cell
     * @param y     y coordinate of cell
     * @return      TetrisCell instance representing state of cell
     */
    @Override
    public TetrisCell getCell(int x, int y)
    {
        TetrisCell result = super.getCell(x, y);
        
        if(piece != null && result.isEmpty())
        {
            // Boolean which determines whether to highlight this cell or not
            boolean[] highlightCell = {false};
            
            // Backup current coordinates of tetromino
            final int oldPieceX = pieceX;
            final int oldPieceY = pieceY;

            // Drop piece to see where it lands
            super.drop();

            // Check if this is a highlighted cell
            piece.forEach((x2, y2, cell) ->
            {
                if(x == x2 + pieceX && y == y2 + pieceY)
                {
                    highlightCell[0] = true;
                }
            });

            // Restore coordinates of tetromino
            pieceX = oldPieceX;
            pieceY = oldPieceY;
            
            // If true, change this to a highlighted cell
            if(highlightCell[0])
            {
                result = TetrisCell.HIGHLIGHT_CELL;
            }
        }
        
        return result;
    }
}
