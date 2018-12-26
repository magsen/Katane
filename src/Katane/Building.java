/*********************************************************************************
*     File Name           :     Building
*     Created By          :     The LO43 Katane team
*     Creation Date       :     [2018-09-14 13:32]
*     Last Modified       :     [2018-12-26 15:29]
*     Description         :     The abstract class Building groups all the buildings
**********************************************************************************/

package Katane;

/* Abstract class for the buildings (Road, Dolorean and TimeTown) */
public abstract class Building {

	protected Player player;
	protected Tile tile; //can be modified with coordinates

	/* Constructor with the owner (Player) and the coordinates */
	public Building(Player player, Tile tile) {
		//modif with coord
		this.player = player;
	}
}
