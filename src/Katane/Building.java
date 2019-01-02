/*********************************************************************************
*     File Name           :     Building
*     Created By          :     The LO43 Katane team
*     Creation Date       :     [2018-09-14 13:32]
*     Last Modified       :     [2019-01-01 19:02]
*     Description         :     The abstract class Building groups all the buildings
**********************************************************************************/

package Katane;

/* Abstract class for the buildings (Road, Dolorean and TimeTown) */
public abstract class Building {

	protected Player player;
	protected Coordinates coordinates;

	/* Constructor with the owner (Player) and the coordinates */
	public Building(Player player, Coordinates coor) {
		this.player = player;
		this.coordinates = coor;
	}
}
