/*********************************************************************************
*     File Name           :     Town.java
*     Created By          :     The LO43 Katane team
*     Creation Date       :     [2018-09-14 13:32]
*     Last Modified       :     [2019-01-05 23:48]
*     Description         :     Town is an abstract Building and is used to implement Dolorean and TimeTown
**********************************************************************************/

package Katane;

import java.util.ArrayList;

/* Class Town */
public abstract class Town extends Building {

	private ArrayList<Tile> adjacentTiles;
	/* Constructor - Owned by a Player and is on a Tile (tile list) */
	public Town(Player player, Coordinates coor) {
		super(player, coor);
		adjacentTiles = new ArrayList<Tile>();
		//must verify that you can build before add
		//this.tile.addBuilding(this);
	}
	
	public ArrayList<Tile> getAdjacentTiles() {
		return adjacentTiles;
	}
	
	public void setAdjacentTiles(ArrayList<Tile> adjTiles) {
		adjacentTiles = adjTiles;
	}
}
