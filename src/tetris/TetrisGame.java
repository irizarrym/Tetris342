/*
    Michael Irizarry (miriza6@uic.edu)
    Tetris (Project 5)
    CS 342 (Software Design), Spring 2016
    University of Illinois at Chicago
*/
package tetris;

import java.awt.event.*;
import java.util.logging.*;
import javax.swing.*;

/**
 * Backend for the game of Tetris
 */
public class TetrisGame
{
    // Instance variables
    private TetrisEvent listener;
    private final TetrisGridPiece grid;
    private Tetromino nextPiece = null;
    private Tetromino[] pieceQueue = new Tetromino[TetrisCell.maxID()*3];
    private int queueIndex = 0;
    private boolean paused = false;
    private boolean gameOver = false;
    
    // Game Instance Variables
    private int currentLevel = 0;
    private int linesCleared = 0;
    private int score = 0;
    private int timer = 0;
    
    // "Block Fall" instance variables
    private BlockFallTimer blockFall;
    private int sleepTime = 500;
    private boolean doNotMove = false;
    
    /**
     * Constructor which initializes an instance of this class
     * 
     * @param listenerParam     A frontend which receives events or commands
     * @param gridParam         An instance of a TetrisGridPiece object
     */
    public TetrisGame(TetrisEvent listenerParam, TetrisGridPiece gridParam)
    {
        this.listener = listenerParam;
        this.grid = gridParam;
        newQueue();
        nextTetromino();
        GameTimer timer = new GameTimer();
        blockFall = new BlockFallTimer();
    }
    
    /**
     * Set or change the frontend that receives events from the backend
     * 
     * @param listenerParam Frontend listening for events
     */
    public void setListener(TetrisEvent listenerParam)
    {
        listener = listenerParam;
    }
    
    /**
     * Reset or start a new game
     */
    public void newGame()
    {
        paused = false;
        gameOver = false;
        currentLevel = 0;
        linesCleared = 0;
        score = 0;
        timer = 0;
        doNotMove = false;
        grid.resetGrid();
        newQueue();
        nextTetromino();
        update();
    }
    
    /**
     * Pause or resume the game
     * 
     * @param pauseParam true to pause the game; false to resume the game
     */
    public void setPaused(boolean pauseParam)
    {
        paused = pauseParam;
    }
    
    /**
     * Check whether the game is paused or not
     * 
     * @return true if paused; false otherwise
     */
    public boolean isPaused()
    {
        return paused;
    }
    
    /**
     * Check whether the game is over or not;
     * call newGame() to set the Game Over state to false.
     * 
     * @return true if game over; false otherwise
     */
    public boolean isGameOver()
    {
        return gameOver;
    }
    
    /**
     * Retrieve the tetromino that is coming up next
     * 
     * @return Tetromino that is coming up next
     */
    public Tetromino getNextPiece()
    {
        return nextPiece;
    }
    
    /**
     * Retrieve the score of the current game
     * 
     * @return score
     */
    public int getScore()
    {
        return score;
    }
    
    /**
     * Retrieve the number of lines cleared in the current game
     * 
     * @return lines cleared
     */
    public int getLines()
    {
        return linesCleared;
    }
    
    /**
     * Retrieve the number of seconds elapsed in the current game
     * 
     * @return seconds elapsed
     */
    public int getTimer()
    {
        return timer;
    }
    
    /**
     * Retrieve the level (difficulty) of the current game
     * 
     * @return level of current game
     */
    public int getLevel()
    {
        return currentLevel;
    }
    
    /**
     * Move the current tetromino one position to the left
     */
    public void moveLeft()
    {
        if(isPaused() || isGameOver())
        {
            return;
        }
        
        if(grid.moveLeft())
        {
            doNotMove = true;
            update();
        }
    }
    
    /**
     * Move the current tetromino one position to the right
     */
    public void moveRight()
    {
        if(isPaused() || isGameOver())
        {
            return;
        }
        
        if(grid.moveRight())
        {
            doNotMove = true;
            update();
        }
    }
    
    /**
     * Move the current tetromino one position down
     */
    public void moveDown()
    {
        if(isPaused() || isGameOver())
        {
            return;
        }
        
        if(grid.moveDown())
        {
            doNotMove = true;
            update();
        }
        else
        {
            if(gameOver = grid.pasteTetromino())
            {
                listener.tetrisGameOver();
            }
            
            nextTetromino();
            addScore(grid.clearLines());
            update();
        }
    }
    
