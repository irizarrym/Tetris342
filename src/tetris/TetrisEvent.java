/*
    Michael Irizarry (miriza6@uic.edu)
    Tetris (Project 5)
    CS 342 (Software Design), Spring 2016
    University of Illinois at Chicago
*/
package tetris;

/**
 * An interface for the backend to send commands or notifications to the frontend
 */
interface TetrisEvent
{
    /**
     * Request the frontend to update the display
     */
    public void tetrisUpdate();
    
    /**
     * Event indicating that a Game Over event has occurred
     */
    public void tetrisGameOver();
}