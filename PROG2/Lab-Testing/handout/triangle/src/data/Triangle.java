package data;
/*
 * Triangle calculations
 */
public class Triangle {

	/**
	 * Calculate area of the given Triangle.
	 *
	 * @param a lenght of side a of the triangle
	 * @param b lenght of side b of the triangle
	 * @param c lenght of side c of the triangle
	 * @return calculated area of the triangle
	 * @throws IllegalArgumentException, if invalid parameters are declared
	 */
	public static double calcArea(double a, double b, double c) throws NotATriangleException {
		double s = (a + b + c) / 2;
		double area;

		if ((a > 0) && (b > 0) && (c > 0)) {
			area = Math.sqrt(s * (s - a) * (s - b) * (s - c));
			System.out.println(area);
		} else {
			throw new NotATriangleException();
		}
		return area;

	}

}
