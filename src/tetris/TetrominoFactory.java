/*
    Michael Irizarry (miriza6@uic.edu)
    Tetris (Project 5)
    CS 342 (Software Design), Spring 2016
    University of Illinois at Chicago
*/
package tetris;

/**
 * A static class which uses the Factory Method pattern to create new instances
 * of Tetromino objects based on a character or integer ID
 */
public final class TetrominoFactory
{
    /**
     * Private constructor prevents creating new instances of this class
     */
    private TetrominoFactory(){ }
    
    /**
     * Create a new Tetromino object
     * 
     * @param c     character ID of tetromino
     * @return      new Tetromino instance
     * @throws Exception 
     */
    public static Tetromino create(char c) throws Exception
    {
        switch(c)
        {
        case 'I': case 'i':
            return new TetrominoI();
            
        case 'J': case 'j':
            return new TetrominoJ();
            
        case 'L': case 'l':
            return new TetrominoL();
            
        case 'O': case 'o':
            return new TetrominoO();
            
        case 'S': case 's':
            return new TetrominoS();
            
        case 'T': case 't':
            return new TetrominoT();
            
        case 'Z': case 'z':
            return new TetrominoZ();
            
        default:
            throw new Exception("Invalid ID: " + c);
        }
    }
    
    /**
     * Create a new Tetromino object
     * 
     * @param id    integer ID of tetromino
     * @return      new Tetromino instance
     * @throws Exception 
     */
    public static Tetromino create(int id) throws Exception
    {
        return create(TetrisCell.indexToChar(id));
    }
}