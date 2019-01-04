/*********************************************************************************
*     File Name           :     Tile.java
*     Created By          :     The LO43 Katane team
*     Creation Date       :     [2018-09-14 13:32]
*     Last Modified       :     [2019-01-04 03:25]
*     Description         :     Class to gather the list of tiles
**********************************************************************************/

package Katane;

import java.util.ArrayList;

/* Class Tile */
public class Tile {

	private ArrayList<Building> building;
	private Ressource ressource;
	private int tileNumber;

	/* Constructor */
	public Tile() {
		System.out.println("-- Tile --");
		building = new ArrayList<Building>();
		ressource = generateRandomRessource(); // random from 0 to 4
		tileNumber = (int) (12 * Math.random() + 1); // random from 1 to 12
	}

	/* Add a building to the tile list */
	public void addBuilding(Building building) {
		this.building.add(building);
	}

	public Ressource generateRandomRessource () {
		Ressource ressource;
		switch ((int) (5 * Math.random())) {
			case 0:
				ressource = new Brick();
				break;
			case 1:
				ressource = new Grain();
				break;
			case 2:
				ressource = new Ore();
				break;
			case 3:
				ressource = new Wood();
				break;
			case 4:
				ressource = new Wool();
				break;
			default:
				ressource = new Brick();
				break;
		}
		return ressource;
	}

	// TEST
	public void print() {
		System.out.println("Tile" + ressource.toString());
	}
}

