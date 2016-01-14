package application;

/**
 * Exception subclass to represent exceptions thrown when working with matrix
 * objects. Copyright (c) 2015 UCL Computer Science
 * 
 * @author Graham Roberts
 * @version 1.1
 */

public class MatrixException extends Exception
{
  /**
   * Provide a custom message.
   * 
   * @param message
   *          The message to store in the exception object.
   */
  public MatrixException(String message)
  {
    super(message);
  }

  /**
   * Attempt made to access an invalid matrix element.
   * 
   * @param row
   *          Index of row
   * @param column
   *          Index of column
   */
  public MatrixException(int row, int column)
  {
    super("Attempt to access invalid element (" + row + "," + column + ")");
  }
}
