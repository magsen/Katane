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

	/* Constructor - Owned by a Player and is on a Tile (tile list) */
	public Town(Player player, Coordinates coor) {
		super(player, coor);
		//must verify that you can build before add
		//this.tile.addBuilding(this);
	}
}