    /**
     * Move the current tetromino down as far as it can go until it lands on
     * the bottom of the grid or another piece
     */
    public void dropPiece()
    {
        if(isPaused() || isGameOver())
        {
            return;
        }
        
        grid.drop();
        
        if(gameOver = grid.pasteTetromino())
        {
            listener.tetrisGameOver();
        }
        
        nextTetromino();
        addScore(grid.clearLines());
        update();
    }
    
    /**
     * Rotate the current tetromino counter-clockwise
     */
    public void rotateLeft()
    {
        if(isPaused() || isGameOver())
        {
            return;
        }
        
        if(grid.rotateLeft())
        {
            doNotMove = true;
            update();
        }
    }
    
    /**
     * Rotate the current tetromino clockwise
     */
    public void rotateRight()
    {
        if(isPaused() || isGameOver())
        {
            return;
        }
        
        if(grid.rotateRight())
        {
            doNotMove = true;
            update();
        }
    }
    
    /**
     * Set the next piece as the current tetromino and retrieve a new
     * tetromino to be set as the next piece
     */
    private void nextTetromino()
    {
        if(queueIndex == pieceQueue.length)
        {
            newQueue();
        }
        
        if(nextPiece == null)
        {
            nextPiece = pieceQueue[queueIndex++];
        }
        
        grid.setTetromino(nextPiece);
        nextPiece = pieceQueue[queueIndex++];
    }
    
    /**
     * Refreshes the random pool for upcoming tetrominoes
     */
    private void newQueue()
    {
        // Fill queue with fresh tetrominoes
        for(int i = 0; i < pieceQueue.length; ++i)
        {
            try
            {
                pieceQueue[i] = TetrominoFactory.create(i%TetrisCell.maxID());
            }
            catch (Exception ex)
            {
                Logger.getLogger(TetrisGame.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        
        // Shuffle queue of tetrominoes
        for(int a = 0; a < pieceQueue.length; ++a)
        {
            Tetromino t = pieceQueue[a];
            int b = (int)(Math.random()*pieceQueue.length);
            pieceQueue[a] = pieceQueue[b];
            pieceQueue[b] = t;
        }
        
        // Reset queue index
        queueIndex = 0;
    }
    
    /**
     * Increase the score based on the number of lines cleared
     * 
     * @param lines Number of lines cleared
     */
    private void addScore(int lines)
    {
        switch(lines)
        {
        case 1:
            score += 40*currentLevel;
            break;
        case 2:
            score += 100*currentLevel;
            break;
        case 3:
            score += 300*currentLevel;
            break;
        case 4:
            score += 1200*currentLevel;
            break;
        default:
            break;
        }

        linesCleared += lines;
        currentLevel = linesCleared/10 + 1;
    }
    
    /**
     * Asks the frontend to update the display because a change has occurred
     */
    private synchronized void update()
    {
        if(listener != null)
        {
            listener.tetrisUpdate();
        }
    }
    
    /**
     * A timer which runs at varying intervals and moves the current tetromino
     * down one position on every trigger
     */
    private class BlockFallTimer implements ActionListener
    {
        private Timer blockFallTimer;
        
        public BlockFallTimer()
        {
            super();
            blockFallTimer = new Timer(getDelay(), this);
            blockFallTimer.setRepeats(false);
            blockFallTimer.start();
        }
        
        @Override
        public void actionPerformed(ActionEvent e)
        {
            if(!paused && !gameOver && !doNotMove)
            {
                moveDown();
            }

            if(doNotMove)
            {
                doNotMove = false;
            }

            blockFallTimer.setDelay(getDelay());
            blockFallTimer.start();
        }
        
        private int getDelay()
        {
            int pseudoLevel = Math.min(currentLevel, 24);
            return (50 - 2*pseudoLevel)*1000/60;
        }
    }
    
    /**
     * A Swing Timer which increments the game timer once every second
     */
    private class GameTimer implements ActionListener
    {
        private Timer gameTimer;
        
        public GameTimer()
        {
            gameTimer = new Timer(1000, this);
            gameTimer.start();
        }

        @Override
        public void actionPerformed(ActionEvent e)
        {
            if(!paused && !gameOver)
            {
                timer += 1;
                update();
            }
        }
    }
}