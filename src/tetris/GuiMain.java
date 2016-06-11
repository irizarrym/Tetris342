/*
    Michael Irizarry (miriza6@uic.edu)
    Tetris (Project 5)
    CS 342 (Software Design), Spring 2016
    University of Illinois at Chicago
*/
package tetris;

import java.awt.*;
import java.awt.event.*;
import java.util.*;
import java.util.logging.*;
import javax.swing.*;
import javax.swing.border.*;

/**
 * Singleton class for the main GUI/Window of the game
 */
public final class GuiMain extends JFrame implements TetrisEvent
{
    // Instance Variables
    private GuiGrid grid;
    private TetrisGridPieceHighlight backendGrid;
    private GuiRightPanel rightPanel;
    private MenuBar menuBar;
    private TetrisGame backend;
    private KeyboardInput keyInput;
    private JLabel statusBar;
    private static GuiMain instance = null;
    
    /**
     * Get instance of singleton
     * 
     * @return  Single instance of this class
     */
    public static GuiMain getInstance()
    {
        if(instance == null)
        {
            instance = new GuiMain();
        }
        
        return instance;
    }
    
    /**
     * Private constructor which initializes the GUI
     */
    private GuiMain()
    {
        // Basic GUI setup and configuration
        super("Tetris");
        super.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        super.setLayout(new BorderLayout());
        // super.setSize(640, 480);
        
        // Create GUI grid
        grid = new GuiGrid(10, 20, 30, 30);
        super.add(grid, BorderLayout.CENTER);
        grid.reset();
        
        // Create GUI right panel
        rightPanel = new GuiRightPanel();
        super.add(rightPanel, BorderLayout.EAST);
        
        // Create GUI Menu Bar
        menuBar = new MenuBar();
        super.add(menuBar.getMenu(), BorderLayout.NORTH);
        
        // Create GUI status bar message
        statusBar = new JLabel("(Move Tetromino: WASD or 1235)  "
            + "(Drop Tetromino: Spacebar or 0)  "
            + "(Rotate Tetromino: QE or 46)");
        statusBar.setHorizontalAlignment(SwingConstants.CENTER);
        super.add(statusBar, BorderLayout.SOUTH);
        
        // Initialize backend
        backendGrid = new TetrisGridPieceHighlight(new TetrisGrid(10, 20));
        backend = new TetrisGame(this, backendGrid);
        keyInput = new KeyboardInput(backend);
        this.addKeyListener(keyInput);
        this.setFocusable(true);
        this.requestFocus();
        
        // Display GUI
        super.setResizable(false);
        pack();
        super.setLocationRelativeTo(null); // Center window on screen
        // super.setVisible(true);
    }
    
    /**
     * Event received from the backend indicating a visible change has occurred
     * in the state of the game.
     */
    @Override
    public void tetrisUpdate()
    {
        backendGrid.forEach((x, y, cell) -> 
        {
            try
            {
                grid.setCell(x, y, cell.getID());
            }
            catch (Exception ex)
            {
                Logger.getLogger(GuiMain.class.getName()).log(Level.SEVERE, null, ex);
            }
        });
        
        rightPanel.setNextPiece(backend.getNextPiece().getID());
        
        rightPanel.getScore().setValue(backend.getScore());
        rightPanel.getLines().setValue(backend.getLines());
        rightPanel.getTimer().setValue(backend.getTimer());
        rightPanel.getLevel().setValue(backend.getLevel());
    }
    
    /**
     * Event listener received from the backend indicating a Game Over event
     */
    @Override
    public void tetrisGameOver()
    {
        JOptionPane.showMessageDialog(null, "Game Over!");
    }
    
    /**
     * Private class which encapsulates the menu bar for the GUI
     */
    private class MenuBar implements ActionListener
    {
        private JMenuBar menuBar;
        private JMenu menuGame;
        private JMenuItem menuNewGame;
        private JMenuItem menuPause;
        private JMenuItem menuExit;
        private JMenu menuHelp;
        private JMenuItem menuHelpHelp;
        private JMenuItem menuAbout;
        
