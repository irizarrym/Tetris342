/*
    Michael Irizarry (miriza6@uic.edu)
    Tetris (Project 5)
    CS 342 (Software Design), Spring 2016
    University of Illinois at Chicago
*/
package tetris;

/**
 * A meta-object which describes a single cell/block in the grid
 */
public class TetrisCell
{
    // Predefined constants for an empty and highlighted cell
    public static final TetrisCell EMPTY_CELL     = new TetrisCell(' ', true);
    public static final TetrisCell HIGHLIGHT_CELL = new TetrisCell('*', true);
    
    // Instance variables
    private static final String ID_SET = "IJLOSTZ";     // Not used yet...
    private final boolean empty;
    private final char id;
    
    /**
     * @param id        The character ID for this cell
     * @param empty     True if this is an empty cell; false otherwise
     */
    TetrisCell(char id, boolean empty)
    {
        this.id = id;
        this.empty = empty;
    }
    
    /**
     * Retrieve the character ID of this cell
     * 
     * @return  character ID
     */
    public char getID()
    {
        return id;
    }
    
    /**
     * Determine if this cell is empty or not
     * 
     * @return  true if empty; false if filled
     */
    public boolean isEmpty()
    {
        return empty;
    }
    
    /**
     * Indicates the number of predefined IDs for tetromino cells
     * 
     * @return  ID count
     */
    public static int maxID()
    {
        return ID_SET.length();
    }
    
    /**
     * Convert a character ID to an integer ID
     * 
     * @param c     Character ID of tetromino/cell
     * @return      Integer ID
     * @throws Exception If character index is undefined
     */
    public static int charToIndex(char c) throws Exception
    {
        switch(c)
        {
        case '*':
            return -2;
        case ' ':
            return -1;
        case 'O': case 'o':
            return 0;
        case 'I': case 'i':
            return 1;
        case 'S': case 's':
            return 2;
        case 'Z': case 'z':
            return 3;
        case 'L': case 'l':
            return 4;
        case 'J': case 'j':
            return 5;
        case 'T': case 't':
            return 6;
        default:
            throw new Exception("Invalid value for arugument; must be one of I, J, L, O, S, T, Z");
        }
    }
    
    /**
     * Convert an integer ID to a character ID
     * 
     * @param id    Integer ID of a tetromino/cell
     * @return      Character ID
     * @throws Exception If integer ID is out of range
     */
    public static char indexToChar(int id) throws Exception
    {
        switch(id)
        {
        case -2:
            return '*';
        case -1:
            return ' ';
        case 0:
            return 'O';
        case 1:
            return 'I';
        case 2:
            return 'S';
        case 3:
            return 'Z';
        case 4:
            return 'L';
        case 5:
            return 'J';
        case 6:
            return 'T';
        default:
            throw new Exception("Invalid value for argument; must be within range 0 <= id <= 6");
        }
    }
}
