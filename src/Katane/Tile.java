/*********************************************************************************
*     File Name           :     Tile.java
*     Created By          :     The LO43 Katane team
*     Creation Date       :     [2018-09-14 13:32]
*     Last Modified       :     [2019-01-05 05:53]
*     Description         :     Class to gather the list of tiles
**********************************************************************************/

package Katane;

import java.util.ArrayList;

/* Class Tile */
public class Tile {

	private ArrayList<Town> townList;
	private Ressource ressource;
	private int tileNumber;

	/* Constructor */
	public Tile() {
		System.out.println("-- Tile --");
		townList = new ArrayList<Town>();
		ressource = generateRandomRessource(); // random from 0 to 4
		tileNumber = (int) (12 * Math.random() + 1); // random from 1 to 12
	}

	/* Add a the town to the list of adjacent towns */
	public void addTown(Town town) {
		this.townList.add(town);
	}

	public void replaceTown (Town tOld, Town tNew) {
		Town tSav;
		for ( Town t : townList ) {
			if (t == tOld) {
				tSav = t;
				townList.remove(tSav);
				townList.add(tNew);
				return;
			}
		}
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

