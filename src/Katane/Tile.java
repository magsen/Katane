/*********************************************************************************
*     File Name           :     Tile.java
*     Created By          :     The LO43 Katane team
*     Creation Date       :     [2018-09-14 13:32]
*     Last Modified       :     [2018-12-26 17:55]
*     Description         :     Class to gather the list of tiles
**********************************************************************************/

package Katane;

import java.util.ArrayList;

/* Class Tile */
public class Tile {

	private ArrayList<Building> building;

	/* Constructor */
	public Tile() {
		System.out.println("-- Tile --");
		this.building = new ArrayList<>();
	}

	/* Add a building to the tile list */
	public void addBuilding(Building building) {
		this.building.add(building);
	}
}
