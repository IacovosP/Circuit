package application;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;
import org.junit.Before;
import org.junit.Test;
import junit.framework.TestCase;


public class JavaTest extends TestCase {
	protected String value1, value2;

	// assigning the values
	@Before 
	protected void setUp() {
		value1= "hello ";
		value2="world";
		aMatrix = new Matrix(5,5);
		rows = 5;
		columns = 5;
	}

	// test method to add two values
	public void testAdd() {
		value1 = value1.concat(value2);
		//System.out.println(value1);
		assertTrue(value1 == "hello world");
	}

	private Matrix aMatrix;
	int rows, columns;

	@Test (expected = MatrixException.class)
	public void testInvalidConstructorInputs_ArrayMatrix() throws MatrixException {
		new Matrix(0,5);
	}

	@Test (expected = MatrixException.class)
	public void testInvalidConstructorInputs_SparseMatrix() throws MatrixException {
		new Matrix(-7,0);
	}

	@Test
	public void theNumberOfRowsShouldBeFive() throws MatrixException {
		assertThat(aMatrix.getNumberOfRows(), is(5));
	}

	@Test
	public void theNumberOfColumnsShouldBeFive() throws MatrixException {
		assertThat(aMatrix.getNumberOfColumns(), is(5));
	}

	@Test
	public void theFirstElementShouldBeZero() throws MatrixException {
		assertThat(aMatrix.getElement(0,0), is(0.0));
	}

	@Test
	public void theFirstElementShouldBeOne() throws MatrixException {
		aMatrix.setElement(0,0,1);
		assertThat(aMatrix.getElement(0,0), is(1.0));
	}
}