/*********************************************************************************
*     File Name           :     Coordinates.java
*     Created By          :     The LO43 project Katane
*     Creation Date       :     [2018-12-29 05:55]
*     Last Modified       :     [2018-12-30 17:26]
*     Description         :     A class that corresponds to the coordinates on the map
**********************************************************************************/

package Katane;

/* Class Coordinates, is an array of integer.
 * It stores something like "(x, y)"
 */
public class Coordinates {

	private Integer[] coordinates;

	/* Default Constructor */
	public Coordinates () {
		coordinates = new Integer[2];
		setCoordinates(0, 0);
	}

	/* Constructor with direct values */
	public Coordinates (int x, int y) {
		coordinates = new Integer[2];
		setCoordinates(x, y);
	}

	/* Constructor by copy */
	public Coordinates (Coordinates coor) {
		coordinates = new Integer[2];
		setCoordinates(coor);
	}

	/* Set manually the coordinates with 2 integers */
	public void setCoordinates (int x, int y) {
		coordinates[0] = x;
		coordinates[1] = y;
	}

	/* Set manually the coordinates by copying an Coordinate */
	public void setCoordinates (Coordinates coor) {
		setCoordinates( (int) coor.getXCoordinate(), (int) coor.getYCoordinate());
	}

	/* Get the X coordinate */
	public Integer getXCoordinate () {
		return coordinates[0];
	}

	/* Get the Y coordinate */
	public Integer getYCoordinate () {
		return coordinates[1];
	}

	/* Increment the coordinates by a given x and y. */
	public void incrementCoordinates (int x, int y) {
		setCoordinates(x + coordinates[0], y + coordinates[1]);
	}

	// TEST some debug printing
	public void print () {
		System.out.println("(" + coordinates[0] + ", " + coordinates[1] + ")");
	}
}