        /**
         * Initialize the menu bar
         */
        public MenuBar()
        {
            menuBar = new JMenuBar();
            menuGame = new JMenu("Game");
            menuNewGame = new JMenuItem("New Game");
            menuPause = new JMenuItem("Pause");
            menuExit = new JMenuItem("Exit");
            menuHelp = new JMenu("Help");
            menuHelpHelp = new JMenuItem("Help");
            menuAbout = new JMenuItem("About");
            
            menuBar.add(menuGame);
            menuBar.add(menuHelp);
            
            menuGame.add(menuNewGame);
            menuGame.add(menuPause);
            menuGame.add(menuExit);
            
            menuHelp.add(menuHelpHelp);
            menuHelp.add(menuAbout);
            
            menuNewGame.addActionListener(this);
            menuPause.addActionListener(this);
            menuExit.addActionListener(this);
            menuHelpHelp.addActionListener(this);
            menuAbout.addActionListener(this);
        }
        
        /**
         * Retrieve the menu bar
         * 
         * @return  menu bar instance
         */
        public JMenuBar getMenu()
        {
            return menuBar;
        }

        /**
         * Event handler for a menu click event
         * 
         * @param e 
         */
        @Override
        public void actionPerformed(ActionEvent e)
        {
            if(e.getSource() == menuNewGame)
            {
                backend.newGame();
                menuPause.setText("Pause");
            }
            if(e.getSource() == menuPause)
            {
                backend.setPaused(!backend.isPaused());
                if(backend.isPaused())
                {
                    menuPause.setText("Resume");
                }
                else
                {
                    menuPause.setText("Pause");
                }
            }
            if(e.getSource() == menuExit)
            {
                System.exit(0);
            }
            if(e.getSource() == menuHelpHelp)
            {
                String title = "Tetris Help";
                String message =
                    "The game of tetris involves you, the player, arranging \n"
                    + "pieces composed of four blocks, called tetrominoes, to\n"
                    + "form full rows to score points. The more rows you clear\n"
                    + "with a single tetromino, the more points you gain. The\n"
                    + "speed at which tetrominoes fall increases with every ten\n"
                    + "rows cleared. The game is over if a tetromino lands but\n"
                    + "there is not enough space left in the grid.\n"
                    + "\n"
                    + "Scoring:\n"
                    + "1 row:   Level * 40 points\n"
                    + "2 rows:  Level * 100 points\n"
                    + "3 rows:  Level * 300 points\n"
                    + "4 rows:  Level * 1200 points\n"
                    + "\n"
                    + "Keys:\n"
                    + "Move tetromino: WASD (numpad: 1235)\n"
                    + "Drop tetromino: Spacebar (numpad: 0)\n"
                    + "Rotate left/right: QE (numpad: 46)";
                
                boolean paused = backend.isPaused();
                backend.setPaused(true);
                JOptionPane.showMessageDialog(null, message, title, 1);
                backend.setPaused(paused);
            }
            if(e.getSource() == menuAbout)
            {
                String title = "About this game";
                String message = "Written by Michael Irizarry\n"
                    + "miriza6@uic.edu\n"
                    + "\n"
                    + "Tetris (Project 5)\n"
                    + "CS 342 (Software Design), Spring 2016\n"
                    + "University of Illinois at Chicago";
                    
                boolean paused = backend.isPaused();
                backend.setPaused(true);
                JOptionPane.showMessageDialog(null, message, title, 1);
                backend.setPaused(paused);
            }
        }
    }
    
    /**
     * Entry point for the program
     * 
     * @param args  Command line arguments
     */
    public static void main(String[] args)
    {
        GuiMain instance = new GuiMain();
        instance.setVisible(true);
    }
}