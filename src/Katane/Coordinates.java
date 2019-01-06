/*********************************************************************************
*     File Name           :     Coordinates.java
*     Created By          :     The LO43 project Katane
*     Creation Date       :     [2018-12-29 05:55]
*     Last Modified       :     [2019-01-06 04:04]
*     Description         :     A class that corresponds to the coordinates on the map
**********************************************************************************/

package Katane;

import java.util.ArrayList;

/* Class Coordinates, is an array of integer.
 * It stores something like "(x, y)"
 */
public class Coordinates {

	private int x, y;

	/* Default Constructor */
	public Coordinates () {
		setCoordinates(0, 0);
	}

	/* Constructor with direct values */
	public Coordinates (int x, int y) {
		setCoordinates(x, y);
	}

	/* Constructor by copy */
	public Coordinates (Coordinates coor) {
		setCoordinates(coor);
	}

	public boolean equals (Coordinates coor) {
		return ( (x == coor.getXCoordinate()) && (y == coor.getYCoordinate()) );
	}

	public String toString () {
		return "(" + x + ", " + y + ")";
	}

	/* Set manually the coordinates with 2 integers */
	public void setCoordinates (int x, int y) {
		this.x = x;
		this.y = y;
	}

	/* Set manually the coordinates by copying an Coordinate */
	public void setCoordinates (Coordinates coor) {
		setCoordinates( coor.getXCoordinate(), coor.getYCoordinate());
	}

	/* Get the X coordinate */
	public Integer getXCoordinate () {
		return x;
	}

	/* Get the Y coordinate */
	public Integer getYCoordinate () {
		return y;
	}

	/* Increment the coordinates by a given x and y. */
	public void incrementCoordinates (int x, int y) {
		setCoordinates(x + this.x, y + this.y);
	}

	/* Convert the coordinates of a road to a list of coordinates of adjacents towns */
	public ArrayList<Coordinates> roadToAdjacentTowns () {
		ArrayList<Coordinates> townCoordinatesList = new ArrayList<Coordinates>();
		Coordinates coor;
		int k = y - y/3;
		if (y % 3 == 0) {
			coor = new Coordinates(x, k);
			townCoordinatesList.add(coor);
			coor = new Coordinates(x + 1, k - 1);
			townCoordinatesList.add(coor);
		} else {
			coor = new Coordinates(x, k - 1);
			townCoordinatesList.add(coor);
			coor = new Coordinates(x, k);
			townCoordinatesList.add(coor);
		}
		return townCoordinatesList;
	}

	/* Convert the coordinates of a town to a list of coordinates of adjacents roads */
	public ArrayList<Coordinates> townToAdjacentRoads () {
		ArrayList<Coordinates> townCoordinatesList = new ArrayList<Coordinates>();
		Coordinates coor;
		int k;
		if (y % 2 == 0) {
			k = 2 * y - y/2;
			coor = new Coordinates(x, k - 1);
			townCoordinatesList.add(coor);
			coor = new Coordinates(x, k + 1);
			townCoordinatesList.add(coor);
			coor = new Coordinates(x, k);
			townCoordinatesList.add(coor);
		} else {
			if (y > 0) {
				k = 2 * y - (y/2);
			} else {
				k = 2 * y - ((y-1)/2);
			}
			coor = new Coordinates(x, k - 1);
			townCoordinatesList.add(coor);
			coor = new Coordinates(x, k);
			townCoordinatesList.add(coor);
			coor = new Coordinates(x - 1, k + 1);
			townCoordinatesList.add(coor);
		}
		return townCoordinatesList;
	}

	/* Convert the coordinates of a road to a list of coordinates of adjacents roads */
	public ArrayList<Coordinates> roadToAdjacentRoads () {
		ArrayList<Coordinates> townCoordinatesList = new ArrayList<Coordinates>();
		Coordinates coor;
		if (y % 3 == 0) {
			coor = new Coordinates(x+1, y - 2);
			townCoordinatesList.add(coor);
			coor = new Coordinates(x+1, y - 1);
			townCoordinatesList.add(coor);
			coor = new Coordinates(x, y - 1);
			townCoordinatesList.add(coor);
			coor = new Coordinates(x, y + 1);
			townCoordinatesList.add(coor);
		} else  {
			if ( (y-1) % 3 == 0) {
				coor = new Coordinates(x, y - 1);
				townCoordinatesList.add(coor);
				coor = new Coordinates(x, y - 2);
				townCoordinatesList.add(coor);
				coor = new Coordinates(x, y + 1);
				townCoordinatesList.add(coor);
				coor = new Coordinates(x - 1, y + 2);
				townCoordinatesList.add(coor);
			} else {
				coor = new Coordinates(x, y - 1);
				townCoordinatesList.add(coor);
				coor = new Coordinates(x - 1, y + 1);
				townCoordinatesList.add(coor);
				coor = new Coordinates(x, y + 1);
				townCoordinatesList.add(coor);
				coor = new Coordinates(x, y + 2);
				townCoordinatesList.add(coor);
			}
		}
		return townCoordinatesList;
	}

	/* Convert the coordinates of a town  to a list of coordinates of adjacents towns */
	public ArrayList<Coordinates> townToAdjacentTowns () {
		ArrayList<Coordinates> townCoordinatesList = new ArrayList<Coordinates>();
		Coordinates coor;
		if (y % 2 == 0) {
			coor = new Coordinates(x, y + 1);
			townCoordinatesList.add(coor);
			coor = new Coordinates(x, y - 1);
			townCoordinatesList.add(coor);
			coor = new Coordinates(x+1, y - 1);
			townCoordinatesList.add(coor);
		} else {
			coor = new Coordinates(x, y - 1);
			townCoordinatesList.add(coor);
			coor = new Coordinates(x, y + 1);
			townCoordinatesList.add(coor);
			coor = new Coordinates(x - 1, y + 1);
			townCoordinatesList.add(coor);
		}
		return townCoordinatesList;
	}

	public ArrayList<Coordinates> townToAdjacentTiles () {
		ArrayList<Coordinates> townCoordinatesList = new ArrayList<Coordinates>();
		Coordinates coor;
		int k;
		if (y % 2 == 0) {
			k = -y/2;
			coor = new Coordinates(k, x);
			townCoordinatesList.add(coor);
			coor = new Coordinates(k + 1 , x);
			townCoordinatesList.add(coor);
			coor = new Coordinates(k, x - 1);
			townCoordinatesList.add(coor);
		} else {
			k = -(y - 1)/2;
			coor = new Coordinates(k, x);
			townCoordinatesList.add(coor);
			coor = new Coordinates(k, x - 1);
			townCoordinatesList.add(coor);
			coor = new Coordinates(k - 1 , x - 1);
			townCoordinatesList.add(coor);
		}
		return townCoordinatesList;
	}


	// TEST some debug printing
	public void print () {
		System.out.println("(" + x + ", " + y + ")");
	}
}

