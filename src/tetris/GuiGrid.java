/*
    Michael Irizarry (miriza6@uic.edu)
    Tetris (Project 5)
    CS 342 (Software Design), Spring 2016
    University of Illinois at Chicago
*/
package tetris;

import java.awt.*;
import java.awt.image.*;
import java.util.*;
import java.util.logging.*;
import javax.swing.*;
import javax.swing.border.*;
import tetris.Graphics;

/**
 * A graphical representation of the Tetris grid
 */
public class GuiGrid extends JPanel
{
    // Two-dimensional array of JLabels is used to display the grid graphically
    private final JLabel[][] grid;
    
    // Array of character IDs used for caching purposes so that only cells which
    // have changed are redrawn
    private final char[][] gridID;
    
    // Caches the graphics resized to the specified cellWidth and cellHeight
    private final Map<Integer, ImageIcon> iconCache;
    
    // These variables tores the dimensions of the grid
    private final int columns, rows, cellWidth, cellHeight;
    
    /**
     * Constructs a new GuiGrid with the specified parameters;
     * the cellWidth and cellHeight will be 20x20.
     * 
     * @param width     Number of columns
     * @param height    Number of rows
     */
    public GuiGrid(int width, int height)
    {
        this(width, height, 20, 20);
    }
    
    /**
     * Constructs a new GuiGrid with the specified parameters.
     * 
     * @param width         Number of columns
     * @param height        Number of rows
     * @param cellWidth     Width of each cell in pixels
     * @param cellHeight    Height of each cell in pixels
     */
    public GuiGrid(int width, int height, int cellWidth, int cellHeight)
    {
        super();
        super.setBorder(new EmptyBorder(20, 20, 20, 20));
        
        this.cellWidth = cellWidth;
        this.cellHeight = cellHeight;
        this.columns = width;
        this.rows = height;
        
        super.setLayout(new GridLayout(height, width));
        grid = new JLabel[width][height];
        
        for(int y = 0; y < height; ++y) for(int x = 0; x < width; ++x) 
        {
            grid[x][y] = new JLabel();
            super.add(grid[x][y]);
        }
        
        gridID = new char[width][height];
        
        iconCache = new HashMap<>();
        
        reset();
    }
    
    /**
     * Fills the grid with empty cells
     */
    public void reset()
    {
        for(int x = 0; x < columns; ++x) for(int y = 0; y < rows; ++y)
        {
            try
            {
                setCell(x, y, ' ');
            }
            catch (Exception ex)
            {
                Logger.getLogger(GuiGrid.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }
    
    /**
     * Retrieve the number of columns in the grid
     * 
     * @return  number of columns
     */
    public int countColumns()
    {
        return columns;
    }
    
    /**
     * Retrieve the number of rows in the grid
     * 
     * @return  number of rows
     */
    public int countRows()
    {
        return rows;
    }
    
    /**
     * Set the cell at x, y to the Tetromino block with the specified ID
     * 
     * @param x     x coordinate of cell
     * @param y     y coordinate of cell
     * @param id    Integer ID of tetromino block
     * @throws Exception 
     */
    public void setCell(int x, int y, int id) throws Exception
    {
        setCell(x, y, TetrisCell.indexToChar(id));
    }
    
    /**
     * Set the cell at x, y to the Tetromino block with the specified ID
     * 
     * @param x     x coordinate of cell
     * @param y     y coordinate of cell
     * @param c     Character ID of tetromino block
     * @throws Exception 
     */
    public void setCell(int x, int y, char c) throws Exception
    {
        if(gridID[x][y] == c) return;
        setCell(x, y, getCachedIcon(c));
        gridID[x][y] = c;
    }
    
    /**
     * Sets the cell at x, y using the 
     * 
     * @param x
     * @param y
     * @param icon 
     */
    private void setCell(int x, int y, ImageIcon icon)
    {
        grid[x][y].setIcon(icon);
        grid[x][y].setSize(icon.getIconWidth(), icon.getIconHeight());
    }
    
    /**
     * Conveniently caches the results of resizing graphics so they are only
     * resized once which greatly speeds up drawing the grid.
     * 
     * @param c     Character ID of the block
     * @return      ImageIcon of the Tetromino block resized to cellWidth*cellHeight
     */
    private ImageIcon getCachedIcon(char c)
    {
        ImageIcon icon = iconCache.get(c);
        
        if(icon == null)
        {
            try
            {
                icon = Graphics.getBlock(c);
            }
            catch (Exception ex)
            {
                Logger.getLogger(GuiGrid.class.getName()).log(Level.SEVERE, null, ex);
            }
            
            icon = new ImageIcon(icon.getImage().getScaledInstance(cellWidth, cellHeight, Image.SCALE_DEFAULT));
            iconCache.put((int)c, icon);
        }
        
        return icon;
    }
}
