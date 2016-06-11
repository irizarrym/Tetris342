/*
    Michael Irizarry (miriza6@uic.edu)
    Tetris (Project 5)
    CS 342 (Software Design), Spring 2016
    University of Illinois at Chicago
*/
package tetris;

import java.awt.*;
import java.io.*;
import java.util.*;
import javax.swing.*;

/**
 * A graphical counter for use as a Swing GUI control
 */
public class GuiCounter extends JPanel
{
    // Height of the counter in pixels
    private final int pixelHeight;
    
    // Value of counter
    private int counter;
    
    // Array of JLabels used to render the individal digits in the counter
    private JLabel[] digits;
    
    /**
     * Constructs a new GuiCounter object with the specified parameters
     * 
     * @param numDigit  Number of visible digits
     * @param height    Height of the control in pixels
     */
    public GuiCounter(int numDigit, int height)
    {
        super();
        super.setLayout(new FlowLayout());
        pixelHeight = height;
        counter = 0;
        
        digits = new JLabel[numDigit];
        for(int i = 0; i < digits.length; ++i)
        {
            digits[i] = new JLabel();
            add(digits[i]);
        }
        
        updateDigits();
    }
    
    /**
     * Modify the value stored in the counter
     * 
     * @param value The new value of the counter
     */
    public void setValue(int value)
    {
        counter = value;
        updateDigits();
    }
    
    /**
     * Retrieve the value stored in the counter
     * 
     * @return Value stored by counter
     */
    public int getValue()
    {
        return counter;
    }
    
    /**
     * Add 1 to the value of the counter
     */
    public void increment()
    {
        setValue(getValue() + 1);
    }
    
    /**
     * Subtract 1 from the value of the counter
     */
    public void decrement()
    {
        setValue(getValue() - 1);
    }
    
    /**
     * Updates the array of JLabels to display the value stored in the counter
     */
    private void updateDigits()
    {
        int value = counter;
        if(value < 0) value = 0;
        for(int i = digits.length - 1; i >= 0; --i)
        {
            ImageIcon icon = Graphics.DIGITS[value%10];
            if(pixelHeight > 0)
            {
                int pixelWidth = icon.getIconWidth()*pixelHeight/icon.getIconHeight();
                icon = new ImageIcon(icon.getImage().getScaledInstance(pixelWidth, pixelHeight, Image.SCALE_DEFAULT));
            }
            digits[i].setIcon(icon);
            value /= 10;
        }
    }
}
