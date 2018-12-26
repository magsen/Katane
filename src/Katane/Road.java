/*********************************************************************************
*     File Name           :     Road.java
*     Created By          :     The LO43 Katane team
*     Creation Date       :     [2018-09-14 13:32]
*     Last Modified       :     [2018-12-26 17:58]
*     Description         :     Building type Road
**********************************************************************************/

package Katane;

import java.util.ArrayList;

/* Class Road which is a Building */
public class Road extends Building{

	private ArrayList<Road> adjacentRoad;

	/* Constructor - Owned by a Player and is on a Tile (tile list) */
	public Road(Player player, Tile tile) {
		super(player, tile);
		this.tile = tile;
		//must verify that yu can build before add
		this.tile.addBuilding(this);
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
