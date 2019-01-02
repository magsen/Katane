/*********************************************************************************
*     File Name           :     Road.java
*     Created By          :     The LO43 Katane team
*     Creation Date       :     [2018-09-14 13:32]
*     Last Modified       :     [2019-01-02 01:55]
*     Description         :     Building type Road
**********************************************************************************/

package Katane;

import java.util.ArrayList;

/* Class Road which is a Building */
public class Road extends Building{

	private ArrayList<Road> adjacentRoad;

	/* Constructor - Owned by a Player and is on a Tile (tile list) */
	public Road(Player player, Coordinates coor) {
		super(player, coor);
		//must verify that you can build before add
		//this.tile.addBuilding(this);
	}

	/* Get the adjacent roads in the list */
	public ArrayList<Road> getAdjacentRoad() {
		return adjacentRoad;
	}

	/* Set the adjacent roads in the list */
	public void setAdjacentRoad(ArrayList<Road> adjacentRoad) {
		this.adjacentRoad = adjacentRoad;
	}
}
