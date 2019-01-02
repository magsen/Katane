/*********************************************************************************
*     File Name           :     Tile.java
*     Created By          :     The LO43 Katane team
*     Creation Date       :     [2018-09-14 13:32]
*     Last Modified       :     [2018-12-30 17:33]
*     Description         :     Class to gather the list of tiles
**********************************************************************************/

package Katane;

import java.util.ArrayList;

/* Class Tile */
public class Tile {

	private ArrayList<Building> building;
	private int ressourceType; // Each number is associated with a ressource (e.g. Brick -> 0)
	private int tileNumber;

	/* Constructor */
	public Tile() {
		System.out.println("-- Tile --");
		building = new ArrayList<>();
		ressourceType = (int) (5 * Math.random()); // random from 0 to 4
		tileNumber = (int) (12 * Math.random() + 1); // random from 1 to 12
	}

	/* Add a building to the tile list */
	public void addBuilding(Building building) {
		this.building.add(building);
	}

	// TEST
	public void print() {
		System.out.println("Tile" + ressourceType);
	}
}

