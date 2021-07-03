package test;
import data.NotATriangleException;
import static org.junit.Assert.*;

import org.junit.Test;

import data.NotATriangleException;

/**
 * Class to test Triangle calculations
 */
public class TriangleTest {
	
	@Test(expected=NotATriangleException.class)
	public void testTriangleA() throws NotATriangleException {
		data.Triangle.calcArea(0,3,4);
	}
	
	@Test(expected=NotATriangleException.class)
	public void testTriangleB() throws NotATriangleException {
		data.Triangle.calcArea(2,0,4);
	}
	
	@Test(expected=NotATriangleException.class)
	public void testTriangleC() throws NotATriangleException {
		data.Triangle.calcArea(2,3,0);
	}
	
	@Test
	public void testTriangleDefault() throws NotATriangleException {
		data.Triangle.calcArea(1,1,5);
	}
	
	@Test
	public void testCalcArea() throws NotATriangleException{
		assertEquals(2.905,data.Triangle.calcArea(2, 3, 4), 0.1);
		assertEquals(9.052,data.Triangle.calcArea(2, 10, 11), 0.1);
		assertEquals(103.645,data.Triangle.calcArea(15, 15, 25), 0.1);
		assertEquals(1.732,data.Triangle.calcArea(2, 2, 2), 0.1);
	}
    


}
