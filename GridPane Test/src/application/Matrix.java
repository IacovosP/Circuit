package application;

import java.util.HashMap;

public class Matrix {
  
	private static class Index {
	  private int x = 0;
	  private int y = 0;
	  private int hashvalue = 0;
	  
	  public Index(final int x, final int y)
	  {
	    this.x = x;
	    this.y = y;
	    hashvalue = ((x +"") + (y + "")).hashCode();
	  }
	  
	  // Override equals and hashcode to ensure Index objects
	  // behave correctly when used as keys in a hash table.
	  
	  @Override
	  public boolean equals(final Object obj)
	  {
	    if (obj instanceof Index)
	    {
	      Index index = (Index) obj;
	      return ((x == index.x) && (y == index.y));
	    }
	    else 
	    {      
		  return false;
        }
	  }
	  
	  @Override
	  public int hashCode()
	  {
	    return hashvalue;
	  }
	}
	
  private HashMap<Index,Double> hashTable;
  private int rows, columns;
  
  public Matrix(final int rows, final int columns) 
  {
	  hashTable = new HashMap<Index,Double>();
	  this.rows = rows;
	  this.columns = columns;
  }
 
  public int getNumberOfRows()
  {
	  return rows;
  }
  
  public int getNumberOfColumns()
  {
	  return columns;
  }
  
  public double getElement(final int row, final int column) 
    throws MatrixException
  {
	  if (row < 0 || row >= getNumberOfRows() || column < 0 || column >= getNumberOfColumns()) {
		  throw new MatrixException(row, column);
	  }
	  Index index = new Index(row,column);
	  if (hashTable.containsKey(index))
	    return (double)hashTable.get(index);
	  else
	    return 0.0;
  }
  
  public void setElement(final int row, final int column, final double value)
    throws MatrixException
  {
	  if (row < 0 || row >= getNumberOfRows() || column < 0 || column >= getNumberOfColumns()) {
		  throw new MatrixException(row, column);
	  }
	  if (value != 0.0) {
		  hashTable.put(new Index(row,column), (Double)value);
		  hashTable.put(new Index(column,row), (Double)value);
	  }
	  else {
		  hashTable.remove(new Index(row,column));
	  }
  } 
}
