/*
    Michael Irizarry (miriza6@uic.edu)
    Tetris (Project 5)
    CS 342 (Software Design), Spring 2016
    University of Illinois at Chicago
*/
package tetris;

import java.awt.*;
import java.awt.event.*;
import java.util.logging.*;

/**
 * A controller which listens for keyboard input and sends commands to the
 * Tetris backend (TetrisGame.class)
 */
public class KeyboardInput implements KeyListener
{
    // Instance variables
    private final TetrisGame backend;

    /**
     * Constructor which initializes a new instance of this class
     * 
     * @param game  Instance of Tetris game backend
     */
    public KeyboardInput(TetrisGame game)
    {
        backend = game;
    }

    /**
     * Listens for key events and sends commands to backend based on which
     * key is pressed
     * 
     * @param e Contains details of the key press event
     */
    @Override
    public void keyPressed(KeyEvent e)
    {
        switch (e.getKeyChar())
        {
        case KeyEvent.VK_LEFT:
        case KeyEvent.VK_A:
        case KeyEvent.VK_1:
        case 'a':
            backend.moveLeft();
            break;

        case KeyEvent.VK_RIGHT:
        case KeyEvent.VK_D:
        case KeyEvent.VK_3:
        case 'd':
            backend.moveRight();
            break;

        case KeyEvent.VK_DOWN:
        case KeyEvent.VK_S:
        case KeyEvent.VK_2:
        case 's':
            backend.moveDown();
            break;

        case KeyEvent.VK_SPACE:
        case KeyEvent.VK_0:
            backend.dropPiece();
            break;

        case KeyEvent.VK_COMMA:
        case KeyEvent.VK_Q:
        case KeyEvent.VK_4:
        case 'q':
            backend.rotateLeft();
            break;

        case KeyEvent.VK_PERIOD:
        case KeyEvent.VK_UP:
        case KeyEvent.VK_E:
        case KeyEvent.VK_W:
        case KeyEvent.VK_5:
        case KeyEvent.VK_6:
        case 'e':
        case 'w':
            backend.rotateRight();
            break;
        }
    }
    
    @Override
    public void keyTyped(KeyEvent e)
    {
    }

    @Override
    public void keyReleased(KeyEvent e)
    {
    }
}
