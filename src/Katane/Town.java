/*********************************************************************************
*     File Name           :     Town.java
*     Created By          :     The LO43 Katane team
*     Creation Date       :     [2018-09-14 13:32]
*     Last Modified       :     [2018-12-26 18:05]
*     Description         :     Town is an abstract Building and is used to implement Dolorean and TimeTown
**********************************************************************************/

package Katane;

/* Class Town */
public abstract class Town extends Building {

	/* Constructor - Owned by a Player and is on a Tile (tile list) */
	public Town(Player player, Tile tile) {
		super(player, tile);
		this.tile = tile;
		//must verify that you can build before add
		this.tile.addBuilding(this);
	}

}
