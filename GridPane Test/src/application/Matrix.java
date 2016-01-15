package application;

import java.util.HashMap;

public class Matrix {

	private static class Index {
		private int x = 0, y = 0;
		private int hashvalue = 0;


		public Index(final int x, final int y) {
			this.x = x;
			this.y = y;
			hashvalue = ((x +"") + (y + "")).hashCode();
		}


		@Override
		public boolean equals(final Object obj) {
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
		public int hashCode() {
			return hashvalue;
		}
	}

	private HashMap<Index,Double> hashTable;
	private HashMap<Integer,Integer> components;
	private int rows, columns, num, component;
	private int visited[] = new int[36];

	public Matrix(final int rows, final int columns) {
		hashTable = new HashMap<Index,Double>();
		components = new HashMap<Integer,Integer>();
		this.rows = rows;
		this.columns = columns;
	}

	public int getNumberOfRows() {
		return rows;
	}

	public int getNumberOfColumns() {
		return columns;
	}

	public double getElement(final int row, final int column) throws MatrixException {
		if (row < 0 || row >= getNumberOfRows() || column < 0 || column >= getNumberOfColumns()) {
			throw new MatrixException(row, column);
		}
		Index index = new Index(row,column);
		if (hashTable.containsKey(index))
			return (double)hashTable.get(index);
		else
			return 0.0;
	}

	public void setElement(final int row, final int column, final double value) throws MatrixException {
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

	public void findConnectedComponents() throws MatrixException {
		for (int i=0; i<36; i++) 
			if (visited[i] == 0) {
				depthFirstSearch(i, component);
				component++;
			}
	}
	
	private void depthFirstSearch(int n, int component) throws MatrixException {
		components.put(component, n);
		for (int i=0; i<6; i++) {
			if (getElement(n,i) == 1 && visited[n] == 0)
				depthFirstSearch(i, component);
		}			
	}
}
