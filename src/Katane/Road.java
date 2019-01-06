/*********************************************************************************
*     File Name           :     Road.java
*     Created By          :     The LO43 Katane team
*     Creation Date       :     [2018-09-14 13:32]
*     Last Modified       :     [2019-01-06 01:32]
*     Description         :     Building type Road
**********************************************************************************/

package Katane;

import java.util.ArrayList;

/* Class Road which is a Building */
public class Road extends Building{

	private boolean isBeingUsed;
	/* Constructor - Owned by a Player and is on a Tile (tile list) */
	public Road(Player player, Coordinates coor) {
		super(player, coor);
		isBeingUsed = false;
		//must verify that you can build before add
		//this.tile.addBuilding(this);
	}

	/* Get the adjacent roads in the list */
	public ArrayList<Road> getAdjacentRoads() {
		return adjacentRoads;
	}

	/* Set the adjacent roads in the list */
	public void setAdjacentRoads(ArrayList<Road> adjacentRoads) {
		this.adjacentRoads = adjacentRoads;
	}

	public void addAdjacentRoad(Road road) {
		adjacentRoads.add(road);
	}
}
