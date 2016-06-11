/*
    Michael Irizarry (miriza6@uic.edu)
    Tetris (Project 5)
    CS 342 (Software Design), Spring 2016
    University of Illinois at Chicago
*/
package tetris;

import java.awt.*;
import java.util.logging.*;
import javax.swing.*;
import javax.swing.border.*;

/**
 * Separate class which encapsulates the right panel of the primary GUI
 */
public class GuiRightPanel extends JPanel
{
    // The height, in pixels, of each GuiCounter instance
    private final int COUNTER_HEIGHT = 30;
    
    // Instance variables
    private JLabel labelNextPiece;
    private JLabel nextPiece;
    private TetrisGridPiece nextPieceGrid;
    private JLabel labelScore;
    private GuiCounter score;
    private JLabel labelLevel;
    private GuiCounter level;
    private JLabel labelLines;
    private GuiCounter lines;
    private JLabel labelTimer;
    private GuiCounter timer;

    /**
     * Constructor which initializes the right panel of the GUI
     */
    public GuiRightPanel()
    {
        super();
        super.setLayout(new GridLayout(10, 2));
        super.setBorder(new EmptyBorder(20, 0, 20, 20));

        // Add timer
        labelTimer = new JLabel("Time Elapsed");
        labelTimer.setHorizontalAlignment(SwingConstants.CENTER);
        labelTimer.setVerticalAlignment(SwingConstants.CENTER);
        labelTimer.setFont(new Font(labelTimer.getFont().getName(), Font.BOLD, 40));
        timer = new GuiCounter(4, COUNTER_HEIGHT);

        // Add score
        labelScore = new JLabel("Score");
        labelScore.setHorizontalAlignment(SwingConstants.CENTER);
        labelScore.setVerticalAlignment(SwingConstants.CENTER);
        labelScore.setFont(labelTimer.getFont());
        score = new GuiCounter(8, COUNTER_HEIGHT);

        // Add lines counter
        labelLines = new JLabel("Lines Cleared");
        labelLines.setHorizontalAlignment(SwingConstants.CENTER);
        labelLines.setVerticalAlignment(SwingConstants.CENTER);
        labelLines.setFont(labelTimer.getFont());
        lines = new GuiCounter(4, COUNTER_HEIGHT);
        
        // Initialize level counter
        labelLevel = new JLabel("Level");
        labelLevel.setHorizontalAlignment(SwingConstants.CENTER);
        labelLevel.setVerticalAlignment(SwingConstants.CENTER);
        labelLevel.setFont(labelTimer.getFont());
        level = new GuiCounter(2, COUNTER_HEIGHT);
        
        // Add components for preview for next piece
        labelNextPiece = new JLabel("Next Piece");
        labelNextPiece.setHorizontalAlignment(SwingConstants.CENTER);
        labelNextPiece.setVerticalAlignment(SwingConstants.CENTER);
        labelNextPiece.setFont(labelTimer.getFont());
        nextPiece = new JLabel();
        nextPiece.setHorizontalAlignment(SwingConstants.CENTER);
        nextPiece.setVerticalAlignment(SwingConstants.CENTER);
        
        // Add components to GUI
        super.add(labelNextPiece);
        super.add(nextPiece);
        super.add(labelScore);
        super.add(score);
        super.add(labelLevel);
        super.add(level);
        super.add(labelLines);
        super.add(lines);
        super.add(labelTimer);
        super.add(timer);
    }
    
    /**
     * Set the tetromino image displayed in the "next piece preview"
     * 
     * @param c Character ID of the tetromino to display
     */
    public void setNextPiece(char c)
    {
        ImageIcon icon;
        
        try
        {
            icon = Graphics.getPiece(c);
            
            icon = new ImageIcon(
            icon.getImage()
            .getScaledInstance(
                icon.getIconWidth()*nextPiece.getHeight()/icon.getIconHeight()*2/3, 
                nextPiece.getHeight()*2/3, Image.SCALE_DEFAULT));
            
            nextPiece.setIcon(icon);
        }
        catch (Exception ex)
        {
            Logger.getLogger(GuiRightPanel.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    /**
     * Retrieve the graphical score display control in the right panel
     * 
     * @return GuiCounter instance for score
     */
    public GuiCounter getScore()
    {
        return score;
    }
    
    /**
     * Retrieve the graphical lines counter control in the right panel
     * 
     * @return GuiCounter instance for line count
     */
    public GuiCounter getLines()
    {
        return lines;
    }
    
    /**
     * Retrieve the graphical timer control in the right panel
     * 
     * @return GuiCounter instance for line count
     */
    public GuiCounter getTimer()
    {
        return timer;
    }
    
    /**
     * Retrieve the graphical level display control in the right panel
     * 
     * @return GuiCounter instance for the current
     */
    public GuiCounter getLevel()
    {
        return level;
    }
}