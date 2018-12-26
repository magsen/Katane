/*********************************************************************************
*     File Name           :     TimeTown.java
*     Created By          :     The LO43 Katane team
*     Creation Date       :     [2018-09-14 13:32]
*     Last Modified       :     [2018-12-26 18:03]
*     Description         :     TimeTown is a Town and Town itself is a Building.
*     					TimeTown is the evolution of a Dolorean.
**********************************************************************************/

package Katane;

/* Class TimeTown is a dolorean that has been evolved */
public class TimeTown extends Town {

	/* Constructor - Owned by a Player and is on a Tile (tile list) */
	public TimeTown(Player player, Tile tile) {
		super(player, tile);
	}

}
