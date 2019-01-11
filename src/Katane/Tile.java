/*********************************************************************************
*     File Name           :     Tile.java
*     Created By          :     The LO43 Katane team
*     Creation Date       :     [2018-09-14 13:32]
*     Last Modified       :     [2019-01-05 16:04]
*     Description         :     Class to gather the list of tiles
**********************************************************************************/

package Katane;

import java.util.ArrayList;

/* Class Tile */
public class Tile {

	private ArrayList<Town> townList;
	private Ressource ressource;
	private int tileNumber;
	private boolean isDesert, isWater;
	private boolean brigand;

	/* Constructor */
	public Tile() {
		System.out.println("-- Tile --");
		townList = new ArrayList<Town>();
		ressource = generateRandomRessource(); // random from 0 to 4
		isDesert = false;
		isWater = false;
		brigand = false;
		do {
			tileNumber = (int) (12 * Math.random() + 1); // random from 1 to 12
			tileNumber = (int) (12 * Math.random() + 1); // random from 1 to 12
		} while (tileNumber == 7);
	}
	
	public Tile (boolean b) {
		super();
		if (b == true) {
			ressource = new Brick(0);
			isWater = true;
		}
	}
	
	public Tile (int i) {
		super();
		ressource = new Brick(0);
		isDesert = true;
	}

	/* Add a the town to the list of adjacent towns */
	public void addTown(Town town) {
		this.townList.add(town);
	}
	
	public int getTileNumber() {
		return tileNumber;
	}
	
	public boolean isDesert () {
		return isDesert;
	}
	
	public boolean isWater () {
		return isWater;
	}
	
	public Ressource getRessource() {
		return ressource;
	}

	public void addToTownList (Town t) {
		townList.add(t);
	}

	public void updateTownToTownList (Town tOld, Town tNew) {
		townList.remove(tOld);
		townList.add(tNew);
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
				ressource = new Brick(1);
				break;
			case 1:
				ressource = new Grain(1);
				break;
			case 2:
				ressource = new Ore(1);
				break;
			case 3:
				ressource = new Wood(1);
				break;
			case 4:
				ressource = new Wool(1);
				break;
			default:
				ressource = new Brick(1);
				break;
		}
		return ressource;
	}

	// TEST
	public void print() {
		System.out.println("Tile" + ressource.toString());
	}
	
	//gui::
	public Town[] getNodes () {
		int i = 0;
		for (Town t : townList) {
			i = i+1;
		}
		Town[] list = new Town[i];
		i = 0;
		for (Town t : townList) {
			list[i] = t;
			i = i + 1;
		}
		return list;
	}
	
	//gui::
	public Town getNodeOfIndex (int x) {
		return getNodes()[x];
	}
	
	public int getChitNumber() {
		return 2;
	}

	public boolean isBrigand() {
		return brigand;
	}

	public void setBrigand(boolean brigand) {
		this.brigand = brigand;
	}
}

